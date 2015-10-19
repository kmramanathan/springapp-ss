<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="f" required="true" type="java.lang.String"%>
<%@attribute name="l" required="true" type="java.lang.String"%>
<%@attribute name="clazz" required="false" type="java.lang.String" %>

<c:if test="${f ne ''}">
<tr class="${clazz}">
<td class="k"><b><c:out value="${l}"/></b></td>
<td class="v"><c:out value="${f}"/></td>
</tr>
</c:if>