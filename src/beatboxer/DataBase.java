package beatboxer;

import java.sql.*;

public class DataBase extends CreateConnection {

    public void CreateDataBase() {
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute("create table if not exists track("
                    + "trackid int primary key NOT NULL,"
                    + "artistid int NOT NULL,"
                    + "albumid int NOT NULL,"
                    + "trackname varchar(50) NOT NULL,"
                    + "dateadded timestamp NOT NULL DEFAULT current_timestamp,"
                    + "location varchar(500) NOT NULL UNIQUE,"
                    + "genre varchar(30),"
                    + "favourite boolean default 0)");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create table if not exists artist("
                    + "artistid int primary key NOT NULL,"
                    + "artistname varchar(50) NOT NULL UNIQUE)");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create table if not exists album("
                    + "albumid int primary key NOT NULL,"
                    + "albumname varchar(100) NOT NULL UNIQUE)");
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
                    + "playlistid int primary key NOT NULL,"
                    + "playlistname varchar(100) NOT NULL UNIQUE)");
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
        }
        try {
            statement = con.createStatement();
            statement.execute("create table id(newid int, playlistid int)");
            statement.clearBatch();
            statement.execute("insert into id values(1,1)");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create trigger if not exists incrementidinsert after insert on track begin update id set newid = newid + 1; end;");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create trigger if not exists incrementplaylistid after insert on playlist begin update id set playlistid = playlistid + 1; end;");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create trigger if not exists deleterefresh after delete on track "
                    + "begin "
                    + "delete from album where albumid not in (select distinct albumid from track); "
                    + "delete from artist where artistid not in (select distinct artistid from track); "
                    + "end;");
            statement.clearBatch();
        } catch (Exception e) {
        }
        try {
            statement = con.createStatement();
            statement.execute("create trigger if not exists updaterefresh after update on track "
                    + "begin "
                    + "delete from album where albumid not in (select distinct albumid from track); "
                    + "delete from artist where artistid not in (select distinct artistid from track); "
                    + "update id set newid = newid + 1;"
                    + "end;");
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
