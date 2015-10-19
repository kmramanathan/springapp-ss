<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Edit Credit Card">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>Reactivate User</h2>
<a href="viewUser.do?username=${username}">View user</a><br/>
<hr/>

<c:choose>
	<c:when test="${cardDeclined}">
The credit card was declined. The billing data was updated (if necessary) but the 
user has NOT been reactivated.<p/>
Message: <c:out value="${cardDeclinedReason}"/>
	</c:when>
	<c:when test="${cardError}">
A credit card processing error occurred. The billing data was updated (if necessary) 
but the user has NOT been reactivated.<p/>
Message: <c:out value="${cardErrorReason}"/>
	</c:when>
	<c:otherwise>
User has been reactivated. The billing data was updated (if necessary) and the credit 
card was successfully charged.<p/>
Amount charged: <c:out value="${amount}"/><br/>
Approval code: <c:out value="${cardApprovalCode}"/><br/>
	</c:otherwise>
</c:choose>


</jsp:body>

</neon:page>

