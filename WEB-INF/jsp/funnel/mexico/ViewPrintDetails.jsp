<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.httpclient.*" %>
<%@ page import="org.apache.commons.httpclient.methods.*" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.w3c.dom.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="org.xml.sax.*" %>
<%@ page import="springapp.web.funnel.mexico.SearchMexicanRecordForm.SearchMexicanRecordFormCommand" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View and Print details</title>

<meta name="description" content="description here" />
<meta name="keywords" content="keywords here" />
<meta http-equiv="content-language" content="en" />

<link href="/springapp/css/mexico.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/JavaScript" src="/springapp/javascript/common.js"></script>
<style type="text/css">
<!--
.style3 {
	color: #03366;
	font-weight: bold;
}
.style4 {
	color: #003366;
	font-weight: bold;
}
.style5 {font-weight: bold}
-->
</style>
</head>
<%
	String strCheck[] = (String[])session.getAttribute("searchResultsItemIndex");
%>

<body><center>
  <div id="owrap">

    <p align="left">
      <!--// masthead begins //-->
      <img src="/springapp/images/common/updated-logo.png" width="293" height="40" /><!--// masthead ends //-->
      
      <!--// main content area begins //--></p>
    <table cellpadding="0" cellspacing="0" border="0" width="951" id="maintable">
      <tr valign="top">
    		
        <!--// column one begins //--> 
          <td width="951" height="1198" align="left" id="colone"><table width="914" border="0" bordercolor="#FFFF99" bgcolor="#FFFF99">
            <tr>
              <td width="908" height="107"><h2><strong>Looking For More Information?</strong></h2>
                <p><span class="style5"><a href="<c:url value="../landing.html"/>">Click here</a> to search our database of over 300 million U.S. criminal records, or...</span></p>
              <p><strong><a href="#">Click here</a> to find out more about the thousands of additional public record databases available to SearchSystems.net members.</strong></p></td>
            </tr>
          </table>
            <table width="916" border="0">
            <tr>
              <td width="662" height="60"><h1 class="style4">Mexico Court Record Search Results</h1>
              <h2 class="style4">View and Print Details</h2></td>
              <td width="140"><a href="<c:url value="/funnel/mexico/searchMexicanRecord.do?type=mexico" />"><strong>Search Again</strong></a></td>
              <td width="100"><a href="#" onclick="javascript: window.print(); return false;"><strong>Print</strong></a></td>
            </tr>
          </table>
            <p>&nbsp;</p>
            <p class="style3">Search terms: <%= ((SearchMexicanRecordFormCommand)session.getAttribute("searchMexicanRecordFormCommand")).getMexicanSearchIndividualName() %></p>

			<%
				Hashtable hTable = (Hashtable) session.getAttribute("result");
				for(int i=0;i<strCheck.length;i++){
					String strCheckVal = strCheck[i];
					Vector vData = (Vector)hTable.get("chk"+strCheckVal);
					String datepub = (String) vData.elementAt(0);
					String party1 = (String) vData.elementAt(1);
					String hearingdate = (String) vData.elementAt(2);
					String comment = (String) vData.elementAt(3);
					String docket = (String) vData.elementAt(4);
					String courtlocal = (String) vData.elementAt(5);
					String party2 = (String) vData.elementAt(6);
					String court = (String) vData.elementAt(7);
					String location = (String) vData.elementAt(8);
					String nature = (String) vData.elementAt(9);
					String caption = (String) vData.elementAt(10);
					//out.println(vData);
			%>
					<table width="908" border="1">
					  <tr>
						<td width="67">Caption:</td>
						<td width="497"><strong>&nbsp;<%=caption%></strong></td>
						<td width="72">Location:</td>
						<td width="132"><strong>&nbsp;<%=location%> </strong></td>
					  </tr>
					</table>
					<table width="907" border="1">
					  <tr>
						<td width="80">Plaintiff(s):</td>
						<td width="364">&nbsp;</td>
						<td width="80">Defendant(s):</td>
						<td width="365">&nbsp;<strong>Jose Juan Ramirez Rodriguez</strong></td>
					  </tr>
					</table>
					<table width="907" border="1">
					  <tr>
						<td width="80">Comment:</td>
						<td width="70">&nbsp;<%=comment%></td>
						<td width="87">Date Pub.:</td>
						<td width="103"><strong>&nbsp;<%=datepub%></strong></td>
						<td width="62">Docket:</td>
						<td width="108"><strong>&nbsp;<%=docket%></strong></td>
						<td width="67">Hearing Date:</td>
						<td width="130">&nbsp;<%=hearingdate%></td>
					  </tr>
					</table>
					<table width="908" border="1">
					  <tr>
						<td width="54">Court:</td>
						<td width="131">&nbsp;<strong><%=court%></strong></td>
						<td width="103">Court (local):</td>
						<td width="169">&nbsp;<strong><%=courtlocal%></strong></td>
						<td width="63">Nature:</td>
						<td width="236">&nbsp;<strong><%=nature%></strong></td>
					  </tr>
					</table>
					<BR><BR>
			<%
				}	
			%>

            <p class="style3">&nbsp;</p>
            <table border="0" cellspacing="0" cellpadding="0" width="100%">
              <tr>
                <td height="149"><h3>DISCLAIMER</h3>
                    <p>Search Systems provides no warranty of any type as to the accuracy of this  information, and any reliance on this information is solely at your own risk and  responsibility. All information retrieved from or through SearchSystems.net  must be utilized in accordance with the User Agreement and all applicable state  and federal laws.</p></td>
              </tr>
            </table></td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
    
   	  </tr></table>
    <!--// main content area ends //-->

  </div>
   
<!--// footer begins //-->
<%@include file="Footer.jsp" %>
<!--// footer ends //-->
	
</center></body>
</html>
