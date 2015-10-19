<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:NewFunnel title="Purchase Failed" >
<jsp:attribute name="stylesheet">new-funnel.css</jsp:attribute>

<jsp:body>
<tr>
<td>
<c:if test="${! empty sessionScope.bjlSearchFormCommand}">
<c:url var="purchaseUrl" value="purchase.do"/>
</c:if>


<c:if test="${! empty sessionScope.searchFormCommand}">
<c:url var="purchaseUrl" value="purchase.do" />
</c:if>
              
<div id="formwrapper">

<strong>Purchase Failed</strong><p/>
<c:if test="${! empty accountDeclined}">
	<strong>Your Account could not be authorized.</strong><p/>
	<p style="color:#003366;">Error Message:</p>
	
	<p style="color:#003366;">
We're sorry that there was a problem processing your transaction. Please click the "Contact" above to send us message or call us at
 (805) 375-4041 M-F between 8AM and 5PM P.S.T and we will work to get you established with any account as quickly as possible.
 
</p>
<p>&nbsp;</p>
</c:if>
<c:if test="${empty accountDeclined}">
	<strong>Your credit card could not be authorized.</strong><p/>
	<p style="color:#003366;">Error Message: <c:out value="${cardDeclineReason}"/></p>
	
	<p style="color:#003366;">
We're sorry that there was a problem processing your transaction. Please click the MY ACCOUNT link in the upper right corner and update your Billing Information. Then follow the instructions to start your search again.
</p>
<p>&nbsp;</p>
</c:if>




</div>

</td>
</tr>
</jsp:body>

</neon:NewFunnel>
