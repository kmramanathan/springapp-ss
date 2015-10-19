

<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 
boolean member=false;
String username=(String)session.getAttribute("username");
if(username != null)
{
	member=true;
}

int total = (Integer)session.getAttribute("totalResult");

if(total == 0 ) { response.sendRedirect("/springapp/findpeople/searchLanding.do"); }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>report options</title>
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
.style26 {color: #FF0000; font-weight: bold; }
.style30 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-style: italic;
}
.style31 {font-size: x-small}
.style59 {	color: #000000;
	font-weight: bold;
}
.style80 {color: #003366}
.style58 {color: #003366; font-weight: bold; }
.style78 {	font-weight: bold;
	list-style-image: url(/springapp/images/common/bullet-yellow-check-smaller.gif);
}
.style86 {font-size: 0.8px; font-weight: bold; color: #003366; }
.style87 {	font-size: 16px
}
.style89 {	font-size: 16px;
	font-weight: bold;
	color: #003366;
}
.style91 {font-size: 16px; font-weight: bold; color: #000000; }
.style92 {font-size: small}
.style93 {color: #FF0000}
.style95 {	font-size: 10px;
	font-style: italic;
}
-->
</style>

<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>


</head>
<body>
<table width="950" height="191%" align="center" style="height:100%">
<tr>
		
    	<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="http://premium.searchsystems.net/contact.php">Contact Us</a> | Welcome <%=username%><p>&nbsp;</p> [<a href="/springapp/findpeople/logout.do">Log Out</a>] </div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>

  </tr>
	<tr>
		<td width="950" style="height:100%; padding: 10px 0;">
        <p>&nbsp;</p>
        <table width="862" border="0" align="center">
          <tr>
            <td width="645" height="567" class="content_1" style="padding:0 30px;"><table width="691" height="79" border="0">
              <tr>
                <td height="75"><h1 align="center" class="style80">Report Options</h1>
                    <h4 align="center" class="style59">Stop the guesswork - -  Reports include address dates.</h4></td>
              </tr>
            </table>
            <table width="687" border="0">
              <tr>
                <td width="318" height="422" bordercolor="#000000"><div align="center">
                    <table width="320" border="2" bordercolor="#003366">
                      <tr>
                        <td width="310" height="412"><table width="300" border="1" align="center" bgcolor="#FFFF99">
                            <tr>
                              <td width="336" height="56"><h2 align="center"><span class="style58">Find People Report</span></h2></td>
                            </tr>
                          </table>
                    <table width="273" border="0" align="center">
                              <tr>
                                <td height="52"><div align="left" class="style59">
                                    <div align="left">
                                      <p>&nbsp;</p>
                                      <p>We found <span class="style26"><%=total%> matches.</span> Your report includes (when available):</p>
                                  </div>
                                </div></td>
                              </tr>
                          </table>
                          <table width="241" border="0" align="center">
                          <tr>
                                <td width="235" height="161"><ul class="style78">
                                    <li class="style78">
                                      <div align="left">Address</div>
                                    </li>
                                    <li class="style78">
                                      <div align="left"><em>Address date!</em></div>
                                    </li>
                                    
                                    <li class="style78">
                                      <div align="left">Date of birth</div>
                                    </li>
                                    <li>
                                      <div align="left">Phone number</div>
                                    </li>
                                    </ul>
                                <p align="center">&nbsp;</p></td>
                            </tr>
                          </table>
                          <p>&nbsp;</p>
                          <p>&nbsp;</p>
                          <table width="300" height="62" border="1" align="center" bgcolor="#A3C6FF">
                        <tr>
                                <td width="291" height="56"><table width="287" border="0">
                                    <tr>
                                      <td width="181"><div align="center" class="style91">Member Price $1.00</div></td>
                                      <td width="96"><a href="<c:url value="/findpeople/purchaseFPSearch.do?resultType=one"/>"><img src="/springapp/images/findpeople/bt-continue-small.png" alt="continue" width="94" height="38" border="0"></a></td>
                                  </tr>
                                </table></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                    </table>
                </div></td>
                <td width="51">&nbsp;</td>
                <td width="321"><table width="320" border="2" bordercolor="#003366">
                    <tr>
                      <td width="305" height="411" bordercolor="#666666"><table width="300" border="1" align="center" bgcolor="#FFFF99">
                          <tr>
                            <td width="335" height="56"><h2 align="center"><span class="style58">Background Report</span></h2></td>
                          </tr>
                        </table>
              <table width="283" border="0" align="center">
                            <tr>
                              <td height="54"><div align="left" class="style59">
                                <p>&nbsp;</p>
                                <p>Includes Find People report information, <span class="style93">PLUS</span> (when available):</p>
                              </div></td>
                            </tr>
                          </table>
                        <table width="281" border="0" align="center">
                            <tr>
                              <td width="295" height="192" valign="top"><ul>
                                  <li class="style78">
                                    <div align="left">Last known address with census data</div>
                                  </li>
                                  
                                <li class="style78">
                                    <div align="left">Address history</div>
                                </li>
                                <li class="style78">
                                    <div align="left">Relatives &amp; roommates</div>
                                </li>
                                <li class="style78">
                                <div align="left">Statewide criminal records</div>
                              </li>
                              <li class="style78">
                                <div align="left">Bing Virtual Earth Map</div>
                              </li>
                                <li class="style78">
                                    <div align="left">Business affiliations</div>
                                </li>
                                <li class="style78">
                                    <div align="left">Website ownership</div>
                                </li>
                                <li class="style78">
                                    <div align="left">Concealed handgun permits</div>
                                </li>
                                <li class="style78">
                                    <div align="left">Aircraft ownership</div>
                                </li>
                                <li class="style78">
                                    <div align="left">And more...</div>
                                </li>
                              </ul>
                                  <p align="center"><a href="<c:url value="/findpeople/showStaticPages.do?page=BGsamplereport"/>" target="_blank">Sample Report</a></p></td>
                          </tr>
                          </table>
                        <table width="300" height="64" border="1" align="center" bgcolor="#A3C6FF">
            <tr>
                              <td width="289" height="58">
							  <form method="post" action="resultsFPRecords.do?resultType=BG">
							  <table width="287" border="0">
                                  <tr>
                                    <td width="179" height="44"><div align="center"> <span class="style91">Member Price $14.95</span></div></td>
                                    <td width="98"><input type="image" src="/springapp/images/findpeople/bt-continue-small.png" alt="continue" width="94" height="38" border="0"></td>
                                </tr>
                              </table>
							  </form>
							  </td>
                            </tr>
                          </table>                      </td>
                    </tr>
                </table></td>
              </tr>
            </table>
            <p align="center"><img src="/springapp/images/findpeople/site-nav-123-type.png" alt="type" width="584" height="39"></p></td>
            <td width="185" class="content_1" style="padding:0 30px;">
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center">&nbsp;</p>
              <p align="center"><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script></p>
              <p align="center"><a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb_seal_100_wide.jpg" alt="bbb" width="100" height="162"></a></p>
            </td>
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
		<td width="950" class="footer" style="height:56px; background:url(/springapp/images/findpeople/bg_footer_11.jpg) top no-repeat; padding:15px 0 0 60px;">
		  <p>Copyright &copy; 2009 Pacific Information Resources, Inc. All rights reserved.</p>
			<p><a href="http://www.searchsystems.net/tos.php" target="_blank">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php" target="_blank">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php" target="_blank">About Us</a></p></td>
  </tr>
</table>
</body>
</html>
