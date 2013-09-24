<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel=stylesheet type="text/css" href="/isai/style2.css">

<!-- <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script> -->
<script type="text/javascript"
	src="/isai/songs/radio/js/quicktime/jquery-1.2.3.pack.js"></script>
<script type="text/javascript"
	src="/isai/songs/radio/js/quicktime/jquery.embedquicktime.js"></script>


<script type="text/javascript"
	src="/isai/songs/radio/js/jquery.autocomplete.js"></script>
<script src="/isai/songs/radio/js/jquery-ui-1.10.3.custom.js"></script>



<center><h2><font color="#972800">Enter Movie Name:</font></h2>
	<form name="searchForm" action="adminaction" method="get" style="font-face: 'Comic Sans MS'; font-size: larger;">
		<input type="hidden" name="action" value="adminedit" />
	
		 <input type="textbox" id="auto1" name="searchartist" class="ui-widget" /> 
		 <input type="submit" name="searchbtn" value="Search by Movie" />
		 </form>
		 
	<h3><font color="#0000FF"> Image Updation:</font></h3>
		
		
		<form method="post" action="adminaction" >
			
<h4>Movie :<input type="text" name="movie"
						value=""  />&nbsp
Image :<input type="text" name="image"  value="" />
					<input type="submit" name="action" value="UpdateImage" /></h4>
</form>
</center>	


<script>
	jQuery($(function() {
		$("#auto1").autocomplete("/isai/auto1");
	}));
	</script>
	
<script>
	jQuery($(function() {
		$(".auto2").autocomplete("/isai/auto2");
	}));
	</script>

<table>
	<tr>
		<h2><th>Catalog_Id:</font></th></h2>
		<h2><th>Artist_Id:</th></h2>
		<h2><th>Movie_Name:</th></h2>
		<h2><th>Song_Name:</th></h2>
		<h2><th>Composer_Name:</th></h2>
		
		<th>Lyricist_Name:</th>
		<th>Artist_Name:</th>
		<th>Image_File_Name:</th>
	</tr>
	
	<c:forEach items="${requestScope.song}" var="item" >
		<form method="post" action="adminaction" >
		

			<tr>
				<td><font color="blue"><c:out value="${item.id}"></c:out></font></td>
				<input type="hidden" name="id" value="<c:out value="${item.id}"></c:out>" />
				<td><font color="red"><c:out value="${item.artistId}"></c:out></font></td>
				<input type="hidden" name="aid" value="<c:out value="${item.artistId}"></c:out>" />
				<td><input type="text" name="movie"
						value="<c:out value="${item.movieName}" ></c:out>" /></td>
				<td><input type="text" name="song"
					value="<c:out value="${item.songName}"></c:out>" /></td>
				<td><input type="text" name="composer"
					value="<c:out value="${item.composerName}"></c:out>" /></td>
				<td><input type="text" name="lyrics"
					value="<c:out value="${item.lyricistName}"></c:out>" /></td>			
				 <td><input type="text" name="artist"  
					value="<c:out value="${item.artistName}"></c:out>" /></td>
					<td><input type="text" name="image" value="<c:out value="${item.image_file_name}" ></c:out>" /></td> 
					<td><input type="submit" name="action" value="update" /></td> 		 
				<td><input type="textbox" class="auto2" name="txt"  /></td>
 		<td><input type="submit" name="action" value="addartist"/></td>
 		<td><input type="submit" name="action" value="Delete"/></td>
		

</tr>

</form>
</c:forEach></table>

</head>

</html>
