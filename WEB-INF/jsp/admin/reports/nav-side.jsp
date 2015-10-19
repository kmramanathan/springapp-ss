<%@ include file="/WEB-INF/jsp/include.jsp" %>
<c:url value="orders.do"  var="orders" />
<c:url value="freeBGRusers.do"  var="FBGRU" />
<c:url value="freeFRusers.do"  var="FFR" />
<c:url value="listModifiedCards.do"  var="modified" />
<c:url value="listExpiredCards.do"  var="expired" />
<c:url value="corporateInvoicing.do"  var="corpInvoice" />
<c:url value="FRInvoicing.do"  var="FRInvoice" />
<c:url value="orders.do"  var="DecRegister" />
<c:url value="newUsers.do"  var="newUsers" />

<a href="${orders}">Show orders</a><br/>
<a href="${FBGRU}">Free BGR Users</a><br/>
<a href="${FFR}">Free FR Users</a><br/>
<a href="${modified}">Modified Cards</a><br/>
<a href="${expired}">Expired Cards</a><br/>
<a href="${corpInvoice}">Corporate Invoicing</a><br/>
<a href="${FRInvoice}">Flat Rate Invoicing</a><br/>

<a href="${newUsers}">Show New Users</a><br/>