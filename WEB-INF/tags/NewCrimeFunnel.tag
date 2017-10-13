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
<META NAME="keywords" CONTENT="Search the largest, most powerful criminal record database available to the public">
<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>

<link href="/springapp/css/common.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/JavaScript" src="/springapp/js/common.js"></script>


<style type="text/css">
.menu {
	font-family:Arial, Helvetica, sans-serif;
	font-size: 16px;
	font-weight: bold;
	color: #ffffff;
	
	width: 450px;
	text-align: right;
	margin: 0px 0px 0 0;
	float: right;
}
.menu a {
	color: #ffffff;
	text-decoration: none;
	padding-left:10px;
}
.contenttable
{
margin:left auto!important;margin:right auto!important;
width:100%;
text-align:left;

}
.homenav{font-family: Arial, Helvetica, sans-serif;
color:#fff; margin-top:0px;float:right;}
.homenav a{font-family: Arial, Helvetica, sans-serif;font-size:13px;
color:#fff; font-weight:bold;text-decoration:none}
.homenav a:hover
{
text-decoration:none!important;
}
a.homelink:hover
{
text-decoration:none!important;
}
</style>
</head>

<body>

<center>
  <div id="owrap">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
	<td rowspan="2" valign="middle">
    <a href="http://dev.searchsystems.net/"><img src="images/common/updated-logo.png" width="293" height="40" /></a>
      <!--// masthead begins //-->
  
     
      </td>
      
     <% if(member) { %>  
      <td style="color:#ffffff;" align="right"><div align="right">Welcome <strong><%=username%></strong></div></td>
      <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  width="80"><a href="https://members.searchsystems.net/login.php?username=<%=username%>" style="text-decoration: none;font-size:14px;  padding:5px 0px 10px 0px;  color:#003366; ">My Account</a>
      </td><td width="30"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="logout.do" style="text-decoration: none;font-size:14px;   padding:5px 0px 10px 0px;  color:#003366; ">Logout</a> </td>
      
     <% } else { %> 
     <td align="right" width="50%">&nbsp;</td>
	  <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  width="80"><a href="https://www.dev.searchsystems.net/springapp/funnel/login.do" style="text-decoration: none;font-size:14px;  padding:5px 0px 10px 0px;  color:#003366; ">My Account</a>
      </td><td width="40"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="login.do" style="text-decoration: none;font-size:14px;   padding:5px 0px 10px 0px;  color:#003366; ">Log In</a> </td>
     <% } %>

 </tr>
 <tr><td valign="top" colspan="3"><div class="homenav">
			<a href="http://dev.searchsystems.net/" class="homelink">Home Page</a> 
		| <a href="/springapp/premium" class="homelink">Premium Databases</a>
		| <a href="http://dev.searchsystems.net/contact.php">Contact</a></div>
	</td>
</tr>
 </table>
 
 <div style="background:#ff9900;height:26px;border-top:1px solid #000">&nbsp;</div>
 <!--// masthead ends //-->

      <!--// main content area begins //-->
      <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> 
        <td id="colone" align="left">
        <table width="100%" align="center" class="contenttable"  cellpadding="0" cellspacing="0" border="0">

            <jsp:doBody />
              
          </table>
        </td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
        
    
   	  </tr></table>
  </div>
   
<!--// footer begins //-->
<div style="clear: both; height: 30px;"></div></div>
<c:set var="now" value="<%=new java.util.Date()%>" />
<div class="footer">Copyright &copy; 1997- <fmt:formatDate pattern="yyyy" value="${now}" /> Search Systems, Inc. All rights reserved.
<address>SearchSystems.net, 2945 Townsgate Road, Suite 200, Westlake Village, California 91361</address>
<address>Call Us at: 805-375-4041 | 
<a href="http://publicrecords.searchsystems.net/privacypolicy.php">Privacy Policy</a>
</address>
</div>

<!--// footer ends //-->
	
</center>     
              
  </body>
</html>
