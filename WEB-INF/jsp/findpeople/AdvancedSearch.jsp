<%@include file="/WEB-INF/jsp/include.jsp" %>
<% 
boolean member=false;
String username=(String)session.getAttribute("username");
String BGlanding=(String)session.getAttribute("BGlanding");
if(username != null)
{
	member=true;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>advanced search</title>
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
.style24 {color: #FF0000}
.style27 {font-family: Arial, Helvetica, sans-serif}
.style28 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; font-size: 16px; }
.style30 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-style: italic;
}
.style31 {font-size: x-small}
.style32 {font-size: 14px; font-family: Arial, Helvetica, sans-serif;}
.style33 {font-size: small}
.style34 {	color: #FF0000;
	font-weight: bold;
	font-size: 12px;
}
.style58 {color: #003366; font-weight: bold; font-size: 16px;}
.style344 {font-weight: bold; font-family: Arial, Helvetica, sans-serif;}
.style355 {font-family: Arial, Helvetica, sans-serif}
.style322 {
	font-size: 13px;
	font-family: Arial, Helvetica, sans-serif;
}
.style60 {font-weight: bold; font-family: Arial, Helvetica, sans-serif; color: #000000; font-size: 12px; }
-->
</style>

<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>

</head>
<body>
 <form:form method="post">
<table width="893" height="191%" align="center" style="height:100%">
	<tr>
		<% if(!member) { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="/springapp/funnel/signup.do?previousPage=/findpeople/searchLanding.do">Sign Up</a> | <% if(BGlanding != null) { %><a href="https://www.searchsystems.net/login.php?calledPage=/findpeople/searchLanding.do?resultType=BGlanding&searchType=advanced"><% } else { %><a href="https://www.searchsystems.net/login.php?calledPage=/findpeople/searchLanding.do&searchType=advanced"><% } %>Log In</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
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
		<td width="893" style="height:215px; background:url(/springapp/images/findpeople/tal_header_03.jpg) top repeat-y;">
		<% if(BGlanding != null) { %>
		
		<table width="893" style="height:215px; background:url(/springapp/images/findpeople/bg_header_top_07.jpg) top no-repeat;">
				<tr>
					<td width="893" class="content_1" style="background:url(/springapp/images/findpeople/bg_header_088.jpg) bottom no-repeat; padding:44px 565px 16px 60px;">
                      <p class="style335"><span class="style344">Step 1: </span><span class="style355">Enter information below to find the person you want to search.</span></p>
                  <p class="style335"><span class="style344">Step 2: </span><span class="style355">Select your subject from a list of matches and run your search.</span></p>
                  <p class="style322">You are only charged if you complete Step 2.</p></td>
			  </tr>
			</table>
			
			
			<% } else { %>
			
			<table width="893" style="height:215px; background:url(/springapp/images/findpeople/bg_header_top_07.jpg) top no-repeat;">
				<tr>
					<td width="893" class="content_1" style="background:url(/springapp/images/findpeople/bg_header_08.jpg) bottom no-repeat; padding:44px 565px 16px 60px;">
                      <p class="style28">Searching for someone?</p>
                      <p class="style28"> Stop the guesswork!</p>
                      <p class="style32">Find anyone's address, date of birth, phone number (if available), and <strong>the date the address was last reported!</strong></p>
                  </td>
			  </tr>
			</table>
			
			<% } %>
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
		  <table width="862" height="267" style="height:207px;">
<tr>
												<td colspan="2" style="padding:20px 30px 20px 30px;" class="content_1"><table width="796" height="198" border="0" align="left">
                                                  <tr>
                                                    <td width="770" height="148" valign="bottom"><table width="787" border="0">
                                                    <tr>
                                                          <td width="162" height="28"><div align="left"><strong>Stop the guesswork!</strong></div></td>
                                                          <td width="299"><div align="left"><strong>Reports include address dates!</strong></div></td>
                                                          <td width="117"><div align="left"></div></td>
                                                          <td width="191"><div align="left"><a href="<c:url value="/findpeople/searchLanding.do?searchType=basic"/>"><strong><u>Go to Basic Search</u></strong></a></div></td>
                                                      </tr>
                                                      </table>
                                                        <table width="784" border="0">
                                                          <tr>
                                                            <td width="200"><div align="left"><strong>First Name <span class="style24">*</span></strong></div></td>
                                                            <td width="188"><div align="left"><strong>MI</strong></div></td>
                                                            <td width="382"><div align="left"><strong>Last Name <span class="style24">*</span></strong></div></td>
                                                        </tr>
                                                          <tr>
                                                            <td><div align="left">
                                                                <form:input path="searchFirstName" cssClass="formfield" size="20"/>
                                                            </div></td>
                                                            <td><div align="left">
                                                                <form:input path="searchMidName" cssClass="formfield" size="1" maxlength="1"/>
                                                            </div></td>
                                                            <td><div align="left">
                                                                <form:input path="searchLastName" cssClass="formfield" size="25" />
                                                            </div></td>
                                                          </tr>
                                                          <tr>
                                                            <td><strong>City</strong></td>
                                                            <td><strong>State</strong></td>
                                                            <td><strong>Date of Birth</strong></td>
                                                          </tr>
                                                          <tr>
                                                            <td height="31"><form:input path="searchCity" cssClass="formfield" size="20" /></td>
                                                          <td>
														  		<form:select path="searchState" cssClass="formfield">
																		<form:options items="${searchState}"/>		
																</form:select>
																</td>
                                                            <td><form:input path="searchDob" cssClass="formfield" size="10" maxlength="10"/>
                                                                <span class="style33">(must be MM/DD/YYYY)</span></td>
                                                          </tr>
                                                        </table>
                                                      <table border="0">
                                                        <tr>
                                                          <td width="341" height="30"><span class="style34">* Required Field</span></td>
                                                          <td width="230"><input type="image" src="/springapp/images/findpeople/bt-start-search-small.png" onClick="document.forms[0].submit();" alt="start search" width="138" height="48" border="0"></td>
                                                       <% if(BGlanding == null) { %>      
                                                          <td><p style="font-size: 14px;color: red;"><strong>This page is for single searches.
																	If you have a Flat Rate plan,
																	<a href="/springapp/flatrate/FRLogin.do" style="color: red;"><u>go here</u></a> to run your search.</strong></p></td>
   														<% } %>
                                                            
                                                        </tr>
                                                      </table>                                                      </td>
                                                  </tr>
                                                </table>
					    <p>&nbsp;</p>                        </td>
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
		<center>
				<form:errors path="*">
							<div style="background-color:#FFFFCC; width:600px;" align="left" ><font color="red" ><b>Please correct the following errors:</b></font> </div>
				</form:errors>
							
								<div style="background-color:#FFFFCC;width:600px; font-size:14px" align="center" ><font color="red"><form:errors path="*" element="div" id="error" cssClass="error-box" /></font></div>
							</center>	
         <table width="862" border="0" align="center">
        
            <% if(BGlanding != null) { %>
        <tr>
   		<td  colspan="2" width="659" class="content_1" style="padding:0 30px;">
            <h3>Your Background Report includes:<img src="/springapp/images/findpeople/BG-bullets.png" alt="background includes" width="461" height="152"></h3>
            </td>
           <td width="193">&nbsp; </td>
        </tr>   
            
<% } else { %>
    <tr>
		<td colspan="2"><p style="color: maroon;font-size: 25px;font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;<strong>Tired of paying for each search?</strong></p>
             <p class="style27">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Now you can do virtually unlimited searches and pay by the day, week, month or year! <a href="/springapp/flatrate/FRLanding.do">Click for details</a>.</strong></p>
				
		</td>
	</tr>	
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>

	<tr>
		<td width="659" class="content_1" style="padding:0 30px;">

            <h3><span class="style58"><strong>Why use SearchSystems.net?</strong> </span> </h3>
            <ul>
              <li class="style27"><strong>Providing Public Record information and research since 1990.</strong></li>
              <li class="style27"><strong>Online since 1996.</strong></li>
              <li class="style27"><strong>Largest public records resource on the Internet.</strong></li>
              <li class="style27"><strong>Only public records site rated A+ by the Better Business Bureau</strong></li>

	      <li class="style27"><strong>Service you can trust.</strong></li>
            </ul>
           
            </td>
            <td width="193" class="content_1" style="padding:0 30px;">
             
             <center>  <a href="http://www.instantssl.com" id="comodoTL">SSL</a>
<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script> &nbsp;&nbsp;&nbsp;

           <a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a>
         </center>
            
            </td>
            </tr>
<% } %>
            
            
            
        </table>
        <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="815" border="0" align="center" bgcolor="#FEF8A5">
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
