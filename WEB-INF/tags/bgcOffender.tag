<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="o" required="true" type="net.searchsystems.limestone.bean.BGCOffenderBean"%>

<!-- start offender -->
<table>

<tr>
<td><b>Full Name</b><br/><c:out value="${o.fullName}"/></td>
<td><b>DOB</b><br/>

<c:choose>
					<c:when test="${o.dateOfBirth eq '1900-01-01'}">
						-- -- --
					</c:when>	
					<c:otherwise>
					<c:out value="${o.dateOfBirth}"/>
					</c:otherwise>			
				</c:choose>	
</td>
<td colspan="2"><b>ID</b><br/><c:out value="${o.recordOffenderId}"/></td>
</tr>

<tr>
<td><neon:bgcField l="Address" f="${o.street}" /></td>
<td><neon:bgcField l="City" f="${o.city}" /></td>
<td><neon:bgcField l="State" f="${o.state}" /></td>
<td><neon:bgcField l="Postal Code" f="${o.postalCode}" /></td>
</tr>

<tr>
<td><neon:bgcField l="Gender" f="${o.gender}" /></td>
<td><neon:bgcField l="Race" f="${o.race}" /></td>
<td><neon:bgcField l="County" f="${o.county}" /></td>
<td><neon:bgcField l="Country" f="${o.country}" /></td>
</tr>

<c:set var="heightFixed" value="${o.heightFeet}"/>
<c:set var="weightFixed" value="${o.weight}"/>
<c:if test="${heightFixed eq '-'}"><c:set var="heightFixed" value="---"/></c:if>
<c:if test="${weightFixed eq '-'}"><c:set var="weightFixed" value="---"/></c:if>

<tr>
<td><neon:bgcField l="Height" f="${heightFixed}" /></td>
<td><neon:bgcField l="Weight" f="${weightFixed}" /></td>
<td><neon:bgcField l="Eye Color" f="${o.eyeColor}" /></td>
<td><neon:bgcField l="Hair Color" f="${o.hairColor}" /></td>
</tr>

<tr>
<td><neon:bgcField l="Record Source" f="${o.provider}" /></td>
<td><neon:bgcField l="Record State" f="${o.recordState}" /></td>
</tr>

</table>
<!-- end offender -->