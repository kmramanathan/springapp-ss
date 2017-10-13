<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:CrimeFunnel title="Search Error">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<div id="title">
<!-- <img src="images/common/confirm-search.gif" alt="Confirm Search" />  -->
</div>
              
<div id="formwrapper">
 <strong> We apologize as there was an error during your search. 
 There may have been a glitch in the system or your search may have produced too many records. 
 The system has notified us and we will look into this.
 Please be assured that your credit card has not been charged. 
</strong><br>

<!-- Edited on Feb 26 2017 for Mantis bug #859
An error occurred during searching. We apologize for the inconvenience.
The error has been reported to our staff.

<strong>Your credit card will not be charged.</strong><br>

It is possible that your search query produced more than the maximum number of
500 results. Please try again by refining your search terms. If you are
searching a common name, try using exact date of birth instead of year only.
Also, try un-checking the box to 'Include Records with No DOB.' 
 -->

</div>
</jsp:body>

</neon:CrimeFunnel>
