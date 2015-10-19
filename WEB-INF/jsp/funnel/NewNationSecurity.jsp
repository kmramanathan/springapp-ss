<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<neon:NewCrimeFunnel title="Search Systems National Security Search" >
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
.datalink{padding: 0;margin: 0;}
</style>
  <form:form name="crimeForm" method="post">
    <form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top">
<td >
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td>
 <h1 class="style59 style92">National Security Search</h1></td>
 <td valign="top"><a href="${bbbUrl}" target="_blank"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="90" height="35" style="float: right;"></a></td>
</tr>
<tr><td colspan="2">
           <h3 class="style1111">Search over <strong>70</strong> International & domestic databases for fugitives, known terrorists, casino cheats, debarred parties, sanctioned parties, registered offenders, excluded providers, and more.</h3>
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
                      	<td colspan="2"><span class="style61"><strong class="style72">*</strong><span class="style91"> Required Field</span></span></td>
                      	<td colspan="2"><span class="style86" style="font-size:14px">Price: <strong>$5.00</strong> </span></td>
                      	
                      </tr>
                     
                        <tr>
                          <td>
                          <p class="style71 style819"><strong>First Name</strong><strong class="style72">*</strong></p>
                            
                            <form:input path="nssFirstName" cssClass="formfield" size="20" maxlength="20" />
                            
                            <form:checkbox path="nssFirstNameExact" cssClass="formfield" />
                            
                            <span class="style61">Exact</span></td>
                             <td>
							 <div style="margin-left:18px"><p class="style71 style819"><strong>Last Name</strong><strong class="style72">*</strong></p>
                            <form:input path="nssLastName" cssClass="formfield" size="16" maxlength="20" />
                        
                          <form:checkbox path="nssLastNameExact" cssClass="formfield" /><span class="style61">Exact</span>
						  </div>
						  </td>
                           <td height="39" valign="top">
                            
                            <p class="style71 style819"><strong>Date of   Birth </strong>(Optional)</p>
                            <span class="style71">
     						<form:select path="nssDobMonth" cssClass="formfield1">
							<form:option value="0">Month</form:option>
							<form:options items="${dobMonths}"/>
							</form:select>&nbsp;

                            <form:select path="nssDobDay" cssClass="formfield1">
							<form:option value="0">Day</form:option>
							<form:options items="${dobDays}"/>
							</form:select>&nbsp; 
                            <form:select path="nssDobYear" cssClass="formfield1">
							<form:option value="0">Year</form:option>
							<form:options items="${dobYears}"/>
							</form:select>
                          </span></td>
                              <td height="44">&nbsp;
                            </td>
                        </tr>
                      
                        
                       
							<tr>
							<td colspan="2">
							<p class="style71 style819"><strong>Purpose of your search</strong><span class="req"><strong>*</strong></span></p>
							<div>
								<form:select path="nssPurpose" cssClass="formfield1">
								<form:option value="">[Choose]</form:option>
								<form:options items="${bgcSearchPurposes}"/>		
								</form:select>
							</div>
							</td >
							<td colspan="2">
							 <p style="color:black;margin:0;padding:0px 0 0 8px"><label for="bgcReferenceCode">Reference Code</label> 
							<form:input path="nssReferenceCode" cssClass="formfield" size="20" maxlength="20" />
							<span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="18" height="23" id="refbtn"></span></p>
							<div id="refdiv" align="right">You can enter an optional reference code for your own tracking purposes. The code will appear on your credit card billing statement or monthly invoice. (NOTE: Reference code may not be available for some credit cards.)</div>
							</td>
							</tr>
							<tr>
							<td colspan="4">
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
                        </tr>
                       
                      </table>
					 
                    
                      
                      
                       <h3 id="viewbtn"><a href="#">Click here to view the databases</a></h3>
                       <div id="dataView">
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Alabama Medicaid - Suspended Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">America's Most Wanted Fugitive List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Australia Registered Offender Registry </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Australian Department of Foreign Affairs and Trade - Sanctions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Australian Reserve Bank Sanctions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Bank of England Sanctions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">California Medi-Cal - Suspended and Ineligible Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Connecticut Department of Social Services - Admin Actions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">DEA Diversion Control Program - Admin Actions against Doctors </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">DEA Diversion Control Program - Cases against Doctors </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Delaware Adult Abuse Registry </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Directorate of Defense Trade Controls - Debarred Parties List </a></p> 
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">European Union Terrorism Sanctions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">FDA Office of Regulatory Affairs - Debarment List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">FDA Office of Regulatory Affairs - Warning Letters </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Federal Deposit Insurance Corporation (FDIC) - Enforcement Decisions and Orders </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Federal Deposit Insurance Corporation (FDIC ) - Failed Bank List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Federal Reserve Board - Enforcement Actions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">FinCEN - Enforcement Actions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">FINRA - Disciplinary Actions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Florida Medicaid - Sanctioned Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Fugitive List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Hawaii Medicaid - Excluded Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Health Resources & Services Administration - Health Education Assistance Loan - Defaulted Borrowers </a></p> 
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">HM Treasury - Consolidated List of Financial Sanctions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">HM Treasury - Investment Ban List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Hong Kong Securities and Futures Commission (SFC) - Enforcement Actions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">HUD - Limited Denials of Participation List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Idaho Medicaid Provider - Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Illinois Casino Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Illinois Office of Inspector General - Sanctioned Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Immigration and Customs Enforcement (ICE) - Most Wanted </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Interpol Most Wanted </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Kansas Department of Health and Environment - Abuse Registry </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Kentucky Medicaid - Excluded Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Maryland Medicaid - Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">MIPT Terrorism Knowledge Base </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Mississippi Medicaid - Excluded Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Missouri Casino Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Monetary Authority of Singapore - Enforcement Actions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">National Credit Union Administration (NCUA) - Administrative Orders </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Naval Criminal Investigative Service (NCIS) - Most Wanted Fugitives  </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Nevada Gaming Control Board - Excluded Person List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">New Jersey Casino Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">New Jersey Department of Treasury - Debarment List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">New York Office of the Medicaid Inspector General - Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">New York Stock Exchange Regulation - Disciplinary Actions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Office of the Comptroller of Currency (OCC) - Enforcement Actions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Office of Foreign Assets Control (OFAC) - Specially Designated Nationals List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Office of Inspector General (OIG) - Health and Human Services Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Office of Inspector General (OIG) - Most Wanted Health Care Fugitives </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Office of Regulatory Affairs - Disqualified, Restricted and Assurances List for Clinical Investigators </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Office of Research Integrity - Public Health Service - Administrative Actions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Office of the Superintendent of Financial Institutions (OSFI) - Canadian Sanctions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Office of Thrift Supervision (OTS) - Enforcement Actions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Ohio Medicaid - Sanctioned Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Ohio Medicaid - Suspended Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Palestinian Legislative Council List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Pennsylvania Medicheck - Precluded Providers List </a></p> 
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Politically Exposed Persons List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">SEC - Enforcement Actions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">South Carolina Medicaid - Excluded Providers </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Tennessee Department of Health - Abuse Registry </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Texas Employee Misconduct Registry </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">Texas Health and Human Services Commission Medicaid and Title XX Provider Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">TRICARE Sanctions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">UK Disqualified Directors List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">United Nations Consolidated Sanctions List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">US Commodity Futures Trading Commission (CFTC) - Disciplinary Actions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">US Department of Commerce - Denied Persons List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">US Department of State - Foreign Terrorist Organizations List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">US Department of State - Nonproliferation Sanctions </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">US Department of State - Terrorist Exclusion List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">US General Services Administration (GSA) - Excluded Parties List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">World Bank Listing of Ineligible Individuals </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">US Department of Labor - Forced and Child Labor List </a></p>
<p class="datalink"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us">US Department of Treasury - Primary Money Laundering Concern List </a></p>




                       
                       </div>
                    </td>
                  </tr>
                </table>
             </td>
             </tr>
             </table>
            
              </td>
            </tr>
             <script>
					  $(document).ready(function(){
							$("#dataView").hide("slow");
							$("#viewbtn").show("slow");
							$("#viewbtn").click(function () {
							$("#dataView").slideToggle();
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
</form:form>
</jsp:body>
</neon:NewCrimeFunnel>