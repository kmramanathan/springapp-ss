<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
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
    <title>Corporations by Company</title>
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
.style14 {font-size: smaller}
-->
    </style>
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
              <table width="945" height="550" border="0">
                <tr>
                  <td width="715" height="546"><h1 align="left">Search Corporations Nationwide</h1>
                    <p align="left">Perform an  instant search for <strong>Corporations, Limited  Liability Companies, Limited Partnerships</strong>, and <strong>Limited Liability Partnerships</strong> nationwide or by state.  Search by company name, officer/agent name,  or address. Includes all 50 U.S. States and the District of Columbia.</p>
					
					
				<form:errors path="*">
					<font color="red"><b>Please correct the following errors:</b></font><br/>
				</form:errors>
				<p class="style39">
					<font color="red"><form:errors path="*" element="div" id="error" cssClass="error-box" /></font>
            	</p>	
				
                    <p align="left"><img src="/springapp/images/common/nav-tab-company.jpg" alt="nav tab company" width="550" height="35" border="0" usemap="#Map" /></p>
                    <table width="636" height="54" border="1" bordercolor="#000000" bgcolor="#FEF8A5">
                      <tr>
                        <td><div align="left">
                          <table width="623">
                            <tr>
                              <td width="286"><h3 align="left">Search by Company</h3>
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
                                <form:input path="corporateSearchCompanyName" cssClass="formfield" size="40" />
                              </label></td>
                              <td><label>
                                <form:input path="corporateSearchCity" cssClass="formfield" />
                              </label></td>
                              <td><label>
                                <label> 
                      	<form:select path="corporateSearchState" cssClass="formfield">
							<form:options items="${corporateSearchState}"/>		
						</form:select>
                      </label>
                              </label></td>
                            </tr>
                            <tr>
                              <td height="20">* Company Name</td>
                              <td>City</td>
                              <td>State</td>
                            </tr>
                                                  </table>
                        </div>
                        <p align="left" class="style14">Do not list an extension (Inc., LLP, LP, etc.). Search will match name input with all extensions. For example, to find &quot;Acme Brewery, LLC&quot; input only &quot;Acme Brewery.&quot;</p>
                          <p align="center"><img src="/springapp/images/common/bt-start-search-small.png" alt="search"  onclick="document.forms[0].submit();" width="138" height="48" border="0" /></p></td>
                      </tr>
                    </table>
                    <table width="636" height="131" border="0">
                      <tr>
                        <td height="127"> <%@include file="BottomUp.jsp" %>                        </td>
                      </tr>
                    </table>                  </td>
                  <td width="220">  <%@include file="RightBannel.jsp" %> </td>
                </tr>
              </table>
              <p>&nbsp;</p>
            </div>
        </div>
        <!--// masthead ends //-->
        


                <!--// content begins //-->

<!-- CONTENT GOES HERE -->
	 <%@include file="BottomBanner.jsp" %>

        <!--// content ends //-->

  </div>

    <!--// mastfoot begins //-->
   <%@include file="Footer.jsp" %>
    <!--// mastfoot ends //-->

    </center>
  </div>

<map name="Map" id="Map">
  <area shape="rect" coords="178,12,340,34" href="corporateSearchLanding.do?searchType=address" alt="search by address" />
<area shape="rect" coords="348,10,542,32" href="corporateSearchLanding.do?searchType=officer" alt="Search by officer/agent" />
</map>


</form:form>
</body>
</html>
