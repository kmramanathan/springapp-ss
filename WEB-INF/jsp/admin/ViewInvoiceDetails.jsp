<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View Invoice Details">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View invoice details for invoice ${invoiceId}</h2>
Items in red are disputed.
<style>
.invoices { border: 1px solid; }
.invoices td { border-top: 1px dotted; padding: 5px; }
.invoices td.right { text-align: right; }
.invoices td.nowrap { white-space: no-wrap; }
</style>
<hr/>
<h3>Results</h3>
<table class="invoices">
	<tr>
		<th>Item ID</th>

		<th>Quantity</th>
		<th>Unit Price</th>
		<th>Total Price</th>
		<th>Amount Paid</th>

		<th>Name</th>
		<th>Description</th>
		
		<th>Start Date</th>
		<th>End Date</th>		
	</tr>
	<c:forEach items="${invoiceDetails}" var="id">
		<c:if test="${id.disputed}">
			<font color="#ff0000">
		</c:if>
		<tr>
			<td class="right"><c:out value="${id.invoiceDetailId}"/></td>
			
			<td class="right"><c:out value="${id.itemQuantity}"/></td>
			<td class="right"><c:out value="${id.itemPrice}"/></td>
			<td class="right"><c:out value="${id.totalPrice}"/></td>
			<td class="right"><c:out value="${id.amountPaid}"/></td>
			
			<td class="nowrap"><c:out value="${id.itemName}"/></td>
			<td><c:out value="${id.itemDescription}"/></td>
			
			<td><fmt:formatDate value="${id.itemStartDate}" type="date" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><fmt:formatDate value="${id.itemEndDate}" type="date" dateStyle="SHORT" timeStyle="SHORT"/></td>			
		</tr>
		<c:if test="${id.disputed}">
			</font>
		</c:if>
		
	</c:forEach>
</table>

</jsp:body>

</neon:page>