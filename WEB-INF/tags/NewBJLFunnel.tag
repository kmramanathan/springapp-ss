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
	float: right;
	width: 450px;
	text-align: right;
	margin: 0px 0px 0 0;
	
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
    <a href="http://publicrecords.searchsystems.net/"><img src="images/common/updated-logo.png" width="293" height="40" /></a>
      <!--// masthead begins //-->
  
     
      </td>
      
      <% if(member) { %>  
      <td style="color:#ffffff;" align="right"><div align="right">Welcome <strong><%=username%></strong></div></td>
      <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  width="80"><a href="https://members.searchsystems.net/login.php?username=<%=username%>" style="text-decoration: none;font-size:14px;  padding:5px 0px 10px 0px;  color:#003366; ">My Account</a>
      </td><td width="30"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="logout.do" style="text-decoration: none;font-size:14px;   padding:5px 0px 10px 0px;  color:#003366; ">Logout</a> </td>
      
     <% } else { %> 
      <td align="right" width="50%">&nbsp;</td>
	  <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  width="80"><a href="https://members.searchsystems.net/login.php" style="text-decoration: none;font-size:14px;  padding:5px 0px 10px 0px;  color:#003366; ">My Account</a>
      </td><td width="40"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="login.do" style="text-decoration: none;font-size:14px;   padding:5px 0px 10px 0px;  color:#003366; ">Log In</a> </td>
     <% } %>

 </tr>
 <tr><td valign="top" colspan="3"><div class="homenav"><a href="http://publicrecords.searchsystems.net/" class="homelink">Home Page</a> | <a href="https://www.searchsystems.net/springapp/funnel/newSearch.do" class="homelink">Criminal Records</a>
      | <a href="https://www.searchsystems.net/springapp/funnel/newSearchBJL.do">Bankruptcies-Judgments-Liens</a> | <a href="https://www.searchsystems.net/springapp/funnel/eviction-records.do">Tenant Screening</a> | <a href="http://publicrecords.searchsystems.net/contact.php">Contact</a></div></td></tr>
 </table>
 <div style="background:#ff9900;height:26px;border-top:1px solid #000">
 <a href="http://www.bbb.org/santa-barbara/business-reviews/internet-services/searchsystems-net-in-newbury-park-ca-13000131/" target="blank"><img src="images/common/bbb-clickratingsm.gif" alt="bbb" width="80" height="25" style="float:right;padding:0;margin:0" /></a>
 </div>
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
<div class="footer style19h style20h"><img src="/springapp/images/common/Footer-trans.png" border="0 alt="ss-footer" width="418" height="60" usemap="#footer"></div>
<map name="footer">
  <area shape="poly" coords="242,38,242,50,278,55,321,56,324,40,277,40,243,37," alt="Privacy Policy" href="http://publicrecords.searchsystems.net/privacypolicy.php">
 
</map>

<!--// footer ends //-->
	
</center>     
              
  </body>
</html>
