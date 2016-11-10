package beatboxer;

import java.sql.*;

public class PlayListInfo extends CreateConnection {

    public void addtrack(int playlistid, int trackid) {
        try {
            PreparedStatement statement = con.prepareStatement("call addtracktoplaylist(?,?)");
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
            statement.execute();
        } catch (SQLException e) {

        }
    }

    public void deletetrack(int playlistid, int trackid) {
        try {
            PreparedStatement statement = con.prepareStatement("call deletefromplaylist(?,?)");
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
            statement.execute();
        } catch (SQLException e) {

        }
    }

    public void move(int playlistid, int trackid, String direction) {
        try {
            PreparedStatement statement = con.prepareStatement("call movetrack(?,?,?)");
            statement.setInt(1, playlistid);
            statement.setInt(2, trackid);
            if (direction.equals("up")) {
                statement.setInt(3, 0);
            } else {
                statement.setInt(3, 1);
            }
            statement.execute();
        } catch (SQLException e) {

        }
    }
}
