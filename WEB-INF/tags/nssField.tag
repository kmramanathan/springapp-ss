<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="f" required="true" type="java.lang.String"%>
<%@attribute name="l" required="true" type="java.lang.String"%>

<b><c:out value="${l}"/></b><br/>
<c:choose>
<c:when test="${f eq ''}"><c:out value="---"/></c:when>
<c:otherwise><c:out value="${f}"/></c:otherwise>
</c:choose>

