package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//we get and set username and password 
public class Login 
{
	public String username;
	public String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() throws Exception //we get the type of the user that logins
	{
		String type = "";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT username, password, type FROM usersdb.clients WHERE username = ? AND password = ?;");
			PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT username, password, type FROM usersdb.sellers WHERE username = ? AND password = ?;");
			PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT username, password, type FROM usersdb.administrators WHERE username = ? AND password = ?;"))
		{
			//we set the username and the password that user typed in order to 
			//check if exist in clients table  
			preparedStatement1.setString(1, username);
			preparedStatement1.setString(2, password);
			ResultSet result1 = preparedStatement1.executeQuery();
			
			//we set the username and the password that user typed in order to 
			//check if exist in sellers table 
			preparedStatement2.setString(1, username);
			preparedStatement2.setString(2, password);
			ResultSet result2 = preparedStatement2.executeQuery();
			
			//we set the username and the password that user typed in order to 
			//check if exist in administrators table 
			preparedStatement3.setString(1, username);
			preparedStatement3.setString(2, password);
			ResultSet result3 = preparedStatement3.executeQuery();
			
			//check if the username and password matches with the results in clients table
			while(result1.next()) 
			{
				if (username.equals(result1.getString("username")) && password.equals(result1.getString("password")))  
				{
					username = result1.getString("username");
					password = result1.getString("password");
					type = result1.getString("type");
				}	
			}
			//check if the username and password matches with the results in sellers table
			while(result2.next()) 
			{
				if (username.equals(result2.getString("username")) && password.equals(result2.getString("password")))  
				{
					username = result2.getString("username");
					password = result2.getString("password");
					type = result2.getString("type");
				}	
			}
			//check if the username and password matches with the results in administrators table
			while(result3.next()) 
			{
				if (username.equals(result3.getString("username")) && password.equals(result3.getString("password")))  
				{
					username = result3.getString("username");
					password = result3.getString("password");
					type = result3.getString("type");
				}	
			}		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return type;
	}
	
	public String getHashMD5(String unhashed, String salt) 
	{
        // Hash the password.
        final String toHash = salt + unhashed + salt;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            return "00000000000000000000000000000000";
        }
        messageDigest.update(toHash.getBytes(), 0, toHash.length());
        String hashed = new BigInteger(1, messageDigest.digest()).toString(16);
        if (hashed.length() < 32) {
            hashed = "0" + hashed;
        }
        return hashed.toUpperCase();
    }
}
