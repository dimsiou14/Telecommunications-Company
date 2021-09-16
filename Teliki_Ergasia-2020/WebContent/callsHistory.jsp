<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Call History</title>
</head>
<style>
	#table,#td
	{
		border:1px solid #000;
		border-collapse:collapse;	
	}
	#td
	{
		text-align:center;
		padding:4px 8px;
	}
</style>
<body>
<% 
	//clear the cache
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.

	if(session.getAttribute("username")==null)
	{
		response.sendRedirect("index.jsp");
	}
%>
User: <%=session.getAttribute("username")%> &nbsp; <a href="logout.jsp"><button>Logout</button></a>
&nbsp; <a href="homeClient.jsp"><button>Home</button></a><br><br><br>  
<table id="table" align="center">
	<tr>
		<td id="td"><b>Client's User Name</b></td>
		<td id="td"><b>Telephone Number</b></td>
		<td id="td"><b>Day</b></td>
		<td id="td"><b>Duration (minutes)</b></td>
		<td id="td"><b>Program ID</b></td>
	</tr>
<%
	String client = String.valueOf(session.getAttribute("username"));//we get client's username
	//connect with database table calls in order to get client's calls values
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT clients_username, telephone_number, day, duration, idprogram " 
		+ "FROM usersdb.calls WHERE clients_username = ?;"))
	{
		preparedStatement.setString(1, client);
		ResultSet result = preparedStatement.executeQuery();
			
		while(result.next())
		{
		//output the calls of the current client
%>
	<tr>
		<td id="td"><%=result.getString("clients_username")%></td>
		<td id="td"><%=result.getString("telephone_number")%></td>
		<td id="td"><%=result.getString("day")%></td>
		<td id="td"><%=result.getInt("duration")%></td>
		<td id="td"><%=result.getInt("idprogram")%></td>
	</tr>
<% 		
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>	
</body>
</html>