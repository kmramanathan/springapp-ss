<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>

<neon:NewFunnel title="Donation Failed">
	<jsp:attribute name="stylesheet">new-funnel.css</jsp:attribute>

	<jsp:body>
<tr>
<td>
<c:if test="${! empty sessionScope.bjlSearchFormCommand}">
<c:url var="purchaseUrl" value="purchase.do" />
</c:if>


<c:if test="${! empty sessionScope.searchFormCommand}">
<c:url var="purchaseUrl" value="purchase.do" />
</c:if>
              
<div id="formwrapper">

<strong>Donation Failed</strong>
					<p />

<strong>Your credit card could not be authorized.</strong>
					<p />

<p style="color: #003366;">Error Message: <c:out
							value="${cardDeclineReason}" />
					</p>

<p style="color: #003366;">
We're sorry that there was a problem processing your transaction. Please click
<a href="${purchaseUrl}">here</a>
to return to the registration page so that you may correct or complete the billing
information and start your search again. If you encounter problems, use our
<a href="${contactUsUrl}">contact form</a>
or call us at (800) 350-2232 (outside the US, call +1-805-375-4041). Customer
service is available Monday to Friday, from 8:00 AM to 5:00 PM PST (UTC-8) or
PDT (UTC-7).
</p>

</div>

</td>
</tr>
</jsp:body>

</neon:NewFunnel>
