<%
	ServletContext context = pageContext.getServletContext();
%>
<h2>Container info</h2>
<h3>Container</h3>
<p><%= context.getServerInfo() %></p>

<h3>Servlet version</h3>
<p><%= context.getMajorVersion() %>.<%= context.getMinorVersion() %></p>

<h2>Page info</h2>
<h3>Request URI</h3>
<p><%= request.getRequestURI() %></p>

<h3>Real path</h3>
<p><%= context.getRealPath(request.getRequestURI()) %></p>

<h3>Servlet name</h3>
<p><%= pageContext.getServletConfig().getServletName() %></p>

<h3>Web app context</h3>
<p><%= request.getContextPath() %></p>

<h3>Server name</h3>
<p><%= request.getServerName() %></p>
