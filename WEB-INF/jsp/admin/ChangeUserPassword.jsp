<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Change User Password">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h2>Change User Password</h2>
<a href="viewUser.do?username=${command.user.username}">View user</a><br/>
<%--
<a href="changeUserPassword.do?username=${command.user.username}">Change password for this user</a><br/>
<a href="addSubscription.do?username=${command.user.username}">Add a subscription for this user</a> 
--%>
<hr/>
<form:form method="post" commandName="ChangeUserPassword" action="changeUserPassword.do">
	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username:</td>
			<td class="input"><c:out value="${ChangeUserPassword.username}"/></td>
			<form:hidden path="username"/>
		</tr>
		
		<tr>
			<td class="label">Password:</td>
			<td class="input">
				<form:password path="password"/>
			</td>
		</tr>
		<tr>
			<td class="label">Confirm:</td>
			<td class="input">
				<form:password path="confirmPassword"/>
			</td>
		</tr>
		
		<tr>
			<form:errors path="*" cssClass="errorBox" element="td" />
		</tr>
		 
	</table>
	
	<input type="submit"/>
</form:form>
</jsp:body>

</neon:page>
