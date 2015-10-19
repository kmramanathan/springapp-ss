<%@include file="/WEB-INF/jsp/include.jsp" %>
<% 
	boolean member=false;
	String username=(String)session.getAttribute("username");
	if(username != null){
		member=true;
	}
	
	String key = (String) session.getAttribute("key");
	if(key == null){
	 	response.sendRedirect("/springapp/findpeople/searchLanding.do");
	 }		
	   
   String returnUrl ="findPeopleResult";
   
   if(session.getAttribute("BGlanding")!= null){
	   returnUrl = "BGfindPeopleResult";
   }
   
   Hashtable teaserResult = new Hashtable();
   if(session.getAttribute("teaserResult") != null){
	   teaserResult = (Hashtable) session.getAttribute("teaserResult");
   }
   		
   Hashtable fpResult = new Hashtable();
   if(session.getAttribute("fpResult")!= null){
	    fpResult = (Hashtable) session.getAttribute("fpResult");
	    returnUrl = "one";
   }
   
   if(teaserResult.isEmpty()) {
   		response.sendRedirect("/springapp/findpeople/searchLanding.do");
   }
   
   fpDetail fp = (fpDetail)	teaserResult.get(key); 
   if(fp == null){
	   if(fpResult != null && !fpResult.isEmpty()){
	  		fp = (fpDetail)fpResult.get(key);
	   }
   }
   
   boolean DOB = false; 
   if(fp.getDOB() != ""){
   		DOB = true;
   }
   
 %>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="springapp.web.findpeople.xmlParser.fpDetail"%>
<%@page import="java.util.Hashtable"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> <% if(DOB) { %> FCRA <% } else { %> Please Note <% } %></title>
<link type="text/css" rel="stylesheet" href="/springapp/css/findpeople_02.css">
<style type="text/css">
<!--

.menu {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #ffffff;
	float: right;
	width: 490px;
	text-align: right;
	margin: 25px 25px 0 0;
}
.menu a {
	color: #ffffff;
	text-decoration: underline;
}
.style27 {font-family: Arial, Helvetica, sans-serif}
.style30 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-style: italic;
}
.style31 {font-size: x-small}
.style58 {color: #003366; font-weight: bold; font-size: 16px;}
.style59 {
	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
}
.style61 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }
.style62 {font-size: small}
.style63 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
.style64 {color: #000000}
.style65 {font-family: Arial, Helvetica, sans-serif; color: #000000; }
-->
</style>

<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>
<script language="javascript" type="text/javascript">
function validateField()
{
	
	var flag=true;
	
<% if(!DOB) { %>	
	if(document.forms[0].BgcYob.value != "" && (document.forms[0].BgcYob.value>=1900 && document.forms[0].BgcYob.value <=1990)  ){
		document.getElementById('yob').style.visibility = "hidden";
	}
	else{
		flag = false;
		document.getElementById('yob').style.visibility = "visible";
	}
	<% } %>
	if(flag){
		document.forms[0].action="BgcInput.do";
		document.forms[0].submit();
	}
	
}

</script>

</head>
<body>
<table width="893" height="191%" align="center" style="height:100%">
<tr>
		<% if(!member) { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
    	<% } else { %>
    	<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a> | Welcome <%=username%><p>&nbsp;</p> [<a href="/springapp/findpeople/logout.do">Log Out</a>] </div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
   	 <% } %>
	</tr>
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;">
        <p>&nbsp;</p>
		
<form method="post">		
        <table width="843" height="91" align="center">
          <tr>
            <td width="773">
			 <% if(!DOB) { %>
			 <h2 class="style59">Please Note:</h2>
              <p class="style59 style64">The record you selected does not include a date of birth. If you proceed with a Background Report on this record, <strong>it will<br/> not include address history information</strong>, as those records are matched by name and date of birth. To return to the basic results list and select a different record, <a href="searchLanding.do?resultType=<%=returnUrl %>">click here</a>.</p>
			  <p class="style59 style64">&nbsp;</p>
              <p class="style59 style64">If you choose to continue, we will need a year of birth in order to do a statewide criminal record search. Please input an approximate year of birth for your subject and we  will search the name and year of birth and give you matches 3 years before and 3 years after the year you input.</p>
			<p class="style59 style64">&nbsp;</p>
            <p><span class="style61">Approximate Year of Birth:</span> 
              <label>
              <input name="BgcYob" type="text" size="4" maxlength="4">
              </label>
            </p>
			<p class="style27">&nbsp;</p>
			<p class="style65"> By  clicking the &quot;Continue&quot; button below, you confirm that your search is for personal use only, and not to be used for employment screening purposes or any other FCRA regulated use. </p>
			   <% } else { %>
            
            <h2 class="style59">Please confirm your personal-use search</h2>
            <p class="style27">As this Background Report includes a criminal record search, we are required to abide by  FCRA rules. Please confirm by clicking the &quot;Continue&quot; button below that your search is for personal use only, and not to be used for employment screening purposes. </p>
             <% } %>
			 <p class="style27">&nbsp;</p>
			<input type="hidden" name="BgcPurpose" value="Personal"/>
             <p align="center"><a href="#" onClick="return validateField();"><img src="/springapp/images/findpeople/bt-continue-small.png"  alt="continue" width="94" height="38"></a></p>
            
			<center>
           <% if(!DOB) { %> 
           <div style="font-size: 14px;color: red; visibility:hidden" id="yob"> <b>Please check the Year of Birth</b></div>
           <% } %>
           </center>
		   
            </td>
          </tr>
        </table>
</form>
        <p class="style27">&nbsp;</p>
		 <p class="style27">&nbsp;</p>
        <table width="862" border="0" align="center">
          <tr>
            <td width="630" class="content_1" style="padding:0 30px;">
              <p><span class="style27 style31">For more information on the Fair Credit Reporting Act, <a href="http://www.ftc.gov/os/statutes/fcra.htm">click here</a>. </span></p>
            <p><span class="style27 style31">To perform a U.S. Criminal Record search under a qualified FCRA purpose, <a href="https://www.searchsystems.net/springapp/funnel/landing.html">click here</a>.</span></p></td>
            <td width="193" class="content_1" style="padding:0 30px;">
              <p align="center"><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script></p>
            <p align="center"><a href="${bbbUrl}"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></p></td>
          </tr>
        </table>
        <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="813" border="0" align="center" bgcolor="#FEF8A5">
      <tr>
                  <td width="895"><p align="left" class="style30">&quot;Your website is the search engine of the future... a source that provides relief from the usual frustrations of online searching. SearchSystems.net is so consistently dependable, it feels as if you're not on this planet. It's just perfect.&quot; - Rose</p>
                      <p align="left" class="style31">&nbsp;</p>
                    <p align="left" class="style30">&quot;Your staff is great. SearchSystems.net is a 5-star program on a basic budget.&quot; - Rick</p>
                    <p align="left" class="style31">&nbsp;</p>
                <p align="left" class="style30">&quot;The one source I always mention in my presentations for private investigators and professional researchers.&quot;  - Tamara Thompson, P.I.</p></td>
                </tr>
            </table>
            <p>&nbsp;</p></td>
          </tr>
        </table>        </td>
  </tr>
	<tr>
		<td width="893" class="footer" style="height:56px; background:url(/springapp/images/findpeople/bg_footer_11.jpg) top no-repeat; padding:15px 0 0 60px;">
			<p>Copyright &copy; 2009 Pacific Information Resources, Inc. All rights reserved.</p>
			<p><a href="http://www.searchsystems.net/tos.php" target="_blank">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php" target="_blank">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php" target="_blank">About Us</a></p></td>
	</tr>
</table>
</body>
</html>
