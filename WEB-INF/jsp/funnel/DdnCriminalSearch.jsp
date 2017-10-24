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
	function yearofbirth()
	{
		var yearbirth = document.crimeForm.bgcDobRangeBaseYear.value
		if(yearbirth == 0)
		{
			document.getElementById("bgcDobRange1").checked=false;
			document.crimeForm.bgcDobRange.checked = false;
		}
		else{
			document.getElementById("bgcDobRange2").checked=true;
			document.crimeForm.bgcDobRange.checked = true;
		
		}
	}
	function getstat(){
		var stat = document.crimeForm.bgcState.value;
		if(stat == "all"){
			document.crimeForm.bgcMatchMissingDates.checked = false;
		}
		else if(stat == "DE"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Delaware_Criminal_Records/";
		}
		else if(stat == "NY"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/New_York_Criminal_Records/";
		}
		else if(stat == "CO"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Colorado_Criminal_Records/";
		}
		else if(stat == "MA"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Massachusetts_Criminal_Records/";
		}else if(stat == "SD"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/South_Dakota_Criminal_Records/";
		}else if(stat == "WY"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Wyoming_Criminal_Records/";
		}else if(stat == "VT"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Vermont_Criminal_Records/";
		}else if(stat == "AL"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Alabama_Criminal_Records/";
		}else{
			document.crimeForm.bgcMatchMissingDates.checked = true;
		}
		
		
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
#datediv{display:none;color:red;position:absolute;width:350px;text-align:justify;font-size: 12px;}
#refdiv{display:none;color:red;position:absolute;width:350px;text-align:justify;font-size: 12px;}
</style>
  <form:form name="crimeForm" method="post">
    <form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top">
<td >
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td>
 <h1 class="style59 style92">Start Your Instant <strong class="style72">Personal</strong> Criminal Search</h1></td>
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
                      	<td width="140px"><span class="style61"><strong class="style72">*</strong>
                      	<span class="style91"> Required Field</span></span>
                      	</td>
                      	<td>
                      	<span style="text-decoration: underline; color:#000;margin-left: 90px;">
                      	<a href="/springapp/funnel/BestResultsGuide.html" class="style71 style819" style="font-size:14px!important;"> <strong>Search Tips </strong></a>
                      	</span>
                      	</td>
                      	<td colspan="2"><span class="style86" style="font-size:14px;float: right;;">Price: <strong>$14.95</strong> Nationwide or <strong>$9.95</strong>
						Statewide <br/>&nbsp;&nbsp;No signup or monthly fees.</span>
						</td>
                      	
                      </tr>
                     
                        <tr>
						<td style="width: 110px;">&nbsp;</td>
                          <td width="230px">
                          <p class="style71 style819"><strong>First Name</strong><strong class="style72">*</strong></p>
                            
                            <form:input path="firstname" cssClass="formfield" size="20" maxlength="20" />
                            
                            <!--form:checkbox path="firstNameExact" cssClass="formfield" />
                            
                            <span class="style61"-->&nbsp;<!--/span--></td>
                             <td width="210px">
							 <div style="width: 230px;"><p class="style71 style819"><strong>Last Name</strong><strong class="style72">*</strong></p>
                            <form:input path="lastname" cssClass="formfield" size="16" maxlength="20" />
                        
                          <!--form:checkbox path="lastNameExact" cssClass="formfield" /<span class="style61"-->&nbsp;<!--/span-->
						  </div>
						  </td>
                           <td><p class="style71 style819">M.I.(optional)</p>
                            <form:input path="middleInitial" cssClass="formfield" size="2" maxlength="1" />
                            </td>
                        </tr>
                      
                        <tr>
						<td style="width: 110px;">&nbsp;</td>
                        <td>
                         <div style="float:left;"> 
		                           <p class="style71 style819"> <!-- <form:radiobutton path="bgcDobRange" value="true" /> -->
		                            <strong>Date of   Birth</strong> <strong class="style72">*</strong><br>
		                           </p>
		                            
		     						<form:select path="crmnlDobMonth" cssClass="formfield1">
									<form:option value="0">Month</form:option>
									<form:options items="${dobMonths}"/>
									</form:select>&nbsp;
		
		                            <form:select path="crmnlDobDay" cssClass="formfield1">
									<form:option value="0">Day</form:option>
									<form:options items="${dobDays}"/>
									</form:select>&nbsp; 
		                            <form:select path="crmnlDobYear" cssClass="formfield1">
									<form:option value="0">Year</form:option>
									<form:options items="${dobYears}"/>
									</form:select>
							</div>	
                        </td> 
						 <td colspan="2">
						 <div style="margin-left:100px;">
							 <p class="style71 style819"><strong>Search Area</strong><strong class="style72">*</strong></p>
								<form:select path="state" size="1"  cssClass="formfield1" onchange="getstat();">
								<form:option value="all">Nationwide</form:option>
								<form:options items="${usStates}"/>
								</form:select>
								
								<div style="display:none;">
								  <p class="style71 style819"><strong>SSN</strong><!--strong class="style72">*</strong--></p>
							   <form:input path="ssn" cssClass="formfield" size="9" maxlength="9" />&nbsp; 
															
								</div>
							</div>
							</td>              
                        </tr>                        						
                      </table>
					  <table width="100%" border="0" bgcolor="#B4DDFE" cellpadding="0" cellspacing="0">
					  <tr style="height: 40px;"><td colspan=4>&nbsp;</td></tr>
					  <tr style="height: 80px;">						
							<td style="width: 110px;">&nbsp;</td>
							<td>
							<div style="float: left;">
								<p class="style71 style819">								
								<input id="newacc1" name="newacc" type="radio" value="true">
								<strong>I am a new customer</strong>
                                &nbsp; <input id="newacc2" name="newacc" type="radio" value="false" checked="checked">
								<strong>I have an account</strong>  
								&nbsp; <input type="submit" name="button2" id="button2" value="Proceed" style="margin-left: 90px;">                 				
                                </p>
								 
							</div>
                           
                            </td>
							<td colspan="2" style="float: left;">
							<div id="datediv" align="right" style="display: block;">Not every reporting agency provides a full date of birth on every record. 
	                           This feature gives you those records as well. CAUTION: These additional results may or may not be for your
	                            person because there is not an exact date of birth match.</div>
							<div id="refdiv" align="right" style="display: none;">You can enter an optional reference code for your own tracking purposes. 
							The code will appear on your credit card billing statement or monthly invoice. 
							(NOTE: Reference code may not be available for some credit cards.)</div>
							</td>
                        </tr>
                       
					  </table>
					  <script>
					  $(document).ready(function(){
							$("#datediv").hide();
							$("#datebtn").show();
							$("#datebtn").click(function () {
							$("#datediv").slideToggle();
							$("#refdiv").hide();
							});
						});
						$(document).ready(function(){
							$("#refdiv").hide();
							$("#refbtn").show();
							$("#refbtn").click(function () {
							$("#refdiv").slideToggle();
							$("#datediv").hide();
							});
						});


					  </script>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        	<td colspan="2" width="75%">
						<!-- <h3 class="style1111">The affordable professional system anyone can use.</h3> -->	
						<p class="padd"><strong>550 Million records! The largest criminal record database available online.<br></strong></p>  							  
                        		<p class="style107 padd"><strong>
								Perform a personal search of United States felonies, misdemeanors, traffic offenses, court records, inmates, probation and parole records, escapees, 
								and more on yourself or someone else that is NOT for Fair Credit Reporting Act (FCRA) purposes (e.g. court order, employment, insurance application, license eligibility, etc.).  
								For more information about FCRA purposes, go <a href="https://www.gpo.gov/fdsys/pkg/USCODE-2011-title15/pdf/USCODE-2011-title15-chap41-subchapIII-sec1681b.pdf" target="_blank">here</a>.</strong>
								</p>
                              <p class="style107 padd"><strong>
							  Certain records are not available in all states.  Please check our <a href="/springapp/funnel/ourDatabases.do" class="style107" target="_blank" style="font-size:14px">coverage area</a>
							  for the region of your search to ensure you receive the best service we have to offer.
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
                      
                      
                       <p style="color:black;color: #036;">
					   All information retrieved from or through SearchSystems.net must be utilized in accordance with the <a href="/springapp/legal/showAgreement.do?version=funnel" target="_blank">
					   User Agreement</a> and all applicable state and federal laws.  
					   Any violation of these will be grounds for immediate termination of your account without notice. <br/>
					   Please see our <a href="/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">disclaimer</a> .
					   </p> 
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