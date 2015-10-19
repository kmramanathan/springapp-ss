<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:userPage title="Change Password">
<jsp:attribute name="stylesheet">members-legacy.css</jsp:attribute>
<jsp:body>

<c:url value="/img/required.gif" var="imgRequiredField"/>
<c:url value="/img/astrix.gif" var="imgAstrix"/>

<div class="main-content">
<div class="page-header"><h1>Cancel Account</h1></div><div></div>
<div class="form-box">

<form method="post">

<div class="sub-form">	
	<div class="instructions-flags">
		<img src="${imgRequiredField}" width="158" height="15" alt="Required fields">
	</div>

	<div class="instructions">
		<p>
		By using this form your account will cancel <b>immediately</b>.  If you have a monthly 
		subscription and would like to use your account until it expires at the end of 
		the billing month, please <a href="http://www.searchsystems.net/webmaster.php">contact us</a>, 
		or call SearchSystems.net at 800-350-2232 (U.S.) or +1-805-375-4041 (International) 
		during regular business hours (Pacific Standard Time).</p>
		
		<p>
		You will logged out as soon as you click "Continue".</p>
	</div>

	<div class="sub-form-inputs">
        <table>
		<tr>
			<td colspan="2">
	        <p>Your feedback is important to us.  Please let us know why you're
	        canceling your account or what we can do to improve our service.</p>
			</td>
		</tr>
		<tr>
			<td class="label">Reason</td>
			<td><textarea name="cancelComment" style="width: auto;"></textarea></td>
		</tr>
        </table>
	</div>
	
	<div class="form-submit">
    	<input type="submit" class="submit" value="Continue"/>
    </div>
</div>

</form>
</div>

</jsp:body>
</neon:userPage>
