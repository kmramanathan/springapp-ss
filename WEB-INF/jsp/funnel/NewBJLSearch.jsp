<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<neon:NewBJLFunnel title="Bankruptcies, Judgments & Tax Liens">
<jsp:attribute name="stylesheet">new-bjl.css</jsp:attribute>

<jsp:body>
<style type="text/css">

.style19 {font-family: Arial, Helvetica, sans-serif}
.style20 {font-size: small}
.style73 {	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight: bold;
	margin: 0;
	padding: 0;
}
.style15 {font-size: 0.8em}
.style24 {color: #FF0000}
.style31 {font-size: x-small}
.style61 {font-size: 18px;
	font-weight: bold;
}
.style113 {color: #FF0000; font-size: 10px; }
.style118 {color: #FFFFFF}
.style120 {color: #000000; font-size: 12px;}
.style126 {font-size: 0.9em;text-decoration: none;}
.style131 {color: #FF0000; font-size: 12px; font-weight: bold; }
.navigation-table a{text-decoration: none;}
.style819{margin:0;padding:0;font-size: 12px; color: #000!important;}
.parastyle{
	margin: 0;
	padding: 5px 0 5px 0;
}
.paralast{padding-bottom: 0;}
.formfield{
  padding: 0;
  margin: 0;
}
.formfield option{padding: 0;
  margin: 0;}
#datediv{display:none;color:red;position:absolute;width:350px;text-align:justify;font-size: 12px;margin-left:30px;margin-top:103px}
#refdiv{display:none;color:red;position:absolute;width:350px;text-align:justify;font-size: 12px;margin-left:30px;margin-top:90px}
#refdiv1{display:none;color:red;position:absolute;width:350px;text-align:justify;font-size: 12px;margin-left:30px;margin-top:90px}
  @-moz-document url-prefix() { 
  #refdiv, #refdiv1, #datediv{
     margin-left:30px;
	 margin-top:90px;
  }
 
}
@media screen and (-webkit-min-device-pixel-ratio:0) {
  #refdiv, #datediv, #refdiv1{
     margin-left:30px;
	 margin-top:90px;
  }
  
}
#datediv{ 
    margin-left:-30px\9;
	*margin-left:-30px;
	_margin-left:-30px\9;
	margin-top:145px\9;
	*margin-top:145px;
	_margin-top:145px;
}
#refdiv, #refdiv1 {
   margin-left:-30px\9;
	*margin-left:-30px;
	_margin-left:-30px;
	margin-top:115px\9;
	*margin-top:115px;
	_margin-top:115px;
 }
</style>

<script type="text/javascript" src="/springapp/js/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#bjlsearchByType2").click(function()
		{
			$('input:radio[name=bjlsearchByType]:nth(1)').attr('checked',true);
			window.location.replace("newSearchBJL.do?searchType=businessname");
		});
	$("#bjlsearchByType1").click(function()
		{
			$('input:radio[name=bjlsearchByType]:nth(0)').attr('checked',true);
			window.location.replace("newSearchBJL.do?searchType=individualname");
		});
	$("#bjlsearchByType3").click(function()
		{
			$('input:radio[name=bjlsearchByType]:nth(2)').attr('checked',true);
			window.location.replace("newSearchBJL.do?searchType=ssn");
		});
});

</script>

<form:form method="post">
<form:hidden path="bjlSearchType"/>
<tr>
<td>
<table width="884" align="center" border="0" cellpadding="0" cellspacing="0">
			<tr valign="top">
			<td width="440">
				<h1 align="left" class="style73">Bankruptcies, Judgments &amp; Tax Liens   </h1>                      
			</td>
			<td width="200">
			<p >Price: <b>$5.00</b> Statewide or Nationwide <br/> 
			No signup or monthly fees.</p>
			</td>
			</tr>
			  <tr valign="top">			  
                <td height="675" colspan="2" valign="top">                	
                    <div align="left" class="style19">
                      <p class="parastyle">Savvy investigators know that a  background search  isn't complete without this search.</p>
                      <p class="parastyle">Does an individual or business have a state or federal tax lien against them? Have they filed for bankruptcy? Do they owe money from civil judgments? To whom do they owe money and how much? Will you be wasting your time pursuing a judgment? Should you do business with them?</p>
                      <p class="parastyle paralast">This simple $5 search will give you the answers you need and provide you with a clearer picture of your subject's  financial history. Search statewide or nationwide for the same low price.</p>
                      <table width="847" border="0" bordercolor="#000000" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="841">
                          <table width="831" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td width="147"><p class="parastyle"><span class="style61">Search by:</span></p>
                                  </td>
                                  <td width="672">
                                  	<table width="635" class="navigation-table" border="0" cellpadding="0" cellspacing="0">
                                    	<tr>
                                      		<td width="301">
                                      			<a href="newSearchBJL.do?searchType=individualname"><form:radiobutton path="bjlsearchByType" value="individualname"/></a><span class="style126">Individual Name</span>
                                        	</td>
                                      		<td width="300"> 
                                      			<a href="newSearchBJL.do?searchType=businessname"><form:radiobutton path="bjlsearchByType" value="businessname"/></a><span class="style126">Business Name</span>
                                        	</td>
                                      		<td width="495">
                                        		<a href="newSearchBJL.do?searchType=ssn"><form:radiobutton path="bjlsearchByType" value="ssn"/></a><span class="style126">SSN or Tax ID Number</span>
                                        	</td>
                                        </tr>
                                  	  </table>
                                   </td>
                                </tr>
                              </table>
                         
                              <c:choose>
                              <c:when test="${searchType eq 'individualname'}">
                            
                              <table width="833"  border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td width="70%">
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td width="120"><div align="left"><strong>Last Name:</strong></div></td>
                                        <td width="300"><div align="left">
                                           
                                            <form:input path="bjllastname" size="24" maxlength="34"/> &nbsp;<span class="style113">(Required - full)</span>
                                        </div></td>
                                      </tr>
                                      
                                       <tr>
                                        <td width="120"><div align="left"><strong>First Name:</strong></div></td>
                                        <td width="320"><div align="left">
                                           
                                            <form:input path="bjlfirstname" size="24" maxlength="34"/>&nbsp;<span class="style113">(Required - full or partial)</span>
                                        </div></td>
                                      </tr>
                                       <tr>
                                        <td width="120"><div align="left"><strong>Middle Initial:</strong></div></td>
                                        <td width="350"><div align="left">
                                           
                                            <form:input path="bjlmiddlename" size="1" maxlength="1"/>&nbsp;<span class="style113">(optional - best if used for common name searches)</span>
                                        </div></td>
                                      </tr>
                                      
                                      <tr>
                                        <td height="37"><div align="left"><strong>State:</strong></div></td>
                                        <td><div align="left">
                                              <form:select path="bjlstate" cssClass="formfield">
											<form:option value=" ">All States</form:option>
											<form:options items="${usStates}"/>
										</form:select>
                                        </div></td>
                                      </tr>
                                      <tr>
                                      	<td>
                                      	 	<div align="left"><strong>Reference:</strong></div> 
                                      	</td>
                                      	<td>
											<form:input path="bjlReference" size="20" maxlength="20" />
											<span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="18" height="23" id="refbtn"></span>
											<div id="refdiv" align="right">You can enter an optional reference code for your own tracking purposes. The code will appear on your credit card billing statement or monthly invoice. (NOTE: Reference code may not be available for some credit cards.)
											</div>
                                      	</td>
                                      </tr>
                                  </table>
                                  </td>
                                  <td width="250">
                                  <table width="250" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                      <td><span class="style131">For best results, use the first and last name only and select a state.</span></td>
                                    </tr>
                                    <tr>
                                      <td><span class="style131">Keep the name short! Don't use Jr., Sr., Inc., LLC, etc.</span></td>
                                    </tr>
                                    
                                  </table>                                    
                                  </td>
                                </tr>
                              </table>
                            <table width="820" border="0" cellpadding="0" cellspacing="0">
                                <tr valign="top">
                                	<td width="4%">&nbsp;</td>
                                  <td width="193" align="left">
                                  	<p class="style71 style819">
									<form:radiobutton path="newacc" value="true"/>
									<strong>I am a new customer</strong></p>
                                	<p class="style71 style819">
                   					<form:radiobutton path="newacc" value="false"/>
									<strong>I have an account</strong>
                                	</p></td>
                                  <td width="314"><input type="image" src="/springapp/funnel/images/common/bt-start-search-small.png" alt="start search" width="138" height="48" /></td>
                                  <td width="279"><p class="parastyle"><img src="/springapp/images/common/comodo-secure.png" alt="Comodo" width="113" height="59"><a href="#"><img src="images/common/bbb-clickratingsm.gif" alt="bbb" width="135" height="52" /></a></p>                                      </td>
                                </tr>
                                <tr valign="top"><td colspan="3"><a href="https://premium.searchsystems.net/bjl-sources.php" target="blank">Database Coverage</a></td>
                                </tr>
                                
                            </table>
                            </c:when>
                            <c:when test="${searchType eq 'businessname'}">
                          
                              <table width="833" border="0" cellpadding="0" cellspacing="0">
                                <tr valign="top">
                                  <td width="100%">
                                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td width="150"><div align="left"><strong>Business Name<span class="style24">*</span>:</strong></div></td>
                                        <td width="410"><div align="left">
                                           
                                            <form:input path="bjlBusinessName" size="30" maxlength="34"/> &nbsp;<span class="style113">*(Required - full or partial)</span>
                                        </div></td>
                                      </tr>
                                      
                                       
                                      <tr>
                                        <td height="37"><div align="left"><strong>State:</strong></div></td>
                                        <td><div align="left">
                                              <form:select path="bjlstate" cssClass="formfield">
											<form:option value=" ">All States</form:option>
											<form:options items="${usStates}"/>
										</form:select>
                                        </div></td>
                                      </tr>
                                      <tr>
                                      	<td>
                                      	 	<div align="left"><strong>Reference:</strong></div> 
                                      	</td>
                                      	<td>
											<form:input path="bjlReference" size="20" maxlength="20" />
											<span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="18" height="23" id="refbtn"></span>
											<div id="refdiv1" align="right">You can enter an optional reference code for your own tracking purposes. The code will appear on your credit card billing statement or monthly invoice. (NOTE: Reference code may not be available for some credit cards.)
											</div>
                                      	</td>
                                      </tr>
                                  </table></td>
                                  <td width="190"><table width="190" border="0">
                                    <tr>
                                      <td><span class="style131">Keep the name short! </span></td>
                                    </tr>
                                    <tr>
                                      <td><span class="style131">Don't use Inc., LLC, LLP, etc.</span></td>
                                    </tr>
                                    
                                  </table>                                    </td>
                                </tr>
                              </table>
                            <table width="820" border="0" cellpadding="0" cellspacing="0">
                                <tr valign="top">
                                	<td width="4%">&nbsp;</td>
                                  	<td width="192"><p class="style71 style819">
									<form:radiobutton path="newacc" value="true"/>
									<strong>I am a new customer</strong></p>
                                	<p class="style71 style819">
                   					<form:radiobutton path="newacc" value="false"/>
									<strong>I have an account</strong>
                                	</p></td>
                                  <td width="300"><input type="image" src="images/common/bt-start-search-small.png" alt="start search" width="138" height="48" /></td>
                                  <td width="279" valign="top"><p class="parastyle"><!--<a href="#"><img src="images/common/Comodo-seal-85.gif" alt="comodo" width="85" height="51" /></a>--><img src="/springapp/images/common/comodo-secure.png" alt="Comodo" width="113" height="59"><a href="#"><img src="images/common/bbb-clickratingsm.gif" alt="bbb" width="135" height="52" /></a></p>                                      </td>
                                </tr>
                                <tr><td colspan="3"><a href="https://premium.searchsystems.net/bjl-sources.php" target="blank">Database Coverage</a></td>
                                </tr>
                                 
                            </table>
                            </c:when>
                            <c:when test="${searchType eq 'ssn'}">
                             
                           
                           
                         <table width="833" height="163" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td width="100%" valign="top">
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td width="150" valign="top">
                                        	<div align="left"><strong>SSN or Tax ID<span class="style24">*</span>:</strong></div></td>
                                        <td>
                                        	<div align="left">
                                           		<form:input path="bjlssn" size="18" maxlength="18" />&nbsp;<span class="style113">(Required)</span>
                                        	</div>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td height="37">
                                        	<div align="left"><strong>State:</strong></div>
                                        </td>
                                        <td>
                                        	<div align="left">
                                              	<form:select path="bjlstate" cssClass="formfield">
													<form:option value=" ">All States</form:option>
													<form:options items="${usStates}"/>
												</form:select>
                                        	</div>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td height="37">
                                        	<div align="left"><strong>Number Above is:</strong></div>
                                        </td>
                                        <td>
                                       		<div align="left">
                                              	<form:select path="bjlssntype" cssClass="formfield">
												<form:options items="${bjlNumberAbove}"/>
												</form:select>
                                        	</div>
                                        </td>
                                      </tr>
                                       <tr>
                                      	<td>
                                      	 	<div align="left"><strong>Reference:</strong></div> 
                                      	</td>
                                      	<td>
											<form:input path="bjlReference"  size="20" maxlength="20" />
											<span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="18" height="23" id="datebtn"></span>
											<div id="datediv" align="right">You can enter an optional reference code for your own tracking purposes. The code will appear on your credit card billing statement or monthly invoice. (NOTE: Reference code may not be available for some credit cards.)
											</div>
                                      	</td>
                                      </tr>
                                      <tr><td colspan="2"> </td></tr>
                                  </table>
                                 
                                  </td>
                                  <td width="420" valign="top">
                                  <table width="400" cellpadding="0" cellspacing="0" border="0">
								  <tr><td><p class="parastyle">
                                  	<span class="style131">CAUTION: Search results will only   include records that contain a Social Security Number or Tax ID. Since   judgments aren't normally referenced by one of these numbers, then you   WILL NOT find judgments here.</span></p></td></tr>
                                    <tr>
                                      <td class="style131">Also, if a bankruptcy or tax lien record   does not contain a Social Security or Tax ID Number, then it will not   appear in your results.</td>
                                    </tr>
                                    <tr>
                                      <td><span class="style131">BEST results are found on the Search By Name   page.</span></td>
                                    </tr>
									
                                    
                                  </table>
                                  </td>
                                </tr>
                              </table>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr valign="top">
                                	<td width="4%">&nbsp;</td>
                                  	<td width="193">
                                  	<p class="style71 style819">
									<form:radiobutton path="newacc" value="true"/>
									<strong>I am a new customer</strong></p>
                                	<p class="style71 style819">
                   					<form:radiobutton path="newacc" value="false"/>
									<strong>I have an account</strong>
                                	</p>
                                </td>
                                  <td width="300"><input type="image" src="images/common/bt-start-search-small.png" alt="start search" width="138" height="48" ></td>
                                  <td width="279"><p><!--<a href="#"><img src="images/common/Comodo-seal-85.gif" alt="comodo" width="85" height="51" /></a>--><img src="/springapp/images/common/comodo-secure.png" alt="Comodo" width="113" height="59"><a href="#"><img src="images/common/bbb-clickratingsm.gif" alt="bbb" width="135" height="52" /></a></p>                                      </td>
                                </tr>
                                <tr valign="top">
                                	<td colspan="3"><a href="https://premium.searchsystems.net/bjl-sources.php" target="blank">Database Coverage</a></td>
                                </tr>
                                 
                            </table>
                            </c:when>
                              </c:choose>
                            </td>
                        </tr>
                      </table>
                      <table width="852">
                  <tr>
                    <td width="844">
                    	<form:errors path="*"  cssStyle="margin-left:200px;color:red;font-size:12px;"/>
                    </td>
                     </tr>
           </table>      
                      <table width="852">
                        <tr>
                          <td width="844" height="158"><span class="style15">All information retrieved from or through SearchSystems.net must be utilized in accordance  with the User Agreement and all applicable state and federal laws, including the <a href="http://www.ftc.gov/os/statutes/fcra.htm">Fair Credit Reporting Act</a>;  any violation of these will be grounds for immediate termination of your account without  notice. <br />
                                <br />
                                <strong>DISCLAIMER:</strong> No warranty of any type is provided as to the quality or accuracy of the  information obtained from or through SearchSystems.net, and any reliance on that  information is solely at your own risk and responsibility.  Information contained herein  is derived solely from public records, which may not be 100 percent accurate or complete.  Users should not assume that this data provides a complete or accurate record of any  person's bankruptcy, judgment, or tax lien history.  Please see SearchSystems' <a href="https://premium.searchsystems.net/agreement.php">User Agreement</a> for all applicable terms and conditions. <br />
                                <br />
                            Search Systems, Inc. dba Search Systems assumes no liability for any claims  for damages arising from the use of this data beyond the actual cost of the searches  performed. </span></td>
                        </tr>
                      </table>
                    </div></td>
              </tr>
            </table>
            
</td></tr>
</form:form>
 <script>
					  $(document).ready(function(){
							$("#datediv").hide();
							$("#datebtn").show();
							$("#datebtn").click(function () {
							$("#datediv").slideToggle();
							});
						});
						$(document).ready(function(){
							$("#refdiv").hide();
							$("#refdiv1").hide();
							$("#refbtn").show();
							$("#refbtn").click(function () {
							$("#refdiv").slideToggle();
							$("#refdiv1").slideToggle();
							});
						});


					  </script>
</jsp:body>
</neon:NewBJLFunnel>