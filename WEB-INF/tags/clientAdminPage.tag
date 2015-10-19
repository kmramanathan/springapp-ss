<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="javascript" required="false" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" type="java.lang.String"%>
<%@attribute name="showTopNav" required="false" type="java.lang.String"%>
<%@attribute name="showSideNav" required="false" type="java.lang.String"%>

<% if(session.getAttribute("adminClientUserSession") == null){
		response.sendRedirect("admin/login.do");	
	}
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>${title}</title>
<style type="text/css">
	<c:forTokens var="item" items="${stylesheet}" delims=",">
    @import url("<c:url value="/css/"/>${item}");
	</c:forTokens>    
</style>

<c:forTokens var="item" items="${javascript}" delims=",">
<script type="text/javascript" src="<c:url value="/js/"/>${item}"></script>
</c:forTokens>    
<jsp:invoke fragment="head" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
</head>

<%-- body starts here --%>
<body>
<div id="container">

<%-- top nav: show main nav then per page nav --%>
<div id="header">
<c:url value="/admin/reports/clientIndex.do"   var="reports"  />
<c:url value="/admin/logout.do"    var="logout"   />
Main Nav:
<a href="${reports}">Reports</a> |
<a href="${logout}">Logout</a>
<br/>

<%-- can do this for additional top nav --%>
<%-- 
<c:import url="nav-top.jsp"/>
--%>
</div>

<%-- side nav --%>
<div id="nav">
Side Nav:<br/>
<c:import url="client-nav-side.jsp"/>
</div>

<%-- 
page nav can also be done with just:

<c:import url="top.jsp"/>

which appears to work fine in context.

Best idea is to put this tag file at the top of /tags, then import
the nav here. Perhaps allow the JSP using this taglib to specify a nav page.
--%>

<%-- main content will go here --%>
<div id="content">
<jsp:doBody />
</div>

<div id="footer">
  <a href="http://validator.w3.org/check?uri=referer"><img src="http://www.w3.org/Icons/valid-html401" border="0" alt="Valid HTML 4.01 Transitional" height="31" width="88"></a>
</div>

<%-- body ends here --%>
</div>
</body>

</html>
