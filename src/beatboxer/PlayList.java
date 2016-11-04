package beatboxer;

import java.sql.*;

public class PlayList extends CreateConnection {

    private String PlayListName;
    private int PlayListId;

    public void create(String playlistname) {
        PlayListName = playlistname;
        try {
            Statement st = con.createStatement();
            ResultSet count = st.executeQuery("select count(*) from playlist");
            count.next();
            boolean flag = true;
            if (count.getInt("count(*)") == 0) {
                flag = false;
            }

            String sql = "select count(playlistid) from playlist where playlistname = ?";
            PreparedStatement check = con.prepareStatement(sql);
            check.setString(1, PlayListName);
            ResultSet res = check.executeQuery();
            res.next();
            if (res.getInt("count(playlistid)") == 0 || !flag) {
                PlayListId = count.getInt("count(*)") + 1;
                sql = "insert into playlist(playlistid,playlistname) values(?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, PlayListId);
                statement.setString(2, PlayListName);
                statement.executeUpdate();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
        }
    }

    public void delete(int playlistid) {
        try {
            String sql = "delete from playlist where playlistid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            statement.executeUpdate();
            sql = "delete from playlistinfo where playlistid = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, playlistid);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
