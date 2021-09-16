<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<style>
	#button
	{
		padding:10px;
		font-size:17px;
	}
	#container
	{
		text-align:center;
	}
</style>
<body>
<% 
	//we clear the cache
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.

	if(session.getAttribute("username")==null)
	{
		response.sendRedirect("index.jsp");
	}
%>
User: <%=session.getAttribute("username")%> &nbsp; <a href="logout.jsp"><button>Logout</button></a><br><br><br>
<div id="container"><a href="telephonePrograms.jsp"><button id="button"><b>TELEPHONE PROGRAMS</b></button></a></div><br><br>
<div id="container"><a href="addClient.jsp"><button id="button"><b>ADD NEW CLIENT</b></button></a></div><br><br>
<div id="container"><a href="progToClient.jsp"><button id="button"><b>ADD PROGRAM TO CLIENT</b></button></a></div><br><br>
<div id="container"><a href="progChange.jsp"><button id="button"><b>CLIENT PROGRAM CHANGE</b></button></a></div><br><br>
<div id="container"><a href="sellerCreateBill.jsp"><button id="button"><b>CLIENT'S BILL CREATION</b></button></a></div><br><br>
</body>
</html>