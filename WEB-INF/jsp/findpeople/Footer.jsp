<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<!-- google analytics code -->
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." :
"http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost +
"google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>

<script type="text/javascript">
var pageTracker = _gat._getTracker("UA-6151264-1");
pageTracker._initData();
pageTracker._trackPageview();
</script>


<div id="footer">
    <div class="wrap">
        <!--// security icons begin //-->
        <ul class="security">
            <li><a href="${bbbUrl}" target="_blank" title="Better Business Bureau"><img src="/springapp/images/common/footer-logo-bb.gif" alt="Better Business Bureau" /></a></li><li><p>&nbsp; &nbsp;</p> </li>
            <li><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script>
</li>
            
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