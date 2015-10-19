<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="obj" required="true" type="springapp.domain.CriminalSearch.CriminalResponseBean" %>


<!-- DDN Information -->
  			
<tr>
	<td colspan="2"><div align="left" class="style118">CRIMINAL  INFORMATION</div></td>
</tr>
<tr>
	<td width="204"><b>Name & Address Info: </b></td>
	<td width="653">
	 <c:if test="${not empty obj.firstName}">Name: &nbsp; ${obj.firstName} </c:if>
	<c:if test="${not empty obj.middleName}"> &nbsp; ${obj.middleName} </c:if>
	 <c:if test="${not empty obj.lastName}"> &nbsp; ${obj.lastName} </c:if>	
	 <c:if test="${not empty obj.DOB}"><br/> DOB: &nbsp; ${obj.DOB} </c:if>
	 <c:if test="${not empty obj.sex}"><br/> Sex: &nbsp; ${obj.sex} </c:if>
	
 </td>
</tr>
<tr>
<td width="204"><br/></td>
<td width="653">
	 <br/>
</td>
</tr>
 <tr>
	<td width="204"><b>Case Info:</b></td>
	<td width="653">
	<c:if test="${not empty obj.offense}">Offense: &nbsp; ${obj.offense} </c:if>
	<c:if test="${not empty obj.caseNum}"><br/> CaseNum: &nbsp; ${obj.caseNum} </c:if>
	 <c:if test="${not empty obj.fileDate}"><br/> FileDate: &nbsp; ${obj.fileDate} </c:if>
	 <c:if test="${not empty obj.disposition}"><br/> Disposition: &nbsp; ${obj.disposition} </c:if>
	 <c:if test="${not empty obj.dispositionDate}"><br/> DispositionDate: &nbsp; ${obj.dispositionDate} </c:if>
	 <c:if test="${not empty obj.offnsCount}"><br/>Offense County: &nbsp; ${obj.offnsCount} </c:if>	
	 <c:if test="${not empty obj.citation}"><br/> Citation: &nbsp; ${obj.citation} </c:if>
	 <c:if test="${not empty obj.sourceofRecord}"><br/>Source of Record: &nbsp; ${obj.sourceofRecord} </c:if>	
	
 </td>
</tr>

 <tr>
<td width="204"><br/></td>
<td width="653">
	 <br/>
</td>
</tr>