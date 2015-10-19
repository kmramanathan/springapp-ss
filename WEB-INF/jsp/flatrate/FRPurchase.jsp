<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRcommonPage title="billing information">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

<form:form method="post">
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;"><table width="890">
          <tr>
            <td width="753" height="128"><table width="738">
              <tr>
                <td width="834" height="91"><h2 align="left" class="style59">Billing Information</h2>
                    <p align="left" class="style67">Please make sure that your name and address are the same as on the  mailing address on your credit card statement. The SearchSystems.net  website protects your billing information and personal information  through secure communications provided by Thawte SSL Certified  encryption. </p>
                  <p align="left" class="style59">&nbsp;</p></td>
              </tr>
            </table></td>
             <td width="125">
             
<c:if test="${!command.i18n}">
			<c:url var="purchaseUrlIntl" value="FRPurchase.do?i18n=1"/>
<strong><p class="style71">Outside the U.S.? <a href="https://www.searchsystems.net/springapp/funnel/Corporate.html" target="blank">Click here</a>.</p></strong>

</c:if>       
                         
             
             
             </td>
          </tr>
        </table>
		  <table width="808">
          <tr>
            <td width="800" height="675"><p><span class="style69"><strong>*</strong> Required Fields</span></p>
            
            <form:errors path="*">
				<b>Please correct the following errors:</b><br/>
			</form:errors>
					<form:errors path="*" element="div" id="error" cssClass="error-box"/>
					
              <p>&nbsp;</p>
              

              <table width="750">
                <tr>
                  <td width="135" height="34"><span class="style61">
                  <label><span class="style70">*</span>Card Holder Name:</label>
                  </span>
                    <label></label></td>
                  <td width="351"><form:input path="name" cssClass="formfield" size="30" /></td>
                  <td width="248"><div align="center" class="style66"> 
                    <div align="left"></div>
                  </div></td>
                </tr>
                <tr>
                  <td height="23">&nbsp;</td>
                  <td><span class="style66">(As shown on your credit card) </span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="34"><span class="style61">
                  <label><span class="style70">*</span>Credit Card Number:</label>
                  </span></td>
                  <td><form:input path="ccNumber" cssClass="formfield" size="30" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td><span class="style66">Numbers only. No spaces or dashes. </span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td><span class="style61">
                  <label><span class="style70">*</span>Authorization Code:</label>
                  </span> </td>
                  <td><form:input path="ccAuthCode" cssClass="formfield" size="10" /></td>
                  <td><div align="left" class="style66">
                    <div align="left"></div>
                  </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td><span class="style66">The Authorization Code for Visa and  MasterCard is the 3 digit number printed on the back 
                  of your credit  card. On American Express cards it is the four numbers printed on the  front.</span></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="33"><span class="style61">
                  <label><span class="style70">*</span>Expiration Date:</label>
                  </span></td>
                  <td><form:select path="ccExpMonth" cssClass="formfield">
						<form:option value="-">Month</form:option>
						<form:options items="${cardExpirationMonths}"/>
                      </form:select>
                        &nbsp; 
                      <form:select path="ccExpYear" cssClass="formfield">
						<form:option value="-">Year</form:option>
						<form:options items="${cardExpirationYears}"/>
                      </form:select></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="35"><span class="style61">
                  <label>Company Name:</label>
                  </span></td>
                  <td><form:input path="company" cssClass="formfield" size="40" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="34"><span class="style61"><span class="style70">*</span>Billing Address:</span></td>
                  <td><form:input path="address" cssClass="formfield" size="40" /></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="31"><span class="style61">
                  <label><span class="style70">*</span>Billing City:</label>
                  </span></td>
                  <td><form:input path="city" cssClass="formfield" size="40" /></td>
                  <td>&nbsp;</td>
                </tr>
                
         <c:choose>

		<c:when test="${command.i18n}">
			<tr><td height="32"><span class="style61">Province/State:</span></td><td><form:input path="state" cssClass="formfield" size="12" /> (optional)</td></tr>
			<tr><td height="32"><span class="style61">Postal Code:</span></td><td> <form:input path="postalCode" cssClass="formfield" size="12" /> (optional)</td></tr>
			<tr><td height="32"><span class="style61"><span class="req"><strong>*</strong></span>Country Code:</span></td><td> <form:input path="countryCode" cssClass="formfield" size="2" /> (2 letters)</td></tr>
		</c:when> 
		
		<c:otherwise>
			<form:hidden path="countryCode" />      
                <tr><td height="32"><span class="style61">
                  <label><span class="style70">*</span>Billing State:</label>
                  </span></td>
                  <td>
                    <div align="left">
                      <form:select path="state" cssClass="formfield">
							<form:option value="-">Select</form:option>
							<form:options items="${usStates}"/>
					</form:select>
                    <span class="style61">Outside US? <a href="https://www.searchsystems.net/springapp/funnel/Corporate.html" target="blank">Click here</a>.</span></div></td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td height="32"><span class="style61">
                  <label><span class="style70">*</span>Zip Code:</label>
                  </span></td>
                  <td><form:input path="postalCode" cssClass="formfield" size="12" /></td>
                  <td>&nbsp;</td>
                </tr>
         </c:otherwise>
         </c:choose>       
                
                <tr>
                  <td><span class="style61">
                  <label><span class="style70">*</span>Phone:</label>
                  </span></td>
                  <form:hidden path="phone"/>
                  <td>
                  (
                    <label>
                    <form:input path="phone1" cssClass="formfield" size="3" maxlength="3"/>
                    </label> 
                    ) 
                    <label>
                    <form:input path="phone2" cssClass="formfield" size="3" maxlength="3" /> 
                  - 
                   <form:input path="phone3" cssClass="formfield" size="4" maxlength="4" /> 
                  </label></td>
                  <td><div align="left" class="style66">
                    <div align="left"></div>
                  </div></td>
                </tr>
              </table>
              <table width="795">
                <tr>
                  <td>
                      <table width="666" height="35">
                        <tr>
                          <td width="132">&nbsp;</td>
                          <td width="522">
                            <span class="style61">
                              <input id="receiveNewsletter" name="receiveNewsletter" value="true" checked="checked" type="checkbox">
                              <input name="_receiveNewsletter" value="on" type="hidden">
                              <strong>Yes!</strong> I would like to receive the Search Systems Newsletter!</span><br>
                            <span class="style61">
                              <label></label>
                              <input id="acceptAgreement" name="acceptAgreement" value="true" type="checkbox">
                              <input name="_acceptAgreement" value="on" type="hidden">
                              I have read &amp; agree to the <a href="http://www.searchsystems.net/springapp/funnel/FR-Terms-Of-Use.html" target="_blank">User Agreement</a></span>
                                <label></label>
                                                    
                          <p>&nbsp;</p>
                          <p align="left"><input type="image" src="/springapp/images/flatrate/bt-continue.png" alt="continue" width="145" height="48"></p></td>
                        </tr>
                      </table>
                    
                    <p align="center">&nbsp;</p>
                  <table width="783">
                    <tr>
                      <td width="434"><span class="style66">If you encounter any problems, use our <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">contact form</a> or call us at (800) 350-2232 (outside the US, call +1-805-375-4041). Customer service is available   Monday to Friday, from 8:00 AM to 5:00 PM PST (UTC-8) or PDT (UTC-7). </span></td>
                      <td width="337"><div align="right"><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
					<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script>
                      <a href="${bbbUrl}" target="blank"><img src="/springapp/images/flatrate/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></div></td>
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
</jsp:body>
</neon:FRcommonPage>	
