<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Search">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
    <h3>Products</h3>
    <form:form method="post" commandName="searchForm">
   	errors: <form:errors path="*" cssClass="errorBox" element="true" /><br/>
    <c:forEach items="${products}" var="prod">
    	<form:radiobutton path="bgcProductId" value="${prod.key}"/> <c:out value="${prod.value}"/><br/>
    </c:forEach>
    <input type="submit" name="_target1" value="Continue"/>
    <input type="submit" name="_cancel" value="Cancel"/>
    <br>
    </form:form>
</jsp:body>

</neon:page>
