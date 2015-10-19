<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Add Subscription">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h1>Add user</h1>
<form:form method="post" commandName="subscription" action="addSubscription.do">
	<form:errors path="*" cssClass="errorBox" element="div" />

	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username:</td>
			<td class="input">
				<form:input path="username"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="username" />
        	</td>
		</tr>
		
		 
	</table>
	
	<input type="submit"/>
</form:form>
</jsp:body>

</neon:page>
