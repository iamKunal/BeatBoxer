package beatboxer;

import java.sql.*;
public class Delete {
	public void deleteTrack(int TrackId){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BeatBoxer","root","123");
			String sql = "delete from track where trackid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,TrackId);
			statement.executeUpdate();
			String sql1 = "delete from trackinfo where trackid = ?";
			PreparedStatement statement1 = con.prepareStatement(sql1);
			statement1.setInt(1,TrackId);
			statement1.executeUpdate();
		}catch(Exception e){
			
		}
	}
	public void deleteAlbum(int AlbumId){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BeatBoxer","root","123");
			String sql = "delete from album where albumid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,AlbumId);
			statement.executeUpdate();
			sql = "select trackid from trackinfo where albumid = ?";
			PreparedStatement gettracks = con.prepareStatement(sql);
			gettracks.setInt(1, AlbumId);
			ResultSet res = gettracks.executeQuery();
			while(res.next()){
				deleteTrack(res.getInt("trackid"));
			}
		}catch(Exception e){
			
		}
	}
}






