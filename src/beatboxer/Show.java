package beatboxer;
import java.sql.*;
import javafx.collections.*;
public class Show extends CreateConnection{

	public ObservableList<BBSong> ShowAllTracks(){
	try{
		Statement count = con.createStatement();
        ResultSet res=count.executeQuery("Select * from Track NATURAL JOIN TrackInfo NATURAL JOIN artist natural join album");
        return BBGenerator.song(res);
	}catch(Exception e){
		
	}
	return null;
	}
	public ObservableList<BBItem> ShowAllArtists(){
		try{
			Statement count = con.createStatement();
	        ResultSet res=count.executeQuery("Select * from Artist");
	        return BBGenerator.item(res);
		}catch(Exception e){
			
		}
		return null;
	}
	public ObservableList<BBItem> ShowAllTracksByArtists(int artistId){
		try{
			String sql = "select * from Track NATURAL JOIN TrackInfo WHERE ArtistId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,artistId);
			ResultSet res=statement.executeQuery();
			return BBGenerator.item(res);
		}catch(Exception e){
			
		}
		return null;
		
	}
	public ObservableList<BBItem> ShowAllAlbums(){
		try{
			Statement count = con.createStatement();
	        ResultSet res=count.executeQuery("Select * from Album");
	        return BBGenerator.item(res);
		}catch(Exception e){
			
		}
		return null;
	}
	public ObservableList<BBItem> ShowAllTracksinAlbum(int albumId){
		try{
			String sql = "select * from Track NATURAL JOIN TrackInfo WHERE AlbumId=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,albumId);
			ResultSet res=statement.executeQuery();
			return BBGenerator.item(res);
		}catch(Exception e){
			
		}
		return null;
	
	}
	
	
}
