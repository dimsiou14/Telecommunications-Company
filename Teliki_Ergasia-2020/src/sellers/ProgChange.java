package sellers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgChange 
{
	private String username;
	private int  progid;
	public int id;
	public String client; 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getProgid() {
		return progid;
	}
	public void setProgid(int progid) {
		this.progid = progid;
	}

	//we check if client's username exists in database 
	public String getUsrnm() throws Exception
	{
		String usrnm="";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT clients_username FROM usersdb.telephone_numbers WHERE clients_username = ?;"))
		{
			preparedStatement.setString(1,username);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) 
			{
				if(username.equals(result.getString("clients_username"))) 
				{
					usrnm = result.getString("clients_username");
					client = result.getString("clients_username");
				}
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return usrnm;
	}
	
	//we check if program id exists in database
	public int getProg() throws Exception
	{
		int prog = 0;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT idprogram FROM usersdb.programs;"))
		{
			//check if the telephone program id exists in programs table
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) 
			{
				if(progid==(result.getInt("idprogram"))) 
				{
					prog = result.getInt("idprogram");
					id = result.getInt("idprogram");
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return prog;
	}
	
	//we check if the program id field is a number
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
	
	//change client program 
	public int progChange() throws ClassNotFoundException 
	{
		int result = 0;
		String DISABLE_KEYS_SQL="SET FOREIGN_KEY_CHECKS=0";
		String CHANGE_PROG_SQL="UPDATE usersdb.telephone_numbers SET idprogram = ? WHERE clients_username = ?;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement1 = connection.prepareStatement(DISABLE_KEYS_SQL);
			PreparedStatement preparedStatement2 = connection.prepareStatement(CHANGE_PROG_SQL))
		{
			preparedStatement1.executeQuery();
			preparedStatement2.setInt(1,getProgid());
			preparedStatement2.setString(2,getUsername());
			
			System.out.println(preparedStatement2);
			result = preparedStatement2.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	//we update the bill's id program when it changes
	public int billUpdate() throws ClassNotFoundException  
	{
		int result=0;
		String BILL_UPDATE_SQL="UPDATE usersdb.bills SET idprogram = ? WHERE clients_username = ?;";
		String ENABLE_KEYS_SQL="SET FOREIGN_KEY_CHECKS=1";
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement1 = connection.prepareStatement(BILL_UPDATE_SQL);
			PreparedStatement preparedStatement2 = connection.prepareStatement(ENABLE_KEYS_SQL);)
		{
			preparedStatement1.setInt(1, id);
			preparedStatement1.setString(2, client);
				
			System.out.println(preparedStatement1);
			result = preparedStatement1.executeUpdate();
			preparedStatement2.executeQuery();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}	
}
