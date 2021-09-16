<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Telephone Programs</title>
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
&nbsp; <a href="homeSeller.jsp"><button>Home</button></a><br><br><br>  
<table id="table" align="center">
	<tr>
		<td id="td"><b>Program ID</b></td>
		<td id="td"><b>Program</b></td>
		<td id="td"><b>Speech Time (minutes)</b></td>
		<td id="td"><b>SMS</b></td>
		<td id="td"><b>Data (MB)</b></td>
		<td id="td"><b>Cost</b></td>
	</tr>
	<%
	
	try
	{
	//connect with database 
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
	Statement statement=connection.createStatement();
	String sql="SELECT * FROM usersdb.programs;";
	ResultSet resultSet = statement.executeQuery(sql);
	while(resultSet.next()){
	//output the values of table programs 
	%>
	<tr>
		<td id="td"><%=resultSet.getInt("idprogram")%></td>
		<td id="td"><%=resultSet.getString("description")%></td>
		<td id="td"><%=resultSet.getInt("speechTime")%></td>
		<td id="td"><%=resultSet.getInt("sms")%></td>
		<td id="td"><%=resultSet.getInt("data")%></td>
		<td id="td"><%=resultSet.getFloat("cost")%></td>
	</tr>
	<%
	}
	} catch(Exception e){
		e.printStackTrace();
	}
	%>
</table><br>
<h4 style="text-align: center">All the above programs have a duration of 30 days.</h4>
</body>
</html>