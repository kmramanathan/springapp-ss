<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="clazz" required="false" type="java.lang.String" %>
<%@attribute name="obj" required="true" type="springapp.domain.NSS.NssOffenseBean"%>

<!-- start offenses -->


<div class="offense">


<table border="0" cellpadding="0" cellspacing="0">
<neon:nssOffenseField l="Description"  f="${obj.description}" clazz="${clazz}"/>
<neon:nssOffenseField l="Statute"  f="${obj.statute }" clazz="${clazz}"/>
<neon:nssOffenseField l="Degree Of Offense"  f="${obj.degreeOfOffense}" clazz="${clazz}"/>
<neon:nssOffenseField l="Offense Date"  f="${obj.offenseDate }" clazz="${clazz}"/>
<neon:nssOffenseField l="Arrest Date"  f="${obj.arrestDate }" clazz="${clazz}"/>
<neon:nssOffenseField l="Arresting Agency"  f="${obj.arrestingAgency }" clazz="${clazz}"/>
<neon:nssOffenseField l="Disposition"  f="${obj.disposition }" clazz="${clazz}"/>
<neon:nssOffenseField l="Disposition Date"  f="${obj.dispositionDate }" clazz="${clazz}"/>
<neon:nssOffenseField l="Sentence"  f="${obj.sentence }"/>
<neon:nssOffenseField l="Sentence Date"  f="${obj.sentenceDate }" clazz="${clazz}"/>
<neon:nssOffenseField l="Confinement"  f="${obj.confinement }" clazz="${clazz}"/>
<neon:nssOffenseField l="Probation"  f="${obj.probation }" clazz="${clazz}"/>
<neon:nssOffenseField l="Fine"  f="${obj.fine }" clazz="${clazz}"/>
<neon:nssOffenseField l="Plea"  f="${obj.plea }" clazz="${clazz}"/>
</table>
</div>

<!-- end offenses -->