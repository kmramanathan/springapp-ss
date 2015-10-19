<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Search Error" rightSidebar="SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<div id="title"><img src="images/common/confirm-search.gif" alt="Confirm Search" /></div>
              
<div id="formwrapper">

An error occurred during searching. We apologize for the inconvenience.
The error has been reported to our staff.

<strong>Your credit card will not be charged.</strong>

</div>
</jsp:body>

</neon:funnelPage>
