<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:NewFunnel title="Create Account">
<jsp:attribute name="stylesheet">new-funnel.css</jsp:attribute>
<jsp:body>
<script type="text/javascript">
	function valLocal(){
		var ch = document.purchaseForm.inside.value;
		if(ch == "lc"){
			document.purchaseForm.countryCode.value = "US";
			document.purchaseForm.i18n.value = false;
			document.getElementById('local').style.display = "block";
			document.getElementById('inter').style.display = "none";
		}else{
			document.purchaseForm.i18n.value = true;
			document.purchaseForm.state.options[0].selected = true;
			document.purchaseForm.postalCode.value = "";
			document.getElementById('inter').style.display = "block";
			document.getElementById('local').style.display = "none";
		}
	}

</script>
<style type="text/css">
.stylere1 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight:bold;
	margin:0;
	padding:0;
	padding-bottom:3px;
}
.stylere2 {color: #003366; font-family: Arial, Helvetica, sans-serif;padding:0;margin:0}
.stylere3 {	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #003366;
	padding: 0;
	margin: 0;
}
.stylere4 {font-family: Arial, Helvetica, sans-serif; font-size: 12px;padding: 5px 0 5px 0;margin: 0 }
.stylere5 {	font-size: 12px;
	color: #FF0000;
	font-family: Arial, Helvetica, sans-serif;
}
.stylere6 {color: #FF0000; padding:0;}
.stylere69 {font-family: Arial, Helvetica, sans-serif; font-size: 11px; }
.stylere70 {font-size: 11px}
.stylere85 {	font-family: Arial, Helvetica, sans-serif;
	font-size: 10px;
}
.stylere86 {	color: #FF0000;
	font-weight: bold;
}
.stylere87 {
	color: #009933;
	font-weight: bold;
}
.stylere67 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #000000;
	font-size: 12px;
}
.stylere88 {font-family: Arial, Helvetica, sans-serif}
.formfield{ background:#ffffff; height:22px; border:1px solid red;}
.userinfo td{ padding:0; margin:0; padding:0px 5px 0px 5px;}
.errorMsg{ font-size:12px; color:#ff0000;}
.errorMsg1{ font-size:12px; color:#003366;}
.style59P {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight:bold;
	padding-left:19px;
	padding: 0px 0 8px 0;
	margin: 0;
}
.style61P {font-family: Arial, Helvetica, sans-serif; font-size: 14px; padding:0; margin:0;text-indent: 0; }
.style65P {	font-size: 12px;
	color: #FF0000;
	font-family: Arial, Helvetica, sans-serif;
	margin:0;
	padding:0 0 0 10px;;
}
.style85P {	font-family: Arial, Helvetica, sans-serif;
	font-size: 10px;
	text-indent:0px;
	padding:0;
	margin:0;
}
.style86P {	color: #FF0000;
	font-weight: bold;
	padding: 0;
	margin: 0;
}
.style87P {
	color: #009933;
	font-weight: bold;
}
.style67P {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #000000;
	font-size: 12px;
	margin:0;
	padding:0 0 0 19px;
}
.purchaseform{  margin-left:0px;font-size: 14px;}
.purchaseform td{ padding:0 0 0px 0; margin:0;}
.formfield{ height:23px; background:#ffffff;}
.style676P{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #000000;
	font-size: 12px;
	margin:0;
	padding:0 0 4px 0;
}
.style71l
{
	padding:0;
	margin:0;
}
.billstate
{
	float: left;
	width: 155px;
	font-size: 14px;
	
}
.billstate2
{
	float: left;
	width: 319px;
	
	font-size: 14px;
}
.billstate3
{
	float: left;
	width: 155px;
	font-size: 14px;
	
}
.billstate4
{
	float: left;
	width: 150px;
	font-size: 14px;
	
}
</style>
                        <!--// content begins //-->

<!-- CONTENT GOES HERE -->

<form:form method="post" name="purchaseForm"  >
<form:hidden path="i18n"/>

<tr>
    <td width="100%" style="padding:0 0 0 15px;">
		
		  <form:errors path="*">
			<b>Please correct the following errors:</b><br/>
		 </form:errors>
		<!--   <form:errors path="ccNumber">
			<b>Card Errors:</b><br/>
		 </form:errors>
		 <form:errors path="ccNumber" element="div" id="errorcc" cssClass="errorMsg1"></form:errors>
		 -->		 
		 <form:errors path="*" element="div" id="error" cssClass="errorMsg"/>
		
		
            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                <tr>
                  <td colspan="2">
                  	
                    <p class="stylere2">Please complete this form in order to purchase your search results and register for FREE to SearchSystems.net.</p>
                    
                  </td>
                  <td rowspan="2">
                  	<p align="right" class="style71l"><a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></p>
                  </td>
                </tr>
                 <tr>
                          <td ><div align="left" class="stylere4">Enter a username and password for your account.</div></td>
                          <td width="260"><div align="left" class="stylere5">* Required Fields</div></td>
                         
                 </tr>
              </table>
            
                   <table width="99%" border="0" cellpadding="0" cellspacing="0" class="userinfo">
                      <tbody>
                      <tr><td colspan="3" height="15"></td></tr>
                        <tr>
                          <td width="128" height="62"><div align="right"  class="stylere4"><span class="stylere6">*</span>Username:</div></td>
                          <td width="280" align="left"><form:input path="username" size="40" cssClass="formfield"/></td>
                          <td width="382" ><span class="stylere69">Your  username can be from 6-20 characters. It must start with a letter, and  may contain only letters (A-Z, a-z) and numbers (0-9). Your username  may contain capital letters but it is not case sensitive. </span></td>
                        </tr>
                        <tr>
                        
                            <td ><div align="right" class="stylere4" height="0"><span class="stylere6">*</span>Password:</div></td>
                            <td ><form:password path="password" size="40" cssClass="formfield"  /></td>
                           <td rowspan="2"><span class="stylere4" height="0"><span class="stylere70">Your  password can be from 6-20 characters. It may contain only letters (A-Z,  a-z) and numbers (0-9). Your password IS case sensitive, so be sure to  use the correct case where necessary. Your password must have at least  1 lowercase letter, at least 1 uppercase letter, and at least 1 number</span>. </span></td>
                          </tr>
                           <tr>
                              <td  ><div align="right" class="stylere4"><span class="stylere6">*</span>Confirm Password:</div></td>
                              <td ><form:password path="confirmPassword" cssClass="formfield" size="40" /></td>
                            
                            </tr>
                      </tbody>
                    </table>
                    <div> &nbsp; </div>
                <h3 align="left" class="stylere3">Personal Information</h3>
                  <p align="left" class="stylere4"> Please enter your personal information including your email address below. We  never share your email address. See our <a target="_blank" href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=privacy">Privacy Policy</a>.  If you have a spam filter, please make sure that you can accept emails from searchsystems.net. </p>
                  
                  <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tbody>
                        <tr>
                          <td><div align="left" class="stylere4"><span class="stylere6">*</span>First Name:</div></td>
                          <td><form:input path="firstName" cssClass="formfield" size="40" /></td>
                          <td width="95"><div align="right" class="stylere4"><span class="stylere6">*</span>Last Name:</div></td>
                          <td><form:input path="lastName" cssClass="formfield" size="40" /></td>
                        </tr>
                         <tr>
                          <td width="72"><div align="right" class="stylere4"><span class="stylere6">*</span>E-mail:</div></td>
                          <td><form:input path="email" cssClass="formfield" size="40" /></td>
                          <td><div align="right" class="stylere4"><span class="stylere6">*</span>Confirm E-mail:</div></td>
                          <td><form:input path="confirmEmail" cssClass="formfield" size="40" /></td>
                        </tr>
                      </tbody>
                    </table>
               		<div style="clear: both"> &nbsp; </div>
                   
                      		<h3 align="left" class="stylere3">Secret Question</h3>
                  			<p align="left" class="stylere4"> Select a secret question from our list below. Make sure to choose a question  that only you know the answer to. </p>
                      
                 
                  <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tbody>
                      
                        <tr>
                          <td width="190"><div align="right" class="stylere4"><span class="stylere6">*</span>Secret Question:</div></td>
                          <td width="250"><form:select path="secretQuestionId"  cssClass="formfield">
						<form:option value="-">Please select</form:option>
						<form:options items="${secretQuestions}"/>
                      </form:select></td>
                       <td width="158"><div align="right" class="stylere4"><span class="stylere6">*</span>Answer:</div></td>
                          <td width="299"><form:input path="secretQuestionAnswer" cssClass="formfield" size="40" /></td>
                        </tr>
                          <tr>
                        	<td width="100%" colspan="4" style="padding-left: 120px;"><span class="stylere69">Your answer may contain only letters (A-Z, a-z), numbers (0-9) and spaces. Your  answer IS case sensitive. </span></td>
                        </tr>
                      </tbody>
                    </table>
                  
                  
             <table width="100%" border="0" cellpadding="0" cellspacing="0" style="clear: both">
              <tr>
                <td>
                	<h2 align="left" class="style59P">Billing Information</h2>
                	<p align="left" class="style676P">Please make sure that your name and address are the same as on the  mailing address on your credit card statement. The SearchSystems.net  website protects your billing information and personal information  through secure communications provided by Comodo SSL Certified  encryption. </p>
                 <table border="0" cellpadding="0" cellspacing="0" class="purchaseform">
                      <tr>
                        <td height="34" align="left" width="17%">
                          <span class="style61P">
            				<span class="style86P">*</span>Card Holder Name:
                          </span>
                            <label></label>
                            </td>
                        <td><form:input path="name" cssClass="formfield" size="30" /></td>
                        <td width="17%">
                        	<span class="style61P">
                   				<span class="style86P">*</span>Credit Card Number:
                        	</span>
                        </td>
                        <td><form:input path="ccNumber" cssClass="formfield" size="30" /></td>
                      </tr>
                      <tr>
                        <td height="15">&nbsp;</td>
                        <td><span class="style85P">(As shown on your credit card) </span></td>
                        <td>&nbsp;</td>
                        <td><span class="style85P">Numbers only. No spaces or dashes. </span></td>
                      </tr>
                      
                      <tr>
                        <td colspan="4">
                        	<table border="0" cellpadding="0" cellspacing="0">
                        		<tr>
                        			<td width="154">
                        			<span class="style61P">
                   					<span class="style86P">*</span>Authorization Code:
                        			</span> </td>
                        			<td width="120">
                       				<form:input path="ccAuthCode" cssClass="formfield" size="10" />
                       				</td>
                         			<td width="40%">
                        			<span class="style85P">The Authorization Code for Visa and  MasterCard is the 3 digit number printed on the back 
                          			of your credit  card. On American Express cards it is the four numbers printed on the  front.</span>
                        			</td>
                        			<td align="right"><img src="/springapp/images/common/comodo-secure.png" alt="Comodo" height="59" width="113" style="float: right;"></td>
                        		</tr>
                        		<tr><td colspan="4" height="6"></td></tr>
                        	</table>
                          </td>
                   		</tr>
                      <tr>
                        <td height="33"><span class="style61P">
                         	<span class="style86P">*</span>Expiration Date:
                       		</span>
                        </td>
                        <td>
                        	<form:select path="ccExpMonth" cssClass="formfield">
							<form:option value="-">Month</form:option>
							<form:options items="${cardExpirationMonths}"/>
                      		</form:select>
                        	&nbsp; 
                      		<form:select path="ccExpYear" cssClass="formfield">
							<form:option value="-">Year</form:option>
							<form:options items="${cardExpirationYears}"/>
                      		</form:select>
                      
                      </td>
                       <td height="35"><span class="style61P">
                          <span class="style86P">*</span>Inside the U.S.?:
                        	</span> 
                       </td>
                       <td><label>
                          <select size="1" style=" height:23px;" name="inside" onchange="valLocal(); return false;">
                            <option value="lc" selected="selected">Yes</option>
                            <option value="in">No</option>
                           </select>
                        </label>
                      </td>
                      </tr>
                    
                     
                      <tr>
                        <td height="34"><span class="style61P"><span class="style86P">*</span>Billing Address:</span></td>
                        <td><form:input path="address" cssClass="formfield" size="40" /></td>
                        <td height="31"><span class="style61P">
                        <span class="style86P">*</span>Billing City:
                        </span></td>
                        <td><form:input path="city" cssClass="formfield" size="35" /></td>
                      </tr>
                     
              
              
              		<tr>
              			<td colspan="4">              				
              					<div id="local">
              						<div class="billstate">
	               			  				              			  				
	                          				<span class="style86P">*</span>Billing State:
	                        				
	                        				</div>
	                        				<div align="left" class="billstate2">
	                           				 <form:select path="state" cssClass="formfield">
												<form:option value="">Select</form:option>
												<form:options items="${usStates}"/>
											</form:select>
	                        					</div>
	                        					<div class="billstate3">
					                        	
					                         	<span class="style86P">*</span>Zip Code:
					                        	
					                        </div>
					                        <div class="billstate4">
					                        	<form:input path="postalCode" cssClass="formfield" size="12" />
					                        </div>          					
              					</div>
              					
              					<div id="inter" style="display: none;">              					
              						<table cellpadding="0" cellspacing="0" border="0">
              							<tr>
						                      <td height="31" style="padding:0; margin:0;" width="150"><span class="style61P">
						                        <span class="style86P">*</span>Country Code:
						                      </span></td>
						
						                      <td>&nbsp;<form:input path="countryCode" cssClass="formfield" size="2" maxlength="2"/>
						                        <span class="style67P">(2 letters)</span></td>
						                      <td>&nbsp;</td>
						                  </tr>
						            						
              						</table>              					
              					</div>
              				</td>
              			</tr>
                            
                            <script type="text/javascript">
                            
	                           	if(document.purchaseForm.i18n.value == "false"){
		                           	document.purchaseForm.inside.options[0].selected = true;
		                           	document.purchaseForm.countryCode.disable = true;
	                    			document.purchaseForm.i18n.value = false;
	                    			document.getElementById('local').style.display = "block";
	                    			document.getElementById('inter').style.display = "none";
	                    		}else{
	                    			document.purchaseForm.inside.options[1].selected = true;
	                    			document.purchaseForm.i18n.value = true;
	                    			document.purchaseForm.state.disable = true;
	                    			document.purchaseForm.postalCode.disable = true;
	                    			document.getElementById('inter').style.display = "block";
	                    			document.getElementById('local').style.display = "none";
	                    		}
                            </script>
               	       <tr><td colspan="4" height="8"></td></tr>             
                      <tr>
                        <td><span class="style61P">
                         	<span class="style86P">*</span>Phone:
                        	</span>
                        </td>
                        <td> <form:input path="phone" cssClass="formfield" size="26" /></td>
                        <td height="35"><span class="style61P">
                          Company Name:
                        </span></td>
                        <td><form:input path="company" cssClass="formfield" size="35" /></td>
                      </tr>
                    </table>
                               
                    <table width="752">
                    <tr>
                        <td width="768"><table width="666" height="35">
                          <tr>
                            <td width="132">&nbsp;</td>
                            <td width="522"><p><span class="style61P">
                              <input id="receiveNewsletter" name="receiveNewsletter" value="true" checked="checked" type="checkbox">
                              <input name="_receiveNewsletter" value="on" type="hidden">
                              <strong>Yes!</strong> I would like to receive the Search Systems Newsletter!</span></p>
                               
                              <span class="style61P">
                             
                             &nbsp;<input id="acceptAgreement" name="acceptAgreement" value="true" type="checkbox">
                              <input name="_acceptAgreement" value="on" type="hidden">
                             <b style="color:red; font-size: 18px;">*</b> <span style="margin: 0;padding: 0; font-size: 18px;">I have read &amp; agree to the <a href="${showAgreementUrl}?version=funnel" target="_blank">User Agreement</a></span></span>
                             </td>
                             </tr>
                            </table>
            		<div>&nbsp;</div>
                  <table width="469">
                    <tr>
                      <td width="158">&nbsp;</td>
                      <td width="299"><input type="image" src="/springapp/images/newfunnel/bt-continue.png" alt="continue" width="145" height="48" border="0"></td>
                    </tr>
                  </table>
                  </td>
              </tr>
            </table>
            </td>
          </tr>
        </table>

	  </td>
  </tr>

</form:form>
        <!--// content ends //-->

</jsp:body>
</neon:NewFunnel>