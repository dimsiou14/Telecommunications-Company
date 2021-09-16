<%@page import="clients.ShowBill"%>
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
<title>Bill</title>
</head>
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
&nbsp; <a href="homeClient.jsp"><button>Home</button></a><br><br> 
<h1 style="text-align: center">Bill</h1>
<h3>Details</h3>
<%	
	String user = String.valueOf(session.getAttribute("username"));//we get client's username
	ShowBill bill = new ShowBill();
	//connect with database table bills in order to get client's bill values
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersdb?useTimezone=true&serverTimezone=UTC", "root", "123");
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usersdb.bills WHERE clients_username = ?;"))
	{
		preparedStatement.setString(1, user);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next())
		{
		//output client's bill values
%>
			User Name:&nbsp;<%=result.getString("clients_username")%><br>
			Telephone number:&nbsp;<%=result.getString("telephone_number")%><br>
			Program ID:&nbsp;<%=result.getInt("idprogram")%><br>
			Month (1-12):&nbsp;<%=result.getInt("month")%><br>
			Total:&nbsp;$&nbsp;<%=bill.getTotal(user)%>
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