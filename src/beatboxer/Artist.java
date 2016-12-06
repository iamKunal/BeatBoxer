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
public class Artist extends CreateConnection {

    public ObservableList<BBSong> ShowAllTracksByArtists(String Artistname) {
        PreparedStatement statement = null;
        try {
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track WHERE artistname = ? order by trackname";
            statement = con.prepareStatement(sql);
            statement.setString(1, Artistname);
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

    public ObservableList<String> SearchArtist(String string) {
        PreparedStatement statement = null;
        ResultSet res = null;
        ObservableList list = FXCollections.observableArrayList();
        try {
            String sql = "select distinct artistname from track WHERE artistname LIKE ? order by artistname";
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
