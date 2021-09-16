<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
	body {text-align:center}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>
<% 
	session.removeAttribute("username"); 
	session.removeAttribute("password"); 
	session.invalidate(); 
%> 
<h1>Successful logout !</h1><br>
<a href="index.jsp"><button>Login</button></a>
</body>
</html>