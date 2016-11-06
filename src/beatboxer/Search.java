package beatboxer;

import java.sql.*;
import javafx.collections.ObservableList;

public class Search extends CreateConnection {

    public ObservableList<BBItem> SearchAlbum(String string) {
        try {
            String sql = "select * from album WHERE albumname LIKE ? order by albumname";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, '%' + string + '%');
            ResultSet res = statement.executeQuery();
            return BBGenerator.item(res);
        } catch (Exception e) {
            return null;
        }
    }

    public ObservableList<BBSong> SearchTrack(String string) {
        try {
            String sql = "select * from track natural join artist natural join album natural join trackinfo where trackname LIKE ? order by trackname";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + string + "%");
            ResultSet res = statement.executeQuery();
            return BBGenerator.song(res);
        } catch (Exception e) {
        }
        return null;
    }

    public ObservableList<BBItem> SearchArtist(String string) {
        try {
            String sql = "select * from artist WHERE artistname LIKE ? order by artistname";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, '%' + string + '%');
            ResultSet res = statement.executeQuery();
            return BBGenerator.item(res);
        } catch (Exception e) {
            return null;
        }
    }
}
