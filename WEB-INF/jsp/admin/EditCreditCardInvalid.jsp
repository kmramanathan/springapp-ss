<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Edit Credit Card">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>Edit credit card</h2>
<a href="viewUser.do?username=${username}">View user</a><br/>
<%--
<a href="changeUserPassword.do?username=${command.user.username}">Change password for this user</a><br/>
<a href="addSubscription.do?username=${command.user.username}">Add a subscription for this user</a> 
--%>
<hr/>

User is inactive or disabled. Credit card cannot be updated until this is resolved.
Click "View User" to return.

</jsp:body>

</neon:page>

