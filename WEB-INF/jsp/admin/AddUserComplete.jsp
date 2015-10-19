<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Add User">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:body>

<h1>Add user</h1>
User ${username} was added successfully.<br/>
<c:out value="${ccResult}"/><p/>
<c:url value="viewUser.do?username=${username}"   var="viewUser"  />
<a href="viewUser.do?username=${username}">View this user</a>

</jsp:body>

</neon:page>