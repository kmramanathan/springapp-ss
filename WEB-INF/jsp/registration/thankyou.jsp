<%@ include file="/WEB-INF/jsp/include.jsp"%>

<link rel="stylesheet" type="text/css" href="css/searchsystems.css" />
<link rel="stylesheet" type="text/css" href="css/registration.css" />

<html>
<head>
<title>Search Systems DirectPass Registration</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/jsp/registration/top.jsp"%>

<div style="margin-left: auto; margin-right: auto; width: 550px;">

    <h1>Thank You</h1>
    
    <table border="0" cellspacing="0">
        <tr>
            <td>
                You have been subscribed as a SearchSystems.net DirectPass Member. 
                Please make sure that you print this page for future reference.
                <br><br>
                When you log into SearchSystems.net, your username and password must 
                be entered in exactly as they were when you registered. For example: 
                a password of ABCDXYZ is not the same as abcdxyz.            
            </td>
            <td style="padding: 0 0 25px 10px;">
                <img src="img/directpass-small.jpg" style="float: right; padding-left: 15px; height: 107px; width: 187px;" alt="DirectPass" />
            </td>
        </tr>
    </table>

    Protect your personal information. Never share your username or password with anyone.
    <br><br>
    Username: <span style="font-weight: bold; font-size: 16px; letter-spacing: 1px;">
    <c:out value="${registration.user.username}" /></span>
    First Name: <span style="font-weight: bold; font-size: 16px; letter-spacing: 1px;">
    <c:out value="${registration.user.firstName}" /></span>
    <br><br>
    <a href="https://publicrecords.searchsystems.net/login-new.php" style="font-size: 1.2em;">Click Here to Login.</a>
    <br><br>
    If you have a problem with a password and need a new one, click on Password 
    Help under the login box and you will be able to reset your password.
    <br><br>
    We will NEVER email you to ask you for your password. If anyone contacts you 
    by phone, fax, or email to request your password, refuse the request.

    <div style="clear: both;"></div>
    <br/>
    
</div>

</body>
</html>