<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
	body{text-align:center}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
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
<h1 style="text-align: center">This User Name already exists, try a new one !</h1>
<br><br>
<a href="addClient.jsp"><button>Try again !</button></a>
</body>
</html>