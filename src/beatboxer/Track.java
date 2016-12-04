/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.sql.*;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author SANJAY TAYAL
 */
public class Track extends CreateConnection {

    public void AddTrack(String trackName, String artistName, String albumName, String location, String genre) {
        if (artistName.isEmpty()) {
            artistName = "Unknown";
        }
        if (albumName.isEmpty()) {
            albumName = "Unknown";
        }
        System.out.println("runned");
        Statement st = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        try {
            st = con.createStatement();
            res = st.executeQuery("select newid from id");
            res.next();
            int trackid = res.getInt("newid");
            res.close();
            int artistid, albumid;
            statement = con.prepareStatement("select artistid from artist where lower(artistname) = ?");
            statement.setString(1, artistName.toLowerCase().trim());
            res = statement.executeQuery();
            if (!res.next()) {
                res.close();
                res = st.executeQuery("select newid from id");
                res.next();
                artistid = res.getInt("newid");
                res.close();
                statement = con.prepareStatement("insert into artist(artistid,artistname) values (?,?)");
                statement.setInt(1, artistid);
                statement.setString(2, artistName.trim());
                statement.execute();
            } else {
                artistid = res.getInt("artistid");
                res.close();
            }
            statement = con.prepareStatement("select albumid from album where lower(albumname) = ?");
            statement.setString(1, albumName.toLowerCase().trim());
            res = statement.executeQuery();
            if (!res.next()) {
                res.close();
                res = st.executeQuery("select newid from id");
                res.next();
                albumid = res.getInt("newid");
                res.close();
                statement = con.prepareStatement("insert into album(albumid,albumname) values (?,?)");
                statement.setInt(1, albumid);
                statement.setString(2, albumName.trim());
                statement.execute();
            } else {
                albumid = res.getInt("albumid");
                res.close();
            }
            statement = con.prepareStatement("insert into track(trackid,artistid,albumid,trackname,location,genre) values(?,?,?,?,?,?)");
            statement.setInt(1, trackid);
            statement.setInt(2, artistid);
            statement.setInt(3, albumid);
            statement.setString(4, trackName.trim());
            statement.setString(5, location.replace("\\", "/"));
            statement.setString(6, genre.trim());
            statement.execute();
        } catch (SQLException e) {
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {

                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public void UpdateTrack(int TrackId, String newTrackName, String newArtistName, String newAlbumName, String newGenre) {
        Statement st = null;
        PreparedStatement statement = null;
        int artistid, albumid;
        try {
            st = con.createStatement();
            statement = con.prepareStatement("select artistid from artist where lower(artistname) = ?");
            statement.setString(1, newArtistName.toLowerCase().trim());
            ResultSet res = statement.executeQuery();
            if (!res.next()) {
                res.close();
                res = st.executeQuery("select newid from id");
                res.next();
                artistid = res.getInt("newid");
                res.close();
                st.clearBatch();
                statement.clearBatch();
                statement = con.prepareStatement("insert into artist(artistid,artistname) values (?,?)");
                statement.setInt(1, artistid);
                statement.setString(2, newArtistName.trim());
                statement.execute();
            } else {
                artistid = res.getInt("artistid");
                res.close();
            }
            statement.clearBatch();
            statement = con.prepareStatement("select albumid from album where lower(albumname) = ?");
            statement.setString(1, newAlbumName.toLowerCase().trim());
            res = statement.executeQuery();
            if (!res.next()) {
                res.close();
                res = st.executeQuery("select newid from id");
                res.next();
                albumid = res.getInt("newid");
                res.close();
                st.clearBatch();
                statement.clearBatch();
                statement = con.prepareStatement("insert into album(albumid,albumname) values (?,?)");
                statement.setInt(1, albumid);
                statement.setString(2, newAlbumName.trim());
                statement.execute();
            } else {
                albumid = res.getInt("albumid");
                res.close();
            }
            statement.clearBatch();
            statement = con.prepareStatement("update track set trackname = ?,genre = ?,artistid = ?,albumid = ? where trackid = ?");
            statement.setString(1, newTrackName.trim());
            statement.setString(2, newGenre.trim());
            statement.setInt(3, artistid);
            statement.setInt(4, albumid);
            statement.setInt(5, TrackId);
            statement.execute();
            statement.clearBatch();
            statement.close();
        } catch (SQLException e) {

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {

                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public void DeleteTrack(int TrackId) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("delete from track where trackid = ?");
            statement.setInt(1, TrackId);
            statement.execute();
            statement.clearBatch();
            statement = con.prepareStatement("delete from playlistinfo where trackid =  ?");
            statement.setInt(1, TrackId);
            statement.execute();
            statement.clearBatch();
        } catch (SQLException e) {

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }

    }

    public ObservableList<BBSong> ShowAllTracks() {
        Statement count = null;
        try {
            count = con.createStatement();
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track natural join artist natural join album order by trackname";
            return BBGenerator.song(count.executeQuery(sql));
        } catch (SQLException e) {

        } finally {
            if (count != null) {
                try {
                    count.close();
                } catch (SQLException e) {

                }
            }
        }
        return null;
    }

    public ObservableList<BBSong> ShowAllFavourites() {
        Statement tracks = null;
        try {
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track natural join artist natural join album where favourite = 1 order by trackname";
            tracks = con.createStatement();
            return BBGenerator.song(tracks.executeQuery(sql));
        } catch (SQLException e) {

        } finally {
            if (tracks != null) {
                try {
                    tracks.close();
                } catch (SQLException e) {

                }
            }
        }
        return null;
    }

    public ObservableList<BBSong> ShowByMode(String mode) {
        mode = mode.toLowerCase();
        ArrayList<String> genre = new ArrayList<>();
        switch (mode) {
            case "driving":
                genre.add("Rap");
                genre.add("Rock");
                genre.add("Romantic");
                genre.add("Pop");
                genre.add("Soul and R&B");
                return BBGenerator.song(ShowByGenre(genre));
            case "exercise":
                genre.add("Dance");
                genre.add("Hip-hop");
                genre.add("Hiphop");
                genre.add("Edm");
                genre.add("Rock");
                genre.add("Pop");
                return BBGenerator.song(ShowByGenre(genre));
            case "party":
                genre.add("House");
                genre.add("Progresive");
                genre.add("Edm");
                genre.add("Dance");
                genre.add("Rap");
                genre.add("Hip hop");
                genre.add("Hip-hop");
                genre.add("Pop");
                genre.add("Electro");
                return BBGenerator.song(ShowByGenre(genre));
            case "soothing":
                genre.add("Romantic");
                genre.add("Ambient");
                genre.add("Soft");
                genre.add("Trap");
                genre.add("Soothing");
                genre.add("Paino");
                genre.add("Tropical");
                genre.add("Soul");
                return BBGenerator.song(ShowByGenre(genre));
        }
        return null;
    }

    public ObservableList<BBSong> ShowRecentlyAdded() {
        Statement tracks = null;
        try {
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track natural join artist natural join album where strftime('%s',current_timestamp) - strftime('%s',dateadded) <= 86400 order by dateadded desc limit 50";
            tracks = con.createStatement();
            return BBGenerator.song(tracks.executeQuery(sql));
        } catch (SQLException e) {

        } finally {
            if (tracks != null) {
                try {
                    tracks.close();
                } catch (SQLException e) {

                }
            }
        }
        return null;
    }

    private ResultSet ShowByGenre(ArrayList<String> genre) {
        PreparedStatement tracks = null;
        try {
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track natural join artist natural join album where genre like ?";
            for (int i = 0; i < genre.size() - 1; i++) {
                sql += " or genre like ?";
            }
            sql += " order by trackname";
            tracks = con.prepareStatement(sql);
            for (int i = 0; i < genre.size(); i++) {
                tracks.setString(i + 1, '%' + genre.get(i) + '%');
            }
            return tracks.executeQuery();
        } catch (SQLException e) {

        } finally {
            if (tracks != null) {
                try {
                    tracks.close();
                } catch (SQLException e) {

                }
            }
        }
        return null;
    }

    public ObservableList<BBSong> SearchTrack(String string) {
        PreparedStatement statement = null;
        try {
            String sql = "select * from track natural join artist natural join album where trackname like ? order by trackname";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + string + "%");
            return BBGenerator.song(statement.executeQuery());
        } catch (Exception e) {
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
        return null;
    }

    public void favourite(int trackid) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("update track set favourite = 1 where trackid = ?");
            statement.setInt(1, trackid);
            statement.execute();
        } catch (SQLException e) {

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public void unfavourite(int trackid) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("update track set favourite = 0 where trackid=?");
            statement.setInt(1, trackid);
            statement.execute();
        } catch (SQLException e) {

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }
}
