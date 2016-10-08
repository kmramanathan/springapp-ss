<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<neon:NewAliasFunnel title="Search Systems Criminal Records" >
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
.style61 {font-family: Arial, Helvetica, sans-serif; font-size: 12px;color:#000;paddibg:0!important;margin:0!important;    vertical-align: text-top;}
.style72 {color: #FF0000!important;font-size:19px;}
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
font-family:Arial, Helvetica, sans-serif;


}
.style821{margin:0;padding:0}
.padd{padding:0;margin:0}
.style107 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #333333; }
#refdiv{display:none;position:absolute;width:290px;text-align:left;font-size: 11px;font-weight:bold; float:left;}
#refdiv2{display:none;position:absolute;width:290px;text-align:left;font-size: 11px;font-weight:bold; float:left;}
.datalink{padding: 0;margin: 0;}
#dataview p{padding:4px 0 4px 0; margin:0;font-family:Arial, Helvetica, sans-serif;font-size:12px}
#dataview{ font-size:12px;font-family:Arial, Helvetica, sans-serif}
.head{color:#000!important}
</style>

 <script>
function showTips()
 {
	var div = document.getElementById("refdiv2");
	var div1 = document.getElementById("refdiv");
	var datediv = document.getElementById("datediv");
	
	div1.style.display = "none";
	datediv.style.display = "none";
	if (div.style.display !== "none") 
	{
	    div.style.display = "none";
	}
	else
	{
	    div.style.display = "block";
	}
 }
 
 function showRefernceCodeTips()
 {
	var div = document.getElementById("refdiv2");
	var div1 = document.getElementById("refdiv");
	var datediv = document.getElementById("datediv");
	
	div.style.display = "none";
	datediv.style.display = "none";
	
	if (div1.style.display !== "none") 
	{
	    div1.style.display = "none";
	}
	else
	{
	    div1.style.display = "block";
	}
 }
 
 function showDateTips()
 {
	var div = document.getElementById("refdiv2");
	var div1 = document.getElementById("refdiv");
	var datediv = document.getElementById("datediv");
	
	div.style.display = "none";
	div1.style.display = "none";
	
	if (datediv.style.display !== "none") 
	{
	    datediv.style.display = "none";
	}
	else
	{
	    datediv.style.display = "block";
	}
 }
 
</script>
  <form:form name="crimeForm" method="post">
    <form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top">
<td >
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td>
 <h1 class="style59 style92">Start Your Instant AKA / Alias Criminal Search</h1></td> 
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
                   
                      <table width="100%" border="0" bgcolor="#B4DDFE" cellpadding="0" cellspacing="0" >
                      <tr>
                      	<td ><span class="style61"><strong class="style72">*</strong>
                      	<span class="style91"> Required Field</span></span>
                      	</td>
                      	<td >
                      	<span style="text-decoration: underline; color:#000;">
                      	<a href="https://www.searchsystems.net/springapp/funnel/BestResultsGuide.html" class="style71 style819" style="font-size:14px!important;"> <strong>Search Tips </strong></a>
                      	</span>
                      	</td>
                      	<td colspan="2"><span class="style86" style="font-size:14px">Price: <strong>$19.95</strong> Nationwide or <strong>$14.95</strong>
						Statewide <br/>&nbsp;&nbsp;No signup or monthly fees.</span>
						</td>
                      	
                      </tr>
                     
                        <tr>
                          <td style="width: 275px;">
                          <p class="style71 style819"><strong>First Name</strong><strong class="style72">*</strong></p>
                             
                            <form:input path="bgcFirstName" cssClass="formfield" size="20" maxlength="20" />                            
                            <form:checkbox path="bgcFirstNameExact" cssClass="formfield" />                            
                            <span class="style61">Exact<img src="/springapp/img/Help.jpg" border="0"  
                            style="margin:0 0 -5px 6px;cursor:pointer;" width="15" height="15" onclick="showTips()"/></span>
                             
                            </td>
                             
                             <td style="width: 266px;">
							 <div style="margin-left:18px"><p class="style71 style819"><strong>Last Name</strong><strong class="style72">*</strong></p>
                            <form:input path="bgcLastName" cssClass="formfield" size="16" maxlength="20" />
                        
                          <form:checkbox path="bgcLastNameExact" cssClass="formfield" />
						  <span class="style61">Exact
							  	<img src="/springapp/img/Help.jpg" border="0" style="margin:0 0 -5px 6px;cursor:pointer;"
							  	 width="15" height="15" onclick="showTips()"/></span>
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
                      
                        
                        <tr>
                          <td>
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
							</div>	
							
                          </td>
                       
                          <td>
							<div style="margin-left:18px">
							  <p class="style71 style819"><strong>SSN</strong><strong class="style72">*</strong></p>
                           <form:input path="bgcSsn" cssClass="formfield" size="9" maxlength="9" />&nbsp; 
														
							</div>
							</td>
                      
                          <td class="style71" colspan="2" valign="top">
                           <strong>Options</strong>                    
                            <p style="color:black;margin:0;padding:0 0 0 1px">
                              
                              <form:checkbox path="bgcMatchMissingDates" cssClass="formfield" />
                              <span for="bgcMatchMissingDates" class="style71" style="font-size:12px;">Include records where there is no date of birth
                              <img src="/springapp/img/Help.jpg" width="15" height="15" onclick="showDateTips()" style="cursor:pointer;"></span>
                              
                            </p>
                          
							</td>
							</tr>
							
							<tr>
							<td colspan="2">
							<p class="style71 style819"><strong>Purpose of your search</strong><span class="req"><strong>*</strong></span></p>
							<div>
								<form:select path="bgcPurpose" cssClass="formfield1">
								<form:option value="">[Choose]</form:option>
								<form:options items="${bgcSearchPurposes}"/>		
								</form:select>
							</div>
							</td >
							<td colspan="2">
							 <p style="color:black;margin:0;padding:0px 0 0 8px"><label for="bgcReferenceCode">Reference Code</label> 
							<form:input path="bgcReferenceCode" cssClass="formfield" size="20" maxlength="20" />
							<span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="15" height="15" 
							onclick="showRefernceCodeTips()" style="cursor:pointer;" /></span>
							</p>							
							</td>
							</tr>
							<tr>
							<td colspan="2">
							<div style="float: left;padding-left:160px">
								<p class="style71 style819">
								
								<form:radiobutton path="newacc" value="true"/>
								<strong>I am a new customer</strong></p>
                                <p class="style71 style819">
                   				<form:radiobutton path="newacc" value="false"/>
								<strong>I have an account</strong>
                                </p>
							</div>
                            <div style="float: left;padding-left: 70px">
                            <p align="center">
                              <input type="submit" name="button2" id="button2" value="Proceed" onclick="bgcredirCtrl(); return false;" style="cursor: pointer;">
                            </p></div>
                            </td>
                            <td colspan="2">
                            <div id="refdiv" align="left" class="style72" style="display:none;">You can enter an optional reference code for your own tracking purposes. 
							The code will appear on your credit card billing statement or monthly invoice. 
							(NOTE: Reference code may not be available for some credit cards.)
							</div>
							 <div id="datediv" align="left" class="style72" style="display:none;font-size:11px;font-weight: bold;">Not every reporting agency provides a full date of birth on every record. 
						   This feature gives you those records as well. CAUTION: These additional results may or may not be for your person 
						   because there is not an exact date of birth match.</div>
						   
                           <div id="refdiv2" align="left"  class="style72" style="display:none;">Uncheck the "Exact" box to do a partial name search.</div>
                           <br/><br/>
                            </td>
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
							<h3 class="style1111">The affordable professional system anyone can use.</h3>
                        		<p class="style107 padd"><strong>Search our database of over 550 million records by name, birth date, and social security number for a subject's name and maiden name, married name, nickname, and aliases revealed by an address history.  FCRA Only.
								<a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do" target="_blank">You can find our coverage area here</a>.</strong></p>
                              <p class="padd"><strong>550 Million records! The largest criminal record database available online.<br>
							  At $19.95 per nationwide search, this is the most information you can find for the lowest price on the Internet.</strong>
							  
                               </p>  
                            </td>
                            <td colspan="2">
							<p class="padd"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do" class="style107" target="_blank" style="font-size:14px">View Our  Coverage</a></p>
                                  <p align="left" class="style107 padd"><a href="https://www.searchsystems.net/springapp/funnel/BestResultsGuide.html">Your Guide to Best Results</a></p>
                                  <p align="left" class="style107 padd"><a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/">Search Criminal Records for Free</a></p>
                                  <p align="left" class="style107 padd"><a href="crim-premium-faq.html">Frequently Asked Questions</a></p>
                            	
                        	</td>
                        	
                        </tr>
                    </table>
                      
                      
                       <p style="color:black;margin:0;padding:7px 0 7px 12px"><span class="style61" >All information retrieved from or through SearchSystems.net must be   utilized   in accordance with the <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=funnel" target="_blank">User Agreement</a> and all applicable state and federal   laws,   including the <a href="http://www.ftc.gov/os/statutes/fcra.htm" target="_blank">Fair Credit Reporting Act</a>;   any violation of these will be grounds for immediate termination of your   account without notice.</span></p>
                        <p class="style61" style="color:black;margin:0;padding:0 0 0 12px">Please see our <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">disclaimer</a> and also our <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">Notice to California Employers or Employers screening   California residents</a>.</p>
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
</neon:NewAliasFunnel>