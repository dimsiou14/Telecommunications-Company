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
User: <%=session.getAttribute("username")%> &nbsp; <a href="logout.jsp"><button>Logout</button></a>
<br><br><br>
<div id="container"><a href="showBill.jsp"><button id="button"><b>BILL DISPLAY</b></button></a></div><br><br>
<div id="container"><a href="callsHistory.jsp"><button id="button"><b>CALL HISTORY</b></button></a></div><br><br>
<div id="container"><a href="payBill.jsp"><button id="button"><b>BILL PAYMENT</b></button></a></div>
</body>
</html>