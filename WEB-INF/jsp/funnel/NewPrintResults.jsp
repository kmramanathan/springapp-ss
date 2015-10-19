<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:CrimeFunnel title="Search Results">

<jsp:attribute name="stylesheet">funnel.css,resultsBgc.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>
<style type="text/css">
tr.even{background-color:#f7f6a5;}
tr.odd{background:#fff;}
</style>
<tr>
<td>
<p style=" color:red; font-size:12pt;">Your report details are below. Be sure to print or copy this page now, as your search results are NOT stored in our system for later viewing.</p>
<div id="title">
<a href="newResults.do">Return to results</a> <a href="javascript:window.print();" style="float:right"> <img src="/springapp/images/newfunnel/printer-icon.jpg" border="0" align="top"></img></a>
</div>
              
<table border="0">
<tr>
<td>

<h2>Result Details</h2>

<c:set var="count" value="0"/>
<c:forEach var="o" items="${offenders}" varStatus="loop" begin="0">
<c:set var="count" value="${count+1}"/>
<c:set var="id" value="${o.bgcOffenderId}"/>
<c:set var="aliases" value="${aliasesMap[id]}"/>
<c:set var="offenses" value="${offensesMap[id]}"/>

<h3>Record #${count}</h3>

<h3>Offender Info</h3>
<div class="offender">
<neon:bgcOffender o="${o}"/>
</div>

<c:if test="${fn:length(aliases) > 0}">
<h3>Aliases</h3>
<div class="aliases">
<neon:bgcAliases aliases="${aliases}"/>
</div>
</c:if>

<h3>Offenses</h3>
<div class="offenses">
<c:set var="row" value="1"/>
<c:forEach var="obj" items="${offenses}" varStatus="loop" begin="0">
<neon:bgcOffenses obj="${obj}" clazz="${loop.index % 2 == 0 ? 'odd' : 'even'}" row="${row}"/>
<c:set var="row" value="${row+1}"/>
</c:forEach>
</div>

<div style="background:#000080;height:15px;margin-top:10px">&nbsp;</div>

</c:forEach>

<h3>DISCLAIMER</h3>
<div class="disclaimer">
Search Systems provides no warranty of any type as to the accuracy of this information, 
and any reliance on this information is solely at your own risk and responsibility.  All 
information retrieved from or through SearchSystems.net must be utilized in accordance with
the User Agreement and all applicable state and federal laws.
<br/><br/> 
Copyright &copy; 1997-2012 Search Systems, Inc. All rights reserved.
</div>
</td>
</tr>
</table>
</td>
</tr>
</jsp:body>

</neon:CrimeFunnel>
