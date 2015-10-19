
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:SkipTracePage  title="Order Form">
<jsp:attribute name="stylesheet">skiptrace.css</jsp:attribute>

<jsp:body>

<form:form method="post">

<table width="887">
          <tr>
            <td height="101"><h1 class="style59">Skip Trace Order Form</h1>
              <p class="style73a">Please fill out the information below and click  the Submit Order button. A research  specialist will contact you shortly.</p>
            </td>
          </tr>
        </table>
		  <table width="808">
          <tr>
            <td width="800" height="675"><table width="857">
              <tr>
                <td width="849" height="136"><h2 align="left" class="style59">Subject Details</h2>
                
                <center>
					<div style="background-color:#FFFFCC;width:600px; font-size:14px" align="center" ><font color="red"><form:errors path="*" element="div" id="error" cssClass="error-box" /></font></div>
					</center>
					
					
                    <p align="left" class="style67">Please fill out the  required fields below (<span class="style95a">bold with yellow background</span>). </p>
                  <p align="left" class="style67">&nbsp;</p>
                  <p align="left" class="style67">If you have additional details, you can fill out the optional fields (in white). When we contact you, we can take down any additional information you may have.</p></td>
              </tr>
            </table>
              <table width="808">
                <tr>
                  <td width="800" height="675"><table width="889">

                    <tr>
                      <td width="23" bgcolor="#FFFF99"><span class="style61">
                        <label><span class="style95a">1.</span></label>
                      </span> <span class="style96"><strong>
                      <label></label>
                      </strong>
                      <label></label>
                      </span> <span class="style96">
                      <label></label>
                      </span> </td>
                      <td width="147" height="56" bgcolor="#FFFF99"><span class="style61">
                        <label><span class="style96"><strong>Reason for Search:</strong></span></label>
                        </span> <span class="style96"><strong>
                        <label></label>
                        </strong>
                        <label></label>
                        </span> <span class="style96">
                        <label></label>
                        </span>
                        <label></label></td>
                      <td width="703" bgcolor="#FFFF99">
                      
                      <form:textarea path="reason" cols="80"></form:textarea>
                      
                      </td>
                    </tr>
                  </table>
                    <table width="889">
                      <tr>
                        <td width="23" bgcolor="#FFFF99"><span class="style61">
                        <label><span class="style95a">2.</span></label>
                        </span></td>
                        <td width="147" height="48" bgcolor="#FFFF99"><span class="style61">
                  <label><span class="style96"><strong>Subject's First Name:</strong></span></label>
                          </span> <span class="style96">
                          <label></label>
                          </span>
                          <label></label></td>
                        <td width="173" bgcolor="#FFFF99"><form:input path="firstName" size="20"/></td>
                      <td width="96" bgcolor="#FFFF99"><span class="style61">
                        <label><span class="style96"><strong>Middle Initial: (optional)</strong></span></label>
                        </span> <span class="style96">
                        <label></label>
                        </span>
                        <label></label></td>
                        <td width="56" bgcolor="#FFFF99"><form:input path="middleName" size="1" maxlength="1"/></td>
                    <td width="88" bgcolor="#FFFF99"><span class="style61">
                        <label><span class="style96"><strong>Last Name:</strong></span></label>
                        </span> <span class="style96">
                        <label></label>
                        </span>
                        <label></label></td>
                        <td width="274" bgcolor="#FFFF99"><form:input path="lastName" size="20"/></td>
                      </tr>
                    </table>
                    <table width="889">
                      <tr>
                        <td width="23" bgcolor="#FFFF99"><span class="style76">
                        <label><span class="style96">3.</span></label>
                        </span> </td>
                        <td width="147" height="46" bgcolor="#FFFF99"><span class="style76">
                          <label><span class="style96">Date of Birth or Approximate Age:</span></label>
                        </span> </td>
                        <td width="223" bgcolor="#FFFF99"><form:input path="dob" size="20"/></td>
                        <td width="29" bgcolor="#FFFF99" class="style99">4. </td>
                        <td width="242" bgcolor="#FFFF99" class="style99"> Is this person a police officer, officer of the court, elected official, or celebrity?</td>
                        <td width="197" bgcolor="#FFFF99"><label></label>
                                               
                          <table width="143">
                            <tr>
                              <td width="56"><label>
                              <c:choose>
                              	<c:when test="${command.specialPerson}">
                              		<c:set var="yes" value="checked='checked'"></c:set>
                              	</c:when>
                              	<c:otherwise>
                              		<c:set var="no" value="checked='checked'"></c:set>
                              	</c:otherwise>
                              </c:choose>
                              
                                <input type="radio" name="specialPerson" value="true" ${yes} />
                              </label></td>
                              <td width="75" class="style99">Yes</td>
                            </tr>
                            <tr>
                              <td><input type="radio" name="specialPerson" value="false" ${no}></td>
                              <td><span class="style99">No</span></td>
                            </tr>
                          </table>                          </td>
                      </tr>
                    </table>
                  <table width="889">
                        <tr>
                          <td width="174" height="33"><p class="style97">Optional Fields:</p></td>
                          <td width="278">&nbsp;</td>
                          <td width="161" height="33">&nbsp;</td>
                          <td width="256">&nbsp;</td>
                      </tr>
                        <tr>
                          <td height="34"><span class="style61">Last-Known  Address:</span></td>
                          <td><form:input path="lastAddress" size="40"/></td>
                          <td height="34"><span class="style61">
                            <label>Social Security Number:</label>
                          </span></td>
                          <td><form:input path="ssn" size="11"/></td>
                        </tr>

                        <tr>
                          <td height="31"><span class="style61">
                            <label>City:</label>
                          </span></td>
                          <td><form:input path="city" size="40"/></td>
                          <td height="34"><span class="style61">
                            <label>Spouse  Name:</label>
                            </span>
                              <label></label></td>
                          <td><form:input path="spouseName" size="30"/></td>
                        </tr>
                        <tr>
                          <td height="32"><span class="style61">
                            <label>State:</label>
                          </span></td>
                          <td><div align="left">
                              <form:select path="state">
                              		<form:option value="">Select</form:option>
                              		<form:options items="${usStates}"/>
                              </form:select>
                          </div></td>
                          <td height="34">&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td height="32"><span class="style61">
                            <label>Zip Code:</label>
                          </span></td>
                          <td><form:input path="zip" size="12"/></td>
                          <td height="34">&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td height="34"><span class="style61">Address Date:</span></td>
                          <td><form:input path="addressDate" size="10"/></td>
                          <td height="34">&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                      </table>
                    <p>&nbsp;</p>
                    <table width="885">
                      <tr>
                        <td width="900" height="82"><h2 align="left" class="style59">Contact Information</h2>
                            <p align="left" class="style67">Please fill out your contact details below.</p>
                        </td>
                      </tr>
                    </table>
                    <p><span class="style69"><strong>*</strong> Required Fields</span></p>
                    <table width="802">
                      <tr>
                        <td width="172" height="34"><span class="style61">
                    <label><span class="style70">*</span>Your Name:</label>
                          </span>
                            <label></label></td>
                        <td width="298"><form:input path="contactName" size="30"/></td>
              <td width="316"><div align="center" class="style66">
                            <div align="left"></div>
                        </div></td>
                      </tr>
                      <tr>
                        <td height="35"><span class="style61">
                          <label>Company Name:</label>
                        </span></td>
                        <td><form:input path="company" size="40"/></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td><span class="style61">
                          <label><span class="style70">*</span>Phone:</label>
                        </span></td>
                        <td><label></label>
                          <label>
                          <form:input path="phone"/>
                          </label></td>
                        <td><div align="left" class="style66">
                            <div align="left"></div>
                        </div></td>
                      </tr>
                      <tr>
                        <td height="34"><span class="style61">
                          <label><span class="style70">*</span>Email Address:</label>
                          </span>
                            <label></label></td>
                        <td><form:input path="email" size="30"/></td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="40"><span class="style61">
                          <label><span class="style70">*</span>Confirm Email:</label>
                          </span>
                            <label></label></td>
                        <td><form:input path="confirmMail" size="30"/></td>
                        <td><span class="style61">
                          <label></label>
                        </span></td>
                      </tr>
                    </table>
                    <table width="885">
                      <tr>
                        <td width="900" height="91"><p align="left" class="style59">&nbsp;</p>
                          <p align="left" class="style67">We don't bill until we have located a confirmed address. We accept  MasterCard, Visa,  American Express, or personal or business checks, but we do not release results until confirmation of payment.</p>
                          <p align="left" class="style67">&nbsp;</p>
                            <p align="left" class="style67">Click the Submit Order button below and a research specialist will contact you to discuss your request.</p>
                            </td>
                      </tr>
                    </table>
                    <p>&nbsp;</p>
                    <table width="905">
                      <tr>
                        <td width="413"><div align="right"><input type="image" src="/springapp/images/skiptrace/bt-submit-order.png" alt="submit order" width="254" height="49" border="0"></div></td>
                        <td width="480"><div align="right">
                        
                        <a href="http://www.instantssl.com" id="comodoTL">SSL</a>
				<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script> &nbsp;&nbsp;&nbsp;
           		<a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></div>
                        
                        </td>
                      </tr>
                    </table>
                    </td>
                </tr>
              </table>
              </td>
          </tr>
        </table>
 </form:form>      
    </jsp:body>
    </neon:SkipTracePage>
