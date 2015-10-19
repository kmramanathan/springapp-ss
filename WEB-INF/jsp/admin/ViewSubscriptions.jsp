<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View Subscriptions">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View subscriptions for user <c:out value="${username}"/></h2>
<style>
.subscriptions td { border: 1px dotted; padding: 5px; }
</style>

<a href="viewUser.do?username=${username}">View user</a><br/>
<hr/>
<h3>Results</h3>
<table class="subscriptions">
	<tr>
		<th>Sub ID</th>
		<th>Rate: (Id) Name</th>
		<th>Active?</th>
		<th>Rebill?</th>
		<th>Init Date</th>
		<th>Prev Bill</th>
		<th>Next Bill</th>
	</tr>
	<c:forEach items="${subscriptions}" var="s">
		<c:url var="viewRate" value="/billing/editRate.do?rateId=${s.rateId}" />
<c:set var="Rebill" value="${s.rebill ? 'Yes' : 'No' }"/>
<c:set var="Active" value="${s.active ? 'Yes' : 'No' }"/>
		<tr>
			<td><c:out value="${s.subscriptionId}"/></td>
			<td>(<a href="${viewRate}">${s.rateId}</a>) ${rateNames[s.rateId]}</td>
			<td><c:out value="${Active}"/></td>
			<td><c:out value="${Rebill}"/></td>
			
			<td><fmt:formatDate value="${s.initDate}" type="date" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><fmt:formatDate value="${s.prevBillDate}" type="date" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><fmt:formatDate value="${s.nextBillDate}" type="date" dateStyle="SHORT" timeStyle="SHORT"/></td>
		</tr>
	</c:forEach>
</table>

</jsp:body>

</neon:page>