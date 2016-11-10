package beatboxer;

import java.sql.*;

public class AddTrack extends CreateConnection {

    public AddTrack(String trackName, String artistName, String albumName, String location, String genre) {
        try {
            PreparedStatement statement = con.prepareStatement("call addtrack(?,?,?,?,?)");
            statement.setString(1, trackName);
            statement.setString(2, artistName);
            statement.setString(3, albumName);
            statement.setString(4, location.replace("\\", "/"));
            statement.setString(5, genre);
            statement.execute();
        } catch (SQLException e) {

        }
    }
}
