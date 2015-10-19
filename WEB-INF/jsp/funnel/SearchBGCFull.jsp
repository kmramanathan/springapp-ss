<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Enter Search" rightSidebar="SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<div id="formwrapper">

<c:if test="${command.nationwideSearch}">
<h1>Criminal Records: Nationwide Search</h1>

<p>Please input the first name, last name and date of birth to be searched.
Choose your search options regarding exact or partial name and full DOB or year of birth. 
Middle initials, middle names, and suffixes such as Jr., Sr., III, M.D., and PhD SHOULD NOT be 
included in the first and last name fields.  Then verify that the name and birth date are correct and 
press "Search" to begin. <a href="ourDatabases.do" target="_blank">Click here</a> to see our coverage.
<b>Results may include traffic violations, in addition to criminal felony, misdemeanor, and sex offender records</b></p>
</c:if>

<c:if test="${!command.nationwideSearch}">
<h1>Criminal Records: State Search</h1>

<p>Please input the first name, last name, date of birth, and state to be searched. 
Choose your search options regarding exact or partial name and full DOB or year of birth.
Middle initials, middle names, and suffixes such as Jr., Sr., III, M.D., and PhD SHOULD NOT be 
included in the first and last name fields.  Then verify that the name and birth date are correct and 
press "Search" to begin. <a href="ourDatabases.do" target="_blank">Click here</a> to see our coverage.
<b>Results may include traffic violations, in addition to criminal felony, misdemeanor, and sex offender records</b></p>
</c:if>

<p>
Click here for our <a href='<c:url value="/funnel/BestResultsGuide.html"/>' target='_blank'><b>guide to obtaining the best results.</b></a>
</p>


    <p class="note"><span class="req"><strong>*</strong></span> Required Fields</p>

    <form:form method="post">

        <ul>
<li></li>
<li><b>Basic Options</b></li>
			<li><form:errors path="*" element="div" id="error" cssClass="error-box" /></li>
            <li>
<label for="bgc_first_name"><span class="req"><strong>*</strong></span> First Name:</label> 
<form:input path="bgcFirstName" cssClass="formfield" size="20" maxlength="20" />
<form:checkbox path="bgcFirstNameExact" cssClass="formfield" /> Exact Match
</li>            
            <li>
<label for="bgc_last_name"><span class="req"><strong>*</strong></span> Last Name:</label> 
<form:input path="bgcLastName" cssClass="formfield" size="20" maxlength="20" />
<form:checkbox path="bgcLastNameExact" cssClass="formfield" /> Exact Match
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

<li><b>Date of Birth Options</b></li>
<li><i>Search by date of birth OR year of birth (choose one):</i></li>

            <li>
<label for="bgc_dob"><span class="req"><strong>*</strong></span> Date of Birth:</label>

<form:radiobutton path="bgcDobRange" value="false" />
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

            <li>
<label for="bgc_dob"><span class="req"><strong>*</strong></span> Year of Birth:</label>
<form:radiobutton path="bgcDobRange" value="true" />
<form:select path="bgcDobRangeBaseYear" cssClass="formfield">
<form:option value="0">Year</form:option>
<form:options items="${dobYears}"/>
</form:select>
within
<form:select path="bgcDobRangeFuzz" cssClass="formfield">
<form:option value="0">0</form:option>
<form:option value="1">1</form:option>
<form:option value="2">2</form:option>
<form:option value="3">3</form:option>
</form:select>
years
            </li>

<li><b>Additional Options</b></li>
<li>
<label for="bgcMatchMissingDates">Missing DOB:</label> 
<form:checkbox path="bgcMatchMissingDates" cssClass="formfield" /> Include results where DOB is missing or unavailable
</li>
<li class="instructions">Caution: A common name may give you too many results. Your limit is 500.</li>

<li>
<label for="bgcMiddleInitial">Middle Initial:</label> 
<form:input path="bgcMiddleInitial" cssClass="formfield" size="2" maxlength="2" />
(optional)
</li>
<li class="instructions">Search results will include records where there is a match on the middle 
initial, or the middle name starts with the letter you enter. Records that don't 
have a middle name or initial will also be included in your results.</li>

<li>
<label for="bgcReferenceCode">Reference Code:</label> 
<form:input path="bgcReferenceCode" cssClass="formfield" size="20" maxlength="20" />
(optional)
</li>
<li class="instructions">You can enter an optional reference code for your own tracking 
purposes. The code will appear on your credit card billing statement. (PLEASE NOTE: 
Reference code may not be available for some credit cards.)</li>

<li><b>Purpose of your search</b></li>
<li>
	<div><span class="req"><strong>*</strong></span>
	<form:select path="bgcPurpose" cssClass="formfield">
		<form:option value="">[Choose]</form:option>
		<form:options items="${bgcSearchPurposes}"/>		
	</form:select>
	</div>
</li>

        </ul>

    <p align="center"><input class="go" type="submit" tabindex="3" value="Search"/></p>

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