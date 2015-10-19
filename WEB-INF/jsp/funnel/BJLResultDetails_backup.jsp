<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Search Results" rightSidebar="ResultsSidebar.jsp">

<jsp:attribute name="stylesheet">funnel.css,resultsBgc.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>
<div id="title">
<a href="resultsBJL.do">Return to results</a>
</div>
              
<div style="">

<h2>Bankruptcies, Judgments and Tax Liens - Result Details</h2>

<h3>Offender Info</h3>
<div class="bjl-result-details">
<neon:bjlResultDetails d="${result}"/>
</div>

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

</neon:funnelPage>
