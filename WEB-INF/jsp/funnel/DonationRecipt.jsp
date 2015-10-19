<%@taglib tagdir="/WEB-INF/tags" prefix="donationreciept"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<donationreciept:DonationReciept title="Search Systems Donation">

	<jsp:attribute name="stylesheet">common.css</jsp:attribute>
	<jsp:attribute name="javascript">common.js</jsp:attribute>
	<jsp:body>
	
<style type="text/css">
<!--
.style19 {
	font-family: Arial, Helvetica, sans-serif
}

.style20 {
	font-size: small
}

.style77 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.style65 {
	font-size: 12px;
	color: #FF0000;
	font-family: Arial, Helvetica, sans-serif;
}

.style67 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #000000;
	font-size: 12px;
}

.style85 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 10px;
}

.style86 {
	color: #FF0000;
	font-weight: bold;
}

.style88 {
	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
}

.style90 {
	font-size: 0.9em
}

.style95 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 0.9em;
}

.style96 {
	font-size: 1em
}
.error {
    	color: red;
    	font-size:12px;
    }
-->
</style>

<table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> 
<td id="colone" align="left"><table width="883" align="center">
          <tr>
            <td width="875" height="638"><table width="840">
                <tr>
                  <td width="577" height="64"><h1>Thank you!</h1></td>
                  <td width="119"><h3>&nbsp;</h3></td>
                  <td width="128"><div align="right"><a href="javascript:window.print();"><img src="/springapp/images/common/print.png" alt="print" width="48" height="52" /></a></div></td>
                </tr>
              </table>
              <table width="844">
                <tr>
                  <td width="836" height="503"><p align="left" class="style99">I can't thank you enough for your donation. Please know how much you've helped to support our site and how much we appreciate it.</p>
                    <table width="791">
                      <tr>
                        <td width="178"><div align="right">Gift Amount:</div></td>
                        <td width="601" class="style98">$${donation.donationamount}0</td>
                      </tr>
                      <tr>
                        <td><div align="right">Gift Frequency:</div></td>
                        <td class="style98">${donation.frequency}</td>
                      </tr>
                      <tr>
                        <td><div align="right">Name:</div></td>
                        <td class="style98">${donation.name}</td>
                      </tr>
                     <c:if test="${not empty donation.company}">
                      <tr>
                        <td><div align="right">Company Name:</div></td>
                        <td class="style98">${donation.company}</td>
                      </tr>
                      </c:if>
                    </table>
                    <p align="left" class="style100">Print this form if you'd like a receipt for your records. Should you have any  questions, contact us directly at:</p>
                    <p align="left" class="style101">Search Systems, P.O. Box 544, Newbury Park, California 91319-0544</p>
                    <p align="left" class="style101">Phone:   (805) 375-4041 | Fax: (805) 375-4042</p>
                    <p align="left" class="style101">Or send us an email by <a href="http://publicrecords.searchsystems.net/contact.php">following this link</a>.
                      <label></label>
                        <label></label>
                    </p>
                    <p class="style101">Thank you from all of us here at SearchSystems.net.</p>
                    <p class="style101">Sincerely,</p>
                    <p class="style101">Tim Koster, Founder</p>
                    <p class="style101"> Pacific Information Resources, Inc. dba Search Systems </p></td>
                </tr>
                <tr><td><a href="http://publicrecords.searchsystems.net/">Back to Home Page</a></td></tr>
              </table>
              <p><label></label></p>
              </td>
            </tr>
        </table>
        </td>

        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
    
   	  </tr></table>
   	  
   	  </jsp:body>
</donationreciept:DonationReciept>