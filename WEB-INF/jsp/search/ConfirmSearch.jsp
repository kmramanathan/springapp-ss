<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Search">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
    <h3>Confirm Search</h3>
    <form:form method="post" commandName="searchForm">
    errors: <form:errors path="*" cssClass="errorBox" element="true" /><br/> 
    First Name: <c:out value="${searchForm.firstName}"/><br/>
    Last Name: <c:out value="${searchForm.lastName}"/><br/>
    Product: <c:out value="${searchForm.bgcProductId}"/><br/>
    
    <input type="submit" name="_finish" value="Confirm"/>
    <input type="submit" name="_cancel" value="Cancel"/>
    <br>
    </form:form>
</jsp:body>

</neon:page>