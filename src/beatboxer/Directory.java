package beatboxer;

import java.sql.*;
import java.util.ArrayList;

public class Directory extends CreateConnection {

    public void add(String Location) {
        String sql = "insert into directories(folderlocation) values(?)";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, Location.replace("\\", "/"));
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void delete(String Location) {
        String sql = "delete from directories where folderlocation = ?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, Location);
            statement.executeUpdate();
            sql = "select trackid from track where location like ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, Location.replace("\\", "/") + "/%");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt("trackid"));
                Delete track = new Delete();
                track.deleteTrack(res.getInt("trackid"));
            }
        } catch (SQLException e) {

        }
    }

    public ArrayList<String> Show() {
        ArrayList<String> list = new ArrayList();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery("select * from directories");
            while (res.next()) {
                list.add(res.getString("folderlocation"));
            }
            return list;
        } catch (SQLException e) {

        }
        return null;
    }
}
