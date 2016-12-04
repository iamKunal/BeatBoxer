package beatboxer;

import javafx.collections.*;

import java.sql.*;

public class BBGenerator {

    public static ObservableList<BBItem> item(ResultSet res) {
        ObservableList<BBItem> list = FXCollections.observableArrayList();
        try {
            while (res.next()) {
                list.add(new BBItem(res.getInt(1), res.getString(2)));
            }
            res.close();
        } catch (SQLException e) {
        }
        return list;
    }

    public static ObservableList<BBSong> song(ResultSet res) {
        ObservableList<BBSong> list = FXCollections.observableArrayList();
        try {
            while (res.next()) {
                list.add(new BBSong(res.getInt("trackid"), res.getString("trackname"), res.getString("albumname"), res.getString("artistname"), res.getString("genre"), res.getString("location"), res.getBoolean("favourite")));
            }
            res.close();
        } catch (SQLException e) {
        }
        return list;
    }

    public static int find(ObservableList<BBSong> haystack, BBSong needle) {
        for (int i = 0; i < haystack.size(); i++) {
            if (haystack.get(i).getId() == needle.getId()) {
                return i;
            }
        }
        return -1;
    }
}
