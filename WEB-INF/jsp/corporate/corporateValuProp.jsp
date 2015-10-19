
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
    <title>Value Proposition</title>
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
    <style type="text/css">
<!--
.style12 {	font-size: small;
	font-weight: bold;
	font-style: italic;
}
.style15 {	color: #000000;
	font-weight: bold;
}
-->
    </style>
</head>
  
<body>
  <div class="bodywrap">

    <center>
      <div id="owrap">
        
        <!--// masthead begins //-->
        <div id="masthead">
            <div id="logo">
              <p><a href="http://www.searchsystems.net/" title="SearchSystems.net"><img src="/springapp/images/common/updated-logo.png" alt="SearchSystems.net" /></a></p>
              <table width="937" border="1">
                <tr>
                  <td width="453" height="507" bordercolor="#000000"><p align="center"><img src="/springapp/images/common/ban-corp-standard.png" alt="corporations report" width="436" height="67" /></p>
                    <p align="center"><img src="/springapp/images/common/7 per search.jpg" alt="7 per search" width="116" height="57" /></p>
                    <table width="434" border="0" align="center">
                    <tr>
                      <td><p align="center"><strong>All 50 States!</strong></p>
                        <p align="left">Our corporations database is updated monthly and contains public record information on corporations, LLC's, LLP's and LP's in all 50 U.S. States and the District of Columbia.</p>
                        <p align="left">Results may include:</p>
                        <ul>
                            <li>
                              <div align="left">Full corporate name</div>
                            </li>
                          <li>
                              <div align="left">Mailing Address</div>
                          </li>
                          <li>
                              <div align="left">Officers/Registered Agent</div>
                          </li>
                          <li>
                              <div align="left">State of filing</div>
                          </li>
                          <li>
                              <div align="left">Registry number</div>
                          </li>
                          <li>
                              <div align="left">Current legal status</div>
                          </li>
                        </ul></td>
                    </tr>
                  </table>
                    <p>&nbsp;</p>
                    <table width="175" border="0" align="center">
                      <tr>
                        <td width="10" height="54"><h4 align="left">&nbsp;</h4></td>
                        <td width="250"><a href="<c:url value="/corporate/purchaseCorporateRecordSearch.do"/>"><img src="/springapp/images/common/bt-continue.png" alt="continue" width="145" height="48" border="0" /></a></td>
                      </tr>
                    </table></td>
                  <td width="16">&nbsp;</td>
                  <td width="454"><p align="center"><img src="/springapp/images/common/ban-corp-w-membership.png" alt="corporations with membership" width="439" height="69" /></p>
                    <p align="center"><img src="/springapp/images/common/5 per search.jpg" alt="5 per search" width="123" height="57" /></p>
                    <table width="428" height="270" border="0" align="center">
                    <tr>
                      <td height="266"><div align="left">
                        <p><strong>Benefits of membership:</strong> </p>
                      </div>
                        <ul>
                          <li>
                            <div align="left">U.S. Criminal Records Nationwide - $5 off standard price</div>
                          </li>
                          <li>
                            <div align="left">U.S. Criminal Records Statewide - $3 off</div>
                          </li>
                          <li>
                            <div align="left">U.S. Bankruptcies, Judgments &amp; Tax Liens - $5 off</div>
                          </li>
                          <li>
                            <div align="left">Nationwide Property - $2 off</div>
                          </li>
                          <li>
                            <div align="left">Mexico Court Records - $5 off</div>
                          </li>
                        </ul>
                        <h4 align="center" class="style15">AND:</h4>
                        <ul>
                          <li>
                            <div align="left">Delay-free unlimited access to the largest directory of free public record databases on the Internet!</div>
                          </li>
                          <li>
                            <div align="left">Seamless checkout.</div>
                          </li>
                          <li>
                            <div align="left">Qualified businesses may elect to receive monthly billing and volume discounts.</div>
                          </li>
                      </ul>                        </td>
                    </tr>
                  </table>
                    <table width="387" border="0" align="center">
                      <tr>
                      
                        <td width="203" height="66"><h4><img src="/springapp/images/common/2995.jpg" alt="2995" width="175" height="64" /></h4></td>
                        <td width="202"><a href="<c:url value="/funnel/signup.do?previousPage=/corporate/confirmCorporateSearch.do"/>"><img src="/springapp/images/common/bt-continue.png" alt="continue" width="145" height="48" border="0" /></a></td>
                      </tr>
                    </table></td>
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
   <%@include file="Footer.jsp"%>
    <!--// mastfoot ends //-->

    </center>
  </div>
</body>
</html>
