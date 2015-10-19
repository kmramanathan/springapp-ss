<%@taglib tagdir="/WEB-INF/tags" prefix="bjlneon"%>
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

<bjlneon:NewFunnel title="${title}">
<jsp:attribute name="stylesheet">new-bjl.css</jsp:attribute>

<jsp:body>
<form:form method="post">
<form:hidden path="bjlSearchType"/>


	<tr>
		<td width="937" style="height:100%; padding: 10px 0;">
		<table width="884" align="center" border="0">
          <tr>
            <td>
            <table border="0" cellpadding="0" cellspacing="0">
            <tr valign="top">
            <td>
            <c:if test="${searchType eq 'name'}">
           <h1 align="left" class="style108n">Bankruptcies, Judgments &amp; Tax Liens </h1>
           </c:if>
            <c:if test="${searchType eq 'address'}">
           <h1 align="left" class="style108n">Bankruptcies, Judgments &amp; Tax Liens </h1>
           </c:if>
            <c:if test="${searchType eq 'ssn'}">
           <h1 align="left" class="style108n">Bankruptcies &amp; Tax Liens </h1>
           </c:if>
            <h2 class="style109n"><strong>Search any state or nationwide for only $5.00</strong></h2>
            </td>
            <td align="left" width="38%">
             <c:if test="${searchType eq 'ssn'}" >     
              			<p  style="color:#ff0000; font-size:12px; font-style:normal;" align="left"><strong>Note:</strong> Judgments are NOT available by Social<br> Security Number or Tax ID.</p>
          </c:if>
          </td>
          </tr>
          </table>
              <div align="left" class="style107">     
             
                <p class="style111n" style="margin:0;padding:0; padding-left:6px;">Instant access to over 100 million  records.</p>
               
                
                <p class="style111n" >Find information vital to decision making:</p>
                <div class="style109n" style="margin:0; padding:0px 0 10px 0;"></div>

                <table width="726">
                  <tr>
                    <td><ul>
                        <li class="style111n">Does someone owe you money?</li>
                      <li class="style111n">Who else do they owe? Are you first or last in line to collect?</li>
                      <li class="style111n">Going into business with someone? What is their track record?</li>
                      <li class="style111n">Trying to locate a specific bankruptcy, judgment or tax lien record?</li>
                    </ul></td>
                  </tr>
                </table>
               <div> &nbsp; </div>
                
                
<c:choose>
<c:when test="${searchType eq 'name'}">
<input type="hidden" name="bjlCaseType" value="Any" >          
<input type="hidden" name="bjlNameType" value="Any" />
                
                <!-- Name BJLSearch Form -->               
                <table width="847" height="329" border="0" bordercolor="#000000">
                  <tr>
                    <td width="841" height="325">
                    <table width="292" border="0">
                        <tr>
                          <td width="286" height="40"><div align="left" class="style61">Search by:</div></td>
                        </tr>
                      </table>
                        <table width="623" border="0">
                          <tr>
                            <td width="190" height="0" class="style110n"><table width="165" border="0" bordercolor="#000000" bgcolor="#FFFFFF">
                              <tr>
                                <td width="155" height="36"><div align="center" style="color:#003366; font-size: 18px;font-weight: bold;">Name</div></td>
                              </tr>
                            </table>
                              </td>
                            <td width="46" class="style61">OR</td>
                            <td width="159"><a href="newSearchBJL.do?searchType=address"><img src="/springapp/images/newfunnel/bt-name-city-zip-small.jpg" alt="name city zip" width="136" height="33" border="0"></a></td>
                            <td width="48"><span class="style61">OR</span></td>
                            <td width="336"><a href="newSearchBJL.do?searchType=ssn"><img src="/springapp/images/newfunnel/bt-ssn-taxid-small.jpg" alt="ssn or tax id" width="136" height="33" border="0"></a></td>
                          </tr>
                        </table>
                        <table width="819" height="154" border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <td width="497" height="150" valign="top">
                            <table width="484" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                  <td width="199"><div align="left"><strong>Name<span class="style24">*</span>:</strong></div></td>
                            <td width="275"><div align="left">
                                      <form:input path="bjlName" size="34" maxlength="34" />
                                  </div></td>
                              </tr>
                                <tr>
                                  <td height="27"><div align="left"><span class="style67">*Required Field</span></div></td>
                                  <td><div align="left"><span class="style31">(Example: Smith John or Jones Plumbing)</span></div></td>
                                </tr>
                                <tr>
                                  <td height="37"><div align="left"><strong>Defendant State:</strong></div></td>
                                  <td><div align="left">
                                       <form:select path="bjlDefendantState" cssClass="formfield">
											<form:option value=" ">All States</form:option>
											<form:options items="${usStates}"/>
										</form:select>
                                  </div></td>
                                </tr>
                                <tr style="display:none">
                                  <td height="27" width="300"><div align="left">
                                    <div style="width:200px;"><strong>Your Reference Number:</strong></div>
                                    <div>&nbsp;</div>
                                    <div>(for your records)</div>
                                  </div></td>
                                  <td valign="top"><div align="left" >
                                      <input name="textfield5" type="text" id="textfield5" size="20" />
                                  </div></td>
                                </tr>
                              </table>                                </td>
                         
                            <td width="288" ><span class="style112n">IMPORTANT TIPS:</span>
                                <p class="style112n">&nbsp;</p>
                                <span class="style112n">For best results, use the first and last name only and select a state. Keep the name short! Don't use Jr., Sr., Inc., LLC, etc.</span>
                              <p class="style112n">&nbsp; </p>
                              <span class="style112n">If it's a common name use the name-city-zip page and add the zip code or city.</span>
                              <p class="style18">&nbsp;</p>
                           <span class="style22" style="padding:0; margin:0;">This search is limited to 1000 results.</span></td>
                      </tr>
                        </table>
                    <table width="820" border="0" align="right">
              <tr>
                            <td width="220" height="94">&nbsp;</td>
                        <td width="329" style="padding-left:20px;"><input type="image" src="/springapp/images/newfunnel/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></td>
                        <td width="330">
                             <a href="http://www.instantssl.com" id="comodoTL">SSL</a>
				<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script> &nbsp;&nbsp;&nbsp;
           		<a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a>
                        
                           </td></tr>
                      </table></td>
                  </tr>
                </table>
                
</c:when>


<c:when test="${searchType eq 'address'}">
<input type="hidden" name="bjlCaseType" value="Any">                
<input name="bjlNameType" value="Any" type="hidden" />                  
                
               <!-- Begin Name & City BJL Search -->
                <table width="849" height="329" border="0" bordercolor="#000000">
                  <tr>
                    <td width="843" height="325"><table width="292" border="0">
                        <tr>
                          <td width="286" height="40"><div align="left" class="style61">Search by:</div></td>
                        </tr>
                      </table>
                        <table width="612">
                          <tr>
                            <td width="238" height="50" class="style110n"><table width="222" border="0" bordercolor="#000000" bgcolor="#FFFFFF">
                              <tr>
                                <td width="212" height="35"><div align="center" style="color:#003366; font-size: 18px;font-weight: bold;">Name and City or Zip</div></td>
                              </tr>
                            </table>
                            </td>
                            <td width="43" class="style61">OR</td>
                            <td width="146"><a href="newSearchBJL.do?searchType=name"><img src="/springapp/images/newfunnel/bt-name-small.jpg" alt="name" width="136" height="33" border="0"></a></td>
                            <td width="32"><span class="style61">OR</span></td>
                            <td width="148"><a href="newSearchBJL.do?searchType=ssn"><img src="/springapp/images/newfunnel/bt-ssn-taxid-small.jpg" alt="ssn or tax id" width="136" height="33" border="0"></a></td>
                          </tr>
                        </table>
                        <table width="100%" height="223" border="0" cellpadding="0" cellspacing="0">
                          <tr valign="top">
                            <td width="482" height="219"><table width="479" border="0" cellpadding="0" cellspacing="0">
                              <tr>
                                <td width="230"><div align="left"><strong>Name<span class="style24">*</span>:</strong></div></td>
                            <td width="267"><div align="left">
                                    <form:input path="bjlName" size="34" maxlength="34" />
                                </div></td>
                              </tr>
                              <tr>
                                <td height="25"><div align="left"><span class="style67">*Required Field</span></div></td>
                                <td><div align="left"><span class="style31">(Example: Smith John or Jones Plumbing)</span></div></td>
                              </tr>
                              <tr>
                                <td height="35"><div align="left"><strong>Defendant City:</strong></div></td>
                                <td><div align="left">
                                    <form:input path="bjlDefendantCity" size="34"/>
                                </div></td>
                              </tr>
                              <tr>
                                <td height="33"><div align="left"><strong>Defendant State:</strong></div></td>
                                <td><div align="left">
                                    <form:select path="bjlDefendantState" cssClass="formfield">
										<form:option value=" ">All States</form:option>
										<form:options items="${usStates}"/>
									</form:select>
                                </div></td>
                              </tr>
                              <tr>
                                <td height="33"><div align="left"><strong>Defendant Zip Code:</strong></div></td>
                                <td><div align="left">
                                    <form:input path="bjlDefendantZip" size="5"/>
                                </div></td>
                              </tr>
                              <tr style="display:none">
                                <td height="42"><div align="left">
                                  <div style="width:200px;"><strong>Your Reference Number:</strong></div>
                                  <div>&nbsp;</div>
                                  <div>(for your records)</div>
                                </div></td>
                              <td valign="top"><div align="left" >
                                    <input name="textfield2" type="text" id="textfield3" size="20" />
                                </div></td>
                              </tr>
                            </table></td>
                            <td width="19"><p align="left">&nbsp;</p>                            </td>
                            <td width="302"><span class="style18 style21 style112n">IMPORTANT TIPS: </span>
                              <p class="style18 style21 style112n">&nbsp;</p>
                              <span class="style18 style21 style112n">Best results are found on our Search By Name page. This page is for  searches on common names. </span>
                              <p class="style18 style21 style112n">&nbsp;</p>
                              <span class="style18 style21 style112n">Keep the name short! Don't use Jr., Sr., Inc., LLC, etc.</span>
                              <p class="style18">&nbsp;</p>
                            <span class="style22">This search is limited to 1000 results.</span></td>
                      </tr>
                        </table>
                    <table width="818" border="0">
              <tr>
                            <td width="230" height="80">&nbsp;</td>
                        <td width="346" style="padding-left:20px;"><input type="image" src="/springapp/images/newfunnel/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></td>
                            <td width="300">
                            <a href="http://www.instantssl.com" id="comodoTL">SSL</a>
				<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script> &nbsp;&nbsp;&nbsp;
           		<a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a>
                        
                           </td>
                      </tr>
                      </table></td>
                  </tr>
                </table>
                <!-- end name & city search -->
                
 </c:when>

<c:when test="${searchType eq 'ssn'}">
<input type="hidden" name="bjlName" value=" ">               
                
                <table width="852" height="329" border="0" bordercolor="#000000">
                  <tr>
                    <td width="846" height="325">
                    <table width="292" border="0">
                        <tr>
                          <td width="286" height="40"><div align="left" class="style61">Search by:</div></td>
                        </tr>
                      </table>
                        <table width="610" border="0" cellpadding="0" cellspacing="0" class="ssntable">
                          <tr>
                            <td width="176" height="0" class="style110n">
                            <table width="163" border="0" bordercolor="#000000" bgcolor="#FFFFFF">
                              <tr>
                                <td width="153" height="37"><div align="center" style="color:#003366; font-size: 18px;font-weight: bold;">SSN  or Tax ID</div></td>
                              </tr>
                            </table>
                            </td>
                            <td width="0" class="style61">OR</td>
                            <td width="150"><a href="newSearchBJL.do?searchType=name"><img src="/springapp/images/newfunnel/bt-name-small.jpg" alt="name" width="136" height="33" border="0"></a></td>
                            <td width="0"><span class="style61">OR</span></td>
                            <td width="194"><a href="newSearchBJL.do?searchType=address"><img src="/springapp/images/newfunnel/bt-name-city-zip-small.jpg" alt="name city zip" width="136" height="33" border="0"></a></td>
                          </tr>
                      </table>
                      <!--  begin Bankruptcies SSN Tax ID -->
                        <table width="842" height="148" border="0">
                          <tr valign="top">
                            <td width="381" height="144"><table width="379" border="0">
                              <tr>
                                <td width="206"><div align="left"><strong>SSN / Tax ID<span class="style24">*</span>:</strong></div></td>
                                <td width="163"><div align="left">
                                    <form:input path="bjlSsnTaxId" size="18" maxlength="18" />
                                </div></td>
                              </tr>
                              <tr>
                                <td height="30"><div align="left"><span class="style67">* Required Field</span></div></td>
                                <td><div align="left"><span class="style31">(No dashes or spaces)</span></div></td>
                              </tr>
                              <tr style="display:none">
                                <td height="50" width="230"><div align="left">
                                  <div style="width:200px;"><strong>Your Reference Number:</strong></div>
                                  <div>&nbsp;</div>
                                  <div>(for your records)</div>
                                </div></td>
                                <td valign="top"><div align="left" >
                                    <input name="textfield5" type="text" id="textfield5" size="18" />
                                </div></td>
                              </tr>
                            </table>
                            </td>
                            <td width="15"><p align="left">&nbsp;</p>                            </td>
                            <td width="432"><p class="style112n"><span class="style112n style21">CAUTION: Search results will only include records that contain a Social Security Number or Tax ID. Since judgments aren't normally referenced by one of these numbers, then you won't find judgments here. Also, if a bankruptcy or tax lien record doesn't contain a Social Security or Tax ID Number, then it won't appear in your results. BEST results are found on the Search By Name page.</span></p>
                           
                            <p ><span class="style22" >This search is limited to 1000 results.</span></p></td>
                      </tr>
                        </table>
                         <!--  end Bankruptcies SSN Tax ID -->
                         <!-- Begin Comodo logo  -->
                    <table width="842" border="0">
      <tr>
                            <td width="240" height="97">&nbsp;</td>
                        <td width="335" style="padding-left:20px;"><input type="image" src="/springapp/images/newfunnel/bt-start-search-small.png" alt="start search" width="138" height="48" border="0"></td>
                        <td width="350">
                             <a href="http://www.instantssl.com" id="comodoTL">SSL</a>
				<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script> &nbsp;&nbsp;&nbsp;
           		<a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a>
                        
                           </td></tr>
                    </table>
                   
               </td>
                  </tr>
                </table>
                  <!-- Begin Comodo logo  -->
 </c:when>
 </c:choose>               
                
         <table width="852">
                  <tr>
                    <td width="844">
                    	<form:errors path="*"  cssStyle="margin-left:200px;color:red;font-size:12px;"/>
                    </td>
                     </tr>
           </table>      
                
                
                
                <table width="852">
                  <tr>
                    <td width="844"><span class="style15">All information retrieved from or through SearchSystems.net must be utilized in accordance  with the User Agreement and all applicable state and federal laws, including the <a href="http://www.ftc.gov/os/statutes/fcra.htm">Fair Credit Reporting Act</a>;  any violation of these will be grounds for immediate termination of your account without  notice. <br />
                        <br />
                        <strong>DISCLAIMER:</strong> No warranty of any type is provided as to the quality or accuracy of the  information obtained from or through SearchSystems.net, and any reliance on that  information is solely at your own risk and responsibility.  Information contained herein  is derived solely from public records, which may not be 100 percent accurate or complete.  Users should not assume that this data provides a complete or accurate record of any  person's bankruptcy, judgment, or tax lien history.  Please see SearchSystems' <a href="${showAgreementUrl}?version=funnel" target="_blank">User Agreement</a> for all applicable terms and conditions. <br />
                        <br />
Pacific Information Resources, Inc. dba Search Systems assumes no liability for any claims  for damages arising from the use of this data beyond the actual cost of the searches  performed. </span></td>
                  </tr>
                </table>
            </div>
            </td>
          </tr>
        </table>
		  </td>
  </tr>
	  
		
</form:form>


</jsp:body>

</bjlneon:NewFunnel>
								 