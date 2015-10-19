<%@ include file="/WEB-INF/jsp/include.jsp" %>
<c:url value="/admin/index.do"   var="admin"  />
<c:url value="/search/index.do"  var="search" />
<c:url value="/admin/reports/index.do"  var="reports" />

<a href="${admin}">Users</a><br/>
<a href="${reports}">Reports</a><br/>
