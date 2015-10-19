<%@ include file="/WEB-INF/jsp/include.jsp" %>
<c:url value="findUser.do"  var="findUser" />
<c:url value="addUser.do"   var="addUserUS"  />
<c:url value="addUser.do?userOutsideUS=true"   var="addUserIntl"  />
<c:url value="disableUser.do"   var="disableUser"  />
<c:url value="listCorporateAccounts.do"   var="corpAcct"  />
<c:url value="/admin/tools/listAdminActions.do"   var="listAdminActions"  />
<c:url value="deleteUser.do"   var="deleteUser"  />

<a href="${findUser}">Find a user</a><br/>
<a href="${disableUser}">Disable a user</a><br/>
<a href="${addUserUS}">Add user (US)</a><br/>
<a href="${addUserIntl}">Add user (Intl)</a><br/>
<a href="${corpAcct}">Corporate Accts</a><br/>

<c:if test="${sessionScope.adminUserSession.username eq 'judd'}">
<p/>
System Tools:<br/>
<a href="${listAdminActions}">List Admin Actions</a><br/>
<a href="${deleteUser}">Delete User</a><br/>
</c:if>
