<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Sales Forecast">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>Sales Forecast</h2>

<hr/>
The sales forecast examines subscriptions to see what is due to rebill in the given
time interval. For more details, click on a rate name. The forecast is based on live data, so
activations and deactivations will be reflected here immediately.
<p/>

Setting the interval to 0 (zero) days will only show subscriptions which
are due to be rebilled now.<p/>

NOTE: Only the next billing cycle is considered for each subscription. A report for 
6 months will only include 1 billing cycle for a monthly subscription.<p/>

<style>
.forecast { border: 1px solid; }
.forecast td { padding: 5px; }
.forecast td.right { text-align: right; }
.forecast td.rightRed { text-align: right; color: #ff0000; }
</style>

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
				
<h3>Forecast for next ${daysAhead} days</h3>

<h4>Expected to rebill</h4>
<table class="forecast">
	<tr>
		<th>Rate ID</th>
		<th>Rate Name</th>
		<th>Subscribers</th>
		<th>Amount</th>
	</tr>
	<c:forEach items="${ratesRebill}" var="r">
		<c:url var="viewSalesForecast" value="viewSalesForecast.do?rateId=${r.rate_id}&daysAhead=${daysAhead}" />
		<tr>
			<td><a href="${viewSalesForecast}"><c:out value="${r.rate_id}"/></a></td>
			<td><c:out value="${r.rate_name}"/></td>
			<td class="right"><c:out value="${r.total_subs}"/></td>
			<td class="right"><c:out value="${r.total_amount}"/></td>
		</tr>
	</c:forEach>
</table>

<h4>Expected to be deactivated (customer requested no rebill)</h4>
<table class="forecast">
	<tr>
		<th>Rate ID</th>
		<th>Rate Name</th>
		<th>Subscribers</th>
		<th>Amount</th>
	</tr>
	<c:forEach items="${ratesNoRebill}" var="r">
		<c:url var="viewDeactForecast" value="viewSalesForecast.do?rateId=${r.rate_id}&deact=1&daysAhead=${daysAhead}" />
		<tr>
			<td><a href="${viewDeactForecast}"><c:out value="${r.rate_id}"/></a></td>
			<td><c:out value="${r.rate_name}"/></td>
			<td class="rightRed"><c:out value="${r.total_subs}"/></td>
			<td class="rightRed"><c:out value="${r.total_amount}"/></td>
		</tr>
	</c:forEach>
</table>

</jsp:body>

</neon:page>