<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Enter Search" rightSidebar="BJLSearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<c:url var="astrixRequiredGif" value="/img/required.gif"/>
<c:url var="astrixGif" value="/img/astrix.gif"/>

<c:choose>

<c:when test="${searchType eq 'name'}">
	<c:set var="searchBy">Search by Name</c:set>
	<c:set var="subhead">Search by NAME ($12.95)</c:set>
	<c:set var="blurb">
	<p>Search our bankruptcy,  judgment, and tax lien database by name and state or nationwide. 
	<strong>All</strong> state bankruptcy courts are included; and information on judgments and tax  
	liens is available from most states. <br />
	    <br />
	<strong>Tip:</strong> For best results use the first and last name only and select a  
	state. If it's a common name use the name and address search page and add the  
	zip code or city. </p>
	</c:set>
</c:when>

<c:when test="${searchType eq 'address'}">
	<c:set var="searchBy">Search by Name and State, City or ZIP Code</c:set>
	<c:set var="subhead">Search by NAME and STATE, CITY or ZIP CODE ($12.95)</c:set>
	<c:set var="blurb">
	<p>Search our bankruptcy,  judgment, and tax lien database by name and state or nationwide. 
	<strong>All</strong> state bankruptcy courts are included; and information on judgments and tax  
	liens is available from most states. <br />
	    <br />
	<strong>IMPORTANT TIPS! PLEASE READ!</strong> Best results are found on our Search by Name 
	page. This page is for searches on common names. <strong>For best results omit the middle name 
	or initial!</strong> </p>
	</c:set>
</c:when>

<c:when test="${searchType eq 'ssn'}">
	<c:set var="searchBy">Search by SSN / Tax ID</c:set>
	<c:set var="subhead">Search by SOCIAL SECURITY NUMBER or TAX ID ($12.95)</c:set>
	<c:set var="blurb">
	<p>Search for <strong>Bankruptcies</strong> and <strong>Tax Liens</strong> by Social Security 
	Number. <strong>All</strong> state bankruptcy courts are included; and  information on tax liens 
	is available from most states.  </p>
	<p><span class="style2">Judgments are <strong>NOT</strong> available by SSN or Tax ID. </span><br />
	  <br />
	<strong>Caution:  Search results will only include records that contain a  Social Security number. 
	Since judgments aren't normally referenced by  SSN or Tax ID, then you won't find those records 
	here. If a bankruptcy  or tax lien record doesn't contain a Social Security number, then it  
	won't appear in your result. BEST results are found on the &quot;Search by  Name&quot; page.</strong> </p>
	</c:set>
</c:when>

<c:otherwise></c:otherwise>

</c:choose>

<style type="text/css">
.style1 { color: #003366; font-weight: bold; }
.style2 { color: #FF0000 }
ul.radiobuttons { list-style:none; margin:0; padding:0; }
</style>

<div class="wrap">

<h2 class="style1">Bankruptcy, Judgment &amp; Tax Lien Searches</h2>
<h4 class="style1">${subhead}</h4>
${blurb}
<p><img src="${astrixRequiredGif}" alt="* denotes a required field" /> </p>
          
<form:form method="post">
<form:hidden path="bjlSearchType" />
<table width="602" cellspacing="1">
<tbody>
    <tr>
      <th colspan="2"><h3>Search by ${searchBy}</h3></th>
    </tr>

<tr>
<form:errors path="*" element="div" id="error" cssClass="error-box" />
</tr>

<c:if test="${searchType eq 'ssn'}">
    <tr>
      <td width="37%"><label for="bjlSsnTaxId">SSN / Tax ID:</label></td>
      <td width="63%"><form:input path="bjlSsnTaxId" size="30" maxlength="30" /><img src="${astrixGif}" alt="Required field" /></td>
    </tr>
</c:if>

<c:if test="${searchType ne 'ssn'}">
    <tr>
      <td width="37%"><label for="bjl_name">Name:</label></td>
      <td width="63%"><form:input path="bjlName" size="30" maxlength="50" /><img src="${astrixGif}" alt="Required field" /></td>
    </tr>

    <tr>
      <td>&nbsp;</td>
      <td><strong>Example:</strong> Smith John or Jones Plumbing</td>
    </tr>
</c:if>

<tr><td>&nbsp;</td></tr>

	<tr>
      <td valign="top"><label for="bjlNameType">Name Type:</label></td>
	  <td><ul class="radiobuttons"><form:radiobuttons path="bjlNameType" items="${bjlNameTypes}" element="li"/></ul></td>
    </tr>
<tr><td>&nbsp;</td></tr>

<c:if test="${searchType eq 'address'}">
	<tr>
      <td valign="top"><label for="bjlDefendantCity">Defendant City:</label></td>
	  <td><form:input path="bjlDefendantCity"/></td>
    </tr>
<tr><td>&nbsp;</td></tr>
</c:if>	

    <tr>
      <td nowrap="nowrap"><label for="bjlDefendantState">Defendant State:</label></td>
      <td>
<form:select path="bjlDefendantState" cssClass="formfield">
<form:option value="-">Select</form:option>
<form:options items="${usStates}"/>
</form:select>
	  </td>
    </tr>
<tr><td>&nbsp;</td></tr>

<c:if test="${searchType eq 'address'}">
	<tr>
      <td valign="top"><label for="bjlDefendantZip">Defendant ZIP Code:</label></td>
	  <td><form:input path="bjlDefendantZip"/></td>
    </tr>
<tr><td>&nbsp;</td></tr>
</c:if>

<c:if test="${searchType ne 'address'}">
    <tr>
      <td valign="top"><label for="bjlCaseType">Case Type:</label></td>
	  <td><ul class="radiobuttons"><form:radiobuttons path="bjlCaseType" items="${bjlCaseTypes}" element="li" /></ul></td>
    </tr>
</c:if>

</tbody>
</table>

<p align="center">
<input type="image" src="images/common/bt-continue.png" alt="continue" width="149" height="60" /></p>

</form:form>

  <p>
    To see a list of the states  and jurisdictions included in this database, <a href="data-sources-bjl.htm">click here</a>. This  search is limited to 500 results. 
    All information retrieved from or through SearchSystems.net must be utilized in  accordance with the User Agreement and all applicable state and federal laws,  including the <a href="http://www.ftc.gov/os/statutes/fcra.htm">Fair Credit  Reporting Act</a>; any violation of these will be grounds for immediate  termination of your account without notice. <br />
    <br />
    <strong>DISCLAIMER:</strong> No warranty of any type is provided as to the quality  or accuracy of the information obtained from or through SearchSystems.net, and  any reliance on that information is solely at your own risk and responsibility.  Information contained herein is derived solely from public records, which may  not be 100 percent accurate or complete. Users should not assume that this data  provides a complete or accurate record of any person's bankruptcy, judgment, or  tax lien history. Please see SearchSystems' <a href="http://premium.searchsystems.net/agreement-funnel.php">User Agreement</a> for  all applicable terms and conditions. <br />
    <br />
    Pacific Information Resources, Inc. dba Search Systems assumes no liability for  any claims for damages arising from the use of this data beyond the actual cost  of the searches performed.</p>

</div>


</jsp:body>

</neon:funnelPage>