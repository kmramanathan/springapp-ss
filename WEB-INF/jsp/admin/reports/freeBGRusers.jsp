<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Free BGR users">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>

<h2>List of Free BGR Users</h2>
<hr/>

<h3>Display filter</h3>

The default search shows list of all free BGR users.<br/>
To know availability of free BGR for particular user enter username.<br/>

<form:form method="post" commandName="command" action="freeBGRusers.do">
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
	</table>
	
	<input type="submit" value="Search User" />	
</form:form>
	<hr/>

<c:forEach items="${tracker}" var="t">	
	<h3>FindPeople User Tracker</h3>
	Displays the count of user clicks in FindPeople.<br/>
    Includes test searches.<br/>
	<table>
		<tr><td><strong>From</strong></td><td> - </td><td>${t.created_date}</td></tr>
		<tr><td><strong>To</strong></td><td> - </td><td>${t.last_updated}</td></tr>		
		<tr><td><strong>Teaser Visit</strong></td><td> - </td><td>${t.teaser_count}</td></tr>	
		<tr><td><strong>FindPeople Details</strong></td><td> - </td><td>${t.fp_detail_count}</td></tr>	
		<tr><td><strong>Background Details</strong></td><td> - </td><td>${t.bg_detail_count}</td></tr>	
		<tr><td><strong>Membership/Free BGR</strong></td><td> - </td><td>${t.free_bg_count}</td></tr>	
	</table> 
</c:forEach>
   
	<hr/>
    <h3>Free BGR Users</h3>
    <c:out value="${userCount}"/> results found.<br/>
    <c:set var="pageSize" value="50"/>
    <c:set var="pageCount" value="${userCount / pageSize}"/>
    <c:if test="${(pageSize < userCount) && ((userCount % pageSize) > 0)}">
    	<c:set var="pageCount" value="${pageCount + 1}"/>
    </c:if>
    
<c:if test="${userCount > 0}">
<table id="BGRusers" class="autoLayout table-autopage:<c:out value="${pageSize}"/> table-autosort:-1 table-stripeclass:alternate">
	<thead>
	<tr>
		<td colspan="6">
			Showing <c:out value="${pageSize}"/> results per page.<br/>
			Navigate to Page:<br/>		
			<a href="#" onclick="selectPage('previous', 'BGRusers'); return false;">[ &lt;&lt;&nbsp;Previous ]</a>
			
			<%-- this forEach & paging stuff could use some work --%>
			<c:set var="currentPage" value="0"/>
			<c:forEach begin="0" end="${pageCount - 1}" step="1">
				<c:set var="nextPage" value="${currentPage + 1}"/>
				<c:url value="" var="pageUrl"/>
				<a href="#" id="page<c:out value="${nextPage}"/>" class="pagelink" onclick="selectPage(<c:out value="${currentPage}"/>, 'BGRusers'); return false;"><c:out value="${nextPage}"/></a>
				<c:set var="currentPage" value="${currentPage + 1}"/>
			</c:forEach>

			<a href="#" onclick="selectPage('next', 'BGRusers'); return false;">[ Next&nbsp;&gt;&gt; ]</a>
		</td>
	</tr>
	
	<tr>
		<th class="table-sortable:alphanumeric">Username<br/>Email</th>
		<th class="table-sortable:alphanumeric">Create Date</th>
		<th class="table-sortable:currency">Search Date</th>
		<th class="table-sortable:alphanumeric">Free BGR</th>
	</tr>
	</thead>

   	<tbody>
    <c:forEach items="${BGRusers}" var="b">	    	
    	<c:url value="/admin/viewUser.do?username=${b.username}" var="viewUserUrl"/>
		<tr>
    		<td>
    			<a href='<c:out value="${viewUserUrl}"/>'><c:out value="${b.username}"/></a><br/>
    			<c:out value="${b.email}"/>
    		</td>
    		<td>
				<c:out value="${b.create_date}"/><br/>    			
			</td>
    		<td><c:out value="${b.modify_date}"/></td>
    		<td><c:out value="${b.free_bgr}"/></td>
    	</tr>
    </c:forEach>
    </tbody>	    		
</table>    
</c:if>
    
</jsp:body>

</neon:page>