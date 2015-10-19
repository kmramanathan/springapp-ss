<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View NSS Request">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View National Security Search Request</h2>

<table class="bgcRequest">
	<tr>
		<td class="label">Request ID</td>
		<td class="value"><c:out value="${req.bgcRequestId}"/></td>
	</tr>
	<tr>
		<td class="label">User ID</td>
		<td class="value"><c:out value="${req.userId}"/></td>
	</tr>
	<tr>
		<td class="label">Product ID</td>
		<td class="value"><c:out value="${req.bgcProductId}"/></td>
	</tr>
	<tr>
		<td class="label">Status ID</td>
		<td class="value"><c:out value="${req.bgcProductId}"/></td>
	</tr>
	<tr>
		<td class="label">Price</td>
		<td class="value"><c:out value="${req.price}"/></td>
	</tr>
	<tr>
		<td class="label">Date</td>
		<td class="value"><c:out value="${req.dateCreated}"/></td>
	</tr>

	<tr>
		<td class="label">First Name</td>
		<td class="value"><c:out value="${req.firstName}"/></td>
	</tr>
	
	<tr>
		<td class="label">Last Name</td>
		<td class="value"><c:out value="${req.lastName}"/></td>
	</tr>

	
	
	<c:if test="${req.dobYear != 0}">
	<tr>
		<td class="label">DOB (Y/M/D)</td>
		<td class="value"><c:out value="${req.dobYear}"/> / <c:out value="${req.dobMonth}"/> / <c:out value="${req.dobDay}"/></td>
	</tr>
	</c:if>
	
	<tr>
		<td class="label">Purpose Of Search</td>
		<td class="value"><c:out value="${purpose.description}"/></td>
	</tr>
	<tr>
		<td class="label">Results Found</td>
		<td class="value"><c:out value="${resp.quantityFound}"/></td>
	</tr>
	
	<tr>
		<td class="label">Results Returned</td>
		<td class="value"><c:out value="${resp.quantityReturned}"/></td>
	</tr>
	<%-- 
	<tr>
		<td class="label">Transaction ID</td>
		<td class="value"><c:out value="${resp.transactionId}"/></td>
	</tr>
	--%>
	
</table>

</jsp:body>

</neon:page>