

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
<title>find people sample report</title>
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
.style27 {font-family: Arial, Helvetica, sans-serif}
.style30 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-style: italic;
}
.style31 {font-size: x-small}
.style23 {font-size: small}
.style33 {font-size: small; font-family: Arial, Helvetica, sans-serif; }
.style34 {font-family: Arial, Helvetica, sans-serif; color: #003366; }
.style35 {font-family: Arial, Helvetica, sans-serif; color: #FF0000; }
-->
</style>
</head>
<body>
<table width="893" height="191%" align="center" style="height:100%">
	<tr>
		<% if(!member) { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="/springapp/findpeople/searchLanding.do">Home Page</a> | <a href="/springapp/funnel/signup.do?previousPage=/findpeople/searchLanding.do">Sign Up</a> | <a href="http://www.searchsystems.net/login.php?signupFor=findpeople&calledPage=/springapp/findpeople/searchLanding.do">Log In</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
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
		<td width="893" style="height:100%; padding: 10px 0;">
        <p>&nbsp;</p>
        <table width="893" height="66" border="0" align="center">
          <tr>
            <td width="582" height="62"><h1 align="left" class="style34">Find People Details</h1></td>
            <td width="353"><h2 align="center" class="style35">(SAMPLE REPORT)</h2></td>
          </tr>
        </table>
        <table width="893" border="0" align="center">
          <tr>
            <td width="813" height="112"><table width="893" border="2" cellpadding="0" cellspacing="0" bordercolor="#999999" id="data">
<tr>
                  <td width="145" bordercolor="#666666"><div align="center" class="style27"><span class="style23"><strong>Name</strong></span></div></td>
                <td width="52" bordercolor="#666666"><div align="center" class="style27"><span class="style23"><strong>Age</strong></span></div></td>
                <td width="155" bordercolor="#666666"><div align="center" class="style27"><span class="style23"><strong>Location</strong></span></div></td>
                <td width="186" bordercolor="#666666"><div align="center" class="style27"><span class="style23"><strong>Address</strong></span></div></td>
                <td width="103" bordercolor="#666666"><div align="center" class="style27"><span class="style23"><strong>Phone</strong></span></div></td>
                <td width="118" bordercolor="#666666"><div align="center" class="style27"><span class="style23"><strong>Date of Birth</strong></span></div></td>
                <td width="118" bordercolor="#666666"><div align="center" class="style27"><span class="style23"><strong>Date Reported</strong></span></div></td>
              </tr>
                <tr>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">John Doe</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">38</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">New York, NY</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>100 W 21st Ave Apt E3</p>
                  </div></td>
                  <td bordercolor="#666666"><p align="center" class="style33">212-555-1010</p></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>03-04-1971</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>01/2006</p>
                  </div></td>
                </tr>
                <tr>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">John Q Doe</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">36</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">Portland, OR</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>55 Meadowgrove Ln</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center"><span class="style27"></span></div></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>11-10-1973</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>03/2008</p>
                  </div></td>
                </tr>
                <tr>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">John T Doe</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">41</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style27">
                      <p class="style23">Los Angeles, CA</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>101 Santa Monica Blvd</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center"><span class="style27"></span></div></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>02-02-1968</p>
                  </div></td>
                  <td bordercolor="#666666"><div align="center" class="style33">
                      <p>11/2007</p>
                  </div></td>
                </tr>
            </table></td>
          </tr>
        </table>
        <p>&nbsp;</p>
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
              <p align="center"><a href="${comodoUrl}"><img src="/springapp/images/findpeople/Comodo-seal-100.gif" width="100" height="60"></a></p>
            <p align="center"><a href="${bbbUrl}"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></p></td>
          </tr>
        </table>
        <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="816" border="0" align="center" bgcolor="#FEF8A5">
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
