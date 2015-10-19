<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<neon:NewFunnel title="${title}" >
<jsp:attribute name="stylesheet">new-bjl.css</jsp:attribute>
<jsp:body>
<style type="text/css">
<!--
.style19 {font-family: Arial, Helvetica, sans-serif}
.style20 {font-size: small}
.style73 {	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight: bold;
}
.style15 {font-size: 0.8em}
.style24 {color: #FF0000}
.style31 {font-size: x-small}
.style61 {font-size: 18px;
	font-weight: bold;
}
.style113 {color: #FF0000; font-size: 10px; }
.style118 {color: #FFFFFF}
.style120 {color: #000000; font-size: small;}
.style126 {font-size: 0.9em}
.style131 {color: #FF0000; font-size: 12px; font-weight: bold; }
-->
</style>
<form:form method="post">
<form:hidden path="bjlSearchType"/>
<tr>
<td>
<table width="884" align="center">
              <tr>
                <td height="675"><h1 align="left" class="style73">Bankruptcies, Judgments &amp; Tax Liens                      
                      </h1>
                    <div align="left" class="style19">
                      <p>Savvy investigators know that a  background search  isn't complete without this search.</p>
                      <p>Does an individual or business have a state or federal tax lien against them? Have they filed for bankruptcy? Do they owe money from civil judgments? To whom do they owe money and how much? Will you be wasting your time pursuing a judgment? Should you do business with them?</p>
                      <p>This simple $5 search will give you the answers you need and provide you with a clearer picture of your subject's  financial history. Search statewide or nationwide for the same low price.</p>
                      <table width="847" height="276" border="0" bordercolor="#000000">
                        <tr>
                          <td width="841" height="272"><table width="831">
                                <tr>
                                  <td width="147" height="51"><p><span class="style61">Search by:</span></p>
                                  </td>
                                  <td width="672"><table width="635">
                                    <tr>
                                      <td width="101" height="33">
                                      <a href="NewBJLSearch.do?searchTypes=name"><form:radiobutton path="bjlsearchByType" value="name"/></a>
                                        <span class="style126">Name</span></td>
                                      <td width="23">&nbsp;</td>
                                      <td width="495"><label>
                                        
                                        <a href="NewBJLSearch.do?searchTypes=ssn"><form:radiobutton path="bjlsearchByType" value="ssn"/></a>
SSN or Tax ID Number</label></td>
                                    </tr>
                                  </table></td>
                                </tr>
                              </table>
                         
                              <c:choose>
                              <c:when test="${searchTypes eq 'name'}">
                              <table width="833" height="121" border="0">
                                <tr>
                                  <td width="485" height="117"><table width="475" border="0">
                                      <tr>
                                        <td width="212"><div align="left"><strong>Name<span class="style24">*</span>:</strong></div></td>
                                        <td width="245"><div align="left">
                                           
                                            <form:input path="bjlName" size="34" maxlength="34"/>
                                        </div></td>
                                      </tr>
                                      <tr>
                                        <td height="27"><div align="left"><span class="style113">*Required Field</span></div></td>
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
                                  </table></td>
                                  <td width="336"><table width="328">
                                    <tr>
                                      <td><span class="style131">For best results, use the first and last name only and select a state.</span></td>
                                    </tr>
                                    <tr>
                                      <td><span class="style131">Keep the name short! Don't use Jr., Sr., Inc., LLC, etc.</span></td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp;</td>
                                    </tr>
                                  </table>                                    </td>
                                </tr>
                              </table>
                            <table width="820" border="0">
                                <tr>
                                  <td width="213" height="82"><span class="style120">Search is limited to 1000 results</span></td>
                                  <td width="314"><a href="#"><img src="images/common/bt-start-search-small.png" alt="start search" width="138" height="48" /></a></td>
                                  <td width="279"><p><a href="#"><img src="images/common/Comodo-seal-85.gif" alt="comodo" width="85" height="51" /></a><a href="#"><img src="images/common/bbb-clickratingsm.gif" alt="bbb" width="135" height="52" /></a></p>                                      </td>
                                </tr>
                            </table>
                            </c:when>
                            <c:when test="${searchTypes eq 'ssn'}">
                         <table width="833" height="163" border="0">
                                <tr>
                                  <td width="485" height="159"><table width="475" border="0">
                                      <tr>
                                        <td width="212"><div align="left"><strong>SSN or Tax ID<span class="style24">*</span>:</strong></div></td>
                                        <td width="245"><div align="left">
                                           <form:input path="bjlSsnTaxId" size="18" maxlength="18" />
                                        </div></td>
                                      </tr>
                                      <tr>
                                        <td height="27"><div align="left"><span class="style113">*Required Field</span></div></td>
                                        <td><div align="left"><span class="style31">(No dashes or spaces)</span></div></td>
                                      </tr>
                                  </table>
                                  <p><span class="style131">CAUTION: Search results will only   include records that contain a Social Security Number or Tax ID. Since   judgments aren't normally referenced by one of these numbers, then you   WILL NOT find judgments here.</span></p></td>
                                  <td width="336"><table width="328" height="105">
                                    <tr>
                                      <td height="26" class="style131">Also, if a bankruptcy or tax lien record   does not contain a Social Security or Tax ID Number, then it will not   appear in your results.</td>
                                    </tr>
                                    <tr>
                                      <td height="44"><span class="style131">BEST results are found on the Search By Name   page.</span></td>
                                    </tr>
                                    
                                  </table>                                    </td>
                                </tr>
                              </table>
                            <table width="820" border="0">
                                <tr>
                                  <td width="213" height="82"><span class="style120">Search is limited to 1000 results</span></td>
                                  <td width="314"><a href="#"><img src="images/common/bt-start-search-small.png" alt="start search" width="138" height="48" /></a></td>
                                  <td width="279"><p><a href="#"><img src="images/common/Comodo-seal-85.gif" alt="comodo" width="85" height="51" /></a><a href="#"><img src="images/common/bbb-clickratingsm.gif" alt="bbb" width="135" height="52" /></a></p>                                      </td>
                                </tr>
                            </table>
                            </c:when>
                              </c:choose>
                            </td>
                        </tr>
                      </table>
                      <table width="852">
                        <tr>
                          <td width="844" height="158"><span class="style15">All information retrieved from or through SearchSystems.net must be utilized in accordance  with the User Agreement and all applicable state and federal laws, including the <a href="http://www.ftc.gov/os/statutes/fcra.htm">Fair Credit Reporting Act</a>;  any violation of these will be grounds for immediate termination of your account without  notice. <br />
                                <br />
                                <strong>DISCLAIMER:</strong> No warranty of any type is provided as to the quality or accuracy of the  information obtained from or through SearchSystems.net, and any reliance on that  information is solely at your own risk and responsibility.  Information contained herein  is derived solely from public records, which may not be 100 percent accurate or complete.  Users should not assume that this data provides a complete or accurate record of any  person's bankruptcy, judgment, or tax lien history.  Please see SearchSystems' <a href="https://premium.searchsystems.net/agreement.php">User Agreement</a> for all applicable terms and conditions. <br />
                                <br />
                            Pacific Information Resources, Inc. dba Search Systems assumes no liability for any claims  for damages arising from the use of this data beyond the actual cost of the searches  performed. </span></td>
                        </tr>
                      </table>
                    </div></td>
              </tr>
            </table>
            
</td></tr>
</form:form>
</jsp:body>
</neon:NewFunnel>