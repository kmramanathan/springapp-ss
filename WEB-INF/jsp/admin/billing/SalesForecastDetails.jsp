<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Sales Forecast">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>

<c:if test="${param.deact != 1}">
<c:set var="pageSize" value="25"/>
<h2>Sales Forecast</h2>
This forecast examines subscriptions to see what will rebill in the next ${daysAhead} days.<p/>
</c:if>

<c:if test="${param.deact == 1}">
<c:set var="pageSize" value="50"/>
<h2>Deactivation Forecast</h2>
This forecast examines subscriptions to see what will deactivate in the next ${daysAhead} days.
These deactivations will take place automatically because the customer declined 
automatic rebilling.<p/>
</c:if>

<%--
Setting the interval to 0 days will only show subscriptions which
are due to be rebilled now.<p/>

<form:form method="post">
Days ahead:
<form:select path="daysAhead">
	<form:option value="0"     label="Zero days"   />
	<form:option value="1"     label="1 day"       />
	<form:option value="7"     label="7 days"      />
	<form:option value="30"    label="30 days"     />
	<form:option value="90"    label="90 days"     />
	<form:option value="180"   label="180 days"    />
	<form:option value="270"   label="270 days"    />
	<form:option value="365"   label="365 days"    />
</form:select>
				
<input type="submit" value="Refresh" />
</form:form>
--%>

<hr/>
<h3>Forecast for next ${daysAhead} days</h3>

<style>
#subscriptions { border: 1px solid; }
#subscriptions td { padding: 5px; }
</style>

<neon:sortableTable pageSize="${pageSize}" rowCount="${rowCount}" tableColumns="5" tableName="subscriptions">
<jsp:attribute name="tableHeaders">
		<th>Sub ID</th>
		<th>Username</th>
		<th>Rate</th>
		<th>Amount</th>
		<th>Next Bill</th>
</jsp:attribute>
<jsp:body>
	<c:forEach items="${subs}" var="s">
		<c:url var="viewUser" value="/admin/viewUser.do?username=${s.username}" />
		<c:url var="viewSalesForecast" value="viewSalesForecast.do?rateId=${r.rate_id}" />

		<tr>
			<td><c:out value="${s.subscription_id}"/></td>
			<td><a href="${viewUser}"><c:out value="${s.username}"/></a></td>
			<td><c:out value="${s.rate_name}"/></td>
			<td><c:out value="${s.recurring_price}"/></td>
			<td><fmt:formatDate value="${s.next_bill_date}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
		</tr>
	</c:forEach>
</jsp:body>
</neon:sortableTable>

</jsp:body>

</neon:page>