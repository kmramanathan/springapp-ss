<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Edit Corporate Account">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h2>Edit corporate account</h2>

<c:if test="${!newAccount}">
<a href="listCorporateAccountUsers.do?accountId=${corporateAccount.corporateAccountId}">Manage users</a><br/>
<a href="deleteCorporateAccount.do?accountId=${corporateAccount.corporateAccountId}">Delete account</a>
</c:if>

<hr/>

<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" /> Required Field

<form:form method="post" commandName="corporateAccount">
	<form:errors path="*" cssClass="errorBox" element="div" />
	<form:hidden path="corporateAccountId"/>

	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Corporate Name:</td>
			<td class="input"><form:input path="corporateName"/>
<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" /></td>
			<td class="error"><form:errors path="corporateName" /></td>
		</tr>
		<tr>
			<td class="label">Contact Person:</td>
			<td class="input"><form:input path="contactPerson"/>
<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" /></td>
			<td class="error"><form:errors path="contactPerson" /></td>
		</tr>

		<tr>
			<td class="label">Address 1:</td>
			<td class="input"><form:input path="address1"/></td>
			<td class="error"><form:errors path="address1" /></td>
		</tr>
		<tr>
			<td class="label">Address 2:</td>
			<td class="input"><form:input path="address2"/></td>
			<td class="error"><form:errors path="address2" /></td>
		</tr>
		<tr>
			<td class="label">City:</td>
			<td class="input"><form:input path="city"/></td>
			<td class="error"><form:errors path="city" /></td>
		</tr>
		<tr>
			<td class="label">State:</td>
			<td class="input"><form:input path="state"/></td>
			<td class="error"><form:errors path="state" /></td>
		</tr>
		<tr>
			<td class="label">ZIP:</td>
			<td class="input"><form:input path="zip"/></td>
			<td class="error"><form:errors path="zip" /></td>
		</tr>

		<tr>
			<td class="label">Phone:</td>
			<td class="input"><form:input path="phone"/>
<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" /></td>
			<td class="input"><form:errors path="phone" /></td>
		</tr>
		<tr>
			<td class="label">Fax:</td>
			<td class="input"><form:input path="fax"/></td>
			<td class="error"><form:errors path="fax" /></td>
		</tr>

		<tr>
			<td class="label">Accounts authorized:</td>
			<td class="input"><form:input path="accountsAuthorized"/>
<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" /></td>
			<td class="error"><form:errors path="accountsAuthorized" /></td>
		</tr>
<%--
		<tr>
			<td class="label">Accounts in use:</td>
			<td class="input"><form:input path="accountsInuse"/>
<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" /></td>
			<td class="error"><form:errors path="accountsInuse" /></td>
		</tr>
	--%>			 
	</table>
	
	<input type="submit" value="Save" />
</form:form>
</jsp:body>

</neon:page>
