package beatboxer;

import java.sql.*;

public class Delete extends CreateConnection {

    public void deleteTrack(int TrackId) {
        try {
            PreparedStatement statement = con.prepareStatement("call deletetrack(?)");
            statement.setInt(1, TrackId);
            statement.executeUpdate();
        } catch (SQLException e) {
            
        }

    }
}
