<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 	boolean member = false;
   String username = "";
	if(session.getAttribute("username") != null){
		member = true;
		username = (String) session.getAttribute("username");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>please wait</title>
<style type="text/css">	
    @import url("/springapp/css/new-funnel.css");	    
</style>
<link href="/springapp/css/common.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/JavaScript" src="/springapp/js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>please wait</title>
<style type="text/css">

.style19 {font-family: Arial, Helvetica, sans-serif}
.style20 {font-size: small}
.style73 {	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight: bold;
}
.style15 {font-size: 0.8em}
.style118 {color: #FFFFFF}
.style119 {color: #000000; }
.style120 {color: #FF0000}
.style121 {color: #003366; font-family: Arial, Helvetica, sans-serif;}
.style102 {color: #003366}
.style104 {font-size: 0.8em;color: #003366;}
#maintable{padding:0 100px 0 100px!important;}

</style>

<script language="javascript" type="text/javascript">
function sendToResult()
{
	setTimeout('document.images.delayGif.src = "/springapp/images/BJL/blue-animated-search.gif"', 200);
	setTimeout('window.status = "Waiting for www.searchsystems.net..."', 200); 
	document.dummy.submit();
	setTimeout('document.images.delayGif.src = "/springapp/images/BJL/blue-animated-search.gif"', 200);
	
}
function goBack(){
	javascript:history.go(-1);
}
</script>

</head>

<% 
	if(session.getAttribute("charged") != null){
		session.removeAttribute("charged");
		response.sendRedirect("newConfirmSearch.do");
}
%> 

<body onLoad="sendToResult();">

<form name="dummy" method="post" action="newConfirmSearch.do"></form>
<center>
  <div id="owrap">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
	<td rowspan="2" valign="middle">
    <a href="http://publicrecords.searchsystems.net/"><img src="images/common/updated-logo.png" width="293" height="40" /></a>
      <!--// masthead begins //-->  
     
      </td>
      
      <td style="color:#ffffff;" align="right"><div align="right">Welcome <strong><%=username%></strong></div></td>

      <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  height="35"  width="80"><a href="https://members.searchsystems.net/login.php?username=<%=username%>" style="text-decoration: none;font-size:14px;  color:#003366; ">My Account</a>
      </td><td width="30"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="logout.do" style="text-decoration: none;font-size:14px;  color:#003366; ">Logout</a> </td>
          
 </tr>
 <tr><td colspan="4">&nbsp;</td> </tr> 
 </table>

      <!--// masthead ends //-->

      <!--// main content area begins //-->
      
    
    <table cellpadding="0" cellspacing="0" border="0" width="950"  id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> <tr><td height="50">&nbsp;</td></tr>
      
 
	<tr>
	<td><table width="850" border="0" cellspacing="0.5" cellpadding="0.5" align="center">
	<tr>
		<td colspan="3">
				<h1 style="text-align:center;"> Your Search is Processing </h1>
				<p align="center"><img name="delayGif" src="/springapp/images/BJL/blue-animated-search.gif" alt="searching" width="190" height="39"></p>
				<h4 align="center"><span class="style34d">Please do not refresh this screen or click your browser's Back button.</span></h4> 
				<h4 align="center"><span class="style34d">This may take few minutes.</span></h4> 
				<h4 align="center"><span class="style34d">We appreciate your patinence. We are searching over 550 million local,<br/> county, 
				state, national, and international records to find your results.</span></h4> 
				 </td>
	</tr>
	</table>
       
  </div>
   
<!--// footer begins //-->
<div class="footer style19h style20h" style="text-align: center;">Copyright &copy; 2017 SearchSystems.net. All rights reserved.</div>
<!--// footer ends //-->
	
</center></body>
</html>
  
	