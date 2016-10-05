<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 	boolean member = false;
   String username = "";
	if(session.getAttribute("username") != null){
		member = true;
		username = (String) session.getAttribute("username");
	}
%>
<html>
<head>
<title>Confirm Purchase</title>
<style type="text/css">	
    @import url("/springapp/css/new-funnel.css");	    
</style>
<link href="/springapp/css/common.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/JavaScript" src="/springapp/js/common.js"></script>
<script type="text/JavaScript" src="/springapp/js/heartcode-canvasloader-min-0.9.1.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>please wait</title>
<style type="text/css">

.style19 {font-family: Arial, Helvetica, sans-serif}
.style20 {font-size: small}
.style73 {	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight: bold;
}
.style15 {font-size: 0.8em}
.style118 {color: #FFFFFF}
.style119 {color: #000000; }
.style120 {color: #FF0000}
.style121 {color: #003366; font-family: Arial, Helvetica, sans-serif;}
.style102 {color: #003366}
.style104 {font-size: 0.8em;color: #003366;}
#maintable{padding:0 100px 0 100px!important;}

</style>
<script language="javascript" type="text/javascript">
function sendToResult()
{
	setTimeout('document.images.delayGif.src = "/springapp/img/ajax-progress-bar.gif', 10000);
	setTimeout('window.status = "Waiting for www.searchsystems.net..."', 10000); 
	document.dummy.submit();
	setTimeout('document.images.delayGif.src = "/springapp/img/ajax-progress-bar.gif"', 10000);
	
}

</script>

</head>

<% 
	if(session.getAttribute("charged") != null){
		session.removeAttribute("charged");
		response.sendRedirect("newConfirmSearch.do");
}
%>


<body onLoad="sendToResult();">

<form name="dummy" method="post" action="newConfirmSearch.do"></form>
<center>
  <div id="owrap">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
	<td rowspan="2" valign="middle">
    <a href="http://publicrecords.searchsystems.net/"><img src="images/common/updated-logo.png" width="293" height="40" /></a>
      <!--// masthead begins //-->  
     
      </td>
      
      <td style="color:#ffffff;" align="right"><div align="right">Welcome <strong><%=username%></strong></div></td>

      <td  style="background:#fec226; border-right:1px solid #f0f0f0;"  height="35"  width="80"><a href="https://members.searchsystems.net/login.php?username=<%=username%>" style="text-decoration: none;font-size:14px;  color:#003366; ">My Account</a>
      </td><td width="30"  style="background:#fec226;font-size:14px;  color:#003366;" ><a href="logout.do" style="text-decoration: none;font-size:14px;  color:#003366; ">Logout</a> </td>
      
     
 </tr>
 <tr><td colspan="4">&nbsp;</td> </tr>
 <tr><td colspan="4">&nbsp;</td> </tr>
 </table>

      <!--// masthead ends //-->

      <!--// main content area begins //-->
      
    
    <table cellpadding="0" cellspacing="0" border="0" width="950"  id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> <tr><td height="50">&nbsp;</td></tr>
      
 
	<tr>
	<td><table width="850" border="0" cellspacing="0.5" cellpadding="0.5" align="center">
	<tr>
		<td colspan="3"> <h1><span class="style102">Your Search is Processing</span></h1>
                                                  
                                                    <p class="style102"><strong>Please do not navigate away from this page. Your search is processing and may take a few moments to complete.</strong></p>
              <p class="style104"><strong>We are searching all records in the state and </strong><strong> verifying the records to ensure a proper match to the criteria you input.</strong> <strong>We appreciate your patience.</strong></p></td>
	</tr>
                      <tr>
                        <th width="409" height="1500" scope="col"><p align="left" class="style104">Criminal record databases as provided by the 50 States</p>
                          <p align="left" class="style104">Alabama Medicaid - Suspended Providers </p>
                          <p align="left" class="style104">America's Most Wanted Fugitive List </p>
                          <p align="left" class="style104">Australia Sex Offender Registry</p>
                          <p align="left" class="style104">Australian Department of Foreign Affairs and Trade - Sanctions List </p>
                          <p align="left" class="style104">Australian Reserve Bank Sanctions List </p>
                          <p align="left" class="style104">Bank of England Sanctions List </p>
                          <p align="left" class="style104">California Medi-Cal - Suspended and Ineligible Providers </p>
                          <p align="left" class="style104">Connecticut Department of Social Services - Admin Actions </p>
                          <p align="left" class="style104">DEA Diversion Control Program - Admin Actions against Doctors </p>
                          <p align="left" class="style104">DEA Diversion Control Program - Cases against Doctors <br />
                          </p>
                          <p align="left" class="style104">Delaware Adult Abuse Registry </p>
                          <p align="left" class="style104">Directorate of Defense Trade Controls - Debarred Parties List </p>
                          <p align="left" class="style104">European Union Terrorism Sanctions List </p>
                          <p align="left" class="style104">FDA Office of Regulatory Affairs - Debarment List </p>
                          <p align="left" class="style104">FDA Office of Regulatory Affairs - Warning Letters </p>
                          <p align="left" class="style104">Federal Deposit Insurance Corporation (FDIC) - Enforcement Decisions and Orders </p>
                          <p align="left" class="style104">Federal Deposit Insurance Corporation (FDIC ) - Failed Bank List </p>
                          <p align="left" class="style104">Federal Reserve Board - Enforcement Actions </p>
                          <p align="left" class="style104">FinCEN - Enforcement Actions </p>
                          <p align="left" class="style104">FINRA - Disciplinary Actions </p>
                          <p align="left" class="style104">Florida Medicaid - Sanctioned Providers </p>
                          <p align="left" class="style104">Fugitive List </p>
                          <p align="left" class="style104">Health Resources and Services Administration - Health Education Assistance Loan - Defaulted Borrowers </p>
                          <p align="left" class="style104">HM Treasury - Consolidated List of Financial Sanctions </p>
                          <p align="left" class="style104">HM Treasury - Investment Ban List </p>
                          <p align="left" class="style104">Hong Kong Securities and Futures Commission (SFC) - Enforcement Actions </p>
                          <p align="left" class="style104">HUD - Limited Denials of Participation List </p>
                          <p align="left" class="style104">Idaho Medicaid Provider - Exclusion List </p>
                          <p align="left" class="style104">Illinois Casino Exclusion List </p>
                          <p align="left" class="style104">Illinois Office of Inspector General - Sanctioned Providers </p>
                          <p align="left" class="style104">Immigration and Customs Enforcement (ICE) - Most Wanted </p>
                          <p align="left" class="style104">Interpol Most Wanted </p>
                          <p align="left" class="style104">Kansas Department of Health and Environment - Abuse Registry </p>
                          <p align="left" class="style104">Kentucky Medicaid - Excluded Providers</p>
                          <p align="left" class="style104">Maryland Medicaid - Exclusion List </p>
                          <p align="left" class="style104">MIPT Terrorism Knowledge Base </p>
                          <p align="left" class="style104">Mississippi Medicaid - Excluded Providers </p>
                          <p align="left" class="style104">Missouri Casino Exclusion List </p>
                          <p align="left" class="style104">Monetary Authority of Singapore - Enforcement Actions </p></th>
                        <th width="30" scope="col">&nbsp;</th>
                        <th width="382" scope="col"><p align="left" class="style104">National Credit Union Administration (NCUA) - Administrative Orders</p>
                          <p align="left" class="style104">Naval Criminal Investigative Service (NCIS) - Most Wanted Fugitives </p>
                          <p align="left" class="style104">Nevada Gaming Control Board - Excluded Person List </p>
                          <p align="left" class="style104">New Jersey Casino Exclusion List </p>
                          <p align="left" class="style104">New Jersey Department of Treasury - Debarment List </p>
                          <p align="left" class="style104">New York Office of the Medicaid Inspector General - Exclusion List </p>
                          <p align="left" class="style104">New York Stock Exchange Regulation - Disciplinary Actions </p>
                          <p align="left" class="style104">Office of the Comptroller of Currency (OCC) - Enforcement Actions List </p>
                          <p align="left" class="style104">Office of Foreign Assets Control (OFAC) - Specially Designated Nationals List </p>
                          <p align="left" class="style104">Office of Inspector General (OIG) - Health and Human Services Exclusion List </p>
                          <p align="left" class="style104">Office of Inspector General (OIG) - Most Wanted Health Care Fugitives </p>
                          <p align="left" class="style104">Office of Regulatory Affairs - Disqualified, Restricted and Assurances List for Clinical Investigators </p>
                          <p align="left" class="style104">Office of Research Integrity - Public Health Service - Administrative Actions List </p>
                          <p align="left" class="style104">Office of the Superintendent of Financial Institutions (OSFI) - Canadian Sanctions List </p>
                          <p align="left" class="style104">Office of Thrift Supervision (OTS) - Enforcement Actions List </p>
                          <p align="left" class="style104">Ohio Medicaid - Sanctioned Providers </p>
                          <p align="left" class="style104">Ohio Medicaid - Suspended Providers </p>
                          <p align="left" class="style104">Palestinian Legislative Council List </p>
                          <p align="left" class="style104">Pennsylvania Medicheck - Precluded Providers List </p>
                          <p align="left" class="style104">Politically Exposed Persons List </p>
                          <p align="left" class="style104">SEC - Enforcement Actions </p>
                          <p align="left" class="style104">South Carolina Medicaid - Excluded Providers </p>
                          <p align="left" class="style104">Tennessee Department of Health - Abuse Registry</p>
                          <p align="left" class="style104">Texas Employee Misconduct Registry</p>
                          <p align="left" class="style104">Texas Health and Human Services Commission Medicaid and Title XX Provider Exclusion List </p>
                          <p align="left" class="style104">TRICARE Sanctions List </p>
                          <p align="left" class="style104">UK Disqualified Directors List </p>
                          <p align="left" class="style104">United Nations Consolidated Sanctions List </p>
                          <p align="left" class="style104">US Commodity Futures Trading Commission (CFTC) - Disciplinary Actions</p>
                          <p align="left" class="style104">US Department of Commerce - Denied Persons List </p>
                          <p align="left" class="style104">US Department of State - Foreign Terrorist Organizations List </p>
                          <p align="left" class="style104">US Department of State - Nonproliferation Sanctions</p>
                          <p align="left" class="style104">US Department of State - Terrorist Exclusion List </p>
                          <p align="left" class="style104">US General Services Administration (GSA) - Excluded Parties List </p>
                          <p align="left" class="style104">World Bank Listing of Ineligible Individuals </p></th>
                      </tr>
                    </table>
	</td>
	</tr>
		
  
                
            </table>
      
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
    
   	 
  </div>
   
<!--// footer begins //-->
<div style="clear: both; height: 30px;"></div><div class="footer style19h style20h">Copyright &copy; 2016 SearchSystems.net. All rights reserved.</div>

<!--// footer ends //-->
	
</center></body>
</html>
  
	