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
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("insert into track(trackname,artistname,albumname,location,genre) values(?,?,?,?,?)");
            statement.setString(1, trackName.trim());
            statement.setString(2, artistName.trim());
            statement.setString(3, albumName.trim());
            statement.setString(4, location.replace("\\", "/"));
            statement.setString(5, genre.trim());
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

    public void UpdateTrack(int TrackId, String newTrackName, String newArtistName, String newAlbumName, String newGenre) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("update track set trackname=?,artistname=?,albumname=?,genre=? where trackid = ?");
            statement.setString(1, newTrackName.trim());
            statement.setString(2, newArtistName.trim());
            statement.setString(3, newAlbumName.trim());
            statement.setString(4, newGenre.trim());
            statement.setInt(5, TrackId);
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
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track order by trackname";
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
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track where favourite = 1 order by trackname";
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
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track where strftime('%s',current_timestamp) - strftime('%s',dateadded) <= 86400 order by dateadded desc limit 50";
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
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track where lower(genre) like ?";
            for (int i = 0; i < genre.size() - 1; i++) {
                sql += " or lower(genre) like ?";
            }
            sql += " order by trackname";
            tracks = con.prepareStatement(sql);
            for (int i = 0; i < genre.size(); i++) {
                tracks.setString(i + 1, '%' + genre.get(i).toLowerCase() + '%');
            }
            return tracks.executeQuery();
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBSong> SearchTrack(String string) {
        PreparedStatement statement = null;
        try {
            String sql = "select * from track where trackname like ? order by trackname";
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
