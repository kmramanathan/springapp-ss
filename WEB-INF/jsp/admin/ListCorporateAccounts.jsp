<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="List Corporate Accounts">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View corporate accounts</h2>
<a href="editCorporateAccount.do?newAccount=1">Add an account</a>

<hr/>
<h3>Accounts</h3>
Click the account ID to edit account details.<br/>

<table class="corporate-accounts" border="1">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Contact</th>
		<th>Phone</th>
		<th>Accounts</th>
	</tr>
	<c:forEach items="${accounts}" var="a">
		<c:url var="editAccount" value="editCorporateAccount.do?accountId=${a.corporateAccountId}" />
		<tr>
			<td><a href="${editAccount}"><c:out value="${a.corporateAccountId}"/></a></td>
			<td><c:out value="${a.corporateName}"/></td>
			<td><c:out value="${a.contactPerson}"/></td>
			<td><c:out value="${a.phone}"/></td>
			<td><c:out value="${a.accountsInuse}"/> of <c:out value="${a.accountsAuthorized}"/></td>
		</tr>
	</c:forEach>
</table>

</jsp:body>

</neon:page>