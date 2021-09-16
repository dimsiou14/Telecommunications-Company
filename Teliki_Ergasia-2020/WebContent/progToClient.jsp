<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Program to Client</title>
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
User: <%=session.getAttribute("username")%> &nbsp; 
<a href="logout.jsp"><button>Logout</button></a> &nbsp;
<a href="homeSeller.jsp"><button>Home</button></a><br><br><br>
	<form action="<%= request.getContextPath()%>/AddProgServlet" method="post">
		<table>
			<tr>
				<td><b>Client's User Name: </b></td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td><b>Client's Telephone: </b></td>
				<td><input type="text" name="telephone"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td><b> Program ID: </b></td>
				<td><input type="text" name="program"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Add Program"></td>
			</tr>
		</table>
	</form>
</body>
</html>