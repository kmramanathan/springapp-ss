<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRcommonPage title="purchase">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

<script type="text/javascript">
 function memberLog(){
    document.signupForm.action="FRSignup.do?memberLogin=true";
    document.signupForm.submit();
 }

</script>

<form:form method="post" action="FRSignup.do" name="signupForm">
<form:hidden path="test"/>
<input type="hidden" value="${command.planType}" name="planType"/>

 	<tr>
		<td width="893" style="height:100%; padding: 10px 0;"><table width="805">
          <tr>
            <td height="306"><h2 align="left" class="style59">Purchase a Flat Rate Plan</h2>
                     <p align="left" class="style67b">Choose one plan option below, then input your existing SearchSystems.net membership information, OR create an account that comes packaged with your Flat Rate plan (at no additional charge). Your account includes delay-free access to our public records directory and discounts on all of our  Premium database searches.</p>
              <p align="left" class="style59">&nbsp;</p>
              <h3 align="left" class="style59">Choose Your Plan (select one only)</h3> 
               <form:errors path="rateId" element="div" id="error" cssClass="error-box"/> 
              <table width="712" >
                <tr>
                
            <c:if test="${command.planType eq 'all' or command.planType eq 'findpeople'}">    
                 <td width="218">
                      <table width="210" align="left"  bordercolor="#000000" style="border-width:1px; border-style:solid; border-color:black;">
                      <tr><td colspan="3" align="left" class="style59">Find People Flat Rate</td>
                      </tr>
                       <tr>
                          <td width="35"><label>
                            <input type="radio" name="rateId" id="50" value="50"/>
                          </label></td>
                          <td width="97"><span class="style61">One Day</span></td>
                          <td><div align="right" class="style61"><strong>$9.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="51" value="51"></td>
                          <td><span class="style61">One Week</span></td>
                          <td><div align="right" class="style61"><strong>$14.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="52" value="52"></td>
                          <td><span class="style61">One Month</span></td>
                          <td><div align="right" class="style61"><strong>$19.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="53" value="53"></td>
                          <td><span class="style61">One Year</span></td>
                          <td><div align="right" class="style61"><strong>$149.00</strong></div></td>
                        </tr>
                      </table>                  </td> 
                  <td width="12">&nbsp;</td>
             </c:if>
             
             <c:if test="${command.planType eq 'all' or command.planType eq 'criminal'}">    
                  <td width="228" >
                  <table width="210" align="left"  bordercolor="#000000" style="border-width:1px; border-style:solid; border-color:black;">
                  		<tr><td colspan="3" align="left" class="style59">Criminal Records Flat Rate</td>
                      </tr>
                       <tr>
                          <td width="35"><label>
                            <input type="radio" name="rateId" id="54" value="54">
                          </label></td>
                          <td width="97"><span class="style61">One Day</span></td>
                          <td><div align="right" class="style61"><strong> $19.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="55" value="55"></td>
                          <td><span class="style61">One Week</span></td>
                          <td><div align="right" class="style61"><strong>$49.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="56" value="56"></td>
                          <td><span class="style61">One Month</span></td>
                          <td><div align="right" class="style61"><strong>$69.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="57" value="57"></td>
                          <td><span class="style61">One Year</span></td>
                          <td><div align="right" class="style61"><strong>$499.00</strong></div></td>
                        </tr>     
                      </table>                  </td>
                  <td width="13">&nbsp;</td>
              </c:if>
              
              <c:if test="${command.planType eq 'all' or command.planType eq 'combined'}">
                  <td width="217" >
                  <table width="210" align="left"  bordercolor="#000000" style="border-width:1px; border-style:solid; border-color:black;">
                  	<tr><td colspan="3" align="left" class="style59">Combined Flat Rate</td>
                      </tr>
                    <tr>
                          <td><label>
                            <input type="radio" name="rateId" id="58" value="58">
                          </label></td>
                          <td width="97"><span class="style61">One Day</span></td>
                          <td width="62"><div align="right" class="style61"><strong> $24.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="59" value="59"></td>
                          <td><span class="style61">One Week</span></td>
                          <td><div align="right" class="style61"><strong>$59.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="60" value="60"></td>
                          <td><span class="style61">One Month</span></td>
                          <td><div align="right" class="style61"><strong>$79.95</strong></div></td>
                        </tr>
                        <tr>
                          <td><input type="radio" name="rateId" id="61" value="61"></td>
                          <td><span class="style61">One Year</span></td>
                          <td><div align="right" class="style61"><strong>$549.00</strong></div></td>
                        </tr>
                                          
                      </table>                  
                      </td>
                </c:if>      
                 
                </tr>
            </table>              </td>
          </tr>
        </table>
          <p>&nbsp;</p>
          
        
          <table width="808">
          <tr>
            <td width="800" height="1102"><h4 align="left" class="style60a">IMPORTANT - If you already have an active SearchSystems.net account, input your Username and Password below. If you are new to this service, skip to the Create an Account section.</h4>
              <table width="621" bgcolor="#FFFF99">
                <tr>
                  <td width="613"><h3 align="left" class="style60a">SearchSystems.net Members</h3>
           	   	
                <table width="609" border="0">
                      <tr>
                        <td width="443"><table width="391" border="0" bordercolor="#FFFFFF">
                          <tbody>
                            <tr>
                              <td width="111"><div align="right" class="style61">Username:</div></td>
                              <td width="270"><form:input path="ssUsername" size="40"/></td>
                            </tr>
                          </tbody>
                        </table>
                          <table width="391" border="0">
                            <tbody>
                              <tr>
                                <td width="112"><div align="right" class="style61">Password:</div></td>
                                <td width="269"><form:password path="ssPassword" size="40"/></td>
                              </tr>
                            </tbody>
                          </table></td>
                        <td width="154"><img src="/springapp/images/flatrate/bt-login-small.png" alt="log in" width="94" height="38" border="0" onclick="memberLog(); return false;" style="cursor: pointer"></td>
                      </tr>
                    </table>
                    <form:errors path="ssUsername" element="div" id="error" cssClass="error-box"/>           
                    <p align="left" class="style60a">&nbsp;</p>
                    </td>
                </tr>
              </table>
              <p align="left" class="style60a">&nbsp;</p>
              <table width="597">
                <tr>
                  <td width="589"><h3 align="center" class="style59">- OR -</h3></td>
                </tr>
              </table>
              <p align="left" class="style60a">&nbsp;</p>
              <table width="620" bgcolor="#FFFF99">
                <tr>
                  <td width="612"><h3 align="left" class="style60a">Don't have a SearchSystems.net Membership? </h3>
                  
                   <form:errors path="*">
							<b>Please correct the following errors:</b><br/>
					</form:errors>
					<form:errors path="*" element="div" id="error" cssClass="error-box"/>
					
                    <h3 align="left" class="style60a">Create an  Account</h3>
                 <table width="527" border="0">
                        <tbody>
                          <tr>
                            <td width="394"><div align="left" class="style61">Enter a username and password for your account.</div></td>
                            <td width="201"><div align="right" class="style65a">* Required Fields</div></td>
                          </tr>
                        </tbody>
                      </table>
                      <p>&nbsp;</p>
                      
              <table width="468" border="0">
                        <tbody>
                          <tr>
                            <td width="136"><div align="right" class="style61"><span class="style66">*</span>Username:</div></td>
                            <td width="320"><form:input path="username" cssClass="formfield" size="40" /></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="398" border="0" align="center">
                        <tbody>
                          <tr>
                            <td width="392"><span class="style69a">Your  username can be from 6-20 characters. It must start with a letter, and  may contain only letters (A-Z, a-z) and numbers (0-9). Your username  may contain capital letters but it is not case sensitive. </span></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="468" border="0">
                        <tbody>
                          <tr>
                            <td width="138"><div align="right" class="style61"><span class="style66">*</span>Password:</div></td>
                            <td width="318"><form:password path="password" cssClass="formfield" size="40" /></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="466" border="0">
                        <tbody>
                          <tr>
                            <td width="139"><div align="right" class="style61"><span class="style66">*</span>Confirm Password:</div></td>
                            <td width="315"><form:password path="confirmPassword" cssClass="formfield" size="40" /></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="396" border="0" align="center">
                        <tbody>
                          <tr>
                            <td><div align="left" class="style61"><span class="style70b">Your  password can be from 6-20 characters. It may contain only letters (A-Z,  a-z) and numbers (0-9). Your password IS case sensitive, so be sure to  use the correct case where necessary. Your password must have at least  1 lowercase letter, at least 1 uppercase letter, and at least 1 number</span>. </div></td>
                          </tr>
                        </tbody>
                      </table>
                      <p>&nbsp;</p>
                      <h3 align="left" class="style60a">Personal Information</h3>
                      <p align="left" class="style61"> Please enter your personal information including your email address below. We  never share your email address. See our <a target="_blank" href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=privacy">Privacy Policy</a>.  If you have a spam filter, please make sure that you can accept emails from searchsystems.net. </p>
                      <p align="left" class="style61">&nbsp;</p>
                      <table width="468" border="0">
                        <tbody>
                          <tr>
                            <td width="138"><div align="right" class="style61"><span class="style66">*</span>First Name:</div></td>
                            <td width="318"><form:input path="firstName" cssClass="formfield" size="40" /></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="468" border="0">
                        <tbody>
                          <tr>
                            <td width="138"><div align="right" class="style61"><span class="style66">*</span>Last Name:</div></td>
                            <td width="318"><form:input path="lastName" cssClass="formfield" size="40" /></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="468" border="0">
                        <tbody>
                          <tr>
                            <td width="138"><div align="right" class="style61"><span class="style66">*</span>E-mail:</div></td>
                            <td width="318"><form:input path="email" cssClass="formfield" size="40" /></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="468" border="0">
                        <tbody>
                          <tr>
                            <td width="138"><div align="right" class="style61"><span class="style66">*</span>Confirm E-mail:</div></td>
                            <td width="318"><form:input path="confirmEmail" cssClass="formfield" size="40" /></td>
                          </tr>
                        </tbody>
                      </table>
                      <p>&nbsp;</p>
                      <h3 align="left" class="style60a">Secret Question</h3>
                      <p align="left" class="style61"> Select a secret question from our list below. Make sure to choose a question  that only you know the answer to. </p>
                      <p align="left" class="style61">&nbsp;</p>
                      <table width="468" border="0">
                        <tbody>
                          <tr>
                            <td width="164"><div align="right" class="style61"><span class="style66">*</span>Secret Question:</div></td>
                            <td width="292"><form:select path="secretQuestionId" cssClass="formfield">
							<form:options items="${secretQuestions}"/>
                      </form:select></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="468" border="0">
                        <tbody>
                          <tr>
                            <td width="161"><div align="right" class="style61"><span class="style66">*</span>Secret Question Answer:</div></td>
                            <td width="295"><form:input path="secretQuestionAnswer" cssClass="formfield" size="40" /></td>
                          </tr>
                        </tbody>
                      </table>
                      <table width="392" border="0" align="center">
                        <tbody>
                          <tr>
                            <td width="391"><div align="left" class="style69a">Your answer may contain only letters (A-Z, a-z), numbers (0-9) and spaces. Your  answer IS case sensitive. </div></td>
                          </tr>
                        </tbody>
                      </table>
                      <p>&nbsp;</p>
                    <table width="602" border="0">
                      <tr>
                          <td width="600" height="46"><div align="center"><input type="image" src="/springapp/images/flatrate/bt-continue.png" alt="continue" width="145" height="48" border="0"></div></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
              <p><label></label>
              </p>
            </td>
          </tr>
        </table>      </td>
	</tr>

</form:form>	

</jsp:body>
</neon:FRcommonPage>
