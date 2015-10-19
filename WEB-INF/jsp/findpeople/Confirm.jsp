
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 
	String username=(String)session.getAttribute("username");
	boolean member=false;
	if(username != null)
	{
		member=true;
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>confirm</title>
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
.style32 {font-family: Arial, Helvetica, sans-serif; color: #003366; }
.style34 {font-size: small; color: #990000;}
-->
</style>
<script language="javascript" type="text/javascript">
function callWait()
{
	document.getElementById('wait').style.visibility = "visible";
	document.forms[0].submit();
}
</script>

</head>
<body>
<form:form method="post">

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
        <table width="893" height="338" border="0">
          <tr>
            <td width="775" height="334"><table width="641" border="0">
              <tr>
                  <td height="52"><h1 align="left" class="style32">Please Confirm Your Search</h1></td>
              </tr>
              </table>
                <table width="641" height="150" border="0">
                  <tr>
                    <td height="131"><table width="614" height="129" border="0" align="left">
                        <tr>
                          <td width="171" height="24"><div align="left" class="style27">Name to Search:</div></td>
                          <td width="542"><div align="left" class="style27"><strong><c:out value="${searchName}"/></strong></div></td>
                      </tr>
                        <tr>
                          <td height="24"><div align="left" class="style27">Type of Search:</div></td>
                          <td><div align="left" class="style27"><strong><c:out value="${Type}"/></strong></div></td>
                      </tr>
                        <tr>
                          <td height="25"><div align="left" class="style27">Location:</div></td>
                           <c:choose>
       							 <c:when test='${searchState == ""}'>
           							 <td><div align="left" class="style27"><strong><c:out value="All States"/></strong></div></td>
        						</c:when>
        						<c:otherwise>
           						 		<td><div align="left" class="style27"><strong><c:out value="${searchState}"/></strong></div></td>
        						</c:otherwise>
    						</c:choose>

                          
                      </tr>
                        <tr>
                          <td><div align="left" class="style27">Price:</div></td>
                          <td><div align="left" class="style27"><strong>$ <c:out value="${Price}"/></strong></div></td>
                          <input type="hidden" name="Price" value="${Price}">
                      </tr>
                        <tr>
                          <td><div align="left" class="style27">Credit Card Number:</div></td>
                          <td><div align="left" class="style27"><strong>***<c:out value="${ccLast4}"/></strong></div></td>
                      </tr>
                    </table></td>
                  </tr>
                </table>
              <table width="641" border="0">
                <tr>
                    <td width="633" height="65"><p align="left"><span class="style27"><strong>Please verify that the search  query is correct.</strong> If the  information you input is correct and you accept the charge for this search,  press Continue To My Search.</span></p></td>
                </tr>
           <c:if test="${Type eq 'Find People'}">     
                <tr>
                    <td width="633" height="65"><p align="left"><span class="style27"><strong>Your card will be charged for this single search. If you have a Flat Rate plan, and wish to perform a search of that database at no charge, you need to <a href="/springapp/flatrate/FRLogin.do">go here</a> instead.</strong></td>
                </tr>
           </c:if>
              </table>
              <p align="center"><input type="image" src="/springapp/images/findpeople/bt-continue-to-search.png" onClick="this.disabled=true;callWait();" alt="continue to search" width="201" height="60"></p>
            <h5>  <p align="center" id="wait" style="font-family: Arial, Helvetica, sans-serif; color: #990000; visibility:hidden">Please be patient and do not click this button twice or use your browser's
"Back" button. We are processing your payment and loading your results. This
may take several moments.</p></h5>
            <p align="center"><img src="/springapp/images/findpeople/site-nav-123-confirm.png" alt="confirm" width="584" height="39"></p></td>
            <td width="613"><p><img src="/springapp/images/findpeople/ss-logo-dotnet-bwhite.png" alt="ss logo" width="237" height="166"></p>
              <p align="center">&nbsp;</p>
              <p align="center"><img src="/springapp/images/findpeople/Comodo-seal-100.gif" width="100" height="60"></p>
              <p align="center">&nbsp;</p>
              <p align="center"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></p>
            </td>
          </tr>
        </table>
        <p>&nbsp;</p>
        <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="811" border="0" align="center" bgcolor="#FEF8A5">
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

</form:form>
</body>
</html>
