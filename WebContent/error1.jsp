<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
 <style type="text/css">
<%@include file="style1.css" %>
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org   /TR/html4/loose.dtd">`
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>


<form>
<table>
<center>
<%
final String message = (String) request.getAttribute ("message");
%>
<h4>
 <c:out value="${message}" /> </h4>
</center>



</table>
</form>
 
 

</body>
</html>
