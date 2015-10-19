<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="d" required="true" type="net.searchsystems.limestone.BjlResults"%>

<!-- start bjl details -->
<table border="0" width="926">

<neon:bjlRow l="Case Number:" f="${d.caseNumber}" m="Attorney:" n="${d.attorney}"/>

<neon:bjlRow l="Filing Type:" f="${d.filingType}" m="Attorney Phone:" n="${d.attorneyPhone}"/>

<neon:bjlRow l="Entity Type:" f="${d.entityType}" m="Attorney Address:" n="${d.attorneyAddress}"/>

<neon:bjlRow l="Defendant:" f="${d.defendant}" m=" " n="${d.addressCity}, ${d.attorneyState} ${d.attorneyZip}"/>

<neon:bjlRow l="Address:" f="${d.address}" m=" " n=" "/>

<neon:bjlRow l=" " f="${d.addressCity}, ${d.addressState} ${d.addressZip} " m="Court ID Code:" n="${d.courtIdCode}"/>

<neon:bjlRow l=" " f=" " m="Court Information:" n="${d.courtName}"/>

<neon:bjlRow l="SSN/Tax ID:" f="${d.ssnTaxId}" m="Court Phone:" n="${d.courtPhone}"/>

<neon:bjlRow l="SSN Verifier:" f="${d.ssnVerifier}" m=" " n=" "/>

<neon:bjlRow l=" " f=" " m="Judges Initials" n="${d.judgesInitials}"/>

<neon:bjlRow l="Plaintiff:" f="${d.plaintiff}" m="Bankruptcy Dismissal:" n="${d.bankruptcyDismissal}"/>

<neon:bjlRow l="Plaintiff Phone:" f="--- " m="Unlawful Detainer:" n="${d.unlawfulDetainer}"/>

<neon:bjlRow l="Plaintiff Address:" f="--- " m=" " n=" "/>

<neon:bjlRow l=" " f="--- " m="Action Type:" n="${d.actionType}"/>

<neon:bjlRow l=" " f=" " m=" " n=" "/>

<neon:bjlRow l="Amount/Liability:" f="${d.amountLiability}" m=" " n=" "/>

<neon:bjlRow l="Assets Available:" f="${d.assetsAvailable}" m=" " n=" "/>

</table>

 <h3 align="left" style="width:928px" class="style113">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - </h3>
<!-- end bjl details -->