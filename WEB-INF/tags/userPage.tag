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
<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

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
<meta http-equiv="Pragma" content="no-cache">
</head>

<!-- start page -->  
<body>
<center>

<!-- header -->
<div class="top-menu">
<a href="http://www.searchsystems.net/index.php" target="_top">Home Page</a> | 
<a href="http://premium.searchsystems.net/" target="_top">Premium Services</a> | 
<a href="http://www.searchsystems.net/webmaster.php" target="_top">Contact Us</a> | 
<a href="http://www.searchsystems.net/tos.php" target="_top">Terms of Service</a> | 
<c:if test="${authenticated}"> 
	<a href="/index.php" target="_top">My Account</a> |
	<a href="/logout.php" target="_top">Logout</a>
</c:if>
<c:if test="${!authenticated}"> 
	<a href="/login.php" target="_top">Login</a> | 
	<a href="https://signup.searchsystems.net/register.php" target="_top">Sign Up</a>
</c:if>
</div>

<div class="main-banner">
<a href="http://www.searchsystems.net/" target="_top">
	<img src='<c:url value="/img/searchsystemsnet.gif"/>' width="344" height="41" border="0" alt="search systems logo" style="padding-right: 10px;"/>
</a>&nbsp;
<img src='<c:url value="/img/publicrecordsdirectory.gif"/>' width="294" height="29" border="0" alt="public records directory" style="padding-left: 10px; padding-bottom: 5px; "/>
</div>


<!-- main content -->
<div class="main-content">
<jsp:doBody/>
</div>

<!-- footer -->
<div class="footer">
<table>
    <tr>
	<td class="menu" style="padding: 10px; white-space: nowrap;">
	   <div class="copyright" style="text-align: left;">
		Copyright &#169; 2003-2007 Pacific Information Resources, Inc.<br /> All rights reserved.
	   </div>
	</td>
    </tr>
</table>
</div>

<!-- end page -->
</center>
</body>

</html>
