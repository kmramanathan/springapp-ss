<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@ page import="java.util.*" %>
<%@ page language="java" import="springapp.web.corporate.xmlParsingHelper.*" %>
<% 
	boolean member=false;
	String username=(String)session.getAttribute("username");
	if(username != null)
	{
		member=true;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="springapp.web.corporate.xmlParsingHelper"%><html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Results</title>
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
	
<script type="text/JavaScript" src="/springapp/javascript/common.js"></script>

<script>
	
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
  
</head>
  
<body>
<form:form method="post">
  <div class="bodywrap">

    <center>
      <div id="owrap">
        
        <!--// masthead begins //-->
        <div id="masthead">
           <%@include file="logo.jsp" %>
              <p>  <%@include file="FindOutBanner.jsp" %> </p>
              <table width="926" border="0">
                <tr>
                  <td width="780" height="58"><h1 align="left">Nationwide Corporations Search Results</h1>
                  </td>
                  <td width="136"><p>&nbsp;</p>
                  </td>
                </tr>
              </table>
              <table width="914" border="0">
                <tr>
                  <td width="172" height="29"><div align="right"><strong>Search Term:</strong></div></td>
                  <td width="448"><div align="left"><strong><c:out value="${searchName}"/></strong></div></td>
                  <td width="280">&nbsp;</td>
                </tr>
                <tr>
                  <td><div align="center"><img src="/springapp/images/common/bt-view-details.png" alt="view details" onclick="document.forms[0].submit();" width="129" height="49"  border="0"/></div></td>
                  <td><div align="left">Check boxes below and click View  Details button to view details and print results from this page. check the page </div></td>
                  <td>&nbsp;</td>
                </tr>
              </table>
              <p>
			  <% if (request.getParameter("errorNoItemSelected") != null && ((String)request.getParameter("errorNoItemSelected")).equalsIgnoreCase("yes")) { %>
				<font color="red"><b>Please correct the following errors:</b></font><br/>
			<p class="style39">
				<font color="red">No item selected to view the details.</font>
            </p>
			<% } %>
			
			
			
			<%    
				Hashtable searchResult=(Hashtable)session.getAttribute("searchResult");
				Enumeration keys=searchResult.keys();
				String chkKey="";
				
			%>			
			
              <table width="924" border="0">
                <tr>
                  <td width="526"><div align="left"></div></td>
                  <td width="388"><div align="right" class="style13"></div></td>
                </tr>
              </table>
              <table width="924" border="1">
                <tr>
                  <td width="105" bordercolor="#666666">
                      <label>
                      <strong>Select All</strong>
                      <label>
                      <input type="checkbox" name="chkbxViewAll" onClick="javascript: if (this.checked) setAllCheckBoxes('chk',true); else setAllCheckBoxes('chk',false);" title="Check/Uncheck All" />
                      </label>
                  </td>
                  <td width="413" bordercolor="#666666"><div align="left"><strong>Corporate Name</strong></div></td>
                  <td width="190" bordercolor="#666666"><div align="left"><strong>Location</strong></div></td>
                  <td width="188" bordercolor="#666666"><div align="left"><strong>Filing Date</strong></div></td>
                </tr>
				
			<%	while(keys.hasMoreElements())
				{
					chkKey=(String)keys.nextElement();
					xmlParsingHelper xp=(xmlParsingHelper)searchResult.get(chkKey);
			%>	
                <tr>
                  <td bordercolor="#666666">
                      <label>
                      <div align="right">
                     <input type="checkbox" name="chk" value="<%=chkKey%>" id="checkbox13" />
                      </div>
                  </td>
                  <td bordercolor="#666666"><div align="left"><%=xp.getName()%></div></td>
                  <td bordercolor="#666666"><div align="left"><%=xp.getIncorporationState()%></div></td>
                  <td bordercolor="#666666"><div align="left"><%=xp.getFilingDate()%></div></td>
                </tr>
			<% } %>
				
              </table>
              <p>&nbsp;</p>
            </div>
        </div>
        <!--// masthead ends //-->
        


                        <!--// content begins //-->

<!-- CONTENT GOES HERE -->
<div>
  <%@include file="BottomBanner.jsp" %>
</div>


        <!--// content ends //-->

  </div>

    <!--// mastfoot begins //-->
    <%@include file="Footer.jsp" %>
    <!--// mastfoot ends //-->

</form:form>  
</body>
</html>
