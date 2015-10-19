<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Add User">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h1>Add user</h1>

<form:form method="post">
	<%-- important stuff here --%>
	<form:hidden path="userOutsideUS" />
	
	<h2>User Information</h2>
	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username</td>
			<td class="input"><form:input  path="user.username" /></td>
			<td class="error"><form:errors path="user.username" /></td>
		</tr>

		<tr>
			<td class="label">First Name</td>
			<td class="input"><form:input  path="user.firstName" /></td>
			<td class="error"><form:errors path="user.firstName" /></td>
		</tr>
		<tr>
			<td class="label">Last Name</td>
			<td class="input"><form:input  path="user.lastName" /></td>
			<td class="error"><form:errors path="user.lastName" /></td>
		</tr>
		<tr>
			<td class="label">Email</td>
			<td class="input"><form:input  path="user.email" /></td>
			<td class="error"><form:errors path="user.email" /></td>
		</tr>
		
		<tr>
			<td class="label">Password</td>
			<td class="input"><form:password path="password" showPassword="true" /></td>
			<td class="error"><form:errors   path="password" /></td>
		</tr>
		<tr>
			<td class="label">Confirm Password</td>
			<td class="input"><form:password path="confirmPassword" showPassword="true" /></td>
			<td class="error"><form:errors   path="confirmPassword" /></td>
		</tr>

		<tr>
			<td class="label">Secret Question</td>
			<td class="input">
				<form:select path="user.passQuestionId">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${passQuestions}" />
				</form:select>			
			</td>
			<td class="error"><form:errors path="user.passQuestionId" /></td>
		</tr>
		<tr>
			<td class="label">Secret Answer</td>
			<td class="input"><form:input  path="user.passAnswer" /></td>
			<td class="error"><form:errors path="user.passAnswer" /></td>
		</tr>
	</table>
	
	
	<h2>Billing Information</h2>
	<table>
		<tr>
			<td class="label">Interval</td>
			<td class="input">
				<form:select path="rateId">
					<form:option value="-" label="--Please Select" />
					<form:options items="${paymentPlans}"  />
				</form:select>
			</td>
			<td class="error"><form:errors path="rateId" /></td>
		</tr>
		<tr>
			<td class="label">Rebill?</td>
			<td class="input"><form:checkbox path="rebill" /> Enables automatic rebilling</td>
			<td class="error"><form:errors path="rebill" /></td>
		</tr>
		<tr>
			<td class="label">Custom Account</td>
			<td class="input"><form:checkbox path="user.customAccount" /> Enables monthly invoicing for Premium searches</td>
			<td class="error"><form:errors path="user.customAccount" /></td>
		</tr>	
		<tr>
			<td class="label">Validate Credit Card</td>
			<td class="input"><form:checkbox path="validateCC" /> Enables CC verification.<br/> 
				Uncheck to add a user without CC (all CC fields below will be ignored).</td>
			<td class="error"><form:errors path="validateCC" /></td>
		</tr>		
	</table>

	<h2>Credit Card Information</h2>
	<table>		
		<tr>
			<td class="label">Number</td>
			<td class="input"><form:input  path="ccNumber" /></td>
			<td class="error"><form:errors path="ccNumber" /></td>
		</tr>
		<tr>
			<td class="label">CVV Code</td>
			<td class="input"><form:input  path="ccCvv" /></td>
			<td class="error"><form:errors path="ccCvv" /></td>
		</tr>
		<tr>
			<td class="label">Exp Month</td>
			<td class="input">
				<form:select path="creditCard.expMonth">
					<form:option value="0" label="--Please Select"/>
					<form:options items="${cardExpirationMonths}" />
				</form:select>
			</td>
			<td class="error"><form:errors path="creditCard.expMonth" /></td>
		</tr>
		<tr>
			<td class="label">Exp Year</td>
			<td class="input">
				<form:select path="creditCard.expYear">
					<form:option value="0" label="--Please Select"/>
					<form:options items="${cardExpirationYears}" />
				</form:select>
			</td>
			<td class="error"><form:errors path="creditCard.expYear" /></td>
		</tr>

		<tr>
			<td class="label">Name</td>
			<td class="input"><form:input  path="creditCard.name" /></td>
			<td class="error"><form:errors path="creditCard.name" /></td>
		</tr>
		<tr>
			<td class="label">Company<br/>(optional)</td>
			<td class="input"><form:input  path="creditCard.company" /></td>
			<td class="error"><form:errors path="creditCard.company" /></td>
		</tr>
		<tr>
			<td class="label">Address 1</td>
			<td class="input"><form:input  path="creditCard.address1" /></td>
			<td class="error"><form:errors path="creditCard.address1" /></td>
		</tr>
		<tr>
			<td class="label">Address 2</td>
			<td class="input"><form:input  path="creditCard.address2" /></td>
			<td class="error"><form:errors path="creditCard.address2" /></td>
		</tr>
		<tr>
			<td class="label">City</td>
			<td class="input"><form:input  path="creditCard.city" /></td>
			<td class="error"><form:errors path="creditCard.city" /></td>
		</tr>
		
		<c:choose>
		
		<c:when test="${command.userOutsideUS}">
		<tr>
			<td class="label">State/Province</td>
			<td class="input"><form:input  path="creditCard.state" /></td>
			<td class="error"><form:errors path="creditCard.state" /></td>
		</tr>
		<tr>
			<td class="label">Postal Code</td>
			<td class="input"><form:input  path="creditCard.postalCode" /></td>
			<td class="error"><form:errors path="creditCard.postalCode" /></td>
		</tr>
		<tr>
			<td class="label">Country</td>
			<td class="input">
				<form:select path="creditCard.countryId">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${countryCodes}" />
				</form:select>
			</td>
			<td class="error"><form:errors path="creditCard.countryId" /></td>
		</tr>		
		</c:when>
		
		<c:otherwise>
		<tr>
			<td class="label">State</td>
			<td class="input">
				<form:select path="creditCard.state">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${usStates}" />
				</form:select>
			</td>
			<td class="error"><form:errors path="creditCard.state" /></td>
		</tr>
		<tr>
			<td class="label">ZIP Code</td>
			<td class="input"><form:input  path="creditCard.postalCode" /></td>
			<td class="error"><form:errors path="creditCard.postalCode" /></td>
		</tr>
		<input type="hidden" name="creditCard.countryId" value="224"/>		
		</c:otherwise>
		
		</c:choose>
		
		<tr>
			<td class="label">Phone</td>
			<td class="input"><form:input  path="creditCard.phone" /></td>
			<td class="error"><form:errors path="creditCard.phone" /></td>
		</tr>
		<tr>
			<td class="label">Fax</td>
			<td class="input"><form:input  path="creditCard.fax" /></td>
			<td class="error"><form:errors path="creditCard.fax" /></td>
		</tr>
		

		<!--
		TODO:  
		options from page 1:
		acct already paid for? premium required? export to arb?
		 -->	
		 
	</table>
	
	<input type="submit"/>
</form:form>
</jsp:body>

</neon:page>
