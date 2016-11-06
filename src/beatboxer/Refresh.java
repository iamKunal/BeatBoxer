package beatboxer;

import java.sql.*;

public class Refresh extends CreateConnection {

    public void refreshArtist() {
        try {
            PreparedStatement statement;
            String sql;
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select artist.artistid from artist where (select count(*) from trackinfo where trackinfo.artistid = artist.artistid)=0");
            while (res.next()) {
                sql = "delete from artist where artistid = ?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, res.getInt("artistid"));
                statement.executeUpdate();
            }
        } catch (SQLException e) {

        }
    }

    public void refreshAlbum() {
        try {
            PreparedStatement statement;
            String sql;
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select album.albumid from album where (select count(*) from trackinfo where trackinfo.albumid = album.albumid)=0; ");
            while (res.next()) {
                sql = "delete from album where albumid = ?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, res.getInt("albumid"));
                statement.executeUpdate();
            }
        } catch (SQLException e) {

        }
    }
}
