<%@page language="java" import="java.io.*,springapp.web.funnel.mexico.SearchMexicanRecordForm.SearchMexicanRecordFormCommand" %>
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
String Name = ((SearchMexicanRecordFormCommand)session.getAttribute("searchMexicanRecordFormCommand")).getMexicanSearchIndividualName();
String strCnt =(String)session.getAttribute("teaserResultCount");			
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>teaser - no records found</title>

<meta name="description" content="description here" />
<meta name="keywords" content="keywords here" />
<meta http-equiv="content-language" content="en" />

<link href="/springapp/css/mexico.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/JavaScript" src="/springapp/javascript/common.js"></script>
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
.style22 {	font-size: 0.7em;
	font-weight: bold;
}
.style23 {font-size: 0.8em; font-weight: bold; }
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
.style40 {color: #000000}
.style41 {font-size: 0.9em}
.style42 {font-size: 1em; }
.style47 {
	font-size: 0.75em;
}
-->
</style>
</head>

<body><center>
  <div id="owrap">

    <p align="left">
      <!--// masthead begins //-->
      <img src="/springapp/images/common/updated-logo.png" width="293" height="40" /><!--// main content area begins //--></p>
    <table cellpadding="0" cellspacing="0" border="0" width="960" id="maintable">
      <tr valign="top">
    		
        <!--// column one begins //--> 
          <td width="704" height="765" align="left" id="colone"><div class="wrap"><div class="innerwrap">
            <h2 class="style1 style34"><span class="style37">Search Mexico</span><span class="style36">Court Records</span></h2>
            <p class="style39"><strong>Does your prospective employee or business associate have a record in Mexico?
</strong></p>
            <p class="style39"><strong>For the first time ever, you can do background, criminal and civil court record
searches on anyone who has lived in or visited Mexico.</strong></p>
            <p>&nbsp;</p>
            <table width="682" border="1" bordercolor="#000000" bgcolor="#FEF8A5">
              <tr>
                <td width="672" height="265" bordercolor="#FEF8A5" bgcolor="#FEF8A5"><label>
                  </label>
                  <h2 align="center"><strong>NO RESULTS FOUND</strong></h2>
                  <h2 align="center">Based on your query for the search  term:</h2>
                  <h3 align="center"><strong><%=Name%></strong></h3>
                  <h3 align="center" class="style40">We found no matching records.</h3>
                  <h3 align="center" class="style40">To refine your search, please click here:</h3>
                  <p align="center" class="style13"><label></label>
                    <a href="<c:url value="/funnel/mexico/searchMexicanRecord.do"/>"><img src="/springapp/images/common/bt-refine-search.png" alt="refine search" width="128" height="49" /></a></p>
                  <h4 align="center" class="style42">&nbsp;</h4>
                  <p align="center" class="style13"><span class="style41">Court indexes are updated regularly from court calendars. Results  include party names, date, case number, court name, and jurisdiction.  Most indexes, except federal criminal, include cause of action (case  type). Results do not include court dockets or documents.</span></p></td>
                </tr>
            </table>
            <h3 class="style14">Features:</h3>
            <ul type="disc">
			  <li><strong>No hit, no fee!</strong> See how many results match your search <U>before</U> you pay.</li>
			  <li>All site pages and results are <strong>presented in English.</strong></li>
              <li>Civil records in 24 of 32 states and criminal in 17. All 32 states provide federal criminal records.</li>
              <li>Covers the 24 most populous states,  and the Federal District, which includes Mexico    City.</li>
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
              <h5align="center" class="style29"><a href="http://www.ftc.gov/os/statutes/fcra.htm" target="_blank">FCRA</a> | <a href="${showAgreementUrl}?version=mexicanfunnel"  target="_blank">User Agreement</a></h5>
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
<%@include file="Footer.jsp" %>
</center>
</body>
</html>
