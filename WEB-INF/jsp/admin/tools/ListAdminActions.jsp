<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="List Admin Actions">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>List Admin Actions</h2>
<style>
.rates { border: 1px solid; }
.rates tr {  }
.rates td { border-top: 1px dotted; padding: 5px; }
.rates td.nowrap { white-space: no-wrap; }
</style>
<hr/>
<table class="actions" cellspacing="0">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Description</th>
	</tr>
	<c:forEach items="${actions}" var="a">
		<tr>
			<td><a href="editAdminAction.do?actionId=${a.actionId}">${a.actionId}</a></td>
			<td class="nowrap"><b>${a.actionName}</b></td>
			<td>${a.actionDescription}</td>
		</tr>
	</c:forEach>
</table>

</jsp:body>

</neon:page>