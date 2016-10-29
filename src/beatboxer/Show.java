package beatboxer;
import java.sql.*;
public class Show extends CreateConnection{

	public ResultSet ShowAllTracks(){
	try{
		Statement count = con.createStatement();
        ResultSet res=count.executeQuery("Select * from Track NATURAL JOIN TrackInfo NATURAL JOIN artist natural join album");
        return res;
	}catch(Exception e){
		
	}
	return null;
	}
	public ResultSet ShowAllArtists(){
		try{
			Statement count = con.createStatement();
	        ResultSet res=count.executeQuery("Select * from Artist");
	        return res;
		}catch(Exception e){
			
		}
		return null;
	}
	public ResultSet ShowAllTracksByArtists(int artistId){
		try{
			String sql = "select * from Track NATURAL JOIN TrackInfo WHERE ArtistId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,artistId);
			ResultSet res=statement.executeQuery();
			return res;
		}catch(Exception e){
			
		}
		return null;
		
	}
	public ResultSet ShowAllAlbums(){
		try{
			Statement count = con.createStatement();
	        ResultSet res=count.executeQuery("Select * from Album");
	        return res;
		}catch(Exception e){
			
		}
		return null;
	}
	public ResultSet ShowAllTracksinAlbum(int albumId){
		try{
			String sql = "select * from Track NATURAL JOIN TrackInfo WHERE AlbumId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,albumId);
			ResultSet res=statement.executeQuery();
			return res;
		}catch(Exception e){
			
		}
		return null;
	
	}
	
	
}
