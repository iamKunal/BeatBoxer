package beatboxer;

import java.sql.*;
public class AddTrack extends CreateConnection{
	private String TrackName;
	private String ArtistName;
	private String AlbumName;
	private String Location;
	private String Genre;
	private int TrackId;
	private int ArtistId;
	private int AlbumId;
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
	private void addartist(){
		try{
			if(ArtistName.equals(null)){
				ArtistId = 0;
				throw new Exception();
			}
			Statement st = con.createStatement();
			ResultSet count = st.executeQuery("select count(*) from artist");
			count.next();
			boolean flag = true;
			if(count.getInt("count(*)") == 0){
				flag = false;
			}
			
			String sql = "select artistid from artist where artistname = ?";
			PreparedStatement check = con.prepareStatement(sql);
			check.setString(1, ArtistName);
			ResultSet res = check.executeQuery();
			if(res == null || !flag){
				ArtistId = count.getInt("count(*)") + 1;
				sql = "insert into artist(artistid,artistname) values(?,?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1,ArtistId);
		        statement.setString(2,ArtistName);
		        statement.executeUpdate();
			}else{
				res.next();
				ArtistId = res.getInt("artistid");
			}
		}catch(Exception e){
			
		}
	}
	private void addalbum(){
		try{
			if(AlbumName.equals(null)){
				AlbumId = 0;
				throw new Exception();
			}
			Statement st = con.createStatement();
			ResultSet count = st.executeQuery("select count(*) from album");
			count.next();
			boolean flag = true;
			if(count.getInt("count(*)") == 0){
				flag = false;
			}
			
			String sql = "select albumid from album where albumname = ?";
			PreparedStatement check = con.prepareStatement(sql);
			check.setString(1, AlbumName);
			ResultSet res = check.executeQuery();
			if(res == null || !flag){
				AlbumId = count.getInt("count(*)") + 1;
				sql = "insert into album(albumid,albumname) values(?,?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1,AlbumId);
		        statement.setString(2,AlbumName);
		        statement.executeUpdate();
			}else{
				res.next();
				AlbumId = res.getInt("albumid");
			}
		}catch(Exception e){
			
		}
	}
	private void addTrack(){
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
	private void addtrackinfo(){
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
