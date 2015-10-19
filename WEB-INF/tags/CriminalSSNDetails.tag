<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="obj" required="true" type="springapp.domain.CriminalSSN.CriminalSSNResponseBean" %>
<%@attribute name="clazz" required="false" type="java.lang.String" %>
<%@attribute name="clazz1" required="false" type="java.lang.String" %>
<%@attribute name="ownrName" required="false" type="java.lang.String" %>
<%@attribute name="dateRecord" required="false" type="java.lang.String" %>
<%@attribute name="rowno" required="false" type="java.lang.String" %>

<!--  *********** Criminal SSN SEARCH RESULT DEATILS ***********
Fetusing fields from springapp.domain.CriminalSSN.CriminalSSNResponseBean.Java  
--> 

<c:choose>
 <c:when test="${not empty obj}"> 

<c:if test="${not empty obj.firstname}">
<tr>
	<td width="204">Name:</td><td width="653"> <c:if test="${not empty obj.firstname}">${obj.firstname} </c:if>  
	<c:if test="${not empty obj.middlename}">&nbsp;  ${obj.middlename}</c:if> &nbsp;<c:if test="${not empty obj.lastname}">  ${obj.lastname}</c:if> </td>
<br/>
</tr>
</c:if>
 
 
<c:if test="${not empty obj.firstname}">
<tr>
	<td width="204">Address:</td>
	<td width="653"> <c:if test="${not empty obj.streetnumber}">${obj.streetnumber}, </c:if>  
	<c:if test="${not empty obj.streetname}"> ${obj.streetname},</c:if> <br/>
	<c:if test="${not empty obj.city}">${obj.city},</c:if><c:if test="${not empty obj.state}">${obj.state},</c:if> <br/>
	<c:if test="${not empty obj.county}">${obj.county},</c:if><c:if test="${not empty obj.postalcode}">${obj.postalcode}</c:if>  
	
	</td>
</tr>
</c:if>  
<c:if test="${not empty obj.phoneinfo}">
<tr>
	<td width="204">Phone:</td>
	<td width="653"> ${obj.phoneinfo}
	</td>
</tr>
</c:if>
<tr>
	<td colspan="2"> <hr/></td>
</tr>
	
</c:when>
 <c:otherwise> 

</c:otherwise>
</c:choose>