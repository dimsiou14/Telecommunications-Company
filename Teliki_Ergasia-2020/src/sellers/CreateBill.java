package sellers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateBill 
{
	private String username;
	public String client;
	private String telnum;
	private int progid;
	private int month;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	//we check if username exists in database
	public String getUsrnm() throws Exception
	{
		String usrnm="null";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT clients_username FROM usersdb.telephone_numbers;"))
		{
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) 
			{
				if(username.equals(result.getString("clients_username"))) 
				{
					usrnm = result.getString("clients_username");
				}
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return usrnm;
	}
	
	//we get the phone number from the current client in order to create his bill
	public String getTelNum() throws Exception 
	{
		String tel="";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT telephone_number FROM usersdb.telephone_numbers WHERE clients_username = ?;"))
		{
			preparedStatement.setString(1,username);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) 
			{
				tel = result.getString("telephone_number");
				telnum = result.getString("telephone_number");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tel;
	} 
	
	//we get the program id from the current client in order to create his bill
	public int getProgID() throws Exception
	{
		int id=0;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT idprogram FROM usersdb.telephone_numbers WHERE clients_username = ?;"))
		{
			preparedStatement.setString(1,username);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) 
			{
				id = result.getInt("idprogram");
				progid = result.getInt("idprogram");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return id;
	}
	
	//current client's bill creation
	public int createBill() throws ClassNotFoundException 
	{
		int result=0;
		String CREATE_BILL_SQL="INSERT INTO bills" +
				"(clients_username, telephone_number, idprogram, month) VALUES" +
				"(?, ?, ?, ?);";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BILL_SQL))
		{
			preparedStatement.setString(1, getUsername());
			preparedStatement.setString(2, telnum);
			preparedStatement.setInt(3, progid);
			preparedStatement.setInt(4, getMonth());
			
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	//we are cheking if the current client's bill already exists in order to update the new one
	public String getClient() throws Exception 
	{
		String c="null";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT clients_username FROM usersdb.bills;"))
		{
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) 
			{
				if(username.equals(result.getString("clients_username"))) 
				{
					c = result.getString("clients_username");
					client = result.getString("clients_username");
				}
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return c;
	}
	
	//update the new bill for the current client
	public int billUpdate() throws ClassNotFoundException  
	{
		int result=0;
		String BILL_UPDATE_SQL="UPDATE usersdb.bills SET month = ? WHERE clients_username = ?;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement(BILL_UPDATE_SQL))
		{
			preparedStatement.setInt(1, month);
			preparedStatement.setString(2, client);
			
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	//we are checking if client's bill for the current month already exists 
	public int getMnth() throws Exception 
	{
		int m=0;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT month FROM usersdb.bills WHERE clients_username = ?;"))
		{
			preparedStatement.setString(1,username);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) 
			{
				if (month==result.getInt("month")) 
				{
					m = result.getInt("month");
				}
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return m;
	}
	
	//we check if the month field is an integer
	public boolean isNumber(String in) 
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
