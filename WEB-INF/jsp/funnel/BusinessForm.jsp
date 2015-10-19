<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:NewFunnel title="Create Business Account">
<jsp:attribute name="stylesheet">new-funnel.css,SpryValidationTextarea.css</jsp:attribute>
<jsp:attribute name="javascript">SpryValidationTextarea.js</jsp:attribute>
<jsp:body>

<style type="text/css">
.stylere1 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight:bold;
	margin:0;
	padding-bottom:3px;
}
.stylere2 {color: #003366; font-family: Arial, Helvetica, sans-serif;}
.stylere3 {	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #003366;
}
.stylere4 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; }
.stylere5 {	font-size: 12px;
	color: #FF0000;
	font-family: Arial, Helvetica, sans-serif;
}
.stylere6 {color: #FF0000; padding:0;}
.stylere69 {font-family: Arial, Helvetica, sans-serif; font-size: 11px; }
.stylere70 {font-size: 11px}
.stylere85 {	font-family: Arial, Helvetica, sans-serif;
	font-size: 10px;
}
.stylere86 {	color: #FF0000;
	font-weight: bold;
}
.stylere87 {
	color: #009933;
	font-weight: bold;
}
.stylere67 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #000000;
	font-size: 12px;
}
.stylere88 {font-family: Arial, Helvetica, sans-serif}
.formfield{ background:#ffffff; height:22px; border:1px solid red;}
.userinfo td{ padding:0; margin:0; padding:0px 5px 0px 5px;}
.errorMsg{ font-size:12px; color:#ff0000;}
</style>
                        <!--// content begins //-->

<!-- CONTENT GOES HERE -->

<form:form method="post">


<tr>
		<td width="893" height="357" style="height:100%; padding: 10px 0;border: 2px">
		
		  <form:errors path="*">
			<b>Please correct the following errors:</b><br/>
		 </form:errors>
		 <form:errors path="*" element="div" id="error" cssClass="errorMsg"/>
		
		<table width="890">
          <tr>
            <td width="882"><table width="818">
                <tr>
                  <td><h2 class="stylere1">Create Your Free Business Account</h2>
                    <p class="stylere2">Please complete this form in order to purchase your search results and register for FREE to SearchSystems.net.</p>
                    
                  </td>
                </tr>
              </table>
              <table width="818">
              <tr>
                <td width="810" height="731"><table width="527" border="0">
                      <tbody>
                        <tr>
                          <td width="394"><div align="left" class="stylere4">Enter a username and password for your account.</div></td>
                          <td width="201"><div align="right" class="stylere5">* Required Fields</div></td>
                        </tr>
                      </tbody>
                    </table>
                   <table width="802" border="0" cellpadding="0" cellspacing="0" class="userinfo">
                      <tbody>
                      <tr><td colspan="3" height="15"></td></tr>
                        <tr>
                          <td width="128" height="62"><div align="right"  class="stylere4"><span class="stylere6">*</span>Username:</div></td>
                          <td width="280" align="left"><form:input path="username" size="40" cssClass="formfield"/></td>
                          <td width="382" ><span class="stylere69">Your  username can be from 6-20 characters. It must start with a letter, and  may contain only letters (A-Z, a-z) and numbers (0-9). Your username  may contain capital letters but it is not case sensitive. </span></td>
                        </tr>
                        <tr>
                        
                            <td ><div align="right" class="stylere4" height="0"><span class="stylere6">*</span>Password:</div></td>
                            <td ><form:password path="password" size="40" cssClass="formfield" /></td>
                           <td rowspan="2"><span class="stylere4" height="0"><span class="stylere70">Your  password can be from 6-20 characters. It may contain only letters (A-Z,  a-z) and numbers (0-9). Your password IS case sensitive, so be sure to  use the correct case where necessary. Your password must have at least  1 lowercase letter, at least 1 uppercase letter, and at least 1 number</span>. </span></td>
                          </tr>
                           <tr>
                              <td  ><div align="right" class="stylere4"><span class="stylere6">*</span>Confirm Password:</div></td>
                              <td ><form:password path="confirmPassword" cssClass="formfield" size="40" /></td>
                            
                            </tr>
                      </tbody>
                    </table>
                    <div> &nbsp; </div>
                <h3 align="left" class="stylere3">Personal Information</h3>
                  <p align="left" class="stylere4"> Please enter your personal information including your email address below. We  never share your email address. See our <a target="_blank" href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=privacy">Privacy Policy</a>.  If you have a spam filter, please make sure that you can accept emails from searchsystems.net. </p>
                  
                  <table width="468" border="0">
                      <tbody>
                        <tr>
                          <td width="138"><div align="right" class="stylere4"><span class="stylere6">*</span>First Name:</div></td>
                          <td width="318"><form:input path="firstName" cssClass="formfield" size="40" /></td>
                        </tr>
                      </tbody>
                    </table>
                  <table width="468" border="0">
                      <tbody>
                        <tr>
                          <td width="138"><div align="right" class="stylere4"><span class="stylere6">*</span>Last Name:</div></td>
                          <td width="318"><form:input path="lastName" cssClass="formfield" size="40" /></td>
                        </tr>
                      </tbody>
                    </table>
                  <table width="468" border="0">
                      <tbody>
                        <tr>
                          <td width="138"><div align="right" class="stylere4"><span class="stylere6">*</span>E-mail:</div></td>
                          <td width="318"><form:input path="email" cssClass="formfield" size="40" /></td>
                        </tr>
                      </tbody>
                    </table>
                  <table width="468" border="0">
                      <tbody>
                        <tr>
                          <td width="138"><div align="right" class="stylere4"><span class="stylere6">*</span>Confirm E-mail:</div></td>
                          <td width="318"><form:input path="confirmEmail" cssClass="formfield" size="40" /></td>
                        </tr>
                      </tbody>
                    </table>
                  <div> &nbsp; </div>
                  <h3 align="left" class="stylere3">Secret Question</h3>
                  <p align="left" class="stylere4"> Select a secret question from our list below. Make sure to choose a question  that only you know the answer to. </p>
                 
                  <table width="468" border="0">
                      <tbody>
                        <tr>
                          <td width="164"><div align="right" class="stylere4"><span class="stylere6">*</span>Secret Question:</div></td>
                          <td width="292"><form:select path="secretQuestionId"  cssClass="formfield">
						<form:option value="-">Please select</form:option>
						<form:options items="${secretQuestions}"/>
                      </form:select></td>
                        </tr>
                      </tbody>
                    </table>
                  <table width="804" border="0">
                      <tbody>
                        <tr>
                          <td width="158"><div align="right" class="stylere4"><span class="stylere6">*</span>Secret Question Answer:</div></td>
                          <td width="299"><form:input path="secretQuestionAnswer" cssClass="formfield" size="40" /></td>
                          <td width="333"><span class="stylere69">Your answer may contain only letters (A-Z, a-z), numbers (0-9) and spaces. Your  answer IS case sensitive. </span></td>
                        </tr>
                      </tbody>
                    </table>
                  <div>&nbsp;</div>
                  <table width="469">
                  <tr><td colspan="2"> <h3 class="style60">FCRA Requirements</h3>
                   <p class="style61"><span id="sprytextarea1">
                    <label>
                    <textarea name="textarea1" id="textarea1" cols="120" rows="10">All users subject to the Federal Trade Commission's jurisdiction must comply with all applicable regulations, including regulations promulgated after this notice was prescribed in 2004.  Information about applicable regulations currently in effect can be found at the Commission's Web site, www.ftc.gov/credit.  Persons not subject to the Commission's jurisdiction should consult with their regulators to find any relevant regulations.

NOTICE TO USERS OF CONSUMER REPORTS:
OBLIGATIONS OF USERS UNDER THE FCRA
   The Fair Credit Reporting Act (FCRA), 15 U.S.C. 1681 - 1681y, requires that this notice be provided to inform users of consumer reports of their legal obligations.  State law may impose additional requirements.  The text of the FCRA is set forth in full at the Federal Trade Commission's Website at www.ftc.gov/credit.  At the end of this document is a list of United States Code citations for the FCRA.  Other information about user duties is also available at the Commission's Web site.  Users must consult the relevant provisions of the FCRA for details about their obligations under the FCRA.

	The first section of this summary sets forth the responsibilities imposed by the FCRA on all users of consumer reports.  The subsequent sections discuss the duties of users of reports that contain specific types of information, or that are used for certain purposes, and the legal consequences of violations.  If you are a furnisher of information to a consumer reporting agency (CRA), you have additional obligations and will receive a separate notice from the CRA describing your duties as a furnisher.

I.  OBLIGATIONS OF ALL USERS OF CONSUMER REPORTS

A. Users Must Have a Permissible Purpose

Congress has limited the use of consumer reports to protect consumers' privacy.  All users must have a permissible purpose under the FCRA to obtain a consumer report.  Section 604 contains a list of the permissible purposes under the law.  These are:
* As ordered by a court or a federal grand jury subpoena.  Section 604(a)(1)
* As instructed by the consumer in writing.  Section 604(a)(2)
* For the extension of credit as a result of an application from a consumer or the review or collection of a consumer's account.  Section 604(a)(3)(A)
* For employment purposes, including hiring and promotion decisions, where the consumer has given written permission.  Sections 604(a)(3)(B) and 604(b)
* For the underwriting of insurance as a result of an application from a consumer.  Section 604(a)(3)(C)
* When there is a legitimate business need, in connection with a business transaction that is initiated by the consumer.  Section 604(a)(3)(F)(i)
* To review a consumer's account to determine whether the consumer continues to meet the terms of the account.  Section 604(a)(3)(F)(ii)
* To determine a consumer's eligibility for a license or other benefit granted by a governmental instrumentality required by law to consider an applicant's financial responsibility or status.  Section 604(a)(3)(D)
* For use by a potential investor or servicer, or current insurer, in a valuation or assessment of the credit or prepayment risks associated with an existing credit obligation.  Section 604(a)(3)(E)
* For use by state and local officials in connection with the determination of child support payments, or modifications and enforcement thereof.  Section 604(a)(4) and 604(a)(5)

       In addition, creditors and insurers may obtain certain consumer report information for the purpose of making "prescreened" unsolicited offers of credit or insurance.  Section 604(c).  The particular obligations of users of "prescreened" information are described in Section VII below.
       
B. Users Must Provide Certifications

    Section 604(f) prohibits any person from obtaining a consumer report from a consumer reporting agency (CRA) unless the person has certified to the CRA the permissible purpose(s) for which the report is being obtained and certifies that the report will not be used for any other purpose.  
    
C. Users Must Notify Consumers When Adverse Actions Are Taken

    The term "adverse action" is defined very broadly by Section 603.  "Adverse actions" include all business, credit, and employment actions affecting consumers that can be considered to have a negative impact as defined by Section 603(k) of the FCRA - such as denying or canceling credit or insurance or denying employment or promotion.  No adverse action occurs in a credit transaction where the creditor makes a counteroffer that is accepted by the consumer. 
    
1. Adverse Actions Based on Information Obtained From a CRA

    If a user takes any type of adverse action as defined by the FCRA that is based at least in part on information contained in a consumer report, Section 615(a) requires the user to notify the consumer.  The notification may be done in writing, orally, or by electronic means.  It must include the following:
* The name, address, and telephone number of the CRA (including a toll-free telephone number, if it is a nationwide CRA) that provided the report.
* A statement that the CRA did not make the adverse decision and is not able to explain why the decision was made.
* A statement setting forth the consumer's right to obtain a free disclosure of the consumer's file from the CRA if the consumer makes a request within 60 days.
* A statement setting forth the consumer's right to dispute directly with the CRA the accuracy or completeness of any information provide by the CRA.

2. Adverse Actions Based on Information Obtained From Third Parties Who Are Not Consumer Reporting Agencies

 	If a person denies (or increases the charge for) credit for personal, family, or household purposes based either wholly or partly upon information from a person other than a CRA, and the information is the type of consumer information covered by the FCRA, Section 615(b)(1) requires that the user clearly and accurately disclose to the consumer his or her right to be told the nature of the information that was relied upon if the consumer makes a written request within 60 days of notification.  The user must provide the disclosure within a reasonable period of time following the consumer's written request.

3. Adverse Actions Based on Information Obtained From Affiliates

    If a person takes an adverse action involving insurance, employment, or a credit transaction initiated by the consumer, based on information of the type covered by the FCRA, and this information was obtained from an entity affiliated with the user of the information by common ownership or control, Section 615(b)(2) requires the user to notify the consumer of the adverse action.  The notice must inform the consumer that he or she may obtain a disclosure of the nature of the information relied upon by making a written request within 60 days of receiving the adverse action notice.  If the consumer makes such a request, the user must disclose the nature of the information not later than 30 days after receiving the request.  If consumer report information is shared among affiliates and then used for an adverse action, the user must make an adverse action disclosure as set forth in I.C.1 above.  
    
    
D. Users Have Obligations When Fraud and Active Duty Military Alerts are in Files

    When a consumer has placed a fraud alert, including one relating to identity theft, or an active duty military alert with a nationwide consumer reporting agency as defined in Section 603(p) and resellers, Section 605A(h) imposes limitations on users of reports obtained from the consumer reporting agency in certain circumstances, including the establishment of a new credit plan and the issuance of additional credit cards.  For initial fraud alerts and active duty alerts, the user must have reasonable policies and procedures in place to form a belief that the user knows the identity of the applicant or contact the consumer at a telephone number specified by the consumer; in the case of extended fraud alerts, the user must contact the consumer in accordance with the contact information provided in the consumer's alert.
    
E. Users Have Obligations When Notified of an Address Discrepancy

    Section 605(h) requires nationwide CRAs, as defined in Section 603(p), to notify users that request reports when the address for a consumer provided by the user in requesting the report is substantially different from the addresses in the consumer's file.  When this occurs, users must comply with regulations specifying the procedures to be followed, which will be issued by the Federal Trade Commission and the banking and credit union regulators.  The Federal Trade Commission's regulations will be available at www.ftc.gov/credit.
    
F. Users have Obligations When Disposing of Records

    Section 628 requires that all users of consumer report information have in place procedures to properly dispose of records containing this information.  The Federal Trade Commission, the Securities and Exchange Commission, and the banking and credit union regulators have issued regulations covering disposal.  The Federal Trade Commission's regulations may be found at www.ftc.gov/credit.

II. CREDITORS MUST MAKE ADDITIONAL DISCLOSURES

       If a person uses a consumer report in connection with an application for, or a grant, extension, or provision of, credit to a consumer on material terms that are materially less favorable than the most favorable term available to a substantial proportion of consumers from or through that person, based in whole or in part on a consumer report, the person must provide a risk-based pricing notice to the consumer in accordance with regulations to be jointly prescribed by the Federal Trade Commission and the Federal Reserve Board.
       
       Section 609(g) requires a disclosure by all persons that make or arrange loans secured by residential real property (one to four units) and that use credit scores.  These persons must provide credit scores and other information about credit scores to applicants, including the disclosure set forth in Section 609(g)(1)(D) ("Notice to the Home Loan Applicant").  
       
III. OBLIGATIONS OF USERS WHEN CONSUMER REPORTS ARE OBTAINED FOR EMPLOYMENT PURPOSES.

A.  Employment Other Than in the Trucking Industry

	If information from a CRA is used for employment purposed, the user has specific duties, which are set forth in Section 604(b) of the FCRA.  The user must: 
* Make a clear and conspicuous written disclosure to the consumer before the report is obtained, in a document that consists solely of the disclosure, that a consumer report may be obtained.
* Obtain from the consumer prior written authorization.  Authorization to access reports during the term of employment may be obtained at the time of employment. 
* Certify to the CRA that the above steps have been followed, that the information being obtained will not be used in violation of any federal or state equal opportunity law or regulation, and that, if any adverse action is to be taken based on the consumer report, a copy of the report and a summary of the consumer's rights will be provided to the consumer.
* Before taking an adverse action, the user must provide a copy of the report to the consumer as well as the summary of consumer's rights.  (The user should receive this summary from the CRA.)  A Section 615(a) adverse action notice should be sent after the adverse action is taken.  

       An adverse action notice also is required in employment situations if credit information (other than transactions and experience data) obtained from an affiliate is used to deny employment.  Section 615(b)(2)

       The procedures for investigative consumer reports and employee misconduct investigations are set forth below.
       
       B.  Employment in the Trucking Industry

	Special rules apply for truck drivers where the only interaction between the consumer and the potential employer is by mail, telephone, or computer.  In the case, the consumer may provide consent orally or electronically, and an adverse action may be made orally, in writing, or electronically.  The consumer may obtain a copy of any report relied upon by the trucking company by contacting the company.

IV. OBLIGATIONS WHEN INVESTIGATIVE CONSUMER REPORTS ARE USED

	Investigative consumer reports are a special type of consumer report in which information about a consumer's character, general reputation, personal characteristics, and mode of living is obtained through personal interviews by an entity or person that is a consumer reporting agency.  Consumers who are the subjects of such reports are given special rights under the FCRA.  If a user intends to obtain an investigative consumer report, Section 606 requires the following:
* The user must disclose to the consumer that an investigative consumer report may be obtained.  This must be done in a written disclosure that is mailed, or otherwise delivered, to the consumer at some time before or not later than three days after the date on which the report was first requested.  The disclosure must include a statement informing the consumer of his or her right to request additional disclosures of the nature and scope of the investigation as described below, and the summary of consumer rights required by Section 609 of the FCRA.  (The summary of consumer rights will be provided by the CRA that conducts the investigation.)
* The user must certify to the CRA that the disclosures set forth above have been made and that the user will make the disclosure described below.
* Upon the written request of a consumer made within a reasonable period of time after the disclosures required above, the user must make a complete disclosure of the nature and scope of the investigation.  This must be made in a written statement that is mailed, or otherwise delivered, to the consumer no later than five days after the date on which the request was received from the consumer or the report was first requested, whichever is later in time.


V. SPECIAL PROCEDURES FOR EMPLOYEE INVESTIGATIONS

       Section 603(x) provides special procedures for investigations of suspected misconduct by an employee or for compliance with Federal, state or local laws and regulations or the rules of a self-regulatory organization, and compliance with written policies of the employer.  These investigations are not treated as consumer reports so long as the employer or its agent complies with the procedures set forth in Section 603(x), and a summary describing the nature and scope of the inquiry is made to the employee if an adverse action is taken based on the investigation. 

VI.  OBLIGATIONS OF USERS OF MEDICAL INFORMATION

       Section 604(g) limits the use of medical information obtained from consumer reporting agencies (other than payment information that appears in a coded form that does not identify the medical provider).  If the information is to be used for an insurance transaction, the consumer must give consent to the user of the report or the information must be coded.  If the report is to be used for employment purposes - or in connection with a credit transaction (except as provided in regulations issued by the banking and credit union regulators) - the consumer must provide specific written consent and the medical information must be relevant.  Any user who receives medical information shall not disclose the information to any other person (except where necessary to carry out the purpose for which the information was disclosed, or as permitted by statute, regulation, or order).

VII. OBLIGATIONS OF USERS OF "PRESCREENED" LISTS

       The FCRA permits creditors and insurers to obtain limited consumer report information for use in connection with unsolicited offers of credit or insurance under certain circumstances.  Sections 603(l), 604(c), 604(e) and 615(d).  This practice is known as "prescreening" and typically involves obtaining from a CRA a list of consumers who meet certain pre-established criteria.  If any person intends to use prescreened lists, that person must (1) before the offer is made, establish the criteria that will be relied upon to make the offer and to grant credit or insurance, and (2) maintain such criteria on file for a three-year period beginning on the date on which the offer is made to each consumer.  In addition, any user must provide with each written solicitation a clear and conspicuous statement that:
* Information contained in a consumer's CRA file was used in connection with the transaction.
* The consumer received the offer because he or she satisfied the criteria for credit worthiness or insurability used to screen for the offer.
* Credit or insurance may not be extended if, after the consumer responds, it is determined that the consumer does not meet the criteria used for screening or any applicable criteria bearing on credit worthiness or insurability, or the consumer does not furnish required collateral.
* The consumer may prohibit the use of information in his or her file in connection with future prescreened offers of credit or insurance by contacting the notification system established by the CRA that provided the report.  The statement must include the address and toll-free telephone number of the appropriate notification system.  
       
       In addition, once the Federal Trade Commission by rule has established the format, type size, and manner of the disclosure required by Section 615(D), users must be in compliance with the rule.  The FTC's regulations will be at www.ftc.gove/credit. 

VIII. OBLIGATIONS OF RESELLERS

A. Disclosure and Certification Requirements
       Section 607(e) requires any person who obtains a consumer report for resale to take the following steps:  
* Disclose the identity of the end-user to the source CRA.
* Identity to the source CRA each permissible purpose for which the report will be furnished to the end-user.
* Establish and follow reasonable procedures to ensure that reports are resold only for permissible purposes, including procedures to obtain:
ii. the identity of all end-users:
iii. certifications from all users of each purpose for which reports will be used;
	      and 
iv. certifications that reports will not be used for any purpose other than the purpose(s) specified to the reseller.  Resellers must make reasonable efforts to verify this information before selling the report.  

B.  Reinvestigations by Resellers

       Under Section 611(f), if a consumer disputes the accuracy or completeness of information in a report prepared by a reseller, the reseller must determine whether this is a result of an action or omission on its part and, if so, correct or delete the information.  If not, the reseller must send the dispute to the source CRA for reinvestigation.  When any CRA notifies the reseller of the results of an investigation, the reseller must immediately convey the information to the consumer.
       
C. Fraud Alerts and Resellers

       Section 605A(f) requires resellers who receive fraud alerts or active duty alerts from another consumer reporting agency to include these in their reports.

IX. LIABILITY FOR VIOLATIONS OF THE FCRA

       Failure to comply with the FCRA can result in state government or federal government enforcement actions, as well as private lawsuits.  Sections 616, 617, and 621.  In addition, any person who knowingly and willfully obtains a consumer report under false pretenses may face criminal prosecution.  Section 619.

The FTC's Web site, www.ftc.gov/credit, has more information about the FCRA, including publications for businesses and the full text of the FCRA.

Citations for FCRA sections in the U.S. Code, 15 U.S.C.  1681 et seq.:

Section 602	15 U.S.C. 1681              Section 603	15 U.S.C. 1681a
Section 604	15 U.S.C. 1681b             Section 605	15 U.S.C. 1681c
Section 605A	15 U.S.C. 1681cA        Section 605B	15 U.S.C. 1681cB
Section 606	15 U.S.C. 1681d             Section 607	15 U.S.C. 1681e
Section 608	15 U.S.C. 1681f             Section 609	15 U.S.C. 1681g
Section 610	15 U.S.C. 1681h             Section 611	15 U.S.C. 1681i
Section 612	15 U.S.C. 1681j             Section 613	15 U.S.C. 1681k
Section 614	15 U.S.C. 1681l             Section 615	15 U.S.C. 1681m
Section 616	15 U.S.C. 1681n             Section 617	15 U.S.C. 1681o
Section 618	15 U.S.C. 1681p             Section 619	15 U.S.C. 1681q
Section 620	15 U.S.C. 1681r             Section 621	15 U.S.C. 1681s
Section 622	15 U.S.C. 1681s-1           Section 623	15 U.S.C. 1681s-2
Section 624	15 U.S.C. 1681t             Section 625	15 U.S.C. 1681u
Section 626	15 U.S.C. 1681v             Section 627	15 U.S.C. 1681w
Section 628	15 U.S.C. 1681x             Section 629	15 U.S.C. 1681y
    </textarea>
                    </label>
                  <span class="textareaRequiredMsg">A value is required.</span></span></p>
                  </td></tr>
                    <tr>
                      <td width="158">&nbsp;</td>
                      <td width="299"><input type="image" src="/springapp/images/newfunnel/bt-continue.png" alt="continue" width="145" height="48" border="0"></td>
                    </tr>
                  </table>
                  </td>
              </tr>
            </table>
            </td>
          </tr>
        </table>
	  </td>
  </tr>
<script type="text/javascript">

var sprytextarea1 = new Spry.Widget.ValidationTextarea("sprytextarea1");
//-->
</script>
</form:form>
        <!--// content ends //-->

</jsp:body>
</neon:NewFunnel>