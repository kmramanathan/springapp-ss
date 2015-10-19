<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View Subscriptions">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View subscriptions for user <c:out value="${username}"/></h2>
<style>
.subscriptions td { border: 1px dotted; padding: 5px; }
</style>

Value of foo: ${foo}

</jsp:body>

</neon:page>