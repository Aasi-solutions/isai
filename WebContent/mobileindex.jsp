<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<% 
String playString = "";
String display = "";
String userShuffle = (String) request.getParameter("shuffle");
String userAction = (String) request.getParameter("action");
String searchartist = (String) request.getParameter("searchartist");
String artist = (String) request.getParameter("artist");
%>

<html>
<head>
<% if (artist == null) { %>
	<title>Imayam Songs</title>
<%} else { %>
	<title><%= artist %></title>
<% } %>

<META HTTP-EQUIV="expires" CONTENT="Thu, 31 Dec 2020 23:59:59 GMT">

        <meta http-equiv="cache-control" content="private" />
        <META HTTP-EQUIV="ACCEPT-ENCODING" CONTENT="gzip, delate" />
	<META NAME="keywords" content="tamil songs online, listen to tamil songs, songs, tamil, movie, songs no firewall issues, aalwar, pokkiri, aalvar, aazhwar, aazhvar, top 10, top 10 songs, imayam top 10, songs, kuselan">
	<META NAME="keywords" content="Yuvan, Yuvan Shankar Raja, Poovellam Kettupaar, g v prakasah">
	<META NAME="description" content="Listen to Songs Online">
	<link rel=stylesheet type="text/css" href="../../style.css">
<%@ include file="/ajax.jsp" %>


</head>

<body background="/isai/songs/design/pagebg.jpg" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">

<c:if test="${sessionScope.popcheck == null}">

<c:set var="popcheck" value="yesdone" scope="session" />

</c:if>

<center>

<jsp:include page="/header.jsp" />

<table width="776" cellpadding="0" cellspacing="0" border="0" background="/isai/songs/design/mainbg.jpg">
	<tr valign="top">
		<td width="22">&nbsp;</td>
		<td width="732">

<table width="732" cellpadding="0" cellspacing="0" border="0">
<tr valign="top">

<td width="156"><table width="156" height="24" cellpadding="0" cellspacing="0" border="0" background="/isai/songs/design/menutop.jpg"><tr valign="middle"><td align="center"><div style="padding-top:6px;padding-left:18px "><span class="style1">Collections</span></div></td></tr></table><table width="156" cellpadding="0" cellspacing="0" border="0" bgcolor="#D3E4F5">
		<tr valign="top">
			<td>

<TABLE BORDER="0" WIDTH="100%" CELLPADDING="0" CELLSPACING="0"><TR><TD bgcolor="#FFFFFF" HEIGHT="1"><TABLE BORDER="0" WIDTH="100%" CELLPADDING="0" CELLSPACING="0"><TR><TD></TD></TR></TABLE></TD></TR></TABLE>

<form name="songsForm" action="index.jsp" method="post">

<BR>
<b>
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=playlist&artist=New Adds">New Additions</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=playlist&artist=Most Listened(all time)">Most Listened (all time)</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=playlist&artist=Most Listened(current month)">Most Listened (monthly)</a><br />

<!--

<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=M%20S%20Viswanathan">M S Viswanathan</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Ilaiyaraaja">Ilaiyaraaja</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=T%20Rajendar">T Rajendar</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=A%20R%20Rahman">A R Rahman</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Deva">Deva</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Vidyasagar">Vidyasagar</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Yuvan%20Shankar%20Raja">Yuvan Shankar Raja</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Bharathwaj">Bharathwaj</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Harris%20Jayaraj">Harris Jayaraj</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Sabesh%20Murali">Sabesh Murali</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Mani%20Sharma">Mani Sharma</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Srikanth%20Deva">Srikanth Deva</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Devi%20Sri%20Prasad">Devi Sri Prasad</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Joshua%20Sridhar ">Joshua Sridhar</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=Vijay%20Antony">Vijay Antony</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=playlist&artist=G%20V%20Prakash">G V Prakash</a><br />
<img src="../../design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/home/imayam2/public_html/songs/radio/index.jsp?action=alltime">All Time Hits</a><br />

-->

<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=userplaylist&artist=Editor%27s%20Choice">Editor's Choice</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=userplaylist&artist=Music @ Work">Music @ Work</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=userplaylist&artist=Thathuva Padalgal">Thathuva Padalgal</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=userplaylist&artist=Kuthu Paattu">Kuthu Paattu</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=userplaylist&artist=Midnight Masala">Midnight Masala</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=userplaylist&artist=Devotional Songs">Devotinal Songs</a><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/songs/radio/index.jsp?action=svee">S Vee Sekhar Drama</a><br />
</b>
<br><br>

<!-- Include the Google Friend Connect javascript library. -->
<script type="text/javascript" src="http://www.google.com/friendconnect/script/friendconnect.js"></script>
<!-- Define the div tag where the gadget will be inserted. -->
<div id="div-5855777784932306835" style="width:150px;border:1px solid #cccccc;"></div>
<!-- Render the gadget into a div. -->
<script type="text/javascript">
var skin = {};
skin['BORDER_COLOR'] = '#cccccc';
skin['ENDCAP_BG_COLOR'] = '#e0ecff';
skin['ENDCAP_TEXT_COLOR'] = '#333333';
skin['ENDCAP_LINK_COLOR'] = '#0000cc';
skin['ALTERNATE_BG_COLOR'] = '#ffffff';
skin['CONTENT_BG_COLOR'] = '#ffffff';
skin['CONTENT_LINK_COLOR'] = '#0000cc';
skin['CONTENT_TEXT_COLOR'] = '#333333';
skin['CONTENT_SECONDARY_LINK_COLOR'] = '#7777cc';
skin['CONTENT_SECONDARY_TEXT_COLOR'] = '#666666';
skin['CONTENT_HEADLINE_COLOR'] = '#333333';
skin['NUMBER_ROWS'] = '2';
google.friendconnect.container.setParentUrl('/' /* location of rpc_relay.html and canvas.html */);
google.friendconnect.container.renderMembersGadget(
 { id: 'div-5855777784932306835',
   site: '06764444249812161494' },
  skin);
</script>

<br />

<!-- Include the Google Friend Connect javascript library. -->
<script type="text/javascript" src="http://www.google.com/friendconnect/script/friendconnect.js"></script>
<!-- Define the div tag where the gadget will be inserted. -->
<div id="div-6867898498992386005" style="width:150px;border:1px solid #cccccc;"></div>
<!-- Render the gadget into a div. -->
<script type="text/javascript">
var skin = {};
skin['BORDER_COLOR'] = '#cccccc';
skin['ENDCAP_BG_COLOR'] = '#e0ecff';
skin['ENDCAP_TEXT_COLOR'] = '#333333';
skin['ENDCAP_LINK_COLOR'] = '#0000cc';
skin['ALTERNATE_BG_COLOR'] = '#ffffff';
skin['CONTENT_BG_COLOR'] = '#ffffff';
skin['CONTENT_LINK_COLOR'] = '#0000cc';
skin['CONTENT_TEXT_COLOR'] = '#333333';
skin['CONTENT_SECONDARY_LINK_COLOR'] = '#7777cc';
skin['CONTENT_SECONDARY_TEXT_COLOR'] = '#666666';
skin['CONTENT_HEADLINE_COLOR'] = '#333333';
skin['DEFAULT_COMMENT_TEXT'] = '- add your comment here -';
skin['HEADER_TEXT'] = 'Comments';
skin['POSTS_PER_PAGE'] = '5';
google.friendconnect.container.setParentUrl('/' /* location of rpc_relay.html and canvas.html */);
google.friendconnect.container.renderWallGadget(
 { id: 'div-6867898498992386005',
   site: '06764444249812161494',
   'view-params':{"disableMinMax":"true","scope":"SITE","features":"video,comment","startMaximized":"true"}
 },
  skin);
</script>



<div style="text-align:center">

<!-- Begin: AdBrite 
<script type="text/javascript">
   var AdBrite_Title_Color = '0000FF';
   var AdBrite_Text_Color = '000000';
   var AdBrite_Background_Color = 'D3E4F5';
   var AdBrite_Border_Color = 'D3E4F5';
</script>
<script src="http://ads.adbrite.com/mb/text_group.php?sid=331049&zs=3132305f363030" type="text/javascript"></script>
<div><a target="_top" href="http://www.adbrite.com/mb/commerce/purchase_form.php?opid=331049&afsid=1" style="font-weight:bold;font-family:Arial;font-size:13px;">Your Ad Here</a></div>


<a href="http://action.one.org/"><img
src="http://action.one.org/media/banners/ONE_banners_006_150_175.gif "
width="150" height="175" border="0"></a>
 End: AdBrite -->
<br><br>


</div>
			</td>
		</tr>
	</table>
	
<BR><BR>

</td>

<td width="3"><img src="/isai/songs/design/spacer.jpg" width="3" height="1" border="0" alt=""></td>
<td width="100%">
<!-- page text -->
<br />

<script src="ufo.js" type="text/javascript"></script>



<%
if ("checked".equalsIgnoreCase(userShuffle)) {
    playString = "displayheight=30&showfsbutton=true&width=350&height=305&repeat=true&shuffle=true&lightcolor=0x0099CC&backcolor=0x000000&frontcolor=0xCCCCCC&autostart=true";
} else {
    playString = "displayheight=30&showfsbutton=true&width=350&height=305&repeat=true&shuffle=false&lightcolor=0x0099CC&backcolor=0x000000&frontcolor=0xCCCCCC&autostart=true";
} 
%>

<% if ("old".equalsIgnoreCase(userAction)) { 
    display = "Pre-Raja";
    playString = "file=playlist.xml&" + playString;
} else if ("illayaraja".equalsIgnoreCase(userAction)) { 
    display = "Raja Hits";
    playString = "file=illayarajahits.xml&" + playString;
} else if ("arr".equalsIgnoreCase(userAction)) { 
    display = "Rehman Hits";
    playString = "file=arrhits.xml&" + playString;
} else if ("alltime".equalsIgnoreCase(userAction)) { 
    display = "Most Listened Songs";
    playString = "file=alltime.xml&" + playString;
} else if ("yuvan".equalsIgnoreCase(userAction)) { 
    display = "Yuvan Hits";
    playString = "file=yuvan.xml&" + playString;
} else if ("deva".equalsIgnoreCase(userAction)) { 
    display = "Deva Hits";
    playString = "file=deva.xml&" + playString;
} else if ("harris".equalsIgnoreCase(userAction)) { 
    display = "Harris Hits";
    playString = "file=harris.xml&" + playString;
} else if ("vidya".equalsIgnoreCase(userAction)) { 
    display = "Vidyasagar Hits";
    playString = "file=vidya.xml&" + playString;
} else if ("joshua".equalsIgnoreCase(userAction)) { 
    display = "Joshua Sridhar Hits";
    playString = "file=joshua.xml&" + playString;
} else if ("gvp".equalsIgnoreCase(userAction)) { 
    display = "G V Prakash Hits";
    playString = "file=gvp.xml&" + playString;
} else if ("mani".equalsIgnoreCase(userAction)) { 
    display = "Mani Sharma Hits";
    playString = "file=mani.xml&" + playString;
} else if ("sri".equalsIgnoreCase(userAction)) { 
    display = "Srikanth Deva Hits";
    playString = "file=sri.xml&" + playString;
} else if ("sabesh".equalsIgnoreCase(userAction)) { 
    display = "Sabesh Murali Hits";
    playString = "file=sri.xml&" + playString;    
} else if ("vijay".equalsIgnoreCase(userAction)) { 
    display = "Vijay Antony Hits";
    playString = "file=vijay.xml&" + playString;    
} else if ("bharat".equalsIgnoreCase(userAction)) { 
    display = "Bharathwaj Hits";
    playString = "file=bharat.xml&" + playString;    
} else if ("svee".equalsIgnoreCase(userAction)) { 
    display = "S Vee Sekhar Drama";
    playString = "file=svee.xml&" + playString;    
} else if ("devisri".equalsIgnoreCase(userAction)) { 
    display = "Devi Sri Prasad Hits";
    playString = "file=devisri.xml&" + playString;    
} else if ("new".equalsIgnoreCase(userAction)) { 
    display = "New Additions";
    playString = "file=newsongs.xml&" + playString;  
} else if ("tr".equalsIgnoreCase(userAction)) { 
    display = "T Rajendar Hits";
    playString = "file=tr.xml&" + playString;  
} else if ("editor".equalsIgnoreCase(userAction)) { 
    display = "Editor's Choice";
    playString = "file=editor.xml&" + playString;  
} else if ("playlist".equalsIgnoreCase(userAction)) { 
    display = artist;
    playString = "file=http://imayam.org/isai/music?action=playSong" + artist + "&" + playString;
} else if ("search".equalsIgnoreCase(userAction)) { 
    display = searchartist;
    playString = "file=http://imayam.org/isai/music?action=search" + searchartist + "&" + playString;
} else if ("userplaylist".equalsIgnoreCase(userAction)) { 
    display = artist;
    playString = "file=http://imayam.org/isai/music?action=playlist" + artist + "&" + playString;

} else { 
    display = "All Time Hits";
    playString = "file=alltime.xml&" + playString;
 } %>

</FORM>


<form name="searchForm" action="index.jsp" method="post">
    <input type="textbox" name="searchartist" />
    <input type="hidden" name="action" value="search" />
 
    <input type="submit" name="action" value="Search & Listen" />

</form>

<% if (userAction != null) { %>

<div style="FONT-SIZE: 15px;COLOR: #ff0000">You are listening to "<%= display %>"</div>

<div style="height:400;">
<div style="float:left;">

<p id="player2"><a href="http://www.macromedia.com/go/getflashplayer">Get the Flash Player</a> to see this player. or select the movie/artists list</p>

<script type="text/javascript">
var FU = { 	movie:"mp3player.swf",width:"350",height:"300",majorversion:"7",build:"0",bgcolor:"#FFFFFF",
flashvars:"<%= playString %>" };
UFO.create(FU, "player2");
</script>

<form name="songsForm2" action="index.jsp" method="post">
<INPUT TYPE="CHECKBOX" NAME="shuffle" value="checked" <%=userShuffle%> >Shuffle<P>
<input type="hidden" name="action" value="<%=userAction%>" >
<input type="hidden" name="artist" value="<%=artist%>" >
<INPUT TYPE="SUBMIT" VALUE="submit">
</FORM>


<!-- Include the Google Friend Connect javascript library. -->
<script type="text/javascript" src="http://www.google.com/friendconnect/script/friendconnect.js"></script>
<!-- Define the div tag where the gadget will be inserted. -->
<div id="div-539963761629799979" style="width:100%;"></div>
<!-- Render the gadget into a div. -->
<script type="text/javascript">
var skin = {};
skin['HEIGHT'] = '21';
skin['BUTTON_STYLE'] = 'compact';
skin['BUTTON_TEXT'] = 'Recommend it!';
skin['BUTTON_ICON'] = 'default';
google.friendconnect.container.setParentUrl('/' /* location of rpc_relay.html and canvas.html */);
google.friendconnect.container.renderOpenSocialGadget(
 { id: 'div-539963761629799979',
   url:'http://www.google.com/friendconnect/gadgets/recommended_pages.xml',
   height: 21,
   site: '06764444249812161494',
   'view-params':{"pageUrl":location.href,"pageTitle":(document.title ? document.title : location.href),"docId":"recommendedPages"}
 },
  skin);
</script>


</div>
<% } else { %>

<div style="font-size:15px;font-weight:bold;padding-top:5px;padding-left:25px;">Quick Playlist  (latest first)</div>
<div style="font-size:15px;font-weight:bold;padding-top:5px;padding-left:25px;">

<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Aboorva%20Raagangal">Aboorva Raagangal</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Aval%20Peyar%20Thamizharasi">Aval Peyar Thamizharasi</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Naanayam">Naanayam</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Thenavattu">Thenavattu</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=World%20Parliament%20of%20Religion%20(1893)">Swami Vivekananda Speech</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Vinnaithaandi%20Varuvaaya">Vinnaithaandi Varuvaaya</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Asal">Asal</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Theeratha%20Vilaiyattu%20Pillai">Theeratha Vilaiyattu Pillai</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Kutty">Kutty</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Goa">Goa</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Paiyaa">Paiyaa</a><br />
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=Rongoon%20Radha">Rongoon Radha</a><br />

<br />
<!-- Include the Google Friend Connect javascript library. -->
<script type="text/javascript" src="http://www.google.com/friendconnect/script/friendconnect.js"></script>
<!-- Define the div tag where the gadget will be inserted. -->
<div id="div-6820056164193701837" style="width:300px;border:1px solid #cccccc;"></div>
<!-- Render the gadget into a div. -->
<script type="text/javascript">
var skin = {};
skin['BORDER_COLOR'] = '#cccccc';
skin['ENDCAP_BG_COLOR'] = '#e0ecff';
skin['ENDCAP_TEXT_COLOR'] = '#333333';
skin['ENDCAP_LINK_COLOR'] = '#0000cc';
skin['ALTERNATE_BG_COLOR'] = '#ffffff';
skin['CONTENT_BG_COLOR'] = '#ffffff';
skin['CONTENT_LINK_COLOR'] = '#0000cc';
skin['CONTENT_TEXT_COLOR'] = '#333333';
skin['CONTENT_SECONDARY_LINK_COLOR'] = '#7777cc';
skin['CONTENT_SECONDARY_TEXT_COLOR'] = '#666666';
skin['CONTENT_HEADLINE_COLOR'] = '#333333';
skin['HEADER_TEXT'] = 'Recommended Playlists';
skin['RECOMMENDATIONS_PER_PAGE'] = '5';
google.friendconnect.container.setParentUrl('/' /* location of rpc_relay.html and canvas.html */);
google.friendconnect.container.renderOpenSocialGadget(
 { id: 'div-6820056164193701837',
   url:'http://www.google.com/friendconnect/gadgets/recommended_pages.xml',
   site: '06764444249812161494',
   'view-params':{"docId":"recommendedPages"}
 },
  skin);
</script>

<SCRIPT charset="utf-8" type="text/javascript" src="http://ws.amazon.com/widgets/q?ServiceVersion=20070822&MarketPlace=US&ID=V20070822/US/imayam-20/8001/d6aa8ee1-f230-4292-b0f3-a5dfcb3a10dd"> </SCRIPT> <NOSCRIPT><A HREF="http://ws.amazon.com/widgets/q?ServiceVersion=20070822&MarketPlace=US&ID=V20070822%2FUS%2Fimayam-20%2F8001%2Fd6aa8ee1-f230-4292-b0f3-a5dfcb3a10dd&Operation=NoScript">Amazon.com Widgets</A></NOSCRIPT>


<% } %>

</div>
</div>

<br><br><br>


<center>
<div style="font-size:15px;font-weight:bold;">Imayam's Most Admired Award</div>
<div style="font-size:10px;">(based on imayam top10)</div>
<div style="font-size:15px;">Composer of the Year 2009 : Harris Jeyaraj</div>
<div style="font-size:15px;">Composer of the Year 2008 : Harris Jeyaraj</div>
<div style="font-size:15px;">Composer of the Year 2007 : Harris Jeyaraj</div>
</center>
<br>

<div>
     <div id="accordionExample">


     		<div>
     		  <div  class="panelheader">
     		  	<b><center>Imayam Top 10 : 2009</center></b>
     		  </div>
     		  <div class="panelContent">
							<ol>
							    <li>Vaaranam Aayiram : Nenjukkul Peidhidum</li>
							    <li>Vaaranam Aayiram : Ava Enna</li>
							    <li>Vaaranam Aayiram : Mundhinam</li>
							    <li>Sakkarakatti : Taxi Taxi</li>
							    <li>Vaaranam Aayiram : Adiyae Kolluthey</li>
							    <li>Vaaranam Aayiram : Annul Maelae</li>
							    <li>AlaiPayuthey :  Snehidhane</li>
							    <li>Sillunnu Oru Kadhal : Munbe Munbe Vaa</li>
							    <li>Vaaranam Aayiram : Oh! Shanthi Shanthi</li>
							    <li>Sakkarakatti : Marudhaani</li>
							</ol>
     		  </div>
     		</div>



     		<div>
     		  <div  class="panelheader">
     		  	<b><center>Imayam Top 10 : 2008</center></b>
     		  </div>
     		  <div class="panelContent">
							<ol>
							    <li>Bheema : Mudhal Mazhai</li>
							    <li>Sakkarakatti : Taxi Taxi</li>
							    <li>Vaaranam Aayiram: Ava Enna</li>
							    <li>Sillunnu Oru Kadhal : New York Nagaram</li>
							    <li>Bheema : Oru Mugamo</li>
							    <li>Azhagiya Tamizh Magan: Ponmagal Vandaal</li>
							    <li>Unnaale Unnaale : June Pona</li>
							    <li>Azhagiya Tamizh Magan : Valayapatti Tamizhe</li>
							    <li>Ghajini : Oru Maalai</li>
							    <li>Vaaranam Aayiram: Nenjukkul Peidhidum</li>

							</ol>
     		  </div>
     		</div>


     		<div>
     		  <div  class="panelheader">
     		  	<b><center>Imayam Top 10 : 2007</center></b>
     		  </div>
     		  <div class="panelContent">
							<ol>
							    <li>Unnaale Unnaale : June Pona</li>
							    <li>Pachai Kili Muthucharam : Unn Siripnil</li>
							    <li>Vettaiyaadu Vilaiyaadu : Vennilave</li>
							    <li>Sillunnu Oru Kadhal : New York Nagaram</li>
							    <li>Pokkiri : Pogadhe</li>
							    <li>Deepavali : Kadhal Vaithu</li>
							    <li>Thamirabharani : Vaartha Onnu</li>
							    <li>Chennai 600028: Saroja Saman Nikalo</li>
							    <li>Sivaji : Athiradee Kaalam</li>
							    <li>Veyyil : Uriguthae</li>
							</ol>
     		  </div>
     		</div>


</div>

</td>
<!-- page text -->




<td width="156"><table width="156" height="24" cellpadding="0" cellspacing="0" border="0" background="/isai/songs/design/menutop.jpg"><tr valign="middle"><td align="center"><div style="padding-top:6px;padding-left:18px "><span class="style1">More Options</span></div></td></tr></table><table width="156" cellpadding="0" cellspacing="0" border="0" bgcolor="#D3E4F5">
		<tr valign="top">
			<td>

<br>
<b>

<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/music?action=movie">Select by Movie</a></img><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/music?action=artist">Select by Artist</a></img><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/music?action=composer">Select by Composer</a></img><br />
<img src="/isai/songs/design/redarrow.jpg" width="13" height="9" border="0" alt=""><a href="/isai/music?action=lyricist">Select by Lyricist</a></img><br />

</b>
<br />
<c:forEach var="item" items="${sessionScope.artists}">
<a href="/isai/songs/radio/index.jsp?action=playlist&artist=<c:out value="${item}" />"><c:out value="${item}" /></a><br>
</c:forEach>

                        </td>
                </tr>
        </table>
</td>






</center>

<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-1613112-1";
urchinTracker();
</script>

</body>
</html>

