<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 
boolean member=false;
String username=(String)session.getAttribute("username");
if(username != null)
{
	member=true;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>find people all details</title>
<link type="text/css" rel="stylesheet" href="/springapp/css/findpeople_02.css">
<style type="text/css">
<!--

.menu {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #ffffff;
	float: right;
	width: 300px;
	text-align: right;
	margin: 25px 25px 0 0;
}
.menu a {
	color: #ffffff;
	text-decoration: underline;
}
.style24 {color: #FF0000}
.style27 {font-family: Arial, Helvetica, sans-serif}
.style30 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-style: italic;
}
.style31 {font-size: x-small}
.style23 {font-size: small}
.style32 {font-family: Arial, Helvetica, sans-serif; color: #003366; }
.style34 {
	font-size: x-small;
	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
}
.style36 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-weight: bold;
}
.style37 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
-->
</style>

<script language="JavaScript">
	function memReq() {
            document.forms[1].action='/springapp/findpeople/resultsFPRecords.do?resultType=BG&memberRequest=true';
            document.forms[1].submit();
        }

</script>

</head>
<body>
<table width="893" height="191%" align="center" style="height:100%">
<tr>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="#">Home Page</a> | <a href="#">Sign Up</a> | <a href="#">Log In</a> | <a href="#">Contact Us</a></div>
    <img src="/springapp/images/findpeople/logo_02.gif" alt="" /></td>
	</tr>
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;"><table width="893" height="714" border="0">
          <tr>
            <td width="896" height="710" valign="top">
			
			<% if(!member)	 {	%>	
			<table width="893" height="63" border="0" bgcolor="#fffeb1">
              <tr>
                  <td><div align="left"><img src="/springapp/images/findpeople/ban-access-more.png" alt="access more" width="840" height="155" border="0" usemap="#Map"></div></td>
              </tr>
              </table>
			 <%   }  %>
                <table width="893" height="109" border="0">
                  <tr>
                    <td width="503" height="105"><table width="420" align="center">
                      <tr>
                        <td><h1><span class="style32">All Details</span></h1></td>
                      </tr>
                    </table>                    </td>
                    <td width="432"><div align="right">
                   <form:form method="post" action="/springapp/findpeople/searchLanding.do">
                    <table width="351" border="0">
                      <tr>
                            <td width="124"><div align="left" class="style23 style27"><strong>First Name <span class="style24">*</span></strong></div></td>
                            <td width="100"><div align="left" class="style23 style27"><strong>Last Name <span class="style24">*</span></strong></div></td>
                            <td width="147"><div align="left" class="style23 style27"><strong>State</strong></div></td>
                          </tr>

                      <tr>
                        <td><div align="left">
                            <form:input path="searchFirstName" cssClass="formfield" size="16" />
                        </div></td>
                        <td><div align="left">
                            <form:input path="searchLastName" cssClass="formfield" size="18" />
                        </div></td>
                        <td><div align="left">
                            <form:select path="searchState" cssClass="formfield">
										<form:options items="${searchState}"/>		
							</form:select>
                        </div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><div align="right"><img src="/springapp/images/findpeople/bt-search-again-xsmall.png"  onclick="document.forms[0].submit();" alt="search again" width="109" height="32" border="0"></div></td>
                      </tr>
                    </table>
                  </form:form>
                    </div></td>
                  </tr>
                </table>
                
                <form:form method="post" action="/springapp/findpeople/resultsFPRecords.do?resultType=BG">
                
              <table width="893" height="132" border="0">
                <tr>
                    <td width="813" height="128"><table width="893" border="2" bordercolor="#999999" >
                      <tr>
                        <td width="34" height="29" bordercolor="#666666">&nbsp;</td>
                        <td width="165" bordercolor="#666666"><div align="center" class="style36">Name</div></td>
                        <td width="67" bordercolor="#666666"><div align="center" class="style36">Age</div></td>
                        <td width="148" bordercolor="#666666"><div align="center" class="style36">Location</div></td>
                        <td width="179" bordercolor="#666666"><div align="center" class="style36">Address</div></td>
                        <td width="93" bordercolor="#666666"><div align="center" class="style36">Phone</div></td>
                        <td width="100" bordercolor="#666666"><div align="center" class="style36">Date of Birth</div></td>
                        <td width="112" bordercolor="#666666"><div align="center" class="style36">Date Reported</div></td>
                      </tr>
                      <tr>
                        <td height="28" bordercolor="#666666"><input type="radio" name="key" id="radio" value="testing" checked="checked"/>  </td>
                      <td bordercolor="#666666"><div align="center" class="style37">
                            <p>John Doe</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>38</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>New York, NY</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>100 W 21st Ave Apt E3</p>
                        </div></td>
                        <td bordercolor="#666666"><p align="center" class="style37">212-555-1010</p></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>03-04-1971</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>01/2006</p>
                        </div></td>
                      </tr>
                      <tr>
                        <td height="30" bordercolor="#666666"><div align="left">
                            <input type="radio" name="radio" id="radio2" value="radio" />
                        </div></td>
                      <td bordercolor="#666666"><div align="center" class="style37">
                            <p>John Q Doe</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>36</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>Portland, OR</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>55 Meadowgrove Ln</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center"><span class="style27"><span class="style31"></span></span></div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>11-10-1973</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>03/2008</p>
                        </div></td>
                      </tr>
                      <tr>
                        <td height="31" bordercolor="#666666"><div align="left">
                            <input type="radio" name="radio" id="radio3" value="radio" />
                        </div></td>
                      <td bordercolor="#666666"><div align="center" class="style37">
                            <p>John T Doe</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>41</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>Los Angeles, CA</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>101 Santa Monica Blvd</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center"><span class="style27"><span class="style31"></span></span></div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>02-02-1968</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>11/2007</p>
                        </div></td>
                      </tr>
                      <tr>
                        <td height="36" bordercolor="#666666"><div align="left">
                            <input type="radio" name="radio" id="radio4" value="radio" />
                        </div></td>
                      <td bordercolor="#666666"><div align="center" class="style37">
                            <p align="center">&lt;FirstName&gt; &lt;MiddleName&gt; &lt;LastName&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;Age&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;City&gt; &lt;State&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;Address&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><p align="center" class="style37">&lt;Phone&gt;</p></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;DOB&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;ValidityDate&gt;</p>
                        </div></td>
                      </tr>
                      <tr>
                        <td height="38" bordercolor="#666666"><div align="left">
                            <input type="radio" name="radio" id="radio5" value="radio" />
                        </div></td>
                      <td bordercolor="#666666"><div align="center" class="style37">
                            <p align="center">&lt;FirstName&gt; &lt;MiddleName&gt; &lt;LastName&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;Age&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;City&gt; &lt;State&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;Address&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><p align="center" class="style37">&lt;Phone&gt;</p></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;DOB&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;ValidityDate&gt;</p>
                        </div></td>
                      </tr>
                      <tr>
                        <td height="44" bordercolor="#666666"><div align="left">
                            <input type="radio" name="radio" id="radio6" value="radio" />
                        </div></td>
                      <td bordercolor="#666666"><div align="center" class="style37">
                            <p align="center">&lt;FirstName&gt; &lt;MiddleName&gt; &lt;LastName&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;Age&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;City&gt; &lt;State&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;Address&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><p align="center" class="style37">&lt;Phone&gt;</p></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;DOB&gt;</p>
                        </div></td>
                        <td bordercolor="#666666"><div align="center" class="style37">
                            <p>&lt;ValidityDate&gt;</p>
                        </div></td>
                      </tr>
                    </table></td>
                </tr>
                </table>
              <p>&nbsp;</p>
              <table width="869" border="0" align="center">
      <tr>
                    <td width="440" height="204"><div align="center">
                      <table width="387" align="center">
                        <tr>
                          <td><h4 align="left" class="style32">Need more information?</h4>
						  
						  
						  <% if(!member) { %>
                            <p align="left" class="style34">Get a <strong>Background Report</strong> on any one of these individuals for <strong>$29.95</strong>, or <strong>become a member for $29.95</strong> <strong>and get your first Background Report for free!</strong> </p>
                            <p align="left" class="style34">&nbsp;</p>
                            <p align="left" class="style34">Additional Background Reports are <strong>$19.95</strong> for members.</p>
					     <%	 } else { %>
						   <p align="left" class="style34">Get a <strong>Background Report</strong> on any one of these individuals  for <strong>$19.95.</strong></p>
						<%   }  %>
							
                            <p align="left" class="style34">&nbsp;</p>
                          <p align="left"><span class="style34"><strong>Background Report includes: </strong>Full name, last-known address with census data, address date, birthdate and age, phone numbers, address history, relatives &amp; roommates, business affiliations, website ownership, aircraft ownership, and more.</span></p></td>
                        </tr>
                      </table>
                      </div></td>
              <td width="443">
			  
			  <% if(!member) {  %>
			  <div align="right">
                <table width="409" border="0">
                  <tr>
                    <td height="41"><p align="left" class="style32 style31">To purchase a <strong>Background Report</strong> or <strong>Membership + Background Report</strong>, select one of the names above via the radio button, then click an option below:</p></td>
                      </tr>
                </table>
				<table width="407" border="0">
                  <tr>
                    <td height="58"><div align="left"><img src="/springapp/images/findpeople/bt-background-2995.png" onclick="document.forms[1].submit();" alt="2995 background" width="374" height="52" border="0"></div></td>
                      </tr>
                  <tr>
                    <td><div align="left"><img src="/springapp/images/findpeople/bt-become-member-2995-free-bg.png" onclick="Javascript:memReq();" alt="become member 2995" width="374" height="70" border="0"></div></td>
                            </tr>
                </table>
              </div>
			  <% } else { %>
			  
			  <div align="right">
                <table width="409" border="0">
                  <tr>
                    <td height="51"><p align="left" class="style32 style31">To purchase a <strong>Background Report</strong>, select one of the names above via the radio button, then click the button below:</p></td>
                      </tr>
                </table>
                <table width="407" border="0">
                  <tr>
                    <td height="58"><div align="left"><img src="/springapp/images/findpeople/bt-background-1995.png" onclick="document.forms[1].submit();" alt="background 1995" width="374" height="52" border="0"></div></td>
                      </tr>
                </table>
              </div>
			  
			  <% } %>
			  
			  
			  </td>
                </tr>
              </table>
              </form:form>
              </td>
          </tr>
        </table>
        
          <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="814" border="0" align="center" bgcolor="#FEF8A5">
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
			<p><a href="#">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php">About Us</a></p></td>
	</tr>
</table>

<map name="Map"><area shape="rect" coords="694,96,819,137" href="#" alt="find out more">
<area shape="rect" coords="818,96,821,97" href="#"></map></body>
</html>
