<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:page title="Search">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
    <h3>Results Summary</h3>

	<c:choose>
		<c:when test="${resultCount == 0}">
			No results found.			
		</c:when>
		<c:otherwise>
			<table>
		    <c:forEach items="${results}" var="result">
		    	<tr>
		    		<td><c:out value="${result.searchId}"/></td>
		    		<td><c:out value="${result.firstName}"/></td>
		    		<td><c:out value="${result.lastName}"/></td>
		    	</tr>
		    </c:forEach>
		    </table>
   		</c:otherwise>	    
	</c:choose>
    
    <input type="submit" name="_finish" value="Finish"/>
    <input type="submit" name="_cancel" value="Cancel"/>
    <br>
</jsp:body>

</neon:page>