package clients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayBill 
{
	//we delete the client's account from the database after the customer pays his bill
	public void payBill(String s) throws Exception 
	{
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usersdb.bills WHERE clients_username = ?;"))
		{
			preparedStatement.setString(1, s);
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
