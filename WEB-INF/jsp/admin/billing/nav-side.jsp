<%@ include file="/WEB-INF/jsp/include.jsp" %>
<c:url value="listBillingReports.do"  var="listBillingReports" />
<c:url value="listRates.do"  var="listRates" />
<c:url value="viewSalesForecast.do"  var="salesForecast" />

<a href="${listRates}">Rates</a><br/>
<a href="${listBillingReports}">Billing reports</a><br/>
<a href="${salesForecast}">Sales Forecast</a><br/>