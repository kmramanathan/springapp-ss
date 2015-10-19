<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:NewFunnel title="Purchase">
<jsp:attribute name="stylesheet">new-funnel.css</jsp:attribute>
<jsp:body>

<style type="text/css">
.errorMsg{ color: #FF0000;
font-size:12px;
}
.style59P {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight:bold;
	padding-left:19px;
}
.style61P {font-family: Arial, Helvetica, sans-serif; font-size: 14px; padding:0; margin:0; }
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
.purchaseform{  margin-left:10px; }
.purchaseform td{ padding:0 0 10px 0; margin:0;}
.formfield{ height:23px; background:#ffffff;}
</style>

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


<form:form method="post" name="purchaseForm">
<form:hidden path="i18n"/>
<tr>
		<td width="893" height="357" style="height:100%;  padding: 10px 0;">
		
			<form:errors path="*">
			<b>Please correct the following errors:</b><br/>
			</form:errors>
			<form:errors path="*" element="div" id="error" cssClass="errorMsg"/>
		
		<table width="890" >
          <tr>
            <td width="882">
            
            
            
            <table width="768"  >
              <tr>
                <td width="760" height="675" ><h2 align="left" class="style59P">Billing Information</h2>
                <p align="left" class="style67P">Please make sure that your name and address are the same as on the  mailing address on your credit card statement. The SearchSystems.net  website protects your billing information and personal information  through secure communications provided by Comodo SSL Certified  encryption. </p>
                 
                  <p><span class="style65P"><strong>*</strong> Required Fields</span></p>
                 
                  <table width="750" border="0" cellpadding="0" cellspacing="0" class="purchaseform">
                      <tr>
                        <td width="150" height="34" align="left"><span class="style61P">
                          <span class="style86P">*</span>Card Holder Name:
                          </span>
                            <label></label>
                            </td>
                        <td width="351"><form:input path="name" cssClass="formfield" size="30" /></td>
                        <td width="248"><div align="center" class="style85P">
                            <div align="left"></div>
                        </div></td>
                      </tr>
                      <tr>
                        <td height="15">&nbsp;</td>
                        <td><span class="style85P">(As shown on your credit card) </span></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="34"><span class="style61P">
                   <span class="style86P">*</span>Credit Card Number:
                        </span></td>
                        <td><form:input path="ccNumber" cssClass="formfield" size="30" /></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><span class="style85P">Numbers only. No spaces or dashes. </span></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td><span class="style61P">
                   <span class="style86P">*</span>Authorization Code:
                        </span> </td>
                        <td><form:input path="ccAuthCode" cssClass="formfield" size="10" /></td>
                        <td><div align="left" class="style85P">
                            <div align="left"></div>
                        </div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>
                        <span class="style85P">The Authorization Code for Visa and  MasterCard is the 3 digit number printed on the back 
                          of your credit  card. On American Express cards it is the four numbers printed on the  front.</span></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="33"><span class="style61P">
                         <span class="style86P">*</span>Expiration Date:
                        </span></td>
                        <td><form:select path="ccExpMonth" cssClass="formfield">
						<form:option value="-">Month</form:option>
						<form:options items="${cardExpirationMonths}"/>
                      </form:select>
                        &nbsp; 
                      <form:select path="ccExpYear" cssClass="formfield">
						<form:option value="-">Year</form:option>
						<form:options items="${cardExpirationYears}"/>
                      </form:select>
                      
                      </td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="35"><span class="style61P">
                          Company Name:
                        </span></td>
                        <td><form:input path="company" cssClass="formfield" size="40" /></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="35"><span class="style61P">
                          <span class="style86P">*</span>Inside the U.S.?:
                        </span> </td>
                        <td><label>
                          <select size="1" style=" height:23px;" name="inside" onchange="valLocal(); return false;">
                            <option value="lc" selected="selected">Yes</option>
                            <option value="in">No</option>
                           </select>
                        </label></td>
                        <td><span class="style87P"></span></td>
                      </tr>
                      <tr>
                        <td height="34"><span class="style61P"><span class="style86P">*</span>Billing Address:</span></td>
                        <td><form:input path="address" cssClass="formfield" size="40" /></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="31"><span class="style61P">
                        <span class="style86P">*</span>Billing City:
                        </span></td>
                        <td><form:input path="city" cssClass="formfield" size="40" /></td>
                        <td>&nbsp;</td>
                      </tr>
              
              
              
              			
              			<tr>
              				<td colspan="2">
              				
              					<div id="local">
              						<table>
              							<tr >
	               			  				<td height="1" style="padding:0; margin:0;" width="150"><span class="style61P">	               			  				
	                          				<span class="style86P">*</span>Billing State:
	                        				</span></td>
	                        				<td  ><div align="left">
	                           				 <form:select path="state" cssClass="formfield">
												<form:option value="">Select</form:option>
												<form:options items="${usStates}"/>
											</form:select>
	                        					</div></td>
	                        			<td>&nbsp;</td>
	                      				</tr>
					                     <tr>
					                        <td height="0" style="padding:0; margin:0;"><span class="style61P">
					                         <span class="style86P">*</span>Zip Code:
					                        </span></td>
					                        <td><form:input path="postalCode" cssClass="formfield" size="12" /></td>
					                        <td>&nbsp;</td>
					                      </tr>               						
              						</table>              					
              					</div>
              					
              					<div id="inter" style="display: none;">              					
              						<table>
              							<tr>
						                      <td height="31" style="padding:0; margin:0;" width="150"><span class="style61P">
						                        <label><span class="style86P">*</span>Country Code:</label>
						                      </span></td>
						
						                      <td><form:input path="countryCode" cssClass="formfield" size="2" maxlength="2"/>
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
               	                     
                      <tr>
                        <td style="padding:0; margin:0;"><span class="style61P">
                         <span class="style86P">*</span>Phone:
                        </span></td>
                        <td> <form:input path="phone" cssClass="formfield" size="26" /></td>
                        <td><div align="left" class="style85P">
                            <div align="left"></div>
                        </div></td>
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
                             
                             <div>&nbsp;</div>
                              <p align="left"><input type="image" src="/springapp/images/newfunnel/bt-continue.png" alt="continue" width="145" height="48"></p></td>
                          </tr>
                        </table>
                       
                          <table width="725">
                            <tr>
                                <td width="413"><span class="style85P">If you encounter any problems, use our <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">contact form</a> or call us at (800) 350-2232 (outside the US, call +1-805-375-4041). Customer service is available   Monday to Friday, from 8:00 AM to 5:00 PM PST (UTC-8) or PDT (UTC-7). </span></td>
                              <td width="300"><div align="right">
                              
                              	 <a href="http://www.instantssl.com" id="comodoTL">SSL</a>
				<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script> &nbsp;&nbsp;&nbsp;
           		<a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a>
                        </div>
                              	
                              	</td>
                            </tr>
                          </table></td>
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