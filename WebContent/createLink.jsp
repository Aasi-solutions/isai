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
<table width="776" height="78" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff">
	<tr valign="top">
<td width="18"><img src="/isai/songs/design/topleft1.jpg" width="18" height="78" border="0" alt=""></td>
<td width="86">

</td>
<td width="551"><a href="http://www.imayam.org/Isai/songs/radio/index.jsp"><img src="/isai/songs/design/smartlink.jpg" width="551" height="78" border="0" alt=""></a></td>
<td width="100%">&nbsp;</td>
<td width="18"><img src="/isai/songs/design/topright1.jpg" width="18" height="78" border="0" alt=""></td>
	</tr>
</table>

<table width="776" height="6" cellpadding="0" cellspacing="0" border="0">
	<tr valign="top">
<td width="18"><img src="/isai/songs/design/topleft2.jpg" width="18" height="6" border="0" alt=""></td>
<td width="100%" background="/isai/songs/design/topbg2.jpg"><img src="/isai/songs/design/topbg2.jpg" width="9" height="6" border="0" alt=""></td>
<td width="18"><img src="/isai/songs/design/topright2.jpg" width="18" height="6" border="0" alt=""></td>
	</tr>
</table>


<table width="776" height="30" cellpadding="0" cellspacing="0" border="0">
	<tr valign="middle">
<td width="18"><img src="/isai/songs/design/topleft3.jpg" width="18" height="30" border="0" alt=""></td>

<td width="740" background="/isai/songs/design/topbg3.jpg">
    <jsp:include page="menu.jsp"/>
</td>
<td width="18"><img src="/isai/songs/design/topright3.jpg" width="18" height="30" border="0" alt=""></td>
	</tr>
</table>

<table width="776" height="10" cellpadding="0" cellspacing="0" border="0">
	<tr valign="top">
<td width="18"><img src="/isai/songs/design/topleft4.jpg" width="18" height="10" border="0" alt=""></td>
<td width="100%" background="/isai/songs/design/topbg4.jpg"><img src="/isai/songs/design/topbg4.jpg" width="10" height="10" border="0" alt=""></td>
<td width="18"><img src="/isai/songs/design/topright4.jpg" width="18" height="10" border="0" alt=""></td>
	</tr>
</table>

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
<!-- page text ---------------------------------------------->

<div style="height:500px;background-color:#D3E4F5;">
	<table border="1" width="100%" bgcolor="#D3E4F5">
		<tr>
			<td colspan="2" width="100%" height="500">
			
			
			
	<h1 style="text-align:center">Create Your SmartLink</h1>

	<br /><br />
	<div style="color:red;"><c:out value="${sessionScope.message}" /></div>
	<br />
	<form action='controller' method='POST'>
	<table>
	    <tr>
	        <td><b>SmartLink (url)</b><br/><input type='text' name='urllink' value='Enter link (URL) ...' maxlength='250' size='50'  onfocus='if (urllink.value=="Enter link (URL) ...") {urllink.value="http://"}'>(required)</td>
	    </tr>
	    <tr>
	        <td><b>SmartLink Name</b><br/> <input type='text' name='urlname' value='Give a name to the link ...' maxlength='50' size='50' onfocus='if (urlname.value=="Give a name to the link ...") {urlname.value=""}'>(required)</td>
	    </tr>
	    <tr>
	        <td><b>Description </b><br/><input type='text' name='urldesc' value='Description here ...' maxlength='200' size='50' onfocus='if (urldesc.value=="Description here ...") {urldesc.value=""}'>(optional)</td>
	    </tr>
	    <tr>
	        <td><b>Private SmartLink </b><input type='checkbox' name='private' value='1'></td>
        </tr>
        <tr >
            <td style="text-align:right;">
                <input type='submit' name='command' value='Cancel'>
                <input type='submit' name='command' value='Save'>
                <input type='hidden' name='cat_id' value='1'>
                <input type='hidden' name='cat_name' value='All Categories'>
                <input type='hidden' name='action' value='create'>
             </td>
        </tr>
    </table>
    </form>
    
    </td>
    </tr>
    </table>
</div>


</td>
<!-- page text ---------------------------------------------->
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

