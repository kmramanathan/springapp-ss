<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="java.util.*" %>

<%-- set up vars for use in page --%>
<%-- move text to either login or confirm page --%>
<c:url var="exclamationImageUrl" value="/conversion/images/common/conversion_upgrade2.gif"/>
<c:set var="mode" value="${param.mode}"/>

<c:choose>
<%-- monthly --%>
<c:when test="${mode == 1}">
<c:url var="topImageUrl" value="/conversion/images/common/upgrade_head.png"/>
<c:url var="submitButtonImageUrl" value="/conversion/images/common/bt-upgrade.png"/>
<c:set var="blurbHeadline">Yes, I'd like to upgrade to an Annual SearchSystems.net membership for only $29.95.</c:set>
<c:set var="blurbText">
By clicking this button, you are authorizing us to convert your monthly account 
to an annual subscription. You may close your account at any time.
</c:set>

</c:when>

<%-- annual --%>
<c:when test="${mode == 2}">
<c:url var="topImageUrl" value="/conversion/images/common/renew-ann-head.png"/>
<c:url var="submitButtonImageUrl" value="/conversion/images/common/bt-renew.png"/>
<c:set var="blurbHeadline">Yes, I'd like to renew my Annual account and save money.</c:set>
<c:set var="blurbText">
By clicking this button, you are authorizing us to renew your annual account.  
You may close your account at any time.
</c:set>

</c:when>

<%-- premium only --%>
<c:otherwise>
<c:url var="topImageUrl" value="/conversion/images/common/upgrade_head.png"/>
<c:url var="submitButtonImageUrl" value="/conversion/images/common/bt-continue.png"/>
<c:set var="blurbHeadline">Yes, I'd like to upgrade my account to a full SearchSystems.Net membership.</c:set>
<c:set var="blurbText">
By clicking this button, you are authorizing us to convert your Premium-only account 
to a full annual subscription. You may close your account at any time.
</c:set>
</c:otherwise>

</c:choose>

<neon:funnelPage title="Search Systems - Upgrade to Annual" rightSidebar="GenericConversionSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<style type="text/css">
<!--
.style12 {
	color: #666666
}

.style13 {
	color: #000066;
	font-weight: bold;
	font-size: 1.1em;
}
.utaButton {
	background-image: url();
	width: 200px;
	height: 60px;
}
-->
</style>

           	  <div id="title"><img src="${exclamationImageUrl}" alt="exclamation" width="91" height="80" /><img src="${topImageUrl}" alt="upgrade-to-annual" width="400" height="75" /></div>
              
				<div id="formwrapper">
					
                    <div id="formwrapper2">
                      <form:form id="loginForm" method="post" >
                        <h3><strong>User Account Verification</strong></h3>
                        <p>Enter your existing username and password.</p>
                        <dir>
                        <li>
                          <label>Username:</label>
							<form:input path="username" cssClass="formfield" size="40" />
                        </li>
                        <li>
                          <label>Password:</label>
							<form:password path="password" cssClass="formfield" size="40" />
                        </li>                        
                        </dir>
<form:errors path="*" element="div" id="error" cssClass="error-box"/>
                        <h3><strong>${blurbHeadline}</strong></h3>                        

                        <p align="center">
<script>
function doSubmit() {
	document.getElementById('loginForm').submit();
}
</script>
<!-- 
						<a href="javascript:doSubmit()">
<img src="images/common/bt-uta-white.jpg"/>						
</a>
 -->
	<input type="image" src="${submitButtonImageUrl}" alt="Upgrade"/>
						</p>
                        
<p align="left"><i>${blurbText}</i></p>

                        <p align="left">We offer the largest, most complete and up-to-date directory of public records online.</p>
                        <p align="left"> Search tens of thousands of databases organized for your convenience - get instant results.</p>
                        <p align="left">Search corporate, property, mortgage, court, criminal, sex offender,  birth, death,  marriage, and many other types of databases from your home or office - billions of records.</p>
                        <p align="left">Don't guess where to find the information you need - we find the source for you.</p>
                        <p align="left">Our site is continually expanding - we add to and update our site on a daily basis.</p>
                        <p align="left">&nbsp;</p>
                        
<h3 align="left"><strong>Plus!</strong></h3>
                        <p align="left">Access the largest criminal records database online (300 million records) and get a $10 discount* on each search.</p>
                        <p align="left">Access our bankruptcy, judgment and tax lien database (over 100 million records) and get $5 off each search.</p>
                        <p align="left">Superior customer service - rated AAA (best) by the Better Business Bureau.</p>
                        <p align="left">Free Guides to help you find the information you need.</p>
                        <p align="left"><span class="style15">*Members pay $19.95 for a nationwide search (regularly $29.95) and $7.95 for a statewide search (regularly $17.95)</span></p>
                        <p align="left">&nbsp;</p>
                        
<h3 align="left"><strong>And More!</strong></h3>
                        <p align="left">We are working to improve the site and our services and will soon release a <strong>people finder </strong>database. We are also working with First American Title to provide their <strong>nationwide property information</strong> online. </p>
                        
                        <ul id="submit">
                          <li><a onClick="${thawteOnClick}" href="#"  title="Thawte"><img src="images/common/col-one-logo-thawte.gif" alt="Thawte, It's a trust thing" /></a></li>
                          <li><a href="${bbbUrl}" title="Better Business Bureau"><img src="images/common/col-one-logo-bbb.gif" alt="Better Business Bureau" /></a></li>
                          <li></li>
                        </ul>

                      </form:form>
                      <div class="cleft"></div>
                    </div>
                    <p align="left">&nbsp;</p>
              </div>

</jsp:body>
</neon:funnelPage>


