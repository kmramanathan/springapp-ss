<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Delete Corporate Account">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h2>Delete Corporate Account</h2>
This will DELETE a corporate account.
<hr/>
<form method="post">
	<table border="0" cellspacing="0">
		<tr>
			<td colspan="2">${errorMsg}</td>
		</tr>

		<tr>
			<td class="label">Account:</td>
			<td class="input">${acct.corporateAccountId}</td>
		</tr>

		<tr>
			<td class="label">Name:</td>
			<td class="input">${acct.corporateName}</td>
		</tr>

		<tr>
			<td class="label">Type "confirm" to delete:</td>
			<td class="input">
				<input name="confirm" type="text"/>
			</td>
		</tr>
	</table>

	<input type="submit"/>
</form>
</jsp:body>

</neon:page>
