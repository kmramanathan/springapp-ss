<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Thank You" rightSidebar="SearchSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<iframe src="https://secure.img-cdn.mediaplex.com/0/11449/universal.html?page_name=membership_confirmation&Membership_1=${purchasePrice}&Membership_2=1&mpuid=${userId}" HEIGHT=1 WIDTH=1 FRAMEBORDER=0></iframe>
<div id="formwrapper">

<b>Thank you for becoming a SearchSystems.Net member!</b><p/>

Your account has been registered successfully.  Your membership includes 
access to our Public Records Directory as well as discounts on Criminal Records, 
Bankruptcies, Judgments, and Tax Liens searches.<p/>
 
When logging into our system, please make sure you enter your username and 
password exactly as you did in the signup form. The password is case-sensitive, 
which means entering a password of ABCDXYZ is not the same as abcdxyz.<p/>

<b>Username:</b> ${username}<p/>

If you would like access to our Public Records Directory <a href="https://publicrecords.searchsystems.net/login-new.php">log in here</a>.<p/>

To perform a Premium search for criminal records or bankruptcies, judgments &
tax liens, <a href="https://premium.searchsystems.net/">click here to log in</a>.


</div>

<neon:funnelGoogleConvSignup/>

</jsp:body>

</neon:funnelPage>
