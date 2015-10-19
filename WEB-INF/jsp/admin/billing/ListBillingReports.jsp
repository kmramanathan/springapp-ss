<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="List Billing Reports">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>List Billing Reports</h2>

<hr/>
<h3>Reports</h3>
Description:<br/>
<ul>
<li>
<b>Subs Deact No Rebill</b>: Number of subscriptions that were deactivated
because the customer declined automatic rebilling. These deactivations
take place before any CC processing.
</li>
<li>
<b>Subs Billed CC Processed</b>: Number of subscriptions that went through the
normal billing process. This should be equal to Approved + Declined + Error.
</li>
</ul>

Click the report ID to view more information.<p/>

<style>
#billing-reports { border: 1px solid; }
#billing-reports td { padding: 5px; }
#billing-reports td.center { text-align: center; }
</style>

<c:set var="pageSize" value="25"/>

<neon:sortableTable pageSize="${pageSize}" rowCount="${rowCount}" tableColumns="5" tableName="billing-reports">
<jsp:attribute name="tableHeaders">
		<th class="table-sortable:numeric">ID</th>
		<th class="table-sortable:date">Time</th>
		<th>Approved</th>
		<th>Declined</th>
		<th>Error</th>
		<th>Subs Deact<br/>No Rebill</th>
		<th>Subs Billed<br/>CC Processed</th>
</jsp:attribute>
<jsp:body>
	<c:forEach items="${reports}" var="r">
		<c:url var="viewReport" value="viewBillingReport.do?reportId=${r.billingReportId}" />
		<tr>
			<td><a href="${viewReport}"><c:out value="${r.billingReportId}"/></a></td>
			<td><fmt:formatDate value="${r.dateFinished}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${r.txnOk}"/> for $<c:out value="${r.amountOk}"/></td>
			<td><c:out value="${r.txnDecline}"/> for $<c:out value="${r.amountDecline}"/></td>
			<td><c:out value="${r.txnFail}"/> for $<c:out value="${r.amountFail}"/></td>
			<td class="center"><c:out value="${r.subsDeactivated}"/></td>
			<td class="center"><c:out value="${r.subsProcessed}"/></td>
		</tr>
	</c:forEach>
</jsp:body>
</neon:sortableTable>

</jsp:body>

</neon:page>