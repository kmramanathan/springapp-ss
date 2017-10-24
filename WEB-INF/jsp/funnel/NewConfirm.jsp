<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<% session.removeAttribute("charged");
%>
<neon:CrimeFunnel title="Confirm Purchase">
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css</jsp:attribute>
<jsp:body>
<script type="text/javascript">
function showCriminalDelay()
{
	window.location.replace('${searchSSN ne null && fn:length(searchSSN) == 9 ? "AliasStateDelay.do" : "CriminalStateDelay.do"}');
}
function showCriminalNationalDelay()
{
	window.location.replace('${searchSSN ne null && fn:length(searchSSN) == 9 ? "AliasNationalDelay.do" : "CriminalNationalDelay.do"}');
}
</script>

<tr>
<td>

<div id="title"></div>
              
<div id="formwrapper">

  <form:form method="post">

  <h1 style="border: 0; padding-top: 1em;"><strong>Please Confirm Your Selection</strong></h1>


	<table style="margin-left:100px;">
	<c:if test="${signup}">
		<tr><td><label><b>Your New Account Request</b></label></td><td>&nbsp;</td></tr>
		<tr><td><label>Your Name:</label></td><td><c:out value="${customerName}"/></td></tr>
		<tr><td><label>Your Username:</label></td><td><c:out value="${customerUsername}"/></td></tr>
		<tr><td><label>Payment Plan:</label></td><td><c:out value="${paymentPlan}"/></td></tr>
		<tr><td><label>Recurring Billing:</label></td><td><c:out value="${recurring}"/></td></tr>
	</c:if>

	<c:if test="${searchCriminal}">
		<tr><td><label><b>Your Search Request</b></label></td><td>&nbsp;</td></tr>
    	<tr><td><label>Name:</label></td><td><c:out value="${searchName}"/></td></tr>
		<tr><td><label>Date of Birth:</label></td><td><c:out value="${searchDOB}"/></td></tr>
		<!--<c:if test="${searchSSN ne null && fn:length(searchSSN) == 9}">
			<tr><td><label>SSN:</label></td><td><c:out value="${searchSSN}"/></td></tr>
		</c:if>-->
		<tr><td><label>Type:</label></td><td><c:out value="${searchType}"/></td></tr>
		<tr><td><label>Price:</label></td><td>$<c:out value="${searchPrice}"/></td></tr>
	</c:if>

	<c:if test="${searchBJL}">
		<tr><td><label><b>Your Search Request</b></label></td><td>&nbsp;</td></tr>
		<tr><td><label>Search Type:</label></td><td><c:out value="${searchType}"/></td></tr>
		<c:if test="${!empty searchName}">
    		<tr><td><label>Name:</label></td><td><c:out value="${searchName}"/></td></tr>
		</c:if>
		<c:if test="${!empty searchSsnTaxId}">
    		<tr><td><label>SSN / Tax ID:</label></td><td><c:out value="${searchSsnTaxId}"/></td></tr>
		</c:if>
		<tr><td><label>Price:</label></td><td>$<c:out value="${searchPrice}"/></td></tr>
	</c:if>

	<tr><td><label>Credit Card Number</label></td><td>***${ccLast4}</td></tr>

	</table>


  <div>
<c:if test="${msg eq 'true'}">
<p style="color:red;font-weight:bold;">NOTE: We will submit your search with a Year of Birth only, as the State does not provide a full date of birth in all of their records. This will ensure that you receive the best results possible.</p>
</c:if>
<c:if test="${search}">
<p style="color:#003366;">
The total cost of this search is $<c:out value="${searchPrice}"/>.
</p>

<p  style="color:#003366;"> 
By performing this search, I acknowledge that I will be charged for this search even 
if there are no results.  I understand that the data from the above sources is collected 
from state repositories, counties and correctional institutions. I also understand that this 
information is considered public record and that the criminal history information reflected 
should not be considered as a 100% complete or accurate history of any individual.
</p>

<p style="color:#003366;"><b>Please verify that the name and birth date are correct.</b> If the information 
you input is correct and you understand what databases will be searched, press 
continue to proceed. If you're not sure what databases will be searched, click on 
<a href="ourDatabases.do" target="_blank">Our Databases</a> 
to review the available criminal record databases. Then click on "Start Searching" to begin 
your search. 
</p>
</c:if>

<c:if test="${signup}">
<p style="color:#003366;">
<b>Please verify that the billing plan is correct.</b>
The first billing will take place immediately.
</p>

<p style="color:#003366;">
All use of our services must be in accordance with our 
<a target="_blank" href="${showAgreementUrl}?version=members">User Agreement</a>. By clicking
on the "Make Payment" button and paying for services, you agree to its terms. Please
read the Agreement carefully.
</p>

</c:if>

  </div>


  <p>

  <c:choose>
  <c:when test="${NationWide}">
  <input type="image" src="images/common/bt-make-your-secure-payment.gif" onclick="this.disabled=true; showCriminalNationalDelay(); return false;" alt="Make Payment and Start Searching" />
  
  </c:when>
  <c:otherwise>
  <input type="image" src="images/common/bt-make-your-secure-payment.gif" onclick="this.disabled=true; showCriminalDelay(); return false;" alt="Make Payment and Start Searching" />
  </c:otherwise>
  </c:choose>
 </p>


  </form:form>

  <br /><br />

</div>
</td>
</tr>
</jsp:body>

</neon:CrimeFunnel>
