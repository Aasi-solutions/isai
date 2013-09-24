<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<html>
<head>
	<title>Imayam SmartLinks</title>
	<META NAME="keywords" content="tagging, online bookmarks, bookmark, ">
	<META NAME="description" content="A free online bookmark service">
	<link rel=stylesheet type="text/css" href="style.css">
</head>

<body background="/isai/songs//isai/songs/design/pagebg.jpg" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">

<center>
<table width="776" height="78" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff">
	<tr valign="top">
<td width="18"><img src="/isai/songs//isai/songs/design/topleft1.jpg" width="18" height="78" border="0" alt=""></td>
<td width="86">

</td>
<td width="551"><a href="http://www.imayam.org/isai/songs/radio/index.jsp"><img src="/isai/songs//isai/songs/design/smartlink.jpg" width="551" height="78" border="0" alt=""></a></td>
<td width="100%">&nbsp;</td>
<td width="18"><img src="/isai/songs//isai/songs/design/topright1.jpg" width="18" height="78" border="0" alt=""></td>
	</tr>
</table>

<table width="776" height="6" cellpadding="0" cellspacing="0" border="0">
	<tr valign="top">
<td width="18"><img src="/isai/songs//isai/songs/design/topleft2.jpg" width="18" height="6" border="0" alt=""></td>
<td width="100%" background="/isai/songs//isai/songs/design/topbg2.jpg"><img src="/isai/songs//isai/songs/design/topbg2.jpg" width="9" height="6" border="0" alt=""></td>
<td width="18"><img src="/isai/songs//isai/songs/design/topright2.jpg" width="18" height="6" border="0" alt=""></td>
	</tr>
</table>


<table width="776" height="30" cellpadding="0" cellspacing="0" border="0">
	<tr valign="middle">
<td width="18"><img src="/isai/songs//isai/songs/design/topleft3.jpg" width="18" height="30" border="0" alt=""></td>

<td width="740" background="/isai/songs//isai/songs/design/topbg3.jpg">
    <jsp:include page="menu.jsp"/>
</td>
<td width="18"><img src="/isai/songs//isai/songs/design/topright3.jpg" width="18" height="30" border="0" alt=""></td>
	</tr>
</table>

<table width="776" height="10" cellpadding="0" cellspacing="0" border="0">
	<tr valign="top">
<td width="18"><img src="/isai/songs//isai/songs/design/topleft4.jpg" width="18" height="10" border="0" alt=""></td>
<td width="100%" background="/isai/songs//isai/songs/design/topbg4.jpg"><img src="/isai/songs//isai/songs/design/topbg4.jpg" width="10" height="10" border="0" alt=""></td>
<td width="18"><img src="/isai/songs//isai/songs/design/topright4.jpg" width="18" height="10" border="0" alt=""></td>
	</tr>
</table>

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
<BR><BR><BR>
<div style="text-align:center">
<a href="http://www.anrdoezrs.net/33106kjspjr68G99AAG687AEBBE7" target="_blank" onmouseover="window.status='http://www.iconnecthere.com';return true;" onmouseout="window.status=' ';return true;">
<img src="http://www.tqlkg.com/51106fz2rxvGIQJJKKQGIHKOLLOH" alt="Advertises Broadband Phone for $15.99" border="0"></a>
</div>
			</td>
		</tr>
	</table>

<BR><BR>

</td>

<td width="3"><img src="/isai/songs/design/spacer.jpg" width="3" height="1" border="0" alt=""></td>
<td width="100%">
<!-- page text -->

	<table border="1" width="100%" bgcolor="#D3E4F5">
		<tr>
			<td colspan="2" width="100%" height="300px">
                <form action="controller" method="post">
                 <br />
                 <div style="text-align:center;"><input name="searchString" size="60" maxlength="100" tabindex="1"><br /> <input name="action" type="submit" value="Search SmartLinks" default tabindex="2"></div>
	            </form>
                <br />
				<% int i = 0; %>
				<c:forEach var="mylink" items="${sessionScope.searchResult}" >
					<% if (i % 2 == 0) { %>
						<div style="background-color:#D5D5D5;">
					<% } else { %>
						<div style="background-color:#E5E5E5;">
					<% } %>

    					<div style="font-size:13;font-weight: bold;">
    					    <a href="<c:out value='${mylink.linkURL}' />"><c:out value='${mylink.linkName}' /></a>
    					</div>
    					<c:out value='${mylink.description}' />
    					<div style="text-align:right;"><a href="controller">View Details</a> | <a href="controller?action=save&amp;id=<c:out value='${mylink.urlId}' />">Save to My SmartLinks</a></div>

                    </div>
					<% i = i + 1; %>
				</c:forEach>
                <%
                if (i == 0) {
				    out.print("<div style='font-size:13;font-weight: bold;'>No SmartLinks to display.</div>");
				}
				%>

			</td>
		</tr>
		<tr>
			<td colspan="2">
				<b>Latest SmartLinks</b><br />
				<c:forEach var="topLink" items="${sessionScope.topTenList}" >
					<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt="">
					<a href="<c:out value='${topLink.linkURL}' />"><c:out value='${topLink.linkName}' /></a>
					linked by <c:out value='${topLink.user}' /> &nbsp;<br />
				</c:forEach>
			</td>
		</tr>
</table>

</td>
<!-- page text -->
<td width="3"><img src="/isai/songs/design/spacer.jpg" width="3" height="1" border="0" alt=""></td>



<td width="156"><table width="156" height="24" cellpadding="0" cellspacing="0" border="0" background="/isai/songs/design/menutop.jpg"><tr valign="middle"><td align="center"><div style="padding-top:6px;padding-left:18px "><span class="style1">Sponsored Links</span></div></td></tr></table><table width="156" cellpadding="5" cellspacing="0" border="0" bgcolor="#D3E4F5">
		<tr valign="top">
			<td>
			<br />
			<iframe src="http://rcm.amazon.com/e/cm?t=imayam-20&o=1&p=20&l=qs1&f=ifr" width="120" height="90" frameborder="0" scrolling="no"></iframe>
			<br />
			<br />
			<iframe src="http://rcm.amazon.com/e/cm?t=imayam-20&o=1&p=10&l=bn1&mode=dvd&browse=404276&=1&fc1=&lt1=&lc1=&bg1=&f=ifr" marginwidth="0" marginheight="0" width="120" height="450" border="0" frameborder="0" style="border:none;" scrolling="no"></iframe>
			</td>
		</tr>
	</table>
	<BR>
	<BR>
	<BR>
</td>




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

