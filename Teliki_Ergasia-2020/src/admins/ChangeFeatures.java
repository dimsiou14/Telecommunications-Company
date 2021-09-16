package admins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeFeatures 
{
	private int id;
	private int speech;
	private int sms;
	private int data;
	private float cost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSpeech() {
		return speech;
	}
	public void setSpeech(int speech) {
		this.speech = speech;
	}
	public int getSms() {
		return sms;
	}
	public void setSms(int sms) {
		this.sms = sms;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	//we check if the program exists in the database 
	public int getProgID() throws Exception
	{
		int prog=-1;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT idprogram FROM usersdb.programs;"))
		{
			//check if the telephone program id exists in programs table
			ResultSet result = preparedStatement.executeQuery();
			while(result.next())
			{
				if(id==(result.getInt("idprogram"))) 
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
	
	//change speech time value
	public int changeSpeech() throws ClassNotFoundException 
	{
		int result=0;
		String CHANGE_SPEECH_SQL="UPDATE usersdb.programs SET speechTime = ? WHERE idprogram = ?;";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
				PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_SPEECH_SQL))
			{
				preparedStatement.setInt(1,getSpeech());
				preparedStatement.setInt(2, getId());
				
				System.out.println(preparedStatement);
				result = preparedStatement.executeUpdate();
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			return result;
	}
	
	//change SMS value
	public int changeSMS() throws ClassNotFoundException
	{
		int result=0;
		String CHANGE_SMS_SQL="UPDATE usersdb.programs SET sms = ? WHERE idprogram = ?;";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_SMS_SQL))
			{
				preparedStatement.setInt(1,getSms());
				preparedStatement.setInt(2, getId());
					
				System.out.println(preparedStatement);
				result = preparedStatement.executeUpdate();
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			return result;
	}
	
	//change Data value
	public int changeData() throws ClassNotFoundException
	{
		int result=0;
		String CHANGE_DATA_SQL="UPDATE usersdb.programs SET data = ? WHERE idprogram = ?;";
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
			PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_DATA_SQL))
			{
				preparedStatement.setInt(1,getData());
				preparedStatement.setInt(2, getId());
					
				System.out.println(preparedStatement);
				result = preparedStatement.executeUpdate();
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			return result;
	}
	
	//change Cost value
		public int changeCost() throws ClassNotFoundException
		{
			int result=0;
			String CHANGE_COST_SQL="UPDATE usersdb.programs SET cost = ? WHERE idprogram = ?;";
			try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
				PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_COST_SQL))
				{
					preparedStatement.setFloat(1,getCost());
					preparedStatement.setInt(2, getId());
						
					System.out.println(preparedStatement);
					result = preparedStatement.executeUpdate();
				}
				catch(SQLException e) 
				{
					e.printStackTrace();
				}
				return result;
		}
		
		//we check if the integer fields are numbers
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
		
		//we check if the cost field is a float number
		public boolean isFloat(String s) 
		{
			try 
			{
				Float.parseFloat(s);
				return true;
			}
			catch (Exception e) 
			{
				return false;
			}
		}
}
