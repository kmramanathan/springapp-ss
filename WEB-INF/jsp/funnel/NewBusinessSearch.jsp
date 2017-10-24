<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<neon:NewCrimeFunnel title="Search Systems Criminal Records" >
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css,style_02.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-latest.js</jsp:attribute>
<jsp:body>

<script type="text/javascript">
	
function shownote(a, b, c){
	document.getElementById(a).style.display = "block";
	document.getElementById(b).style.display = "none";
	document.getElementById(c).style.display = "none";
}

</script>

<style type="text/css">
#button2
{
	font-size:14px;
}

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

.style71 {font-family: Arial, Helvetica, sans-serif;color:black!important;paddibg:0!important;margin:0!important;}
.style61 {font-family: Arial, Helvetica, sans-serif; font-size: 12px;color:#000;paddibg:0!important;margin:0!important;}
.style72 {color: #FF0000!important}
.style75 {font-size: 14px}
.style76 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14;
}
.style77 {font-size: 14}
.style81 {color: #003366}
.style83 {
	font-family: Arial, Helvetica, sans-serif;
	color: #006633;
	font-weight: bold;
}
.style86 {font-size: 12px}
.style88 {color: #000000}
.style90 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FF0000; }
.style91 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FF0000; }
.style818{color:#003366!important;margin:0!important;padding:0 0 10px 9px!important;}
.style819{padding:0 0 5px 0px!important;margin:0!important}
.style95 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: small;
	color: #003366;
	font-weight: bold;
}
.style92 {
	
	font-weight: bold;
	margin:0;
	padding: 0;
	
	
}
.style93 {	font-size: medium;
	white-space: normal;
	width: auto;
}
.style1111{
padding: 0;
margin: 0;
}
.style821{margin:0;padding:0}
.padd{padding:0;margin:0}
.style107 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #333333; }
#datediv{display:none;color:red;position:absolute;width:250px;text-align:justify;font-size: 12px;margin-left:100px;margin-top:37px}
#refdiv{display:none;color:red;position:absolute;width:250px;text-align:justify;font-size: 12px;}
</style>
  <form:form name="crimeForm" method="post">
    <form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top">
<td >
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td>
 <h1 class="style59 style92">Start Your Instant Criminal Search</h1></td>
 <td valign="top"><a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="90" height="35" style="float: right;"></a></td>
</tr>
<tr><td colspan="2">
           <h3 class="style1111">Search over <strong>550</strong> <strong>Million</strong> <strong>criminal records</strong> nationwide!</h3>
           </td>
           </tr>
       </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr valign="top">
            <td width="99%" >
           
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr valign="top">
                    <td width="100%">
                   
                      <table width="100%" border="0" bgcolor="#B4DDFE" cellpadding="0" cellspacing="0">
                      <tr>
                      	<td ><span class="style61"><strong class="style72">*</strong>
                      	<span class="style91"> Required Field</span></span>
                      	</td>
                      	<td >
                      	<span style="text-decoration: underline; color:#000;">
                      	<a href="/springapp/funnel/BestResultsGuide.html" class="style71 style819" style="font-size:14px!important;"> <strong>Search Tips </strong></a>
                      	</span>
                      	</td>
                      	<td colspan="2"><span class="style86" style="font-size:14px">Price: <strong>$14.95</strong> Nationwide or <strong>$9.95</strong>
						Statewide <br/>&nbsp;&nbsp;No signup or monthly fees.</span>
						</td>
                      	
                      </tr>
                     
                        <tr>
                          <td>
                          <p class="style71 style819"><strong>First Name</strong><strong class="style72">*</strong></p>
                            
                            <form:input path="bgcFirstName" cssClass="formfield" size="20" maxlength="20" />
                            
                            <!--form:checkbox path="bgcFirstNameExact" cssClass="formfield" />
                            
                            <span class="style61">Exact</span--></td>
                             <td>
							 <div style="margin-left:18px"><p class="style71 style819"><strong>Last Name</strong><strong class="style72">*</strong></p>
                            <form:input path="bgcLastName" cssClass="formfield" size="16" maxlength="20" />
                        
                          <!--form:checkbox path="bgcLastNameExact" cssClass="formfield" /><span class="style61">Exact</span-->
						  </div>
						  </td>
                           <td><p class="style71 style819">M.I.(optional)</p>
                            <form:input path="bgcMiddleInitial" cssClass="formfield" size="2" maxlength="1" />
                            </td>
                              <td height="44"><p class="style71 style819"><strong>Search Area</strong><strong class="style72">*</strong></p>
                            <form:select path="bgcState" size="1"  cssClass="formfield1" onchange="getstat();">
                            <form:option value="all">Nationwide</form:option>
							<form:options items="${usStates}"/>
                            </form:select>
                            
                            </td>
                        </tr>
                         </table>
                     <table width="100%" border="0" bgcolor="#B4DDFE" cellpadding="0" cellspacing="0">    
                        <tr>
						<td style="width: 110px;">&nbsp;</td>
						<td width="250px" colspan="3">
						    <div style="float:left;"> 
                           <p class="style71 style819">
                            <strong>Date of   Birth</strong><strong class="style72">*</strong><br>
                           </p>
                            
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
							
							<div style="color:black;float:right;margin-left:80px;"><label for="bgcReferenceCode">Reference Code</label> 
							<form:input path="bgcReferenceCode" cssClass="formfield" size="20" maxlength="20" />
							<span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="18" height="23" id="refbtn"></span></div>
							
							
							<div style="display:none;">
							  <p class="style71 style819"><strong>SSN</strong><strong class="style72">*</strong></p>
                           <form:input path="bgcSsn" cssClass="formfield" size="9" maxlength="9"  />&nbsp; 
														
							</div>
							</div>
                          </td>
							</tr>
							<tr>
							<td style="width: 110px;">&nbsp;</td>
							<td width="350px">
							<div style="float: left;">
							<p class="style71 style819"><strong>Purpose of your search</strong><span class="req"><strong>*</strong></span></p>
								<div>
									<form:select path="bgcPurpose" cssClass="formfield1">
									<form:option value="">[Choose]</form:option>
									<form:options items="${bgcSearchPurposes}"/>		
									</form:select>
								</div>
							</div>
							</td>
							<td colspan="2">
							 <!--p style="color:black;margin:0;padding:0px 0 0 8px"><label for="bgcReferenceCode">Reference Code</label> 
							<form:input path="bgcReferenceCode" cssClass="formfield" size="20" maxlength="20" />
							<span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="18" height="23" id="refbtn"></span></p>
							-->							             	
							<div id="refdiv" align="right">You can enter an optional reference code for your own tracking purposes. The code will appear on your credit card billing statement or monthly invoice. (NOTE: Reference code may not be available for some credit cards.)</div>
							&nbsp;
							</td>
							</tr>
							<tr>
							<td style="width: 110px;">&nbsp;</td>
							<td width="350px" colspan="2">
							<div style="float: left;">
								<div style="float: left;padding-left:10px">
									<p class="style71 style819">
									
									<form:radiobutton path="newacc" value="true"/>
									<strong>I am a new customer</strong></p>
									<p class="style71 style819">
									<form:radiobutton path="newacc" value="false"/>
									<strong>I have an account</strong>
									</p>
								</div>
								<div style="float:left;margin-left:50px;">
								<p align="center">
								  <input type="submit" name="button2" id="button2" value="Proceed" onclick="bgcredirCtrl(); return false;" style="cursor: pointer;">
								</p>
								</div>
							</div>
                            </td>
							<td>&nbsp;</td>
						</tr>
                       
                   
					   </table>


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
							$("#refbtn").show();
							$("#refbtn").click(function () {
							$("#refdiv").slideToggle();
							});
						});


					  </script>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    	 <tr>
                        	<td colspan="2" width="75%">
						<!-- <h3 class="style1111">The affordable professional system anyone can use.</h3> -->	
                        		<p class="style107 padd"><strong>Search felonies, misdemeanors, traffic offenses, court records, inmate, probation, parole records, escapees, and more. Certain records are not available in all states. Please check our coverage area for the region of your search, to ensure you receive the best service we have to offer. 
								<a href="/springapp/funnel/ourDatabases.do" target="_blank">You can find our coverage area here</a>.</strong></p>
                              
							  <p class="padd">
							  This search is for FCRA compliant searches only, meaning that your search purpose must be by:<br/>
							  Court order or subpoena;<br/>
Instructed by consumer in writing;<br/>
Employment purposes with written permission<br/>
Consumer application for insurance;<br/>
Business transaction initiated by consumer;<br/>
Determine if consumer meets terms of account;<br/>
Eligibility for a license or benefit; or<br/>
Use by potential investor, servicer, or insurer.<br/>
If your search does not meet any of these requirements or you are searching for records on yourself, then use the "Personal Search" option on the previous page.<br/>
For more information on FCRA requirements and compliance, go <a href="https://www.gpo.gov/fdsys/pkg/USCODE-2011-title15/pdf/USCODE-2011-title15-chap41-subchapIII-sec1681b.pdf" target="_blank">here.</a><br/>

							  
                               </p>  
                            </td>
                            <td colspan="2">
							<p class="padd"><a href="/springapp/funnel/ourDatabases.do" class="style107" target="_blank" style="font-size:14px">View Our  Coverage</a></p>
                                  <p align="left" class="style107 padd"><a href="/springapp/funnel/BestResultsGuide.html">Your Guide to Best Results</a></p>
                                  <p align="left" class="style107 padd"><a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/">Search Criminal Records for Free</a></p>
                                  <p align="left" class="style107 padd"><a href="crim-premium-faq.html">Frequently Asked Questions</a></p>
                            	
                        	</td>
                        	
                        </tr>
                    </table>
                      
                      
                       <p style="color:black;margin:0;padding:7px 0 7px 12px"><span class="style61" >All information retrieved from or through SearchSystems.net must be   utilized   in accordance with the <a href="/springapp/legal/showAgreement.do?version=funnel" target="_blank">User Agreement</a> and all applicable state and federal   laws,   including the <a href="http://www.ftc.gov/os/statutes/fcra.htm" target="_blank">Fair Credit Reporting Act</a>;   any violation of these will be grounds for immediate termination of your   account without notice.</span></p>
                        <p class="style61" style="color:black;margin:0;padding:0 0 0 12px">Please see our <a href="/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">disclaimer</a> and also our <a href="/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">Notice to California Employers or Employers screening   California residents</a>.</p>
                    </td>
                  </tr>
                </table>
             </td>
             </tr>
             </table>
            
              </td>
            </tr>
</form:form>
</jsp:body>
</neon:NewCrimeFunnel>