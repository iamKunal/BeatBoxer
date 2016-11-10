package beatboxer;

import java.sql.*;
import java.util.ArrayList;

public class Directory extends CreateConnection {

    public void add(String Location) {
        try {
            PreparedStatement statement = con.prepareStatement("call adddirectory(?)");
            statement.setString(1, Location.replace("\\", "/"));
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void delete(String Location) {
        try {
            PreparedStatement statement = con.prepareStatement("call deletedirectory(?)");
            statement.setString(1, Location.replace("\\", "/"));
            statement.executeUpdate();
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
