<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:NewCrimeFunnel title="Criminal Records Search">
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css,style_02.css</jsp:attribute>
<jsp:body>
<script type="text/javascript">
	
function shownote(a, b, c){
	document.getElementById(a).style.display = "block";
	document.getElementById(b).style.display = "none";
	document.getElementById(c).style.display = "none";
}
	function getstat(){
		var stat = document.crimeForm.bgcState.value;
		if(stat == "all"){
			document.crimeForm.bgcMatchMissingDates.checked = false;
		}
		
		else if(stat == "MA" || stat == "SD" || stat == "WV"){
			window.location.href= "http://publicrecords.searchsystems.net/criminal-records-state-help.php";
		}
		else{
			document.crimeForm.bgcMatchMissingDates.checked = false;
		}
	}
</script>
<style type="text/css">

.formfield1
{
	height:23px;
}
.formfield1 option
{
	margin:0;padding:0;
}
	
.style59 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
}

.style71 {font-family: Arial, Helvetica, sans-serif;color:#black!important;paddibg:0!important;margin:0!important;}
.style61 {font-family: Arial, Helvetica, sans-serif; font-size: 12px;color:#000;paddibg:0!important;margin:0!important;}
.style69 {font-family: Arial, Helvetica, sans-serif; font-size: 12px;color:#000!important;paddibg:0!important;margin:0!important;}
.style72 {color: #FF0000}
.style75 {font-size: 14px}
.style76 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14;
}
.style77 {font-size: 14}
.style81 {color: #003366!important;margin:0;padding:0}
.style83 {
	font-family: Arial, Helvetica, sans-serif;
	color: #006633;
	font-weight: bold;
}
.style86 {font-size: 12px}
.style88 {color: #000000}
.style90 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FF0000; }
#button2
{
	font-size:14px;
}
.buttonDiv
{
	padding-bottom:10px;
	padding-top:3px;
}

</style>
  <form:form name="crimeForm" method="post">
    <form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top"><td >
<table width="892">
          <tr valign="top">
            <td width="642" ><h1 class="style59">Personal Criminal Records  Search</h1>
              <p class="style81 style71"><strong>Who can you trust? What's on your record? Find your answers  with a personal  criminal record search  using the largest, most powerful  database available  to the public. Includes  prison and probation records,  court records of felony and misdemeanor convictions, traffic records, and sex offenders.</strong></p>
              
              <p class="style81 style71"><strong>Fill out the form and get instant results. For background screening, business, employment, or tenant purposes, click on the Business Search tab.</strong></p>
              <p style="padding:0px;margin:0">&nbsp;</p>
			<c:choose>
            
              <c:when test="${bgcSearchType eq 'bgcsearch'}">
             <table width="609" border="0" cellpadding="0" cellspacing="0">
                <tr valign="top">
                   <td width="601">
                   <table width="595" border="0" cellpadding="0" cellspacing="0">
                      	<tr valign="top">
                        	<td width="182" height="37" bgcolor="#B4DDFE"><p class="style71" style="margin:0;padding:0 0 0 12px"><strong style="color:black">Personal Search</strong></p></td>
                        	<td width="170" bgcolor="#FFFFFF">&nbsp;</td>
                        	<td width="227" bgcolor="#FFFF99" class="style71"><div align="right"><a href="/springapp/funnel/NewBusinessSearch.do"><strong>Business Search</strong></a></div></td>
                      	</tr>
                    </table>
                   
                    <table width="595" bgcolor="#B4DDFE">
                        <tr valign="top">
                          <td height="0">
                          <table width="576" border="0" cellspacing="0" cellpadding="0" bgcolor="#B4DDFE">
                            <tr>
                              <th width="300" scope="col"><label>
                               <a href="newSearch.do?bgcSearchType=bgcsearch"> 
                                <form:radiobutton path="bgcpersonalsearchtype" value="bgcsearch"/></a>
                              </label>
                                <span class="style61">Search</span>
                                <label>
                                <a href="newSearch.do?bgcSearchType=bgcadvancedsearch"> 
                                <form:radiobutton path="bgcpersonalsearchtype" value="bgcadvancedsearch"/></a>
                                </label>
                                <span class="style61">Advanced Search</span></th>
                              	<th width="276" scope="col"><strong class="style72">*</strong><span class="style90"> Required Field</span></th>
                            	</tr>
                                <tr><td colspan="2"><p class="style69">Click on Advanced Search for <strong>POWERFUL</strong> search features like searching by partial name, year of birth, age range, or opt to include records that do not have a date of birth.</p></td></tr>
               				</table>
                          </td>
                        </tr>
                        
                        <tr>
                          <td><span class="style71"><label>First Name<strong class="style72">*</strong>:</label>
                          <form:input path="bgcFirstName" cssClass="formfield" size="20" maxlength="20" />
                            <span class="style61">To include a middle initial, see options below</span></span></td>
                        </tr>
                        <tr>
                          <td><span class="style71">
                            <label>Last Name<strong class="style72">*</strong>:</label>
                            <form:input path="bgcLastName" cssClass="formfield" size="20" maxlength="20" />
                            <span class="style61">Please DO NOT  include suffixes such as Jr., Sr., III, MD</span></span></td>
                        </tr>
                        <tr>
                          <td><span class="style71">
                            <label>Middle Initial:</label>
                            <form:input path="bgcMiddleInitial" cssClass="formfield" size="2" maxlength="1" />
                            <span class="style61">(optional)</span></span></td>
                        </tr>
                        
                        <tr>
                          <td><span class="style71">
                            <label>Date of Birth<strong class="style72">*</strong>:</label>
                     		<form:select path="bgcDobMonth" cssClass="formfield1">
							<form:option value="0">Month</form:option>
							<form:options items="${dobMonths}"/>
							</form:select>&nbsp;
							
                            <form:select path="bgcDobDay" cssClass="formfield1">
							<form:option value="0">Day</form:option>
							<form:options items="${dobDays}"/>
							</form:select>&nbsp;
                            
                            <form:select path="bgcDobYear" cssClass="formfield1">
							<form:option value="0">Year</form:option>
							<form:options items="${dobYears}"/>
							</form:select>

                          </span></td>
                        </tr>
                        <tr>
                          <td><span class="style71">
                            <label>Search Area<strong class="style72">*</strong>:</label>
                            <form:select path="bgcState" size="1"  cssClass="formfield1" onchange="getstat();">
                            <form:option value="all">Nationwide</form:option>
							<form:options items="${usStates}"/>
                            </form:select>
                            <span class="style86">Price: <strong>$14.95</strong> Nationwide or <strong>$9.95</strong> Statewide</span></span></td>
                        </tr>
                        
                        
                        <tr>
                          <td class="style71">
                            
                            <div align="center" class="buttonDiv">
                             <input type="submit" name="button2" id="button2" value="Proceed">
                              
                            </div></td>
                        </tr>
                      </table>
                      
                      <p class="style61">By clicking the  button above, you acknowlege your understanding that SearchSystems.net does not represent or warrant that the results provided   will be 100% accurate and up to date. Our criminal records database contains information from    publicly available sources. We do not  make any   representation or warranty as to the character or integrity of the   person  that is the subject of your search  through our service. Information retrieved from a Personal Search  should not be used for  any FCRA related business, employment, or tenant screening purpose.</p>
                      
                      <p class="style61">Check our <a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do">COVERAGE PAGE</a> to see       which jurisdictions, types of records, and date ranges are provided in each State. Results may include traffic violations, in addition to criminal   felony, misdemeanor, and sex offender records.</p>
                  
                      <p class="style61">All information retrieved from or through SearchSystems.net must be   utilized   in accordance with the <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=funnel" target="_blank">User Agreement</a> and all applicable state and federal   laws. Please see our <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">disclaimer</a>.</p>
                    </td>
                  </tr>
                </table>
              </c:when>
             <c:when test="${bgcSearchType eq 'bgcadvancedsearch'}">
                <table width="609" border="0">
                  <tr valign="top">
                    <td width="601">
                    <table width="595" border="0">
                      <tr>
                        <td width="182" bgcolor="#B4DDFE"><p class="style71" style="margin:0;padding:0 0 0 12px;color:black"><strong>Personal Search</strong></p></td>
                        <td width="170" bgcolor="#FFFFFF">&nbsp;</td>
                        <td width="227" bgcolor="#FFFF99" class="style71"><div align="right"><a href="/springapp/funnel/NewBusinessSearch.do"><strong>Business Search</strong></a></div></td>
                      </tr>
                    </table>
                      <table width="595" bgcolor="#B4DDFE">
                        <tr>
                          <td height="0"> 
                          <table width="576" border="0" cellspacing="0" cellpadding="0" bgcolor="#B4DDFE">
                            <tr>
                              <th width="300" scope="col"><label>
                               <a href="newSearch.do?bgcSearchType=bgcsearch"> 
                                <form:radiobutton path="bgcpersonalsearchtype" value="bgcsearch"/></a>
                              </label>
                                <span class="style61">Search</span>
                                <label>
                                <a href="newSearch.do?bgcSearchType=bgcadvancedsearch"> 
                                <form:radiobutton path="bgcpersonalsearchtype" value="bgcadvancedsearch"/></a>
                                </label>
                                <span class="style61">Advanced Search</span></th>
                              	<th width="276" scope="col"><strong class="style72">*</strong><span class="style90"> Required Field</span></th>
                            	</tr>
               				</table></td>
                        </tr>
                        <tr>
                          <td><span class="style71"><label>First Name<strong class="style72">*</strong>:</label>
                           <form:input path="bgcFirstName" cssClass="formfield" size="20" maxlength="20" />
                            </span><span class="style71">
                            <form:checkbox path="bgcFirstNameExact" cssClass="formfield" />
                            
                            </span><span class="style61">Exact Match</span></td>
                        </tr>
                        <tr>
                          <td><span class="style71">
                            <label>Last Name<strong class="style72">*</strong>:</label>
                            <form:input path="bgcLastName" cssClass="formfield" size="20" maxlength="20" />
                          </span><span class="style71">
                      <form:checkbox path="bgcLastNameExact" cssClass="formfield" />
                          
                          </span><span class="style61">Exact Match</span></td>
                        </tr>
                        <tr>
                          <td><span class="style71">
                            <label>Middle Initial:</label>
                            <form:input path="bgcMiddleInitial" cssClass="formfield" size="2" maxlength="1" />
                            <span class="style61">(optional)</span></span></td>
                        </tr>
                        <tr>
                          <td><p class="style61" style="padding:0 0 0 12px;margin:0;color:black;">Please DO NOT  include suffixes such as Jr., Sr., III, M.D., and PhD in the  name  fields.</p>
                          <p class="style61" style="padding:0 0 0 12px;margin:0;color:black;"> If you'd like to include a middle initial, see &quot;Additional  Options&quot; below. </p></td>
                        </tr>
                        <tr>
                          <td height="44"><span class="style71">
                            <label>Search Area<strong class="style72">*</strong>:</label>
                            <form:select path="bgcState" size="1"  cssClass="formfield1" onchange="getstat();">
                            <form:option value="all">Nationwide</form:option>
							<form:options items="${usStates}"/>
                            </form:select>
                            
                            <span class="style86">Price: <strong>$14.95</strong> Nationwide or <strong>$9.95</strong> Statewide</span></span></td>
                        </tr>
                        <tr>
                          <td height="30"><span class="style71"><strong class="style71">Options - </strong>Search by date of birth <strong>OR</strong> year of birth (choose one):</span></td>
                        </tr>
                        <tr>
                          <td height="39">
                            <span class="style71">
                            <label>Date of   Birth<strong class="style72">*</strong>:</label>
                            <form:radiobutton path="bgcDobRange" value="false" />
     						<form:select path="bgcDobMonth" cssClass="formfield1">
							<form:option value="0">Month</form:option>
							<form:options items="${dobMonths}"/>
							</form:select>&nbsp;

                            <form:select path="bgcDobDay" cssClass="formfield1">
							<form:option value="0">Day</form:option>
							<form:options items="${dobDays}"/>
							</form:select>&nbsp; 
                            <form:select path="bgcDobYear" cssClass="formfield1">
							<form:option value="0">Year</form:option>
							<form:options items="${dobYears}"/>
							</form:select>
                          </span></td>
                        </tr>
                        <tr>
                          <td><span class="style71">
                            <label>Year of   Birth<strong class="style72">*</strong>:</label>
                            <form:radiobutton path="bgcDobRange" value="true" />
                            <form:select path="bgcDobRangeBaseYear" cssClass="formfield1">
							<form:option value="0">Year</form:option>
							<form:options items="${dobYears}"/>
							</form:select>

							within
						<form:select path="bgcDobRangeFuzz" cssClass="formfield1">
							<form:option value="0">0</form:option>
							<form:option value="1">1</form:option>
							<form:option value="2">2</form:option>
							<form:option value="3">3</form:option>
						</form:select>
							years 
								</span>
							</td>
                        </tr>
                        <tr>
                          <td class="style71">
                                                    
                            <p style="padding:0 0 5px 12px;margin:0;">
                              
                              <form:checkbox path="bgcMatchMissingDates" cssClass="formfield" />
                              <label for="bgcMatchMissingDates" class="style71">Include records where the  date of birth is unavailable or incomplete</label>
                            </p>
                           
                            <p class="style61" style="padding:0 0 0 12px;margin:0;color:black;">Not every reporting agency provides a full date of birth on every record. This feature gives you those records as well.  CAUTION: These additional results may or may not be for your person because there is not an exact date of birth match.<label for="label"></label>
                            </p>
                           
                            <p align="center" class="style61">
                              <input type="submit" name="button2" id="button2" value="Proceed" style="font-size:14px;">
                            </p></td>
                        </tr>
                      </table>
                     
                      <p class="style61">By clicking the  button above, you acknowlege your understanding that SearchSystems.net does not represent or warrant that the results provided   will be 100% accurate and up to date. Our criminal records database contains information from    publicly available sources. We do not  make any   representation or warranty as to the character or integrity of the   person  that is the subject of your search  through our service. Information retrieved from a Personal Search  should not be used for  any FCRA related business, employment, or tenant screening purpose.</p>
                     
                      <p class="style61">Check our <a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do">COVERAGE PAGE</a> to see       which jurisdictions, types of records, and date ranges are provided in each State. Results may include traffic violations, in addition to criminal   felony, misdemeanor, and sex offender records.</p>
                      
                      <p class="style61">All information retrieved from or through SearchSystems.net must be   utilized   in accordance with the <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=funnel" target="_blank">User Agreement</a> and all applicable state and federal   laws. Please see our <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">disclaimer</a>.</p>
                    </td>
                  </tr>
                </table>
             </c:when> 
             </c:choose>
            
              </td>
            <td width="238"><p class="style71"><img src="/springapp/images/crimefunnel/InsideEdition-banner.png" alt="Inside Edition" width="229" height="54"></p>
              <p><iframe width="300" height="315" src="http://www.youtube.com/embed/xKOhd6nccPs" frameborder="0" allowfullscreen></iframe></p>
              <p align="center" class="style71"><span class="style75"><span class="style77"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do">Database Coverage</a></span></span></p>
             
            <p align="center" class="style61"><span class="style75"><a href="http://publicrecords.searchsystems.net/free-criminal-guide-best-results.html">Guide to Best Results</a></span></p>
           
            <p align="center" class="style61"><a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/" class="style75">Free Criminal Records</a></p>
           
            <p align="center" class="style61"><a href="http://www.searchsystems.net/crim-premium-faq.html" class="style75">FAQ</a></p>
            <p class="style71">&nbsp;</p>
            <p class="style83">&nbsp;</p>
            <p class="style83">&nbsp;</p></td>
          </tr>
        </table>	

</td></tr>
</form:form>
</jsp:body>
</neon:NewCrimeFunnel>