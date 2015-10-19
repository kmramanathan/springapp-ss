<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Cleanup test users">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>Cleanup test users</h2>

<c:if test="${!empty deleted}">
Deleted ${deleted} users.<br/>
</c:if>

<form method="post">
<input type="submit"/>
</form>

<table class="corporate-account-users" border="1">
	<tr>
		<th>User ID</th>
		<th>Create Date</th>
		<th>Username</th>
		<th>First Name</th>
		<th>Last name</th>
		<th>Sub ID</th>
	</tr>
	<c:forEach items="${users}" var="u">
		<tr>
			<td>${u.user_id}</td>
			<td>${u.create_date}</td>
			<td>${u.username}</td>
			<td>${u.first_name}</td>
			<td>${u.last_name}</td>
			<td>${u.subscription_id}</td>
		</tr>
	</c:forEach>
</table>

</jsp:body>

</neon:page>