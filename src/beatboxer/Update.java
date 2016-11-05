package beatboxer;

import java.sql.*;
public class Update extends CreateConnection{
	public void updateTrack(int TrackId, String newTrackName){
		try {
			String sql = "update track set trackname = ? where trackid = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, newTrackName);
			statement.setInt(2,TrackId);
			statement.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
	public void updateArtist(int TrackId, String newArtistName){
		int ArtistId = 0;
		try {
			Statement count = con.createStatement();
			ResultSet res = count.executeQuery("select * from artist");
			while(res.next()){
				if(res.getString("artistname").equals(newArtistName)){
                                    ArtistId = res.getInt("artistid");
                                    trackinfo(ArtistId,TrackId,"artistid");
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
                        trackinfo(ArtistId,TrackId,"artistid");
                        
		} catch (Exception e) {
		}
	}
	public void updateAlbum(int TrackId, String newAlbumName){
		int AlbumId = 0;
		try {
			Statement count = con.createStatement();
			ResultSet res = count.executeQuery("select * from album");
			while(res.next()){
				if(res.getString("albumname").equals(newAlbumName)){
					AlbumId = res.getInt("albumid");
                                        trackinfo(AlbumId,TrackId,"albumid");
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
                        trackinfo(AlbumId,TrackId,"albumid");
		} catch (Exception e) {
		}
	}
	
    public void updategenre(int trackid, String newgenre) {
        String sql = "update track set genre = ? where trackid = ?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, newgenre);
            statement.setInt(2, trackid);
            statement.executeUpdate();
        } catch (SQLException e) {

        }
    }	
	
        private void trackinfo(int id1,int id2,String colname){
            if(colname.equals("artistid")){
                try {
                    String sql = "update trackinfo set artistid = ? where trackid = ?";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setInt(1,id1);
                    statement.setInt(2,id2);
                    statement.executeUpdate();
                } catch (SQLException e) {
                }
            }else{
                try {
                    String sql = "update trackinfo set albumid = ? where trackid = ?";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setInt(1,id1);
                    statement.setInt(2,id2);
                    statement.executeUpdate();
                } catch (SQLException e) {
                }
            }   
        }
}
