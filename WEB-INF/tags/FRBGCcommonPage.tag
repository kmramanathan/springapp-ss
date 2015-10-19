<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="javascript" required="false" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" type="java.lang.String"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 	boolean member = false;
	String FRusername ="";
	if(session.getAttribute("FRusername") != null) { 
	  FRusername = (String) session.getAttribute("FRusername");
	  member = true;
  	}
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${title}</title>
<style type="text/css">
	<c:forTokens var="item" items="${stylesheet}" delims=",">
    @import url("/springapp/css/${item}");
	</c:forTokens>    
</style>

<c:forTokens var="item" items="${javascript}" delims=",">
<script type="text/javascript" src='<c:url value="/js/${item}"/>'></script>
</c:forTokens>    
<jsp:invoke fragment="head" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>
</head>

<body>

 <table width="893" height="191%" align="center" style="height:100%">
<tr>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="http://www.searchsystems.net">Search Systems Home</a> |  <a href="BGCsearchLanding.do">Criminal Records</a> | <a href="FRMenu.do">Flat Rate Menu</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do">Contact Us</a><br>
        	 <% if(member) { %>	Welcome <%=FRusername%> (<a href="FRLogout.do">logout</a>)<% } %> </div>
    <a href="http://www.searchsystems.net"><img src="/springapp/images/flatrate/logo_02.gif" alt="logo"  border="0"/></a></td>
	</tr>
	
		
		<jsp:doBody />
		
		
       <tr>
		<td width="893" class="footer" style="height:56px; background:url(/springapp/images/flatrate/bg_footer_11.jpg) top no-repeat; padding:15px 0 0 60px;">
			<p>Copyright &copy; 2009 Pacific Information Resources, Inc. All rights reserved.</p>
			<p><a href="http://www.searchsystems.net/springapp/funnel/FR-Terms-Of-Use.html" target="blank">Terms of Use</a> | 
			<a href="http://premium.searchsystems.net/privacy.php" target="blank">Privacy Policy</a> | 
			<a href="http://www.searchsystems.net/about.php" target="blank">About Us</a></p></td>
	</tr>
</table>


<!-- google analytics code -->
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
var pageTracker = _gat._getTracker("UA-6151264-1");
pageTracker._initData();
pageTracker._trackPageview();
</script>

</body>
</html>

