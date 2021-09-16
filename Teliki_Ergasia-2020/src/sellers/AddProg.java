package sellers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddProg 
{
	private String username;
	private String telephone;
	private int progId;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getProgId() {
		return progId;
	}
	public void setProgId(int progId) {
		this.progId = progId;
	}
	
	public String getUsrnm() throws Exception //we check if username exists in the database 
	{
		String usrnm="null";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM usersdb.clients;"))
		{
			//check if username exists in clients table
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) 
			{
				if(username.equals(result.getString("username"))) 
				{
					usrnm=result.getString("username");
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return usrnm;
	}
	
	public int getProg() throws Exception//we check if the program exists in the database 
	{
		int prog=-1;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT idprogram FROM usersdb.programs;"))
		{
			//check if the telephone program id exists in programs table
			ResultSet result = preparedStatement.executeQuery();
			while(result.next())
			{
				if(progId==(result.getInt("idprogram"))) 
				{
					prog=result.getInt("idprogram");
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return prog;
	}
	
	public String getClient() throws Exception //we check if client has already a telephone program 
	{
		String rslt="null";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT clients_username FROM usersdb.telephone_numbers;"))
		{
			//check in telephone_numbers table if clients username already has a telephone program
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) 
			{
				if(username.equals(result.getString("clients_username"))) 
				{
					rslt=result.getString("clients_username");
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return rslt;
	}
	
	public int addProg() throws ClassNotFoundException //add telephone program to client in database
	{
		int result=0;
		String ADD_PROG_SQL="INSERT INTO telephone_numbers" +
				"(clients_username, telephone_number, idprogram) VALUES" +
				"(?, ?, ?);";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
				
			//create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_PROG_SQL))
		{
			preparedStatement.setString(1, getUsername());
			preparedStatement.setString(2, getTelephone());
			preparedStatement.setInt(3, getProgId());
			
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
	
	public boolean isNumber(String in)//we check if the program id field is integer 
	{
		try 
		{
			Integer.parseInt(in);
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
}
