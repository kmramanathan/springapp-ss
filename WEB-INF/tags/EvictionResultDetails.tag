<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="obj" required="true" type="springapp.domain.eviction.EvictionResults" %>
<%@attribute name="clazz" required="false" type="java.lang.String" %>
<%@attribute name="clazz1" required="false" type="java.lang.String" %>


<tr>
	<td colspan="2"><div align="left" class="style118">CASE INFORMATION</div></td>
</tr>
<tr>
	<td width="204">Case Number:</td><td width="653">${obj.case_number}</td>
</tr>
<tr>
	<td width="204">Book Number:</td><td width="653">${obj.book_number}</td>
</tr>
<tr>
	<td width="204">Page Number:</td><td width="653">${obj.page_number}</td>
</tr>
<tr>
	<td width="204">Filing Date:</td><td width="653"><c:choose>
			   				<c:when test="${fn:length(obj.file_date) > 0}">
								<c:out value="${fn:substring(obj.file_date,4,6)}"/>/
								<c:out value="${fn:substring(obj.file_date,6,8)}"/>/
								<c:out value="${fn:substring(obj.file_date,0,4)}"/>
							</c:when>
						</c:choose></td>
</tr>
<tr>
	<td width="204">Filing Type:</td><td width="653">${obj.court_type_code}</td>
</tr>
<tr>
	<td width="204">Filing Type Description:</td><td width="653">${obj.court_type_desc}</td>
</tr>
<tr>
	<td width="204">Court:</td><td width="653">${obj.name}<br>
		${obj.address1 } ${obj.address2 }<br>
		<c:if test="${not empty obj.city}">${obj.city },</c:if> ${obj.state } ${obj.zip }
		
		
	</td>
</tr>
<tr>
	<td colspan="2"><div align="left" class="style1191">PLAINTIFF (LANDLORD)</div></td>
</tr>
<tr>
	<td>Name:</td><td>${obj.p_fullname}</td>
</tr>
<tr>
	<td colspan="2"><div align="left" class="style1191">DEBTOR (TENANT)</div></td>
</tr>
<tr>
	<td>Name:</td><td>${obj.d_fullname}</td>
</tr>
<tr>
	<td>Address:</td><td>${obj.d_address_line_1} ${obj.d_address_line_2} ${obj.d_apartment_number}<br>
<c:if test="${not empty obj.d_city}">${obj.d_city},</c:if> ${obj.d_state } ${obj.d_zipcode }
	</td>
</tr>	
<tr>
	<td>County:</td><td>${obj.d_county_fips_desc}</td>
</tr>	
				
