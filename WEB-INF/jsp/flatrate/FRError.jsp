<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>


<neon:funnelPage title="Error" rightSidebar="../funnel/SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<div id="title"><img src="images/common/confirm-search.gif" alt="Error" /></div>
              
<div id="formwrapper">

An error occurred. We apologize for the inconvenience.
The error has been reported to our staff.<p/>

<a href="FRLanding.do"><strong>Return to the start page</strong></a>

</div>
</jsp:body>

</neon:funnelPage>

