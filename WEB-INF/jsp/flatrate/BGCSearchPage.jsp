<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRBGCcommonPage title="Criminal records flat rate">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

  <tr>
    <td width="893" style="height:215px; background:url(/springapp/images/flatrate/tal_header_03.jpg) top repeat-y;"><table width="893" style="height:215px; background:url(/springapp/images/flatrate/bg_header_top_07.jpg) top no-repeat;">
        <tr>
          <td width="893" class="content_1" style="background:url(/springapp/images/flatrate/bg_header_08.jpg) bottom no-repeat; padding:44px 565px 16px 60px;"><p class="style49">&nbsp;</p>
            <p class="style49"><Strong>Welcome!</Strong></p>
            <p><span class="style32a">Here you may run virtually unlimited* nationwide criminal record searches from our database of over 400 million records.</span></p>            
          </td>
        </tr>
      </table></td>
  </tr>
  
  <form:form method="post">
  
  <tr>
    <td width="893" style="height:207px; background:url(/springapp/images/flatrate/bg_content_02.jpg) top no-repeat; padding:18px 7px 0 20px;"><div class="side_right">
        <div class="fill_bot">
          <div class="fill_top">
            <div class="left_top">
              <div class="left_bot">
                <div class="right_top">
                  <div class="right_bot">
                    <table width="862" height="267" style="height:207px;">
                      <tr>
                        <td colspan="2" style="padding:20px 30px 20px 30px;" class="content_1"><table width="760" border="0" align="center">
                            <tr>
                              <td width="530" height="35"><div align="center">
                                  <h2 align="left"><span class="style41a">Nationwide Criminal Record Search</span></h2>
                                  <p align="left"><span class="style26a">* Required Field</span></p>
                                </div></td>
                              <td width="220"><div align="right" class="style42a">
                                  <div align="right"></div>
                                </div></td>
                            </tr>
                          </table>
                          <table width="760" border="0" align="center">
                            <tr>
                              <td width="297" height="35"><div align="center">
                                  <label for="bgc_first_name"><span class="style43a"><span class="style45">*</span> <span class="style46a">First Name:</span></span></label>
                                  <form:input path="bgcFirstName" cssClass="formfield" size="24" maxlength="20" />
                                </div></td>
                              <td><span class="style51">Input 3 or more letters of first name (e.g. SAM will return results for SAM
									and SAMUEL and SAMANTHA). </span></td>
                              <td width="160"></td>
                            </tr>
                          </table>
                          <table width="760" border="0" align="center">
                            <tr>
                              <td width="297" height="38"><div align="center">
                                  <label for="bgc_first_name"></label>
                                  <label for="bgc_dob"></label>
                                  <label for="bgc_first_name"><span class="style43a"><span class="style45">*</span> <span class="style46a">Last Name:</span></span></label>
                                  <form:input path="bgcLastName" cssClass="formfield" size="24" maxlength="20" />
                                  <label for="bgc_dob"></label>
                                </div></td>
                              <td> <span class="style51">Input exact last name (no partial search allowed).</span> </td>
                              <td width="159"><div align="right"><a href="BGCDatasource.do" target="blank">Database Coverage</a></div></td>
                            </tr>
                          </table>
                          <table width="760" border="0" align="center">
                            <tr>
                              <td width="298" height="38"><div align="center">
                                  <label for="bgc_first_name"></label>
                                  <label for="bgc_dob"></label>
                                  <label for="bgc_first_name"></label>
                                  <label for="bgc_dob"><span class="style45">*</span> <span class="style46a">Date of Birth:</span></label>
                                 	<form:select path="bgcDobMonth" cssClass="formfield">
										<form:option value="">Month</form:option>
										<form:options items="${bgcDobMonths}"/>
									</form:select>&nbsp;
									<form:select path="bgcDobDay" cssClass="formfield">
									<form:option value="">Day</form:option>
									<form:options items="${bgcDobDays}"/>
									</form:select>&nbsp; 
									<form:select path="bgcDobYear" cssClass="formfield">
									<form:option value="">Year</form:option>
									<form:options items="${bgcDobYears}"/>
									</form:select>
                                  <label for="bgc_dob"></label>
                                  <label for="bgc_dob"></label>
                                  <label for="bgc_first_name"></label>
                                  <label for="bgc_dob"></label>
                                </div></td>
                              <td width="452"><label for="bgc_dob"></label>
                                <label for="bgc_dob">
                                <form:checkbox path="exactDob" cssClass="formfield" />
                                <span class="style51">Non-exact DOB match for sex offenders. Check this box if you wish to include more States in your sex offender search (50 versus 39). Be careful, as it may return records that are not for your subject (as DOB may not be listed).</span></label></td>
                            </tr>
                          </table><br>
                          <table width="760" border="0" align="center">
                            <tr>
                              <td width="205" height="41"><label for="bgcReferenceCode"></label>
                                <label for="bgc_first_name"></label>
                                <span class="style46a">Purpose of your search:</span></td>
                              <td width="545">
                                  <div><span class="style45">*</span>
                                    <form:select path="bgcPurpose" cssClass="formfield">
			<form:option value="">[Choose]</form:option>
            <form:options items="${bgcSearchPurposes}"/>
            </form:select>
                                  </div>
                                </td>
                            </tr>
                          </table>
                          <table width="760" border="0" align="center">
                            <tr>
                              <td width="267" height="70"><label for="bgc_first_name"></label>
                                <label for="bgc_dob"></label></td>
                              <td width="207"><div align="center"><input type="image" src="/springapp/images/flatrate/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></div></td>
                              <td width="272"><div align="center" class="style48a style42a"></div></td>
                            </tr>
                          </table></td>
                      </tr>
                    </table>
                    
                     <center>
					<div style="background-color:#FFFFCC;width:600px; font-size:14px" align="center" ><font color="red"><form:errors path="*" element="div" id="error" cssClass="error-box" /></font></div>
					</center>
                    
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div></td>
  </tr>
  </form:form>
  <tr>
    <td width="893" style="height:100%; padding: 10px 0;"><table width="804" border="0" align="center">
        <tr>
          <td width="750" class="content_1" style="padding:0 30px;"><h3 class="style33b"><span class="style50">* To prevent data mining abuse, we set a limit of 100 searches per day. If you need more than that, please contact our office at 800-350-2232.</span></h3>
            <h3 class="style33b">All information retrieved from or through SearchSystems.net must be utilized in accordance  with the User Agreement and all  applicable state and federal laws, including the <a href="http://www.ftc.gov/os/statutes/fcra.htm">Fair Credit Reporting Act</a>;  any violation of these will be grounds for immediate termination of your account without  notice. </h3>
            <h3 class="style33b">Please see our <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=disclaimer" target="_blank">disclaimer</a> and also our <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=disclaimer" target="_blank">Notice to California Employers or Employers screening California residents</a>.<br />
              <br />
              DISCLAIMER: No warranty of any type is provided as to the quality or accuracy of the  information obtained from or through SearchSystems.net, and any reliance on that  information is solely at your own risk and responsibility.  Information contained herein  is derived solely from public records, which may not be 100 percent accurate or complete.  Users should not assume that this data provides a complete or accurate record of any  person's criminal record  history.  Please see SearchSystems' <a href="https://premium.searchsystems.net/agreement.php">User Agreement</a> for all applicable terms and conditions. <br />
              <br />
              Pacific Information Resources, Inc. dba Search Systems assumes no liability for any claims  for damages arising from the use of this data beyond the actual cost of the searches  performed. </h3></td>
        </tr>
      </table></td>
  </tr>

</jsp:body>
</neon:FRBGCcommonPage>
