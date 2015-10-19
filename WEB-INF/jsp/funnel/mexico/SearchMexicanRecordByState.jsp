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
<title>search by state(s)</title>

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
.style44 {font-size: 0.8em; color: #FF0000; }
.style45 {
	font-size: 0.9em;
}
.style46 {
	color: #000000;
	font-weight: bold;
}
.style47 {
	font-size: 0.75em;
}
.style48 {font-weight: bold}
-->
</style>
</head>

<body><center>
<form:form method="post">
  <div id="owrap">

    <p align="left">
      <!--// masthead begins //-->
      <img src="/springapp/images/common/updated-logo.png" width="293" height="40" /><!--// main content area begins //--></p>
    <table cellpadding="0" cellspacing="0" border="0" width="960" id="maintable">
      <tr valign="top">
    		
        <!--// column one begins //--> 
          <td height="1043" align="left" id="colone"><div class="wrap"><div class="innerwrap">
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
                <td><a href="<c:url value="/funnel/mexico/searchMexicanRecord.do?searchType=nation"/>"><img src="/springapp/images/common/navigation-tabs2_01.gif" alt="" width="167" height="35" border="0" /></a><img src="/springapp/images/common/navigation-tabs2_02.gif" alt="" width="158" height="35" border="0" /><a href="<c:url value="/funnel/mexico/searchMexicanRecord.do?searchType=region"/>"><img src="/springapp/images/common/navigation-tabs2_03.gif" alt="" width="155" height="35" border="0" /></a></td>
              </tr>
            </table>
            <table width="704" height="319" border="0" bordercolor="#FEF8A5" bgcolor="#FEF8A5">
              <tr>
                <td width="430" height="315" bordercolor="#FEF8A5" bgcolor="#FEF8A5"><label> </label>
                    <h2><strong>Search By State(s)</strong></h2>
                    <p class="style45">Select any 6 states to search below, or click Seach by Region  to choose pre-selected states in regional blocks.</p>
                    <table width="446" border="0">
                      <tr>
                          <td><strong>Enter Individual or Business Name</strong></td>
                          <td><a href="<c:url value="/funnel/mexico/showStaticPages.do?page=searchtips"/>" class="style32">Important 
                            Search Tips</a></td>
                      </tr>
                    </table>
                  <label><br />
                    <form:input path="mexicanSearchIndividualName" cssClass="formfield" size="65" maxlength="65" />
                    <br />
                    <span class="style31">(e.g. Jose Juan Robles Martinez OR Cervezas AVC)</span></label>
                    <p class="style31">Enter a name or combination of names. Do not input commas or other punctuation. Do not use single letters or initials. Do not put company extension (Inc., LLC,  etc.).</p>
                  <p class="style31">The term &quot;AND&quot; is automatically placed between the words you input. Results will include records that include all of the words you input, not necessarily in the same order.</p>
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
                  </p></td>
				  
				<td width="291" bordercolor="#FFFF99" bgcolor="#FEF8A5"><h2 align="center"><strong>Select   Search Type</strong></h2>
                  	<p align="center"><strong>Results from up to 6 states</strong></p>
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
				  	<p>&nbsp; </p>
                   	<p align="center"><span class="style48">
					<a href="<c:url value="/funnel/mexico/showStaticPages.do?page=samplereport"/>">Sample 
                        Report</a> | 
					<a href="<c:url value="/funnel/mexico/showStaticPages.do?page=coverage"/>">Coverage</a><% if (!member) { %> | <a href="<c:url value="/funnel/mexico/showStaticPages.do?page=prices"/>">Prices</a><% } %></span>
					</p></td>
			</tr>
			</table>
            <table width="704" height="443" border="0" bordercolor="#FEF8A5" bgcolor="#FEF8A5">
              <tr>
                <td height="439">
				<h3 class="style46">Select up to 6 States to search:</h3>
                <div>
                    <table width="690" border="0">
                      <tr>
                        <td width="304" height="284"><p>
                          <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Aguascalientes" />
						  </label>
                          Aguascalientes</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Baja California" />
							</label>
                            Baja California North *</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Baja California Sur" />
							</label>
                            Baja California South *
                            <label></label>
                          </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Campeche" />
							</label>
Campeche *</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Chihuahua" />
							</label>
                            Chihuahua</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Chiapas" />
							</label>
                            Chiapas</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Coahuila" />
							</label>
                            Coahuila </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Colima" />
							</label>
                            Colima </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Federal District" />
							</label>
                            Federal District *</p>
                          <p>
                            <label></label>
                          (includes Mexico City)</p></td>
                        <td width="201"><p>
                          <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Durango" />
</label>
Durango
</p>
                          <p>
                            <label>
  								<form:checkbox path="mexicanSearchStates" cssClass="formfield" value="State of Mexico" />
                                </label>
                          State of Mexico *
                            <label></label>
                          </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Guanajuato" />
							</label>
                            Guanajuato *</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Guerrero" />
							</label>
Guerrero *</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Hidalgo" />
							</label>
                            Hidalgo *</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Jalisco" />
							</label>
                            Jalisco *</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Michoacan" />
							</label>
                            Michoacan </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Morelos" />
							</label>
                            Morelos </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Nuevo Leon" />
							</label>
                            Nuevo Leon </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Queretaro" />
							</label>
                          Queretaro </p></td>
                        <td width="171"><p>
                          <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Quintana Roo" />
						  </label>
                          Quintana Roo </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Sinaloa" />
							</label>
                            Sinaloa *</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="San Luis Potosi" />
							</label>
                            San Luis Potosi </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Sonora" />
							</label>
                            Sonora </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Tabasco" />
							</label>
                            Tabasco </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Tamaulipas" />
							</label>
                            Tamaulipas </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Veracruz" />
							</label>
                            Veracruz </p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Yucatan" />
							</label>
                            Yucatan *</p>
                          <p>
                            <label>
                            <form:checkbox path="mexicanSearchStates" cssClass="formfield" value="Zacatecas" />
							</label>
                          Zacatecas </p></td>
                      </tr>
                      <tr>
                        <td><p class="style47">* Includes state civil records and federal criminal records. State criminal records are not included.</p></td>
                        <td><div align="center">
                            <!--<input type="image" src="/springapp/images/common/bt-start-search-small.png" alt="start search" width="138" height="48" />--><img src="/springapp/images/common/bt-start-search-small.png" alt="search" width="138" height="48" onclick="javascript:showWaitMessage(); this.enabled = false; document.forms[0].submit();" style="cursor: pointer; " /></div></td>
                        <td>&nbsp;</td>
                      </tr>
                    </table>
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
