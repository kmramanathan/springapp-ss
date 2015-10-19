<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="springapp.web.funnel.NewSearchForm.SearchFormCommand"%>
<%@page import="springapp.web.funnel.NewSearchForm"%>
<neon:NewFunnel title="New Login">
<jsp:attribute name="stylesheet">new-funnel.css</jsp:attribute>
<jsp:body>

<script type="text/javascript">
	function bgcredirCtrl(){
		if(document.loginform.bgc[1].checked){
			if(document.loginform.username.value == "" || document.loginform.password.value == ""){
				document.getElementById("errorDiv").style.display = "block";
			}else{
				document.loginform.submit();
			}
		}else{
			window.location.href = "register.do";
		}
		
	}
	function evictionredirCtrl(){
		if(document.loginform.eviction[1].checked){
			if(document.loginform.username.value == "" || document.loginform.password.value == ""){
				document.getElementById("errorDiv").style.display = "block";
			}else{
				document.loginform.submit();
			}
		}else{
			window.location.href = "register.do";
		}
		
	}
	function evicBusinessredirCtrl(){
		if(document.loginform.evicBusiness[1].checked){
			if(document.loginform.username.value == "" || document.loginform.password.value == ""){
				document.getElementById("errorDiv").style.display = "block";
			}else{
				document.loginform.submit();
			}
		}else{
			window.location.href = "register.do";
		}
		
	}

	function newbgcredirCtrl(){
		if(document.loginform.newbgc[1].checked){
			if(document.loginform.username.value == "" || document.loginform.password.value == ""){
				document.getElementById("errorDiv").style.display = "block";
			}else{
				document.loginform.submit();
			}
		}else{
			window.location.href = "BusinessRegister.do";
		}
		
	}
	function bjlredirCtrl(){
		if(document.loginform.bjl[1].checked){
			if(document.loginform.username.value == "" || document.loginform.password.value == ""){
				document.getElementById("errorDiv").style.display = "block";
			}else{
				document.loginform.submit();
			}
		}else{
			window.location.href = "register.do";
		}
		
	}
	function realPropredirCtrl(){
		if(document.loginform.realprop[1].checked){
			if(document.loginform.username.value == "" || document.loginform.password.value == ""){
				document.getElementById("errorDiv").style.display = "block";
			}else{
				document.loginform.submit();
			}
		}else{
			window.location.href = "register.do";
		}
		
	}
	function realPropAddressredirCtrl(){
		if(document.loginform.realpropAddress[1].checked){
			if(document.loginform.username.value == "" || document.loginform.password.value == ""){
				document.getElementById("errorDiv").style.display = "block";
			}else{
				document.loginform.submit();
			}
		}else{
			window.location.href = "register.do";
		}
		
	}
</script>
<style type="text/css">
<!--
.style71l {font-family: Arial, Helvetica, sans-serif}
.style73l {
	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight: bold;
	
	
}
.style739{padding-top:7px;
	margin:0;}
.style74l {font-family: Arial, Helvetica, sans-serif; font-size: small; }
.style75l {
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 12px;
}
.style76l {
	font-family: Arial, Helvetica, sans-serif;
	color: #FF0000;
	font-size: 12px;
}
.style77l {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
.style79l {
	font-size: 12px;
	color: #000000;
}
.style80l {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #009933;
	font-weight: bold;
}
.style59l {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
	 font-weight:bold;
}
.errorMsg{color:#ff0000;}
.rpadd
{
	padding:0;margin:0;font-size: 12px;
}
-->
</style>

<form:form method="post" name="loginform">
<input type="hidden" name="orderSummary" value="${orderSummary}"/>
         <tr>
            <td width="649">
            <table width="556">
              <tr>
                <td width="548" height="91"><h2 align="left" class="style59l"> Login</h2>
                  <p class="style73l">Log in with your Search Systems Username and Password.</p>
                  <c:set var="obj" value="${sessionScope.searchtypes}"></c:set>
                  <table width="491" border="0" cellpadding="0" cellspacing="0" >
                    <tr id="logintablerow">
                        <td width="91"><span class="style77l"><strong>Username:</strong></span></td>
              <td width="192" align="left">
                          <input type="text" name="username" id="textfield3" />
                        </td>
                        <td width="192"><a href="https://members.searchsystems.net/retrieve_username_step1.php" class="style74l">Username Help</a></td>
                    </tr>
                      <tr id="logintablerow1">
                        <td width="91"><span class="style77l"><strong> Password:</strong></span></td>
                        <td width="192"><input type="password" name="password" id="textfield4" /></td>
                        <td><a href="https://members.searchsystems.net/reset_password_step1.php" class="style74l">Password Help</a></td>
                    </tr>
                    <tr><td colspan="2" height="15"></td></tr>
                    <tr>
                        <td width="349" colspan="2" align="center">
                        	<div align="center" style="padding-left:40px;">
                        		<input type="image" src="/springapp/images/newfunnel/bt-continue-small.png" alt="continue" width="94" height="38" border="0">
                        	</div>
                       </td>
                    </tr>
                  <tr>  
                  	<td colspan="2" align="center">
                  		<div align="center" id="errorDiv" style="display: none;" class="errorMsg">username or password can not be empty</div>
                  	</td>
                  </tr>
                  <tr>      
                  	<td colspan="2" align="center"><font class="errorMsg">${errorMsg}</font>
                  	</td>
                  </tr>
                 
                   <tr>
                  	<td colspan="2">
                  	<p class="rpadd"><strong>Don't have Search Systems account?</strong></p>
                  	<p class="rpadd">No Problem. <a href="register.do">Click here</a> to sign up for free.</p>
                  	<p class="rpadd">Then you can proceed with your search.</p>
                  	</td>
                  </tr>
                    </table>
                  
                </td>
              </tr>
            </table></td>
            <td width="231" valign="top"><p class="style75l">Order Summary</p>
           
            <p class="style77l">${orderSummary}</p>
        	<p class="style71l">&nbsp;</p>
            <p align="center" class="style71l"><a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></p>
            
            <p align="center" class="style71l"><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
				<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script> &nbsp;&nbsp;&nbsp;
           		</p>            </td>
          </tr>
       
</form:form>

</jsp:body>
</neon:NewFunnel>