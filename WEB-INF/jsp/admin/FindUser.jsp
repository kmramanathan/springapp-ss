<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Find User">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<script type="text/javascript" src="/springapp/js/jquery-1.2.1.pack.js"></script>
<script type="text/javascript" src="/springapp/js/table.js"></script>
<script language="javascript">
// handle page nav
// this uses table.js
function pageexample(page) {
	var t = document.getElementById('page');
	var res;
	if (page=="previous") {
		res=Table.pagePrevious(t);
	}
	else if (page=="next") {
		res=Table.pageNext(t);
	}
	else {
		res=Table.page(t,page);
	}
	var currentPage = res.page+1;
	$('.pagelink').removeClass('currentpage');
	$('#page'+currentPage).addClass('currentpage');
}

function resize() {
    var t = document.getElementById('page');
    var select = document.getElementById('results_count');
    var index = select.selectedIndex;
    var count = select.options[index].value;
    Table.autopage(t,{'pagesize':+count});;
    // fix up pages
}
</script>

<h2>Find a user</h2>
<form:form method="post" commandName="findUser">
	Search for users based on different fields. Fields left blank will be ignored.
	<table border="0" cellspacing="0">
		<%-- 
		<tr>
			<td>Search field</td>
			<td>
				<form:select path="searchField">
					<form:option  value="-"   label="Select a field"   />
					<form:options items="${findUser.searchFields}" /> 					
				</form:select>
			</td>
			<td><form:errors path="searchField" cssClass="errorBox"/></td>
		</tr>
		<tr>
			<td>Search term</td>
			<td><form:input path="searchTerm"/></td>
			<td><form:errors path="searchTerm" cssClass="errorBox" /></td>			
		</tr>
		<tr>
			<td>Partial match</td>
			<td><form:checkbox path="partialMatch"/></td>
			<td>Minimum of 3 characters required.</td>
		</tr>
		--%>
		
		<tr>
			<td>Username</td>
			<td><form:input path="username"/></td>
			<td><form:errors path="username" cssClass="errorBox" /></td>			
		</tr>
		<tr>
			<td>First Name</td>
			<td><form:input path="firstName"/></td>
			<td><form:errors path="firstName" cssClass="errorBox" /></td>			
		</tr>
		<tr>
			<td>Last Name</td>
			<td><form:input path="lastName"/></td>
			<td><form:errors path="lastName" cssClass="errorBox" /></td>			
		</tr>
		<tr>
			<td>Email</td>
			<td><form:input path="email"/></td>
			<td><form:errors path="email" cssClass="errorBox" /></td>			
		</tr>
		<tr>
			<td>Last 4 of CC</td>
			<td><form:input path="last4"/></td>
			<td><form:errors path="last4" cssClass="errorBox" /></td>			
		</tr>
		<tr>
			<td>Address</td>
			<td><form:input path="address"/></td>
			<td><form:errors path="address" cssClass="errorBox" /></td>			
		</tr>
		<tr>
			<td>Phone</td>
			<td><form:input path="phone"/></td>
			<td><form:errors path="phone" cssClass="errorBox" /></td>			
		</tr>
				
		<tr>
			<td>Partial match</td>
			<td><form:checkbox path="partialMatch"/></td>
			<td>Minimum of 1 character required. Use additional characters to limit results.</td>
		</tr>
		
		<tr>
			<td>Match ALL fields</td>
			<td><form:checkbox path="matchAllFields"/></td>
			<td>Default is to match ANY field. Check this box to limit results.</td>
		</tr>
				
	</table>
	
	<input type="submit"/>
</form:form>

<c:if test="${count > 0}">
<h2>Search results</h2>
	<c:choose>
	<c:when test="${count > 0}">
	    Color Key
		<table class="userListKey">
	    	<tr>
	    		<td class="disabled"  >Disabled</td>
	    		<td class="inactive"  >Inactive</td>
	    		<td class="directPass">DirectPass</td>
	    		<td class="flatrate">FlatRate</td>
	    		<td class="dpandfr">DP and FR</td>
    			<td class="normal"    >Other</td>	    		
	    	</tr>	    	
	    </table>
	    
		Showing <c:out value="${count}"/> results.
		<table id="userList" class="userList example table-autosort:0 table-stripeclass:alternate">
			<thead>
				<tr>
					<th class="table-sortable:alphanumeric">Username</th>
					<th class="table-sortable:alphanumeric">First</th>
					<th class="table-sortable:alphanumeric">Last</th>
					<th class="table-sortable:alphanumeric">Email</th>
					<th class="table-sortable:alphanumeric">Billing Name</th>
					<th class="table-sortable:alphanumeric">Address</th>
					<th class="table-sortable:alphanumeric">Phone</th>
					<th class="table-sortable:alphanumeric">Last 4 of CC</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${resultList}" var="r">
				<c:choose>
				<c:when test="${r.disabled}">
					<c:set var="rowClass" value="disabled"/>
				</c:when>
				<c:when test="${r.inactive && !r.flatrate}">
					<c:set var="rowClass" value="inactive"/>
				</c:when>
				<c:when test="${r.directPass && !r.inactive && r.flatrate}">
					<c:set var="rowClass" value="dpandfr"/>
				</c:when>
				<c:when test="${r.directPass && !r.flatrate}">
					<c:set var="rowClass" value="directPass"/>
				</c:when>
				<c:when test="${r.flatrate}">
					<c:set var="rowClass" value="flatrate"/>
				</c:when>
				<c:otherwise>
					<c:set var="rowClass" value=""/>
				</c:otherwise>
				</c:choose>

				<tr class="<c:out value="${rowClass}"/>">
				<td>
					<c:url value="/admin/viewUser.do" var="viewUserUrl">
						<c:param name="username" value="${r.username}"/>
					</c:url>
					<a href='<c:out value="${viewUserUrl}"/>'><c:out value="${r.username}"/></a>
				</td>
				<td><c:out value="${r.firstName}"/></td>
				<td><c:out value="${r.lastName}"/></td>
				<td><c:out value="${r.email}"/></td>

				<td><c:out value="${r.billingName}"/></td>
				<td><c:out value="${r.address}"/></td>
				<td><c:out value="${r.phone}"/></td>
				<td><c:out value="${r.last4}"/></td>
				
				</tr>
			</c:forEach>
			</tbody>
			<%-- 
			<tfoot>
				<tr>
					<th class="table-page:previous">Previous page</th>
					<th class="table-page-number:page">Page <span id="userListpage"></span></th>
					<th class="table-page-count:pagecount">Of <span id="pagecount"></span></th>
					<th class="table-page:next">Next page</th>
				</tr>
			</tfoot>
			--%>
		</table>
	</c:when>
	<c:otherwise>
	  	No results found.
	</c:otherwise>
	</c:choose>
  
</c:if>
</jsp:body>

</neon:page>