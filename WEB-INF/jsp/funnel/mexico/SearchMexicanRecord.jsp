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
<title>Court Records Mexico</title>

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
.style12 {
	font-size: small;
	font-weight: bold;
	font-style: italic;
}
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
.style22 {	font-size: 0.7em;
	font-weight: bold;
}
.style23 {font-size: 0.8em; font-weight: bold; }
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
.style41 {
	font-size: medium;
	font-weight: bold;
}
.style44 {font-size: 0.8em; color: #FF0000; }
.style47 {
	font-size: 0.75em;
}
-->
</style>
</head>

<body>
<center>
<form:form method="post">
  <div id="owrap">

    <p align="left">
      <!--// masthead begins //-->
      <img src="/springapp/images/common/updated-logo.png" width="293" height="40" /><!--// main content area begins //--></p>
    <table cellpadding="0" cellspacing="0" border="0" width="960" id="maintable">
      <tr valign="top">
    		
        <!--// column one begins //--> 
          <td height="765" align="left" id="colone"><div class="wrap"><div class="innerwrap">
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
                <td><img src="/springapp/images/common/navigation-tabs_01.gif" alt="" width="167" height="35" border="0" /><a href="<c:url value="/funnel/mexico/searchMexicanRecord.do?searchType=state"/>"><img src="/springapp/images/common/navigation-tabs_02.gif" alt="" width="158" height="35" border="0" /></a><a href="<c:url value="/funnel/mexico/searchMexicanRecord.do?searchType=region"/>"><img src="/springapp/images/common/navigation-tabs_03.gif" alt="" width="155" height="35" border="0" /></a></td>
              </tr>
            </table>
            <table width="705" border="0" bordercolor="#FEF8A5" bgcolor="#FEF8A5">
              <tr>
                <td bordercolor="#FEF8A5" bgcolor="#FEF8A5" colspan="2"><label>
                  </label><strong>Search Mexico Court Records Nationwide (State and Federal)</strong>
				</td>
			  </tr>
			  <tr>
                <td width="420" height="241" bordercolor="#FEF8A5" bgcolor="#FEF8A5">
                  <table width="420" border="0">
                    <tr>
                          <td><label for="mexican_sarch_individual_name"><strong>Enter Individual or Business Name</strong></label></td>
                          <td><a href="<c:url value="/funnel/mexico/showStaticPages.do?page=searchtips"/>" class="style32">Important 
                            Search Tips</a></td>
                    </tr>
                  </table>
                  <label><br />
                  <form:input path="mexicanSearchIndividualName" cssClass="formfield" size="65" maxlength="65" />
				  <br />
                  <span class="style31">(e.g. Jose Juan Robles Martinez OR Cervezas AVC)</span></label>
                    <p class="style31">Enter a name or combination of names. Do not input commas or other punctuation. Do not use single letters or initials. Do not put company extension (Inc., LLC,  etc.).</p>
                    <p class="style31"><span class="style31">The term &quot;AND&quot; is automatically placed between the words you input. Results will include records that include all of the words you input, not necessarily in the same order.</span></p>
                      <p class="style31">&nbsp;</p>
                      <p class="style13">
                        <span class="style14">Purpose of your Search: 
                      <label> </label>
                      </span> 

                      <label> 
                      	<form:select path="mexicanSearchPurpose" cssClass="formfield">
							<form:option value="">[Choose]</form:option>
							<form:options items="${mexicanSearchPurposes}"/>		
						</form:select>
                      </label>
                    </p>
                      <p align="center" class="style13">
					  <span class="style41">
					  	<a href="<c:url value="/funnel/mexico/showStaticPages.do?page=samplereport"/>">Sample 
                        Report</a> | 
						<a href="<c:url value="/funnel/mexico/showStaticPages.do?page=coverage"/>">Coverage</a>
						<% if (!member) { %>
						 | <a href="<c:url value="/funnel/mexico/showStaticPages.do?page=prices"/>">Prices</a>
						 <% } %>
					  </span>
					  </p>
				</td>
				<td width="275" bordercolor="#FFFF99" bgcolor="#FEF8A5"><p><strong>Select  Nationwide Search Type</strong></p> 			  					<p>
						<label>
							<% if (member) { %>
								<form:radiobutton path="searchRecordType" value="PENAL-14.95" />
							<% } else { %>
								<form:radiobutton path="searchRecordType" value="PENAL-29.95" />
							<% } %>
						</label>
						Criminal Court records<strong><%= (member ? " - $14.95": "") %></strong>
					</p>
					<p>
						<label>
							<% if (member) { %>
								<form:radiobutton path="searchRecordType" value="CIVIL-14.95" />
							<% } else { %>
								<form:radiobutton path="searchRecordType" value="CIVIL-29.95" />
							<% } %>
						</label>
						Civil Court records<strong><%= (member ? " - $14.95": "") %></strong>
					</p>
					<p>
						<label>
							<% if (member) { %>
								<form:radiobutton path="searchRecordType" value="all-24.95" />
							<% } else { %>
								<form:radiobutton path="searchRecordType" value="all-49.95" />
							<% } %>
						</label>
						All court records<strong><%= (member ? " - $24.95": "") %></strong>
					</p>
					<p><label></label></p>
					<p align="center">
						<img src="/springapp/images/common/bt-start-search-small.png" alt="search" width="138" height="48" onclick="javascript:showWaitMessage(); this.enabled = false; document.forms[0].submit();" style="cursor: pointer; " />
					</p>
				</td>
			</tr>
            </table>
			<div id="hidepage" style="background-color: #FFFFFF; layer-background-color: #FFFFFF; visibility:hidden; height: 0px;"> 
				<font size="2" color="red"><b>Don't click the search button twice or click the back button. Searches can take up to 2 minutes.</b></font><br />
			</div> 
			
            <h3 class="style14">Features:</h3>
            <ul type="disc">
			  <li><strong>No hit, no fee!</strong> See how many results match your search <U>before</U> you pay.</li>
			  <li>All site pages and results are <strong>presented in English.</strong></li>
              <li>Civil records in 28 of 32 states and state criminal records in 17 of 32 states. All 32 states provide federal criminal records.</li>
              <li>Covers the 28 most populous states,  and the Federal District, which includes Mexico    City.</li>
              <li>Covers over 85% of the population, all major business centers, and all border states. See our <a href="<c:url value="/funnel/mexico/showStaticPages.do?page=coverage"/>">Coverage</a>.</li>
              <li>Do additional <strong>advanced</strong> searches on your results <strong>at no additional  charge.</strong></li>
              </ul>
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
    <table width="960" border="0">
      <tr>
        <td bgcolor="#FFFFFF"><p align="left" class="style23">Includes, but not limited to, the following types of filings:</p>
        <p align="left" class="style22">Alimony | Arrests | Assault | Bigamy | Blackmail | Burglary | Child support | Civil actions | Commercial claims | Confidence fraud | Criminal tourists | Death registration | Defamation | Divorces | DUI arrests | Estate | Federal criminal records | Firearms | Foreclosures | Indictments | Injunctions | Judgments | Judgment liens | Lease disputes | Malpractice | Manslaughter | Mortgages | Murder | Narcotics | Personal injury | Rape |   Robbery |  Slander | Smuggling | Tax appeals |    Terrorist activities |  Traffic accidents</p></td>
      </tr>
      <tr>
        <td bgcolor="#FEF8A5"><p align="left" class="style12">Accolades for SearchSystems.net:</p>
          <blockquote>
            <p align="left" class="style12"><em>&quot;One of the best&quot; - Wall Street Journal</em></p>
            <p align="left" class="style12">&quot;When I show foreign journalists this site, their jaws drop.&quot; -  Al Tompkins, Poynteronline</p>
            <p align="left" class="style12">&quot;Your website is the search engine of the future... a source that provides relief from the usual frustrations of online searching. Searchsystems.net is so consistently dependable, it feels as if you're not on this planet. It's just perfect.&quot; - Rose</p>
            <p align="left" class="style12">&quot;The one source I always mention in my presentations for private investigators and professional researchers.&quot;  - Tamara Thompson, P.I.</p>
        </blockquote></td>
      </tr>
    </table>
    </div>
</form:form>
<%@include file="Footer.jsp" %>
</center>
</body>
</html>
