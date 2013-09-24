<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
<title>
Imayam GRE/GMAT Answer Sheet
</title>
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-1613112-1";
urchinTracker();
</script>
</head>
<body bgcolor="#ffffff">
<div style="float:left;">
<h1>
Imayam GRE/GMAT Answer Sheet
</h1>

<form method="post" action="gre">
	<c:if test="${sessionScope.result == null}">
        <table>
            <tr><td>Reference #</td><td><input type="text" name="reference" value="ABCD" /></td></tr>
            <tr><td>No Of Questions</td><td><input type="text" name="questionCount" value="50" /></td></tr>
            <tr><td>Duration (min)</td><td><input type="text" name="duration" value="120" /></td></tr>
            <tr><td>Date </td><td><input type="text" name="examDate" value="6/28/2007" /></td></tr>
            <tr><td></td><td><input type="hidden" name="action" value="details" /><input type="submit" value="Start Test" /></td></tr>
        </table>
	</c:if>
    <c:if test="${sessionScope.result != null}">
        <table border="1">
            <tr><td>Reference #</td><td><b><c:out value="${sessionScope.result[0].reference}" /></b></td><td>No Of Questions</td><td><b><c:out value="${sessionScope.result[0].questionCount}" /></b></td></tr>
            <tr><td>Duration (min)</td><td><b><c:out value="${sessionScope.result[0].duration}" /></b></td><td>Date </td><td><b><c:out value="${sessionScope.result[0].examDate}" /></td></b></tr>
            <tr><td colspan="2"><b>Time Left :</b></td><td colspan="2"><b><c:out value="${sessionScope.result[0].timeLeft}" /></b></td></tr>
            <tr><td colspan="4">
                <b>Select Options : <br />
				<input type="radio" name="choice" value="A" /> A
				<input type="radio" name="choice" value="B" /> B
				<input type="radio" name="choice" value="C" /> C
				<input type="radio" name="choice" value="D" /> D
				<input type="radio" name="choice" value="E" /> E
				</b>
            </td></tr>
            <tr><td colspan="4" align="right">
				<input type="submit" name="action" value="Reset">
				<input type="submit" name="action" value="Next">
            </td></tr>
        </table>

    <table border="1">
	<tr><th>Id</th><th>Option Selected</th><th>Time Spend (in sec)</th>
	</tr>
	<c:forEach items="${sessionScope.result}" var="question">
		<tr>
        <td><c:out value="${question.id}"></c:out></td>
		<td>
            <c:if test="${question.option != null}">
                <c:out value="${question.option}"></c:out>
            </c:if>
    	</td>
        <td><c:out value="${question.diff}"></c:out></td>
		</tr>
	</c:forEach>

	</table>
    </c:if>
</form>
<ul>
    <li>Hit Start Button for each question</li>
    <li>It should be followed by Stop, if not you might get some error!</li>
	<li>Hit reset to start fresh or take a 30+ min break!</li>
</ul>
</div>
<div style="float:right;">
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
</body>
</html>
