<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Thank You" rightSidebar="GenericSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<div id="formwrapper">

Thank you. Your message has been received.<p/>

<a href="${closeWindowUrl}"><img src="images/common/bt-back-small.png" alt="Return" /></a>

</div>

</jsp:body>

</neon:funnelPage>
