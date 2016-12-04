/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

import java.sql.*;
import javafx.collections.ObservableList;

/**
 *
 * @author SANJAY TAYAL
 */
public class Album extends CreateConnection {

    public ObservableList<BBItem> ShowAllAlbums() {
        Statement count = null;
        try {
            count = con.createStatement();
            return BBGenerator.item(count.executeQuery("Select * from album order by albumname"));
        } catch (SQLException e) {

        } finally {
            if (count != null) {
                try {
                    count.close();
                } catch (SQLException e) {

                }
            }
        }
        return null;
    }

    public ObservableList<BBSong> ShowAllTracksinAlbum(int albumId) {
        PreparedStatement statement = null;
        try {
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track natural join artist natural join album WHERE albumid = ? order by trackname";
            statement = con.prepareStatement(sql);
            statement.setInt(1, albumId);
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

    public ObservableList<BBItem> SearchAlbum(String string) {
        PreparedStatement statement = null;
        try {
            String sql = "select * from album WHERE albumname LIKE ? order by albumname";
            statement = con.prepareStatement(sql);
            statement.setString(1, '%' + string + '%');
            return BBGenerator.item(statement.executeQuery());
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
