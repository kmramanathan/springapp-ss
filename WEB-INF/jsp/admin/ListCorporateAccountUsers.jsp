<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="List Corporate Accounts">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>Manage corporate account users</h2>

This page allows you to link users to a corporate account or to remove users 
from a corporate account. The user MUST be already set up and working.<p/>
Currently this page does not change the user's account. It only enables reporting for the 
account in the <a href="corporateInvoicing.do">Corporate Invoicing</a> section.

<hr/>
<h3>Users for <a href="editCorporateAccount.do?accountId=${a.corporateAccountId}"><c:out value="${a.corporateName}"/></a></h3>

<table class="corporate-account-users" border="1">
	<tr>
		<th>User ID</th>
		<th>Username</th>
		<th>Name</th>
		<th>&nbsp;</th>

	</tr>
	<c:forEach items="${users}" var="u">
		<c:url var="viewUser" value="viewUser.do?userId=${u.userId}" />
		<c:url var="removeUser" value="editCorporateAccountUsers.do?accountId=${a.corporateAccountId}&mode=remove&username=${u.username}" />

		<tr>
			<td><a href="${viewUser}"><c:out value="${u.userId}"/></a></td>
			<td><c:out value="${u.username}"/></td>
			<td><c:out value="${u.firstName} ${u.lastName}"/></td>
			<td><a href="${removeUser}">Remove</a></td>
		</tr>
	</c:forEach>
</table>

<form action="editCorporateAccountUsers.do" method="post">
Enter username to add: <input type="text" name="username"/><br/>
<input type="hidden" name="accountId" value="${a.corporateAccountId}" />
<input type="hidden" name="mode" value="add" />
<input type="submit"/>
</form>

</jsp:body>

</neon:page>