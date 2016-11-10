package beatboxer;

import java.sql.*;

public class Favourites extends CreateConnection {

    public void favourite(int trackid) {
        try {
            PreparedStatement statement = con.prepareStatement("call favourite(?)");
            statement.setInt(1, trackid);
            statement.execute();
        } catch (SQLException e) {

        }
    }

    public void unfavourite(int trackid) {
        try {
            PreparedStatement statement = con.prepareStatement("call unfavourite(?)");
            statement.setInt(1, trackid);
            statement.execute();
        } catch (SQLException e) {

        }
    }
}
