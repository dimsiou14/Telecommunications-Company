<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
	body {text-align:center}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
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
<h1 style="text-align: center">Telephone program's features changed successfully !</h1>
<br><br>
<a href="homeAdministrator.jsp"><button>Home</button></a>
</body>
</html>