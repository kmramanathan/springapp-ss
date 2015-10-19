<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="clazz" required="false" type="java.lang.String" %>
<%@attribute name="row" required="true" type="java.lang.String" %>
<%@attribute name="obj" required="true" type="net.searchsystems.limestone.bean.BGCOffenseBean"%>

<!-- start offenses -->


<div class="offense">
Result #<c:out value="${row}"/>

<table border="0" cellpadding="0" cellspacing="0">
<neon:bgcOffenseField l="Description"  f="${obj.description}" clazz="${clazz}"/>
<neon:bgcOffenseField l="Statute"  f="${obj.statute }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Degree Of Offense"  f="${obj.degreeOfOffense}" clazz="${clazz}"/>
<neon:bgcOffenseField l="Offense Date"  f="${obj.offenseDate }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Arrest Date"  f="${obj.arrestDate }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Arresting Agency"  f="${obj.arrestingAgency }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Disposition"  f="${obj.disposition }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Disposition Date"  f="${obj.dispositionDate }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Sentence"  f="${obj.sentence }"/>
<neon:bgcOffenseField l="Sentence Date"  f="${obj.sentenceDate }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Confinement"  f="${obj.confinement }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Probation"  f="${obj.probation }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Fine"  f="${obj.fine }" clazz="${clazz}"/>
<neon:bgcOffenseField l="Plea"  f="${obj.plea }" clazz="${clazz}"/>
</table>
</div>

<!-- end offenses -->