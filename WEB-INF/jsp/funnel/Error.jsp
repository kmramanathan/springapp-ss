<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%  boolean bjl = false;
	if(session.getAttribute("bjlSearchFormCommand") != null) {
		bjl = true;
	}
	if(bjl){ %>
<neon:funnelPage title="Error" rightSidebar="SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<div id="title"><img src="images/common/confirm-search.gif" alt="Error" /></div>
              
<div id="formwrapper">

An error occurred. We apologize for the inconvenience.
The error has been reported to our staff.<p/>

<a href="searchBJL.do">Return to the start page</a>

</div>
</jsp:body>

</neon:funnelPage>
<% } else { %>

<neon:funnelPage title="Error" rightSidebar="SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<div id="title"><img src="images/common/confirm-search.gif" alt="Error" /></div>
              
<div id="formwrapper">

An error occurred. We apologize for the inconvenience.
The error has been reported to our staff.<p/>

<a href="landing.html">Return to the start page</a>

</div>
</jsp:body>

</neon:funnelPage>

<% } %>