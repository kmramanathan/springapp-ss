<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<script type="text/javascript" src="/springapp/js/jquery-1.2.1.pack.js"></script>
<script type="text/javascript" src="/springapp/js/table.js"></script>
<script type="text/javascript" src="/springapp/js/selectPage.js"></script>

<%@attribute name="tableHeaders" fragment="true"%>
<%@attribute name="tableName" required="true" type="java.lang.String"%>
<%@attribute name="tableColumns" required="true" type="java.lang.Integer"%>
<%@attribute name="pageSize" required="true" type="java.lang.Integer"%>
<%@attribute name="rowCount" required="true" type="java.lang.Integer"%>

<style>
#${tableName} td.table-nav { padding: 5px; border: 1px dashed; }
#${tableName} td.table-nav-spacer { padding: 5px; border: 0px; }
</style>

<c:set var="pageCount" value="${(rowCount / pageSize) + ((rowCount % pageSize) gt 0 ? 1 : 0)}"/>

<c:if test="${rowCount > 0}">
<table id="${tableName}" class="autoLayout table-autopage:<c:out value="${pageSize}"/> table-autosort:-1 table-stripeclass:alternate">
	<thead>
	<tr>
	<td class="table-nav" colspan="${tableColumns}">
		Showing <c:out value="${pageSize}"/> results per page.<br/>
		Navigate to Page:<br/>		
		<a href="#" onclick="selectPage('previous', '${tableName}'); return false;">[ &lt;&lt;&nbsp;Previous ]</a>

		
		<%-- this forEach & paging stuff could use some work --%>
		<c:set var="currentPage" value="0"/>
		<c:forEach begin="0" end="${pageCount - 1}" step="1">
			<c:set var="nextPage" value="${currentPage + 1}"/>
			<c:url value="" var="pageUrl"/>
			<a href="#" id="page<c:out value="${nextPage}"/>" class="pagelink" onclick="selectPage(<c:out value="${currentPage}"/>, '${tableName}'); return false;"><c:out value="${nextPage}"/></a>
			<c:set var="currentPage" value="${currentPage + 1}"/>
		</c:forEach>

		<a href="#" onclick="selectPage('next', '${tableName}'); return false;">[ Next&nbsp;&gt;&gt; ]</a>
	</td>
	</tr>

	<tr><td class="table-nav-spacer" colspan="${tableColumns}">&nbsp;</td></tr>
	
	<tr>
		<jsp:invoke fragment="tableHeaders"/>
	</tr>

	</thead>
		
	<tbody>
		<jsp:doBody/>
	</tbody>
</table>
</c:if>

