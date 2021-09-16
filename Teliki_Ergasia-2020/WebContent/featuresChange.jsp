<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change telephone program features</title>
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
<a href="homeAdministrator.jsp"><button>Home</button></a><br><br><br>
<form action="<%= request.getContextPath()%>/ChangeFeaturesServlet" method="post">
		<table>
			<tr>
				<td><b> Program ID: </b></td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td><b> Speech Time (minutes): </b></td>
				<td><input type="text" name="speech"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td><b> SMS: </b></td>
				<td><input type="text" name="sms"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td><b> Data (MB): </b></td>
				<td><input type="text" name="data"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td><b> Cost: </b></td>
				<td><input type="text" name="cost"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Change Features"></td>
			</tr>
		</table>
	</form>
</body>
</html>