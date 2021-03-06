<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPageOneCol title="Search Results">

<jsp:attribute name="stylesheet">funnel.css,resultsBgc.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>
<div id="title">
<a href="results.do">Return to results</a>
</div>
              
<div style="">

<h2>Result Details</h2>

<c:set var="count" value="0"/>
<c:forEach var="o" items="${offenders}">
<c:set var="count" value="${count+1}"/>
<c:set var="id" value="${o.bgcOffenderId}"/>
<c:set var="aliases" value="${aliasesMap[id]}"/>
<c:set var="offenses" value="${offensesMap[id]}"/>

<h3>Result #${count}</h3>

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
<!--<neon:bgcOffenses offenses="${offenses}"/>-->
</div>

<hr/>

</c:forEach>

<h3>DISCLAIMER</h3>
<div class="disclaimer">
Search Systems provides no warranty of any type as to the accuracy of this information, 
and any reliance on this information is solely at your own risk and responsibility.  All 
information retrieved from or through SearchSystems.net must be utilized in accordance with
the User Agreement and all applicable state and federal laws.
<br/><br/> 
Copyright &copy; 2003-2008 Pacific Information Resources, Inc. All rights reserved.
</div>

</div>

</jsp:body>

</neon:funnelPageOneCol>
