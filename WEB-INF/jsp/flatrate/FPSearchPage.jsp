<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<c:choose>
<c:when test="${searchType eq 'basic'}">
	<c:set var="title">Findpeople flat rate</c:set>
</c:when>
<c:when test="${searchType eq 'advanced'}">
	<c:set var="title">Advanced Search</c:set>
</c:when>
<c:when test="${empty searchType}">
	<c:set var="title">Findpeople flat rate</c:set>
</c:when>
</c:choose>

<neon:FRFPcommonPage title="${title}">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

<tr>
		<td width="893" style="height:215px; background:url(/springapp/images/flatrate/tal_header_03.jpg) top repeat-y;">
			<table width="893" style="height:215px; background:url(/springapp/images/flatrate/bg_header_top_07.jpg) top no-repeat;">
				<tr>
					<td width="893" class="content_1" style="background:url(/springapp/images/flatrate/bg_fpheader_08.jpg) bottom no-repeat; padding:44px 565px 16px 60px;">
                      <p class="style28">Welcome!</p>
                      <p><span class="style47">Here you may run virtually unlimited* searches for names, addresses, <strong>address report dates</strong>, phone numbers, and dates of birth</span><span class="style28">.</span></p>
                  </td>
			  </tr>
			</table>
        </td>
	</tr>	
	

<form:form method="post" name="searchForm">
<input type="hidden" value="${searchType}"/>
	<tr>
		<td width="893" style="height:207px; background:url(/springapp/images/flatrate/bg_content_02.jpg) top no-repeat; padding:18px 7px 0 20px;">
			<div class="side_right">
				<div class="fill_bot">
					<div class="fill_top">
						<div class="left_top">
							<div class="left_bot">
								<div class="right_top">
									<div class="right_bot">
									
<c:choose>
<c:when test="${searchType eq 'basic'}">

	<table width="862" height="267" style="height:207px;">
<tr>
												<td colspan="2" style="padding:20px 30px 20px 30px;" class="content_1">
												  <table width="796" height="154" border="0" align="left">
                                                    <tr>
                                                      <td width="770" height="148">
													  
													  
													  <table width="785" border="0">
                                                          <tr>
                                                            <td width="301" height="33"><div align="left">
                                                              <h2 class="style36">Find People</h2>
                                                            </div></td>
                                                            <td width="240"><div align="left"></div></td>
                                                            <td width="230"><div align="right"><strong><a href="FPsearchLanding.do?searchType=advanced">Go to Advanced Search</a></strong></div></td>
                                                        </tr>
                                                        </table>
                                                        <table width="786" border="0">
                                                          <tr>
                                                              <td width="218"><div align="left"><strong>First Name <span class="style24">*</span></strong></div></td>
                                                            <td width="86"><div align="left"><strong>MI</strong></div></td>
                                                            <td width="249"><div align="left"><strong>Last Name <span class="style24">*</span></strong></div></td>
                                                            <td width="215"><div align="left"><strong>State</strong></div></td>
                                                          </tr>
                                                            <tr>
                                                              <td height="42"><div align="left">
                                                                  <form:input path="searchFirstName" cssClass="formfield" size="24" />
                                                              </div></td>
                                                <td><div align="left">
                                                                  <form:input path="searchMidName" cssClass="formfield" size="1" maxlength="1"/>
                                                              </div></td>
                                                              <td><div align="left">
                                                                  <form:input path="searchLastName" cssClass="formfield" size="24" />
                                                              </div></td>
                                                              <td><div align="left">
                                                                  <form:select path="searchState" cssClass="formfield">
																		<form:options items="${searchState}"/>		
																</form:select>
                                                              </div></td>
                                                            </tr>
                                                          </table>
                                                          <table width="785" border="0">
                                                            <tr>
                                                              <td width="338" height="62"><p class="style26a">* Required Field</p>                                                              </td>
                                                              <td width="178"><input type="image" src="/springapp/images/flatrate/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></td>
                                                              <td width="255"><div align="center"></div></td>
                                                            </tr>
                                                          </table>                                                      </td>
                                                    </tr>
                                                  </table>
					    <p>&nbsp;</p>                        </td>
</tr>
									  </table>
	
		 
</c:when>

<c:when test="${searchType eq 'advanced'}">
	<table width="862" height="267" style="height:207px;">
<tr>
												<td colspan="2" style="padding:20px 30px 20px 30px;" class="content_1"><table width="796" height="223" border="0" align="left">
                                                  <tr>
                                                    <td width="770" height="219" valign="bottom"><table width="784" border="0">
                                                    <tr>
                                                          <td width="535" height="28"><div align="left" class="style27">
                                                            <h2><span class="style36">Find People - Advanced Search</span></h2>
                                                      </div></td>
                                                      <td width="239"><div align="right"><a href="FPsearchLanding.do?searchType=basic"><strong><u>Go to Basic Search</u></strong></a></div></td>
                                                      </tr>
                                                      </table>
                        <table width="784" border="0">
                                                          <tr>
                                                            <td width="200"><div align="left"><strong>First Name <span class="style24">*</span></strong></div></td>
                                                            <td width="188"><div align="left"><strong>MI</strong></div></td>
                                                            <td width="382"><div align="left"><strong>Last Name <span class="style24">*</span></strong></div></td>
                                                        </tr>
                                                          <tr>
                                                            <td><div align="left">
                                                                 <form:input path="searchFirstName" cssClass="formfield" size="20" />
                                                            </div></td>
                                                            <td><div align="left">
                                                                <form:input path="searchMidName" cssClass="formfield" size="1" maxlength="1"/>
                                                            </div></td>
                                                            <td><div align="left">
                                                                <form:input path="searchLastName" cssClass="formfield" size="25" />
                                                            </div></td>
                                                          </tr>
                                                          <tr>
                                                            <td><strong>City</strong></td>
                                                            <td><strong>State</strong></td>
                                                            <td><strong>Date of Birth</strong></td>
                                                          </tr>
                                                          <tr>
                                                            <td height="31"><form:input path="searchCity" cssClass="formfield" size="20" /></td>
                                                          <td>  <form:select path="searchState" cssClass="formfield">
																		<form:options items="${searchState}"/>		
																</form:select></td>
                                                            <td><form:input path="searchDob" cssClass="formfield" size="10" maxlength="10"/>
                                                                <span class="style23">(must be MM/DD/YYYY)</span></td>
                                                          </tr>
                                                        </table>
                                                      <table width="496" border="0">
                                                        <tr>
                                                          <td width="341" height="30"><span class="style34a">* Required Field</span></td>
                                                          <td width="145"><input type="image" src="/springapp/images/flatrate/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></td>
                                                        </tr>
                                                      </table>                                                      </td>
                                                  </tr>
                                                </table>
					    <p>&nbsp;</p>                        </td>
</tr>
									  </table>

</c:when>


</c:choose>
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
		<td width="893" style="height:100%; padding: 10px 0;"><div align="center">
		  <p class="style48">* To prevent data mining abuse, we set a limit of 100 searches per day. If you need more than that, please contact our office at 800-350-2232.</p>
		  <p class="style48">&nbsp;</p>
		  <p class="style48"><span class="style60">Need more help finding people? Check out our <a href="/springapp/funnel/Find-People-Guide.html" target="blank">Free Guide</a>.</span></p>
		</div></td>
  </tr>

</jsp:body>

</neon:FRFPcommonPage>
								 