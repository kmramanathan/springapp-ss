<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:userPage title="Index">
<jsp:attribute name="stylesheet">members-legacy.css</jsp:attribute>

<jsp:body>

<h1>My Account Settings</h1>

<div class="note-box">
Welcome to the account administration area where you may use the selections
below to change your account settings. Click on the
<strong>Change My Password</strong> link to change your password. Click on
<strong>Change My Contact Information</strong> to change your registered name,
email address, or mailing address. If you need to change your credit card
information or use a different credit card, click on the link that says
<strong>Change My Billing Information.</strong>

<br/><br/>

If you have any problems with using this service to change your settings, please
<a href="${contactUsUrl}">contact us</a>. We can help you 
change the settings or answer any questions that you might have.
</div>


<div id="my-account-options">
<table style="font-size: 1.3em;">
    <tr>
        <td class="td-image"><a href="changePassword.do" target="_top"><img src='<c:url value="/img/keys.jpg"/>' style="height: 61px; width: 85px;" alt="Change My Password" border="0" /></a></td>
        <td class="td-text"><a href="changePassword.do">Change My Password</a></td>
    </tr>
    <tr>
        <td class="td-image"><a href="updatePersonalInformation.do" target="_top"><img src='<c:url value="/img/at.jpg"/>' style="height: 61px; width: 85px;" alt="Update My Personal Info" border="0" /></a></td>
        <td class="td-text"><a href="updatePersonalInformation.do">Update My Personal Information</a></td>
    </tr>
    <tr>
        <td class="td-image"><a href="updateBillingInfo.do" target="_top"><img src='<c:url value="/img/ccard.jpg"/>' style="height: 61px; width: 85px;" alt="Update My Billing Info" border="0" /></a></td>
        <td class="td-text"><a href="updateBillingInfo.do">Update My Billing Information</a></td>
    </tr>
    <tr>
        <td class="td-image"><a href="cancelAccount.do" target="_top"><img src='<c:url value="/img/door_knob.jpg"/>' style="height: 62px; width: 84px;" alt="Cancel My Account" border="0" /></a></td>
        <td class="td-text"><a href="cancelAccount.do">Cancel My Account</a></td>
    </tr>    
    
    <tr>
        <td class="td-image"><a href="logout.do" target="_top"><img src='<c:url value="/img/hands.jpg"/>' style="height: 62px; width: 84px;" alt="Logout" border="0" /></a></td>
        <td class="td-text"><a href="logout.do">Logout</a></td>
    </tr>

</table>
</div>


</jsp:body>

</neon:userPage>
