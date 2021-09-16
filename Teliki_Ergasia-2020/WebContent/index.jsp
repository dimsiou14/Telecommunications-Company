<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1 style="text-align: center">Login</h1>
	<form action="<%= request.getContextPath()%>/LoginServlet" method="post">
		<table>
			<tr>
				<td><b> User Name: </b></td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td><b> Password: </b></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login"> <input onclick="window.location.href='Register_Form/register.jsp'" type="button" value="Register"></td>
			</tr>
		</table>
	</form>
</body>
</html>