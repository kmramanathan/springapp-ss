<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="sups" required="true" type="springapp.domain.NSS.NssOffenseSupplementBean[]"%>

<!-- start supplements -->
<table>

<c:set var="row" value="1"/>
<c:forEach var="s" items="${sups}">
<tr>
<td><c:out value="${row}"/></td>
<td><c:out value="${s.displayTitle}"/></td>
<td><c:out value="${s.displayValue}"/></td>

</tr>
<c:set var="row" value="${row+1}"/>
</c:forEach>

</table>
<!-- end supplements -->