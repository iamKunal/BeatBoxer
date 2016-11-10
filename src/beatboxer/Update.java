package beatboxer;

import java.sql.*;

public class Update extends CreateConnection {

    public void updateTrack(int TrackId, String newTrackName, String newArtistName, String newAlbumName, String newGenre) {
        try {
            PreparedStatement statement = con.prepareStatement("call updatetrack(?,?,?,?,?)");
            statement.setInt(1, TrackId);
            statement.setString(2, newTrackName);
            statement.setString(3, newArtistName);
            statement.setString(4, newAlbumName);
            statement.setString(5, newGenre);
            statement.execute();
        } catch (SQLException e) {

        }
    }
}
