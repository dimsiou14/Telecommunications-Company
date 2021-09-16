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
<h1 style="text-align: center">Something went wrong, check your form again !</h1>
<br>
<h3>Requirements:</h3>
<ul>
	<li>The fields 'Client's User Name', 'Client's Telephone' and 'Program ID' are required</li>
	<li>The client's user name must belong to an existing client</li>
	<li>The telephone number must be of this type: 69XXXXXXXX</li>
	<li>The program ID must belong to an existing program</li>
</ul>
<br><br>
<div id="container"><a href="progToClient.jsp"><button>Try again !</button></a></div>
</body>
</html>