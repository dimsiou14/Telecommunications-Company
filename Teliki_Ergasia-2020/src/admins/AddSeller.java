package admins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSeller 
{
	private String username;
	private String password;
	private String name;
	private String surname;
	public String type="Seller";
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUsrnm() throws Exception //we check if username already exists in the database 
	{
		String usrnm="";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT username FROM usersdb.clients;");
			PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT username FROM usersdb.sellers;");
			PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT username FROM usersdb.administrators;"))
		{
			//check if username exists in clients table
			ResultSet result1 = preparedStatement1.executeQuery();
			while(result1.next())
			{
				if(username.equals(result1.getString("username"))) 
				{
					usrnm=result1.getString("username");
				}
			}
			
			//check if username exists in sellers table
			ResultSet result2 = preparedStatement2.executeQuery();
			while(result2.next())
			{
				if(username.equals(result2.getString("username"))) 
				{
					usrnm=result2.getString("username");
				}
			}
			
			//check if username exists in administrators table
			ResultSet result3 = preparedStatement3.executeQuery();
			while(result3.next())
			{
				if(username.equals(result3.getString("username"))) 
				{
					usrnm=result3.getString("username");
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return usrnm;
	}
	
	public int registerSeller() throws ClassNotFoundException //seller registration in database
	{
		int result=0;
		String INSERT_SELLER_SQL="INSERT INTO sellers" +
				"(username, password, name, surname, type) VALUES" +
				"(?, ?, ?, ?, ?);";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
				
			//create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SELLER_SQL))
		{
			preparedStatement.setString(1, getUsername());
			preparedStatement.setString(2, getPassword());
			preparedStatement.setString(3, getName());
			preparedStatement.setString(4, getSurname());
			preparedStatement.setString(5, type);
			
			System.out.println(preparedStatement);
			//execute the query or update query
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}

}
