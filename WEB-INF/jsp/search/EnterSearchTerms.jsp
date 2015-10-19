<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Search">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
    <h3>Confirm Search</h3>
    <form:form method="post" commandName="searchForm">
    errors: <form:errors path="*" cssClass="errorBox" element="true" /><br/>
    First Name: <form:input path="firstName"/><br/>
    Last Name: <form:input path="lastName"/><br/>
    
    <input type="submit" name="_target2" value="Continue"/>
    <input type="submit" name="_cancel" value="Cancel"/>
    <br>
    </form:form>
</jsp:body>

</neon:page>
