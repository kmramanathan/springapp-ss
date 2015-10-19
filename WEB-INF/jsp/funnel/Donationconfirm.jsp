<%@page import="springapp.web.funnel.donationform"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="donationreciept"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>

<donationreciept:DonationReciept title="Search Systems Donation Confirmation">
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
            <td width="875" height="519"><table width="848">
                <tr>
                  <td width="643" height="56"><h2>Review &amp; Confirm your Support</h2></td>
                  <td width="193"><div align="center">
                      <h3>&nbsp;</h3>
                  </div></td>
                </tr>
              </table>
              <table width="837">
                <tr>
                  <td width="829" height="392"><p align="left" class="style98">Please look over the amount of your donation and whether you want to make it one-time or monthly.</p>
                    <p align="left" class="style98"> If you need to make changes, just click the Back button on your browser.</p>
                    <p align="left" class="style98">If the information is correct, click the Confirm Donation button to complete your donation.</p>
                    <table width="608">
                      <tr>
                        <td width="178"><div align="right">Donation Amount:</div></td>
                        <td width="418" class="style97">$${donation.donationamount}0</td>
                      </tr>
                      <tr>
                        <td><div align="right"> Donation Frequency:</div></td>
                        <td class="style97">${donation.frequency}</td>
                      </tr>
                      <tr>
                        <td><div align="right">Name:</div></td>
                        <td class="style97">${donation.name}</td>
                      </tr>
                       <c:if test="${not empty donation.company}">
                      <tr>
                        <td><div align="right">Company Name:</div></td>
                        <td class="style97">${donation.company}</td>
                      </tr>
                      </c:if>
                    </table>
                    <p><span class="style98">This donation helps to save SearchSystems.net and keep it free.</span></p>
                    <table width="781">
                      <tr>
                        <td width="486"><div align="center"><form:form action="DConfirmation.do" method="POST">
                          <input type="submit" name="Continue2" id="Continue2" value="Confirm Donation" />
                          <a href="Donation.do"><input type="button" value="Return Back" /></a>
                        </form:form>
                        </div></td>
                        <td width="113"><a href="#"><img src="images/common/Comodo-seal-85.gif" alt="comodo secure" width="85" height="51" /></a></td>
                        <td width="166"><a href="https://www.bbb.org/online/consumer/cks.aspx?ID=10404141025041762"><img src="images/common/bbb-clickratingsm.gif" alt="bbb" width="135" height="52" /></a></td>
                      </tr>
                    </table>                    </td>
                </tr>
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