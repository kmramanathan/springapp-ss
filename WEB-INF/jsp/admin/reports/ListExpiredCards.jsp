<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="List Expired Credit Cards">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<script type="text/javascript" src="/springapp/js/jquery-1.2.1.pack.js"></script>
<script type="text/javascript" src="/springapp/js/table.js"></script>
<script type="text/javascript" src="/springapp/js/selectPage.js"></script>

<h2>Expired credit cards</h2>

Note: only active users are included in this list.

<hr/>
<h3>Results</h3>
<c:set var="pageSize" value="50"/>
<c:set var="pageCount" value="${rowCount / pageSize}"/>
<c:if test="${(pageSize < rowCount) && ((rowCount % pageSize) > 0)}">
	<c:set var="pageCount" value="${pageCount + 1}"/>
</c:if>

<c:out value="${rowCount}"/> results found.<br/>
<c:if test="${rowCount > 0}">
<table id="creditCards" class="autoLayout table-autopage:<c:out value="${pageSize}"/> table-autosort:-1 table-stripeclass:alternate">
	<thead>
	<tr>
	<td colspan="3">
		Showing <c:out value="${pageSize}"/> results per page.<br/>
		Navigate to Page:<br/>		
		<a href="#" onclick="selectPage('previous', 'creditCards'); return false;">[ &lt;&lt;&nbsp;Previous ]</a>
		
		<%-- this forEach & paging stuff could use some work --%>
		<c:set var="currentPage" value="0"/>
		<c:forEach begin="0" end="${pageCount - 1}" step="1">
			<c:set var="nextPage" value="${currentPage + 1}"/>
			<c:url value="" var="pageUrl"/>
			<a href="#" id="page<c:out value="${nextPage}"/>" class="pagelink" onclick="selectPage(<c:out value="${currentPage}"/>, 'creditCards'); return false;"><c:out value="${nextPage}"/></a>
			<c:set var="currentPage" value="${currentPage + 1}"/>
		</c:forEach>

		<a href="#" onclick="selectPage('next', 'creditCards'); return false;">[ Next&nbsp;&gt;&gt; ]</a>
	</td>
	</tr>
	
	<tr>
		<th class="table-sortable:alphanumeric">Username</th>
		<th class="table-sortable:alphanumeric">Name</th>
		<th class="table-sortable:alphanumeric">Email</th>
	</tr>
	</thead>
		
	<tbody>
	<c:forEach items="${creditCards}" var="cc">
		<c:url value="/admin/viewInvoiceDetails.do?invoiceId=" var="viewInvoiceDetailsUrl"/>
		<tr>
			<td><a href='<c:out value="${viewInvoiceDetailsUrl}"/>'><c:out value="${cc.username}"/></a></td>
			<td><c:out value="${cc.first_name}"/> <c:out value="${cc.last_name}"/></td>
			<td><c:out value="${cc.email}"/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>

</jsp:body>

</neon:page>