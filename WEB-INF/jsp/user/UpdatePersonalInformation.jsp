<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:userPage title="Update My Personal Information">
<jsp:attribute name="stylesheet">members-legacy.css</jsp:attribute>
<jsp:body>

<h2>Update My Personal Information</h2>

<div class="main-content">

<div class="note-box">
This area is designed to let you change your contact information. Please make
changes to the form below if you've changed your name or email address. Then
click on the <strong>Change Contact Information</strong> button to put these
changes into effect.
</div>

<div class="form-box">
<form:form method="post" commandName="user">
	<form:errors path="*" cssClass="errorBox" element="td" />

    <div class="sub-form">
		<div class="instructions-flags">
			<img src="/images/required.gif" width="158" height="15" alt="Required fields">
		</div>

        <div class="instructions">
            <h4>Contact Information</h4>

            This information is used for billing and customer service. We never share
            your email address. See our <strong>
			<a href="${showAgreementUrl}?version=privacy" target="_blank">Privacy Policy</a></strong>.
	    </div>

		<div class="sub-form-inputs">
        <table>
            <tr>
                <td class="label"><label for="firstName">First Name:</label></td>
                <td class="nowrap">
                	<form:input path="firstName" cssClass="short" size="20" maxlength="25" />
					<img src="/images/astrix.gif" alt="Required field" style="height: 15px; width: 15px;"/>
				</td>
            </tr>
            <tr>
                <td class="label"><label for="lastName">Last Name:</label></td>
                <td class="nowrap">
                	<form:input path="lastName" cssClass="short" size="20" maxlength="25" />
                	<img src="/images/astrix.gif" alt="Required field" style="height: 15px; width: 15px;"/>
                </td>
            </tr>
            <tr>
                <td class="label"><label for="email">Email Address:</label></td>
                <td class="nowrap">
                	<form:input path="email" cssClass="short" size="20" maxlength="25" />
                	<img src="/images/astrix.gif" alt="Required field" style="height: 15px; width: 15px;"/>
                </td>
            </tr>
		</table>
		</div>
	</div>

	<div class="sub-form">
		<div class="instructions">
        	<h4>Secret Question</h4>

            This secret question is used to randomly generate a new password for your account in the event that
            you lose or forgot your password.
		</div>

		<div class="sub-form-inputs">
		<table>
            <tr>
                <td class="label"><label for="passQuestionId">Question:</label></td>
                <td class="nowrap">
					<form:select path="passQuestionId" cssClass="formfield">
						<form:option value="-">Please select</form:option>
						<form:options items="${secretQuestions}"/>
                    </form:select>
                </td>
            </tr>
            <tr>

                <td class="label"><label for="passAnswer">Answer:</label></td>
                <td class="nowrap">
					<form:input path="passAnswer" cssClass="short" size="20" maxlength="25" />
                	<img src="/images/astrix.gif" alt="Required field" style="height: 15px; width: 15px;"/>
                </td>
            </tr>
		</table>
		</div>

	</div>
	
	<div class="form-submit">
		<input type="submit" class="submit" name="submit" value="Save Changes"/>
		<input type="reset" class="reset" name="reset" value="Undo Changes"/>
	</div>

</form:form>
</div>


</jsp:body>
</neon:userPage>
