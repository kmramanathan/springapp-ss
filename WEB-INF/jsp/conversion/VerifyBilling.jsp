<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<c:url var="submitButtonImageUrl" value="/conversion/images/common/bt-continue.png"/>

<neon:funnelPage title="Verify Billing" rightSidebar="GenericConversionSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<style>
li.instructions { font-size: 0.7em; }
</style>

<div id="title">
<img src="images/common/ban-upgrade-prem.png" alt="Upgrade to Full Membership" width="555" height="100" />
</div>
              
              <div id="formwrapper">
              
                <p>Please make sure that your name and address are the same as on the mailing address on your credit card statement.  The SearchSystems.net website protects your billing information and personal information through secure communications provided by Thawte SSL Certified encryption.
                </p>
              
                <p><span class="req"><strong>*</strong></span> Required Fields</p>
              
                <form:form method="post">

<form:errors path="*">
<b>Please correct the following errors:</b><br/>
</form:errors>
<form:errors path="*" element="div" id="error" cssClass="error-box"/>
  
                <ul class="ccdetails">

<c:if test="${!command.i18n}">
<li>
<strong><a href="verifyBilling.do?i18n=1">Outside the US? Click here</a></strong>
</li>
</c:if>

                    <li><label><span class="req"><strong>*</strong></span>Card Holder Name:</label><form:input path="name" cssClass="formfield" size="30" />
                    </li>
                    <li>
                      <div align="center"><span class="style1"> (As shown on your credit card)</span> </div>
                    </li>
                    <li><label><span class="req"><strong>*</strong></span>Credit Card Number:</label><form:input path="ccNumber" cssClass="formfield" size="30" /></li>
                    <li><label><span class="req"><strong>*</strong></span>Authorization Code:</label><form:input path="ccAuthCode" cssClass="formfield" size="10" />
                      </li>
                    <li>
                      <div align="left" class="small">
                        <div align="center">The Authorization Code for Visa and  MasterCard is the 3 digit number printed on the back<br /> of your credit  card. On American Express cards it is the four numbers printed on the  front.</div>
                      </div>
                    </li>
                    <li><label><span class="req"><strong>*</strong></span>Expiration Date:</label> 
                      <form:select path="ccExpMonth" cssClass="formfield">
						<form:option value="-">Month</form:option>
						<form:options items="${cardExpirationMonths}"/>
                      </form:select>
                        &nbsp; 
                      <form:select path="ccExpYear" cssClass="formfield">
						<form:option value="-">Year</form:option>
						<form:options items="${cardExpirationYears}"/>
                      </form:select>
                    </li>

	<li><label><span class="req"><strong>*</strong></span>Payment Plan:</label>

<c:if test="${!directPass}">
	<form:select path="rateId" cssClass="formfield">
		<form:option value="-">Select</form:option>
		<form:options items="${paymentPlansPremium}"/>
	</form:select>
</c:if>
<c:if test="${directPass}">
	<form:select path="rateId" cssClass="formfield">
		<form:option value="-">Select</form:option>
		<form:options items="${paymentPlansMember}"/>
	</form:select>
</c:if>

	<li class="checkbox"><label></label><form:checkbox path="recurringBilling" cssClass="formfield" /> 
    <strong>Yes,</strong> I'd like to be billed on a recurring basis.</li>

                </ul>
                
                <div class="divider"></div>

                <ul>
                    <li><label>Company Name:</label><form:input path="company" cssClass="formfield" size="40" /></li>
                    <li><label><span class="req"><strong>*</strong></span>Billing Address:</label> <form:input path="address" cssClass="formfield" size="40" /></li>
                    <li><label><span class="req"><strong>*</strong></span>Billing City:</label> <form:input path="city" cssClass="formfield" size="40" /></li>
<c:choose>

<c:when test="${command.i18n}">
<li><label>Province/State:</label> <form:input path="state" cssClass="formfield" size="12" /> (optional)</li>
<li><label>Postal Code:</label> <form:input path="postalCode" cssClass="formfield" size="12" /> (optional)</li>
<li><label><span class="req"><strong>*</strong></span>Country Code:</label> <form:input path="countryCode" cssClass="formfield" size="2" /> (2 letters)</li>
</c:when>

<c:otherwise>
<form:hidden path="countryCode" />
<li><label><span class="req"><strong>*</strong></span>Billing State:</label> 
	<form:select path="state" cssClass="formfield">
		<form:option value="-">Select</form:option>
		<form:options items="${states}"/>
	</form:select>
</li>
<li><label><span class="req"><strong>*</strong></span>Zip Code:</label> <form:input path="postalCode" cssClass="formfield" size="12" /></li>
</c:otherwise>

</c:choose>
                    <li><label><span class="req"><strong>*</strong></span>Phone:</label> <form:input path="phone" cssClass="formfield" size="26" /></li>
					<li><div align="left" class="small"><div align="center">
(Area code first. No parentheses or dashes, ex: 2135550010)
</div></div></li>

<c:if test="${command.search}">
                    <li><label><span class="req"><strong>*</strong></span>Email:</label> <form:input path="email" cssClass="formfield" size="26" /></li>
                    <li><label><span class="req"><strong>*</strong></span>Confirm E-mail:</label> <form:input path="confirmEmail" cssClass="formfield" size="26" /></li>
</c:if>
                    <li class="checkbox"><label></label><form:checkbox path="receiveNewsletter" cssClass="formfield" /> 
                    <strong>Yes</strong> I would like to receive the Search Systems Newsletter!</li>
                    <li class="checkbox"><label></label><form:checkbox path="acceptAgreement" cssClass="formfield" /> 
					I have read &amp; agree to the <a href="${showAgreementUrl}?version=funnel" target="_blank">User Agreement</a></li>
                </ul>
                
                <ul id="submit">

                    <li><label></label><input type="image" src="${submitButtonImageUrl}" alt="Upgrade" /></li>
					<li><div align="left" class="small"><div align="center">
If you encounter any problems, use our <a href="${contactUsUrl}" target="_blank">contact form</a> 
or call us at (800) 350-2232 (outside the US, call +1-805-375-4041). Customer service is available 
Monday to Friday, from 8:00 AM to 5:00 PM PST (UTC-8) or PDT (UTC-7).
</div></div></li>
                </ul>
                
                </form:form>

                <br /><br />

              </div>
</jsp:body>

</neon:funnelPage>