<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Validate Username">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h2>Validate Username</h2>
Test a username to see if it is valid.
<hr/>
<form method="post">
	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username:</td>
			<td class="input"><input name="username" type="text" value="${username}"/></td>
		</tr>

		<tr>
			<td class="label">Valid:</td>
			<td class="input" style="font-color: #ff0000;">${valid}</td>
		</tr>

	</table>
	
	<input type="submit"/>
</form>
</jsp:body>

</neon:page>
