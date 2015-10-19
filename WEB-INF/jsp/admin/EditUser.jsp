<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Edit User">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>Edit user</h2>
<a href="viewUser.do?username=${command.user.username}">View user</a><br/>
<%-- 
<a href="changeUserPassword.do?username=${command.user.username}">Change password for this user</a><br/>
<a href="addSubscription.do?username=${command.user.username}">Add a subscription for this user</a> 
--%>
<hr/>

<form:form method="post" commandName="command">
	<table class="user">
		<tr>
			<td class="label">Username</td>
			<td class="input">
				<c:out value="${command.user.username}" />
				<form:hidden path="user.username"/>
			</td>
		</tr>
		
		<tr>
			<td class="label">User ID</td>
			<td class="input">
				<c:out value="${command.user.userId}" />
				<form:hidden path="user.userId"/>
			</td>
		</tr>
		<tr>
			<td class="label">User IP</td>
			<td class="input">
				<c:out value="${command.user.cancelComment}" />
				<form:hidden path="user.cancelComment"/>
			</td>
		</tr>
		<tr>
			<td class="label">First Name</td>
			<td class="input">
				<form:input path="user.firstName" />
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="user.firstName" />
        	</td>
		</tr>
		<tr>
			<td class="label">Last Name</td>
			<td class="input">
				<form:input path="user.lastName"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="user.lastName" />
        	</td>
		</tr>
		<tr>
			<td class="label">Email</td>
			<td class="input">
				<form:input path="user.email"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="user.email" />
        	</td>
		</tr>		
		
		<tr>
			<td class="label">Secret Question</td>			
			<td width="50%">
				<form:select path="user.passQuestionId">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${passQuestions}" />
				</form:select>
			</td>
			<td class="error">
          		<form:errors path="user.passQuestionId" />
        	</td>
		</tr>		
		<tr>			
			<td class="label">Secret Answer</td>
			<td class="input">
				<form:input path="user.passAnswer"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="user.passAnswer" />
        	</td>
		</tr>
		
		<c:choose>
			<c:when test="${command.user.disabled}">
				<c:set var="disabledRowClass" value="disabled"/>
				<c:set var="inactiveRowClass" value=""/>
			</c:when>
			<c:when test="${command.user.inactive}">
				<c:set var="disabledRowClass" value=""/>
				<c:set var="inactiveRowClass" value="inactive"/>
			</c:when>
			<c:when test="${command.user.inactive && command.user.disabled}">
				<c:set var="disabledRowClass" value="disabled"/>
				<c:set var="inactiveRowClass" value="inactive"/>
			</c:when>
			<c:otherwise>
				<c:set var="disabledRowClass" value=""/>
				<c:set var="inactiveRowClass" value=""/>
			</c:otherwise>
		</c:choose>

		<tr>			
			<td class="label">Custom Account</td>
			<td class="input"><form:checkbox path="user.customAccount"/></td>
			<td class="error"><form:errors path="user.customAccount" /></td>
		</tr>
		<tr class="<c:out value="${disabledRowClass}"/>">			
			<td class="label">Disabled</td>
			<td class="input"><form:checkbox path="user.disabled"/></td>
			<td class="error"><form:errors path="user.disabled" /></td>
		</tr>
		<tr class="<c:out value="${inactiveRowClass}"/>">			
			<td class="label">Inactive</td>
			<td class="input"><form:checkbox path="user.inactive"/></td>
			<td class="error"><form:errors path="user.inactive" /></td>
		</tr>
		<tr>			
			<td class="label">No Credit Card</td>
			<td class="input"><form:checkbox path="user.noCreditCard"/></td>
			<td class="error"><form:errors path="user.noCreditCard" /></td>
		</tr>
		<tr>			
			<td class="label">No Premium</td>
			<td class="input"><form:checkbox path="user.noPremium"/></td>
			<td class="error"><form:errors path="user.noPremium" /></td>
		</tr>
		<tr>			
			<td class="label">DirectPass</td>
			<td class="input"><form:checkbox path="user.subDirectoryUser"/></td>
			<td class="error"><form:errors path="user.subDirectoryUser" /></td>
		</tr>
		
	</table>
	
	<input type="submit"/>
</form:form>
</jsp:body>

</neon:page>

