package beatboxer;

import java.sql.*;
import javafx.collections.*;

public class Show extends CreateConnection {

    public ObservableList<BBSong> ShowAllTracks() {
        try {
            Statement count = con.createStatement();
            ResultSet res = count.executeQuery("select * from track natural join artist natural join album natural join trackinfo");
            return BBGenerator.song(res);
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBItem> ShowAllArtists() {
        try {
            Statement count = con.createStatement();
            ResultSet res = count.executeQuery("Select * from artist");
            return BBGenerator.item(res);
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBSong> ShowAllTracksByArtists(int artistId) {
        try {
            String sql = "select * from track NATURAL JOIN trackinfo WHERE artistid=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, artistId);
            ResultSet res = statement.executeQuery();
            return BBGenerator.song(res);
        } catch (SQLException e) {

        }
        return null;

    }

    public ObservableList<BBItem> ShowAllAlbums() {
        try {
            Statement count = con.createStatement();
            ResultSet res = count.executeQuery("Select * from album");
            return BBGenerator.item(res);
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBSong> ShowAllTracksinAlbum(int albumId) {
        try {
            String sql = "select * from track NATURAL JOIN trackinfo WHERE albumid=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, albumId);
            ResultSet res = statement.executeQuery();
            return BBGenerator.song(res);
        } catch (SQLException e) {

        }
        return null;

    }

    public ObservableList<BBItem> ShowAllPlayLists() {
        try {
            Statement count = con.createStatement();
            ResultSet res = count.executeQuery("Select * from playlist");
            return BBGenerator.item(res);
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBSong> ShowAllTracksinPlayList(int playlistid) {
        try {
            String sql = "select * from track natural join artist natural join album natural join trackinfo natural join playlistinfo where playlistid = ? order by trackorder";
            PreparedStatement tracks = con.prepareStatement(sql);
            tracks.setInt(1, playlistid);
            ResultSet res = tracks.executeQuery();
            return BBGenerator.song(res);
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBSong> ShowAllFavourites() {
        try {
            String sql = "select * from track natural join artist natural join album natural join trackinfo where favourite = true";
            Statement tracks = con.createStatement();
            ResultSet res = tracks.executeQuery(sql);
            return BBGenerator.song(res);
        } catch (SQLException e) {

        }
        return null;
    }
}
