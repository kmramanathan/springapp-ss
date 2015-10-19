<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View BJL Request">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View BJL Request</h2>

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
		<td class="value">
			<c:if test="${not empty req.name}"><c:out value="${req.name}"/>
				&nbsp;<c:out value="${req.defendant}"/>
			</c:if>
			<c:if test="${not empty req.defendantCity}">
				<c:out value="${req.defendantCity}"></c:out>
			</c:if>
		</td>
	</tr>
	
	<tr>
		<td class="label">State</td>
		<td class="value"><c:out value="${req.defendantState}"/></td>
	</tr>
	
	<tr>
		<td class="label">SSN/Tax ID</td>
		<td class="value"><c:out value="${req.defendantSsnTaxId}"/></td>
	</tr>
	<tr>
		<td class="label">Search Type</td>
		<td class="value"><c:out value="${req.searchType}"/></td>
	</tr>
	<tr>
		<td class="label">Reference Code</td>
		<td class="value"><c:out value="${req.referenceCode}"/></td>
	</tr>

	
</table>

</jsp:body>

</neon:page>