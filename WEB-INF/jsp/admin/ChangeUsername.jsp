<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Change Username">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h2>Change Username</h2>
<a href="viewUser.do?username=${oldUsername}">View user</a><br/>
<hr/>
<form method="post" action="changeUsername.do">
The new username must be 4 to 25 characters long. Acceptable characters are letters,
numbers, dash and underscore. The username must start with a letter or number.<p/>
	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Old Username:</td>
			<td class="input">${oldUsername}</td>
		</tr>

		<tr>
			<td class="label">New Username:</td>
			<td class="input"><input type="text" name="newUsername" value="${newUsername}" /></td>
		</tr>
			<input type="hidden" name="oldUsername" value="${oldUsername}" />
				
		<tr><td>${errorMsg}</td></tr>
		 
	</table>
	
	<input type="submit"/>
</form>
</jsp:body>

</neon:page>
