<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
   String username="";
	boolean member=false;
	if(session.getAttribute("username") != null)
	{
		member=true;
		username=(String) session.getAttribute("username");
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Premium Databases</title>

<meta name="description" content="description here" />
<meta name="keywords" content="keywords here" />
<meta http-equiv="content-language" content="en" />

<link href="/springapp/css/common.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/JavaScript" src="javascript/common.js"></script>


<style type="text/css">
<!--
.style19 {font-family: Arial, Helvetica, sans-serif}
.style20 {font-size: small}
.style59 {	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
}
.style61 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; }
.style72 {color: #FF0000}
.style77 {font-size: 14}
.style86 {font-size: 12px}
.style90 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #000000; }
.style91 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FF0000; }
.style92 {
	font-size: x-large;
	font-weight: bold;
}
.style93 {	font-size: medium;
	white-space: normal;
	width: auto;
}
.style95 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: small;
	color: #003366;
	font-weight: bold;
}
.style106 {color: #333333}
.style107 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #333333; }
.style591 {font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
}
.style592 {font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
}
-->
</style>
</head>

<body><center>
  <div id="owrap">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
	<td rowspan="2" valign="middle">
    <a href="http://publicrecords.searchsystems.net/"><img src="images/common/updated-logo.png" width="293" height="40" /></a>
      <!--// masthead begins //-->  
     
      </td>
    <% if(member) { %>  
      <td style="color:#ffffff;" align="right"><div align="right">Welcome <strong><%=username%></strong></div></td>
      <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  height="35"  width="80"><a href="https://members.searchsystems.net/login.php?username=<%=username%>" style="text-decoration: none;font-size:14px; padding:5px 0px 10px 0px;   color:#003366; ">My Account</a>
      </td><td width="30"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="/springapp/funnel/logout.do" style="text-decoration: none; padding:5px 0px 10px 0px; font-size:14px;  color:#003366; ">Logout</a> </td>
      
     <% }else {%>
	  <td align="right" width="50%">&nbsp;</td>
	  <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  width="80"><a href="https://members.searchsystems.net/login.php" style="text-decoration: none;font-size:14px;  padding:5px 0px 10px 0px;  color:#003366; ">My Account</a>
      </td><td width="40"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="/springapp/funnel/login.do" style="text-decoration: none;font-size:14px;   padding:5px 0px 10px 0px;  color:#003366; ">Log In</a> </td>
     <% } %>
 </tr>
 </table>
         <!--// main content area begins //--> 
    <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> 
        <td id="colone" align="left"><table width="921">
          <tr>
            <td width="913" height="387"><table width="895" border="0" align="center">
              <tr>
                <td width="339" height="54"><span class="style59 style92">Premium Databases</span></td>
                <td width="546" class="style95"><h3 style="color:green"><strong><em>When a free search isn't enough.</em></strong></h3></td>
              </tr>
            </table>
              <table width="893" border="0">
                <tr>
                  <td width="887" height="506"><p><strong>In addition to providing the world's largest and longest-running directory of free public record databases, Search Systems also offers unique fee-based &quot;premium&quot; databases that help you save time and search for information using features that  free databases don't offer.</strong></p>
                    <p>Access all of our Nationwide and Statewide Premium databases from the menu below. If you have an account, you may log in now via the link in the upper right. If you do not yet have an account,<a href="/springapp/funnel/register.do">click here</a>, or you can establish one for free when you begin your first search.</p>
                    <table width="778" border="0" align="center">
                      <tr>
                        <td><p><a href="/springapp/funnel/selectSearch.do" target="self"><img src="images/common/prem-link-crim.jpg" alt="criminal records" width="221" height="101" usemap="#Map2" border="0" /></a></p></td>
                        <td><p><strong>Nationwide  $14.95 | Statewide $9.95</strong></p>
                          <p>The largest database of this type available to the public. Search over 500 million criminal records, including those from national, and state databases. Instant results and powerful search features.</p></td>
                      </tr>
					   <tr>
                        <td><p>
						<a href="/springapp/funnel/aliasSearch.do" target="self">
						<img src="images/common/Aliash_Srch.jpg" alt="Alias Search records" width="221" height="101" usemap="#Map5" border="0" /></a></p>
						</td>
                        <td>
						<p><strong>National $19.95 | Statewide $14.95</strong></p>
                        <p>
						Search our database of over 550 million records by name, birth date, and social security number for a subject's name and maiden name, 
						married name, nickname, and aliases revealed by an address history. FCRA Only.</p></td>
                      </tr>
					  <tr valign="top">
                        <td  valign="top"><p><a href="/springapp/funnel/national-security-search.do" target="self"><img src="images/common/prem_link_national.jpg" alt="National Security" width="221" height="80" usemap="#Map1" border="0" /></a></p></td>
                        <td><p><strong>Domestic U.S. & International Records $5.00</strong></p>
                           
						  <p>Search national, state, and international security databases for terrorists, illegal imports, fugitives, sanctioned and/or debarred persons, casino cheats, money launderers, and more. </p> 
                          <!-- <p>The National Security Search has been temporarily disabled as we make changes to the system to meet with current Fair Credit Reporting Act requirements. We apologize for the inconvenience.</p>-->
						  </td>
					  </tr>
                      <tr>
                        <td height="102"><p><a href="/springapp/funnel/newSearchBJL.do" target="self"><img src="images/common/prem-link-bank.jpg" alt="Bankruptcies Judgments Tax Liens" width="226" height="80" usemap="#Map3" border="0" /></a></p></td>
                        <td><p><strong>Nationwide or Statewide $5.00</strong></p>
                          <p>Search over 100 million bankruptcies, judgments, and tax liens nationwide by Individual Name, Business Name, or SSN/Tax ID.</p></td>
                      </tr>
                      <tr>
                        <td height="81"><p><a href="/springapp/funnel/eviction-records.do" target="self"><img src="images/common/prem-link-tenant.jpg" alt="tenant screening" width="221" height="81" usemap="#Map4" border="0" /></a></p></td>
                        <td><p><strong>Statewide  $5.00</strong></p>
                          <p>Landlords &amp; property managers: Manage your risks and save time looking for court eviction records. Use this database to find them for you.</p></td>
                      </tr>
                  </table>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
    
   	  </tr></table>
  </div>
   	
</center>

<!--// footer begins //-->
<div style="clear: both; height: 30px;"></div>

<div class="footer" style="text-align: center;">Copyright &copy; 1997-2016 Search Systems, Inc. All rights reserved.
<address>SearchSystems.net, 2945 Townsgate Road, Suite 200, Westlake Village, California 91361</address>
<address>Call Us at: 805-375-4041 | 
<a href="http://publicrecords.searchsystems.net/privacypolicy.php">Privacy Policy</a>
</address>
</div>
<!--// footer ends //-->

<map name="Map" id="Map">
  <area shape="rect" coords="14,5,120,26" href="/springapp/funnel/corp-bus-search.do"  alt="Corporations" />
</map>

<map name="Map2" id="Map2">
  <area shape="rect" coords="12,5,148,24" href="/springapp/funnel/selectSearch.do"  alt="Criminal Records" />
</map>
<map name="Map4" id="Map4">
  <area shape="rect" coords="14,6,151,45" href="/springapp/funnel/eviction-records.do"  alt="Tenant Screening, Evictions" />
</map>
<map name="Map1" id="Map1">
  <area shape="rect" coords="14,6,151,45" href="/springapp/funnel/national-security-search.do"  alt="National Security" />
</map>

<map name="Map3" id="Map3">
  <area shape="rect" coords="16,6,118,64" href="/springapp/funnel/newSearchBJL.do"  alt="Bankruptcies Judgments Tax Liens" />
</map>

<map name="Map5" id="Map5">
  <area shape="rect" coords="12,5,148,24" href="/springapp/funnel/aliasSearch.do"  alt="Alias Search Records" />
</map>

</body>
</html>
