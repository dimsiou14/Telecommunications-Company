package user.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import users.User;

public class UserDb 
{
	public String get(User user) throws Exception //if user types a username that already exists in database
	{
		String rslt="";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT username FROM usersdb.clients;");
			PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT username FROM usersdb.sellers;");
			PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT username FROM usersdb.administrators;"))
		{
			//check username from clients table
			ResultSet result1 = preparedStatement1.executeQuery();
			while(result1.next()) 
			{
				if (user.getUsername().equals(result1.getString("username"))) 
				{
					rslt = result1.getString("username");
				}
			}
			//check username from sellers table
			ResultSet result2 = preparedStatement2.executeQuery();
			while(result2.next()) 
			{
				if (user.getUsername().equals(result2.getString("username"))) 
				{
					rslt = result2.getString("username");
				}
			}
			//check username from administrators table
			ResultSet result3 = preparedStatement3.executeQuery();
			while(result3.next()) 
			{
				if (user.getUsername().equals(result3.getString("username"))) 
				{
					rslt = result3.getString("username");
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return rslt;
	}
	
	public int registerUser(User user) throws ClassNotFoundException //user registration in database
	{
		int result = 0;
		//if type of users is client user is registered in clients table 
		if (user.getType().equals("Client") || user.getType().equals("client") || user.getType().equals("CLIENT")) 
		{
			String INSERT_CLIENT_SQL="INSERT INTO clients" +
					"(username, password, name, surname, type) VALUES" +
					"(?, ?, ?, ?, ?);";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			
				//create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL))
			{
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getName());
				preparedStatement.setString(4, user.getSurname());
				preparedStatement.setString(5, user.getType());
					
				System.out.println(preparedStatement);
				//execute the query or update query
				result = preparedStatement.executeUpdate();			
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		//if type of users is seller is registered in sellers table  
		else if (user.getType().equals("Seller") || user.getType().equals("seller") || user.getType().equals("SELLER")) 
		{
			String INSERT_SELLER_SQL="INSERT INTO sellers" +
					"(username, password, name, surname, type) VALUES" +
					"(?, ?, ?, ?, ?);";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SELLER_SQL))
			{
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getName());
				preparedStatement.setString(4, user.getSurname());
				preparedStatement.setString(5, user.getType());
					
				System.out.println(preparedStatement);
				result = preparedStatement.executeUpdate();			
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return result;
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
