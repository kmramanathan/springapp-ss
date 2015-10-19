<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<% 
	String username=(String)session.getAttribute("username");
	boolean member=false;
	if(username != null)
	{
		member=true;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>confirmation members</title>

<meta name="description" content="description here" />
<meta name="keywords" content="keywords here" />
<meta http-equiv="content-language" content="en" />

<link href="/springapp/css/mexico.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/JavaScript" src="/springapp/javascript/common.js"></script>
<style type="text/css">
<!--
.style24 {
	color: #003366;
	font-weight: bold;
}
.style25 {font-size: 0.8em}
-->
</style>
</head>

<body><center>
<form:form method="post">
  <div id="owrap">

    <p align="left">
      <!--// masthead begins //-->
      <img src="/springapp/images/common/updated-logo.png" width="293" height="40" />
      <!--// masthead ends //-->
      
      <!--// main content area begins //--></p>
    <table cellpadding="0" cellspacing="0" border="0" width="900" id="maintable">
      <tr valign="top">
    		
        <!--// column one begins //--> 
          <td width="667" height="621" align="left" id="colone"><div class="wrap">
            <div class="innerwrap">
            <h1 class="style24">Please Confirm Your Selection</h1>
            <table width="654" border="0">
              <tr>
                <td width="147">Name to Search:</td>
                <td width="507"><strong><c:out value="${searchName}"/></strong></td>
              </tr>
              <tr>
                <td>Region:</td>
                <td style="WORD-BREAK:BREAK-ALL;"><strong><c:out value="${searchRegion}"/></strong></td>
              </tr>
              <tr>
                <td>Court Type:</td>
                <td><strong><c:out value="${searchRecordType}"/></strong></td>
              </tr>
              <tr>
                <td>Price:</td>
                <td><strong>$<c:out value="${searchPrice}"/></strong></td>
              </tr>
			  <tr>
			  	<td>Credit Card Number:</td>
				<td><strong>***${ccLast4}</strong></td>
			  </tr>
            </table>
            <center>
              <p align="left" class="style25">By performing this search, I acknowledge that I will be charged for this search.  I understand that the data from the above sources is collected from Mexico state and federal court calendars and indexes.  I also understand that this   information is considered public record and that the court history information reflected   should not be considered as a 100% complete or accurate history of any individual.            </p>
              <p align="left" class="style25">    <strong>Please verify that the name and options are correct.</strong> If the information   you input is correct and you understand what databases will be searched, input your billing information and press   Continue To My Search. If you're not sure what databases will be searched, click on <a href="<c:url value="/funnel/mexico/showStaticPages.do?page=coverage"/>" target="_blank"> Mexico Data Coverage</a> to review the available court index databases. </p>
              <p align="left" class="style25">&nbsp;</p>
            </center>
           <p align="center">
  <input type="image" src="/springapp/images/common/bt-continue-to-search.png" alt="Make Payment and Start Searching" width="201" height="60" />
  </p>
            </div>
			<% if(!member) { %>
			<p align="center">
		      <table width="458" border="1">
                <tr>
                  <td width="458" height="68" align="left"><h4 class="style28"> Thinking of doing more searches?  Want additional benefits?</h4>
                    <h4 class="style28"> Look for our membership offer on your Result Details page.</h4></td>
                </tr>
              </table>
			  </p>
			  <% } %>
              <p align="left" class="style25">&nbsp;</p>
              <p align="left" class="style25">&nbsp;</p>
          </div>          
		  
		  

		  
		  </td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
          <td width="220" align="left" id="coltwo"><p align="center"><img src="/springapp/images/common/ss-logo-dotnet-bgray.png" width="200" height="160" /></p>
            <p align="center"><img src="/springapp/images/common/MexFlag-small.png" width="200" height="114" /></p>
            <div align="left"></div>
            <div id="photo">
              <h3>&nbsp;</h3>
              <h3><strong>Trusted and Secure</strong></h3>
              <p><img src="/springapp/images/common/SSL-bbb-thawte.jpg" alt="ssl thawte bb" width="200" height="150" /></p>
          </div>        </td>
        <!--// column two begins //--> 
   	  </tr></table>
  </div>
</form:form> 

  <!--// footer begins //-->
  <%@include file="Footer.jsp" %>
  <!--// footer ends //-->
	
</center></body>
</html>
