<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Disable User">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h2>Disable User</h2>
<%--
<a href="viewUser.do?username=${command.user.username}">View user</a><br/>
<a href="changeUserPassword.do?username=${command.user.username}">Change password for this user</a><br/>
<a href="addSubscription.do?username=${command.user.username}">Add a subscription for this user</a> 
--%>
<hr/>
<form:form method="post" commandName="DisableUser">
	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username:</td>
			<td class="input">
				<form:input path="username"/>
			</td>
			<td><form:errors path="username" cssClass="errorBox" /></td>
		</tr>

		<tr>
			<td class="label">Inactive:</td>
			<td class="input">
				<form:checkbox path="inactive"/>
			</td>
			<td><form:errors path="inactive" cssClass="errorBox" /></td>
		</tr>
		<tr>
			<td class="label">Disabled:</td>
			<td class="input">
				<form:checkbox path="disabled"/>
			</td>
			<td><form:errors path="disabled" cssClass="errorBox" /></td>
		</tr>		

		<tr>
			<td class="label">Reason:</td>
			<td class="input">
				<form:input path="reason"/> (optional, max 50 chars)
			</td>
			<td><form:errors path="reason" cssClass="errorBox" /></td>
		</tr>				
	</table>
	
	<input type="submit"/>
</form:form>
</jsp:body>

</neon:page>
