<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="obj" required="true" type="springapp.domain.RealProperty.RealPropResponseBean" %>
<%@attribute name="clazz" required="false" type="java.lang.String" %>
<%@attribute name="clazz1" required="false" type="java.lang.String" %>
<%@attribute name="ownrName" required="false" type="java.lang.String" %>
<%@attribute name="dateRecord" required="false" type="java.lang.String" %>
<%@attribute name="rowno" required="false" type="java.lang.String" %>

<!--  *********** REAL PROPERTY SEARCH RESULT DEATILS ***********
Fetusing fields from springapp.domain.RealProperty.RealPropResponseBean.Java  
-->


<!-- Property Information -->

<c:choose>
 <c:when test="${obj.primaryowner != ownrName}"> 
 <c:if test="${not empty rowno}">
 			
<td colspan="2">
     <hr /> <br/>
</td>
</tr>
  	 	</c:if>
<tr>
	<td colspan="2"><div align="left" class="style118">PROPERTY INFORMATION</div></td>
</tr>
<tr>
	<td width="204">House Address:</td><td width="653">
	<c:if test="${not empty obj.houseno}">${obj.houseno }</c:if>&nbsp;<c:if test="${not empty obj.street}">${obj.street }</c:if>
	<br/><c:if test="${not empty obj.city}">${obj.city } </c:if><c:if test="${not empty obj.state}">${obj.state } </c:if>
	<c:if test="${not empty obj.zip}">${obj.zip }</c:if>
 </td>
</tr>
<c:if test="${not empty obj.phoneno}">
<tr>
	<td width="204">Phone number:</td><td width="653">${obj.phoneno}</td>
</tr>
</c:if>
<c:if test="${not empty obj.apn}">
<tr>
	<td width="204">Apn:</td><td width="653">${obj.apn}</td>
</tr>
</c:if>
<c:if test="${not empty obj.fipscode}">
<tr>
	<td width="204">Flips Code:</td><td width="653">${obj.fipscode}</td>
</tr>
</c:if>
<c:if test="${not empty obj.municipalcode}">
<tr>
	<td width="204">Municipal Code:</td><td width="653">${obj.municipalcode}</td>
</tr>
</c:if>
<c:if test="${not empty obj.accountnumber}">
<tr>
	<td width="204">Account Number:</td><td width="653">${obj.accountnumber}</td>
</tr>
</c:if>
<tr>
<td colspan="2">
   <br/>
</td>
</tr>	

<tr>
	<td colspan="2"><div align="left" class="style1191">Owner Information</div></td>
</tr>
<c:if test="${not empty obj.primaryowner}">
<tr>
	<td>Primary Owner:</td><td>${obj.primaryowner}</td>
</tr>
</c:if>
<c:if test="${not empty obj.secondaryowner}">
<tr>
	<td>Secondary Owner:</td><td>${obj.secondaryowner}</td>
</tr>
</c:if>
<tr>
	<td>Address:</td>
	<td>
	<c:if test="${not empty obj.owneraptno}">${obj.owneraptno}, </c:if> <c:if test="${not empty obj.ownerhouseno}">${obj.ownerhouseno},</c:if> 
	<c:if test="${not empty obj.ownerstreet}"> ${obj.ownerstreet} </c:if> 
	<br/>
	<c:if test="${not empty obj.ownerstate}"> ${obj.ownerstate} </c:if> <c:if test="${not empty obj.ownerzip}"> ${obj.ownerzip} </c:if>
<br>
	</td>
</tr>
<tr>
	<td>Mode:</td><td>${obj.mode}</td>
</tr>	

<c:if test="${obj.calculatedvalue ne 0}" >
<tr>
	<td>Calculated Value:</td><td>${obj.calculatedvalue}</td>
</tr>	
	</c:if>	
<c:if test="${obj.saleprice ne 0}" > 
<tr>
	<td>Sale Price:</td><td>${obj.saleprice}</td>
</tr>	
</c:if>
<c:if test="${not empty obj.saledate}">
<tr>
	<td>Sale Date:</td><td>
	
	<c:choose>
			   				<c:when test="${fn:length(obj.saledate) > 0}">
								<c:out value="${fn:substring(obj.saledate,4,6)}"/>/
								<c:out value="${fn:substring(obj.saledate,6,8)}"/>/
								<c:out value="${fn:substring(obj.saledate,0,4)}"/>
							</c:when>
	</c:choose>
	</td>
</tr>
</c:if>
<c:if test="${not empty obj.daterecorded}">
<tr>
	<td>Date Recorded:</td>
	<td>
	<c:choose>
			   				<c:when test="${fn:length(obj.daterecorded) > 0}">
								<c:out value="${fn:substring(obj.daterecorded,4,6)}"/>/
								<c:out value="${fn:substring(obj.daterecorded,6,8)}"/>/
								<c:out value="${fn:substring(obj.daterecorded,0,4)}"/>
							</c:when>
	</c:choose>
	</td>
</tr>
</c:if>	
	
</c:when>
 <c:otherwise>
<c:if test="${not empty obj.daterecorded}">
<tr>
	<td>Date Recorded:</td>
	<td>
	<c:choose>
			   				<c:when test="${fn:length(obj.daterecorded) > 0}">
								<c:out value="${fn:substring(obj.daterecorded,4,6)}"/>/
								<c:out value="${fn:substring(obj.daterecorded,6,8)}"/>/
								<c:out value="${fn:substring(obj.daterecorded,0,4)}"/>
							</c:when>
	</c:choose>
	</td>
</tr>
</c:if>

</c:otherwise>
</c:choose>