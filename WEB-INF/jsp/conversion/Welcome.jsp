<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Thank You" rightSidebar="GenericConversionSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<div id="formwrapper">

<h3><strong>Your transaction was successful</strong></h3>

<p>Congratulations. You have upgraded to a full SearchSystems.net 
membership. A confirmation receipt has been sent to your email address.</p>

<p>You may log in using your existing Username and Password.</p>

<b>Username:</b> ${username}<p/>

For access to Criminal Records, Bankruptcies, Judgments and Tax Liens searches
<a href="https://premium.searchsystems.net/">click here to log in</a>.<p/>

If you would like access to our Public Records Directory 
<a href="http://www.searchsystems.net/">log in here</a>.<p/>

</div>

</jsp:body>

</neon:funnelPage>
