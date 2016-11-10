package beatboxer;

import java.sql.*;

public class PlayList extends CreateConnection {

    public void create(String playlistname) {
        try {
            PreparedStatement statement = con.prepareStatement("call createplaylist(?)");
            statement.setString(1, playlistname);
            statement.execute();
        } catch (Exception e) {
        }
    }

    public void delete(int playlistid) {
        try {
            PreparedStatement statement = con.prepareStatement("call deleteplaylist(?)");
            statement.setInt(1, playlistid);
            statement.execute();
        } catch (SQLException e) {

        }
    }

    public void update(int playlistid, String newname) {
        try {
            String sql = "call updateplaylist(?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            statement.setString(2, newname);
            statement.execute();
        } catch (SQLException e) {

        }
    }
}
