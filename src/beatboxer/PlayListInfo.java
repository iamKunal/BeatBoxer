package beatboxer;

import java.sql.*;

public class PlayListInfo extends CreateConnection {

    private int trackorder;

    public void addtrack(int playlistid, int trackid) {
        try {
            String sql = "select count(trackorder) from playlistinfo where playlistid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            ResultSet res = statement.executeQuery();
            res.next();
            trackorder = res.getInt("count(trackorder)") + 1;
            sql = "insert into playlistinfo(playlistid,trackid,trackorder) values(?,?,?)";
            statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
            statement.setInt(3, trackorder);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deletetrack(int playlistid, int trackid) {
        try {
            String sql = "select trackorder from playlistinfo where playlistid = ? and trackid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
            ResultSet res = statement.executeQuery();
            res.next();
            trackorder = res.getInt("trackorder");
            sql = "delete from playlistinfo where playlistid = ? and trackid = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
            statement.executeUpdate();
            sql = "update playlistinfo set trackorder = trackorder - 1 where playlistid = ? and trackorder > ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            statement.setInt(2, trackorder);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void move(int playlistid, int trackid, String direction) {
        if (direction.equals("up")) {
            String sql = "select trackorder from playlistinfo where playlistid = ? and trackid = ?";
            try {
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, playlistid);
                statement.setInt(2, trackid);
                ResultSet res = statement.executeQuery();
                res.next();
                trackorder = res.getInt("trackorder");
                if (trackorder > 1) {
                    sql = "select trackid from playlistinfo where playlistid = ? and trackorder = ?";
                    statement = con.prepareStatement(sql);
                    statement.setInt(1, playlistid);
                    statement.setInt(2, trackorder - 1);
                    res = statement.executeQuery();
                    res.next();
                    int prevtrackid = res.getInt("trackid");
                    sql = "update playlistinfo set trackorder = trackorder + 1 where playlistid = ? and trackid = ?";
                    statement = con.prepareStatement(sql);
                    statement.setInt(1, playlistid);
                    statement.setInt(2, prevtrackid);
                    statement.executeUpdate();
                    sql = "update playlistinfo set trackorder = trackorder - 1 where playlistid = ? and trackid = ?";
                    statement = con.prepareStatement(sql);
                    statement.setInt(1, playlistid);
                    statement.setInt(2, trackid);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {

            }
        } else {
            String sql = "select trackorder from playlistinfo where playlistid = ? and trackid = ?";
            try {
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, playlistid);
                statement.setInt(2, trackid);
                ResultSet res = statement.executeQuery();
                res.next();
                trackorder = res.getInt("trackorder");
                sql = "select count(*) from playlistinfo where playlistid = ?";
                PreparedStatement count = con.prepareStatement(sql);
                count.setInt(1, playlistid);
                res = count.executeQuery();
                res.next();
                if (trackorder < res.getInt("count(*")) {
                    sql = "select trackid from playlistinfo where playlistid = ? and trackorder = ?";
                    statement = con.prepareStatement(sql);
                    statement.setInt(1, playlistid);
                    statement.setInt(2, trackorder + 1);
                    res = statement.executeQuery();
                    res.next();
                    int nexttrackid = res.getInt("trackid");
                    sql = "update playlistinfo set trackorder = trackorder - 1 where playlistid = ? and trackid = ?";
                    statement = con.prepareStatement(sql);
                    statement.setInt(1, playlistid);
                    statement.setInt(2, nexttrackid);
                    statement.executeUpdate();
                    sql = "update playlistinfo set trackorder = trackorder + 1 where playlistid = ? and trackid = ?";
                    statement = con.prepareStatement(sql);
                    statement.setInt(1, playlistid);
                    statement.setInt(2, trackid);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {

            }
        }
    }
}
