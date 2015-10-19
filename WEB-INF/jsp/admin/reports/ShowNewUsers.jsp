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
<h2>Show New Users</h2>
<hr/>

<h3>Display filters</h3>

The default search shows all users for the past 24 hours.<br/>


<form:form method="post" commandName="command" action="newUsers.do">
	<form:errors path="*" cssClass="errorBox" element="div" />

	<table border="0" cellspacing="0">
		
		<tr>
			<td class="label">Search past:</td>
			<td class="input">
				<form:select path="daysToSearch">
					<form:option value="1"     label="24 hours"      />
					<form:option value="7"     label="7 days"      />
					<form:option value="30"    label="30 days"     />
				</form:select>
			</td>
			<td class="error">
          		<form:errors path="daysToSearch" />
        	</td>
		</tr>
	
	</table>
	
	<input type="submit" value="Search Orders" />	
</form:form>


	<hr/>
   
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
		<td colspan="3">
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
		<th class="table-sortable:alphanumeric">Username</th>
		<th class="table-sortable:alphanumeric">Email</th>			
		
		<th class="table-sortable:alphanumeric">Date/Time</th>
	</tr>
	</thead>

   	<tbody> 
   
<c:forEach items="${customers}" var="cust">

	<tr class="newcustomer">
    		<td>
    			<a target="blank" href='<c:out value="/springapp/admin/viewUser.do?username=${cust.username}"/>'>
    			<c:out value="${cust.username}"/></a>    			
    		</td>
    		<td >
				<c:out value="${cust.email}"/>			
			</td>
    		
    		
			
    		<td class="nowrap"><fmt:formatDate value="${cust.createDate}" type="both" dateStyle="SHORT" timeStyle="SHORT"/></td>
    	</tr>
 </c:forEach>
   
    </tbody>	    		
</table>    
</c:if>
    
</jsp:body>

</neon:page>