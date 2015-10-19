<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<div id="options">
<form name="loginform" action="https://www.searchsystems.net/login.php" method="get">
<input type="hidden" name="linkid" value="${linkid}"/>
      <a href="javascript:submitForm()"><img src="images/common/already-member.png" alt="Login" /></a>
</form>
<script language="JavaScript">
function submitForm()
{
  document.loginform.submit();
}
</script>
</div>
  
<div id="photo"><img src="images/common/image-female-businesswoman.jpg" alt="Search Systems" /></div>
  
<div id="testimonials">
  
    <p>"Search Systems provides access to the most extensive, best-organized and most frequently updated online directory of direct links to free government and private source public records.  It is the one source I always mention in my presentations for private investigators and professional researchers."</p>
    <p class="credit">- Tamara Thompson, P.I.<br /><a href="http://pibuzz.com/" target="_blank" title="PIBuzz">pibuzz.com</a></p>
    
</div>
