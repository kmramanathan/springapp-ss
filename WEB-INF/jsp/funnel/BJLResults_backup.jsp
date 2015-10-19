<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPageOneCol title="Search Results">

<jsp:attribute name="stylesheet">funnel.css,resultsBgc.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js,verifyCheckBoxesForPrint.js</jsp:attribute>

<jsp:body>
<%-- CJ tracking code, uses BGC response id --%>
<iframe src="https://secure.img-cdn.mediaplex.com/0/11449/universal.html?page_name=results_page&Sale_1=${searchPrice}&Sale_2=1&mpuid=${responseId}" HEIGHT="1" WIDTH="1" FRAMEBORDER="0"></iframe>

<div id="title">Want Another Search? 
<a href="searchAgain.do?nationwide=1">Nationwide $29.95</a> | 
<a href="searchAgain.do?nationwide=0">State $17.95</a>
</div>
              
<div id="formwrapper">

<h1>Search Results - Bankruptcies, Judgments & Tax Liens</h1>

<c:out value="${resultsCount}"/> results found.<br/>
<c:set var="pageCount" value="${resultsCount / pageSize}"/>
<c:if test="${(pageSize < resultsCount) && ((resultsCount % pageSize) > 0)}">
	<c:set var="pageCount" value="${pageCount + 1}"/>
</c:if>

<div id="bjl-results">
<form:form action="printResults.do" method="post">

<neon:sortableTable pageSize="${pageSize}" rowCount="${resultsCount}" tableColumns="5" tableName="bjl-results">
<jsp:attribute name="tableHeaders">
		<th class="table-sortable:numeric">Result #</th>
		<th class="table-sortable:alphanumeric">Defendant</th>
		<th>Filing Date</th>
		<th>Filing Type</th>
		<th>Court Name</th>
		<th>Case Number</th>
</jsp:attribute>
<jsp:body>
	<td colspan="3">
        <input type="submit" name="printChecked" value="Print Checked Details"  />
        <input type="submit" name="printAll" value="Print All Details" onclick="printAll = true;"/>
        &nbsp;
    </td>
	<c:forEach items="${results}" var="r">
		<c:url var="viewDetails" value="resultDetailsBJL.do?resultId=${r.resultId}" />
		
		<tr>
			<td><a href="${viewDetails}"><c:out value="${r.resultId}"/></a></td>
			<td>${r.defendant}</td>
			<td>${r.filingDate}</td>
			<td>${r.filingType}</td>
			<td>${r.courtName} (${r.courtIdCode})</td>
			<td>${r.caseNumber}</td>
		</tr>
	</c:forEach>
</jsp:body>
</neon:sortableTable>

</form:form>
</div>

</div>

<neon:funnelGoogleConvSearch/>

</jsp:body>

</neon:funnelPageOneCol>
