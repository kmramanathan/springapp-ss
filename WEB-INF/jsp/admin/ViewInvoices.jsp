<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View Invoices">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View invoices for user <c:out value="${user.username}"/></h2>

<hr/>
<h3>Results</h3>
<table class="invoices">
	<tr>
		<th>Invoice ID</th>
		<th>Bill Date</th>
		<th>Due Date</th>
		<th>Amount Billed</th>
		<th>Amount Paid</th>
	</tr>
	<c:forEach items="${invoices}" var="i">
		<c:url value="/admin/viewInvoiceDetails.do?invoiceId=${i.invoiceId}" var="viewInvoiceDetailsUrl"/>
		<tr>
			<td><a href='<c:out value="${viewInvoiceDetailsUrl}"/>'><c:out value="${i.invoiceId}"/></a></td>
			<td><fmt:formatDate value="${i.invoiceBillDate}" type="date" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><fmt:formatDate value="${i.invoiceDueDate}" type="date" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${i.amountBilled}"/></td>
			<td><c:out value="${i.amountPaid}"/></td>
		</tr>
	</c:forEach>
</table>

</jsp:body>

</neon:page>