<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
	#container{text-align:center;}
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
<h1 style="text-align: center">Something went wrong, check the telephone program's form again !</h1>
<br>
<h3>Requirements:</h3>
<ul>
	<li>All fields are required</li>
	<li>The program's ID must not already exist</li>
	<li>The fields Program ID, Speech Time (minutes), SMS, Data (MB) must be numbers</li>
	<li>The field Cost must be of this type: X.X or X (X:number)</li>
</ul>
<br><br>
<div id="container"><a href="addProg.jsp"><button>Try again !</button></a></div>
</body>
</html>