<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:userPage title="Update My Billing Information">
<jsp:attribute name="stylesheet">members-legacy.css</jsp:attribute>

<jsp:body>
<style>
li.instructions { font-size: 0.7em; }
</style>
              
<div id="formwrapper">
              
<p>Please make sure that your name and address are the same as on the mailing address on your credit card statement.  The SearchSystems.net website protects your billing information and personal information through secure communications provided by Thawte SSL Certified encryption.
</p>
              
<p><span class="req"><strong>*</strong></span>Required Fields</p>
              
<form:form method="post">

<form:errors path="*">
<b>Please correct the following errors:</b><br/>
</form:errors>
<form:errors path="*" element="div" id="error" cssClass="error-box"/>
  
<ul class="ccdetails">

<c:if test="${!command.i18n}">
	<li>
	<strong><a href="updateBillingInfo.do?i18n=1">Outside the US? Click here</a></strong>
	</li>
</c:if>

<li><label><span class="req"><strong>*</strong></span>Card Holder Name:</label><form:input path="name" cssClass="formfield" size="30" /></li>
<li><div align="center"><span class="style1">(As shown on your credit card)</span></div></li>
<li><label><span class="req"><strong>*</strong></span>Credit Card Number:</label><form:input path="ccNumber" cssClass="formfield" size="30" /></li>
<li><label><span class="req"><strong>*</strong></span>Authorization Code:</label><form:input path="ccAuthCode" cssClass="formfield" size="10" /></li>
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
	<li><div align="left" class="small"><div align="center">(Area code first. No parentheses or dashes, ex: 2135550010)</div></div></li>

	<li class="checkbox"><label></label><form:checkbox path="acceptAgreement" cssClass="formfield" />I have read &amp; agree to the <a href="${showAgreementUrl}?version=members" target="_blank">User Agreement</a></li>

</ul>
                
<ul id="submit">
	<li><label></label><input type="submit" value="Save Changes" /></li>
	<li><div align="left" class="small"><div align="center">
If you encounter any problems, use our <a href="${contactUsUrl}" target="_blank">contact form</a> 
or call us at (800) 350-2232 (outside the US, call +1-805-375-4041). Customer service is available 
Monday to Friday, from 8:00 AM to 5:00 PM PST (UTC-8) or PDT (UTC-7).
</div></div>
	</li>
</ul>
                
</form:form>

</div>
</jsp:body>

</neon:userPage>