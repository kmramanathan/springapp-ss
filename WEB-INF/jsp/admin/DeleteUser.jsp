<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Delete User">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h2>Delete User</h2>
This will DELETE a user, including transactions, subscriptions and credit cards.
<hr/>
<form method="post">
	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username:</td>
			<td class="input">
				<input name="username" type="text"/>
			</td>
		</tr>
	</table>
	
	<input type="submit"/>
</form>
</jsp:body>

</neon:page>
