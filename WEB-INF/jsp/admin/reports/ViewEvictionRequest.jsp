<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View Eviction Request">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View Eviction Request</h2>

<table class="bjlRequest">
	<tr>
		<td class="label">Request ID</td>
		<td class="value"><c:out value="${req.userSearchId}"/></td>
	</tr>
	<tr>
		<td class="label">User ID</td>
		<td class="value"><c:out value="${req.userId}"/></td>
	</tr>
	<tr>
		<td class="label">Transaction ID</td>
		<td class="value"><c:out value="${req.transactionId}"/></td>
	</tr>
	<tr>
		<td class="label">Date</td>
		<td class="value"><c:out value="${req.createDate}"/></td>
	</tr>
	<tr>
		<td class="label">Match Count</td>
		<td class="value"><c:out value="${req.matchCount}"/></td>
	</tr>
	<tr>
		<td class="label">Invoiced?</td>
		<td class="value"><c:out value="${req.invoiced}"/></td>
	</tr>


	<tr>
		<td class="label">Name</td>
		<td class="value"><c:out value="${req.firstname}"/> <c:out value="${req.lastname}"/> <c:out value="${req.businessname}"/></td>
	</tr>
	
	<tr>
		<td class="label">City</td>
		<td class="value"><c:out value="${req.city}"/></td>
	</tr>
	<tr>
		<td class="label">State</td>
		<td class="value"><c:out value="${req.state}"/></td>
	</tr>
	
	<tr>
		<td class="label">Reference Code</td>
		<td class="value"><c:out value="${req.reference}"/></td>
	</tr>

	
</table>

</jsp:body>

</neon:page>