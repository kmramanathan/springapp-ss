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
<title>background report sample</title>
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
.style23 {font-size: small}
.style32 {font-family: Arial, Helvetica, sans-serif; color: #003366; }
.style29 {font-size: large; font-weight: bold; }
.style50 {font-family: Arial, Helvetica, sans-serif; font-size: medium; font-weight: bold; }
.style51 {font-size: medium}
.style52 {font-family: Arial, Helvetica, sans-serif; font-size: medium; }
.style53 {	font-size: medium;
	font-weight: bold;
}
.style55 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-weight: bold;
}
.style56 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
.style57 {color: #8399B1}
.style60 {color: #000000; font-size: x-small; font-family: Arial, Helvetica, sans-serif; }
.style61 {color: #000000}
.style62 {font-family: Arial, Helvetica, sans-serif; color: #FF0000; }
.style66 {font-family: Arial, Helvetica, sans-serif; color: #003366; font-weight: bold; }
.style45 {font-size: small; font-family: Arial, Helvetica, sans-serif; }
.style65 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }
.style70 {font-family: Arial, Helvetica, sans-serif; font-size: small; font-style: italic; }
.style44 {	font-family: Arial, Helvetica, sans-serif;
	font-size: small;
	font-weight: bold;
}
.style41 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-weight: bold;
}
.style42 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
.style45 {font-size: small; font-family: Arial, Helvetica, sans-serif; }
.style47 {font-size: small; font-weight: bold; }
.style71 {
	font-size: 14px
}
.style72 {font-family: Arial, Helvetica, sans-serif; font-size: 14px; font-weight: bold; }
.style90 {
	font-size: medium;
	color: #003366;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}



-->
</style>
</head>
<body>
<table width="893" height="191%" align="center" style="height:100%">
	<tr>
		<% if(!member) { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="/springapp/funnel/signup.do?previousPage=/findpeople/searchLanding.do">Sign Up</a> | <a href="http://www.searchsystems.net/login.php?calledPage=/findpeople/searchLanding.do">Log In</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
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
		<table width="893" height="714" border="0">
          <tr>
            <td width="896" height="710" valign="top">
           <table width="600" height="70" border="0">
                  <tr>
                    <td width="600" height="66">

			<h1 class="style32">Sample Background Reports</h1>
			<p class="style90" style="padding-bottom: 10px">As of July 2, 2009</p>
			<p>&nbsp;</p>
			<table width="891" border="0">

                <tr>
                  <td width="870" height="143"><p><span class="style70" style="padding-bottom: 10px"><strong>Important:</strong> This Background Report  contains information gathered from public records sources.  The  information is only as accurate as the agencies that  contribute it.</span></p>
                    <p>&nbsp;</p>
                    <p><span class="style70" style="padding-bottom: 10px">Address history and address information is matched by name and birth date (when available). Statewide criminal records are matched by name and birth date and we include results where the date of birth is unavailable. We also provide additional data (such as Business Search, Campaign Contributions, U.S. Domain Name Ownership, etc.) that isn't searchable by birth date. Therefore we provide you with <strong>all</strong> available records that match the name you searched. These records may or may not belong to your subject, so please review the information carefully.</span></p>
                  <p>&nbsp;</p></td>

                </tr>
              </table>
			
			
			</td>
                    
                
                   </tr>
                </table>

                
                <table width="893" border="0">
                  <tr>
                    <td><table width="502" height="146" cellpadding="0" cellspacing="0">
                      <col width="117" />
                        <col width="205" />
                        <col width="59" />
                        <col width="99" />
                        <col width="85" />
                        <col width="64" />
                        <tr height="51">
                          <td width="291" height="30"><div align="left" class="style27">
                            <div align="left"><span class="style29">STEVE L SMITH</span></div>
                          </div></td>
                      </tr>
                        <tr height="17">
                          <td height="17"><div align="left" class="style50">
                            <div align="left"><strong>442 GREENRIVER RD</strong></div>
                          </div></td>
                        </tr>
                        <tr height="17">
                          <td height="17"><div align="left" class="style50">MINEOLA NY 11501-2116</div></td>
                        </tr>
                        <tr height="17">
                          <td height="17"><div align="left" class="style50"><strong>212-555-0123</strong></div></td>
                        </tr>
                        <tr height="17">
                          <td height="17"><div align="left"><span class="style27"><span class="style51"></span></span></div></td>
                        </tr>
                        <tr height="17">
                          <td height="17"><div align="left" class="style52"><strong>Validity Date:</strong><strong> 01/2006</strong></div></td>
                        </tr>
                        <tr height="17">
                          <td height="17"><div align="left" class="style52"><strong>DOB:</strong><strong> 7/07/1957</strong></div></td>
                        </tr>
                    </table>
                    <p>&nbsp;</p>
                      <table width="501" height="159">
                        <tr>
                          <td width="256" height="153"><p class="style65">Summary:</p>
                              <p>&nbsp;</p>
                            <p class="style45">Address History (4)</p>
                            <p class="style45">Address Information (17)</p>
                            <p class="style45">Statewide Criminal Records (2)</p>
                            <p class="style45">Business Search (2)</p>
                            <p class="style45">Campaign Contributions (2)</p>
                            <p class="style45">U.S. Domain Name Ownership (2)</p>
                            <p class="style45">FCC Licenses (2)</p></td>
                            
                          <td width="233"><p>&nbsp;</p>
                              <p class="style27">&nbsp;</p>
                            <p class="style45">Firearm Dealer Licenses (2)</p>
                            <p class="style45">Concealed Weapons Permits (2)</p>
                            <p class="style45">DEA Search (2)</p>
                            <p class="style45">Merchant Vessels (2)</p>
                            <p class="style45">Aircraft Search (2)</p>
                            <p class="style45">FAA Pilot Licenses (2)</p>
                            </td>
                        </tr>
                      </table>
                    <p>&nbsp;</p></td>
                    <td><p><img src="/springapp/images/findpeople/sample-map.png" alt="sample map" width="378" height="259"></p>
                    <p style="padding-top: 10px"><span class="style70"><strong>Sample Map.</strong>  View the
location  by map view, satellite, or 'Bird's Eye' aerial photography to
view the house/apartment and neighborhood.</span></p>
</td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>Address History</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="117" />
                  <col width="205" />
                  <col width="59" />
                  <col width="99" />
                  <col width="85" />
                  <col width="64" />
                  <tr height="17">
                    <td width="314" height="17"><div align="left" class="style55">
                      <div align="left">442 GREENRIVER RD</div>
                    </div></td>
                    <td width="103"><div align="left" class="style56">
                      <div align="left">County:</div>
                    </div></td>
                    <td width="171"><div align="left" class="style56">
                      <div align="left">NASSAU</div>
                    </div></td>
                    <td width="151"><div align="left" class="style56">
                      <div align="left">Validity Date:</div>
                    </div></td>
                    <td width="135"><div align="left" class="style56">
                      <div align="left">01/2006</div>
                    </div></td>
                    <td width="17">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17"><div align="left" class="style56"><strong>MINEOLA NY 11501-2116</strong></div></td>
                    <td><div align="left" class="style56">Phone:</div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr height="18">
                    <td height="18"><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style55">522 BLAKE ST</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">County:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">WINNEBAGO</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">06/2005</div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style55">OSHKOSH WI 54901</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Phone:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17"><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17"><div align="left" class="style55">77 W 19TH AVE</div></td>
                    <td><div align="left" class="style56">County:</div></td>
                    <td><div align="left" class="style56">WINNEBAGO</div></td>
                    <td><div align="left" class="style56">Validity Date:</div></td>
                    <td><div align="left" class="style56">02/2002</div></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17"><div align="left" class="style55">OSHKOSH WI 54902-6906</div></td>
                    <td><div align="left" class="style56">Phone:</div></td>
                    <td><div align="left" class="style56">920-555-0123</div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17"><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style55">1010 LUMBER TRL APT B</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">County:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">WINNEBAGO</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">11/1996</div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style55">OSHKOSH WI 54904-8063</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Phone:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">414-555-0123</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="640"><div align="center"></div></td>
                  </tr>
                </table>
<table width="193" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="40" width="189"><h4 align="left" class="style32"><strong>Address    Information</strong></h4></td>
              </tr>
                </table>
            <table width="891">
              <tr>
                <td width="235"><table width="236">
                    <tr>
                      <td width="228">&nbsp;</td>
                    </tr>
                    <tr height="17">
                      <td height="17"><div align="left" class="style56"><strong>442 GREENRIVER RD</strong></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17"><div align="left" class="style56"><strong>MINEOLA NY 11501-2116</strong></div></td>
                    </tr>
                    
                    <tr height="17">
                      <td height="17"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17"><div align="left" class="style56"></div></td>
                    </tr>


                  </table>
                    <p>&nbsp;</p>
                  <p>&nbsp;</p></td>
                <td width="399"><table width="398">
                    <tr>
                      <td width="217"><span class="style56"><strong>Relatives/Roommates:</strong></span></td>
                      <td width="82">&nbsp;</td>
                      <td width="103">&nbsp;</td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">RICHARD M FALK</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left"><span class="style56">10/1998</span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">(relationship unknown)</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="16">
                      <td><div align="left" class="style56">RODNEY S RAMMSTEIN</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left" class="style56">01/2006</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">(relationship unknown)</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left" class="style56">12/20/1972</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>
                    <tr height="18">
                      <td><div align="left" class="style56">JAMES E SMITH</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left" class="style56">02/2002</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">RELATIVE</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left" class="style56"> 03/17/1922</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">BRUCE SMITH</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left" class="style56">06/2001</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">RELATIVE</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>

                    <tr height="17">
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">DEBRA SMITH</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left" class="style56">06/2005</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">RELATIVE</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left" class="style56">09/10/1967</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>


                </table></td>
                <td width="241"><table width="239">
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Census Data</div></td>
                      <td bgcolor="#B9DCFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">19111</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Households per zip:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">7637</div></td>
                    </tr>
                    <tr height="16">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">White population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">16508</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Black population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">200</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Hispanic Population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">2483</div></td>
                    </tr>
                    <tr height="18">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Persons per house:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">3</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Average house value:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">241400</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Income per household:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">60595</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Lat:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">40.746806</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Long:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">-73.639751</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Elevation:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">107</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">State:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">NY</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">City type:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">P</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Area code:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">516</div></td>
                    </tr>
                    
                </table></td>
              </tr>
            </table>
            <table width="891">
              <tr>
                <td width="235"><table width="236">
                    <tr>
                      <td width="228" bgcolor="#fffeb1">&nbsp;</td>
                    </tr>
                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left" class="style56"><strong>52 BLAKE ST</strong></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left" class="style56"><strong>OSHKOSH, WI 54901</strong></div></td>
                    </tr>
                    
                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left" class="style56"></div></td>
                    </tr>

                  </table>
                    <p>&nbsp;</p>
                  <p>&nbsp;</p></td>
                <td width="399"><table width="398">
                    <tr>
                      <td width="217" bgcolor="#fffeb1"><span class="style56"><strong>Relatives/Roommates:</strong></span></td>
                      <td width="82" bgcolor="#fffeb1">&nbsp;</td>
                      <td width="103" bgcolor="#fffeb1">&nbsp;</td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">PAUL J COMBES</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">07/1998</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">(relationship unknown)</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DOB:</div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="19">
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">RANDALL J WATTS</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">06/2001</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">(relationship unknown)</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DOB:</div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">MARIE WATTS</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">06/1998</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">(relationship unknown)</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DOB:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">01/13/1934</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DEBRA SMITH</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">06/2005</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">RELATIVE</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DOB:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">09/10/1967</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">JAMES E SMITH</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">06/2005</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">RELATIVE</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DOB:</div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>

                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>

                </table></td>
                <td width="241"><table width="239">
                    <tr height="17">
                      <td width="134" bgcolor="#B9DCFF"><div align="left" class="style56">Census Data</div></td>
                      <td width="93" bgcolor="#B9DCFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">37010</div></td>
                    </tr>
                    <tr height="19">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Households per zip:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">13728</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">White population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">33632</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Black population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">1181</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Hispanic Population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">652</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Persons per house:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">3</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Average house value:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">83800</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Income per household:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">36324</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Lat:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">44.018004</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Long:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">-88.538444</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Elevation:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">743</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">State:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">WI </div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">City type:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">P</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Area code:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">920</div></td>
                    </tr>
                    
                </table></td>
              </tr>
            </table>
            <table width="891">
              <tr>
                <td width="235"><table width="236">
                    <tr>
                      <td width="228">&nbsp;</td>
                    </tr>
                    <tr height="17">
                      <td height="17"><div align="left" class="style56"><strong>77 W 19TH AVE</strong></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17"><div align="left" class="style56"><strong>OSHKOSH WI 54902-6906</strong></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17"><div align="left" class="style56">920-555-0123</div></td>
                    </tr>

                    <tr height="17">
                      <td height="17"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                  </table>
                    <p>&nbsp;</p>
                  <p>&nbsp;</p></td>
                <td width="399"><table width="398">
                    <tr>
                      <td width="217"><span class="style56"><strong>Relatives/Roommates:</strong></span></td>
                      <td width="82">&nbsp;</td>
                      <td width="103">&nbsp;</td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">DONALD C BROWER</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left" class="style56">09/1996</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">(relationship unknown)</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">KIM B BROWER</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left" class="style56">07/1995</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">(relationship unknown)</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left" class="style56">05/30/1978</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">DEBRA SMITH</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left" class="style56">02/2002</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">RELATIVE</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left" class="style56">09/10/1967</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">JAMES E SMITH</div></td>
                      <td><div align="left" class="style56">Validity Date:</div></td>
                      <td><div align="left" class="style56">05/1999</div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left" class="style56">RELATIVE</div></td>
                      <td><div align="left" class="style56">DOB:</div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>

                    <tr height="17">
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>

                </table></td>
                <td width="241"><table width="239">
                    <tr height="17">
                      <td width="133" bgcolor="#B9DCFF"><div align="left" class="style56">Census Data</div></td>
                      <td width="94" bgcolor="#B9DCFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">22611</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Households per zip:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">10431</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">White population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">21642</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Black population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">127</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Hispanic Population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">352</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Persons per house:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">3</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Average house value:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">84400</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Income per household:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">38563</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Lat:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">43.996871</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Long:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">-88.669133</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Elevation:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">743</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">State:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">WI </div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">City type:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">P</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Area code:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">920</div></td>
                    </tr>
                    
                </table></td>
              </tr>
            </table>
            <table width="891">
              <tr>
                <td width="235"><table width="236">
                    <tr>
                      <td width="228" bgcolor="#fffeb1">&nbsp;</td>
                    </tr>
                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left" class="style56"><strong>1010 LUMBER TRL APT B</strong></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left" class="style56"><strong>OSHKOSH WI 54904-8063</strong></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">414-555-3210</div></td>
                    </tr>

                    <tr height="17">
                      <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>

                  </table>
                    <p>&nbsp;</p>
                  <p>&nbsp;</p></td>
                <td width="399"><table width="398">
                    <tr>
                      <td width="217" bgcolor="#fffeb1"><span class="style56"><strong>Relatives/Roommates:</strong>&lt;otherPeople&gt;</span></td>
                      <td width="82" bgcolor="#fffeb1">&nbsp;</td>
                      <td width="103" bgcolor="#fffeb1">&nbsp;</td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">JAMES E SMITH</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">11/1996</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">RELATIVE</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DOB:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">03/17/1922</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DEBRA  SMITH</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">11/1996</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">RELATIVE</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DOB:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">09/10/1967</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">BRUCE SMITH</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">Validity Date:</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">06/2001</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left" class="style56">RELATIVE</div></td>
                      <td bgcolor="#fffeb1"><div align="left" class="style56">DOB:</div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>

                    <tr height="17">
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>

                </table></td>
                <td width="241"><table width="239">
                    <tr height="17">
                      <td width="135" bgcolor="#B9DCFF"><div align="left" class="style56">Census Data</div></td>
                      <td width="92" bgcolor="#B9DCFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">17963</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Households per zip:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">7096</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">White population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">17489</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Black population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">45</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Hispanic Population:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">146</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Persons per house:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">3</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Average house value:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">136200</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Income per household:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">54615</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Lat:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">44.025563</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Long:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">-88.617262</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Elevation:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">743</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">State:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">WI </div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">City type:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">P</div></td>
                    </tr>
                    <tr height="17">
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">Area code:</div></td>
                      <td bgcolor="#B9DCFF"><div align="left" class="style56">920</div></td>
                    </tr>
                    
                </table></td>
              </tr>
            </table>
            <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="640"><div align="center"></div></td>
                  </tr>
                </table>
                
                <!--  insert here -->
                <h4 align="left" class="style32"><strong>Statewide Criminal Records</strong></h4>
                <table width="891">
                  <tr>
                    <td height="215"><h4 class="style27">Offender Info</h4>
                      <table width="883" border="0">
                        <tr>
                          <td width="367" bgcolor="#FFFFFF"><p class="style41">Full Name</p>
                          <p class="style42">SMITH, STEVE L</p></td>
                          <td width="195" bgcolor="#FFFFFF"><p class="style41">DOB</p>
                          <p class="style42">1957-07-07</p></td>
                          <td width="178" bgcolor="#FFFFFF"><p class="style41">ID</p>
                          <p class="style42">1515318</p>                          </td>
                          <td width="115" bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><p class="style41">Address</p>
                          <p class="style42">442 GREENRIVER</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">City</p>
                          <p class="style42">ANYTOWN</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">State</p>
                          <p class="style42">NY</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Postal Code</p>
                          <p class="style42">11501</p></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><p class="style41">Gender</p>
                          <p class="style42">MALE</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Race</p>
                          <p class="style42">CAUCASIAN</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">County</p>
                          <p class="style42">NASSAU</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Country</p>
                          <p class="style42">USA</p></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><p class="style41">Height</p>
                          <p class="style42">6'00''</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Weight</p>
                          <p class="style42">185</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Eye Color</p>
                          <p class="style42">BROWN</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Hair Color</p>
                          <p class="style42">BROWN</p></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><p class="style41">Record Source</p>
                          <p class="style42">Nassau County Superior Court</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Record State</p>
                          <p class="style42">NY</p></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                        </tr>
                    </table>                      </td>
                  </tr>
                  <tr>
                    <td><h4 class="style27">Aliases</h4>
                      <table width="491" border="0">
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42">No aliases found.</span></div></td>
                        </tr>
                      </table>
                    <p>&nbsp;</p></td>
                  </tr>
                  <tr>
                    <td><h4 class="style27">Offenses</h4>
                      <p class="style41 style71">Result #1</p>
                      <table width="554" border="0">
                        <tr>
                          <td width="127" bgcolor="#FFFFFF"><div align="left" class="style41">Description</div></td>
                          <td width="417" bgcolor="#FFFFFF"><div align="left"> <span class="style42">AGGRAVATED ASSAULT</span> </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Degree of Offense</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"> <span class="style42">CRIMINAL FELONY</span> </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Arresting Agency</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"> <span class="style42">NASSAU COUNTY SHERIFF</span> </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Disposition</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"> <span class="style42">CONVICTED</span> </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Disposition Date</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"> <span class="style42">03-31-2002</span> </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Arrest Date</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"> <span class="style42">11-15-2001</span> </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Sentence</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style42">2 YEARS</span></div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Sentence Date</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style42">03-31-2002</span></div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Confinement</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style42">YES</span></div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Probation</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style42">1 YEAR</span></div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Offense Date</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style42">11-15-2001</span></div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Fine</div></td>
                          <td bgcolor="#FFFFFF"><div align="left">
                              <p><span class="style42">$508.00</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Statute</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style42">245 (a) (1)</span></div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Original Plea</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"> <span class="style42">NOT GUILTY</span> </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><div align="left" class="style41">Case Number</div></td>
                          <td bgcolor="#FFFFFF"><div align="left"> <span class="style42">1515318</span> </div></td>
                        </tr>
                      </table>
                      <p>&nbsp;</p>
                      <p class="style72">Result #2</p>
                      <table width="560" border="0">
                        <tr>
                          <td width="129" bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Description</strong></div>
                          </div></td>
                          <td width="421" bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">ATTEMPTED RAPE</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Degree of Offense</strong></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">CRIMINAL FELONY</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Arresting Agency</strong></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">NASSAU COUNTY SHERIFF</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Disposition</strong></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">CONVICTED</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Disposition Date</strong></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">03-31-2002</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Arrest Date</strong></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">11-15-2001</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><span class="style41">Sentence</span></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">5 YEARS</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><span class="style41">Sentence Date</span></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">03-31-2002</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><span class="style41">Confinement</span></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">YES</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><span class="style41">Probation</span></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">NO</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="left" class="style41">Offense Date</div></td>
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42">11-15-2001</span></div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="left" class="style41">Fine</div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">$508.00</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Statute</strong></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">PC-264.1</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Original Plea</strong></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">NOT GUILTY</span></p>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="right" class="style42">
                              <div align="left"><strong>Case Number</strong></div>
                          </div></td>
                          <td bgcolor="#FFFEB1"><div align="left">
                              <p><span class="style42">1515318</span></p>
                          </div></td>
                        </tr>
                      </table></td>
                  </tr>
                </table>
                <p><hr>
        <table width="891" height="400">
                  <tr>
                    <td height="215"><h4 class="style27">Offender Info</h4>
                        <table width="883" border="0">
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Full Name</p>
                                <p class="style42">SMITH, STEVE L</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">DOB</p>
                                <p class="style42">1957-07-07</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">ID</p>
                                <p class="style42">1515318</p></td>
                            <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Address</p>
                                <p class="style42">442 GREENRIVER</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">City</p>
                                <p class="style42">ANYTOWN</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">State</p>
                                <p class="style42">NY</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Postal Code</p>
                                <p class="style42">11501</p></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Gender</p>
                                <p class="style42">MALE</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Race</p>
                                <p class="style42">CAUCASIAN</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">County</p>
                                <p class="style42">NASSAU</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Country</p>
                                <p class="style42">USA</p></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Height</p>
                                <p class="style42">6'00''</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Weight</p>
                                <p class="style42">185</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Eye Color</p>
                                <p class="style42">BROWN</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Hair Color</p>
                                <p class="style42">BROWN</p></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Record Source</p>
                                <p class="style42">Nassau County Superior Court</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Record State</p>
                                <p class="style42">NY</p></td>
                            <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                            <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                          </tr>
                      </table></td>
                  </tr>
                  <tr>
                    <td><h4 class="style27">Aliases</h4>
                        <table width="491" border="0">
                          <tr>
                            <td bgcolor="#FFFEB1"><span class="style42">No aliases found.</span></td>
                          </tr>
                        </table>
                    <p>&nbsp;</p></td>
                  </tr>
                  <tr>
                    <td height="300"><h4 class="style27">Offenses</h4>
                        <p class="style72">Result #1</p>
                        <table width="549" border="0">
                          <tr>
                            <td width="130" bgcolor="#FFFFFF"><div align="right" class="style42">
                                <div align="left"><strong>Description</strong></div>
                            </div></td>
                            <td width="409" bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">EXPIRED PLATES - PRIVATE</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="right" class="style42">
                                <div align="left"><strong>Degree of Offense</strong></div>
                            </div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">WAIVER</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="right" class="style42">
                                <div align="left"><strong>Arresting Agency</strong></div>
                            </div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42"> MINNEOLA POLICE DEPARTMENT</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="right" class="style42">
                                <div align="left"><strong>Disposition</strong></div>
                            </div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">WG - WAIVER GUILTY</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="right" class="style42">
                                <div align="left"><strong>Disposition Date</strong></div>
                            </div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">1997-08-27</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="right" class="style42">
                                <div align="left"><strong>Arrest Date</strong></div>
                            </div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p>&nbsp;</p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Sentence</div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p>&nbsp;</p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Sentence Date</div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p>&nbsp;</p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Confinement</div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p>&nbsp;</p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Probation</div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">N/A</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Offense Date</div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">1997-08-27</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Fine</div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">$38.00</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Statute</div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">335.09E1</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="right" class="style42">
                                <div align="left"><strong>Original Plea</strong></div>
                            </div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">WAIVER GUILTY</span></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="right" class="style42">
                                <div align="left"><strong>Case Number</strong></div>
                            </div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style42">97TRD09224-A</span></p>
                            </div></td>
                          </tr>
                        </table>
                        <p>&nbsp;</p>
                    <p><span class="style27">To see the data sources and coverage for our statewide criminal records search, <a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do" target="_blank">click here</a>.</span></p>
                    </td>
              </tr>
                     </table>   
        
                <!--  -->
                
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="944"><div align="center"></div></td>
                  </tr>
                </table>
				
                <h4 align="left" class="style32"><strong>Business Search</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="116" />
                  <col width="106" />
                  <col width="220" />
                  <col width="115" />
                  <col width="131" />
                  <tr height="17">
                    <td width="101" height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Company:</div></td>
                    <td width="247" bgcolor="#FFFFFF"><div align="left" class="style56"> DELI-SMITH</div></td>
                    <td width="99" bgcolor="#FFFFFF"><div align="left" class="style56">Phone:</div></td>
                    <td width="162" bgcolor="#FFFFFF"><div align="left" class="style56">212-555-0022</div></td>
                    <td width="113" bgcolor="#FFFFFF"><div align="left" class="style56">Square Footage:</div></td>
                    <td width="169" bgcolor="#FFFFFF"><div align="left" class="style56">0 to 2499</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">302 MAIN ST</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Fax:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Credit Score:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">A</div></td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">MINEOLA, NY 11501</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">County:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56"> NASSAU</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Credit Score:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">94</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Title:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">OWNER</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Annual Sales:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Less than $500,000</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">SIC Code:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">581208</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Owner Name:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">STEVE SMITH</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56"># of Employees:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">1 to 4</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Description:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">RESTAURANTS</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Company:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">LIQUORSMITH &amp; SPIRITS</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Phone:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">212-555-0021</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Square Footage:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">0 to 2499</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">310 MAIN ST</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Fax:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Credit Score:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">B+</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">MINEOLA, NY 11501</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">County:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">NASSAU</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Credit Score:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">87</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Title:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">OWNER</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Annual Sales:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">$500,000 to 1 Million</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">SIC Code:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">892102</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Owner Name:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">STEVE L SMITH</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56"># of Employees:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">1 to 4</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Description:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">LIQUORS-RETAIL</div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="945"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>Campaign Contributions</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="51" />
                  <col width="158" />
                  <col width="116" />
                  <col width="140" span="3" />
                  <tr height="17">
                    <td width="203" height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Steve L Smith</div></td>
                    <td width="143" bgcolor="#FFFFFF"><div align="left" class="style56">Occupation:</div></td>
                    <td width="178" bgcolor="#FFFFFF"><div align="left" class="style56">Entrepreneur</div></td>
                    <td width="123" bgcolor="#FFFFFF"><div align="left" class="style56">Candidate:</div></td>
                    <td width="214" bgcolor="#FFFFFF"><div align="left" class="style56">Bush</div></td>
                    <td width="30" bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">302 Main St.</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Contribution Date:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">2/16/1999</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Term:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">2000</div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="#FFFFFF"><div align="left" class="style56">Mineola, NY 11501</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Steve Smith</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Occupation:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Entrepreneur</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Candidate:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">McCain</div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="18">
                    <td height="18" bgcolor="#fffeb1"><div align="left" class="style56">442 Greenriver Road</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Contribution Date:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">8/2/2008</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Term:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">2008</div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Mineola, NY 11501</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="945"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>U.S. Domain Name Ownership</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="73" />
                  <col width="101" />
                  <col width="177" />
                  <col width="64" />
                  <col width="149" />
                  <tr height="17">
                    <td width="113" height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Domain Name:</div></td>
                    <td width="325" bgcolor="#FFFFFF"><div align="left" class="style56"><a href="http://www.domain.net/">www.liquorsmith.net</a></div></td>
                    <td width="87" bgcolor="#FFFFFF"><div align="left" class="style56">Phone:</div></td>
                    <td width="309" bgcolor="#FFFFFF"><div align="left" class="style56">212-555-0123</div></td>
                    <td width="57" bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Owner Name:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">STEVE L. SMITH</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Fax:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">212-555-0124</div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="#FFFFFF"><div align="left" class="style56">Company:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">SMITH DOMAINS</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Email:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56"><a href="mailto:jsmith@domain.net">jsmith@domain.net</a></div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">302 MAIN ST</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">
                        <div align="left">MINEOLA, NY 11051</div>
                    </div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="18">
                    <td height="18" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Domain Name:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56"><a href="http://www.bigsample.com/">www.bigsample.com</a></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Phone:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">212-555-1111</div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Owner Name:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">STEVE SMITH</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Fax:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">212-555-1010</div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Company:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">BIG SAMPLE CO.</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Email:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56"><a href="mailto:jason@bigsample.com">jason@bigsample.com</a></div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">310 MAIN ST</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="944"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>FCC Licenses</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="64" />
                  <col width="195" />
                  <col width="97" />
                  <col width="78" />
                  <tr height="17">
                    <td width="112" bgcolor="#FFFFFF"><div align="left" class="style56">Name:</div></td>
                    <td width="329" bgcolor="#FFFFFF"><div align="left" class="style56">STEVE SMITH</div></td>
                    <td width="144" bgcolor="#FFFFFF"><div align="left" class="style56">Callsign:</div></td>
                    <td width="306" bgcolor="#FFFFFF"><div align="left" class="style56">AB0A</div></td>
                  </tr>
                  <tr height="17">
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">310 MAIN ST</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">FRN Number:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">2224149</div></td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="#FFFFFF"><span class="style31"></span></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><span class="style31"></span></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Name:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">DEBRA SMITH</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Callsign:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">AB0BA</div></td>
                  </tr>
                  <tr height="18">
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">310 MAIN ST</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">FRN Number:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">2498431</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><span class="style31"></span></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="733"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>Firearm Dealer Licenses</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="74" />
                  <col width="84" />
                  <col width="197" />
                  <col width="108" />
                  <col width="195" />
                  <tr height="17">
                    <td width="73" height="17" bgcolor="#FFFFFF"><div align="left" class="style60">Name:</div></td>
                    <td width="288" bgcolor="#FFFFFF"><div align="left" class="style60">STEVE SMITH</div></td>
                    <td width="124" bgcolor="#FFFFFF"><div align="left" class="style60">Business Name:</div></td>
                    <td width="406" bgcolor="#FFFFFF"><div align="left" class="style60">GUNSMITH RIFLES</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style60">Address:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style60">302 MAIN ST</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style60">Mailing Address:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style60">310 MAIN ST</div></td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style57"><span class="style31"><span class="style27"><span class="style61"></span></span></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style60">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style57"><span class="style31"><span class="style27"><span class="style61"></span></span></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style60">MINEOLA, NY 11051</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style60">Phone:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style60">212-555-0123</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style60">Phone:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style60">212-555-0123</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style57"><span class="style31"><span class="style27"><span class="style61"></span></span></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style57"><span class="style31"><span class="style27"><span class="style61"></span></span></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style57"><span class="style31"><span class="style27"><span class="style61"></span></span></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style57"><span class="style31"><span class="style27"><span class="style61"></span></span></span></span></span></div></td>
                  </tr>
                  <tr height="18">
                    <td height="18" bgcolor="#fffeb1"><div align="left" class="style60">Name:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">DEBRA SMITH</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">Business Name:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">ONE SHOT FIREARMS</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style60">Address:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">310 MAIN ST</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">Mailing Address:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">PO BOX 5558</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style57"><span class="style31"><span class="style27"><span class="style61"></span></span></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style57"><span class="style31"><span class="style27"><span class="style61"></span></span></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">MINEOLA, NY 11051</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style60">Phone:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">212-555-0123</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">Phone:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style60">212-555-0123</div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="945"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>Concealed Weapons Permits</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="125" />
                  <col width="196" />
                  <col width="118" />
                  <col width="84" />
                  <tr height="17">
                    <td width="205" height="17"><div align="left" class="style56">STEVE L SMITH</div></td>
                    <td width="85"><div align="left" class="style56">Date of Birth:</div></td>
                    <td width="117"><div align="left" class="style56">N/A</div></td>
                    <td width="121"><div align="left" class="style56">License Number:</div></td>
                    <td width="152"><div align="left" class="style56">W 993275</div></td>
                    <td width="63"><div align="left" class="style56">County:</div></td>
                    <td width="148"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">302 MAIN ST</div></td>
                    <td><div align="left" class="style56">Race:</div></td>
                    <td><div align="left" class="style56">W</div></td>
                    <td><div align="left" class="style56">Expires:</div></td>
                    <td><div align="left" class="style56">5/23/2010</div></td>
                    <td><div align="left" class="style56">State:</div></td>
                    <td><div align="left" class="style56">NY</div></td>
                  </tr>
                  <tr height="16">
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td><div align="left" class="style56">Sex:</div></td>
                    <td><div align="left" class="style56">M</div></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">DEBRA SMITH</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Date of Birth:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">N/A</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">License Number:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">W 233368</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">County:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">NASSAU</div></td>
                  </tr>
                  <tr height="17">
                    <td bgcolor="#fffeb1"><div align="left" class="style56">310 MAIN ST</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Race:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">W</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Expires:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">8/5/2008</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">State:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">NY</div></td>
                  </tr>
                  <tr height="17">
                    <td bgcolor="#fffeb1"><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Sex:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">W</div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="914"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>DEA Search</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="85" />
                  <col width="105" />
                  <col width="190" />
                  <col width="103" />
                  <col width="127" />
                  <tr height="17">
                    <td width="89" height="17"><div align="left" class="style56">Name:</div></td>
                    <td width="251"><div align="left" class="style56">SMITH  PHARMACY</div></td>
                    <td width="112"><div align="left" class="style56">DEA Number:</div></td>
                    <td width="213"><div align="left" class="style56">A963129995</div></td>
                    <td width="283"><div align="left" class="style56">County: NASSAU</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17"><div align="left" class="style56">Address:</div></td>
                    <td><div align="left" class="style56">320 MAIN ST</div></td>
                    <td><div align="left" class="style56">Business Type:</div></td>
                    <td><div align="left" class="style56">PHARMACY</div></td>
                    <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="16">
                    <td height="16"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td><div align="left" class="style56">Expires:</div></td>
                    <td><div align="left" class="style56">4/30/2009</div></td>
                    <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="85" />
                  <col width="105" />
                  <col width="310" />
                  <tr height="17">
                    <td width="83" height="17"><div align="left" class="style56">Schedules:</div></td>
                    <td width="808"><div align="left" class="style56">SCHEDULE TWO NARCOTIC SUBSTANCES, SCHEDULE TWO NON-NARCOTIC SUBSTANCES, SCHEDULE THREE NARCOTIC</div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="277" height="17" bgcolor="#FFFFFF"></td>
                    <td width="666" bgcolor="#FFFFFF"></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="85" />
                  <col width="105" />
                  <col width="190" />
                  <col width="103" />
                  <col width="127" />
                  <tr height="17">
                    <td width="88" height="17" bgcolor="#fffeb1"><div align="left" class="style56">Name:</div></td>
                    <td width="255" bgcolor="#fffeb1"><div align="left" class="style56">STEVE L SMITH</div></td>
                    <td width="111" bgcolor="#fffeb1"><div align="left" class="style56">DEA Number:</div></td>
                    <td width="213" bgcolor="#fffeb1"><div align="left" class="style56">AA0036032</div></td>
                    <td width="281" bgcolor="#fffeb1"><div align="left" class="style56">County: NASSAU</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">PO BOX 5558</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Business Type:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">PRACTITIONER</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Expires:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">6/30/2009</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                </table>
<table width="893" cellpadding="0" cellspacing="0">
                  <col width="85" />
                  <col width="105" />
                  <col width="310" />
                  <tr height="17">
                    <td width="82" height="17" bgcolor="#fffeb1"><div align="left" class="style56">Schedules:</div></td>
                    <td width="809" bgcolor="#fffeb1"><div align="left" class="style56">SCHEDULE TWO NARCOTIC SUBSTANCES, SCHEDULE TWO NON-NARCOTIC SUBSTANCES, SCHEDULE THREE NARCOTIC</div></td>
              </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="944"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>Merchant Vessels</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="117" />
                  <col width="120" />
                  <col width="182" />
                  <col width="107" />
                  <col width="139" />
                  <tr height="17">
                    <td width="117" height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Vessel Name:</div></td>
                    <td width="221" bgcolor="#FFFFFF"><div align="left" class="style56">WARLOCK</div></td>
                    <td width="102" bgcolor="#FFFFFF"><div align="left" class="style56">Callsign:</div></td>
                    <td width="209" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td width="97" bgcolor="#FFFFFF"><div align="left" class="style56">Shipyard:</div></td>
                    <td width="202" bgcolor="#FFFFFF"><div align="left" class="style56">CATALINA YACHTS</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Hull Number:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">CTYL5285M85C</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">IMO Number:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Home Port:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">TRAVERSE CITY </div></td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="#FFFFFF"><div align="left" class="style56">Hull ID:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Service Type:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Recreational</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">State of Port:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">MI</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Company Owner:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Boat Maker:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">CATALINA YACHTS</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Horsepower:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Company Type:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Year Built:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">1978</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Gross Tons:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">5</div></td>
                  </tr>
                  <tr height="18">
                    <td height="18" bgcolor="#FFFFFF"><div align="left" class="style56">Owner Name:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">STEVE L SMITH</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Hull Material:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">FRP (fiberglass)</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Length:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">26.6</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left" class="style56">Address:</div></td>
                    <td width="221"><div align="left" class="style56">442 GREENRIVER RD</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Motor Type:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">UNSPECIFIED</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Width:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">8.8</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td><div align="left" class="style56">MINEOLA NY 11501-2116</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Country Built:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">UNITED STATES</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Depth:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">5.8</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Vessel Name:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">WIDOWMAKER</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Callsign:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Shipyard:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">RICHARD JONES</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Hull Number:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">ALC813442181</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">IMO Number:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Home Port:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">TRAVERSE CITY </div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Hull ID:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Service Type:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Recreational</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">State of Port:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">MI </div></td>
                  </tr>
                  <tr height="19">
                    <td height="19" bgcolor="#fffeb1"><div align="left" class="style56">Company Owner:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Boat Maker:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">RICHARD JONES</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Horsepower:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Company Type:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Year Built:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">1984</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Gross Tons:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">10</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Owner Name:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">STEVE SMITH</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Hull Material:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Aluminum</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Length:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">28</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">302 MAIN ST</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Motor Type:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">UNSPECIFIED</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Width:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">11</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Country Built:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">UNITED STATES</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Depth:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">5.3</div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="943"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>Aircraft Search</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="107" />
                  <col width="96" />
                  <col width="202" />
                  <col width="117" />
                  <col width="108" />
                  <tr height="17">
                    <td width="116" height="17"><div align="left" class="style56">Owner Name:</div></td>
                    <td width="231"><div align="left" class="style56">STEVE L SMITH</div></td>
                    <td width="127"><div align="left" class="style56">Model:</div></td>
                    <td width="170"><div align="left" class="style56">Cessna</div></td>
                    <td width="137"><div align="left" class="style56">Year Built:</div></td>
                    <td width="167"><div align="left" class="style56">1992</div></td>
                  </tr>
                  <tr height="17">
                    <td height="16"><div align="left" class="style56">Address:</div></td>
                    <td><div align="left" class="style56">302 MAIN ST</div></td>
                    <td height="17"><div align="left" class="style56">N Number:</div></td>
                    <td><div align="left" class="style56">91000</div></td>
                    <td><div align="left" class="style56">Certification Date:</div></td>
                    <td><div align="left" class="style56">6/15/2007</div></td>
                  </tr>
                  <tr height="16">
                    <td height="17"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td><div align="left" class="style56"> MINEOLA, NY 11051</div></td>
                    <td><div align="left" class="style56">Serial Number:</div></td>
                    <td><div align="left" class="style56">CFL8676543</div></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td><div align="left"></div></td>
                    <td><div align="left"></div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="107" />
                  <col width="96" />
                  <col width="202" />
                  <col width="117" />
                  <col width="108" />
                  <tr height="17">
                    <td width="109" height="17" bgcolor="#fffeb1"><div align="left" class="style56">Owner Name:</div></td>
                    <td width="215" bgcolor="#fffeb1"><div align="left" class="style56">STEVE L SMITH</div></td>
                    <td width="119" bgcolor="#fffeb1"><div align="left" class="style56">Model:</div></td>
                    <td width="160" bgcolor="#fffeb1"><div align="left" class="style56">Bell</div></td>
                    <td width="130" bgcolor="#fffeb1"><div align="left" class="style56">Year Built:</div></td>
                    <td width="158" bgcolor="#fffeb1"><div align="left" class="style56">1990</div></td>
                  </tr>
                  <tr height="17">
                    <td height="16" bgcolor="#fffeb1"><div align="left" class="style56">Address:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">302 MAIN ST</div></td>
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">N Number:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">950503</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Certification Date:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">7/19/2006</div></td>
                  </tr>
                  <tr height="16">
                    <td height="17" bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56"> MINEOLA, NY 11051</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Serial Number:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">CFL97899952</div></td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                    <td bgcolor="#fffeb1">&nbsp;</td>
                  </tr>
                </table>
            <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="943"><div align="center"></div></td>
                  </tr>
                </table>
                <h4 align="left" class="style32"><strong>FAA Pilot Licenses</strong></h4>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="138" />
                  <col width="177" />
                  <col width="127" />
                  <col width="209" />
                  <tr height="17">
                    <td width="208" height="17" bgcolor="#FFFFFF"><div align="left" class="style56">STEVE L SMITH</div></td>
                    <td width="126" bgcolor="#FFFFFF"><div align="left" class="style56">FAA Number:</div></td>
                    <td width="245" bgcolor="#FFFFFF"><div align="left" class="style56">A0005509</div></td>
                    <td width="124" bgcolor="#FFFFFF"><div align="left" class="style56">Certification Level:</div></td>
                    <td width="245" bgcolor="#FFFFFF"><div align="left" class="style56">AIRLINE TRANSPORT PILOT</div></td>
                  </tr>
                  <tr height="17">
                    <td><div align="left" class="style56">302 MAIN ST</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Medical Exp:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">4/14/2010</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Ratings:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">M/AIRFR, M/POWER</div></td>
                  </tr>
                  <tr height="16">
                    <td><div align="left" class="style56"> MINEOLA, NY 11051</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">Certification Type:</div></td>
                    <td bgcolor="#FFFFFF"><div align="left" class="style56">MECHANIC</div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="18">
                    <td height="18" bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">DEBRA SMITH</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">FAA Number:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">A0022012</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Certification Level:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">AIRLINE TRANSPORT PILOT</div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">310 MAIN ST</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Medical Exp:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">7/12/2012</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Ratings:</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="#fffeb1"><div align="left" class="style56">MINEOLA, NY 11051</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">Certification Type:</div></td>
                    <td bgcolor="#fffeb1"><div align="left" class="style56">CONTROL TOWER OPERATOR</div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="#fffeb1"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="944"><div align="center"></div></td>
                  </tr>
              </table>
                            
            </td>
          </tr>
        </table>
        
        <p>&nbsp;</p>
        
          <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94">
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
			<p><a href="http://www.searchsystems.net/tos.php" target="_blank">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php" target="_blank">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php" target="_blank">About Us</a></p></td>
	</tr>
</table>

</body>
</html>
