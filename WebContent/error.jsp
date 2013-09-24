<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ page isErrorPage="true" %>


<html>
<head>
	<title>Imayam SmartLinks</title>
	<META NAME="keywords" content="tagging, online bookmarks, bookmark, ">
	<META NAME="description" content="A free online bookmark service">
	<link rel=stylesheet type="text/css" href="style.css">
</head>

<body background="/isai/songs//isai/songs/design/pagebg.jpg" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">

<center>

<jsp:include page="/header.jsp" />

<table width="776" cellpadding="0" cellspacing="0" border="0" background="/isai/songs//isai/songs/design/mainbg.jpg">
	<tr valign="top">
		<td width="22">&nbsp;</td>
		<td width="732">

<table width="732" cellpadding="0" cellspacing="0" border="0">
<tr valign="top">

<td width="156"><table width="156" height="24" cellpadding="0" cellspacing="0" border="0" background="/isai/songs//isai/songs/design/menutop.jpg"><tr valign="middle"><td align="center"><div style="padding-top:6px;padding-left:18px "><span class="style1">Options</span></div></td></tr></table><table width="156" cellpadding="0" cellspacing="0" border="0" bgcolor="#D3E4F5">
		<tr valign="top">
			<td>

<TABLE BORDER="0" WIDTH="100%" CELLPADDING="0" CELLSPACING="0"><TR><TD bgcolor="#FFFFFF" HEIGHT="1"><TABLE BORDER="0" WIDTH="100%" CELLPADDING="0" CELLSPACING="0"><TR><TD></TD></TR></TABLE></TD></TR></TABLE>

<BR>
<b>
<c:if test="${sessionScope.username == null}">
    <img src="/isai/songs//isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="controller?action=loginpage">Login</a><br />
</c:if>
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="controller?action=mylinks">My SmartLinks</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="search.jsp">Search SmartLinks</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="createLink.jsp">Create SmartLink</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="controller?action=share">Share SmartLink</a><br />
<c:if test="${sessionScope.username != null}">
    <img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="controller?action=logout">Logout</a><br />
</c:if>
</b>
<br><br>
<div style="text-align:center">
<!-- Begin: AdBrite -->
<script type="text/javascript">
   var AdBrite_Title_Color = '0000FF';
   var AdBrite_Text_Color = '000000';
   var AdBrite_Background_Color = 'D3E4F5';
   var AdBrite_Border_Color = 'D3E4F5';
</script>
<script src="http://ads.adbrite.com/mb/text_group.php?sid=331049&zs=3132305f363030" type="text/javascript"></script>
<div><a target="_top" href="http://www.adbrite.com/mb/commerce/purchase_form.php?opid=331049&afsid=1" style="font-weight:bold;font-family:Arial;font-size:13px;">Your Ad Here</a></div>
<!-- End: AdBrite -->
</div>
</td>
		</tr>
	</table>

<BR><BR>

</td>

<td width="3"><img src="/isai/songs/design/spacer.jpg" width="3" height="1" border="0" alt=""></td>
<td width="100%">
<!-- page text -->
<center>
<br /><br />

<b>Unable to process your request. Please try again.</b>

<br />
<a href="/isai/songs/radio/index.jsp">imayam.org</a> <br />
<a href="http://www.dreamminds.net">dreamminds.net</a> <br />

<c:out value="${message}" />

</center>


</td>
<!-- page text -->
<td width="3"><img src="/isai/songs/design/spacer.jpg" width="3" height="1" border="0" alt=""></td>



</tr>
</table>

		</td>
		<td width="22">&nbsp;</td>
	</tr>
</table>


<jsp:include page="footer.jsp"/>

</center>
</body>
</html>

