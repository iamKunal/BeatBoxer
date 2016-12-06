/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SANJAY TAYAL
 */
public class Album extends CreateConnection {

    public ObservableList<BBSong> ShowAllTracksinAlbum(String Albumname) {
        PreparedStatement statement = null;
        try {
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track WHERE albumname = ? order by trackname";
            statement = con.prepareStatement(sql);
            statement.setString(1, Albumname);
            return BBGenerator.song(statement.executeQuery());
        } catch (SQLException e) {

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
        return null;

    }

    public ObservableList<String> SearchAlbum(String string) {
        PreparedStatement statement = null;
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet res = null;
        try {
            String sql = "select distinct albumname from track WHERE albumname LIKE ? order by albumname";
            statement = con.prepareStatement(sql);
            statement.setString(1, '%' + string + '%');
            res = statement.executeQuery();
            while (res.next()) {
                list.add(res.getString(1));
            }
            res.close();
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }
}
