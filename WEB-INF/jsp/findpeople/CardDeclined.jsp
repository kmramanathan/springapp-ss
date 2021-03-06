<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%
      String resultType = (String) session.getAttribute("resultType");
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Purchase Failed</title>
<link href="/springapp/css/funnel.css" rel="stylesheet" type="text/css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
</head>

<body>
  <div class="bodywrap">
    <center>
      <div id="owrap">
        
        <!--// masthead begins //-->
        <div id="masthead">
            <div id="logo"><a href="http://www.searchsystems.net/" title="SearchSystems.net"><img src="/springapp/images/common/updated-logo.png" alt="SearchSystems.net" /></a></div>
        </div>
        <!--// masthead ends //-->
        
        <!--// main content area begins //-->
        <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable">
          <tr valign="top">
            
            <!--// column one begins //--> 
            <td id="colone" align="left">
              <div class="wrap">
                <div class="innerwrap">

                    <!--// content begins //-->
					<%-- main content will go here --%>
					<div id="content">
						<c:url var="purchaseUrl" value="purchaseMexicanRecordSearch.do" />
									  
						<div id="formwrapper">
						
						<strong>Purchase Failed</strong><p/>
						
						Your credit card could not be authorized.<p/>
						
						<p>Error Message: <c:out value="${cardDeclineReason}"/></p>
						
						<p>
						We're sorry that there was a problem processing your transaction. Please click
						<a href="purchaseFPSearch.do?resultType=<%=resultType %>">here</a>
						to correct or complete the billing
						information and start your search again. If you encounter problems, use our
						<a href="${contactUsUrl}" target="_blank">contact form</a>
						or call us at (800) 350-2232 (outside the US, call +1-805-375-4041). Customer
						service is available Monday to Friday, from 8:00 AM to 5:00 PM PST (UTC-8) or
						PDT (UTC-7).
						</p>
						
						</div>					</div>
                    <!--// content ends //-->

                 </div>
              </div>
            </td>
            <!--// column one ends //--> 

            <!--// column two begins //-->
            <td id="coltwo" align="left">
                <div class="wrap">

                    <!--// menu begins //-->
					<div id="security"></div>

																  
						<div id="photo"><img src="/springapp/images/common/image-male-searching.jpg" alt="Search Systems" /></div>
						  
						<div id="testimonials">
						  
							<p>"Thank you, my criminal records search gave me just what I needed. The Better Business Bureau was right with their "Triple A" rating for your company.  I will definitely recommend your service."</p>
							<p class="credit">- Sabine</p> 
											 
						</div>
                    <!--// menu ends //-->

                </div>
            </td>
            <!--// column two ends //--> 

	        </tr>
	    </table>
    <!--// main content area ends //-->

  </div>

    <!--// mastfoot begins //-->
    <div id="footer">
        <div class="wrap">
            <!--// security icons begin //-->
            <ul class="security">
                <li><a href="${bbbUrl}" target="_blank" title="Better Business Bureau"><img src="/springapp/images/common/footer-logo-bb.gif" alt="Better Business Bureau" /></a></li>
                
            </ul>
            <!--// security icons end //-->

            <!--// primary footer links //-->
            <ul class="primary">
                <li>Copyright &copy; 2003-2008 Pacific Information Resources, Inc. All rights reserved.</li>
            </ul>
            <!--// primary footer links ends //-->

            <!--// secondary (lesser) footer links begin //--> 
            <ul class="secondary">
                <li><a href="${contactUsUrl}" target="_blank" title="Contact Us">Contact Us</a> | </li>
                <li><a href="${showAgreementUrl}?version=privacy" target="_blank" title="Privacy Policy">Privacy Policy</a> | </li>
                <li><a href="${showAgreementUrl}?version=disclaimer" target="_blank" title="Disclaimer">Disclaimer</a></li>
            </ul>
            <!--// secondary footer ends //-->
        </div>
    </div>
    <!--// mastfoot ends //-->

	</center>
  </div>

<!-- google analytics code -->
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
var pageTracker = _gat._getTracker("UA-6151264-1");
pageTracker._initData();
pageTracker._trackPageview();
</script>

</body>
</html>

