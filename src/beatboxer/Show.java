package beatboxer;

import java.sql.*;
import javafx.collections.*;

public class Show extends CreateConnection {

    public ObservableList<BBSong> ShowAllTracks() {
        try {
            Statement count = con.createStatement();
            ResultSet res = count.executeQuery("select * from track natural join artist natural join album natural join trackinfo order by trackname");
            return BBGenerator.song(res);
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBItem> ShowAllArtists() {
        try {
            Statement count = con.createStatement();
            ResultSet res = count.executeQuery("Select * from artist order by artistname");
            return BBGenerator.item(res);
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBSong> ShowAllTracksByArtists(int artistId) {
        try {
            String sql = "select * from track natural join artist natural join album natural join trackinfo WHERE artistid = ? order by trackname";
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
            ResultSet res = count.executeQuery("Select * from album order by albumname");
            return BBGenerator.item(res);
        } catch (SQLException e) {

        }
        return null;
    }

    public ObservableList<BBSong> ShowAllTracksinAlbum(int albumId) {
        try {
            String sql = "select * from track natural join artist natural join album natural join trackinfo WHERE albumid = ? order by trackname";
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
            ResultSet res = count.executeQuery("Select * from playlist order by playlistname");
            ObservableList<BBItem> list = FXCollections.observableArrayList();
            list.add(new BBItem(0, "All Songs"));
            list.add(new BBItem(-1, "Favourites"));
            list.addAll(BBGenerator.item(res));
            return list;
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
            String sql = "select * from track natural join artist natural join album natural join trackinfo where favourite = true order by trackname";
            Statement tracks = con.createStatement();
            ResultSet res = tracks.executeQuery(sql);
            return BBGenerator.song(res);
        } catch (SQLException e) {

        }
        return null;
    }
}
