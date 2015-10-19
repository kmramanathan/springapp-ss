<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Flat Rate Invoice">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>

<h2>Flat Rate Invoice</h2>
<hr/>

<form:form method="post">
	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username:</td>
			<td class="input">
				<form:select path="userId">
					<form:option value="-"  label="Select" />
					<form:options items="${FRMonthlyAccounts}" />
				</form:select>
			</td>
		</tr>
		<tr>
			<td class="label">Month:</td>
			<td class="input">
				<form:select path="month">
					<form:option value="-"  label="Select" />
					<form:options items="${months}" />
				</form:select>
			</td>
		</tr>
		<tr>
			<td class="label">Year:</td>
			<td class="input">
				<form:select path="year">
					<form:option value="-"  label="Select" />
					<form:options items="${years}" />
				</form:select>
			</td>
		</tr>
		<tr>
			<td class="label">Search Type:</td>
			<td class="input">
				<form:select path="searchType">
					<form:option value="-"  label="Select" />
					<form:options items="${searchTypes}" />
				</form:select>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="Get Searches" /></td>
		</tr>
	
	</table>	
</form:form>

<c:if test="${empty corp.corporateName}">
	<c:set var="corporateName" value="N/A" />
</c:if>
<c:if test="${!empty corp.corporateName}">
	<c:set var="corporateName" value="${corp.corporateName}" />
</c:if>

<c:if test="${searches != null}">
	<hr/>

    <h3>Searches</h3>
    <c:out value="${searchCount}"/> results found.<br/>

<c:if test="${searchCount > 0}">
<a href="FRInvoicingDownload.do?searchType=${searchType}&month=${month}&year=${year}&userId=${userId}">Download results as CSV file</a>

    <c:set var="pageSize" value="50"/>
    <c:set var="pageCount" value="${searchCount / pageSize}"/>
    <c:if test="${(pageSize < searchCount) && ((searchCount % pageSize) > 0)}">
    	<c:set var="pageCount" value="${pageCount + 1}"/>
    </c:if>
    
<table id="orders" class="autoLayout table-autopage:<c:out value="${pageSize}"/> table-autosort:-1 table-stripeclass:alternate">
	<thead>
	<tr>
		<td colspan="6">
			Showing <c:out value="${pageSize}"/> results per page.<br/>
			Navigate to Page:<br/>		
			<a href="#" onclick="selectPage('previous', 'orders'); return false;">[ &lt;&lt;&nbsp;Previous ]</a>
			
			<%-- this forEach & paging stuff could use some work --%>
			<c:set var="currentPage" value="0"/>
			<c:forEach begin="0" end="${pageCount - 1}" step="1">
				<c:set var="nextPage" value="${currentPage + 1}"/>
				<c:url value="" var="pageUrl"/>
				<a href="#" id="page<c:out value="${nextPage}"/>" class="pagelink" onclick="selectPage(<c:out value="${currentPage}"/>, 'orders'); return false;"><c:out value="${nextPage}"/></a>
				<c:set var="currentPage" value="${currentPage + 1}"/>
			</c:forEach>

			<a href="#" onclick="selectPage('next', 'orders'); return false;">[ Next&nbsp;&gt;&gt; ]</a>
		</td>
	</tr>
<c:if test="${searchType == 1}">	
	<tr>
		<th class="table-sortable:alphanumeric">Request ID</th>
		<th class="table-sortable:alphanumeric">Corporate Name</th>
		<th class="table-sortable:alphanumeric">Username</th>
		<th class="">Date/Time</th>
		<th class="table-sortable:alphanumeric">Product</th>
		<th class="table-sortable:alphanumeric">Description</th>
		<th class="table-sortable:alphanumeric">Price</th>
		<th class="table-sortable:alphanumeric"># of Results</th>
		<th class="table-sortable:alphanumeric">First Name</th>
		<th class="table-sortable:alphanumeric">Last Name</th>
		<th class="table-sortable:alphanumeric">Reference Code</th>
	</tr>
</c:if>
<c:if test="${searchType == 2}">	
	<tr>
		<th class="table-sortable:alphanumeric">Search ID</th>
		<th class="table-sortable:alphanumeric">Corporate Name</th>
		<th class="table-sortable:alphanumeric">Username</th>
		<th class="">Date/Time</th>
		<th class="table-sortable:alphanumeric">Category</th>
		<th class="table-sortable:alphanumeric">Subcategory</th>
		<th class="table-sortable:alphanumeric">Price</th>
		<th class="table-sortable:alphanumeric"># of Results</th>
		<th class="table-sortable:alphanumeric">Name</th>
		<th class="table-sortable:alphanumeric">SSN/Tax ID</th>
		<th class="table-sortable:alphanumeric">Defendant</th>
		<th class="table-sortable:alphanumeric">Reference Code</th>
	</tr>
</c:if>
<c:if test="${searchType == 3}">	
	<tr>
		<th class="table-sortable:alphanumeric">Request ID</th>
		<th class="table-sortable:alphanumeric">Corporate Name</th>
		<th class="table-sortable:alphanumeric">User Name</th>
		<th class="table-sortable:alphanumeric">Product</th>
		<th class="">Date/Time</th>
		<th class="table-sortable:alphanumeric">First Name</th>
		<th class="table-sortable:alphanumeric">Last Name</th>
		<th class="table-sortable:alphanumeric">State</th>
		<th class="table-sortable:alphanumeric">Price</th>
	</tr>
</c:if>

<c:if test="${searchType == 4}">	
	<tr>
		<th class="table-sortable:alphanumeric">Subscription ID</th>
		<th class="table-sortable:alphanumeric">Corporate Name</th>
		<th class="table-sortable:alphanumeric">User Name</th>
		<th class="table-sortable:alphanumeric">Plan</th>
		<th class="table-sortable:alphanumeric">Plan Type</th>
		<th class="table-sortable:date">Start Date</th>
		<th class="table-sortable:date">End Date</th>
		<th class="table-sortable:alphanumeric">Price</th>
		<th class="table-sortable:date">Create Date</th>
	</tr>
</c:if>

	</thead>

   	<tbody>
    <c:forEach items="${searches}" var="s">
		<c:set var="id" value="${s['bgc_request_id']}"/>
    	<c:url value="/admin/viewUser.do?username=${s['username']}" var="viewUserUrl"/>
		<c:url value="/reports/viewBGCRequest.do?requestId=${id}" var="viewBGCUrl"/>

<c:if test="${searchType == 1}">
    	<tr>
			<td><a href="${viewBGCUrl}"><c:out value="${s['bgc_request_id']}"/></a></td>
			<td><c:out value="${corporateName}"/></td>
			<td><c:out value="${s['username']}"/></td>
			<td><fmt:formatDate value="${s['date_created']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${s['bgc_product_name']}"/></td>
			<td><c:out value="${s['bgc_product_desc']}"/></td>
			<td><c:out value="${s['price']}"/></td>
			<td><c:out value="${s['quantity_returned']}"/></td>
			<td><c:out value="${s['first_name']}"/></td>
			<td><c:out value="${s['last_name']}"/></td>
			<td><c:out value="${s['reference_code']}"/></td>
    	</tr>
</c:if>

<c:if test="${searchType == 2}">
    	<tr>
			<td><c:out value="${s['user_search_id']}"/></td>
			<td><c:out value="${corporateName}"/></td>
			<td><c:out value="${s['username']}"/></td>
			<td><fmt:formatDate value="${s['create_date']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${s['category']}"/></td>
			<td><c:out value="${s['subcategory']}"/></td>
			<td><c:out value="${s['price']}"/></td>
			<td><c:out value="${s['match_count']}"/></td>
			<td><c:out value="${s['name']}"/></td>
			<td><c:out value="${s['defendant_ssn_tax_id']}"/></td>
			<td><c:out value="${s['defendant']}"/></td>
			<td><c:out value="${s['reference_code']}"/></td>
    	</tr>
</c:if>

<c:if test="${searchType == 3}">
    	<tr>
			<td><c:out value="${s['request_id']}"/></td>
			<td><c:out value="${corporateName}"/></td>
			<td><c:out value="${s['username']}"/></td>
			<td><c:out value="${s['product']}"/></td>
			<td><fmt:formatDate value="${s['created_date']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${s['first_name']}"/></td>
			<td><c:out value="${s['last_name']}"/></td>
			<td><c:out value="${s['state']}"/></td>
			<td><c:out value="${s['price']}"/></td>
			
    	</tr>
</c:if>

<c:if test="${searchType == 4}">
    	<tr>
			<td><c:out value="${s['subscriptionid']}"/></td>
			<td><c:out value="${corporateName}"/></td>
			<td><c:out value="${s['username']}"/></td>
			<td><c:out value="${s['rate_name']}"/></td>
			<td><c:out value="${s['rate_description']}"/></td>
			<td><fmt:formatDate value="${s['startdate']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><fmt:formatDate value="${s['enddate']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${s['recurring_price']}"/></td>
			<td><fmt:formatDate value="${s['createdate']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
						
    	</tr>
</c:if>

    </c:forEach>
    </tbody>	    		
</table>    
</c:if>
</c:if>

    
</jsp:body>

</neon:page>