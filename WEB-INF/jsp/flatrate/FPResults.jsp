<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRFPcommonPage title="find people details">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

	<tr>
		<td width="893" style="height:100%; padding: 10px 0;"><table width="893" height="511" border="0">
          <tr>
            <td width="896" height="507" valign="top"><table width="893" height="86" border="0">
<tr>
                    <td width="513" height="82"><p>&nbsp;</p>
                      <table width="451" align="center">
                      <tr>
                        <td><h1><span class="style32">Results</span></h1>
                        </td>
                      </tr>
                    </table>                    
                </td>
<td width="370"><div align="right">

   <form:form method="post" action="FPsearchLanding.do">
                        <table width="351" border="0">
                          <tr>
                            <td width="124"><div align="left" class="style23 style27"><strong>First Name <span class="style24">*</span></strong></div></td>
                            <td width="100"><div align="left" class="style23 style27"><strong>Last Name <span class="style24">*</span></strong></div></td>
                            <td width="147"><div align="left" class="style23 style27"><strong>State</strong></div></td>
                          </tr>
                          <tr>
                            <td><div align="left">
                                <input type="text" name="searchFirstName" />
                            </div></td>
                            <td><div align="left">
                                <input type="text" name="searchLastName" />
                            </div></td>
                            <td><div align="left">
                                <form:select path="searchState" cssClass="formfield">
									<form:options items="${searchState}"/>		
								</form:select>
                            </div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><div align="right"><input type="image" src="/springapp/images/flatrate/bt-search-again-xsmall.png" alt="search again" width="109" height="32" border="0" /></div></td>
                          </tr>
                        </table>
     </form:form>                   
                        
                    </div></td>
              </tr>
                </table>
              <table width="883" align="center">
                <tr>
                  <td height="93"><p><span class="style42">Here are the result details you requested. </span></p>
                  <p>&nbsp;</p>
                  <p><span class="style42">Need more information? For an additional fee, SearchSystems.net can provide you with a detailed Background Report. Scroll down for instructions on how to place an order</span>.</p></td>
                </tr>
              </table>
              
    <form method="post" action="getBGResult.do">         
    
    <input type="hidden" name="searchFirstName" value="${sfpfc.searchFirstName}">
    <input type="hidden" name="searchMidName" value="${sfpfc.searchMidName}">
    <input type="hidden" name="searchLastName" value="${sfpfc.searchLastName}">
    <input type="hidden" name="searchDob" value="${sfpfc.searchDob}">
    <input type="hidden" name="searchCity" value="${sfpfc.searchCity}">
    <input type="hidden" name="searchState" value="${sfpfc.searchState}">
     
              <table width="893" height="0" border="0">
                <tr>
                  <td width="813" height="0"><table width="887" border="0" bordercolor="#999999">
                      <tr>
                        <td width="25" height="24">&nbsp;</td>
                        <td width="124"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Name</strong></span></div>
                        </div></td>
                        <td width="118"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Date Reported</strong></span></div>
                        </div></td>
                        <td width="176"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Address</strong></span></div>
                        </div></td>
                        <td width="193"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Location</strong></span></div>
                        </div></td>
                        <td width="113"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Phone</strong></span></div>
                        </div></td>
                        <td width="108"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Date of Birth</strong></span></div>
                        </div></td>
                      </tr>
                      
             <c:forEach items="${fpList}" var="fp">         
                      <tr>
                        <td><div align="left">
                            <input type="radio" name="key" id="radio5" value="${fp.personID}" checked="checked">
                        </div></td>
                        <td><div align="center" class="style47a">
                            <p align="left">${fp.firstName} ${fp.middleName} ${fp.lastName}</p>
                        </div></td>
                        <td><div align="center" class="style47a">
                            <p align="left">${fp.validityDate}</p>
                        </div></td>
                        <td><div align="center" class="style47a">
                            <div align="left">${fp.address}</div>
                        </div></td>
                        <td><div align="center" class="style47a">
                            <p align="left">${fp.city}, ${fp.state}</p>
                        </div></td>
                        <td><div align="left"><span class="style27"><span class="style31">${fp.phone}</span></span></div></td>
                        <td><div align="center" class="style47a">
                            <div align="left">${fp.dob}</div>
                        </div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      
             </c:forEach> 
                      
                    </table>
                    <p>&nbsp;</p>
                  <p><span class="style27">Need more help finding people? Check out our <a href="/springapp/funnel/Find-People-Guide.html" target="blank">Free Guide</a>.</span></p></td>
                </tr>
              </table>
             <p style="">&nbsp;</p>
              <table width="844" border="2" align="center" bordercolor="#000000">
                <tr>
                  <td width="840" height="221"><h4 class="style32">Need more information? Select a record and click on the button below to do a background search. This is an additional purchase outside of your Flat Rate plan.</h4>
                    <h4 class="style32"><span class="style32">Important! For best results, select a record with a full date of birth.</span></h4>
                    <h4 class="style32">Background Report  includes:</h4>
                    <table width="769" align="center">
                      <tr>
                        <td width="479" height="156">
                           <img src="/springapp/images/flatrate/BG-bullets.png" alt="background bullets" width="461" height="152">                      
                        </td>
                        <td width="278"><p><input type="image" src="/springapp/images/flatrate/bt-background-995.png" alt="background report 14.95" width="276" height="50"/></p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p align="center" class="style27"><a href="/springapp/findpeople/showStaticPages.do?page=BGsamplereport" target="blank">View sample Background Report</a></p></td>
                      </tr>
                    </table>
                    </td>
                </tr>
              </table>
     </form>         
              
              <p>&nbsp;</p>
            </td>
          </tr>
        </table>
         </td>
	</tr>

    
</jsp:body>

</neon:FRFPcommonPage>
								 