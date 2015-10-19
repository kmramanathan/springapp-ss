<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="javascript" required="false" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" type="java.lang.String"%>
<%@attribute name="rightSidebar" required="true" type="java.lang.String"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>${title}</title>
<style type="text/css">
	<c:forTokens var="item" items="${stylesheet}" delims=",">
    @import url("/springapp/css/${item}");
	</c:forTokens>    
</style>

<c:forTokens var="item" items="${javascript}" delims=",">
<script type="text/javascript" src='<c:url value="/js/${item}"/>'></script>
</c:forTokens>    
<jsp:invoke fragment="head" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">

<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>
</head>

<body>
  <div class="bodywrap">
    <center>
      <div id="owrap">
        
        <!--// masthead begins //-->
        <div id="masthead">
            <div id="logo"><a href="http://www.searchsystems.net/" title="SearchSystems.net"><img src="images/common/updated-logo.png" alt="SearchSystems.net" /></a></div>
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
					<jsp:doBody />
					</div>
                    <!--// content ends //-->

                 </div>
              </div>
            </td>
            <!--// column one ends //--> 

            <!--// column two begins //-->
            <td id="coltwo" align="left">
                <div class="wrap">

                    <!--// menu begins //-->
					<jsp:include page="${rightSidebar}"/>
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
                <li><a href="${bbbUrl}" target="_blank" title="Better Business Bureau"><img src="/springapp/images/common/footer-logo-bb-new.gif" alt="Better Business Bureau" /></a></li>
                <li><p>&nbsp;&nbsp;</p> </li>
                <li><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script></p>
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

