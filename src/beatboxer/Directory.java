package beatboxer;
import java.sql.*;
public class Directory extends CreateConnection{
    public void add(String Location){
        String sql = "insert into directories(folderlocation) values(?)";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,Location);
            statement.executeUpdate();
        }catch(SQLException e){
            
        }
    }
    public void delete(String Location){
        String sql = "delete from directories where folderlocation = ?";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,Location);
            statement.executeUpdate();
        }catch(SQLException e){
            
        }
    }
}
