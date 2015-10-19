<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="hisObj" required="true" type="springapp.domain.CorpHistory"%>
<%@attribute name="count" required="true" type="java.lang.String" %>
<%@attribute name="clazz" required="false" type="java.lang.String"%>
<!-- start Corp History -->
<table class="${clazz}" width="96%" cellpadding="1" cellspacing="1">
<c:if test="${not empty hisObj.info_title or not empty hisObj.info_desc}">
<tr>
<td width="104" class="strong">History ${count}:</td>
<td>${hisObj.info_title} ${hisObj.info_desc}</td>
</tr>
</c:if>
<c:if test="${not empty hisObj.history_pages or not empty hisObj.history_code}">
<tr><td width="104" class="strong">History ${count}:</td><td>${hisObj.history_pages} ${hisObj.history_code} 
						<c:choose>
			   				<c:when test="${fn:length(hisObj.effective_date) > 0}">
								<c:out value="${fn:substring(hisObj.effective_date,4,6)}"/>/
								<c:out value="${fn:substring(hisObj.effective_date,6,8)}"/>/
								<c:out value="${fn:substring(hisObj.effective_date,0,4)}"/>
							</c:when>
						</c:choose>
						</td>
</tr>
</c:if>
<tr>
<td></td>
<td>${hisObj.history_desc}</td>
</tr>
<tr>
<td></td>
<td>${hisObj.history_num} ${hisObj.history_name} ${hisObj.locator_num}</td>
</tr>



</table>
<!-- end aliases -->