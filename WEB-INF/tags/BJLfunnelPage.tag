<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="javascript" required="false" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" type="java.lang.String"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% if(session.getAttribute("username") != null){
	String username = (String) session.getAttribute("username");
	response.sendRedirect("http://www.searchsystems.net/shareSession.php?usn="+username);	
}
	%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>
</head>

<body>
  <table width="893" height="191%" align="center" style="height:100%">
<tr>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="http://www.searchsystems.net">Search Systems Home</a> | <a href="signup.do">Sign Up</a> | <a href="https://premium.searchsystems.net/login.php">Log In</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
    <img src="/springapp/images/BJL/logo_02.gif" alt="" /></td>
	</tr>
	
					<jsp:doBody />
					 
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;">
        <table width="862" border="0" align="center">
          <tr>
            <td width="659" class="content_1" style="padding:0 30px;">
              <p><span class="style15">All information retrieved from or through SearchSystems.net must be utilized in accordance  with the User Agreement and all applicable state and federal laws, including the <a href="http://www.ftc.gov/os/statutes/fcra.htm">Fair Credit Reporting Act</a>;  any violation of these will be grounds for immediate termination of your account without  notice. <br />
                <br />
                <strong>DISCLAIMER:</strong> No warranty of any type is provided as to the quality or accuracy of the  information obtained from or through SearchSystems.net, and any reliance on that  information is solely at your own risk and responsibility.  Information contained herein  is derived solely from public records, which may not be 100 percent accurate or complete.  Users should not assume that this data provides a complete or accurate record of any  person's bankruptcy, judgment, or tax lien history.<br />
                <br />
Pacific Information Resources, Inc. dba Search Systems assumes no liability for any claims  for damages arising from the use of this data beyond the actual cost of the searches  performed. </span></p></td>
            <td width="193" class="content_1" style="padding:0 30px;">
              <p align="center"><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script></p></p>
            <p align="center"><a href="${bbbUrl}" target="_blank"><img src="/springapp/images/BJL/bbb-clickratingsm.gif" alt="bbb" width="135" height="52" ></a></p></td>
          </tr>
        </table>
        <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="813" border="0" align="center" bgcolor="#FEF8A5">
      <tr>
                  <td width="895"><p align="left" class="style30">&quot;Your website is the search engine of the future... a source that provides relief from the usual frustrations of online searching. SearchSystems.net is so consistently dependable, it feels as if you're not on this planet. It's just perfect.&quot; - Rose</p>
                      <p align="left" class="style31">&nbsp;</p>
                    <p align="left" class="style30">&quot;Your staff is great. SearchSystems.net is a 5-star program on a basic budget.&quot; - Rick</p>
                    <p align="left" class="style31">&nbsp;</p>
                <p align="left" class="style30">&quot;The one source I always mention in my presentations for private investigators and professional researchers.&quot;  - Tamara Thompson, P.I.</p></td>
                </tr>
            </table>
            <p>&nbsp;</p></td>
          </tr>
        </table>        </td>
  </tr>
	<tr>
		<td width="893" class="footer" style="height:56px; background:url(/springapp/images/BJL/bg_footer_11.jpg) top no-repeat; padding:15px 0 0 60px;">
			<p>Copyright &copy; 2009 Pacific Information Resources, Inc. All rights reserved.</p>
			<p><a href="http://www.searchsystems.net/tos.php">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php">About Us</a></p></td>
	</tr>
</table>


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

