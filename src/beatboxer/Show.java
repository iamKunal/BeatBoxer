package beatboxer;
import java.sql.*;
import javafx.collections.*;
public class Show extends CreateConnection{

	public ObservableList<BBSong> ShowAllTracks(){
	try{
		Statement count = con.createStatement();
        ResultSet res=count.executeQuery("Select * from track NATURAL JOIN trackinfo NATURAL JOIN artist natural join album");
        return BBGenerator.song(res);
	}catch(Exception e){
		
	}
	return null;
	}
	public ObservableList<BBItem> ShowAllArtists(){
		try{
			Statement count = con.createStatement();
	        ResultSet res=count.executeQuery("Select * from artist");
	        return BBGenerator.item(res);
		}catch(Exception e){
			
		}
		return null;
	}
	public ObservableList<BBItem> ShowAllTracksByArtists(int artistId){
		try{
			String sql = "select * from track NATURAL JOIN trackinfo WHERE artistid=?";
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
	        ResultSet res=count.executeQuery("Select * from album");
	        return BBGenerator.item(res);
		}catch(Exception e){
			
		}
		return null;
	}
	public ObservableList<BBItem> ShowAllTracksinAlbum(int albumId){
		try{
			String sql = "select * from track NATURAL JOIN trackinfo WHERE albumid=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,albumId);
			ResultSet res=statement.executeQuery();
			return BBGenerator.item(res);
		}catch(Exception e){
			
		}
		return null;
	
	}
	
	
}
