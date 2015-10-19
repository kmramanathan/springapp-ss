<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="javascript" required="false" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" type="java.lang.String"%>
<%@attribute name="load" required="false" type="java.lang.String"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 	boolean member = false;
 String username="";
	if(session.getAttribute("username") != null){
		member = true;
	 username = (String) session.getAttribute("username");
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
<META NAME="keywords" CONTENT="background check, criminal records, nationwide, statewide, criminal court records, inmates">
<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>

<link href="/springapp/css/common.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/JavaScript" src="/springapp/js/common.js"></script>
<style type="text/css">
.menu {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #ffffff;
	float: right;
	width: 450px;
	text-align: right;
	margin: 25px 25px 0 0;
	float: right;
}
.menu a {
	color: #ffffff;
	text-decoration: underline;
}
</style>
</head>

<body>

<center>
  <div id="owrap">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
	<td rowspan="2" valign="middle">
    <a href="http://publicrecords.searchsystems.net/"><img src="images/common/updated-logo.png" width="293" height="40" /></a><img src="/springapp/images/newfunnel/criminal-records-logo.gif" alt="criminal records" style="padding-left: 30px" width="247" height="31" />
      <!--// masthead begins //-->
  
     
      </td>
      
      <% if(member) { %>  
      <td style="color:#ffffff;" align="right"><div align="right">Welcome <strong><%=username%></strong></div></td>
      <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  width="80"><a href="https://members.searchsystems.net/login.php?username=<%=username%>" style="text-decoration: none;font-size:14px;  padding:5px 0px 10px 0px;  color:#003366; ">My Account</a>
      </td><td width="30"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="logout.do" style="text-decoration: none;font-size:14px;   padding:5px 0px 10px 0px;  color:#003366; ">Logout</a> </td>
      
     <% } else { %> 
      <td colspan="3">&nbsp;</td>
     <% } %>

 </tr>
 <tr><td colspan="4">&nbsp;</td></tr>
 <tr><td colspan="4" align="right"><div class="menu"><a href="http://publicrecords.searchsystems.net/">Search Systems Home</a> | <a href="http://publicrecords.searchsystems.net/contact.php">Contact Us</a></div></td> </tr>
 </table>
 <!--// masthead ends //-->

      <!--// main content area begins //-->
      <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> 
        <td id="colone" align="left">
        <table width="866" align="center">

            <jsp:doBody />
              
          </table>
        </td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
        
    
   	  </tr></table>
  </div>
   
<!--// footer begins //-->
<div style="clear: both; height: 30px;"></div></div><div class="footer style19 style20">Copyright &copy; 1997-2012 Search Systems, Inc. All rights reserved.</div>

<!--// footer ends //-->
	
</center>     
              
  </body>
</html>
