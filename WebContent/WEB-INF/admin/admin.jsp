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
<title>Welcome to Admin Account!!!</title>
</head>
<body>

<script>
 String adminaction = (String) request.getparameter("adminaction");
 function validate(){
	 var username=document.form.user.value;
	 var password=document.form.pass.value;
	 if(username==""){
		 
	  alert("Enter Username!");
	   return false;
	 }
	 if(password==""){
	  alert("Enter Password!");
	   return false;
	 }
	 return true;
	 } 

</script>

<center><h1>Welcome to Admin Account!!!</h1><br>
<%
final String message = (String) request.getAttribute ("message");
%>
<h4>
 <c:out value="${message}" /> </h4>

<!--  <form name="f1"  action="adminaction" onSubmit="return validate();" method="post">
<input type = "hidden" name="action" value="loginaction"/><br>
UserName<input type="text" name="uname"/><br>
Password<input type="password" name=pass><br><br><br>
<input type="submit" value="submit" /><br></center>
</form>   -->
<form name="form" method="post" action="adminaction" onsubmit="javascript:return validate();">
<table>
<input type = "hidden" name="action" value="loginaction"/><br>
<tr><td>Username:</td><td><input type="text" name="user"></td></tr>
<tr><td>Password:</td><td><input type="password" name="pass"></td></tr>
<tr><td></td><td><input type="submit" value="Login"></td></tr>

<!--  <tr><td></td><td><a href="register.jsp">Register Here</a></td></tr> -->


</table>
</form>
 
 

</body>
</html>