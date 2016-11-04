package beatboxer;

import java.sql.*;

public class Favourites extends CreateConnection {

    public void favourite(int trackid) {
        String sql = "update track set favourite = true where trackid = ?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, trackid);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void unfavourite(int trackid) {
        String sql = "update track set favourite = false where trackid = ?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, trackid);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
