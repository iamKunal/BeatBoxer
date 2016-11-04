package beatboxer;

import java.sql.*;

public class Favourites extends CreateConnection {

    public void add(int trackid) {
        String sql = "insert into favourites(trackid) values(?)";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, trackid);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void delete(int trackid) {
        String sql = "delete from favourites where trackid = ?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, trackid);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
