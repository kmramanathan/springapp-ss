<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="addrObj" required="true" type="springapp.domain.CorpAddress"%>
<%@attribute name="count" required="true" type="java.lang.String" %>
<%@attribute name="clazz" required="false" type="java.lang.String"%>

<!-- start corpaddress -->
<table class="${clazz}" width="96%" cellpadding="1" cellspacing="1">
<tr>
<td width="104" class="strong">Address ${count}:</td>
<td>${addrObj.a_address1} ${addrObj.a_address2} ${addrObj.a_address3}</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>${addrObj.a_city} ${addrObj.a_state} ${addrObj.a_zip_code}</td>
</tr>
</table>
<!-- end corpaddress -->