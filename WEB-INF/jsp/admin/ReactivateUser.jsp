<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Reactivate User">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>Reactivate User</h2>
<%--
<a href="viewUser.do?username=${command.user.username}">View user</a><br/>
<a href="changeUserPassword.do?username=${command.user.username}">Change password for this user</a><br/>
<a href="addSubscription.do?username=${command.user.username}">Add a subscription for this user</a> 
--%>
<hr/>

Please verify the billing information and update it if necessary.

<form:form method="post" commandName="command">
	<form:errors path="*" cssClass="errorBox" element="div" />

	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username</td>
			<td class="input">
				<c:out value="${command.username}" />
			</td>
		</tr>
		
		<tr>
			<td class="label">Name on card</td>
			<td class="input">
				<form:input path="creditCard.name" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.name" />
        	</td>
		</tr>	
		<tr>
			<td class="label">Company</td>
			<td class="input">
				<form:input path="creditCard.company" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.company" />
        	</td>
		</tr>
		<tr>
			<td class="label">Address 1</td>
			<td class="input">
				<form:input path="creditCard.address1" />
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.address1" />
        	</td>
		</tr>
		<tr>
			<td class="label">Address 2</td>
			<td class="input">
				<form:input path="creditCard.address2" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.address2" />
        	</td>
		</tr>
		<tr>
			<td class="label">City</td>
			<td class="input">
				<form:input path="creditCard.city" />
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.city" />
        	</td>
		</tr>
		<tr>
			<td class="label">State</td>
			<td class="input">
				<form:input path="creditCard.state" />
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.state" />
        	</td>
		</tr>
		<tr>
			<td class="label">Postal Code</td>
			<td class="input">
				<form:input path="creditCard.postalCode" />
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.postalCode" />
        	</td>
		</tr>
		<tr>
			<td class="label">Phone</td>
			<td class="input">
				<form:input path="creditCard.phone" />
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.phone" />
        	</td>
		</tr>
		<tr>
			<td class="label">Fax</td>
			<td class="input">
				<form:input path="creditCard.fax" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.fax" />
        	</td>
		</tr>
		
		<tr>
			<td class="label">Country</td>			
			<td width="50%">
				<form:select path="creditCard.countryId">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${countryCodes}" />
				</form:select>
			</td>
			<td class="error">
          		<form:errors path="creditCard.countryId" />
        	</td>
		</tr>
		
		<tr>
			<td class="label">Card Number</td>
			<td class="input">
				<form:input path="cardNumber" />
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="cardNumber" />
        	</td>
		</tr>
		<tr>
			<td class="label">Expires</td>			
			<td width="50%">
				<form:select path="creditCard.expMonth">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${cardExpirationMonths}" />
				</form:select>
				<form:select path="creditCard.expYear">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${cardExpirationYears}" />
				</form:select>
			</td>
			<td class="error">
          		<form:errors path="creditCard.expMonth" />
          		<form:errors path="creditCard.expYear" />
        	</td>
		</tr>		
			
	</table>
	
	<input type="submit"/>
</form:form>
</jsp:body>

</neon:page>

