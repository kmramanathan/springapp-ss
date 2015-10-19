<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Search Systems DirectPass Registration">
<jsp:attribute name="stylesheet">registration.css</jsp:attribute>

<jsp:body>

<%@ include file="top.jsp" %>

<h1>SearchSystems.net DirectPass Registration</h1>

<div id="content-main">
<form:form method="post" commandName="registration">
	<form:errors path="*" cssClass="errorBox" element="true" />

	<table border="0" cellspacing="0">
		<tr>
			<th colspan="4">Account Information</th>
		</tr>
		<tr>
			<td colspan="4" style="padding: 1em;">This information uniquely
			identifies you when logging into our website. Please carefully review
			the email address you enter before proceeding. This is the address we
			will use to send you very important information regarding your
			application. We never share your email address. See our <a
				href="http://www.searchsystems.net/privacy.php">Privacy Policy</a>.
			If you have a spam filter, please make sure that you can accept
			emails from searchsystems.net. <br />
			<br />
			<b>Note:</b> If you are a premium member and would like DirectPass
			please <a href="/premium_subscribe.php">click here</a>.</td>
		</tr>
		<%-- Associated errors to firstName field displayed --%>
		<tr>
			<td class="label">First Name:</td>
			<td class="input">
				<form:input path="user.firstName" />
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="user.firstName" />
        	</td>
		</tr>
		<tr>
			<td class="label">Last Name:</td>
			<td class="input">
				<form:input path="user.lastName"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="user.lastName" />
        	</td>
		</tr>
		<tr>
			<td class="label">Email:</td>
			<td class="input">
				<form:input path="user.email"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="user.email" />
        	</td>
		</tr>
		<tr>
			<td class="label">Confirm Email:</td>
			<td class="input">
				<form:input path="confirmEmail"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="confirmEmail" />
        	</td>
		</tr>

		<tr>
			<td colspan="4" style="padding: 1em;">Please choose a username.
			Your username can be from 6-20 characters. It must start with a
			letter, and may contain only letters (A-Z, a-z) and numbers (0-9).
			Your username may contain capital letters but it is not case
			sensitive; our systems consider "JohnSmith123" and "johnsmith123" to
			be identical. If your desired username is already taken, you will
			have an opportunity to choose again.</td>
		</tr>

		<tr>
			<td class="label">Username:</td>
			<td class="input">
				<form:input path="user.username"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="user.username" />
        	</td>
		</tr>

		<tr>
			<td colspan="4" style="padding: 1em;">Please choose a password.
			Enter the password twice to confirm it. Your password can be from
			6-20 characters. It may contain only letters (A-Z, a-z) and numbers
			(0-9). Your password <b>IS</b> case sensitive, so be sure to use the
			correct case where necessary. Your password must have at least 1
			lowercase letter, at least 1 uppercase letter, and at least 1 number.
			</td>
		</tr>

		<tr>
			<td class="label">Password:</td>
			<td class="input">
				<form:input path="password"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="password" />
        	</td>
		</tr>
		<tr>
			<td class="label">Confirm Password:</td>
			<td class="input">
				<form:input path="confirmPassword"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="confirmPassword" />
        	</td>
		</tr>

	</table>

	<br />

	<table cellspacing="0">
		<tr>
			<th colspan="4">Secret Question</th>
		</tr>
		<tr>
			<td colspan="4" style="padding: 1em;">Choosing a secret question
			and answer will allow you to reset the password for your account in
			the event that you lose or forget your password. <br />
			<br />
			Select a secret question from our list below. Make sure to choose a
			question that only you know the answer to. Type your answer in the
			box below. Your answer may contain only letters (A-Z, a-z), numbers
			(0-9) and spaces. Your answer <b>IS</b> case sensitive, so be sure to
			use the correct case where necessary.</td>
		</tr>
		<tr>
			<td class="label">Question:</td>			
			<td width="50%">
				<form:select path="hintQuestion">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${registration.hintQuestions}" />
				</form:select>
			</td>
			<td class="error">
          		<form:errors path="hintQuestion" />
        	</td>
		</tr>
		
		<tr>			
			<td class="label">Answer:</td>
			<td class="input">
				<form:input path="hintAnswer"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="hintAnswer" />
        	</td>
		</tr>
	</table>

	<br />

	<table cellspacing="0">
		<tr>
			<th colspan="2">Billing Information</th>
		</tr>
		<tr>
			<td colspan="2" style="padding: 1em;">Please make sure that your
			name and address are the same as on the mailing address on your
			credit card statement. <br />
			<br />

			The SearchSystems.net website protects your billing information and
			personal information through secure communications provided by Thawte
			SSL Certified encryption service. <br />
			<br />

			<a onClick="${thawteOnClick}" href="#"  title="Thawte">
				<img alt="Thawte Certified" src="img/thawte_logo.gif" style="border: 0;" />
			</a> 
			<img alt="We accept these cards" src="img/cards.gif" /></td>
		</tr>
		<tr>
			<td class="label">Card Holder Name:</td>
			<td class="input">
				<form:input path="creditCard.name"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.name" />
        	</td>
		</tr>
		<tr>
			<td class="label">Credit Card Number:</td>
			<td class="input">
				<form:input path="cardNumber"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="cardNumber" />
        	</td>
		</tr>
		<tr>
			<td class="label">Expiration Month/Year:</td>
			<td>
				<form:select path="creditCard.expMonth">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${registration.cardExpirationMonths}" />
				</form:select>
				<form:select path="creditCard.expYear">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${registration.cardExpirationYears}" />
				</form:select>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.expMonth" />
          		<form:errors path="creditCard.expYear" />
        	</td>
		</tr>
		<tr>
			<td class="label">Authorization Code:</td>
			<td class="input">
				<form:input path="cardCvv"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="cardCvv" />
        	</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
			<div style="width: 400px;">This number is printed on the back
			of your credit card, it is the last 3 digits after the credit card
			number. On American Express cards it is the four numbers on the
			front.</div>
			</td>
		</tr>
		<tr>
			<td class="label">Purchase Plan:</td>
			<td>
				<form:radiobuttons path="purchasePlan" items="${registration.purchasePlans}"/>
			</td>
			<td class="error">
          		<form:errors path="purchasePlan" />
        	</td>
		</tr>
		<tr>
			<td class="label">Company:</td>
			<td class="input">
				<form:input path="creditCard.company"/>
			</td>
			<td class="error">
          		<form:errors path="creditCard.company" />
        	</td>
		</tr>
		<tr>
			<td class="label">Billing Address Line 1:</td>
			<td class="input">
				<form:input path="creditCard.address1"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.address1" />
        	</td>
		</tr>
		<tr>
			<td class="label">Billing Address Line 2:</td>
			<td class="input">
				<form:input path="creditCard.address2"/>
			</td>
			<td class="error">
          		<form:errors path="creditCard.address2" />
        	</td>
		</tr>
		<tr>
			<td class="label">Billing City:</td>
			<td class="input">
				<form:input path="creditCard.city"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.city" />
        	</td>
		</tr>

		<tr>
			<td class="label">Billing State:</td>
			<td>
				<form:select path="creditCard.state">
					<form:option value="-" label="--Please Select"/>
					<form:options items="${registration.states}" />
				</form:select>				
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.state" />
        	</td>
		</tr>
		<tr>
			<td class="label">Zip Code:</td>
			<td class="input">
				<form:input path="cardZipCode"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="cardZipCode" />
        	</td>
		</tr>
		<tr>
			<td class="first">&nbsp;</td>
			<td>(Your 5 digit zip code.)</td>
		</tr>
		<tr>
			<td class="label">Day Phone:</td>
			<td class="input">
				<form:input path="creditCard.phone"/>
				<img src="img/astrix.gif" style="height: 15px; width: 15px;" alt="Required field" />
			</td>
			<td class="error">
          		<form:errors path="creditCard.phone" />
        	</td>
		</tr>
		<tr>
			<td class="first">&nbsp;</td>
			<td>(Area code first. No parentheses or dashes, ex: 2135550010)</td>
		</tr>
		<tr>
			<td class="input">
				<form:input path="creditCard.fax"/>
			</td>
			<td class="error">
          		<form:errors path="creditCard.fax" />
        	</td>
		</tr>
		<tr>
			<td class="first">&nbsp;</td>
			<td>(Area code first. No parentheses or dashes, ex: 2135550010)</td>
		</tr>
	</table>

	<table cellspacing="0" border="0">
		<tr>
			<td class="first">
			<form:checkbox path="receiveNewsletter" value="checked" />
			Uncheck this box if you would like to opt-out of receiving our newsletter.
			</td>
		<tr>
		<tr>
			<td><input type="submit" value="Create Account" /></td>
		</tr>
	</table>

</form:form>
</div>

</jsp:body>
</neon:page>
