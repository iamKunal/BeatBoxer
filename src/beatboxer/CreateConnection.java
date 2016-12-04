package beatboxer;

import java.sql.*;

public class CreateConnection {

    public Connection con;

    CreateConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:beatboxer.db");
        } catch (Exception e) {

        }
    }
}
