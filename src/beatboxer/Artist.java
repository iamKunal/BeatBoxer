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
public class Artist extends CreateConnection {

    public ObservableList<BBItem> ShowAllArtists() {
        Statement count = null;
        try {
            count = con.createStatement();
            return BBGenerator.item(count.executeQuery("Select * from artist order by artistname"));
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

    public ObservableList<BBSong> ShowAllTracksByArtists(int artistId) {
        PreparedStatement statement = null;
        try {
            String sql = "select trackid,trackname,artistname,albumname,location,genre,favourite from track natural join artist natural join album WHERE artistid = ? order by trackname";
            statement = con.prepareStatement(sql);
            statement.setInt(1, artistId);
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

    public ObservableList<BBItem> SearchArtist(String string) {
        PreparedStatement statement = null;
        try {
            String sql = "select * from artist WHERE artistname LIKE ? order by artistname";
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
