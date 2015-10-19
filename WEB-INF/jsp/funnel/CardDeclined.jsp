<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Purchase Failed" rightSidebar="SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<c:if test="${sessionScope.purchaseFormCommand.signup}">
<c:url var="purchaseUrl" value="purchaseSignup.do"/>
</c:if>


<c:if test="${sessionScope.purchaseFormCommand.search}">
<c:url var="purchaseUrl" value="purchaseSearch.do" />
</c:if>
              
<div id="formwrapper">

<strong>Purchase Failed</strong><p/>

<strong>Your credit card could not be authorized.</strong><p/>

<p>Error Message: <c:out value="${cardDeclineReason}"/></p>

<p>
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
</jsp:body>

</neon:funnelPage>
