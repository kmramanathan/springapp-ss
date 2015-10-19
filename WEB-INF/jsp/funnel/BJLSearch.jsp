<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<c:choose>
<c:when test="${searchType eq 'name'}">
	<c:set var="title">Bankruptcies by Name</c:set>
</c:when>
<c:when test="${searchType eq 'address'}">
	<c:set var="title">Bankruptcies by Name City Zip</c:set>
</c:when>
<c:when test="${searchType eq 'ssn'}">
	<c:set var="title">Bankruptcies by SSN Tax ID</c:set>
</c:when>
</c:choose>

<neon:BJLfunnelPage title="${title}">
<jsp:attribute name="stylesheet">BJL_02.css</jsp:attribute>

<jsp:body>

<form:form method="post">
<form:hidden path="bjlSearchType"/>
		
		<tr>
		<td width="893" style="height:215px; background:url(/springapp/images/BJL/tal_header_03.jpg) top repeat-y;">
			<table width="893" style="height:215px; background:url(/springapp/images/BJL/bg_header_top_07.jpg) top no-repeat;">
				<tr>
					<td width="893" height="180" class="content_1" style="background:url(/springapp/images/BJL/bg_header_08.jpg) bottom no-repeat; padding:44px 565px 16px 60px;">
                      <p class="style28">&nbsp;</p>
                      <p class="style28">Search by State or Nationwide!</p>
                 <c:if test="${searchType eq 'ssn'}">
                 	<p class="style60a"><strong>Note:</strong> Judgments are NOT available by Social Security Number or Tax ID.</p>
                 </c:if>   
                 <c:if test="${searchType ne 'ssn'}">
                 	<p class="style60"> Instant access to over 100 million bankruptcy, judgment, and tax lien records.</p>
                 </c:if> 
                 </td>
			  </tr>
			</table>
        </td>
	</tr>
	<tr>
		<td width="893" style="height:207px; background:url(/springapp/images/BJL/bg_content_02.jpg) top no-repeat; padding:18px 7px 0 20px;">
			<div class="side_right">
				<div class="fill_bot">
					<div class="fill_top">
						<div class="left_top">
							<div class="left_bot">
								<div class="right_top">
									<div class="right_bot">
<c:choose>
<c:when test="${searchType eq 'name'}">
<input type="hidden" name="bjlCaseType" value="Any" >
		  <table width="862" height="267" style="height:207px;">
<tr>
												<td colspan="2" style="padding:20px 30px 20px 30px;" class="content_1">
												  <table width="783" height="154" border="0" align="center">
                                                    <tr>
                                                      <td width="770" height="148"><table width="771" border="0">
                          <tr>
                                                            <td width="374" height="40"><div align="left" class="style61">Search by Name</div></td>
                                                    <td width="201"><div align="left"><a href="searchBJL.do?searchType=address"><u>Search by Name-City-Zip</u></a></div></td>
                                                            <td width="182"><div align="left" class="style64"><a href="searchBJL.do?searchType=ssn"><u>Search by SSN/Tax ID</u></a></div></td>
                                                        </tr>
                                                        </table>
                                                          <table width="771" height="189" border="0">

                                                        <tr>
                                                          <td width="493" height="185"><table width="464" border="0">
                                                          <tr>
                                                                <td width="183"><div align="left"><strong>Name<span class="style24">*</span>:</strong></div></td>
                                                      <td width="271"><div align="left">
                                                                    <form:input path="bjlName" size="34" maxlength="34" />
                                                                </div></td>
                                                            </tr>
                                                              <tr>
                                                                <td><div align="left"><span class="style67">*Required Field</span></div></td>
                                                                <td><div align="left"><span class="style31">(Example: Smith John or Jones Plumbing)</span></div></td>
                                                              </tr>
                                                              <tr>
                                                                <td><div align="left"><strong>Defendant State:</strong></div></td>
                                                                <td><div align="left">
                                                                    <form:select path="bjlDefendantState" cssClass="formfield">
																		<form:option value=" ">All States</form:option>
																		<form:options items="${usStates}"/>
																		</form:select>
																	</div>
                                                                 </td>
                                                              </tr>
                                                              <tr>
                                                                <td height="27"><div align="left"><strong>Your Reference Code:</strong></div></td>
                                                                <td><div align="left">
                                                                    <input name="textfield5" type="text" id="textfield5" size="20" />
                                                                </div></td>
                                                              </tr>
                                                            </table>
                                                            <p>&nbsp;</p>
                                                            <p align="left"><span class="style22">This search is limited to 1000 results.</span></p>                                                              </td>
                                                          <td width="221"><p align="left"><strong>Name Type:</strong></p>
                                                              <p align="left">
                                                                <label>
                                                                <input name="bjlNameType" type="radio" id="radio5" value="Any" checked="checked" />
                                                                </label>
                                                                Any Name</p>
                                                            <p align="left">
                                                                <label>
                                                                <input type="radio" name="bjlNameType" id="radio6" value="Defendant" />
                                                                </label>
                                                              Defendant</p>
                                                            <p align="left">
                                                                <label>
                                                                <input type="radio" name="bjlNameType" id="radio7" value="Plaintiff" />
                                                                </label>
                                                              Plaintiff </p>
                                                            <p align="left">
                                                                <label>
                                                                <input type="radio" name="bjlNameType" id="radio8" value="Attorney" />
                                                                </label>
                                                              Attorney</p></td>
                                                          <td width="221"><p class="style18">IMPORTANT TIPS:</p>
                                                            <p class="style18">For best results, use the first and last name only and select a state.</p>
                                                          <p class="style18"> If it's a common name use the name-city-zip page and add the zip code or city.</p></td>
                                                        </tr>
                                                      </table>                                                      
                                                      <table width="747" border="0">
                                                        <tr>
                                                          <td width="336" height="61">&nbsp;</td>
                                                          <td width="145"><input type="image" src="/springapp/images/BJL/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></td>
                                                          <td width="252">&nbsp;</td>
                                                        </tr>
                                                      </table>
                                                      </td>
                                                    </tr>
                                                  </table>					                            </td>
</tr>
									  </table>
</c:when>

<c:when test="${searchType eq 'address'}">
<input type="hidden" name="bjlCaseType" value="Any">
		<table width="862" height="267" style="height:207px;">
<tr>
												<td colspan="2" style="padding:20px 30px 20px 30px;" class="content_1">
												  <table width="783" height="154" border="0" align="center">
                                                    <tr>
                                                      <td width="770" height="148"><table width="772" border="0">
                          <tr>
                                                            <td width="470" height="40"><div align="left" class="style61">
                                                              <p>Search by Name and State, City or Zip Code</p>
                                                              </div></td>
                                    <td width="129"><div align="left"><a href="searchBJL.do?searchType=name"><u>Search by Name</u></a></div></td>
                                    <td width="159"><div align="left" class="style64"><a href="searchBJL.do?searchType=ssn"><u>Search by SSN/Tax ID</u></a></div></td>
                                                        </tr>
                                                        </table>
                                                          <table width="770" height="189" border="0">

                                                        <tr>
                                                          <td width="469" height="185"><table width="448" border="0">
                                                            <tr>
                                                                  <td width="178"><div align="left"><strong>Name<span class="style24">*</span>:</strong></div></td>
                                                                  <td width="260"><div align="left">
                                                                      <form:input path="bjlName" size="34" maxlength="34" />
                                                                  </div></td>
                                                                </tr>
                                                                <tr>
                                                                  <td><div align="left"><span class="style67">*Required Field</span></div></td>
                                                                  <td><div align="left"><span class="style31">(Example: Smith John or Jones Plumbing)</span></div></td>
                                                                </tr>
                                                                <tr>
                                                                  <td><div align="left"><strong>Defendant City:</strong></div></td>
                                                                  <td><div align="left">
                                                                      <form:input path="bjlDefendantCity" size="34"/>
                                                                  </div></td>
                                                                </tr>
                                                                <tr>
                                                                  <td><div align="left"><strong>Defendant State:</strong></div></td>
                                                                  <td><div align="left">
                                                                      <form:select path="bjlDefendantState" cssClass="formfield">
																		<form:option value=" ">All States</form:option>
																		<form:options items="${usStates}"/>
																		</form:select>
                                                                  </div></td>
                                                                </tr>
                                                                <tr>
                                                                  <td><div align="left"><strong>Defendant Zip Code:</strong></div></td>
                                                                  <td><div align="left">
                                                                      <form:input path="bjlDefendantZip" size="5"/>
                                                                  </div></td>
                                                                </tr>
                                                                <tr>
                                                                  <td height="27"><div align="left"><strong>Your Reference Code:</strong></div></td>
                                                                  <td><div align="left">
                                                                      <input name="textfield" type="text" id="textfield3" size="20" />
                                                                  </div></td>
                                                                </tr>
                                                              </table>
                                                          </td>
                                                          <td width="130"><p align="left"><strong>Name Type:</strong></p>
                                                              <p align="left">
                                                                <label>
                                                                <input name="bjlNameType" type="radio" id="radio5" value="Any" checked="checked" />
                                                                </label>
                                                                Any Name</p>
                                                            <p align="left">
                                                                <label>
                                                                <input type="radio" name="bjlNameType" id="radio6" value="Defendant" />
                                                                </label>
                                                              Defendant</p>
                                                            <p align="left">
                                                                <label>
                                                                <input type="radio" name="bjlNameType" id="radio7" value="Plaintiff" />
                                                                </label>
                                                              Plaintiff </p>
                                                            <p align="left">
                                                                <label>
                                                                <input type="radio" name="bjlNameType" id="radio8" value="Attorney" />
                                                                </label>
                                                              Attorney</p></td>
                                                          <td width="157"><p class="style18 style21">IMPORTANT TIPS:
                                                          </p>
                                                          <p class="style18 style21">Best results are found on our Search By Name page. This page is for  searches on common names. </p>
                                                          <p class="style18 style21">For best results omit the middle name or  initial!</p></td>
                                                        </tr>
                                                      </table>                                                      
                                                      <table width="747" border="0">
                                                        <tr>
                                                          <td width="336" height="61"><span class="style22">This search is limited to 1000 results.</span></td>
                                                          <td width="145"><input type="image" src="/springapp/images/BJL/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></td>
                                                          <td width="252">&nbsp;</td>
                                                        </tr>
                                                      </table>
                                                      </td>
                                                    </tr>
                                                  </table>					                            </td>
</tr>
									  </table>
</c:when>

<c:when test="${searchType eq 'ssn'}">
<input type="hidden" name="bjlName" value=" ">
	<table width="862" height="267" style="height:207px;">
<tr>
												<td colspan="2" style="padding:20px 30px 20px 30px;" class="content_1">
												  <table width="792" height="327" border="0" align="center">
                                                    <tr>
                                                      <td width="770" height="323"><table width="782" border="0">
                          <tr>
                                                            <td width="444" height="40"><div align="left" class="style61">
                                                              <p>Search by Social Security Number or Tax ID</p>
                                                              <p class="style68">(For Bankruptcies and Tax Liens Only)</p>
                                    </div></td>
                                    <td width="141"><div align="left"><a href="searchBJL.do?searchType=name"><u>Search by Name</u></a></div></td>
                                    <td width="183"><div align="left" class="style64"><a href="searchBJL.do?searchType=address"><u>Search by Name-City-Zip</u></a></div></td>
                                                        </tr>
                                                        </table>
                                                          <table width="770" height="160" border="0">

                                                        <tr>
                                                          <td width="550" height="156"><table width="426" border="0">
                                                            <tr>
                                                              <td width="183"><div align="left"><strong>SSN / Tax ID<span class="style24">*</span>:</strong></div></td>
                                                              <td width="185"><div align="left">
                                                                  <form:input path="bjlSsnTaxId" size="18" maxlength="18" />
                                                              </div></td>
                                                            </tr>
                                                            <tr>
                                                              <td><div align="left"><span class="style67">* Required Field</span></div></td>
                                                              <td><div align="left"><span class="style31">(No dashes or spaces)</span></div></td>
                                                            </tr>
                                                            <tr>
                                                              <td height="33"><div align="left"><strong>Your Reference Code:</strong></div></td>
                                                              <td><div align="left">
                                                                  <input name="textfield5" type="text" id="textfield5" size="18" />
                                                              </div></td>
                                                            </tr>
                                                          </table>
                                                          <p class="style18 style21">CAUTION: Search results will only include records that contain a Social Security Number or Tax ID. Since judgments aren't normally referenced by one of these numbers, then you won't find judgments here. Also, if a bankruptcy or tax lien record doesn't contain a Social Security or Tax ID Number, then it won't appear in your results. BEST results are found on the Search By Name page.</p></td>
                                                          <td width="210"><p align="left"><strong>Case Type:</strong></p>
                                                              <p align="left">
                                                                <label>
                                                                <input name="bjlCaseType" type="radio" id="radio5" value="Any" checked="checked" />
                                                                </label>
                                                                Bankruptcy and Tax Lien</p>
                                                              <p align="left">
                                                                <label>
                                                                <input type="radio" name="bjlCaseType" id="radio6" value="Bankruptcy" />
                                                                </label>
                                                                Bankruptcy </p>
                                                              <p align="left">
                                                                <label>
                                                                <input type="radio" name="bjlCaseType" id="radio7" value="Tax Lien" />
                                                                </label>
                                                                Tax Lien
  <label></label>
                                                              </p></td>
                                                          </tr>
                                                      </table>                                                      
                                                      <table width="747" border="0">
                                                        <tr>
                                                          <td width="336" height="61"><span class="style22">This search is limited to 1000 results.</span></td>
                                                          <td width="145"><input type="image" src="/springapp/images/BJL/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></td>
                                                          <td width="252">&nbsp;</td>
                                                        </tr>
                                                      </table>
                                                      </td>
                                                    </tr>
                                                  </table>					                            </td>
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


</jsp:body>

</neon:BJLfunnelPage>
								 