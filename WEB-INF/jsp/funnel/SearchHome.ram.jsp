<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<neon:NewCrimeFunnel title="Search Systems Criminal Records" >
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css,style_02.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-latest.js</jsp:attribute>
<jsp:body>


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
#refdiv{display:none;color:red;position:absolute;width:250px;text-align:justify;font-size: 12px;margin-left:100px}
</style>
  <form:form name="crimeForm" >
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
                      	 
                      	</td>
                      	<td colspan="2"><span class="style86" style="font-size:14px">Price: <strong>$14.95</strong> Nationwide or <strong>$9.95</strong>
						Statewide <br/>&nbsp;&nbsp;No signup or monthly fees.</span>
						</td>
                      	
                      </tr>
							<tr>
							<td colspan="2">
							<p class="style71 style819"><strong>Purpose of your search</strong><span class="req"><strong>*</strong></span></p>
							<div>
							
							<div style="float: left;padding-left:160px">
								<p class="style71 style819">
								<form>
								<input type="radio" name="personal" id="personal" value="personal"  onclick ="radioPresnl();"/> 
                      	  	 <label for="personal"><strong> Personal Use Only &nbsp;&nbsp;  </strong></label>
								</p>
                                <p class="style71 style819">
                   				<input type="radio" name="NonPersnlradio" id="NonPersnlradio" value="Nonpersonal" 
                      	    onclick ="radioNonPresnl();"/> 
                      	   <label for="NonPersnlradio"><strong>Non Personal &nbsp;&nbsp; </strong></label>
                                </p>
                                </form>
							</div>				
							<div style="float: left;padding-left: 70px">
                            <p align="center">
                              <input type="submit" name="button2" id="button2" value="Proceed" 
                              onclick="goToLocation();" style="cursor: pointer;">
                            </p></div>
                            
							</div>
							</td >
							<td colspan="2">
							
							</td>
							</tr>
							 
                       
                      </table>
                      <script type="text/javascript">
                      function radioPresnl()
						{
							if (document.getElementById('personal').checked)
							{  
								var Nonpersonal = document.getElementById('NonPersnlradio');
								Nonpersonal.checked = false; 
							}
						}
                      function radioNonPresnl()
						{ 
							if (document.getElementById('NonPersnlradio').checked)
							{
								var personal = document.getElementById("personal");
								personal.checked = false;  
								 
							}
						}
						function goToLocation()
						{
							if (document.getElementById('personal').checked)  //non FCRA search
							{								
								window.location = "/springapp/funnel/criminalSearch.do";
							}
							
							if (document.getElementById('NonPersnlradio').checked)
							{
								window.location = "/springapp/funnel/newSearch.do";
							}
						}
						 


					  </script>
                     <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    	 <tr>
                        	<td colspan="2" width="75%">
						<!-- <h3 class="style1111">The affordable professional system anyone can use.</h3> -->	
                        		<p class="style107 padd"><strong>Search felonies, misdemeanors, traffic offenses, court records, inmate, probation, parole records, escapees, and more. Certain records are not available in all states. Please check our coverage area for the region of your search, to ensure you receive the best service we have to offer. 
								<a href="/springapp/funnel/ourDatabases.do" target="_blank">You can find our coverage area here</a>.</strong></p>
                              <p class="padd"><strong>550 Million records! The largest criminal record database available online.<br>
							  At $14.95 per nationwide search, this is the most information you can find for the lowest price on the Internet.</strong>
							  
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