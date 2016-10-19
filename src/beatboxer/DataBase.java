package beatboxer;

import java.sql.*;
public class DataBase extends CreateConnection{
	public void CreateDataBase(){
		try{
			Statement check = con.createStatement();
			check.execute("create database BeatBoxer");
			check.execute("use BeatBoxer");
				check.execute("create table Track(" +
						"TrackId int primary key NOT NULL," +
						"TrackName varchar(50) NOT NULL," +
						"DateAdded date NOT NULL," +
						"Location varchar(500) NOT NULL," +
						"Genre varchar(30)," +
						"NoOfTimesPlayed int," +
						"MD5Hash varchar(128))");
				check.execute("create table Artist(" +
						"ArtistId int primary key NOT NULL," +
						"ArtistName varchar(50) NOT NULL)");
				check.execute("create table Album(" + 
						"AlbumId int primary key NOT NULL," +
						"AlbumName varchar(100) NOT NULL)");
				check.execute("create table Directories(FolderLocation varchar(500) NOT NULL)");
				check.execute("create table Favourites(TrackId int primary key NOT NULL)");
				check.execute("create table PlayList(" +
						"PlayListId int primary key NOT NULL," +
						"PlayListName varchar(100) NOT NULL)");
				check.execute("create table PlayListInfo("+
						"PlayListId int NOT NULL," +
						"TrackId int NOT NULL," +
						"PlayOrder int NOT NULL," +
						"primary key(PlayListId, TrackId))");
				check.execute("create table TrackInfo(TrackId int Primary key NOT NULL," +
						"ArtistId int,AlbumId int)");
		}catch(Exception e){
			
		}
	}
}
