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
<title>Confirm Purchase</title>
<style type="text/css">	
    @import url("/springapp/css/new-funnel.css");	    
</style>
<link href="/springapp/css/common.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/JavaScript" src="/springapp/js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>please wait</title>
<style type="text/css">
<!--
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
-->
</style>
<script language="javascript" type="text/javascript">
function sendToResult()
{
	setTimeout('document.images.delayGif.src = "/springapp/img/ajax-progress-bar.gif', 10);
	setTimeout('window.status = "Waiting for www.searchsystems.net..."', 10); 
	document.dummy.submit();
	setTimeout('document.images.delayGif.src = "/springapp/img/ajax-progress-bar.gif"', 10);
	
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
 <tr><td colspan="4">&nbsp;</td> </tr>
 </table>

      <!--// masthead ends //-->

      <!--// main content area begins //-->
      
    
    <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> <tr><td height="50">&nbsp;</td></tr>
       <tr>
                                                    <td width="868" height="210">
                                                    <h1><span class="style102">Your Search is Processing</span></h1>
                                                    <p>&nbsp;</p>
                                                        <p align="left"><img name="delayGif" src="/springapp/img/ajax-progress-bar.gif" alt="searching" width="128" height="15"></p>
                                                   
                                                    <p class="style102"><strong>Please do not navigate away from this page. Your search is processing and may take a few moments to complete.</strong></p>
              <p class="style102"><strong>We are searching all records in the state and </strong><strong> verifying the records to ensure a proper match to the criteria you input.</strong> <strong>We appreciate your patience.</strong></p>
													</td>
                                                  </tr>
 
	<tr>
	<td>&nbsp;
	</td>
	</tr>
		
  
                
            </table>
      
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
    
   	 
  </div>
   
<!--// footer begins //-->
<div style="clear: both; height: 30px;"></div><div class="footer style19h style20h">Copyright &copy; 2016 SearchSystems.net. All rights reserved.</div>

<!--// footer ends //-->
	
</center></body>
</html>
  
	