<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page isErrorPage="true" %>

<neon:funnelPageOneCol title="Error">

<jsp:attribute name="stylesheet">funnel-onecol.css</jsp:attribute>
<jsp:body>

<div id="title">
</div>

<div id="formwrapper">

<h3>Application Error</h3>

<p>An error was detected while processing this request. We apologize for the 
inconvenience. This error has been reported to our administrative staff.</p>

<c:if test="${sessionScope.adminUserSession != null}">
<c:if test="${exception != null}">
<p>Message: ${exception.message}</p>
<p>Cause: ${exception.cause}</p>
</c:if>
</c:if>

</div>

</jsp:body>
</neon:funnelPageOneCol>
