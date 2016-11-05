package beatboxer;

import java.sql.*;

public class AddTrack extends CreateConnection {

    private String TrackName;
    private String ArtistName;
    private String AlbumName;
    private String Location;
    private String Genre;
    private int TrackId;
    private int ArtistId;
    private int AlbumId;

    public AddTrack(String trackName, String artistName, String albumName, String location, String genre) {
        TrackName = trackName;
        ArtistName = artistName;
        AlbumName = albumName;
        Location = location.replace("\\", "/");
        Genre = genre;
        addTrack();
        addartist();
        addalbum();
        addtrackinfo();
    }

    private void addartist() {
        try {
            if (ArtistName.equals(null)) {
                ArtistId = 0;
                throw new Exception();
            }
            Statement st = con.createStatement();
            ResultSet count = st.executeQuery("select count(*) from artist");
            count.next();
            boolean flag = true;
            if (count.getInt("count(*)") == 0) {
                flag = false;
            }

            String sql = "select count(artistid) from artist where artistname = ?";
            PreparedStatement check = con.prepareStatement(sql);
            check.setString(1, ArtistName);
            ResultSet res = check.executeQuery();
            res.next();
            if (res.getInt("count(artistid)") == 0 || !flag) {
                ArtistId = count.getInt("count(*)") + 1;
                sql = "insert into artist(artistid,artistname) values(?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, ArtistId);
                statement.setString(2, ArtistName);
                statement.executeUpdate();
            } else {
                sql = "select artistid from artist where artistname = ?";
                check = con.prepareStatement(sql);
                check.setString(1, ArtistName);
                res = check.executeQuery();
                res.next();
                ArtistId = res.getInt("artistid");
            }
        } catch (Exception e) {

        }
    }

    private void addalbum() {
        try {
            if (AlbumName.equals(null)) {
                AlbumId = 0;
                throw new Exception();
            }
            Statement st = con.createStatement();
            ResultSet count = st.executeQuery("select count(*) from album");
            count.next();
            boolean flag = true;
            if (count.getInt("count(*)") == 0) {
                flag = false;
            }

            String sql = "select count(albumid) from album where albumname = ?";
            PreparedStatement check = con.prepareStatement(sql);
            check.setString(1, AlbumName);
            ResultSet res = check.executeQuery();
            res.next();
            if (res.getInt("count(albumid)") == 0 || !flag) {
                AlbumId = count.getInt("count(*)") + 1;
                sql = "insert into album(albumid,albumname) values(?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, AlbumId);
                statement.setString(2, AlbumName);
                statement.executeUpdate();
            } else {
                sql = "select albumid from album where albumname = ?";
                check = con.prepareStatement(sql);
                check.setString(1, AlbumName);
                res = check.executeQuery();
                res.next();
                AlbumId = res.getInt("albumid");
            }
        } catch (Exception e) {

        }
    }

    private void addTrack() {
        try {
            Statement count = con.createStatement();
            ResultSet res = count.executeQuery("select count(*) from track");
            res.next();
            TrackId = res.getInt("count(*)") + 1;
            String sql = "insert into track(trackid,trackname,genre,location) values(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, TrackId);
            statement.setString(2, TrackName);
            statement.setString(3, Genre);
            statement.setString(4, Location);
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    private void addtrackinfo() {
        try {
            String sql = "insert into trackinfo(trackid,artistid,albumid) values(?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, TrackId);
            statement.setInt(2, ArtistId);
            statement.setInt(3, AlbumId);
            statement.executeUpdate();

        } catch (Exception e) {

        }
    }
}
