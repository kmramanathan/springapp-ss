
<%@include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/springapp/css/style_02.css">
<title></title>
<style type="text/css">
.styletable111{font-size: 13px}

.menu {
	font-family:Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #ffffff;
	float: right;
	width: 500px;
	text-align: right;
	margin-top: 25px;
	margin-right: 25px;
	margin-bottom: 0;
	margin-left: 0;
}
.menu a {
	color: #ffffff;
	text-decoration: underline;
}
.style107 {font-family: Arial, Helvetica, sans-serif}
.style108 {font-family: Arial, Helvetica, sans-serif; color: #003366; }
.style1 {font-size: small}
.style74 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
.style109 {font-family: Arial, Helvetica, sans-serif; font-size: small; }
</style>
</head>
<body>

<table width="905"  align="center">
<tr>
		<td width="897" style="height:70px; ">
       	  <div class="menu"><a href="http://publicrecords.searchsystems.net/">Search Systems Home</a> | <a href="http://publicrecords.searchsystems.net/contact.php">Contact Us</a></div>

    <img src="/springapp/funnel/images/logo_02.gif" alt="" /></td>
  </tr>
	<tr>
		<td width="897" style="padding: 10px 0;">
		<table width="846" align="center">
          <tr>
            <td width="838">
         
<div id="formwrapper">
<table border="0" cellpadding="0" cellspacing="0" width="99%">
<tr valign="top">
<td><h2><span class="style108">Search Results</span></h2> </td>
<td align="right">
<p><a href="javascript:window.print();"><img src="/springapp/images/common/print.png" border="0" align="right" alt="Print Version"/></a></p>
</td></tr>
<tr><td colspan="2" align="right"><h3 style="text-align: right;float:right;"><span class="style107"><a href="newSearch.do">Search Again</a></span></h3></td></tr>
</table>
<p>&nbsp;</p>

<p style="font-family:Arial, Helvetica, sans-serif;"><strong>Your criminal record search is complete.</strong></p>
<p>&nbsp;</p>
<c:choose>
<c:when test="${sessionScope.location eq 'all'}">
<p style="color:#000; font-family:Arial, Helvetica, sans-serif;">We searched over 450 million criminal records nationwide and over 70 national and international terrorist and debarred persons databases and found NO criminal disposition records for your subject, based on your search criteria:</p>
</c:when>
<c:otherwise>
<p style="color:#000; font-family:  Arial, Helvetica, sans-serif;">We searched available criminal disposition records and did not find any records that matched your subject, based on your search criteria:</p>
</c:otherwise></c:choose>
<p>&nbsp;</p>
<table cellspacing="0" cellpadding="0" border="0" style="background-color: #FFFFFF;margin-left: 55px;font-family:Arial, Helvetica, sans-serif;">
    <tr>
        <td nowrap="nowrap" style="padding: 0 75px 0 0;">First Name:</td>
        <td class="fill" style="padding: 0;"><strong><c:out value="${sessionScope.fname}"/></strong></td>
    </tr>
    <tr>
        <td nowrap="nowrap" style="padding: 0 75px 0 0;">Last Name:</td>
        <td class="fill" style="padding: 0;"><strong><c:out value="${sessionScope.lname}"/></strong></td>
    </tr>
     <tr>
        <td nowrap="nowrap" style="padding: 0 75px 0 0;">Location:</td>
        
        <c:choose>
        	<c:when test="${sessionScope.location eq 'all'}">
        		<td class="fill" style="padding: 0;"><strong>Nationwide</strong></td>
        	</c:when>
        	<c:otherwise>
        		<td class="fill" style="padding: 0;"><strong><c:out value="${sessionScope.location}"/></strong></td>
        	</c:otherwise>
        </c:choose>
        
        
    </tr>
    <tr>
        <td style="padding: 0.30em 0 0 0;">Date of Birth:</td>
        <td style="padding: 0.30em 0 0 0;"><strong><c:out value="${sessionScope.dob}"/></strong></td>
    </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<c:choose>
        	<c:when test="${sessionScope.location eq 'all'}">
 <table width="783" border="0" cellspacing="0.5" cellpadding="0.5" style="font-family: Arial, Helvetica, sans-serif;">
                  <tr>
                    <th width="134" bordercolor="#000000" scope="col"><span class="style1">State</span></th>
                    <th width="128" bordercolor="#000000" scope="col"><div align="left"><span class="style1">Records Found</span></div></th>
                    <th width="115" bordercolor="#000000" scope="col"><span class="style1">State</span></th>
                    <th width="127" bordercolor="#000000" scope="col"><div align="left"><span class="style1">Records Found</span></div></th>
                    <th width="134" bordercolor="#000000" scope="col"><span class="style1">State</span></th>
                    <th width="145" bordercolor="#000000" scope="col"><div align="left"><span class="style1">Records Found</span></div></th>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Alabama</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Louisiana</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Oklahoma</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Alaska</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Maine</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Oregon</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Arizona</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Maryland</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Pennsylvania</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Arkansas</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Massachusetts</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Rhode Island</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">California</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Michigan</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">South Carolina</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Colorado</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Minnesota</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">South Dakota</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Connecticut</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Mississippi</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Tennessee</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Delaware</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Missouri</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Texas</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">District of Columbia</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Montana</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Utah</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Florida</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Nebraska</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Vermont</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Georgia</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Nevada</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Virginia</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Hawaii</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">New Hampshire</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Washington</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Idaho</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">New Jersey</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">West Virginia</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Illinois</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">New Mexico</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Wisconsin</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Indiana</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">New York</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Wyoming</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Iowa</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">North Carolina</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000" class="style1">National</td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Kansas</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">North Dakota</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000" class="style1">International</td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                  </tr>
                  <tr>
                    <td bordercolor="#000000"><span class="style1">Kentucky</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000"><span class="style1">Ohio</span></td>
                    <td bordercolor="#000000"><div align="left"><span class="style1">0</span></div></td>
                    <td bordercolor="#000000" class="style1">&nbsp;</td>
                    <td bordercolor="#000000">&nbsp;</td>
                  </tr>
                </table>
                </c:when>
                
                </c:choose>
                <p>&nbsp;</p>
                <p class="style109">Coverage varies in each state depending on state and local law. Check the coverage section on your results page for details on the availability of records in each state.</p>
                <p>&nbsp;</p>
              </div>
            <p align="left"><span class="style74"><strong>DISCLAIMER</strong><br />
SearchSystems.net provides no warranty of any type as to the accuracy of this information,  and any reliance on this information is solely at your own risk and responsibility.  All  information retrieved from or through SearchSystems.net must be utilized in accordance with  the User Agreement and all applicable state and federal laws. </span></p></td>
          </tr>
          <tr>
            <td height="23">&nbsp;</td>
          </tr>
        </table>
 </td>
  </tr>
  <tr><td><p align="right"><span class="style107"><a href="newSearch.do">Search Again</a></span></p></td></tr>
	<tr>
		<td width="897" class="footer" style="height:56px; background:url(../images/bg_footer_11.jpg) top no-repeat; padding:15px 0 0 60px;">
		  <p>Copyright &copy; 2014 Pacific Information Resources, Inc. All rights reserved.</p>
			<p><a href="#">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php">About Us</a></p></td>
  </tr>
</table>
</body>
</html>

