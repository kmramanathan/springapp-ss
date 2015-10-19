<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<% 
	String username=(String)session.getAttribute("userName");
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
<title>search by region</title>

<meta name="description" content="description here" />
<meta name="keywords" content="keywords here" />
<meta http-equiv="content-language" content="en" />

<link href="/springapp/css/mexico.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/JavaScript" src="/springapp/javascript/common.js"></script>
<script language="JavaScript"> 
	<!-- 
		function showWaitMessage() { 
			if (document.getElementById) { // DOM3 = IE5, NS6 
				document.getElementById('hidepage').style.visibility = 'visible'; 
				document.getElementById('hidepage').style.height = '10px';
			} else { 
				if (document.layers) { // Netscape 4 
					document.hidepage.visibility = 'show';
					document.getElementById('hidepage').style.height = '10px'; 
				} else { // IE 4 
					document.all.hidepage.style.visibility = 'visible'; 
					document.getElementById('hidepage').style.height = '10px';
				} 
			} 
		} 
	// End --> 
</script> 
<style type="text/css">
<!--
.style13 {font-size: 0.8em}
.style14 {
	color: #000000;
	font-weight: bold;
}
.style27 {
	font-size: 1.5em;
	font-weight: bold;
	color: #FF0000;
}
.style29 {	color: #003366;
	font-weight: bold;
}
.style31 {
	font-size: x-small;
}
.style32 {
	color: #003399;
	font-weight: bold;
	font-size: small;
}
.style34 {color: #FF0000}
.style36 {
	color: #006847;
	font-weight: bold;
	font-family: Tahoma;
}
.style37 {
	font-family: Tahoma;
	font-weight: bold;
}
.style38 {
	color: #FF0000;
	font-size: 1em;
	font-weight: bold;
}
.style39 {font-size: 1.0em}
.style40 {
	font-weight: bold;
	font-size: medium;
}
.style41 {
	color: #000000;
	font-weight: bold;
}
.style43 {font-size: 9em}
.style44 {font-size: 0.8em; color: #FF0000; }
.style47 {
	font-size: 0.75em;
}
-->
</style>
</head>

<body><center>

<form:form method="post">
<input name="page" type="hidden" value="region" />

  <div id="owrap">

    <p align="left">
      <!--// masthead begins //-->
      <img src="/springapp/images/common/updated-logo.png" width="293" height="40" /><!--// main content area begins //--></p>
    <table cellpadding="0" cellspacing="0" border="0" width="960" id="maintable">
      <tr valign="top">
    		
        <!--// column one begins //--> 
          <td height="1073" align="left" id="colone"><div class="wrap"><div class="innerwrap">
            <h2 class="style1 style34"><span class="style37">Search Mexico</span><span class="style36">Court Records</span></h2>
            <p class="style39"><strong>Does your prospective employee or business associate have a record in Mexico?
</strong></p>
            <p class="style39"><strong>For the first time ever, you can do background, criminal and civil court record
searches on anyone who has lived in or visited Mexico.</strong></p>
            <p style="color:red;font-size:0.9em">
			<form:errors path="*">
				<b>Please correct the following errors:</b>
			</form:errors>
			<form:errors path="*" element="div" id="error" cssClass="error-box" />
			</p>
           
            <table id="Table_01" width="490" height="35" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td><a href="<c:url value="/funnel/mexico/searchMexicanRecord.do?searchType=nation"/>"><img src="/springapp/images/common/navigation-tabs3_01.gif" alt="" width="174" height="35" border="0" /></a><a href="<c:url value="/funnel/mexico/searchMexicanRecord.do?searchType=state"/>"><img src="/springapp/images/common/navigation-tabs3_02.gif" alt="" width="151" height="35" border="0" /></a><img src="/springapp/images/common/navigation-tabs3_03.gif" alt="" width="155" height="35" border="0" /></td>
              </tr>
            </table>
            <table width="704" height="252" border="0" bordercolor="#FEF8A5" bgcolor="#FEF8A5">
              <tr>
                <td width="430" height="248" bordercolor="#FEF8A5" bgcolor="#FEF8A5"><label>
                  </label>
                  <table width="429" border="0">
                    <tr>
                      <td><strong>Enter Individual or Business Name</strong></td>
                      <td><a href="<c:url value="/funnel/mexico/showStaticPages.do?page=searchtips"/>" class="style32">Important Search Tips</a></td>
                    </tr>
                  </table>
                  <label><br />
                  <form:input path="mexicanSearchIndividualName" cssClass="formfield" size="65" maxlength="65" />
                  <br />
                  <span class="style31">(e.g. Jose Juan Robles Martinez OR Cervezas AVC)</span></label>
                  <p class="style31">Enter a name or combination of names. Do not input commas or other punctuation. Do not use single letters or initials. Do not put company extension (Inc., LLC,  etc.).</p>
                    <p class="style31"><span class="style31">The term &quot;AND&quot; is automatically placed between the words you input. Results will include records that include all of the words you input, not necessarily in the same order.</span></p>
                    <p class="style31">&nbsp;</p>
                  <p class="style13"><span class="style14">Purpose of your Search: 
                      <label> </label>
                      </span>
                    <label>
                    	<form:select path="mexicanSearchPurpose" cssClass="formfield">
							<form:option value="">[Choose]</form:option>
							<form:options items="${mexicanSearchPurposes}"/>		
						</form:select>
                    </label>
</p>
                  <p align="center" class="style13"><span class="style40"><a href="<c:url value="/funnel/mexico/showStaticPages.do?page=samplereport"/>">Sample Report</a> | <a href="<c:url value="/funnel/mexico/showStaticPages.do?page=coverage"/>">Coverage</a>
				  <% if (!member) { %>
						 | <a href="<c:url value="/funnel/mexico/showStaticPages.do?page=prices"/>">Prices</a>
				  <% } %>
				  </span></p></td>
                
				<td width="264" bordercolor="#FFFF99" bgcolor="#FEF8A5"><h2 align="center"><strong>Select   Search Type</strong></h2>
					<p align="center"><strong>Results from  chosen region</strong></p>
					<p>
						<label>
							<% if (member) { %>
								<form:radiobutton path="searchRecordType" value="PENAL-12.95" />
							<% } else { %>
								<form:radiobutton path="searchRecordType" value="PENAL-24.95" />
							<% } %>
						</label>
						Criminal Court records<strong><%= (member ? " - $12.95": "") %></strong>
					</p>
					<p>
						<label>
							<% if (member) { %>
								<form:radiobutton path="searchRecordType" value="CIVIL-12.95" />
							<% } else { %>
								<form:radiobutton path="searchRecordType" value="CIVIL-24.95" />
							<% } %>
						</label>
						Civil Court records<strong><%= (member ? " - $12.95": "") %></strong>
					</p>
					<p>
						<label>
							<% if (member) { %>
								<form:radiobutton path="searchRecordType" value="all-19.95" />
							<% } else { %>
								<form:radiobutton path="searchRecordType" value="all-34.95" />
							<% } %>
						</label>
						All court records<strong><%= (member ? " - $19.95": "") %></strong>
					</p>
					<p>
						<label></label>
					</p>                 
				</td> 
			</tr>
            </table>
            <table width="704" height="540" border="0" bordercolor="#FEF8A5" bgcolor="#FEF8A5">
              <tr>
                <td height="536"><div align="center">
                  <h3 align="left"><span class="style41">Select One Region Below</span></h3>
                  <p align="left">
                    <label>
                    	<form:select path="mexicanSearchRegion" cssClass="formfield">
							<form:options items="${mexicanSearchRegions}"/>
							<form:option value="">[Choose one]</form:option>		
						</form:select>
					</label></p>
                  <p align="left" class="style41">&nbsp;</p>
                  <p><img src="/springapp/images/common/mex-map-regions.png" alt="mexico map" width="654" height="450" /></p>
                  <p>
				  <!--<input type="image" src="/springapp/images/common/bt-start-search-small.png" alt="start search" width="138" height="48" /> -->
				  <img src="/springapp/images/common/bt-start-search-small.png" alt="search" width="138" height="48" onclick="javascript:showWaitMessage(); this.enabled = false; document.forms[0].submit();" style="cursor: pointer; " />
				  </p>
                </div></td>
              </tr>
            </table>
			<div id="hidepage" style="background-color: #FFFFFF; layer-background-color: #FFFFFF; visibility:hidden; height: 0px;"> 
				<font size="2" color="red"><b>Don't click the search button twice or click the back button. Searches can take up to 2 minutes.</b></font><br />
			</div> 
            </div>
          </div></td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
         <td width="255" align="left" id="coltwo"><p align="center"><img src="/springapp/images/common/MexFlag-small.png" width="150" height="100" /></p>
            <div id="photo">
              <p class="style38">Did you know?</p>
              <p align="justify">There are as many as 20 Million U.S. residents born in Mexico, and over 20 Million persons of Mexican heritage in Arizona, California, Illinois, New Mexico, and Texas alone.</p>
              <p align="justify">There are over 250 Million individual border crossings each year between the U.S. and Mexico.</p>
			  
			  <% if(!member) { %>
              <a href="<c:url value="https://www.searchsystems.net/login.php" />"><img src="/springapp/images/common/already-member.png" alt="already member" width="180" height="70" /></a>
			   <% } %>
              <h5 align="center" class="style29"><a href="http://www.ftc.gov/os/statutes/fcra.htm" target="_blank">FCRA</a> | <a href="${showAgreementUrl}?version=mexicanfunnel"  target="_blank">User Agreement</a></h5>
              <h3><strong>Trusted and Secure</strong></h3>
              <p><img src="/springapp/images/common/SSL-bbb-thawte.jpg" alt="ssl thawte bb" width="150" height="120" /></p>
        </div>        </td>
        <!--// column two begins //--> 
    </tr></table>
    <!--// footer ends //-->
</div>
</form:form>
<!--// footer begins //-->
<%@include file="Footer.jsp" %>
<!--// footer ends //-->
</center>
</body>
</html>
