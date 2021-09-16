package clients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowBill 
{
	//we are calculating the total of client's bill 
	public float getTotal(String s) throws Exception
	{
		float total = 0;
		int prog;
		//we will get the program id from the current client in order to get the cost
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT idprogram FROM usersdb.telephone_numbers WHERE clients_username = ?;"))
		{
			preparedStatement.setString(1, s);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) 
			{
				prog = result.getInt("idprogram");//we get program id
					
				//now that we have the program id, we will try to get the cost
				try(Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
					PreparedStatement preparedStatement1 = connection1.prepareStatement("SELECT cost FROM usersdb.programs WHERE idprogram = ?;"))
				{
					preparedStatement1.setInt(1, prog);
					ResultSet result1 = preparedStatement1.executeQuery();
						
					while(result1.next()) 
					{ 	
						total = result1.getFloat("cost");//we get the program's cost
					}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return total;
	}
}
