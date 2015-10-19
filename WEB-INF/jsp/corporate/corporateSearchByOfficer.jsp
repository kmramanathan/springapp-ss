<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
boolean member=false;
String username=(String)session.getAttribute("username");
if(username != null)
{
	member=true;
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Corporations by Officer</title>
    <meta name="title" content="SearchSystems.net Premium"/>
    <meta name="description" content="SearchSystems.net Criminal and Offender Records, Bankruptcies, Judgments and Tax Liens, federal, court, civil, bankruptcy, public"/>
    <meta name="keywords" content="criminal, offender, bankruptcies, tax, liens, judgments, federal, property, court, civil, bankruptcy, public, records, national, social security, documents, suit, action, certificates, lawsuits, information, real, estate, state, florida, california, michigan"/>
    <meta name="rating" content="GENERAL"/>

    <meta name="classification" content="Search"/>
    <meta name="copyright" content="SearchSystems.net"/>
    <meta name="distribution" content="GLOBAL"/>
    <meta name="Author" content="SearchSystems.net"/>
    <meta http-equiv="content-language" content="en" />
    <link href="/springapp/css/corporate.css" rel="stylesheet" type="text/css" media="screen" />

</head>
  
<body>

<form:form method="post">

  <div class="bodywrap">

    <center>
      <div id="owrap">
        
        <!--// masthead begins //-->
        <div id="masthead">
            <div id="logo">
              <p><a href="http://www.searchsystems.net/" title="SearchSystems.net"><img src="/springapp/images/common/updated-logo.png" alt="SearchSystems.net" /></a></p>
              <table width="945" height="532" border="0">
                <tr>
                  <td width="715" height="528"><h1 align="left">Search Corporations Nationwide</h1>
                    <p align="left">Perform an  instant search for <strong>Corporations, Limited  Liability Companies, Limited Partnerships</strong>, and <strong>Limited Liability Partnerships</strong> nationwide or by state.  Search by company name, officer/agent name,  or address. Includes all 50 U.S. States and the District of Columbia.</p>
					
					
				<form:errors path="*">
					<font color="red"><b>Please correct the following errors:</b></font><br/>
				</form:errors>
				<p class="style39">
					<font color="red"><form:errors path="*" element="div" id="error" cssClass="error-box" /></font>
            	</p>	
					
                    <p align="left"><img src="/springapp/images/common/nav-tab-officer.jpg" alt="nav tab officer" width="550" height="35" border="0" usemap="#Map" /></p>
                    <table width="636" height="54" border="1" bordercolor="#000000" bgcolor="#FEF8A5">
                      <tr>
                        <td><div align="left">
                          <table width="623">
                            <tr>
                              <td width="286"><h3 align="left">Search by Officer/Agent</h3>
                              <p align="left">* Required Field</p></td>
                              <td width="167">&nbsp;</td>
                               <td width="156">
							   <% if(!member) { %>							  
							  <div align="center"><img src="/springapp/images/common/7.jpg" alt="7" width="66" height="59" /></div>
							  <% } else { %>
							  <div align="center"><img src="/springapp/images/common/5.jpg" alt="5" width="74" height="70" /></div>
							  <% } %>
								</td>
                            </tr>
                            <tr>
                              <td><label>
                                <form:input path="corporateSearchOfficerName" cssClass="formfield" size="40" />
                              </label></td>
                              <td><label>
                                <form:input path="corporateSearchCity" cssClass="formfield" />
                              </label></td>
                              <td><label>
                                <form:select path="corporateSearchState" cssClass="formfield">
									<form:options items="${corporateSearchState}"/>		
						</form:select>
                              </label></td>
                            </tr>
                            <tr>
                              <td height="20">* Officer/Agent Name</td>
                              <td>City</td>
                              <td>State</td>
                            </tr>
                                                  </table>
                        </div>
                        <p align="left" class="style14">Search by First and Last Name (e.g. JOHN DOE). Do not input middle initials or suffixes (Jr., Sr., M.D., etc.).</p>
                       <p align="center"><img src="/springapp/images/common/bt-start-search-small.png" alt="search" width="138" height="48" onclick="document.forms[0].submit();" border="0"/></p></td>
                      </tr>
                    </table>
                    <table width="636" height="131" border="0">
                      <tr>
                        <td height="127"><p align="left">Includes all 50 U.S. States and the District of Columbia. Updated Monthly.</p>
                          <p align="left"><img src="/springapp/images/common/results-include.png" alt="results include" width="584" height="113" /></p>
                        </td>
                      </tr>
                    </table>
                  </td>
                  <td width="220"><p><img src="/springapp/images/common/image-female-businesswoman.jpg" alt="female business" width="261" height="301" /></p>
                     <% if(!member) { %><p align="center"><a href="#"><img src="/springapp/images/common/already-member.png" alt="already member" width="238" height="67" border="0" /></a></p> <% } %>
                  <p><img src="/springapp/images/common/SSL-bbb-thawte.jpg" alt="bbb thawte" width="263" height="118" /></p>
                  </td>
                </tr>
              </table>
              <p>&nbsp;</p>
            </div>
        </div>
        <!--// masthead ends //-->
        


                        <!--// content begins //-->

<!-- CONTENT GOES HERE -->
<div>
  <table width="943" height="94" border="0" bgcolor="#FEF8A5">
  <tr>
    <td><p align="left" class="style12">Accolades for SearchSystems.net:</p>
      <blockquote>
        <p align="left" class="style12"><em>&quot;One of the best&quot; - Wall Street Journal</em></p>
        <p align="left" class="style12">&quot;When I show foreign journalists this site, their jaws drop.&quot; -  Al Tompkins, Poynteronline</p>
        <p align="left" class="style12">&quot;Your website is the search engine of the future... a source that provides relief from the usual frustrations of online searching. Searchsystems.net is so consistently dependable, it feels as if you're not on this planet. It's just perfect.&quot; - Rose</p>
        <p align="left" class="style12">&quot;The one source I always mention in my presentations for private investigators and professional researchers.&quot;  - Tamara Thompson, P.I.</p>
      </blockquote></td>
  </tr>
</table>
</div>


        <!--// content ends //-->

  </div>

    <!--// mastfoot begins //-->
    <div id="footer">
        <div class="wrap">
            <!--// security icons begin //-->
            <ul class="security">
                <li><a href="https://www.bbb.org/online/consumer/cks.aspx?ID=10404141025041762" target="_blank" title="Better Business Bureau"><img src="/springapp/images/common/footer-logo-bb.gif" alt="Better Business Bureau" /></a></li>
                <li><a onclick="window.open('https://www.thawte.com/cgi/server/certdetails.exe?referer=https://www.searchsystems.net/', 'Thawte', 'width=500, height=500')" href="#" title="Thawte"><img src="/springapp/images/common/footer-logo-thawte.gif" alt="Thawte" /></a></li>
            </ul>
            <!--// security icons end //-->

            <!--// primary footer links //-->
            <ul class="primary">
                <li>Copyright &copy; 2003-2008 Pacific Information Resources, Inc. All rights reserved.</li>
            </ul>
            <!--// primary footer links ends //-->

            <!--// secondary (lesser) footer links begin //--> 
            <ul class="secondary">
                <li><a href="http://premium.searchsystems.net/contact.php" target="_blank" title="Contact Us">Contact Us</a> | </li>
                <li><a href="http://premium.searchsystems.net/privacy.php" target="_blank" title="Privacy Policy">Privacy Policy</a> | </li>
                <li><a href="http://premium.searchsystems.net/disclaimer.php" target="_blank" title="Disclaimer">Disclaimer</a></li>
            </ul>
            <!--// secondary footer ends //-->
        </div>
    </div>
    <!--// mastfoot ends //-->

    </center>
  </div>

<map name="Map" id="Map"><area shape="rect" coords="3,9,175,34" href="corporateSearchLanding.do?searchType=company" alt="search by company" />
<area shape="rect" coords="180,9,341,36" href="corporateSearchLanding.do?searchType=address" alt="search by address" />
</map>

</form:form>
</body>
</html>
