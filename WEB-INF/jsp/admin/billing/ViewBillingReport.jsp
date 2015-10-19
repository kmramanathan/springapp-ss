<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View Billing Report">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View Billing Report</h2>

<hr/>
<h3>Report Details</h3>

<style>
.billing-reports { border: 1px solid; }
.billing-reports td { padding: 5px; }
.transactions { border: 1px solid; }
.transactions td { padding: 5px; }
</style>

<table class="billing-reports" border="1">
	<tr>
		<th>ID</th>
		<th>Start</th>
		<th>Finish</th>
		<th>Approved</th>
		<th>Declined</th>
		<th>Error</th>
		<th>Subs Deact<br/>No Rebill</th>
		<th>Subs Billed<br/>CC Processed</th>
	</tr>
	<tr>
		<td><a href="${viewReport}"><c:out value="${r.billingReportId}"/></a></td>
		<td><c:out value="${r.dateStarted}"/></td>
		<td><c:out value="${r.dateFinished}"/></td>
		<td><c:out value="${r.txnOk}"/> for $<c:out value="${r.amountOk}"/></td>
		<td><c:out value="${r.txnDecline}"/> for $<c:out value="${r.amountDecline}"/></td>
		<td><c:out value="${r.txnFail}"/> for $<c:out value="${r.amountFail}"/></td>
		<td class="center"><c:out value="${r.subsDeactivated}"/></td>
		<td class="center"><c:out value="${r.subsProcessed}"/></td>		
	</tr>
</table>

<h3>Transactions</h3>

<table class="transactions" border="1">
	<tr>
		<th>ID</th>
		<th>User ID</th>
		<th>CC Name</th>
		<th>CC Last 4</th>
		<th>Amount</th>
		<th>Status</th>
		<th>Bank Response</th>
	</tr>

<c:forEach items="${txns}" var="t">
	<c:url var="viewTxn" value="/admin/reports/viewTransaction.do?transactionId=${t.transactionId}" />
	<c:url var="viewUser" value="/admin/viewUser.do?userId=${t.userId}" />
	<tr>
		<td><a href="${viewTxn}"><c:out value="${t.transactionId}"/></a></td>
		<td><a href="${viewUser}">${t.userId}</a></td>
		<td>${t.ccName}</td>
		<td>${t.ccLastDigits}</td>
		<td>${t.cost}</td>
		<td>${txnStatus[t.transactionStatusId]}</td>
		<td>${t.bankResponseReasonText}</td>	
	</tr>
</c:forEach>
</table>

</jsp:body>

</neon:page>