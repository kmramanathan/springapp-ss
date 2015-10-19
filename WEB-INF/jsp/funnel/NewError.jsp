<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%  boolean bjl = false;
	if(session.getAttribute("bjlSearchFormCommand") != null) {
		bjl = true;
	}
	boolean nss=false;
	if(session.getAttribute("nationSearchFormCommand") != null)
	{
		nss=true;
	}
	if(nss)
	{%>
		<neon:NewFunnel title="Error" >
<jsp:attribute name="stylesheet">new-funnel.css</jsp:attribute>

<jsp:body>
<div id="title"><img src="images/common/confirm-search.gif" alt="Error" /></div>
              
<div id="formwrapper">

An error occurred. We apologize for the inconvenience.
The error has been reported to our staff.<p/>

<a href="nationSecurity.do">Return to the start page</a>

</div>
</jsp:body>

</neon:NewFunnel>
	<% }
	else if(bjl){ %>
<neon:NewFunnel title="Error" >
<jsp:attribute name="stylesheet">new-funnel.css</jsp:attribute>

<jsp:body>
<div id="title"><img src="images/common/confirm-search.gif" alt="Error" /></div>
              
<div id="formwrapper">

An error occurred. We apologize for the inconvenience.
The error has been reported to our staff.<p/>

<a href="newSearchBJL.do">Return to the start page</a>

</div>
</jsp:body>

</neon:NewFunnel>
<% } else { %>

<neon:NewFunnel title="Error">
<jsp:attribute name="stylesheet">new-funnel.css</jsp:attribute>

<jsp:body>
<div id="title"><img src="images/common/confirm-search.gif" alt="Error" /></div>
              
<div id="formwrapper">

An error occurred. We apologize for the inconvenience.
The error has been reported to our staff.<p/>

<a href="newSearch.do">Return to the start page</a>

</div>
</jsp:body>

</neon:NewFunnel>

<% } %>