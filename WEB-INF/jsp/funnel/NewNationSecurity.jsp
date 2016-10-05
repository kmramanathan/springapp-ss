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
font-family:Arial, Helvetica, sans-serif;


}
.style821{margin:0;padding:0}
.padd{padding:0;margin:0}
.style107 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #333333; }
#datediv{display:none;color:red;position:absolute;width:250px;text-align:justify;font-size: 12px;margin-left:100px;margin-top:37px}
#refdiv{display:none;color:red;position:absolute;width:250px;text-align:justify;font-size: 12px;margin-left:100px}
.datalink{padding: 0;margin: 0;}
#dataview p{padding:4px 0 4px 0; margin:0;font-family:Arial, Helvetica, sans-serif;font-size:12px}
#dataview{ font-size:12px;font-family:Arial, Helvetica, sans-serif}
.head{color:#000!important}
</style>
  <form:form name="crimeForm" method="post">
    <form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top">
<td >
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td>
 <h1 class="style59 style92">National Security Search</h1></td>
 <td valign="top">
 <a href="http://www.bbb.org/santa-barbara/business-reviews/internet-services/searchsystems-net-in-newbury-park-ca-13000131/#bbbonlineclick" target="_blank">
 <img src="https://seal-santabarbara.bbb.org/seals/blue-seal-200-42-searchsystems-net-13000131.png" alt="bbb" style="float: right;"></a>
 </td>
</tr>
<tr><td colspan="2">
           <p class="style1111">Search over 75 National, International, and State databases for known terrorists, casino cheats, Interpol fugitives, NCIS & FBI most wanted, debarred parties, sanctioned parties, and persons involved in government fraud, federal banking law violations, money laundering, and illegal imports.</p>
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
                            
                            <p class="style71 style819"><strong>Date of   Birth </strong><strong class="style72">*</strong></p>
                            <!--span class="style71" -->
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
                          <!--/span--></td>
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
					 
                    
                      
                      
                     
                       <div id="dataview"> 
<h2 class="haed">National</h2>
<p><strong>New York Stock Exchange Regulation - Disciplinary Actions</strong></p> 
Data contains over 4,000 individuals and companies who have violated the NYSE rules and applicable federal laws or regulations of the New York Stock Exchange. This data includes NYSE, NYSE Arca, and NYSE Amex disciplinary actions. Data elements include Decision Number, Announcement Date, Name, Position, and Case Type. 
<p><strong>Office of the Comptroller of Currency (OCC) - Enforcement Actions List </strong></p>
US Department of the Treasury list of individuals affiliated with financial institutions against which the OCC has brought enforcement actions due to violations of laws, rules or regulations, unsafe or unsound practices, violations of final orders, violations of conditions imposed in writing and individuals due to breach of fiduciary duty. Date of Birth is not available from this source. 
<p><strong>Office of Foreign Assets Control (OFAC) - Specially Designated Nationals List</strong></p>
The Office of Foreign Assets Control (OFAC) administers a series of laws that impose economic sanctions against hostile targets to further U.S. foreign policy and national security objectives. This listing contains the names and description information on those persons identified by the United States to pose a threat to the interests and security of the United States. This data file also includes narcotics traffickers, terrorists, and business and organizations supporting threatening activities. Date of Birth is available for approximately 45% of the records in this source. 
<p><strong>Office of Inspector General (OIG) - Health and Human Services Exclusion List </strong></p>
Office of the Inspector General List of over 43,000 individuals excluded from participation in Medicare, Medicaid, and other Federally-funded health care programs dating back to 1980. Results may include Name, Address, DOB, Professional Classification, Professional Specialty, Exclusion Type, Exclusion Date and Reinstatement Date. 
<p><strong>Office of Inspector General (OIG) - Most Wanted Health Care Fugitives</strong></p>
Data contains individuals currently listed as the Office of Inspector General Most Wanted Fugitives. Search results may include Name, Date of Birth, Height, Weight, Status, and Summary. 
<p><strong>Office of Regulatory Affairs - Disqualified, Restricted and Assurances List for Clinical Investigators </strong></p>
Contains over 180 clinical investigators who have been disqualified or "totally restricted." FDA may disqualify a clinical investigator if the clinical investigator has repeatedly or deliberately failed to comply with applicable regulatory requirements or the clinical investigator has repeatedly or deliberately submitted false information to the sponsor or, if applicable, to the FDA. A disqualified clinical investigator is not eligible to receive investigational drugs, biologics, or devices.  
<p><strong>Office of Research Integrity - Public Health Service - Administrative Actions List </strong></p>
Includes the names of 39 individuals that have had administrative actions imposed against them by the Office of Research Integrity. Results may include Name, Institution that led investigation, City, State, Effective Date, Expiration Date, Exclusion Dates, and Comments. 
<p><strong>Office of Thrift Supervision (OTS) - Enforcement Actions List</strong></p>
The Office of Thrift Supervision (OTS) is the primary regulator of all federally chartered and many state-chartered thrift institutions. The OTS enforcement actions list is a directory of actions levied against institution-affiliated parties, officers, directors and controlling shareholders of such entities and others. 
<p><strong>US Department of Labor - Forced and Child Labor List </strong></p>
Data contains nations identified by the US Department of Labor to produce goods using forced labor or child labor. Results may include Name, Good, Child Labor, and Forced Labor. 
<p><strong>US Department of Treasury - Primary Money Laundering Concern List</strong></p>
The Primary Money Laundering Concern List is maintained by the US Department of Treasury as a result of US Patriot Act: Section 311. The list contains jurisdictions and financial institutions identified as concerns for money laundering. Results may include Name, Finding Date, Notice of Proposed Rulemaking Date, Final Ruling Date, and Rescinded Date. 
<p><strong>US Commodity Futures Trading Commission (CFTC) - Disciplinary Actions </strong></p>
Data contains individuals or firms who have not paid awards levied against them in proceedings under the CFTC reparations program. The registrations of these individuals or firms have been suspended and they cannot trade on any contract market. Data also includes individuals and entities with administrative sanctions placed against them. Search results may include Name, Alias, CFTC Docket Number, NFA ID Number, Address, Effective Date, Judgment Amount, Interest, Filing Fee, and Explanation. 
<p><strong>US Department of Commerce - Denied Persons List </strong></p>
Data from the U.S. Department of Commerce Bureau of Industry and Security on persons whose export privileges are denied by federal written order. 
<p><strong>US Department of State - Foreign Terrorist Organizations List </strong></p>
Data contains foreign organizations that are designated by the Secretary of State in accordance with section 219 of the Immigration and Nationality Act. These groups/individuals have been identified as participants in terrorist activity and have been designated as a threat to the security of the United States. Search results may include Name, Alternate Name, Most Recent Designation Date, and Comments. 
<p><strong>US Department of State - Nonproliferation Sanctions </strong></p>
Data contains nonproliferation sanctions issued by the US Department of State. The United States imposes sanctions under various legal authorities against foreign individuals, private entities, and governments that engage in proliferation activities. Search results may include Name, Sanctioned By, Sanction Type, Imposed Date, Federal Register Notice, Federal Register Notice Date, and Expiration Date. 

<p><strong>US Department of State - Terrorist Exclusion List </strong></p>
Data contains individuals and organizations identified by the US Department of State to be associated with terrorist activity. Groups or persons appearing on this list have engaged in some form of terrorist activity and are excluded from entering the United States. Search results may include Name, Alternate Name, Exclusion Date, and Comments. 
<p><strong>US General Services Administration (GSA) - Excluded Parties List </strong></p>
List of over 93,000 parties excluded from Federal Procurement and Non - procurement Programs. Identifies those parties excluded, throughout the U.S. Government, from receiving Federal contracts or certain subcontracts and from certain types of Federal financial and non-financial assistance and benefits. Results may include Name, Address, Alias, Offense Code, Agency Contact and Term of Exclusion. 
<p><strong>DEA Diversion Control Program - Admin Actions against Doctors</strong></p>
Data contains administrative actions against doctors issued by the DEA Office of Diversion Control. Search results may include Name, Profession, Administrative Action, Federal Register Number, Admin Action Date, and Description 
<p><strong>DEA Diversion Control Program - Cases against Doctors </strong></p>
Data contains a listing of investigations of physician registrants in which DEA was involved that resulted in the arrest and prosecution of the registrant. Search results may include Name, Age, Address, Arrest Date, Conviction Date, Judicial Status, Offense, DEA Registration Status, and Case Remarks. 
<p><strong>Directorate of Defense Trade Controls - Debarred Parties List </strong></p>
National Security Debarred List consists of individuals who have been convicted of violating or conspiracy to violate the Arms Export Control Act (AECA). These persons are prohibited from participating directly or indirectly in the export of defense articles (including technical data) and defense services. This list is a subset of persons who may be "debarred" or ineligible to participate in the export of defense articles and defense services. 
<p><strong>FDA Office of Regulatory Affairs - Debarment List </strong></p>
FDA list of 200 persons from the Disqualified/Totally Restricted List and Office of Regulatory Affairs Debarment List. Individuals convicted of a felony under Federal law for conduct relating to development or approval of any drug product, or otherwise relating to any drug product under the Federal Food, Drug, and Cosmetic Act appear on the debarment list. Individuals appearing on the disqualified/totally restricted list are restricted from receiving investigational drugs, biologics, or devices if FDA determines that the investigator has repeatedly or deliberately failed to comply with regulatory requirements for studies or has submitted false information to the study's sponsor. 
<p><strong>FDA Office of Regulatory Affairs - Warning Letters</strong></p>
Data contains individuals and companies which have been issued a warning letter from the Food and Drug Administration. The FDA issues a warning letter once they have determined that a manufacturer has significantly violated the FDA regulations. The warning letter identifies the violation and then requires the manufacturer to take immediate corrective action. Search results may include Name, Letter Issued Date, Issuing Agency, Subject, Response Letter Posted, and Closeout Date.  
<p><strong>Federal Deposit Insurance Corporation (FDIC) - Enforcement Decisions and Orders </strong></p>
The FDIC Enforcement Decisions and Orders contain the full text of the formal enforcement actions against financial institutions and individuals for violations of laws, rules, or regulations, unsafe or unsound banking practices, breaches of fiduciary duty, and violations of final orders, conditions imposed in writing or written agreements. This list contains over 2,000 offenders. Results may include Name, Issue Date, Termination Date, Banking Organization, Address, Enforcement Action Taken, and Document Numbers. 
<p><strong>Federal Deposit Insurance Corporation (FDIC ) - Failed Bank List </strong></p>
Data contains nearly 100 failed banks listed with the FDIC. Search results may include Bank Name, City, State, Certification Number, Closing Date, and Updated Date. 

<p><strong>Federal Reserve Board - Enforcement Actions </strong></p>
The Federal Reserve Board takes formal enforcement actions against entities and individuals for violations of laws, rules, or regulations, unsafe or unsound practices, breaches of fiduciary duty, and violations of final orders. Formal enforcement actions include cease and desist orders, written agreements, removal and prohibition orders, and orders assessing civil money penalties. This list contains over 380 offenders. Search results may include Name, Banking Organization, Location, Effective Date, Termination Date, and Enforcement Action. 
<p><strong>FinCEN - Enforcement Actions </strong></p>
Financial Crimes Enforcement Network (FinCEN) Enforcement Actions on individuals and entities who have violated the Bank Secrecy Act requirements of reporting or recordkeeping. Data contains Name, Enforcement Action, Date, and Matter Number. 
<p><strong>FINRA - Disciplinary Actions </strong></p>
Disciplinary actions issued by FINRA (Financial Industry Regulatory Authority) against firms and individuals for violations of FINRA rules; federal securities laws, rules and regulations; and the rules of the Municipal Securities Rulemaking Board (MSRB). Search results may include Name, Type of Disciplinary Action, CRD Number, Position, Address, FINRA Case Number, and Month and Year of Action. 
<p><strong>Fugitive List </strong></p>
Compiled lists containing information on nearly 1,500 fugitives including the Name, Aliases, Place of Birth and Possible Residence. Information is taken from the FBI Most Wanted, U.S. Marshals Most Wanted, Secret Service Most Wanted, FBI Seeking Information List - War on Terrorism, and the DEA Fugitives list. 
<p><strong>Health Resources & Services Administration - Health Education Assistance Loan - Defaulted Borrowers </strong></p>
The Health Education Assistance Loan program provided federal insurance for educational loans to health professions students. The Defaulted Borrowers list contains over 1,000 individuals who have had one or more default claims paid by the Department of Health and Human Services, been excluded from the Medicare program as a result of his or her HEAL default, and have not had the Medicare exclusion stayed, or lifted, by the Office of Inspector General as a result of entering a settlement agreement. Results may include Name, City, State, Amount Due, Discipline, School Name, and Graduation Date. 
<p><strong>HUD - Limited Denials of Participation List </strong></p>
Controlled by the US Department of Housing and Urban Development, the Limited Denials of Participation List contains individuals and entities that have been excluded from further participation in a HUD program area. These exclusions are issued to parties who fail to comply with HUD program standards. Search results may include Name, Address, Disqualification Details, Start Date, End Date, List Date, and Office. 

<p><strong>Immigration and Customs Enforcement (ICE) - Most Wanted </strong></p>
includes the current Most Wanted Fugitives regulated by the US Immigration and Customs Enforcement. These individuals have committed serious crimes in the United States including trafficking, smuggling, murder, and more. Results may include Name, Aliases, Date of Birth, Height, Weight, Eye Color, Hair Color, Registered, Nationality, Place of Birth, Complexion, Identifying Marks, Occupation, Last Known Residence, Offense, Offense Details, and Warrant Information. 
<p><strong>MIPT Terrorism Knowledge Base </strong></p>
Developed by the Memorial Institute for the Prevention of Terrorism (MIPT), the Terrorism Knowledge Base offers in-depth information on global terrorist incidents, terrorism-related court cases, and terrorist groups and leaders. Data also covers the history, affiliations, locations, and tactics of terrorist groups operating across the world, with over 35 years of terrorism incident data and hundreds of group and leader profiles and trials. Results may include Alias, Group, Role, Current Location, Education, Marital Status.
<p><strong>National Credit Union Administration (NCUA) - Administrative Orders </strong></p>
List of individuals against whom formal enforcement orders have been issued by the NCUA pursuant to Section 206 of the Federal Credit Union Act. Results may include Name, Relations to Credit Union, Name of Credit Union and Docket Number. 
<p><strong>Naval Criminal Investigative Service (NCIS) - Most Wanted Fugitives </strong></p>
Individuals currently wanted by the Naval Criminal Investigative Service (NCIS). Search results may include Name, DOB, Physical Description, Offense.
<p><strong>Politically Exposed Persons List </strong></p>
The Central Intelligence Agency publishes names of persons who may have politically damaging information in the public view. The directory is intended to be used primarily as a reference aid and includes as many governments of the world as is considered practical, some of them not officially recognized by the United States. Regimes with which the United States has no diplomatic exchanges are indicated by the initials NDE. Politically Exposed Persons List data may include Position, Position Holder, and Country. 
<p><strong>SEC - Enforcement Actions</strong></p>
Individuals and firms who have had federal court actions taken against them by the Securities and Exchange Commission. Search results may include Name, Release Date, and Release Number. 
<p><strong>TRICARE Sanctions List </strong></p>
Individuals who have been sanctioned from participating in TRICARE, a health care provider for the military. Search results may contain Name, Address, Medical Center, Offense Description, Action Taken, Notice Date, and Term. 
<p><strong>America's Most Wanted Fugitive List </strong></p>
Individuals who are currently listed as America's Most Wanted Fugitives. Search results may include Name, Age, Physical Characteristics, Last Known Location Information, Offense, and Prior Conviction Information. 
<h2 class="haed">International</h2>
<p><strong>United Nations Consolidated Sanctions List </strong></p>
Individuals upon whom United Nations Sanctions have been imposed based on new anti-terrorism measures. 
<p><strong>World Bank Listing of Ineligible Individuals </strong></p>
Individuals who are ineligible to be awarded World Bank-financed contacts because of violation of the bank's fraud and corruption policies. 
<p><strong>Interpol Most Wanted </strong></p>
Persons are wanted by national jurisdictions (or the International Criminal Tribunals, where appropriate) and Interpol's role is to assist the national police forces in identifying or locating those persons with a view to their arrest and extradition. 
<p><strong>Australia Registered Offender Registry </strong></p>
Individuals convicted of a registered offense, murder of a child, and other forms of child abuse in Australia. This list is compiled by M.A.K.O, a non-profit organization, and does not contain a complete list of Australian offenders. Search results may include Name, Age, Offense, Occupation, Sentence, and Last Known Location. 
<p><strong>Australian Department of Foreign Affairs and Trade - Sanctions List </strong></p>
The Australian Department of Foreign Affairs and Trade maintains a list of individuals and entities that have been suspected or identified by the United Nations or Australia as terrorists. The moment an individual or entity is placed on this list, its assets must be frozen under Australian law. Asset holders are under an obligation to freeze the assets or economic resources of persons involved in terrorism as listed in the Consolidated List. 
<p><strong>Australian Reserve Bank Sanctions List </strong></p>
List published by Australian Reserve Bank in an effort to freeze the assets of known terrorists. The list includes individuals listed in the UN Consolidated list, plus unique individuals designated by Australia's Minister for Foreign Affairs. 
<p><strong>Bank of England Sanctions List </strong></p>
List published by The Bank of England, the central bank of the United Kingdom, containing the names of individuals against which financial sanctions have been imposed. List contains detailed information about each entity and may include Name, Address and DOB. Date of Birth or Year of Birth is available for approximately 35% of the records from this source. Please note: Financial Sanctions in United Nations, European Union and United Kingdom are now maintained by the HM Treasury. All current sanctions will be available in the HM Treasury - Consolidated List of Financial Sanctions data. 

<p><strong>Canadian Sanctions List - Office of the Superintendent of Financial Institutions (OSFI) </strong></p>
List of names, maintained by Canada's Office of the Superintendent of Financial Institutions, of individuals sanctioned under new Canadian and United Nations anti-terrorism measures.
<p><strong>European Union Terrorism Sanctions List</strong></p>
List, maintained by the European Union, of known terrorists subject to financial sanctions.  
<p><strong>HM Treasury - Consolidated List of Financial Sanctions</strong></p>
Maintained by the HM Treasury, this data contains a consolidated list of asset freeze targets designated by the United Nations, European Union and United Kingdom under legislation relating to current financial sanctions regimes. 
<p><strong>HM Treasury - Investment Ban List </strong></p>
List of investment ban targets designated by the European Union under legislation relating to current financial sanctions regimes. 
<p><strong>Hong Kong Securities and Futures Commission (SFC) - Enforcement Actions </strong></p>
Hong Kong Securities and Futures Commission enforcement actions taken against individuals and entities in Hong Kong. These records represent nearly 2,000 individuals or entities that have been investigated by the SFC for situations involving crime and/or misconduct in the securities and futures industry. These records contain Name, Date, and Enforcement Action. 
<p><strong>Monetary Authority of Singapore - Enforcement Actions</strong></p>
Individuals and entities that have had enforcement actions taken against them by the Monetary Authority of Singapore. Enforcement actions can be imposed on an individual or entity for several reasons including a breach of law or regulation or failure to take remedial action after concerns have been addressed by the MAS. Search results may contain name, enforcement action date, and details regarding the action taken. 
<p><strong>Palestinian Legislative Council List </strong></p>
United States Department of the Treasury list of 130 Palestinian Legislative Council Members who were elected on the party slate of a foreign terrorist organization, are on the Specially Designated Terrorists or Specially Designated Global Terrorist Lists. These individuals are not included on the Designated Nationals or Blocked Persons Lists. Transactions involving these individuals must be rejected. 
<p><strong>UK Disqualified Directors List</strong></p>
Individuals who have been disqualified under the Company Directors Disqualification Act 1986. This disqualifies a person from: acting as a director of a company; taking part, directly or indirectly, in the promotion, formation or management of a company; being a liquidator or an administrator of a company; and being a receiver or manager of a company's property.  
<h2 class="haed">State</h2>
<p><strong>Alabama Medicaid - Suspended Providers </strong></p>
Individuals and entities that have been excluded from participating and/or providing services in the Medicaid program in Alabama. Data contains Name, Occupation, Suspension Date, and Suspension Agency. 
<p><strong>California Medi-Cal - Suspended and Ineligible Providers </strong></p>
Individuals suspended or ineligible from providing services under the California Medi-Cal program. Search results may include Physician Name, Address, Provider Number, Action Description, and Action Date. 
<p><strong>Connecticut Department of Social Services - Admin Actions List</strong></p>
List of providers found to be in violation of their provider agreements who are suspended from Connecticut Medical Assistance Programs. In certain situations violators may also be sanctioned by the Federal Government, Department of Health and Human Services, Office of the Inspector General, which results in a provider's exclusion from participation in Federal Healthcare Programs including Medicare and Medicaid. Data may contain Individual Name, Business Name, Specialty, Address, Effective Date of Action, Period of Action, and Description of Action. 
<p><strong>Delaware Adult Abuse Registry</strong></p>
Individuals currently listed on the Delaware Adult Abuse Registry, maintained by the Delaware Health and Social Services. Search results may include Name, ID Number, License Number, Registration Date, Removal Date, and Type of Abuse. 
<p><strong>Florida Medicaid - Sanctioned Providers </strong></p>
Over 250 individuals and providers that were sanctioned or terminated while rendering services for the Florida Medicaid program. Search results may include Name, Provider Type, Address, License Number, Imposed Sanction Date, Violation Code, Sanction Type, and Fine Amount. 
<p><strong>Hawaii Medicaid - Excluded Providers</strong></p>
Individuals and entities who have been excluded from participating in providing services in the Medicaid program in Hawaii. Data contains Name, Medicaid Provider ID Number, Program/Provider Type, Exclusion Date, and Reinstatement Date. 
<p><strong>Idaho Medicaid Provider - Exclusion List</strong></p>
Over 100 individuals and entities who have been excluded from participating in the Medicaid program in Idaho. Data contains current and previous exclusions dating back to the 1990s. Search results may include Name, Exclusion Date, Period of Exclusion, and Additional Information. 
<p><strong>Illinois Casino Exclusion List </strong></p>
Individuals currently excluded by the Illinois Gaming Board from participation in Illinois gaming activities. Search results may include Name, DOB, Physical Description, Aliases, Exclusion Date, and Reason for Exclusion. 
<p><strong>Illinois Office of Inspector General - Sanctioned Providers</strong></p>
Maintained by the Illinois Healthcare and Family Services Office of Inspector General, this list contains over 1,000 individuals and entities that are currently sanctioned from participating in the Medicaid program of Illinois. Search results may include Name, Address, License Number, Affiliation, Action Date, and Action Type. 
<p><strong>Kansas Department of Health and Environment - Abuse Registry </strong></p>
Data contains a list of individuals with findings of abuse, neglect, or exploitation. This list is maintained by the Kansas Department of Health and Environment. Search results may include Name, DOB, Offense, and Registration Date. 
<p><strong>Kentucky Medicaid - Excluded Providers </strong></p>
Contains individuals and entities excluded from participation and/or providing services under the Medicaid program in Kentucky. Data may contain Name, Practice Name and Termination Date. 
<p><strong>Maryland Medicaid - Exclusion List</strong></p> 
Over 1,000 individuals and entities that have been excluded from participation in the Medicaid program in Maryland. Search results may include Name, Address, License Number, Specialty, Sanction Date, and Sanction Type. 
<p><strong>Mississippi Medicaid - Excluded Providers </strong></p>
Individuals and entities excluded from participation and/or providing services under the Medicaid program in Mississippi. Data may contain Name, Practice Name, Specialty, Address and Suspension/Sanction Period. 
<p><strong>Missouri Casino Exclusion List </strong></p>
Individuals currently excluded by the Missouri Gaming Commission from participation in riverboat gambling operations in Missouri and Illinois. Search results may include Name, DOB, Address, Physical Description, Exclusion Date, Exclusion State, and Record Date. 
<p><strong>Nevada Gaming Control Board - Excluded Person List </strong></p>
Individuals listed on the Nevada Gaming Control Board Excluded Person List, also known as Nevada's Black Book. Search results may include Name, DOB, Alias, Registered, Race, Height, Weight, Hair Color, Eye Color, Place of Birth, Last Known Location, Occupation, Listed Date, Date, and Comments. 
<p><strong>New Jersey Casino Exclusion List </strong></p>
Individuals currently listed on the New Jersey Casino Control Commission Exclusion List. These individuals are to be excluded or ejected from any licensed casino in New Jersey, such as career or professional offenders, cheats or criminal offenders. Search results may include Name, DOB, Physical Description, Effective Date, Address, and Exclusion Description. 
<p><strong>New Jersey Department of Treasury - Debarment List </strong></p>
Maintained by the New Jersey Department of Treasury, this list contains individuals and entities that have been suspended, disqualified, or otherwise debarred from providing goods or services to the state of New Jersey. The majority of these records involve debarment records from the medical profession but may also include construction and vendor professions. Search results may include Name, Address, Debarment Reason, Debarring Agency, Effective Date, and Expiration Date. 
<p><strong>New York Office of the Medicaid Inspector General - Exclusion List </strong></p>
List of individuals and entities whose participation in the New York Medicaid program has been restricted, terminated or excluded under the provisions of 18 NYCRR 504.7(b) - (h), 515.3, or 515.7. Other healthcare providers may also be included on this list if they have failed to meet certain prescribed requirements and are therefore precluded from ordering or prescribing care, services or supplies, exclusive of in-patient hospital care. Search results may contain Name, Provider Type, Provider Number, License Number, County, Action Date, and Action Type. 
<p><strong>Ohio Medicaid - Sanctioned Providers</strong></p>
Individuals and entities that have been excluded from participating in and/or providing services in the Medicaid program in Ohio. This list does NOT include voluntary resignations. Data may include Name, Provider Type, Address, Medicaid Provider Number, Date of Termination/Exclusion, Reason for Termination/Exclusion and Reinstatement Date. 
<p><strong>Ohio Medicaid - Suspended Providers</strong></p>
Non-institutional and non-agency individuals and entities whose Medicaid provider agreements have been suspended due to indictment of the Medicaid provider, its owner or owners, officer, authorized agent, associate, manager , or employee for committing an offense that would be a felony or misdemeanor under the laws of Ohio and the act relates to or results from prescribing, furnishing or billing for medical care, services, or supplies under the Medicaid program; or participating in the performance of management of administrative services relating to prescribing, billing, or furnishing medical care, services, or supplies under the Medicaid program. Suspensions remain in force until the criminal proceedings are completed. Data may contain Name, Company Name, Address, Provider Type, Indictment Date, Indictment Code and Final Disposition.  
<p><strong>Pennsylvania Medicheck - Precluded Providers List </strong></p>
Nearly 2,000 providers, individuals, and other entities that are precluded from participation in the Medical Assistance (MA) Program. Data may contain Provider Name, Business Name, License Number, Status, Begin Date, End Date, County, List Date and NPI number. 
<p><strong>South Carolina Medicaid - Excluded Providers</strong></p>
Individuals and entities that have been excluded from participation in South Carolina's Medicaid programs by the federal government and/or the State of South Carolina. Results may include Name, NPI, City, State, Zip, Profession and Date of Exclusion. 
<p><strong>Tennessee Department of Health - Abuse Registry </strong></p>
Individuals who are currently required to registry with the Tennessee Department of Health, Abuse Registry. These individuals have been investigated and determined to have abused, neglected, or misappropriated personal property of a vulnerable person in the state of TN. Search results may include Name, Date of Birth, File Number, License Number, Registration Date, and Reporting Department. 
<p><strong>Texas Employee Misconduct Registry </strong></p>
Individuals who are currently listed on the Employee Misconduct Registry, maintained by the Texas Department of Aging and Disability Services. Individuals listed on the registry include unlicensed personnel who have committed acts of abuse, neglect, exploitation, misappropriation, or misconduct against residents and consumers and are denied employment in DADS-regulated facilities and agencies. Search results may include Name, Address, and Registry Date. 
<p><strong>Texas Health and Human Services Commission Medicaid and Title XX Provider Exclusion List</strong></p>
Over 6,000 individuals affected by various legal authorities, including; convictions for program-related fraud and patient abuse, licensing board actions, U.S. Health & Human Services OIG (Medicare) exclusion actions, and permissible exclusions as allowed by various legal authorities. Results may include Name, License Number, Start Date, Add Date, Reinstated Date, and Comments. 

 


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