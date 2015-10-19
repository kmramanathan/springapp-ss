<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@ page import="java.util.*" %>
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
<title>Results</title>

<meta name="description" content="description here" />
<meta name="keywords" content="keywords here" />
<meta http-equiv="content-language" content="en" />

<link href="/springapp/css/mexico.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/JavaScript" src="/springapp/javascript/common.js"></script>

<script>
	function submitPage(){
		document.result.submit();
	}
	
	function setAllCheckBoxes(FieldName, CheckValue) {
		if(!document.forms[0])
			return;
		var objCheckBoxes = document.forms[0].elements[FieldName];
		if(!objCheckBoxes)
			return;
		var countCheckBoxes = objCheckBoxes.length;
		if(!countCheckBoxes)
			objCheckBoxes.checked = CheckValue;
		else
			// set the check value for all check boxes
			for(var i = 0; i < countCheckBoxes; i++)
				objCheckBoxes[i].checked = CheckValue;
		return false;
	}
</script>
<style type="text/css">
<!--
.style3 {
	color: #003366;
	font-weight: bold;
}
.style6 {font-size: x-small; font-weight: bold; }
.style7 {font-size: x-small}
.style8 {color: #003366}
.style9 {font-size: 0.8em}
.style5 {font-weight: bold}
-->
</style>
</head>

<body>
<form:form action="ViewDetailsMexicanRecord.do" method="post">
<center>
  <div id="owrap">

    <p align="left">
      <!--// masthead begins //-->
      <img src="/springapp/images/common/updated-logo.png" width="293" height="40" /></p>
    <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> 
          <td height="849" align="left" id="colone">
		  <% if(!member) { %>
		  <table width="950" border="0" bordercolor="#FFFF99" bgcolor="#FFFF99">
            	<tr>
              		<td width="908" height="107"><h2><strong>Looking For More Information?</strong></h2>
                		<p><a href="showStaticPages.do?page=wholePicture">Click here</a> to become a member to access thousands of additional public record databases and get discounts on our Premium database searches (including Mexico court records, over 300 million U.S. criminal records, and U.S. bankruptcies, judgments and tax liens).</p>         
    		   		</td>
            	</tr>
          </table>
		  <p>&nbsp;</p>
		  <% } %>
            <h1 class="style3">Mexico Court Record Search Results</h1>
            <h3>View details or refine search for each page of results</h3>
            <h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use the text box below to refine your search results from this page.</h4>
            <% if (request.getParameter("errorNoItemSelected") != null && ((String)request.getParameter("errorNoItemSelected")).equalsIgnoreCase("yes")) { %>
				<font color="red"><b>Please correct the following errors:</b></font><br/>
			<p class="style39">
				<font color="red">No item selected to view the details.</font>
            </p>
			<% } %>
			<table width="936" border="0">
              <tr>
                <td width="377">Search Terms: <strong><c:out value="${searchName}"/></strong><br />
                  <input name="refineSearchText" type="text" id="refineSearchText" size="60" /></td>
                <td width="150"><input type="image" src="/springapp/images/common/bt-refine-search.png" alt="refine search" width="128" height="49" name="refineSearch" value="Refine Search" /></td>
                <td width="395"><p class="style9">Use quotation marks to narrow on an exact name (e.g. &quot;Jose Juan Ramirez&quot;), rather than searching all words anywhere in the record.</p> <!--<p class="style9">This feature will only refine the results shown on this page. If your results continue to your additional pages, you can refine those as well.</p>--></td>
              </tr>
            </table>
            <p><label></label></p>
            <table width="838" border="0">
              <tr>
                <td width="147" height="73"><p><input type="image" src="/springapp/images/common/bt-view-details.png" alt="view details" width="129" height="49" name="viewDetails" value="View Details"/></p></td>
                <td width="688">Check boxes below and click View Details button to view details and print results from this page.</td>
              </tr>
            </table>
            <table width="941" border="0" id="pageViewFooter">
          		<tr>
            		<td width="163" class="style8" align="left"><div id="pageNumberAtHeader"></div></td>
            		<td width="401" class="style8" align="center"><div id="matchesTitleAtHeader"></div></td>
					<td width="356" class="style8" align="right"><div id="pagesLinkTitleAtHeader"></div></td>
				</tr>
        	</table>
	    <table width="947" border="1">
          <tr>
            <td width="28"><input type="checkbox" name="chkbxViewAll" onclick="javascript: if (this.checked) setAllCheckBoxes('chk',true); else setAllCheckBoxes('chk',false);" title="Check/Uncheck All" /></td>
            <td width="616" class="style6">Caption</td>
            <td width="145" class="style6">Location</td>
            <td width="130" class="style6">Court</td>
          </tr>
<%
			String datepub = "";
			String party1 = "";
			String hearingdate = "";
			String comment = "";
			String docket = "";
			String courtlocal = "";
			String party2 = "";
			String court = "";
			String location = "";
			String nature = "";
			String caption = "";
			
			int refineSearchCount = 0;
			
			Hashtable hList = (Hashtable)session.getAttribute("resultData");
			if(hList!=null){
				for(int s=0; s< hList.size(); s++){
					Vector v = (Vector)hList.get("chk" + s);
					datepub = (String)v.elementAt(0);
					party1 = (String)v.elementAt(1);
					hearingdate = (String)v.elementAt(2);
					comment = (String)v.elementAt(3);
					docket = (String)v.elementAt(4);
					courtlocal = (String)v.elementAt(5);
					party2 = (String)v.elementAt(6);
					court = (String)v.elementAt(7);
					location = (String)v.elementAt(8);
					nature = (String)v.elementAt(9);
					caption = (String)v.elementAt(10);
					if(request.getParameter("isHighLightText") != null && ((String)request.getParameter("isHighLightText")).equalsIgnoreCase("yes")) {
						String strHighlightText = (String)request.getParameter("highLightText");
						strHighlightText = (strHighlightText != null ? strHighlightText.trim(): "");
						String strToken = "";
						if (strHighlightText.startsWith("\"")) { 
							strHighlightText = strHighlightText.replace("\"","");
							strToken = ""; 
						} else { 
							strToken = " "; 
						}
						StringTokenizer strTokens = new StringTokenizer(strHighlightText,strToken);
						while(strTokens.hasMoreTokens()) {
							strHighlightText = (String)strTokens.nextToken();
							if(caption.toUpperCase().indexOf(strHighlightText.toUpperCase()) != -1) {								
%>
							<tr>
								<td><input type="checkbox" name="chk" value="<%=s%>" id="checkbox2" /></td>
								<td class="style7">
									<strong><%=caption%></strong>
								</td>
								<td><div align="center" class="style7">
								  <div align="left"><%=location%></div>
								</div></td>
								<td><span class="style7"><%=court%></span></td>
							</tr>
<%
								refineSearchCount += 1;
								break;
							}
						} 
					} else {
%>
								<tr>
									<td><input type="checkbox" name="chk" value="<%=s%>" id="checkbox2" /></td>
									<td class="style7">
									<strong><%=caption%></strong>
									</td>
									<td><div align="center" class="style7">
									<div align="left"><%=location%></div>
									</div></td>
									<td><span class="style7"><%=court%></span></td>
								</tr>	
<%
					}	
				}
			}		
%>
        </table>
		<%
			int intTotalMatches  = Integer.parseInt((String)session.getAttribute("teaserResultCount"));
			int intTotalPage = 0;
			int intCurrentPage = 0;
			int intRowStart = 0;
			int intRowEnd = 0;
			if(intTotalMatches!=0){
				if(intTotalMatches<=50){
					intTotalPage = 1;
				}
				else{
					intTotalPage = intTotalMatches/50;
					int intExtra = intTotalMatches%50;
					if(intExtra>0){
						intTotalPage = intTotalPage + 1;
					}
				}
				int intRowIndex = Integer.parseInt((String)request.getAttribute("strRowIndex"));
				intRowStart = intRowIndex+1;
				intRowEnd= intRowStart+49;
				if(intRowEnd>intTotalMatches){
					intRowEnd = intTotalMatches;
				}
				intCurrentPage = (intRowIndex/50) + 1;
			}
		%>

        <table width="941" border="0" id="pageViewFooter">
          <tr>
            <td width="163" class="style8" align="left">Page <%=intCurrentPage%> <input type="hidden" name="pageNumberDisplayed" value="<%=intCurrentPage%>" /></td>
            <td width="401" class="style8" align="center"><div id="matchesTitleAtFooter">
			<%
				if(request.getParameter("isHighLightText") != null && ((String)request.getParameter("isHighLightText")).equalsIgnoreCase("yes") && refineSearchCount == 0) {
			%>
			No Matches
			<%
				} else {
			%>
			Matches <%=intRowStart%>-<%= (refineSearchCount != 0 ? (intRowStart + refineSearchCount) -1: intRowEnd)%>
			<%
				}
			%>
			</div></td>
            <td width="356" class="style8" align="right"><div id="pagesLinkTitleAtFooter">
			<%
				if((intCurrentPage-1)==0){
			%>
					Prev

			<%
				} else{
			%>
				<a href="/springapp/funnel/mexico/resultsMexicanRecords.do?pageNumber=<%=intCurrentPage-1%>">Prev</a>
			<%
				}
			%>
		<%
			int intSeriesStart = 0;
			int intSeriesEnd = 0;
			for(int j=intCurrentPage;j>=0;j--){
				int k = 0;
				k = j%10;
				if(k==0){
					intSeriesStart = j;
					intSeriesEnd = intSeriesStart + 10;
					break;
				}
			}
			
			if(intSeriesEnd>intTotalPage){
					intSeriesEnd = intTotalPage;			
			}
			if(intSeriesStart==0){
				intSeriesStart = 1;
			}
		
			 
			for(int i=intSeriesStart;i<=intSeriesEnd;i++){				
				if(intCurrentPage==i){
		%>
					<strong><%=i%></strong>	|
		<%
				} else{
		%>
					<a href="/springapp/funnel/mexico/resultsMexicanRecords.do?pageNumber=<%=i%>"><%=i%></a>|
		<%
				}
			}
			if(intCurrentPage!=intTotalPage){
		%>
				<a href="/springapp/funnel/mexico/resultsMexicanRecords.do?pageNumber=<%=intCurrentPage+1%>">next</a>
		<%
			}		
		%>
          </div>
		  </td>
		  </tr>
        </table>        
        </td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
    
   	  </tr>
      <tr valign="top">
        <td id="colone2" align="left">&nbsp;</td>
      </tr>
    </table>
    <!--// main content area ends //-->

  </div>
</center>
</form:form>
<!--// footer begins //-->
<%@include file="Footer.jsp" %>
<!--// footer ends //-->
</body>
<script language="javascript">
	document.getElementById("pageNumberAtHeader").innerHTML = "Page " + document.getElementById("pageNumberDisplayed").value;
	document.getElementById("matchesTitleAtHeader").innerHTML = document.getElementById("matchesTitleAtFooter").innerHTML;
	document.getElementById("pagesLinkTitleAtHeader").innerHTML = document.getElementById("pagesLinkTitleAtFooter").innerHTML;
</script>
</html>
