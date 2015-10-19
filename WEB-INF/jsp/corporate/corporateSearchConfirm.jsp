
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
    <title>Confirmation</title>
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
              <table width="945" height="409" border="0">
                <tr>
                  <td width="715" height="405"><h1 align="left">Please Confirm Your Search</h1>
                    <table width="478" border="0" align="left">
                      <tr>
                        <td width="171">Name to Search:</td>
                        <td width="297"><strong><c:out value="${searchName}"/></strong></td>
                      </tr>
                     <tr>
                        <td>Price:</td>
                        <% if(member) { %>
					   <td><strong>$5</strong></td> <% } %> 
					  <% if(!member) { %>
					   <td><strong>$7</strong></td> <% } %>
                      </tr>
                      
                      <tr>
                        <td>Credit Card Number:</td>
                        <td><strong>***${ccLast4}</strong></td>
                      </tr>
                    </table>
                    <p align="left">&nbsp;</p>
                    <p align="left">&nbsp;</p>
                    <p align="left">&nbsp;</p>
                    <p align="left">&nbsp;</p>
                    <p align="left">By performing this search, I  acknowledge that I will be charged for this search. I understand that this  information is considered public record and that the information reflected  should not be considered as a 100% complete or accurate history of any company. </p>
                    <p align="left"><br />
                      <strong>Please verify that the search  query is correct.</strong> If the  information you input is correct and you understand what databases will be  searched, press Continue To My Search. </p>
                    <p>&nbsp;</p>
                    <p align="center"><input type="image" src="/springapp/images/common/bt-continue-to-search.png" alt="continue to search" width="201" height="60" border="0" /></p>
                  </td>
                  <td width="220"><p><img src="/springapp/images/common/ss-logo-dotnet-bgray.png" alt="diamond logo" width="261" height="177" /></p>
                    <p>&nbsp;</p>
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
  </form:form>
</body>
</html>
