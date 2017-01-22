<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Corporate Invoice">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>
<script>
	$(document).ready(function(){
		$('#btn').bind('click', function() {
		    if($("#accountId").val() == "-") {
				$("#errorLblForAccount").show();
				return false;
		    }
			if($("#searchType").val() == "-") {
				$("#errorLblForSearchType").show();
				return false;
		    }
			if($("#year").val() == "-") {
				$("#errorLblForYear").show();
				return false;
		    }
			if($("#month").val() == "-") {
				$("#errorLblForMonth").show();
				return false;
		    }
			$('#searchCorpInvoicing').submit();
		});
		
		$('#accountId').bind('change', function() {
			if($("#accountId").val() != "-") {
				$("#errorLblForAccount").hide();
			}
		});
		
		$('#searchType').bind('change', function() {
			if($("#searchType").val() != "-") {
				$("#errorLblForSearchType").hide();
			}
		});
		
		$('#year').bind('change', function() {
			if($("#year").val() != "-") {
				$("#errorLblForYear").hide();
			}
		});
		
		$('#month').bind('change', function() {
			if($("#month").val() != "-") {
				$("#errorLblForMonth").hide();
			}
		});
	});
</script>
<h2>Corporate Invoice</h2>
<hr/>

<form:form method="post" id="searchCorpInvoicing">
	<table border="0" cellspacing="0" width="600">
		<tr>
			<td class="label" width="15%">Account:</td>
			<td class="input">
				<form:select path="accountId">
					<form:option value="-"  label="Select" />
					<form:options items="${corporateAccounts}" />
				</form:select><div id="errorLblForAccount" style="display:none;color:red;float:left"><b>please select</b></div>
			</td>
		</tr>
		<tr>
			<td class="label">Search Type:</td>
			<td class="input">
				<form:select path="searchType">
					<form:option value="-"  label="Select" />
					<form:options items="${searchTypes}" />
				</form:select><div id="errorLblForSearchType" align="left" style="display:none;color:red;float:left"><b>please select</b></div>
			</td>
		</tr>
		<tr>
			<td class="label">Year:</td>
			<td class="input">
				<form:select path="year">
					<form:option value="-"  label="Select" />
					<form:options items="${years}" />
				</form:select><div id="errorLblForYear" align="left" style="display:none;color:red;float:left"><b>please select</b></div>
			</td>
		</tr>
		<tr>
			<td class="label">Month:</td>
			<td class="input">
				<form:select path="month">
					<form:option value="-"  label="Select" />
					<form:options items="${months}" />
				</form:select><div id="errorLblForMonth" align="left" style="display:none;color:red;float:left"><b>please select</b></div>
			</td>
		</tr>
		<tr>
			<td><input id="btn" type="button" value="Get Searches" /></td>
		</tr>
	
	</table>	
</form:form>

<c:if test="${searches != null}">
	<hr/>

    <h3>Searches</h3>
    <c:out value="${searchCount}"/> results found.<br/>

<c:if test="${searchCount > 0}">
<a href="corporateInvoicingDownload.do?searchType=${searchType}&month=${month}&year=${year}&accountId=${accountId}">Download results as CSV file</a>

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
		<th class="table-sortable:alphanumeric">Search ID</th>
		<th class="table-sortable:alphanumeric">User Name</th>
		<th class="">Date/Time</th>
		<th class="table-sortable:alphanumeric">Category</th>
		<th class="table-sortable:alphanumeric">First Name</th>
		<th class="table-sortable:alphanumeric">Last Name</th>
		<c:if test="!${s['businessname']}">
		<th class="table-sortable:alphanumeric">Business Name</th>
		</c:if>
		<th class="table-sortable:alphanumeric"># of Results</th>
		<th class="table-sortable:alphanumeric">State</th>
		<th class="table-sortable:alphanumeric">Price</th>
	</tr>
</c:if>

<c:if test="${searchType == 4}">	
	<tr>
		<th class="table-sortable:alphanumeric">Search ID</th>
		<th class="table-sortable:alphanumeric">User Name</th>
		<th class="table-sortable:alphanumeric">Date</th>
		<th class="table-sortable:alphanumeric">Category</th>
		<th class="table-sortable:alphanumeric">First Name</th>
		<th class="table-sortable:date">Last Name</th>
		<c:if test="!${s['businessname']}">
		<th class="table-sortable:alphanumeric">Business Name</th>
		</c:if>
		<th class="table-sortable:alphanumeric"># of Results</th>
		<th class="table-sortable:alphanumeric">State</th>
		<th class="table-sortable:alphanumeric">Price</th>
	</tr>
</c:if>
<c:if test="${searchType == 5}">	
	<tr>
		<th class="table-sortable:alphanumeric">Request ID</th>
		<th class="table-sortable:alphanumeric">User Name</th>
		<th class="">Date / Time</th>
		<th class="table-sortable:alphanumeric">Product</th>
		<th class="table-sortable:alphanumeric">Description</th>
		<th class="table-sortable:alphanumeric">Price</th>
		<th class="table-sortable:alphanumeric"># of Results</th>
		<th class="table-sortable:alphanumeric">First Name</th>
		<th class="table-sortable:alphanumeric">Last Name</th>
		<th class="table-sortable:alphanumeric">Reference Code</th>
		
	</tr>
</c:if>

	</thead>

   	<tbody>
    <c:forEach items="${searches}" var="s">
		<c:set var="id" value="${s['bgc_request_id']}"/>
		<c:set var="nss_id" value="${s['nss_request_id']}" />
    	<c:url value="/admin/viewUser.do?username=${s['username']}" var="viewUserUrl"/>
		<c:url value="/admin/reports/viewBGCRequest.do?transactionId=${id}" var="viewBGCUrl"/>
		<c:url value="/admin/reports/ViewNSSRequest.do?transactionId=${nss_id}" var="viewNssUrl"/>

<c:if test="${searchType == 1}">
    	<tr>
			<td><a href="${viewBGCUrl}"><c:out value="${s['bgc_request_id']}"/></a></td>
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
			<td><c:out value="${s['username']}"/></td>
			<td><fmt:formatDate value="${s['create_date']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${s['category']}"/></td>
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
			<td><c:out value="${s['user_search_id']}"/></td>
			<td><c:out value="${s['username']}"/></td>
			<td><fmt:formatDate value="${s['create_date']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${s['category']}"/></td>
			<td><c:out value="${s['firstname']}"/></td>
			<td><c:out value="${s['lastname']}"/></td>
			<c:if test="!${s['businessname']}">
			<td><c:out value="${s['businessname']}"/></td>
			</c:if>
			<td><c:out value="${s['match_count']}"/></td>
			<td><c:out value="${s['state']}"/></td>
			<td><c:out value="${s['price']}"/></td>
			
    	</tr>
</c:if>

<c:if test="${searchType == 4}">
    	<tr>
			<td><c:out value="${s['user_search_id']}"/></td>
			<td><c:out value="${s['username']}"/></td>
			<td><fmt:formatDate value="${s['create_date']}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
			<td><c:out value="${s['category']}"/></td>
			<td><c:out value="${s['firstname']}"/></td>
			<td><c:out value="${s['lastname']}"/></td>
			<c:if test="!${s['businessname']}">
			<td><c:out value="${s['businessname']}"/></td>
			</c:if>
			<td><c:out value="${s['match_count']}"/></td>
			<td><c:out value="${s['state']}"/></td>
			<td><c:out value="${s['price']}"/></td>
		</tr>
</c:if>
<c:if test="${searchType == 5}">
    	<tr>
			<td><a href="${viewBGCUrl}"><c:out value="${s['nss_request_id']}"/></a></td>
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

    </c:forEach>
    </tbody>	    		
</table>    
</c:if>
</c:if>

    
</jsp:body>

</neon:page>