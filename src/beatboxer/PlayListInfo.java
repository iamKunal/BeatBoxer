package beatboxer;
import java.sql.*;


public class PlayListInfo extends CreateConnection {
	private int TrackOrder;
	public void AddTrack(int playlistid,int trackid){
		try{
			String sql = "select count(trackorder) from playlistinfo where playlistid = ?";
			PreparedStatement count = con.prepareStatement(sql);
			count.setInt(1, playlistid);
			ResultSet res = count.executeQuery();
			res.next();
			TrackOrder = res.getInt("count(trackorder)") + 1;		
			sql = "insert into playlistinfo(playlistid,trackid,trackorder) values(?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,playlistid);
            statement.setInt(2,trackid);
            statement.setInt(3,TrackOrder);
            statement.executeUpdate();
	    }
		catch(Exception E){
		}
	}
	public void DeleteTrack(int playlistid,int trackid){
		try{
			String sql = "select trackorder from playlistinfo where playlistid=? and trackid=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,playlistid);
	        statement.setInt(2,trackid);
	        ResultSet res = statement.executeQuery();
	        res.next();
	        TrackOrder = res.getInt("trackorder");
	        sql = "delete from playlistinfo where playlistid = ? and trackid = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1,playlistid);
			statement.setInt(2,trackid);
	        statement.executeUpdate();
	        sql = "update playlistinfo set trackorder=trackorder-1 where playlistid = ? and trackorder > ?";
	        statement = con.prepareStatement(sql);
	        statement.setInt(1,playlistid);
	        statement.setInt(2,TrackOrder);
	        statement.executeUpdate();
		}
		catch(Exception E){
		}
	}
}