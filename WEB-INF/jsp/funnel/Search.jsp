<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Enter Search" rightSidebar="SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<div id="formwrapper">

<c:if test="${command.nationwideSearch}">
<h1>Criminal Records: Nationwide Search</h1>

<p>Please input the exact first name, exact last name and date of birth to be searched. 
Middle initials, middle names, and suffixes such as Jr., Sr., III, M.D., and PhD CAN NOT be 
included in the search fields.  Then verify that the name and birth date are correct and 
press "Search" to begin. <a href="ourDatabases.do" target="_blank">Click here</a> to see our coverage.
<b>Results may include traffic violations, in addition to criminal felony, misdemeanor, and sex offender records</b></p>
</c:if>

<c:if test="${!command.nationwideSearch}">
<h1>Criminal Records: State Search</h1>

<p>Please input the exact first name, exact last name, date of birth, and state to be searched. 
Middle initials, middle names, and suffixes such as Jr., Sr., III, M.D., and PhD CAN NOT be 
included in the search fields.  Then verify that the name and birth date are correct and 
press "Search" to begin. <a href="ourDatabases.do" target="_blank">Click here</a> to see our coverage.
<b>Results may include traffic violations, in addition to criminal felony, misdemeanor, and sex offender records</b></p>
</c:if>

<p>
Click here for our <a href='<c:url value="/funnel/BestResultsGuide.html"/>' target='_blank'><b>guide to obtaining the best results.</b></a>
</p>

    <p class="note"><span class="req"><strong>*</strong></span> Required Fields</p>

    <form:form method="post">

        <ul>
			<li><form:errors path="*" element="div" id="error" cssClass="error-box" /></li>
            <li><label for="bgc_first_name"><span class="req"><strong>*</strong></span> First Name:</label> <form:input path="bgcFirstName" cssClass="formfield" size="20" maxlength="20" /></li>            
            <li><label for="bgc_last_name"><span class="req"><strong>*</strong></span> Last Name:</label> <form:input path="bgcLastName" cssClass="formfield" size="20" maxlength="20" /></li>

            <li><label for="bgc_dob"><span class="req"><strong>*</strong></span> Date of Birth:</label>
<form:select path="bgcDobMonth" cssClass="formfield">
<form:option value="0">Month</form:option>
<form:options items="${dobMonths}"/>
</form:select>&nbsp;
<form:select path="bgcDobDay" cssClass="formfield">
<form:option value="0">Day</form:option>
<form:options items="${dobDays}"/>
</form:select>&nbsp; 
<form:select path="bgcDobYear" cssClass="formfield">
<form:option value="0">Year</form:option>
<form:options items="${dobYears}"/>
</form:select>
            </li>

<c:if test="${!command.nationwideSearch}">
<li>
<label for="bgc_state"><span class="req"><strong>*</strong></span> State:</label>
<form:select path="bgcState" cssClass="formfield">
<form:option value="">[Choose]</form:option>
<form:options items="${usStates}"/>
</form:select>
</li>
</c:if>


        </ul>


            <div><span class="req"><strong>*</strong></span> Purpose of your search:<br />
            <form:select path="bgcPurpose" cssClass="formfield">
			<form:option value="">[Choose]</form:option>
            <form:options items="${bgcSearchPurposes}"/>
            </form:select>
            </div>

    <p align="center"><input class="go" type="submit" tabindex="3" value="Search"/></p>

<p>
<b>Don't have a Date of Birth for your subject?</b> Members save $10 on each state or 
nationwide search, may use our expanded search features to search by partial name, 
age (plus or minus up to 3 years), and opt to receive results that don't have a 
date of birth in the record. <a href="signup.do">Click here</a> if you'd like to 
become a SearchSystems.net Member.
</p>

    </form:form>
<hr/>

    <p>
All information retrieved from or through SearchSystems.net must be utilized 
in accordance with the <a href="${showAgreementUrl}?version=funnel" target="_blank">User Agreement</a> and all applicable state and federal laws, 
including the <a href="http://www.ftc.gov/os/statutes/fcra.htm" target="_blank">Fair Credit Reporting Act</a>; 
any violation of these will be grounds for immediate termination of your account without notice. 
</p>

    <p>Please see our <a href="${showAgreementUrl}?version=disclaimer" target="_blank">disclaimer</a> and also our <a href="${showAgreementUrl}?version=disclaimer" target="_blank">Notice to California Employers or Employers screening California residents</a>.</p>

</div>


</jsp:body>

</neon:funnelPage>