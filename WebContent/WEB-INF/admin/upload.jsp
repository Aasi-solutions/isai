<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
</head>
<body style="background-color:grey">
<center><h1><font color="blue">File Upload</font></h1></center>
</br></br>    
      <form method="post" name="f2" action="adminaction?action=uploadaction" enctype="multipart/form-data">
 <h2>  Enter Movie Name     : <input type="textbox"  name="fname" id="fid" ></h2></br>
     <h2>Select File to upload:
 <input type="file" name="uploadFile" style="font-face: 'Comic Sans MS'; font-size: medium; color: blue; background-color: #FFFFC0; border: 3pt ridge lightgrey" /> 
 <input type="submit" value="Upload" style="font-face: 'Comic Sans MS'; font-size: medium; color: teal; background-color: #FFFFC0; border: 3pt ridge lightgrey" /></br></br>      
 Or<font color="red">     GoTo Edit:</font>    <a href="/isai/adminaction?action=adminedit">Edit</a> </form>  
</body>
</html>