<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>

<neon:funnelPage title="Value Proposition" rightSidebar="ValuePropositionSidebar.jsp">
	<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

	<jsp:body>

<div id="title"><img
			src="images/common/need-access-2.gif"
			alt="Need Access to Even More Records?, Become a Member! Sign Up Today! It's Easy, Safe &amp; Secure." /></div>
  
    <div class="twocol">
        <p>
SearchSystems.net is the professional's choice for searching public records and criminal
records.  Every <b>SearchSystems.net Member</b> receives unlimited 
access to tens of thousands of handpicked public record databases and discounts on criminal 
records searches.  Sign up today to start searching and 
take advantage of exclusive <b>members only savings!</b>
    </div>
      
    <div class="twocol">
		<div class="arrow">
      <ul>
      
		<li>Membership gives you $10 off every Criminal Record Search.</li>
          <li>Access our Database of 300 million State, National, International, and Terrorist Criminal Records</li>
          <li>Access our Directory of over 44,000 Searchable Public Record Databases</li>
          <li>Direct links to over 2,100 additional Criminal Record Databases</li>
          <li>Only $29.95 for a whole year!</li>
          <li>Service you can trust. Rated AAA (Best) by the Better Business Bureau.</li>
      </ul>
</div></div>
      
<div class="buttons">

<c:url value="signup.do" var="signupUrl"/>
<c:url value="search.do?nationwide=${nationwide}" var="searchUrl"/>

<div class="twocol rightborder"><a href='<c:out value="${searchUrl}"/>' title=""><img src="images/common/bt-continue-to-search.png" alt="" /></a></div>
  
<div class="twocol"><a href='<c:out value="${signupUrl}"/>' title=""><img src="images/common/template-become-a-member.png" alt="" /></a></div>
  
  <div class="cleft"></div>

</div>

<br />
		<br />
		<br />

</jsp:body>

</neon:funnelPage>
