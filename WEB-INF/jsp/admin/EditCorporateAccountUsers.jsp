<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Edit Corporate Account">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h2>Edit corporate account</h2>

<form:form method="post" commandName="corporateAccount">
	<form:errors path="*" cssClass="errorBox" element="div" />
	<form:hidden path="corporateAccountId"/>

	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Corporate Name:</td>
			<td class="input"><form:input path="corporateName"/></td>
			<td class="error"><form:errors path="corporateName" /></td>
		</tr>
		<tr>
			<td class="label">Contact Person:</td>
			<td class="input"><form:input path="contactPerson"/></td>
			<td class="error"><form:errors path="contactPerson" /></td>
		</tr>

				 
	</table>
	
	<input type="submit"/>
</form:form>
</jsp:body>

</neon:page>
