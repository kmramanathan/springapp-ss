<%@taglib tagdir="/WEB-INF/tags" prefix="donation"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<donation:DonationFunnel title="Search Systems Donation">
	<jsp:attribute name="stylesheet">common.css</jsp:attribute>
	<jsp:attribute name="javascript">common.js</jsp:attribute>
	<jsp:body>
	
<style type="text/css">
<!--
.style19 {
	font-family: Arial, Helvetica, sans-serif
}

.style20 {
	font-size: small
}

.style77 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.style65 {
	font-size: 12px;
	color: #FF0000;
	font-family: Arial, Helvetica, sans-serif;
}

.style67 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #000000;
	font-size: 12px;
}

.style85 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 10px;
}

.style86 {
	color: #FF0000;
	font-weight: bold;
}

.style88 {
	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
}

.style90 {
	font-size: 0.9em
}

.style95 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 0.9em;
}

.style96 {
	font-size: 1em
}
.error {
    	color: red;
    	font-size:12px;
    }
-->
</style>

<script type="text/JavaScript">

function showhide(){
	var val=document.getElementById("location").value;
	
	if(val=="No"){
		document.donateform.i18n.value = true;
		
		document.donateform.state.options[0].selected = true;
		document.donateform.postalCode.value = "";
		document.getElementById("staterow").style.display="none";
		document.getElementById("ziprow").style.display="none";
		document.getElementById("countryrow").style.display="table-row";
	}
	if(val=="Yes"){
		document.donateform.countryCode.value = "US";
		document.donateform.i18n.value = false;
		
		document.getElementById("staterow").style.display="table-row";
		document.getElementById("ziprow").style.display="table-row";
		document.getElementById("countryrow").style.display="none";
	}
	
}

</script>
    <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> 
        <td id="colone" align="left"><table width="883" align="center">
          <tr valign="top">
            <td width="875"><table width="848">
              <tr>
                <td width="576"><h3>From Tim Koster, Search Systems Founder</h3></td>
                <td width="260"><div align="center">
                  <h3>&nbsp;</h3>
                </div></td>
              </tr>

            </table>
              <p class="style96">We need your help.</p>
              <p class="style96">As we mentioned on the  previous page, we're the pioneers of public records on the Internet. Though we  generate revenue from premium pay searches and advertising, we're still a lean  family-owned organization and the focus of our site remains the free public  record directory.</p>
              <p class="style96"> Help us to keep it free and  accessible to everyone. Consider a one-time or recurring donation to Search  Systems by using the form below. Or, if you'd rather contribute by sponsoring  an ad on our website,<u><a href="http://publicrecords.searchsystems.net/sponsor-ads.php">click here</a></u>.</p>
              <p class="style96"> If you're on a tight budget,  mention our site to your friends. Or you could write about us in your blog and  link to your favorite page. You could also mention us on Twitter or &quot;like&quot;  us on Facebook. It all helps.</p>
              <p class="style96">Thank you for your consideration.</p>
             
              
              <p class="style96">Tim Koster<br />
              Founder, Search Systems </p>
  <form:form method="POST" name="donateform">
                <form:hidden path="i18n" />
                
              <table width="80%" border="0">
                <tr>
                
              
                  <td width="191" class="style77" valign="top"><span class="style86">*</span> Donation Amount:</td>
                  <td width="300" height="60" valign="top">
                  <spring:bind path="command.donationamount">
                  <form:select path="donationamount" multiple="single">
					<form:option value="0" label="Select Amount" />
					<form:options items="${amountList}"/>
				</form:select>
				</spring:bind>
				<br /><br />
				<form:errors path="donationamount"  cssClass="error" />
				</td>
                  <td width="82" class="style77" valign="top">Frequency:</td>
                  <td width="269" valign="top">
                  
                  <form:select path="frequency">
                  <form:options items="${frequencyList}"/>
                  
                  </form:select>
					
                  </td>
                  
                </tr>
              </table>
              <p style="margin: 0;padding: 0">You may make a one-time donation to SearchSystems.net or a  convenient automatic monthly deduction that will end in 12 months (or sooner if  we determine donations are no longer necessary). </p>
              <p>Please make sure that your name and address are the same as on the  mailing address on your credit card statement. The SearchSystems.net  website protects your billing information and personal information  through secure communications provided by Comodo SSL Certified  encryption. </p>
              <table width="837" border="0">
                <tr valign="top">
                  <td width="829" >
                  <p><span class="style65"><strong>*</strong> Required Fields</span></p>
                    <table width="804" border="0">
                      <tr>
                        <td height="34">
                        	<span class="style77">
                          		<label><span class="style86">*</span>Card Holder Name:<br /></label>
                          	</span> 
                          	<span class="style85">(As shown on your credit card) </span>
                        </td>
                        
                        <td><form:input path="name" size="30"/></td>
                        
                        <td>
                        	<div align="center" class="style85">
                            	<div align="left"><form:errors path="name"  cssClass="error" /></div>
                        	</div>
                       	</td>
                     </tr>
                     
					 <tr>
                          <td width="204" height="34">
                          	<span class="style77">
                            	<label>Company Name:<br /></label>
                          	</span>
                          </td>
                          <td width="339"><form:input path="company" size="30"/></td>
                          <td width="245"><div align="center" class="style85">
                              <div align="left"><form:errors path="company"  cssClass="error" /></div>
                          </div></td>
                     </tr>
                     
                     <tr>
                          <td height="34">
                          	<span class="style77">
                            	<label><span class="style86">*</span>Credit Card Number:<br /></label>
                          	</span>
                            <span class="style85">(No spaces or dashes) </span></td>
                          <td><input type="text" name="ccNumber" size="30" /></td>
                          <td><form:errors path="ccNumber"  cssClass="error" /></td>
                    </tr>
                    
                    <tr>
                          <td height="56"><span class="style77">
                            <label><span class="style86">*</span>Authorization Code:</label>
                          </span> 
                          </td>
                          <td>
                          	<table width="330">
                              <tr>
                                <td width="64" height="38"><input type="text" name="ccAuthCode" size="10"/></td>
                                <td width="238"><span class="style85">Visa,   MC - 3 digits   on the back 
                            of    card. Am Ex - 4 digits on the front of card.</span></td>
                              </tr>
                            </table>                            
                          </td>
                          <td>
                          	<div align="left" class="style85">
                              <div align="left"><form:errors path="ccAuthCode"  cssClass="error" /></div>
                          	</div>
                          </td>
                   </tr>
                   
                   <tr>
                          <td height="33"><span class="style77">
                            <label><span class="style86">*</span>Expiration Date:</label></span>
                          </td>
                          <td>
                          
                          <form:select path="ccExpMonth" multiple="single">
						  	<form:option value="-" label="Month" />
								<form:options items="${cardExpirationMonths}" />
						  </form:select>
						  
						  
                          <form:select path="ccExpYear" multiple="single" >
							<form:option value="-" label="Year" />
								<form:options items="${cardExpirationYears}" />
						  </form:select>
						  
						  </td>
                          <td>
                          	<form:errors path="ccExpMonth"  cssClass="error" />
                          	<form:errors path="ccExpYear"  cssClass="error" />
                          </td>
                        </tr>
                        
                        <tr>
                          <td height="35"><span class="style77">
                            <label><span class="style86">*</span>Inside the U.S.?:</label>
                          </span> </td>
                          <td><label>
                          
                            <select id="location" onchange="showhide()" >
								<option value="Yes" selected="selected">Yes</option>
								<option value="No">No</option>
							</select>
							
                          </label></td>
                          <td>&nbsp;</td>
                        </tr>
                   		
                   		<tr>
                          <td height="34"><span class="style77"><span class="style86">*</span><span class="style95">Billing Address:</span></span></td>
                          <td><form:input path="address" size="40"/></td>
                          <td><form:errors path="address"  cssClass="error" /></td>
                        </tr>
                   		
                   		<tr>
                          <td height="31"><span class="style77">
                            <label><span class="style86">*</span>Billing City:</label>
                          </span></td>
                          <td><form:input path="city" size="40"/></td>
                          <td><form:errors path="city"  cssClass="error" /></td>
                        </tr>
                    
                    	<tr id="staterow">
                          <td height="32">
                          	<span class="style77" style="display:block;" >
                            	<label><span class="style86">*</span>Billing State:</label>
                          	</span>
                          </td>
                          
                          <td>
                          	<div align="left">
                          	
                              <form:select path="state" multiple="single">
								<form:option value="" label="Select" />
									<form:options items="${usStates}"/>
				     		  </form:select>
				     		  
                          	</div>
                          </td>
                          
                          <td>
                          	<div>
                          		<form:errors path="state"  cssClass="error" />
                          	</div>
                          </td>
                          
                        </tr>
                        
                        <tr id="ziprow">
                          <td height="32">
                          	<span class="style77">
                            	<label><span class="style86">*</span>Zip Code:</label>
                          	</span>	
                          </td>
                          
                          <td>
                          		<div>
                          			<input type="text" name="postalCode" size="12" />
                          		</div>
                          </td>
                          
                          <td>
                          	<div><form:errors path="postalCode"  cssClass="error" /></div>
                          </td>
                          
                        </tr>
                        
                        <tr id="countryrow" style="display:none;">
                        	<td>
                        		<span class="style77">
                            		<label><span class="style86">*</span>Country Code:</label>
                        		</span>
                        	</td>
                        	
                        	<td>
                        		<div>
                          				<form:input path="countryCode" size="2" maxlength="2" />
                              			<span class="style85">(2 letters)</span>
                            	</div>
                            </td>
                            
                        	<td>
                        		<div><form:errors path="countryCode"  cssClass="error" /></div>
                        	</td>
                        </tr>
                        
                        <tr>
                        <td height="32"><span class="style77">
                            <label> Email Address (optional):</label>
                          </span></td>
                        <td><form:input path="email" size="40"/></td>
                        <td><form:errors path="email"  cssClass="error" /></td>
                        </tr>
                        
                        
                        
                      </table>
                    
                    <table width="752">
                        <tr>
                          <td width="768"><table width="666" height="114">
                              <tr>
                                <td width="132" height="108">&nbsp;</td>
                                <td width="522"><p><span class="style77">
                                    <input id="receiveNewsletter" name="receiveNewsletter" value="true" checked="checked" type="checkbox" />
                                    <input name="_receiveNewsletter" value="on" type="hidden" />
                                    <strong>Yes!</strong> I would like to receive the Search Systems Newsletter!</span><span class="style77"><label></label>
                                    </span>
                                    </p>
                                  <label></label>
                                  <p align="left">
                                      <input type="submit" name="Continue2" id="Continue2" value="Review Donation" />
                                    
                                  </p></td>
                              </tr>
                            </table>
                           
                            </form:form>
                            <table width="735">
                              <tr>
                                <td width="434"><span class="style85">If you encounter any problems, use our <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">contact form</a> or call us at (800) 350-2232 (outside the US, call +1-805-375-4041). Customer service is available   Monday to Friday, from 8:00 AM to 5:00 PM PST (UTC-8) or PDT (UTC-7). </span></td>
                                <td width="129"><img src="/springapp/images/common/Comodo-seal-85.gif" alt="comodo secure" width="85" height="51" /></td>
                                <td width="156"><a href="https://www.bbb.org/online/consumer/cks.aspx?ID=10404141025041762"><img src="/springapp/images/common/bbb-clickratingsm.gif" alt="bbb" width="135" height="52" /></a></td>
                              </tr>
                            </table>                            </td>
                        </tr>
                    </table></td>
                </tr>
              </table>
          
                       </td>
            </tr>
        </table>
        </td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
    
   	  </tr></table>
   	  </jsp:body>
</donation:DonationFunnel>