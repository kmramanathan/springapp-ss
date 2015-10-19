<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Show Orders">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>
<style type="text/css">
.newcustomer{background:green;color:white;}
.newcustomer #newuser{position:absolute;margin-left:130px;color:#ff9900;font-weight:bold;}
</style>
<h2>Show orders</h2>
<hr/>

<h3>Display filters</h3>

The default search shows all orders for the past 24 hours.<br/>
Any search over 7 days must use at least 1 field for filtering.<br/>

<form:form method="post" commandName="command" action="orders.do">
	<form:errors path="*" cssClass="errorBox" element="div" />

	<table border="0" cellspacing="0">
		<tr>
			<td class="label">Username:</td>
			<td class="input">
				<form:input path="username"/>
			</td>
			<td class="error">
          		<form:errors path="username" />
        	</td>
		</tr>
		
		<tr>
			<td class="label">Email:</td>
			<td class="input">
				<form:input path="email"/>
			</td>
			<td class="error">
          		<form:errors path="email" />
        	</td>
		</tr>
		
		<tr>
			<td class="label">Transaction:</td>
			<td class="input">
				<form:input path="transaction"/>
			</td>
			<td class="error">
          		<form:errors path="transaction" />
        	</td>
		</tr>
		
		<tr>
			<td class="label">Last 4 of CC:</td>
			<td class="input">
				<form:input path="last4"/>
			</td>
			<td class="error">
          		<form:errors path="last4" />
        	</td>
		</tr>
		
		<tr>
			<td class="label">Amount:</td>
			<td class="input">
				<form:input path="amount"/>
			</td>
			<td class="error">
          		<form:errors path="amount" />
        	</td>
		</tr>
		
		<tr>
			<td class="label">Search past:</td>
			<td class="input">
				<form:select path="daysToSearch">
					<form:option value="1"     label="24 hours"      />
					<form:option value="7"     label="7 days"      />
					<form:option value="30"    label="30 days"     />
					<form:option value="90"    label="90 days"     />
					<form:option value="365"   label="365 days"    />
					<form:option value="10000" label="All records" />
				</form:select>
			</td>
			<td class="error">
          		<form:errors path="daysToSearch" />
        	</td>
		</tr>

		<tr>
			<td>Partial match</td>
			<td><form:checkbox path="partialMatch"/></td>
			<td>Supported for username/email only.</td>
		</tr>		
	</table>
	
	<input type="submit" value="Search Orders" />	
</form:form>


	<hr/>
    <h3>Orders</h3>
    <c:out value="${orderCount}"/> results found.<br/>
    <c:set var="pageSize" value="50"/>
    <c:set var="pageCount" value="${orderCount / pageSize}"/>
    <c:if test="${(pageSize < orderCount) && ((orderCount % pageSize) > 0)}">
    	<c:set var="pageCount" value="${pageCount + 1}"/>
    </c:if>
    
<c:if test="${orderCount > 0}">
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
	
	<tr>
		<th class="table-sortable:alphanumeric">Username<br/>Email</th>
		<th class="table-sortable:alphanumeric">Transaction</th>
		<th class="table-sortable:currency">Cost</th>
		<th class="table-sortable:alphanumeric">Status</th>
		<th class="table-sortable:alphanumeric">Description</th>
		<th class="">Date/Time</th>
	</tr>
	</thead>

   	<tbody>
    <c:forEach items="${orders}" var="o">	    	
    	<c:url value="/admin/viewUser.do?username=${o.username}" var="viewUserUrl"/>
		<c:url value="viewTransaction.do?transactionId=${o.invoiceNumber}" var="viewTxnUrl"/>
		<c:url value="viewBJLRequest.do?transactionId=${o.invoiceNumber}" var="viewBJLUrl"/>
		<c:url value="viewBGCRequest.do?transactionId=${o.invoiceNumber}" var="viewBGCUrl"/>
		<c:url value="showFRSubscription.do?transactionId=${o.invoiceNumber}" var="viewFRUrl"/>
		<c:url value="ViewEvictionRequest.do?transactionId=${o.invoiceNumber}" var="viewEvicUrl"/>
		<c:url value="ViewCorpRequest.do?transactionId=${o.invoiceNumber}" var="viewCorpUrl"/>
		<c:url value="ViewNSSRequest.do?transactionId=${o.invoiceNumber}" var="viewNSSUrl"/>
		<c:url value="ViewRealPropRequest.do?transactionId=${o.invoiceNumber}" var="viewRealPropUrl"/>
		
<c:choose>

<c:when test="${o.searchCategoryId == 5}">
<c:set var="description" value="${o.type}"/>
<c:set var="detailsUrl" value=""/>
</c:when>


<c:when test="${o.searchCategoryId == 30}">
<c:set var="description" value="${o.type} ${o.subtype}"/>
<c:set var="detailsUrl">
<a target="_blank" href="${viewBGCUrl}">(Details)</a>
</c:set>
</c:when>

<c:when test="${o.searchCategoryId == 59}">
<c:set var="description" value="${o.type} ${o.subtype}"/>
<c:set var="detailsUrl">
<a target="_blank" href="${viewNSSUrl}">(Details)</a>
</c:set>
</c:when>

<c:when test="${o.searchCategoryId == 1}">
<c:set var="description" value="${o.type} / ${o.subtype}"/>
<c:set var="detailsUrl">
<a target="_blank" href="${viewBJLUrl}">(Details)</a>
</c:set>
</c:when>

<c:when test="${o.searchCategoryId == 11}">
<c:set var="description" value="${o.type} / ${o.subtype} "/>
<c:set var="detailsUrl">
<a target="_blank" href="${viewBJLUrl}">(Details)</a>
</c:set>
</c:when>


<c:when test="${o.searchCategoryId == 50}">
<c:set var="description" value="${o.type} / ${o.subtype}"/>
<c:set var="detailsUrl">
<a target="_blank" href="${viewFRUrl}">(Details)</a>
</c:set>
</c:when>

<c:when test="${o.searchCategoryId == 33}">
<c:set var="description" value="${o.type} / ${o.subtype}"/>
<c:set var="detailsUrl">
<a target="_blank" href="${viewEvicUrl}">(Details)</a>
</c:set>
</c:when>

<c:when test="${o.searchCategoryId == 38}">
<c:set var="description" value="${o.type} / ${o.subtype}"/>
<c:set var="detailsUrl">
<a target="_blank" href="${viewCorpUrl}">(Details)</a>
</c:set>
</c:when>

<c:when test="${o.searchCategoryId == 66}">
<c:set var="description" value="${o.type} "/>
<c:set var="detailsUrl">
<a target="_blank" href="${viewRealPropUrl}">(Details)</a>
</c:set>
</c:when>

<c:otherwise>
<c:set var="description" value="${o.type} / ${o.subtype}"/>
<c:set var="detailsUrl" value=""/>
</c:otherwise>
</c:choose>

<c:forEach items="${customers}" var="cust">
	<c:choose>
	<c:when test="${cust.username eq o.username}">
		<c:set var="newuser" value="${cust.username}"/>
    	<tr class="newcustomer">
    		<td><div id="newuser">New Customer</div>
    			<a href='<c:out value="${viewUserUrl}"/>'><c:out value="${o.username}"/></a><br/>
    			<c:out value="${o.email}"/>
    		</td>
    		<td>
				<a href='<c:out value="${viewTxnUrl}"/>' target="_blank"><c:out value="${o.invoiceNumber}"/></a><br/>    			
			</td>
    		<td><c:out value="${o.cost}"/></td>
    		<td><c:out value="${o.status}"/></td>
    		<td>
    		<c:out value="${description}"/>
			<c:if test="${detailsUrl ne ''}">${detailsUrl}</c:if>
			</td>

    		<td class="nowrap"><fmt:formatDate value="${o.date}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
    	</tr>
		
    	</c:when>
		
    	</c:choose>
 </c:forEach>

<c:if test="${newuser != o.username}">
		<tr>
    		<td>
    			<a href='<c:out value="${viewUserUrl}"/>'><c:out value="${o.username}"/></a><br/>
    			<c:out value="${o.email}"/>
    		</td>
    		<td>
				<a href='<c:out value="${viewTxnUrl}"/>' target="_blank"><c:out value="${o.invoiceNumber}"/></a><br/>    			
			</td>
    		<td><c:out value="${o.cost}"/></td>
    		<td><c:out value="${o.status}"/></td>
    		<td>
    		<c:out value="${description}"/>
			<c:if test="${detailsUrl ne ''}">${detailsUrl}</c:if>
			</td>

    		<td class="nowrap"><fmt:formatDate value="${o.date}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
    	</tr>
		</c:if>
		 </c:forEach>
   
    </tbody>	    		
</table>    
</c:if>
    
</jsp:body>

</neon:page>