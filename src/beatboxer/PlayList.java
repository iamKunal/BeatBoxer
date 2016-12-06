package beatboxer;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlayList extends CreateConnection {

    public void create(String playlistname) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("insert into playlist(playlistname) values(?)");
            statement.setString(1, playlistname.trim());
            statement.execute();
        } catch (Exception e) {
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public void delete(int playlistid) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("delete from playlist where playlistid = ?");
            statement.setInt(1, playlistid);
            statement.execute();
            statement.clearBatch();
            statement = con.prepareStatement("delete from playlistinfo where playlistid =?");
            statement.setInt(1, playlistid);
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

    public void update(int playlistid, String newname) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("update playlist set playlistname = ? where playlistid = ?");
            statement.setString(1, newname);
            statement.setInt(2, playlistid);
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

    public void addtrack(int playlistid, int trackid) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("select count(*) from playlistinfo where playlistid = ?");
            statement.setInt(1, playlistid);
            ResultSet res = statement.executeQuery();
            res.next();
            int torder = res.getInt(1);
            res.close();
            statement.clearBatch();
            statement = con.prepareStatement("insert into playlistinfo(playlistid,trackid,trackorder) values (?,?,?)");
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
            statement.setInt(3, torder + 1);
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

    public void deletetrack(int playlistid, int trackid) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("delete from playlistinfo where playlistid=? and trackid=?");
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
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

    public void move(int playlistid, int trackid, String direction) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("select trackorder from playlistinfo where playlistid = ? and trackid = ?");
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
            ResultSet res = statement.executeQuery();
            res.next();
            int torder = res.getInt("trackorder");
            res.close();
            statement.clearBatch();
            int swaptid, torder1;
            if (direction.equals("up")) {
                statement = con.prepareStatement("select trackid, trackorder from playlistinfo where playlistid = ? and trackorder < ? order by trackorder desc limit 1");
                statement.setInt(1, playlistid);
                statement.setInt(2, torder);
                res = statement.executeQuery();
                res.next();
                swaptid = res.getInt("trackid");
                torder1 = res.getInt("trackorder");
                res.close();
                statement.clearBatch();
                statement = con.prepareStatement("update playlistinfo set trackorder = ? where playlistid = ? and trackid = ?");
                statement.setInt(1, torder);
                statement.setInt(2, playlistid);
                statement.setInt(3, swaptid);
                statement.execute();
                statement.clearBatch();
                statement = con.prepareStatement("update playlistinfo set trackorder = ? where playlistid = ? and trackid = ?");
                statement.setInt(1, torder1);
                statement.setInt(2, playlistid);
                statement.setInt(3, trackid);
                statement.execute();
            } else {
                statement = con.prepareStatement("select trackid, trackorder from playlistinfo where playlistid = ? and trackorder > ? order by trackorder limit 1");
                statement.setInt(1, playlistid);
                statement.setInt(2, torder);
                res = statement.executeQuery();
                res.next();
                swaptid = res.getInt("trackid");
                torder1 = res.getInt("trackorder");
                res.close();
                statement.clearBatch();
                statement = con.prepareStatement("update playlistinfo set trackorder = ? where playlistid = ? and trackid = ?");
                statement.setInt(1, torder);
                statement.setInt(2, playlistid);
                statement.setInt(3, swaptid);
                statement.execute();
                statement.clearBatch();
                statement = con.prepareStatement("update playlistinfo set trackorder = ? where playlistid = ? and trackid = ?");
                statement.setInt(1, torder1);
                statement.setInt(2, playlistid);
                statement.setInt(3, trackid);
                statement.execute();
            }
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

    public ObservableList<BBItem> ShowAllPlayLists() {
        Statement count = null;
        try {
            count = con.createStatement();
            String sql = "Select * from playlist order by playlistname";
            ObservableList<BBItem> list = FXCollections.observableArrayList();
            list.add(new BBItem(0, "All Songs"));
            list.add(new BBItem(-1, "Favourites"));
            list.add(new BBItem(-2, "Recently Added"));
            list.addAll(BBGenerator.item(count.executeQuery(sql)));
            return list;
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

    public ObservableList<BBSong> ShowAllTracksinPlayList(int playlistid) {
        PreparedStatement tracks = null;
        try {
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track natural join playlistinfo where playlistid = ? order by trackorder";
            tracks = con.prepareStatement(sql);
            tracks.setInt(1, playlistid);
            return BBGenerator.song(tracks.executeQuery());
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
}
