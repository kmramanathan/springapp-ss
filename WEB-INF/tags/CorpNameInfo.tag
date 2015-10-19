<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="nameObj" required="true" type="springapp.domain.CorpNameInfo"%>
<%@attribute name="count" required="true" type="java.lang.String" %>
<%@attribute name="clazz" required="false" type="java.lang.String"%>
<!-- start nameinfo -->
<table class="${clazz}" width="96%" cellpadding="1" cellspacing="1">
<tr>
<td width="104" class="strong">Name ${count}:</td>
<td>${nameObj.name}</td>
</tr>
<tr>
<td class="strong">Name Type:</td>
<td>${nameObj.name_type}</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>${nameObj.na_address1} ${nameObj.na_address2} ${nameObj.na_address3}</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>${nameObj.na_city} ${nameObj.na_state} ${nameObj.na_zip_code}</td>
</tr>
</table>
<!-- end nameinfo -->