<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:userPage title="Index">
<jsp:attribute name="stylesheet">members-legacy.css</jsp:attribute>

<jsp:body>

<c:if test="${!empty message}">
  <b><font color="RED"><c:url value="${message}"/></font></b>
</c:if>

<form action="login.do" method="POST">

<c:if test="${!empty loginForwardAction}">
<input type="hidden" name="loginForwardAction" value="${loginForwardAction}"/>
</c:if>

<table align="center" border="0">
<tr>
<td colspan="2">Please enter your username and password.
<br />&nbsp;</td>
</tr>
<tr>
<td>Username:</td>
<td><input type="text" name="username" /></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="password" /></td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="submit" name="Login" /></td>
</tr>
</table>

</form>

</jsp:body>
</neon:userPage>

