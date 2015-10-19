<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRBGCcommonPage title="details">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

	<tr>
		<td width="950" style="height:100%; padding: 10px 0;"><table width="893" border="0">
          <tr>
            <td width="896" valign="top"><table width="891" height="76" border="0">
                <tr>
                  <td width="692" height="72"><table width="693" align="left">
                      <tr>
                        <td height="53"><h2 class="style76">Nationwide Criminal Record Result Details</h2>                        </td>
                      </tr>
                  </table></td>
                  <td width="189"><div align="right"><a href="BGCsearchLanding.do" class="style27">Search Again</a></div></td>
                </tr>
              </table>
                <table width="890" border="0">
                  <tr>
                    <td width="697" height="90"><p class="style27"><strong>Name Searched:</strong> <span class="style117a">${FRBGCRequest.bgcFirstName} ${FRBGCRequest.bgcLastName }</span></p>
                        <p class="style27"><strong>Date of Birth:</strong> <span class="style117a">${FRBGCRequest.bgcDobMonth}-${FRBGCRequest.bgcDobDay}-${FRBGCRequest.bgcDobYear}</span></p>
                    </td>
                  </tr>
                </table>
                       
                
         <c:forEach items="${bgcList}" var="bg">
                      
              <table width="603">
                  <tr>
                    <td><h4 class="style27">Offender Info</h4>
                        <table width="603" border="0">
                          <tr>
                            <td width="150" bgcolor="#FFFFFF"><p class="style41">Full Name</p>
                                <p class="style74b">${bg.firstName}, ${bg.lastName} ${bg.middleName}</p></td>
                            <td width="150" bgcolor="#FFFFFF"><p class="style41">DOB</p>
                                <p class="style74b">${bg.dob}</p></td>
                            <td width="150" bgcolor="#FFFFFF"><p class="style41"></p>
                                <p class="style74b"> </p></td>
                            <td width="150" bgcolor="#FFFFFF"><div align="left">
                            	<span class="style31"><span class="style27"></span></span></div></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Address</p>
                                <p class="style74b">${bg.address} ${bg.address2}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">City</p>
                                <p class="style74b">${bg.city}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">State</p>
                                <p class="style74b">${bg.state}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Postal Code</p>
                                <p class="style74b">${bg.zip}</p></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Gender</p>
                                <p class="style74b">${bg.sex}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Race</p>
                                <p class="style74b">${bg.race}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">County</p>
                                <p class="style74b">${bg.county}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Country</p>
                                <p class="style74b"> </p></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Height</p>
                                <p class="style74b">${bg.height}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Weight</p>
                                <p class="style74b">${bg.weight}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Eye Color</p>
                                <p class="style74b">${bg.eyeColor}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Hair Color</p>
                                <p class="style74b">${bg.hairColor}</p></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF"><p class="style41">Record Source</p>
                                <p class="style74b">${bg.sourceName}</p></td>
                            <td bgcolor="#FFFFFF"><p class="style41">Record State</p>
                                <p class="style74b">${bg.sourceState}</p></td>
                            <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                            <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                          </tr>
                      </table></td>
                  </tr>
                  <tr>
                    <td> <br> <h4 class="style27">Aliases</h4>
                        <table width="491" border="0">
                        <tr>
                        	<c:if test="${empty bg.aliases}">
                        	 	<td bgcolor="#FFFEB1"><div align="left"><span class="style74b">No Aliases Found</span></div></td>
                        	</c:if>
                        	<c:if test="${!empty bg.aliases}">
                        	 	<td bgcolor="#FFFEB1"><div align="left"><span class="style74b">${bg.aliases}</span></div></td>
                        	</c:if>
                        </tr>
                        </table>
                      <p>&nbsp;</p></td>
                  </tr>
                  <tr>
                    <td><h4 class="style27">Offenses</h4>
                        <p class="style41 style71b">Result #1</p>
                      <table width="554" border="0">
                          <tr>
                            <td width="127" bgcolor="#FFFFFF"><div align="left" class="style41">Description</div></td>
                            <td width="417" bgcolor="#FFFFFF"><div align="left"> <span class="style74b">${bg.offensedescription1} ${bg.offensedescription2}</span> </div></td>
                          </tr>
                          
                     <c:if test="${!empty bg.offenseCode}">     
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Offense Code</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"> <span class="style74b">${bg.offenseCode} </span> </div></td>
                          </tr>
                     </c:if> 
                      <c:if test="${!empty bg.arrestingAgency}">   
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Arresting Agency</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"> <span class="style74b">${bg.arrestingAgency}</span> </div></td>
                          </tr>
                      </c:if>
                      <c:if test="${!empty bg.disposition}"> 
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Disposition</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"> <span class="style74b">${bg.disposition}</span> </div></td>
                          </tr>
                      </c:if>
                      <c:if test="${!empty bg.dispositiondate}">
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Disposition Date</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"> <span class="style74b">${bg.dispositiondate}</span> </div></td>
                          </tr>
                      </c:if>
                      <c:if test="${!empty bg.sentenceYYYMMDDD}">
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Sentence Date</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"><span class="style74b">${bg.sentenceYYYMMDDD}</span></div></td>
                          </tr>
                      </c:if>
                      <c:if test="${!empty bg.probationYYYMMDDD}">
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Probation</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"><span class="style74b">${bg.probationYYYMMDDD}</span></div></td>
                          </tr>
                      </c:if>
                      <c:if test="${!empty bg.offenseDate}">
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Offense Date</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"><span class="style74b">${bg.offenseDate}</span></div></td>
                          </tr>
                      </c:if>
                      <c:if test="${!empty bg.fines}">
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Fine</div></td>
                            <td bgcolor="#FFFFFF"><div align="left">
                                <p><span class="style74b">${bg.fines}</span></p>
                            </div></td>
                          </tr>
                      </c:if>
                      <c:if test="${!empty bg.plea}">
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Original Plea</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"> <span class="style74b">${bg.plea}</span> </div></td>
                          </tr>
                      </c:if>
                      <c:if test="${!empty bg.casenumber}">
                          <tr>
                            <td bgcolor="#FFFFFF"><div align="left" class="style41">Case Number</div></td>
                            <td bgcolor="#FFFFFF"><div align="left"> <span class="style74b">${bg.casenumber}</span> </div></td>
                          </tr>
                      </c:if>
                        </table>
                      <p>&nbsp;</p>
                      
                      </td>
                  </tr>
                </table>
              <p>&nbsp;</p>
              <hr />
              
         </c:forEach>     
              
              
              
              
              
              
              
              
              
                <table width="891">
                  <tr>
                    <td height="500">
                      <table width="829" border="0" align="center">
                          <tr>
                            <td height="56"><p class="style27">If you need more information about a criminal case type based on the offense code from your results, <a href="CaseType.do" target="blank">click here</a> for links to state penal code sites.</p></td>
                          </tr>
                          <tr>
                            <td width="648" height="36"><p class="style27">To see the data sources and coverage for our criminal records search, <a href="BGCDatasource.do" target="blank">click here</a>.</p></td>
                          </tr>
                          <tr>
                            <td height="36"><span class="style27">Take advantage of our free <a href="http://www.searchsystems.net/free-criminal-records-search-guide.php">Search Systems Guide to Criminal Records on the Internet</a> to help you find the information you need.  Our guide lists the databases contained in our       database, but also includes a complete description of our <a href="http://www.searchsystems.net/list.php?nid=494">Criminal Records Directory</a> which contains thousands of links to criminal court records, "wanted" lists and databases,       warrants, arrests, bookings, inmate, parole, and sex offender databases, as well as links to       information on how to order criminal histories from state agencies - all organized nationwide       and by state and category so that they are easy to find. </span></td>
                          </tr>
                        </table>
                      <p>&nbsp;</p>
                      <table width="834" border="1" align="center">
                          <tr>
<td><span class="style74b"><strong>DISCLAIMER</strong><br />
                              SearchSystems.net provides no warranty of any type as to the accuracy of this information,  and any reliance on this information is solely at your own risk and responsibility.  All  information retrieved from or through SearchSystems.net must be utilized in accordance with  the User Agreement and all applicable state and federal laws. <br />
                              <br />
                              Copyright © 2003-2008 Pacific Information Resources, Inc. All rights reserved. </span></td>
                          </tr>
                      </table></td>
                  </tr>
              </table>
              
              
              </td>
          </tr>
        </table>
	  </td>
	</tr>

</jsp:body>
</neon:FRBGCcommonPage>
