<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Free BGR users">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>

<h2>List of Free Flat Rate Users</h2>
<hr/>

   <h3>Free FR Users</h3>
    <c:out value="${userCount}"/> results found.<br/>
    <c:set var="pageSize" value="50"/>
    <c:set var="pageCount" value="${userCount / pageSize}"/>
    <c:if test="${(pageSize < userCount) && ((userCount % pageSize) > 0)}">
    	<c:set var="pageCount" value="${pageCount + 1}"/>
    </c:if>
    
<c:if test="${userCount > 0}">
<table id="FRusers" class="autoLayout table-autopage:<c:out value="${pageSize}"/> table-autosort:-1 table-stripeclass:alternate">
	<thead>
	<tr>
		<td colspan="6">
			Showing <c:out value="${pageSize}"/> results per page.<br/>
			Navigate to Page:<br/>		
			<a href="#" onclick="selectPage('previous', 'FRusers'); return false;">[ &lt;&lt;&nbsp;Previous ]</a>
			
			<%-- this forEach & paging stuff could use some work --%>
			<c:set var="currentPage" value="0"/>
			<c:forEach begin="0" end="${pageCount - 1}" step="1">
				<c:set var="nextPage" value="${currentPage + 1}"/>
				<c:url value="" var="pageUrl"/>
				<a href="#" id="page<c:out value="${nextPage}"/>" class="pagelink" onclick="selectPage(<c:out value="${currentPage}"/>, 'FRusers'); return false;"><c:out value="${nextPage}"/></a>
				<c:set var="currentPage" value="${currentPage + 1}"/>
			</c:forEach>

			<a href="#" onclick="selectPage('next', 'FRusers'); return false;">[ Next&nbsp;&gt;&gt; ]</a>
		</td>
	</tr>
	
	<tr>
		<th class="table-sortable:alphanumeric">Username<br/>Email</th>
		<th class="table-sortable:currency">Subscription Id</th>
		<th class="table-sortable:alphanumeric">Create Date</th>
	</tr>
	</thead>

   	<tbody>
    <c:forEach items="${FRusers}" var="b">	    	
    	<c:url value="/admin/viewUser.do?username=${b.username}" var="viewUserUrl"/>
    	<c:url value="/admin/reports/showFRSubscription.do?transactionId=${b.transaction_id}" var="viewSubUrl"/>
		<tr>
    		<td>
    			<a href='<c:out value="${viewUserUrl}"/>'><c:out value="${b.username}"/></a><br/>
    			<c:out value="${b.email}"/>
    		</td>
    		<td>
				<a href='<c:out value="${viewSubUrl}"/>'><c:out value="${b.frsubscriptionid}"/></a><br/>    			
			</td>
    		<td><c:out value="${b.createdate}"/></td>
    	</tr>
    </c:forEach>
    </tbody>	    		
</table>    
</c:if>
    
</jsp:body>

</neon:page>