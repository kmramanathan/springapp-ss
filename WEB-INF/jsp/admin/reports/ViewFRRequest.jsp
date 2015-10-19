<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="View FR Request">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View Flat Rate Request</h2>

<table class="FRRequest">
	<tr>
		<td class="label">Subscription ID</td>
		<td class="value"><c:out value="${frsub.subscriptionid}"/></td>
	</tr>
	<tr>
		<td class="label">User ID</td>
		<td class="value"><c:out value="${frsub.userid}"/></td>
	</tr>
	<tr>
		<td class="label">Plan Duration</td>
		<td class="value"><c:out value="${rate.rateName}"/></td>
	</tr>
	<tr>
		<td class="label">Plan Type</td>
		<td class="value"><c:out value="${rate.rateDescription}"/></td>
	</tr>
	<tr>
		<td class="label">Start Date</td>
		<td class="value"><c:out value="${frsub.startdate}"/></td>
	</tr>
	<tr>
		<td class="label">End Date</td>
		<td class="value"><c:out value="${frsub.enddate}"/></td>
	</tr>
	<tr>
		<td class="label">Days Available</td>
		<td class="value"><c:out value="${frsub.daysavailable}"/></td>
	</tr>
	<tr>
		<td class="label">Day close</td>
		<td class="value"><c:out value="${frsub.nextdate}"/></td>
	</tr>
	<tr>
		<td class="label">FindPeople Search Available</td>
		<td class="value"><c:out value="${frsub.findpeoplesearches}"/></td>
	</tr>
	<tr>
		<td class="label">Criminal Search Available</td>
		<td class="value"><c:out value="${frsub.criminalsearches}"/></td>
	</tr>

	<tr>
	
	    <c:if test="${frsub.status eq 0}">
	    	<c:set var="status" value="PipeLine" />
	    </c:if>
	    <c:if test="${frsub.status eq 1}">
	    	<c:set var="status" value="Active" />
	    </c:if>
	    <c:if test="${frsub.status eq 3}">
	    	<c:set var="status" value="Expired" />
	    </c:if>
	
		<td class="label">Status</td>
		<td class="value"><c:out value="${status}"/></td>
	</tr>	
	
	<tr>
		<td class="label">Create Date</td>
		<td class="value"><c:out value="${frsub.createdate}"/></td>
	</tr>
		
</table>

</jsp:body>

</neon:page>