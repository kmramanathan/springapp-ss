<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View User">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View user</h2>
<a href="editUser.do?username=${user.username}">Edit user</a><br/>

<c:choose>
	<c:when test="${user.disabled || user.inactive}">
<a href="reactivateUser.do?username=${user.username}">Reactivate user</a><br/>
	</c:when>
	<c:otherwise>
<a href="changeUserPassword.do?username=${user.username}">Change user password</a><br/>
<a href="editCreditCard.do?username=${user.username}">Edit credit card</a><br/>
<a href="changeUsername.do?oldUsername=${user.username}">Change username</a><br/>
<a href="viewSubscriptions.do?username=${user.username}">View subscriptions</a><br/>
<a href="viewInvoices.do?username=${user.username}">View invoices</a><br/>
	</c:otherwise>
</c:choose>

<%-- 
<a href="viewInvoices.do?username=${user.username}">View invoices</a><br/>
<a href="viewPayments.do?username=${user.username}">View payments</a><br/>
--%>
<hr/>

<table class="user">
	<tr>
		<td class="label">Username</td>
		<td class="value"><c:out value="${user.username}"/></td>
	</tr>
	<tr>
		<td class="label">User ID</td>
		<td class="value"><c:out value="${user.userId}"/></td>
	</tr>
	<tr>
		<td class="label">User IP</td>
		<td class="value"><c:out value="${user.cancelComment}"/></td>
	</tr>
	<tr>
		<td class="label">First Name:</td>
		<td class="value"><c:out value="${user.firstName}"/></td>
	</tr>	
	<tr>
		<td class="label">Last Name:</td>
		<td class="value"><c:out value="${user.lastName}"/></td>
	</tr>
	<tr>
		<td class="label">Email</td>
		<td class="value"><c:out value="${user.email}"/></td>
	</tr>

	<tr>
		<td class="label">Custom Account</td>
		<td class="value"><c:out value="${user.customAccount}"/></td>
	</tr>
	
	<c:choose>
		<c:when test="${user.disabled}">
			<c:set var="disabledRowClass" value="disabled"/>
			<c:set var="inactiveRowClass" value=""/>
		</c:when>
		<c:when test="${user.inactive}">
			<c:set var="disabledRowClass" value=""/>
			<c:set var="inactiveRowClass" value="inactive"/>
		</c:when>
		<c:when test="${user.inactive && user.disabled}">
			<c:set var="disabledRowClass" value="disabled"/>
			<c:set var="inactiveRowClass" value="inactive"/>
		</c:when>
		<c:otherwise>
			<c:set var="disabledRowClass" value=""/>
			<c:set var="inactiveRowClass" value=""/>
		</c:otherwise>
	</c:choose>

	<tr class="<c:out value="${disabledRowClass}"/>">
		<td class="label">Disabled</td>
		<td class="value"><c:out value="${user.disabled}"/></td>
	</tr>
	<tr class="<c:out value="${inactiveRowClass}"/>">
		<td class="label">Inactive</td>
		<td class="value"><c:out value="${user.inactive}"/></td>
	</tr>
	
	<c:if test="${user.disabled || user.inactive}">
		<tr class="<c:out value="${disabledRowClass}"/>">
			<td class="label">Cancel Date</td>
			<td class="value"><fmt:formatDate value="${user.disabledDate}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
		</tr>
		<tr class="<c:out value="${disabledRowClass}"/>">
			<td class="label">Cancel Reason</td>
			<td class="value"><c:out value="${user.disabledReason}"/></td>
		</tr>	
	</c:if>
	

	<tr>
		<td class="label">No Credit Card</td>
		<td class="value"><c:out value="${user.noCreditCard}"/></td>
	</tr>
	<tr>
		<td class="label">No Premium</td>
		<td class="value"><c:out value="${user.noPremium}"/></td>
	</tr>
	<tr>
		<td class="label">DirectPass</td>
		<td class="value"><c:out value="${user.subDirectoryUser}"/></td>
	</tr>	
</table>

</jsp:body>

</neon:page>