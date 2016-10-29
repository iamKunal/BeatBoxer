package beatboxer;

import java.sql.*;
public class Update extends CreateConnection{
	void updateTrack(int TrackId, String newTrackName){
		try {
			String sql = "update track set trackname = ? where trackid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, newTrackName);
			statement.setInt(2,TrackId);
			statement.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	void updateArtist(int TrackId, String newArtistName){
		int ArtistId = 0;
		try {
			PreparedStatement getartistid = con.prepareStatement("select artistid from trackinfo where trackid = ?");
			getartistid.setInt(1, TrackId);
			ResultSet artist = getartistid.executeQuery();
			artist.next();
			ArtistId = artist.getInt("artistid");
			Statement count = con.createStatement();
			ResultSet res = count.executeQuery("select artistname from artist");
			while(res.next()){
				if(res.getString("artistname").equals(newArtistName)){
					ArtistId = res.getInt("artistid");
					throw new Exception();
				}
			}
			res = count.executeQuery("select count(*) from artist");
			res.next();
			ArtistId = res.getInt("count(*)") + 1;
			String sql = "insert into artist(artistid,artistname) values(?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,ArtistId);
	        statement.setString(2,newArtistName);
	        statement.executeUpdate();
	        sql = "update trackinfo set artistid = ? where trackid = ?";
			PreparedStatement statement1 = con.prepareStatement(sql);
			statement1.setInt(1, ArtistId);
			statement1.setInt(2,TrackId);
			statement1.executeUpdate();
		} catch (Exception e) {
			try {
				String sql = "update trackinfo set artistid = ? where trackid = ?";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1, ArtistId);
				statement.setInt(2,TrackId);
				statement.executeUpdate();
			} catch (Exception e1) {
			}
		}
	}
	void updateAlbum(int TrackId, String newAlbumName){
		int AlbumId = 0;
		try {
			PreparedStatement getalbumid = con.prepareStatement("select albumid from trackinfo where trackid = ?");
			getalbumid.setInt(1, TrackId);
			ResultSet album = getalbumid.executeQuery();
			album.next();
			AlbumId = album.getInt("albumid");
			Statement count = con.createStatement();
			ResultSet res = count.executeQuery("select albumname from album");
			while(res.next()){
				if(res.getString("albumname").equals(newAlbumName)){
					AlbumId = res.getInt("albumid");
					throw new Exception();
				}
			}
			res = count.executeQuery("select count(*) from album");
			res.next();
			AlbumId = res.getInt("count(*)") + 1;
			String sql = "insert into album(albumid,albumname) values(?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,AlbumId);
	        statement.setString(2,newAlbumName);
	        statement.executeUpdate();
	        sql = "update trackinfo set albumtid = ? where trackid = ?";
			PreparedStatement statement1 = con.prepareStatement(sql);
			statement1.setInt(1, AlbumId);
			statement1.setInt(2,TrackId);
			statement1.executeUpdate();
		} catch (Exception e) {
			try {
				String sql = "update trackinfo set albumid = ? where trackid = ?";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1, AlbumId);
				statement.setInt(2,TrackId);
				statement.executeUpdate();
			} catch (Exception e1) {
			}
		}
	}
}
