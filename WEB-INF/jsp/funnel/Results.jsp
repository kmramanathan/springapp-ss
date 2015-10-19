<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPageOneCol title="Search Results">

<jsp:attribute name="stylesheet">funnel.css,resultsBgc.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js,verifyCheckBoxesForPrint.js</jsp:attribute>

<jsp:body>

<div id="upsellBanner">
<a href="https://www.searchsystems.net/springapp/funnel/WholePicture.html">
	<img src="/springapp/images/common/ban-access-more.png" />
</a>
</div>

<%-- CJ tracking code, uses BGC response id --%>
<iframe src="https://secure.img-cdn.mediaplex.com/0/11449/universal.html?page_name=results_page&Sale_1=${searchPrice}&Sale_2=1&mpuid=${responseId}" HEIGHT="1" WIDTH="1" FRAMEBORDER="0"></iframe>

<div id="title">Want Another Search? 
<a href="searchAgain.do?nationwide=1&campaign=${sessionScope.campaign}">Nationwide</a> | 
<a href="searchAgain.do?nationwide=0&campaign=${sessionScope.campaign}">State</a>
</div>
              
<div id="formwrapper">

<h1>Criminal Record Results</h1>
<c:set var="pageSize" value="50" />
<c:out value="${offendersCount}"/> results found.<br/>
<c:set var="pageCount" value="${offendersCount / pageSize}"/>
<c:if test="${(pageSize < offendersCount) && ((offendersCount % pageSize) > 0)}">
	<c:set var="pageCount" value="${pageCount + 1}"/>
</c:if>

<div id="bgc-results">
<form:form action="printResults.do" method="post">

<table id="orders" class="autoLayout table-autopage:<c:out value="${pageSize}"/> table-autosort:-1 table-stripeclass:alternate">
<thead>
<tr>
	<td colspan="3">
        <input type="submit" name="printChecked" value="Print Checked Details"  />
        <input type="submit" name="printAll" value="Print All Details" onclick="printAll = true;"/>
        &nbsp;
    </td>
	<td colspan="2" style="text-align: right;">
		Showing <c:out value="${pageSize}"/> results per page.
			<select id="pageSize" name="pageSize" onchange="setPageSize(document.getElementById('pageSize').value)">
<c:forEach var="size" items="${pageSizes}">
<c:set var="selected" value=""/>
<c:if test="${size eq pageSize}">
<c:set var="selected" value="selected"/>
</c:if> 
<option value="${size}" ${selected}>${size}</option>
</c:forEach>
			</select> 
			<%--		
			<form:select path="pageSize" onchange="setPageSize(document.getElementById('pageSize').value)">
<form:option value="10">10</form:option>
<form:option value="25">25</form:option>
<form:option value="50">50</form:option>
			</form:select> 
			--%>
	</td>
</tr>

<tr class="result-headers">
	<th>&nbsp;</th>
	<th class="table-sortable:numeric">Row #</th>
	<th class="table-sortable:numeric">Detail #</th>
	<th class="table-sortable:alphanumeric">Name</th>
	<th class="table-sortable:alphanumeric">Date of Birth</th>
	<th class="table-sortable:alphanumeric">Provider</th>
</tr>
</thead>

<tbody>
	<c:set var="row" value="1"/>
    <c:forEach items="${offenders}" var="o">
		<c:url value="resultDetails.do?offenderId=${o.bgcOffenderId}&hashKey=${o.hashKey}" var="detailUrl"/>
	  	<tr>
			<td><form:checkbox path="resultsToPrint" value="${o.bgcOffenderId}" /></td>
	  		<td><c:out value="${row}"/></td>
			<td><a href="${detailUrl}"><c:out value="${o.bgcOffenderId}"/></a></td>
	  		<td><c:out value="${o.fullName}"/></td>
			<td><c:out value="${o.dateOfBirth}"/></td>
			<td><c:out value="${o.provider}"/></td>
	  	</tr>
		<c:set var="row" value="${row+1}"/>
  	</c:forEach>
</tbody>

<tfoot>
	<tr><td colspan="6"><hr width="75%"/></td></tr>

	<tr><td colspan="6">
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
	</td></tr>
</tfoot>

</table>

</form:form>
</div>

</div>

<neon:funnelGoogleConvSearch/>

</jsp:body>

</neon:funnelPageOneCol>
