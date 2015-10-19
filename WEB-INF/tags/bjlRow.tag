<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="f" required="true" type="java.lang.String"%>
<%@attribute name="l" required="true" type="java.lang.String"%>
<%@attribute name="m" required="true" type="java.lang.String"%>
<%@attribute name="n" required="true" type="java.lang.String"%>
<c:choose>
<c:when test="${empty f}"><c:set var="field" value="---"/></c:when>
<c:otherwise><c:set var="field" value="${f}"/></c:otherwise>
</c:choose>

<c:choose>
<c:when test="${empty n}"><c:set var="field" value="---"/></c:when>
<c:otherwise><c:set var="field1" value="${n}"/></c:otherwise>
</c:choose>

<tr>
	<td  ><span class="style111" style="font-size:13px;">${l}</span></td>
	<c:choose>
	<c:when test="${empty f}"><td class="field" ><pre class="style111" style="font-size:13px;">---</td></c:when>
	<c:otherwise><td class="field" ><pre class="style111" style="font-size:13px; padding:0;margin:0;">${f}</pre></td></c:otherwise>
	</c:choose>
	
	<td><span class="style111" style="width:150px; padding:0;margin:0;"  style="font-size:13px;">${m}</span></td>
	<c:choose>
	<c:when test="${empty n}"><td class="field" style="padding:0;margin:0;"><pre class="style111">---</td></c:when>
	<c:otherwise><td class="field1"><pre class="style111" style="font-size:13px; padding:0;margin:0;">${n}</pre></td></c:otherwise>
	</c:choose>
	
</tr>