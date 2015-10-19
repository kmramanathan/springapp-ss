<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="obj" required="true" type="springapp.domain.CorpResponses" %>
<%@attribute name="clazz" required="false" type="java.lang.String" %>
<%@attribute name="clazz1" required="false" type="java.lang.String" %>
<%@attribute name="count" required="false" type="java.lang.String" %>

<table class="corpresult">
<tr>
	<td colspan="4"><div align="left" class="style118">Corporate Record ${count}</div></td>
</tr>
<tr>
	<td width="204" class="strong">Corporation Name:</td><td width="250">${obj.corporation_name}</td>
	<td width="200" bgcolor="#FFFF99" class="strong">Incorp.Date:</td>
	<td width="200" bgcolor="#FFFF99"><c:choose>
			   				<c:when test="${fn:length(obj.incorp_date) > 0}">
								<c:out value="${fn:substring(obj.incorp_date,4,6)}"/>/
								<c:out value="${fn:substring(obj.incorp_date,6,8)}"/>/
								<c:out value="${fn:substring(obj.incorp_date,0,4)}"/>
							</c:when>
						</c:choose>
						</td>
</tr>
<tr>
	<td width="204" class="strong">Corporation Type:</td><td width="250">${obj.corporation_type}</td>
	<td width="200" class="strong">Business Type:</td><td width="200">${obj.bus_type}</td>
</tr>
<tr>
	<td width="204" class="strong">Status:</td><td width="250">${obj.corporation_status}</td>
	<td width="200" bgcolor="#FFFF99" class="strong">Status Date:</td><td width="200" bgcolor="#FFFF99">${obj.corporation_status_date}</td>
</tr>
<tr>
	<td width="204" class="strong">Originated State:</td><td width="250">${obj.originated_state}</td>
	<td width="200" class="strong">Corp.ID:</td><td width="200">${obj.corporation_id}</td>
</tr>
<tr>
	<td width="204" class="strong">Filing Number:</td><td width="250">${obj.filing_number}</td>
	<td width="200" bgcolor="#FFFF99" class="strong">Tax ID:</td><td width="200" bgcolor="#FFFF99">${obj.federal_tax_id}</td>
</tr>
<tr>
	<td width="204" class="strong">Filing Date:</td><td width="250"><c:choose>
			   				<c:when test="${fn:length(obj.filing_date) > 0}">
								<c:out value="${fn:substring(obj.filing_date,4,6)}"/>/
								<c:out value="${fn:substring(obj.filing_date,6,8)}"/>/
								<c:out value="${fn:substring(obj.filing_date,0,4)}"/>
							</c:when>
						</c:choose></td>
						<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td width="204" class="strong">Filing State:</td><td width="250">${obj.filing_state}</td>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td width="204" class="strong">State:</td><td width="250">${obj.state}</td>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td width="204" class="strong">County:</td><td width="250">${obj.county}	
	</td>
	<td colspan="2">&nbsp;</td>
</tr>

</table>



				
