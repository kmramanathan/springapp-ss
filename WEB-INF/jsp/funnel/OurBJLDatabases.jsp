<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:CrimeFunnel title="Criminal Records Data Sources">
<jsp:attribute name="stylesheet">new-funnel.css,funnel.css,ourDatabases.css</jsp:attribute>
<jsp:attribute name="javascript">prototype.js,scriptaculous.js</jsp:attribute>

<jsp:body>
<style type="text/css">
.startsearch{
	float:right;
	text-decoration:none;
}
.startbutton{
	float:right;
	margin-top:7px;
}
</style>

<table border="0" cellpadding="0" cellspacing="0"><tr><td align="left" width="93%"><h1>Criminal Records Data Sources</h1></td><td align="right"><a href="https://www.searchsystems.net/springapp/funnel/newSearch.do" class="startsearch"><input type="button" value="Start Search" align="right" class="startbutton" /></a></td></tr></table>

<script type="text/javascript" language="javascript">
function selectState() 
{
    var x = document.getElementById("state");
    var state = x.options[x.selectedIndex].value;
    var url = document.URL;
    window.location.href = "bjlCoverage.do?state=" + state;
}
//<a href="javascript:show()">Show</a> | <a href="javascript:hide()">Hide</a> 
function show() {
	$('databases_inner').show();
}
function hide() {
	$('databases_inner').switchOff();
}
</script>

<b>
Use the States menu below to see the coverage in each state, or click on the National and International links to see the coverage sources and descriptions in those areas. A Nationwide search will bring you results from all three types of databases - All States, National data, and International data. A Statewide search will bring results from the selected state only.
</b>
<br/>

<%-- draw top nav & selector if state mode --%>
<div id="databases-nav">
<div id="databases-links">
<c:choose>

<c:when test="${view eq 'us'}">
<a href='bjlCoverage.do?view=state'>States</a> | 
<b>National &amp; International</b>
</c:when>

<c:otherwise>
<b>States</b> | 
<a href='bjlCoverage.do?view=us'>National &amp; International</a>  
<div id="databases-state-selector">
Select a state:
<select id="state" name="state" onChange="selectState()">
<option value="">Please Select</option>
<c:forEach var="s" items="${states}">
	<option value="${s}"><c:out value="${s}"/></option>
</c:forEach>
</select>
</div>
</c:otherwise>

</c:choose>
</div>
</div>

<%-- draw descriptions --%>
<div id="descriptions"/>

<table class="">
<tr>
<th style="width: 25%">Source</th>
<th>Description</th>
</tr>

<c:choose>

<c:when test="${view eq 'state'}">
<c:set var="source" value="${desc[0].provider}"/>
<tr>
<td style="vertical-align: top; font-weight: bold;"><c:out value="${source}"/></td>
<td style="vertical-align: top;" >

<c:forEach var="d" items="${desc}">		
	${d.description}
</c:forEach>

</td>
</tr>
<!-- new one -->
</c:when>

<c:otherwise>
<c:forEach var="d" items="${desc}">
	<tr>
	<td style="vertical-align: top; font-weight: bold;"><c:out value="${d.provider}"/></td>
		
	<td style="vertical-align: top;">${d.description}</td>
	</tr>
</c:forEach>
</c:otherwise>

</c:choose>

</table>

</div>

</jsp:body>

</neon:CrimeFunnel>