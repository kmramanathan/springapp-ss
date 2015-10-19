<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:userPage title="Change Password">
<jsp:attribute name="stylesheet">members-legacy.css</jsp:attribute>
<jsp:body>
<c:url value="/img/required.gif" var="imgRequiredField"/>
<c:url value="/img/astrix.gif" var="imgAstrix"/>

<h2>Change Password</h2>

<div class="sub-form">

<div class="instructions-flags">
	<img src="${imgRequiredField}" width="158" height="15" alt="Required fields">
</div>
	
<div class="instructions">
	Select a new password and enter it below. Your password will be hidden, so you
	must enter it twice to confirm that it is correct.		
    <br/><br/>

	Your password must be at least 6 characters long. It must consist of only letters
	and numbers, and must have at least 1 capital letter, 1 lowercase letter and 1
	number. Other restrictions may apply as well. If your password is not accepted, 
	you will see an error message and you will be able to choose another password.
	<br/><br/>
	
	Choose a password that you can remember, but not something obvious such as your 
	last name or birthday. We strongly suggest that you never write down your password 
	on paper. If you do, make sure you store it in a secure location. Remember that 
	you are responsible	for all use of your account.
    <br/><br/>

	Passwords are stored in our database in an encrypted format. We can help you change
    your password if you forget it, but we cannot tell you your current password.
    <br/><br/>

    This process is extremely important in order to protect your security. We will
    <strong>never</strong> contact you and ask for your password. If
    <strong>anyone</strong> writes, emails, or calls you for your password, refuse
    the request.
</div>

<div class="sub-form-inputs">
<form:form method="post">
	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Password:</td>
			<td class="input">
				<form:password path="password" />
				<img src="${imgAstrix}" alt="Required field" style="height: 15px; width: 15px;"/>
			</td>
		</tr>
		<tr>
			<td class="label">Confirm Password:</td>
			<td class="input">
				<form:password path="confirmPassword" />
				<img src="${imgAstrix}" alt="Required field" style="height: 15px; width: 15px;"/>
			</td>
		</tr>	
		<tr>
			<form:errors path="*" cssClass="errorBox" element="td" />
		</tr>
	</table>
	<input type="submit"/>
</form:form>
</div>

</div>

</jsp:body>
</neon:userPage>
