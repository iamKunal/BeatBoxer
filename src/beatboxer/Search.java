package beatboxer;
import java.sql.*;
public class Search extends CreateConnection{
	
	public ResultSet SearchAlbum(String string){
		try {
			String sql = "select * from Album WHERE AlbumName LIKE ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1,'%'+string+'%');
			ResultSet res=statement.executeQuery();
			return res;
			}
		 catch(Exception e) {
			 return null;
		}	
	}
	
	public ResultSet SearchTrack(String string){
		try {
			String sql = "select * from Track NATURAL JOIN TrackInfo" + 
					"NATURAL JOIN artist NATURAL JOIN album WHERE TrackName LIKE ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1,"%"+string+"%");
			ResultSet res=statement.executeQuery();
			return res;
			}
		 catch(Exception e) {
		}
		return null;	
	}
	
	public ResultSet SearchArtist(String string){
		try {
			String sql = "select * from Artist WHERE ArtistName LIKE ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1,'%'+string+'%');
			ResultSet res=statement.executeQuery();
			return res;
			}
		 catch(Exception e) {
			 return null;
		}	
	}
}
