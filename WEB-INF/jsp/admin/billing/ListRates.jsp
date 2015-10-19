<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="List Rates">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>List Rates</h2>
<style>
.rates { border: 1px solid; }
.rates tr {  }
.rates td { border-top: 1px dotted; padding: 5px; }
.rates td.nowrap { white-space: no-wrap; }
</style>
<hr/>
<table class="rates" cellspacing="0">
	<tr>
		<th>Rate ID</th>
		<th>Name<br/>Description</th>
		<th>Initial</th>
		<th>Recurring</th>
		<th>Notes</th>
	</tr>
	<c:forEach items="${rates}" var="r">
		<tr>
			<td><a href="editRate.do?rateId=${r.rateId}"><c:out value="${r.rateId}"/></a></td>
			<td>
				<b>${r.rateName}</b><br/>
				${r.rateDescription}
			</td>
			<td class="nowrap">
			<c:choose>
				<c:when test="${r.initialPeriod > 0}">
					$${r.initialPrice} for ${r.initialUnits} ${billingPeriods[r.initialPeriod]}
				</c:when>
				<c:when test="${(r.initialPeriod == 0) && (r.initialPrice > 0)}">
					$${r.initialPrice} (item)
				</c:when>
				<c:otherwise>
					None
				</c:otherwise>				
			</c:choose>
			</td>
			<td class="nowrap">
			<c:choose>
				<c:when test="${r.recurringPeriod > 0}">
					$${r.recurringPrice} for ${r.recurringUnits} ${billingPeriods[r.recurringPeriod]}
				</c:when>
				<c:otherwise>
					None
				</c:otherwise>				
			</c:choose>
			</td>
			<td><c:out value="${r.rateInternalDescription}"/></td>
		</tr>
	</c:forEach>
</table>

</jsp:body>

</neon:page>