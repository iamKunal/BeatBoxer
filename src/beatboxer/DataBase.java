package beatboxer;

import java.sql.*;

public class DataBase extends CreateConnection {

    public void CreateDataBase() {
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute("create table if not exists track("
                    + "trackid INTEGER primary key autoincrement,"
                    + "trackname varchar(50) NOT NULL,"
                    + "artistname varchar(50) NOT NULL,"
                    + "albumname varchar(100) NOT NULL,"
                    + "dateadded timestamp NOT NULL DEFAULT current_timestamp,"
                    + "location varchar(500) NOT NULL UNIQUE,"
                    + "genre varchar(30),"
                    + "favourite boolean default 0)");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create table if not exists directories(folderlocation varchar(500) NOT NULL UNIQUE)");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create table if not exists playlist("
                    + "playlistid INTEGER primary key autoincrement,"
                    + "playlistname varchar(50) NOT NULL UNIQUE)");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create table if not exists playlistinfo("
                    + "playlistid int NOT NULL,"
                    + "trackid int NOT NULL,"
                    + "trackorder int NOT NULL,"
                    + "primary key(playlistid, trackid))");
            statement.clearBatch();
        } catch (Exception e) {
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
