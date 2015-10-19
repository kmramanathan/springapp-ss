<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View Payments">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View payments for user <c:out value="${user.username}"/></h2>

<hr/>
<h3>Results</h3>
<table class="payments">
	<tr>
		<th>Payment ID</th>
		<th>Date</th>
		<th>Amount Paid</th>
		<th>Amount Applied</th>		
	</tr>
	<c:forEach items="${payments}" var="p">
		<tr>
			<td><c:out value="${p.paymentId}"/></td>
			<td><fmt:formatDate value="${p.paymentDate}" type="date" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${p.amountPaid}"/></td>
			<td><c:out value="${p.amountApplied}"/></td>
		</tr>
	</c:forEach>
</table>

</jsp:body>

</neon:page>