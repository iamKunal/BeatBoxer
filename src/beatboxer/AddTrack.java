package beatboxer;

import java.sql.*;
public class AddTrack extends CreateConnection{
	String TrackName;
	String ArtistName;
	String AlbumName;
	String Location;
	String Genre;
	int TrackId;
	int ArtistId;
	int AlbumId;
	public AddTrack(String trackName, String artistName, String albumName,String location, String genre){
		TrackName = trackName;
		ArtistName = artistName;
		AlbumName = albumName;
		Location = location;
		Genre = genre;
	}
	public void add(){
		addTrack();
		addartist();
		addalbum();
		addtrackinfo();
	}
	void addartist(){
		try{
			if(ArtistName.equals(null)){
				ArtistId = 0;
				throw new Exception();
			}
			Statement count = con.createStatement();
			ResultSet res = count.executeQuery("select * from artist");
			boolean flag = true;
			while(res.next()){
				if(res.getString("artistname").equals(ArtistName)){
					ArtistId = res.getInt("artistid");
					flag = false;
					break;
				}
			}
			if(flag){
				res = count.executeQuery("select count(*) from artist");
				res.next();
				ArtistId = res.getInt("count(*)") + 1;
				String sql = "insert into artist(artistid,artistname) values(?,?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1,ArtistId);
		        statement.setString(2,ArtistName);
		        statement.executeUpdate();
			}
		}catch(Exception e){
			
		}
	}
	void addalbum(){
		try{
			if(AlbumName.equals(null)){
				AlbumId = 0;
				throw new Exception();
			}
			Statement count = con.createStatement();
			ResultSet res = count.executeQuery("select * from album");
			boolean flag = true;
			while(res.next()){
				if(res.getString("albumname").equals(AlbumName)){
					AlbumId = res.getInt("albumid");
					flag = false;
					break;
				}
			}
			if(flag){
				res = count.executeQuery("select count(*) from album");
				res.next();
				AlbumId = res.getInt("count(*)") + 1;
				String sql = "insert into album(albumid,albumname) values(?,?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1,AlbumId);
		        statement.setString(2,AlbumName);
		        statement.executeUpdate();
			}
		}catch(Exception e){
			
		}
	}
	void addTrack(){
		try{
			Statement count = con.createStatement();
			ResultSet res = count.executeQuery("select count(*) from track");
			res.next();
			TrackId = res.getInt("count(*)") + 1;
			String sql = "insert into track(trackid,trackname,genre,dateadded,location) values(?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,TrackId);
	        statement.setString(2,TrackName);
	        statement.setString(3, Genre);
	        Date curr = new Date(0);
	        statement.setDate(4, curr);
	        statement.setString(5, Location);
	        statement.executeUpdate();
		}catch(Exception e){
			
		}
	}
	void addtrackinfo(){
		try{
			String sql = "insert into trackinfo(trackid,artistid,albumid) values(?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,TrackId);
	        statement.setInt(2,ArtistId);
	        statement.setInt(3,AlbumId);
	        statement.executeUpdate();
			
		}catch(Exception e){
			
		}
	}
}
