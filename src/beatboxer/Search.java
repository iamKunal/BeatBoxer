package beatboxer;
import java.sql.*;
public class Search extends CreateConnection{
	
	public ResultSet SearchAlbum(String string){
		try {
			String sql = "select * from album WHERE albumname LIKE ?";
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
			String sql = "select * from track NATURAL JOIN trackinfo" + 
					"NATURAL JOIN artist NATURAL JOIN album WHERE trackname LIKE ?";
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
			String sql = "select * from artist WHERE artistname LIKE ?";
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
