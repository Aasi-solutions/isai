<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<html>
<head>
	<title>Imayam SmartLinks</title>
	<META NAME="keywords" content="tagging, online bookmarks, bookmark, ">
	<META NAME="description" content="A free online bookmark service">
	<link rel=stylesheet type="text/css" href="style.css">
</head>

<body background="/isai/songs/design/pagebg.jpg" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">

<center>

<jsp:include page="/header.jsp" />

<table width="776" cellpadding="0" cellspacing="0" border="0" background="/isai/songs/design/mainbg.jpg">
	<tr valign="top">
		<td width="22">&nbsp;</td>
		<td width="732">

<table width="732" cellpadding="0" cellspacing="0" border="0">
<tr valign="top">

<td width="156"><table width="156" height="24" cellpadding="0" cellspacing="0" border="0" background="/isai/songs/design/menutop.jpg"><tr valign="middle"><td align="center"><div style="padding-top:6px;padding-left:18px "><span class="style1">Options</span></div></td></tr></table><table width="156" cellpadding="0" cellspacing="0" border="0" bgcolor="#D3E4F5">
		<tr valign="top">
			<td>

<TABLE BORDER="0" WIDTH="100%" CELLPADDING="0" CELLSPACING="0"><TR><TD bgcolor="#FFFFFF" HEIGHT="1"><TABLE BORDER="0" WIDTH="100%" CELLPADDING="0" CELLSPACING="0"><TR><TD></TD></TR></TABLE></TD></TR></TABLE>

<BR>
<b>
<c:if test="${sessionScope.username == null}">
    <img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="controller?action=loginpage">Login</a><br />
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
<br><br>
<div style="text-align:center">
<script type="text/javascript"><!--
  amazon_ad_tag = "imayam-20";  amazon_ad_width = "120";  amazon_ad_height = "600";  amazon_ad_logo = "hide";  amazon_ad_link_target = "new";//--></script>
<script type="text/javascript" src="http://www.assoc-amazon.com/s/ads.js"></script></div>
			</td>
		</tr>
	</table>

<BR><BR>

</td>

<td width="3"><img src="/isai/songs/design/spacer.jpg" width="3" height="1" border="0" alt=""></td>
<td width="100%">
<!-- page text -->

<table border="0" width="100%" bgcolor="#D3E4F5">
    <tr>
        <th colspan="2">
            <b>Latest SmartLinks</b>
        </th>
    </tr>
    <c:forEach var="topLink" items="${sessionScope.topTenList}" >
        <tr>
            <td>
                <div align="left" style="font-size: 125%">
                    <img src="/isai/songs/design/redarrow.jpg">
                    <a href="<c:out value='${topLink.linkURL}' />"><c:out value='${topLink.linkName}' /></a>
                </div>
            </td>
            <td>
                <div align="right">
                    linked by <a href=""><c:out value='${topLink.user}' /></a>
                </div>
            </td>
        </tr>
        <tr>
            <td width="75%">
		<c:out value="${topLink.description}" />
            </td>
            <td width="25%">
                <div align="right">
			<a href="controller?action=save&amp;id=<c:out value='${topLink.urlId}' />">Save to My Links</a>
                    <a href="">Category List1</a>
                </div>
            </td>
        </tr>
        <tr>
            <td style="background:#999;width:1px;height:1px;padding:0;" colspan="2">
            </td>
        </tr>
    </c:forEach>
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
			<script type="text/javascript" language="javascript" src="http://www.jdoqocy.com/placeholder-902177?target=_top&mouseover=N"></script>
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

