<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<div id="footer">
    <div class="wrap">
        <!--// security icons begin //-->
        <ul class="security">
            <li><a href="${bbbUrl}" target="_blank" title="Better Business Bureau"><img src="/springapp/images/common/footer-logo-bb.gif" alt="Better Business Bureau" /></a></li>
            <li><a onClick="${thawteOnClick}" href="#"  title="Thawte"><img src="/springapp/images/common/footer-logo-thawte.gif" alt="Thawte" /></a></li>
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