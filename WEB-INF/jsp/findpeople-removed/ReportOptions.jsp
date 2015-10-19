
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
.style93 {color: #FF0000}
.style95 {
	font-size: 10px;
	font-style: italic;
}
.style96 {color: #FFFFFF}
.style97 {
	color: #000000;
	font-weight: bold;
	font-style: italic;
	font-size: 14px;
}
.style98 {
	color: #000000;
	font-size: 11px;
}
.style99 {font-weight: bold; list-style-image: url(/springapp/images/findpeople/bullet-yellow-check-smaller.gif); color: #FF0000; }
-->
</style>
</head>
<body>
<table width="950" height="191%" align="center" style="height:100%">
<tr>
	
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
    	
  </tr>
	<tr>
		<td width="950" style="height:100%; padding: 10px 0;">
        <p>&nbsp;</p>
        <table width="893" border="0" align="center">
          <tr>
            <td width="645" height="596" class="content_1" style="padding:0 30px;"><table width="890" height="79" border="0" align="center">
              <tr>
                <td height="75"><h1 align="center" class="style80">Report Options</h1>
                    <h4 align="center" class="style59">Stop the guesswork - -  Reports include address dates.</h4></td>
              </tr>
            </table>
            
              <table width="782" border="0">
              <tr>
                <td width="354" height="465" bordercolor="#000000"><div align="center">
                    <table width="310" border="2" bordercolor="#003366">
                      <tr>
                        <td width="310" height="451"><table width="300" border="1" align="center" bgcolor="#FFFF99">
                            <tr>
                              <td width="336" height="65"><h2 align="center"><span class="style58">Find People Report</span></h2></td>
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
                          <table width="240" border="0" align="center">
                              <tr>
                                <td width="225" height="168"><ul class="style78">
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
                                </ul>                                    </td>
                            </tr>
                          </table>
                          <p>&nbsp;</p>
                          <p>&nbsp;</p>
                          <p>&nbsp;</p>
                          <table width="300" height="50" border="1" align="center" bgcolor="#FFFF99">
                          <tr>
                                <td width="350" height="44"><table width="287" border="0">
                                    <tr>
                                      <td width="181" height="38"><div align="center" class="style89">Regular Price $1.95</div><div align="center" style="font-size:smaller">(Member Price $1.00)</div></td>
                                      <td width="96"><a href="<c:url value="/findpeople/purchaseFPSearch.do?resultType=one"/>"><img src="/springapp/images/findpeople/bt-continue-small.png" alt="continue" width="94" height="38" border="0"></td>
                                  </tr>
                                </table></td>
                            </tr>
                          </table>
                                               </td>
                      </tr>
                    </table>
                </div></td>
                <td width="17">&nbsp;</td>
                <td width="305" bordercolor="#003366"><table width="310" border="2" bordercolor="#003366">
                  <tr>
                    <td width="310" height="452"><table width="300" border="1" align="center" bgcolor="#FFFF99">
                        <tr>
                          <td width="336" height="65"><h2 align="center"><span class="style58">Background Report</span></h2></td>
                        </tr>
                      </table>
                        <table width="273" border="0" align="center">
                          <tr>
                            <td height="52"><div align="left" class="style59">
                                <div align="left">
                                  <p>&nbsp;</p>
                                  <p>Includes Find People report information, <span class="style93">PLUS</span> (when available):</p>
                                </div>
                            </div></td>
                          </tr>
                        </table>
                        <table width="257" height="237" border="0" align="center">
                          <tr>
                            <td width="251" height="202" valign="top"><ul>
                                <li class="style78">
                                  <div align="left">
                                    <div align="left">Last known address w/census data</div>
                                  </div>
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
                                <p align="center" class="style93"><a href="<c:url value="/findpeople/showStaticPages.do?page=BGsamplereport"/>" target="_blank">Sample Report</a></p></td>
                          </tr>
                        </table>
                        <p>&nbsp;</p>
                        <table width="300" height="50" border="1" align="center" bgcolor="#FFFF99">
                          <tr>
                            <td width="350" height="44">
                            <form method="post" action="resultsFPRecords.do?resultType=BG">
                            <table width="287" border="0">
                                <tr>
                                  <td width="181" height="38"><div align="center" class="style89"><span class="style86 style87">Regular Price $19.95</span></div><div align="center" ><span style="font-size:smaller">(Member Price $14.95)</span></div></td>
                                  <td width="96"><input type="image" src="/springapp/images/findpeople/bt-continue-small.png" alt="continue" width="94" height="38" border="0"></td>
                                </tr>
                            </table>
                            </form>
                            </td>
                          </tr>
                        </table>
                      </td>
                  </tr>
                </table>                  </td>
                <td width="305" bordercolor="#003366">&nbsp;</td>
                <td width="386"><table width="314" border="2" bordercolor="#003366">
                    <tr>
                      <td width="305" height="453" bordercolor="#666666">
					  
					  <table width="300" border="1" align="center" bgcolor="#FFFF99">
                          <tr>
                            <td width="335" height="65"><h2 align="center"><span class="style58">Free Background Report<br/>
							<font style="font-size:13px"><strong>with Membership</strong></font></span></h2>
							</td>
                          </tr>
                        </table>
                        <table width="264" border="0" align="center">
                            <tr>
                              <td height="54"><div align="left" class="style59">
                                <p>&nbsp;</p>
                                <p>Become a <span class="style80">SearchSystems.net </span>member and receive:</p>
                              </div></td>
                            </tr>
                        </table>
                          <table width="281" align="center" height="236">
                          <tr>
                            <td><div align="center"><img src="/springapp/images/findpeople/report-options-content.png" alt="content" width="258" height="232"></div></td>
                          </tr>
                        </table>
                        <p>&nbsp;</p>
						
                        <table width="300" height="50" border="1" align="center" bgcolor="#FFFF99">
                            <tr>
                              <td width="289" height="44">
                              <form method="post" action="resultsFPRecords.do?resultType=BG&memberRequest=true">
                              <table width="287" border="0">
                                  <tr>
                                    <td width="181" height="38"><div align="center" class="style86 style87"> One Year $29.95</div></td>
                                    <td width="96"><input type="image" src="/springapp/images/findpeople/bt-continue-small.png" alt="continue" width="94" height="38" border="0"></td>
                                </tr>
                              </table>
                              </form>
                              </td>
                            </tr>
                        </table>
                                              </td>
                    </tr>
                </table></td>
              </tr>
            </table>
            
              <table width="890">
              <tr>
                <td><div align="center"><img src="/springapp/images/findpeople/site-nav-123-type.png" alt="type" width="584" height="39"></div></td>
              </tr>
            </table>
            </td>
          </tr>
        </table>
        <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p align="center">&nbsp;</p>
              
              
              <div align="center">
                <table width="816" border="0" align="center" bgcolor="#FEF8A5">
                  <tr>
                    <td width="895"><p align="left" class="style30">&quot;Your website is the search engine of the future... a source that provides relief from the usual frustrations of online searching. SearchSystems.net is so consistently dependable, it feels as if you're not on this planet. It's just perfect.&quot; - Rose</p>
                        <p align="left" class="style31">&nbsp;</p>
                      <p align="left" class="style30">&quot;Your staff is great. SearchSystems.net is a 5-star program on a basic budget.&quot; - Rick</p>
                      <p align="left" class="style31">&nbsp;</p>
                    <p align="left" class="style30">&quot;The one source I always mention in my presentations for private investigators and professional researchers.&quot;  - Tamara Thompson, P.I.</p></td>
                  </tr>
                  </table>
            </div>              <p align="center">&nbsp;</p></td>
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
