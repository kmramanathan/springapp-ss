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


<c:set var="pageCount" value="${(rowCount / pageSize) + ((rowCount % pageSize) gt 0 ? 1 : 0)}"/>

<c:if test="${rowCount > 0}">
<table id="${tableName}" class="autoLayout table-autopage:<c:out value="${pageSize}"/> 
table-autosort:-1 table-stripeclass:alternate">
	<thead>
	<tr>
	<td colspan="3"><div align="left" class="style107"> ${rowCount} Matches Found</div></td>
	<td class="table-nav" colspan="2"><div align="right" class="style108">
		<a href="#" onclick="selectPage('previous', '${tableName}'); return false;">Previous</a>

		
		<%-- this forEach & paging stuff could use some work --%>
		<c:set var="currentPage" value="0"/>
		<c:forEach begin="0" end="${pageCount - 1}" step="1">|
			<c:set var="nextPage" value="${currentPage + 1}"/>
			<c:url value="" var="pageUrl"/>
			<a href="#" id="page<c:out value="${nextPage}"/>" class="pagelink"
			 onclick="selectPage(<c:out value="${currentPage}"/>, '${tableName}'); return false;">
			 <c:out value="${nextPage}"/></a>
			<c:set var="currentPage" value="${currentPage + 1}"/>
		</c:forEach>|

		<a href="#" onclick="selectPage('next', '${tableName}'); return false;">Next</a>
		</div>
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

