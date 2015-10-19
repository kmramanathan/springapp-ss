<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="aliases" required="true" type="net.searchsystems.limestone.bean.BGCAliasBean[]"%>

<!-- start aliases -->
<table>

<c:set var="row" value="1"/>
<c:forEach var="a" items="${aliases}">
<tr>
<td><c:out value="${row}"/></td>
<td><neon:bgcField l="First Name"  f="${a.firstName}"/></td>
<td><neon:bgcField l="Last Name"   f="${a.lastName}"/></td>
<td><neon:bgcField l="Middle Name" f="${a.middleName}"/></td>
<td><neon:bgcField l="Suffix"      f="${a.suffix}"/></td>
</tr>
<c:set var="row" value="${row+1}"/>
</c:forEach>

</table>
<!-- end aliases -->