<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Confirm Purchase" rightSidebar="SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<div id="title"></div>
              
<div id="formwrapper">

  <form:form method="post">

  <h1 style="border: 0; padding-top: 1em;">Please Confirm Your Selection</h1>

<center>
	<table>
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

	<tr><td><label>&nbsp;</label></td><td>&nbsp;</td></tr>
	<tr><td><label>Credit Card Number</label></td><td>***${ccLast4}</td></tr>

	</table>
</center>

  <ul>

<c:if test="${search}">
<li>
The total cost of this search is $<c:out value="${searchPrice}"/>.
</li>

<li> 
By performing this search, I acknowledge that I will be charged for this search even 
if there are no results.  I understand that the data from the above sources is collected 
from state repositories, counties and correctional institutions. I also understand that this 
information is considered public record and that the criminal history information reflected 
should not be considered as a 100% complete or accurate history of any individual.
</li>

<li><b>Please verify that the name and birth date are correct.</b> If the information 
you input is correct and you understand what databases will be searched, press 
continue to proceed. If you're not sure what databases will be searched, click on 
<a href="ourDatabases.do" target="_blank">Our Databases</a> 
to review the available criminal record databases. Then click on "Start Searching" to begin 
your search. 
</li>
<li><b>Your card will be charged for this single search. If you have a Flat Rate
plan, and wish to perform a search of that database at no charge, you need to
<a href="/springapp/flatrate/FRLogin.do">go here</a> instead.</b>
</li>
</c:if>

<c:if test="${signup}">
<li>
<b>Please verify that the billing plan is correct.</b>
The first billing will take place immediately.
</li>

<li>
All use of our services must be in accordance with our 
<a target="_blank" href="${showAgreementUrl}?version=members">User Agreement</a>. By clicking
on the "Make Payment" button and paying for services, you agree to its terms. Please
read the Agreement carefully.
</li>

</c:if>

  </ul>


  <ul id="submit"><li>
  <input type="image" src="images/common/bt-make-your-secure-payment.gif" alt="Make Payment and Start Searching" />
  </li></ul>


  </form:form>

  <br /><br />

</div>
</jsp:body>

</neon:funnelPage>
