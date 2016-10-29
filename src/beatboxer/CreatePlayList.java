package beatboxer;
import java.sql.*;

import javax.naming.NotContextException;
public class CreatePlayList extends CreateConnection{
	private String PlayListName;
	private int PlayListId;
	public CreatePlayList(String playlistname){
		PlayListName = playlistname;
		try{
			if(PlayListName.equals(null)){
				throw new NotContextException();
			}
			Statement st = con.createStatement();
			ResultSet count = st.executeQuery("select count(*) from playlist");
			count.next();
			boolean flag = true;
			if(count.getInt("count(*)") == 0){
				flag = false;
			}
			
			String sql = "select count(playlistid) from playlist where playlistname = ?";
			PreparedStatement check = con.prepareStatement(sql);
			check.setString(1, PlayListName);
			ResultSet res = check.executeQuery();
			res.next();
			if(res.getInt("count(playlistid)")==0 || !flag){
				PlayListId = count.getInt("count(*)") + 1;
				sql = "insert into playlist(playlistid,playlistname) values(?,?)";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1,PlayListId);
		        statement.setString(2,PlayListName);
		        statement.executeUpdate();
			}else{
				throw new Exception();
			}
		}catch(NotContextException e){
			System.out.println("Please enter a playlist name");
		}catch(Exception e){
			String s = "Cannot create PlayList";
			System.out.println(s);
		}
	}
}
