package beatboxer;

import java.sql.*;
import java.util.ArrayList;

public class Directory extends CreateConnection {

    public void add(String Location) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("insert into directories(folderlocation) values(?)");
            statement.setString(1, Location.replace("\\", "/"));
            statement.execute();
        } catch (SQLException e) {
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public void delete(String Location) {
        Location = Location.replace("\\", "/");
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement("delete from playlistinfo where playlistinfo.trackid in (select track.trackid from track where track.location like ?)");
            statement.setString(1, Location + '%');
            statement.execute();
            statement.clearBatch();
            statement = con.prepareStatement("delete from track where track.location like ?");
            statement.setString(1, Location + '%');
            statement.execute();
            statement.clearBatch();
            statement = con.prepareStatement("delete from directories where folderlocation = ?");
            statement.setString(1, Location);
            statement.execute();
            statement.clearBatch();
        } catch (SQLException e) {
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public ArrayList<String> Show() {
        ArrayList<String> list = new ArrayList();
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet res = statement.executeQuery("select * from directories");
            while (res.next()) {
                list.add(res.getString("folderlocation"));
            }
            res.close();
            return list;
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
}
