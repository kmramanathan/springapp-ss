<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<% session.removeAttribute("charged");
%>
<neon:CrimeFunnel title="Confirm Purchase">
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css</jsp:attribute>
<jsp:body>
<script type="text/javascript">
function showNationalDelay()
{
	window.location.replace("NationalSecurityDelay.do");
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

	<c:if test="${searchNationalSecurity}">
		<tr><td><label><b>Your Search Request</b></label></td><td>&nbsp;</td></tr>
    	<tr><td><label>Name:</label></td><td><c:out value="${searchName}"/></td></tr>
		<tr><td><label>Price:</label></td><td>$<c:out value="${searchPrice}"/></td></tr>
		<tr><td><label>Type:</label></td><td><c:out value="${searchNationalType}"/></td></tr>
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


  <div>
<c:if test="${not empty msg}">
<p style="color:red;font-weight:bold;"><c:out value="${msg}"/></p>
</c:if>
<c:if test="${search}">
<p style="color:#003366;">
The total cost of this search is $<c:out value="${searchPrice}"/>.
</p>

<p  style="color:#003366;"> 
By performing this search, I acknowledge that I will be charged for this search even 
if there are no results.  I understand that the data from the above sources is collected 
from state, national, international sources.
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

  
  <input type="image" src="images/common/bt-make-your-secure-payment.gif" onclick="this.disabled=true; showNationalDelay(); return false;" alt="Make Payment and Start Searching" />
 </p>


  </form:form>

  <br /><br />

</div>
</td>
</tr>
</jsp:body>

</neon:CrimeFunnel>
