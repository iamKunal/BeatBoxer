package beatboxer;

import java.sql.*;
public class DataBase{
	public void CreateDataBase(){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","123");
			Statement check = con.createStatement();
			check.execute("create database BeatBoxer");
			check.execute("use BeatBoxer");
				check.execute("create table track(" +
						"trackid int primary key NOT NULL," +
						"trackname varchar(50) NOT NULL," +
						"dateadded timestamp NOT NULL DEFAULT current_timestamp," +
						"location varchar(500) NOT NULL UNIQUE," +
						"genre varchar(30)," +
						"nooftimesplayed int default 0," +
						"favourite boolean default false)");
				check.execute("create table artist(" +
						"artistid int primary key NOT NULL," +
						"artistname varchar(50) NOT NULL)");
				check.execute("create table album(" + 
						"albumid int primary key NOT NULL," +
						"albumname varchar(100) NOT NULL)");
				check.execute("create table directories(folderlocation varchar(500) NOT NULL)");
				check.execute("create table favourites(trackid int primary key NOT NULL)");
				check.execute("create table playlist(" +
						"playlistid int primary key NOT NULL," +
						"playlistname varchar(100) NOT NULL)");
				check.execute("create table playlistinfo("+
						"playlistid int NOT NULL," +
						"trackid int NOT NULL," +
						"trackorder int NOT NULL," +
						"primary key(playlistid, trackid))");
				check.execute("create table trackinfo(trackid int Primary key NOT NULL," +
						"artistid int,albumid int)");
		}catch(Exception e){
			
		}
	}
}
