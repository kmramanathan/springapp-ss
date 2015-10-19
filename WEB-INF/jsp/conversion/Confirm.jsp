<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Confirm Upgrade" rightSidebar="GenericConversionSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<div id="title"></div>
              
<div id="formwrapper">

  <form:form method="post">

  <h1 style="border: 0; padding-top: 1em;">Please Confirm Your Selection</h1>

<center>
	<table>
		<tr><td><label><b>Your Account Upgrade Request</b></label></td><td>&nbsp;</td></tr>
		<tr><td><label>Your Name:</label></td><td><c:out value="${customerName}"/></td></tr>
		<tr><td><label>Your Username:</label></td><td><c:out value="${customerUsername}"/></td></tr>
		<tr><td><label>Credit Card Number</label></td><td>***${ccLast4}</td></tr>
		<tr><td><label>Payment Plan:</label></td><td><c:out value="${paymentPlan}"/></td></tr>
		<tr><td><label>Recurring Billing:</label></td><td><c:out value="${recurring ? 'Yes' : 'No'}"/></td></tr>
	</table>
</center>

  <ul>

<li>
<b>Please verify that the billing plan is correct.</b>
The first billing will take place immediately.
</li>

<li>
All use of our services must be in accordance with our 
<a target="_blank" href="${showAgreementUrl}?version=members">User Agreement</a>. By clicking
on the "Upgrade" button and paying for services, you agree to its terms. Please
read the Agreement carefully.
</li>

  </ul>


  <ul id="submit"><li>
  <input type="image" src="images/common/bt-upgrade-prem.png" alt="Upgrade" />
  </li></ul>


  </form:form>

  <br /><br />

</div>
</jsp:body>

</neon:funnelPage>
