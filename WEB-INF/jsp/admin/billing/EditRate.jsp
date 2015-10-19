<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Add/Edit Rate">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h1>Add/Edit Rate</h1>

<h3>Instructions</h3>
The Name and Description fields are displayed to customers in various
contexts. Name is intended for display in selector boxes, on forms and 
anywhere that a shorter name is required. Description is longer and 
is used for invoices, emails and credit card transactions. Name should
be a reasonable abbreviation of Description.<p/>

The Internal Notes field is NOT shown to customers. It can be used for 
additional notes about the rate. A suggestion is to use the name
of the Web site or advertising campaign that the rate is used for.<p/>

The recurring period and price are what will be used to rebill the rate.
The initial period and price can be used to set up an optional trial. 
If no trial is desired, set the initial period to 0 (zero) to disable 
the trial. To provide a FREE trial, set the initial period as desired and 
set the initial price to 0.00.<p/>

The flags are as follows:<br/>
<ul>
<li>Active is a general field. Rates which are not active are unavailable
for ANY use.</li>
<li>Allow New indicates whether new customers are allowed for this rate. 
Existing customers may continue to use the rate.</li>
<li>Require Approval indicates whether a customer with this rate must be
approved in advance by administrative staff.</li>
<li>Bill In Advance indicates whether the rate is billed in advance or
in arrears.</li>
</ul>

<h3>Rate Settings</h3>
<form:form method="post" commandName="rate" action="editRate.do">
	<form:errors path="*" cssClass="errorBox" element="div" />

	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Rate Name:</td>
			<td class="input"><form:input path="rateName" size="15"/></td>
			<td class="error"><form:errors path="rateName" /></td>
		</tr>
		<tr>
			<td class="label">Description:</td>
			<td class="input"><form:input path="rateDescription" size="40" /></td>
			<td class="error"><form:errors path="rateDescription" /></td>
		</tr>
		
		<tr>
			<td class="label">Internal Notes:</td>
			<td class="input"><form:input path="rateInternalDescription" size="40"/></td>
			<td class="error"><form:errors path="rateInternalDescription" /></td>
		</tr>		

		<tr>
			<td class="label">Initial Period:</td>
			<td class="input">
				<form:input path="initialUnits" size="15"/>
				<form:select path="initialPeriod"><form:options items="${billingPeriods}"/></form:select>
			</td>
		</tr>
		<tr>
			<td class="label">Initial Price:</td>
			<td class="input"><form:input path="initialPrice" size="15"/></td>
			<td class="error"><form:errors path="initialPrice" /></td>
		</tr>

		<tr>
			<td class="label">Recurring Period:</td>
			<td class="input">
				<form:input path="recurringUnits" size="15"/>
				<form:select path="recurringPeriod"><form:options items="${billingPeriods}"/></form:select>
			</td>
		</tr>
		<tr>
			<td class="label">Recurring Price:</td>
			<td class="input"><form:input path="recurringPrice" size="15"/></td>
			<td class="error"><form:errors path="recurringPrice" /></td>
		</tr>
			
		<tr>
			<td class="label">Active:</td>
			<td class="input"><form:checkbox path="active"/></td>
		</tr>
		<tr>
			<td class="label">Allow New:</td>
			<td class="input"><form:checkbox path="allowNew"/></td>
		</tr>
		<tr>
			<td class="label">Require Approval:</td>
			<td class="input"><form:checkbox path="requireApproval"/></td>
		</tr>
		<tr>
			<td class="label">Bill In Advance:</td>
			<td class="input"><form:checkbox path="billInAdvance"/></td>
		</tr>
	</table>
	
	<form:hidden path="rateId"/>
	<input type="submit"/>
</form:form>
</jsp:body>

</neon:page>
