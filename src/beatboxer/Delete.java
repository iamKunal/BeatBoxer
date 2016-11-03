package beatboxer;

import java.sql.*;
public class Delete extends CreateConnection{
	public void deleteTrack(int TrackId){
		try{
			String sql = "delete from track where trackid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,TrackId);
			statement.executeUpdate();
			sql = "delete from trackinfo where trackid = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1,TrackId);
			statement.executeUpdate();
		}catch(SQLException e){
			
		}
	}
	public void deleteAlbum(int AlbumId){
		try{
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
		}catch(SQLException e){
			
		}
	}
}






