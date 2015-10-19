<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url var="closeWindowUrl" value="javascript:window.close()"  />
<c:url var="comodoUrl" value="http://www.instantssl.com"  />
<c:url var="bbbUrl" value="https://www.bbb.org/online/consumer/cks.aspx?ID=10404141025041762" />

<c:set var="thawteOnClick" value="window.open('http://www.instantssl.com', 'Thawte', 'width=500, height=500')" />
<c:set var="thawteA2" value="onClick=window.open('http://www.instantssl.com', 'Thawte', 'width=500, height=500')" />

<c:url var="showAgreementUrl" value="/legal/showAgreement.do" />
<c:url var="contactUsUrl" value="/legal/contactUs.do" />
<%--
<a onClick="${thawteOnClick}" href="#"  title="Thawte"></a>
<a ${thawteA} href="#"><img alt="Thawte Certified" src="/images/thawte_logo.gif"  style="border: 0;"/></a>
<a onClick="window.open('https://www.thawte.com/cgi/server/certdetails.exe?referer=https://premium.searchsystems.net/', 'Thawte', 'width=500, height=500')" href="#"><img alt="Thawte Certified" src="/images/thawte_logo.gif"  style="border: 0;"/></a>
<c:url var="thawteUrl" value="https://www.thawte.com" />
<c:url var="thawteUrl" value="javascript:window.open('https://www.thawte.com/cgi/server/certdetails.exe?referer=https://www.searchsystems.net/', 'Thawte', 'width=500, height=500')"  />
<c:url var="thawteUrl" value="https://www.thawte.com/core/process?process=public-site-seal-cert-details&public-site-seal-cert-details.referer=https://premium.searchsystems.net/"  />
<c:url var="thawteUrl" value="javascript:window.open('https://www.thawte.com/cgi/server/certdetails.exe?referer=https://www.searchsystems.net/', 'Thawte', 'width=500, height=500')"  />
<c:url var="bbbUrl" value="javascript:window.open('https://www.bbb.org/online/consumer/cks.aspx?ID=10404141025041762', 'Thawte', 'width=500, height=500')"  />
--%>