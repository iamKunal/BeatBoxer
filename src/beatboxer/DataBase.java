package beatboxer;

import java.sql.*;

public class DataBase {

    public void CreateDataBase() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "123");
            Statement check = con.createStatement();
            check.execute("create database beatboxer");
            check.execute("use beatboxer");
            check.execute("create table track("
                    + "trackid int primary key NOT NULL,"
                    + "artistid int NOT NULL,"
                    + "albumid int NOT NULL,"
                    + "trackname varchar(50) NOT NULL,"
                    + "dateadded timestamp NOT NULL DEFAULT current_timestamp,"
                    + "location varchar(500) NOT NULL UNIQUE,"
                    + "genre varchar(30),"
                    + "favourite boolean default false)");
            check.execute("create table artist("
                    + "artistid int primary key NOT NULL,"
                    + "artistname varchar(50) NOT NULL UNIQUE)");
            check.execute("create table album("
                    + "albumid int primary key NOT NULL,"
                    + "albumname varchar(100) NOT NULL UNIQUE)");
            check.execute("create table directories(folderlocation varchar(500) NOT NULL UNIQUE)");
            check.execute("create table playlist("
                    + "playlistid int primary key NOT NULL,"
                    + "playlistname varchar(100) NOT NULL UNIQUE)");
            check.execute("create table playlistinfo("
                    + "playlistid int NOT NULL,"
                    + "trackid int NOT NULL,"
                    + "trackorder int NOT NULL,"
                    + "primary key(playlistid, trackid))");
            check.execute("create table id(newid int, playlistid int)");
            check.execute("insert into id values(1,1)");
            check.execute("create trigger incrementidinsert after insert on track for each row update id set newid = newid + 1");
            check.execute("create trigger incrementplaylistid after insert on playlist for each row update id set playlistid = playlistid + 1");
            check.execute("create trigger deleterefresh after delete on track "
                    + "for each row begin "
                    + "delete from album where albumid not in (select distinct albumid from track); "
                    + "delete from artist where artistid not in (select distinct artistid from track); "
                    + "end");
            check.execute("create trigger updaterefresh after update on track "
                    + "for each row begin "
                    + "delete from album where albumid not in (select distinct albumid from track); "
                    + "delete from artist where artistid not in (select distinct artistid from track); "
                    + "update id set newid = newid + 1;"
                    + "end");

            check.execute("CREATE PROCEDURE addtrack(in trackname varchar(50),in artistname varchar(50),in albumname varchar(100),in location varchar(500),in genre varchar(30)) "
                    + "begin "
                    + "declare artistid int; "
                    + "declare albumid int; "
                    + "declare trackid int; "
                    + "set trackid = (select newid from id); "
                    + "set artistid = searchartist(artistname); "
                    + "set albumid = searchalbum(albumname); "
                    + "if (artistid = -1) then "
                    + "	set artistid = (select newid from id); "
                    + "	insert into artist values (artistid, artistname); "
                    + "end if; "
                    + "if (albumid = -1) then "
                    + "	set albumid = (select newid from id); "
                    + "	insert into album values (albumid, albumname); "
                    + "end if; "
                    + "insert into track(trackid,artistid,albumid,trackname,location,genre) values(trackid,artistid,albumid,trackname,location,genre); "
                    + "end");
            check.execute("CREATE PROCEDURE adddirectory(in location varchar(500)) "
                    + "BEGIN "
                    + "insert into directories values (location); "
                    + "END");
            check.execute("CREATE PROCEDURE deletedirectory(in location varchar(500)) "
                    + "BEGIN "
                    + "delete from playlistinfo where playlistinfo.trackid in (select track.trackid from track where track.location like concat(location, '%'));"
                    + "delete from track where track.location like concat(location, '%');  "
                    + "delete from directories where folderlocation = location; "
                    + "END");
            check.execute("CREATE PROCEDURE movetrack(in pid int, in tid int, in direction int) "
                    + "BEGIN "
                    + "declare swaptid int; "
                    + "declare torder int; "
                    + "set torder = (select trackorder from playlistinfo where playlistid = pid and trackid = tid); "
                    + "if(direction = 0) then "
                    + "    set swaptid = (select trackid from playlistinfo where playlistid = pid and trackorder = torder - 1); "
                    + "    update playlistinfo set trackorder = torder where playlistid = pid and trackid = swaptid; "
                    + "    update playlistinfo set trackorder = torder - 1 where playlistid = pid and trackid = tid; "
                    + "else "
                    + "	set swaptid = (select trackid from playlistinfo where playlistid = pid and trackorder = torder + 1); "
                    + "    update playlistinfo set trackorder = torder where playlistid = pid and trackid = swaptid; "
                    + "    update playlistinfo set trackorder = torder + 1 where playlistid = pid and trackid = tid; "
                    + "end if; "
                    + "END");
            check.execute("CREATE PROCEDURE addtracktoplaylist(in pid int,in tid int) "
                    + "BEGIN "
                    + "declare torder int; "
                    + "set torder = (select count(*) from playlistinfo where playlistid=pid); "
                    + "set torder = torder + 1; "
                    + "insert into playlistinfo values(pid,tid,torder); "
                    + "END");
            check.execute("CREATE PROCEDURE createplaylist(in PlaylistName varchar(100)) "
                    + "BEGIN "
                    + "declare pid int; "
                    + "set pid = (select playlistid from id); "
                    + "insert into playlist values(pid,PlaylistName); "
                    + "END");
            check.execute("CREATE PROCEDURE deletefromplaylist(in pid int,in tid int) "
                    + "BEGIN "
                    + "declare torder int; "
                    + "set torder=(select trackorder from playlistinfo where playlistid=pid and trackid=tid); "
                    + "delete from playlistinfo where playlistid=pid and trackid=tid; "
                    + "update playlistinfo set trackorder=trackorder-1 where playlistid=pid and trackorder>torder; "
                    + "END");
            check.execute("CREATE PROCEDURE deleteplaylist(in pid int) "
                    + "BEGIN "
                    + "delete from playlist where playlistid=pid; "
                    + "delete from playlistinfo where playlistid=pid; "
                    + "END");
            check.execute("CREATE PROCEDURE deletetrack(in TrackId int) "
                    + "BEGIN "
                    + "delete from track where track.trackid=TrackId; "
                    + "delete from playlistinfo where playlistinfo.trackid=TrackId; "
                    + "END");
            check.execute("CREATE PROCEDURE favourite(in TrackId int) "
                    + "BEGIN "
                    + "update track set favourite=True where track.trackid=TrackId; "
                    + "END");
            check.execute("CREATE PROCEDURE unfavourite(in TrackId int) "
                    + "BEGIN "
                    + "update track set favourite=false where track.trackid=TrackId; "
                    + "END");
            check.execute("CREATE PROCEDURE updatetrack(in TrackId int,in newtrack varchar(50),in newartist varchar(50),in newalbum varchar(100),in newgenre varchar(30)) "
                    + "BEGIN "
                    + "declare ArtistId int; "
                    + "declare AlbumId int; "
                    + "set ArtistId=searchartist(newartist); "
                    + "set AlbumId=searchalbum(newalbum); "
                    + "if (ArtistId = -1) then "
                    + "	set ArtistId=(select newid from id); "
                    + "	insert into artist values (ArtistId,newartist); "
                    + "end if; "
                    + "if (AlbumId = -1) then "
                    + "	set AlbumId = (select newid from id); "
                    + "	insert into album values (AlbumId,newalbum); "
                    + "end if; "
                    + "update track set trackname = newtrack,genre = newgenre,track.artistid = track.ArtistId,albumid = AlbumId where track.trackid = TrackId; "
                    + "END");
            check.execute("CREATE FUNCTION searchartist(name varchar(50)) RETURNS int(11) "
                    + "BEGIN "
                    + "declare id int; "
                    + "set id = (select artistid from artist where artistname = name); "
                    + "if (id > 0) then return id; "
                    + "end if; "
                    + "RETURN -1; "
                    + "END");
            check.execute("CREATE FUNCTION searchalbum(name varchar(100)) RETURNS int(11) "
                    + "BEGIN "
                    + "declare id int; "
                    + "set id = (select albumid from album where albumname = name); "
                    + "if (id > 0) then return id; "
                    + "end if; "
                    + "RETURN -1; "
                    + "END");
            check.execute("CREATE PROCEDURE updateplaylist(in pid int, in newname varchar(50)) "
                    + "BEGIN "
                    + "update playlist set playlistname = newname where playlistid = pid; "
                    + "END");
        } catch (Exception e) {

        }
    }
}
