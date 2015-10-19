
<%@ page language="java" import="springapp.web.findpeople.SearchLandingForm" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 
boolean member=false;
String username=(String)session.getAttribute("username");
if(username != null)
{
	member=true;
}
 
 SearchFPFormCommand sf=(SearchFPFormCommand) session.getAttribute("searchFPFormCommand");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="springapp.web.findpeople.SearchLandingForm.SearchFPFormCommand"%>
<%@page import="springapp.web.findpeople.SearchLandingForm.SearchFPFormCommand"%>
<%@page import="springapp.web.findpeople.SearchLandingForm.SearchFPFormCommand"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>no results found</title>
<link type="text/css" rel="stylesheet" href="/springapp/css/findpeople_02.css">
<style type="text/css">
<!--

.menu {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #ffffff;
	float: right;
	width: 400px;
	text-align: right;
	margin: 25px 25px 0 0;
}
.menu a {
	color: #ffffff;
	text-decoration: underline;
}
.style24 {color: #FF0000}
.style26 {color: #FF0000; font-weight: bold; }
.style27 {font-family: Arial, Helvetica, sans-serif}
.style28 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; font-size: 16px; }
.style30 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-style: italic;
}
.style31 {font-size: x-small}
.style32 {font-size: 14px; font-family: Arial, Helvetica, sans-serif;}
.style13 {color: #000000;
	font-weight: bold;
}
.style33 {	font-size: 14px;
	color: #FF0000;
}
.style35 {color: #FF0000; font-weight: bold; font-size: 14px; }
-->
</style>


<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>

</head>
<body>
<table width="893" height="191%" align="center" style="height:100%">
<tr>
		<% if(!member) { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="/springapp/findpeople/searchLanding.do">Home Page</a> | <a href="/springapp/funnel/signup.do?previousPage=/findpeople/searchLanding.do">Sign Up</a> | <a href="http://www.searchsystems.net/login.php?calledPage=/findpeople/searchLanding.do">Log In</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
    	<% } else { %>
    	<td width="893" style="height:70px; ">
        	<div class="menu"><a href="/springapp/findpeople/searchLanding.do">Home Page</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a> | Welcome <%=username%><p>&nbsp;</p> [<a href="/springapp/findpeople/logout.do">Log Out</a>] </div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
   	 <% } %>
	</tr>
	<tr>
		<td width="893" style="height:215px; background:url(/springapp/images/findpeople/tal_header_03.jpg) top repeat-y;">
			<table width="893" style="height:215px; background:url(/springapp/images/findpeople/bg_header_top_07.jpg) top no-repeat;">
				<tr>
					<td width="893" class="content_1" style="background:url(/springapp/images/findpeople/bg_header_08.jpg) bottom no-repeat; padding:44px 565px 16px 60px;">
                      <p class="style28">&nbsp;</p>
                  <p class="style28">No results were found.</p>
                  <p class="style28">Please try a different search.</p></td>
			  </tr>
			</table>
        </td>
	</tr>
	<tr>
		<td width="893" style="height:207px; background:url(/springapp/images/findpeople/bg_content_02.jpg) top no-repeat; padding:18px 7px 0 20px;">
			<div class="side_right">
				<div class="fill_bot">
					<div class="fill_top">
						<div class="left_top">
							<div class="left_bot">
								<div class="right_top">
									<div class="right_bot">
		  <table width="850" height="267" style="height:207px;">
<tr>
												<td width="1285" colspan="2" class="content_1" style="padding:20px 30px 20px 30px;"><h3>We're sorry but no records were found in our database for:</h3>
												  <table width="540" border="0" align="left">
                                                    <tr>
                                                      <td width="131"><h4 align="left" class="style13 style33"><%=sf.getSearchFirstName()%> <%=sf.getSearchMidName()%> <%=sf.getSearchLastName() %></h4></td>
                                                      <td width="86"><h4 align="left" class="style35"><%=sf.getSearchCity() %></h4></td>
                                                      <td width="46"><h4 align="left" class="style35"><%=sf.getSearchState() %></h4></td>
                                                      <td width="190"><h4 class="style35"><% if(sf.getSearchDob() != "" ) { %>DOB: <%=sf.getSearchDob()%> <%} %> </h4></td>
                                                    </tr>
                                                  </table>					                              
					                              <p>&nbsp;</p>
					                              <p>&nbsp;</p>
					                              <table width="683" border="0">
                                                    <tr>
                                                      <td width="214"><p align="center"><strong>To try another search, </strong><strong>click the button below:</strong></p>
                                                        <table width="135" border="0" align="center">
                                                            <tr>
                                                              <td width="129"><div align="center"><a href="<c:url value="/findpeople/searchLanding.do"/>"><img src="/springapp/images/findpeople/bt-search-again-xsmall.png" alt="search again" width="109" height="32" border="0"></a></div></td>
                                                            </tr>
                                                          </table>
                                                        <p>&nbsp;</p></td>
                                                      <td width="459"><table width="410" border="1" align="center" cellpadding="1" cellspacing="1">
                                                        <tr>
                                                          <td><p align="left"><strong>Search Tips:</strong></p>
                                                            <p align="left">If no results were found, chances are you input too much information. </p>
                                                            <p align="left">Try a broader search, using just a name.</p>
                                                            <p align="left">If that returns too many results, refine your search using a state or city and state.</p>
                                                          <p align="left">If you still get too many results, try using the Advanced Search and include a date of birth.</p></td>
                                                        </tr>
                                                      </table>
                                                        </td>
                                                    </tr>
                                                  </table>                                                  
                        <p>&nbsp;</p></td>
</tr>
									  </table>
								  </div>
							  </div>
							</div>
						</div>
					</div>
				</div>
	  </div></td>
	</tr>
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;">
        <table width="862" border="0" align="center">
          <tr>
            <td width="659" class="content_1" style="padding:0 30px;">
            <h3><span class="style27"><strong>SearchSystems.net</strong> </span> </h3>
            <ul>
              <li class="style27"> Public Records research since 1990.</li>
              <li class="style27"> Online since 1996.</li>
              <li class="style27"> Largest public records resource on the net.</li>
              <li class="style27"> Only public records site rated A+ by the Better Business Bureau.</li>
            </ul></td>
            <td width="193" class="content_1" style="padding:0 30px;">
              <p align="center"><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script>
</p>
            <p align="center"><a href="${bbbUrl}"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></p></td>
          </tr>
        </table>
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
</body>
</html>
