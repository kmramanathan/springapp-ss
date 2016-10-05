<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>


<neon:NationalSecurityFunnel title="No Results Found">
<jsp:attribute name="stylesheet">BJL_02.css,new-funnel.css</jsp:attribute>
<jsp:body>
<style type="text/css">
<!--
.style19 {font-family: Arial, Helvetica, sans-serif}
.style20 {font-size: small}
.style73 {	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight: bold;
}
.style118 {color: #FFFFFF}
p{margin:0;padding:0;}
-->
</style>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<table width="884" align="center">
          <tr>
            <td>
            <h1 align="left" class="style73">National Security Search Results</h1>
            <p align="left">Your search is complete. We found <strong>NO NATIONAL SECURITY RECORDS</strong> for:</p>
              <div align="left" class="style107">
                <table cellspacing="0">
                  <tbody>
          
             
                    <tr>
                      <td width="126" nowrap="nowrap"><p>First Name:</p></td>
                      <td width="146"><span class="style109a">${firstName}</span></td>
                    </tr>
                    <tr>
                      <td><p>Last Name:</p></td>
                      <td><span class="style109a">${lastName}</span></td>
                    </tr>
                   
              		<tr>
              			<td colspan="2"><p>Tips: If you don't find the record you are looking for, check the spelling, and make sure that the first name was entered in the correct field.  Same for the last name. </p></td>
              			
              		</tr>
                    
                  </tbody>
                </table>
                </div>
                </td>
                <td valign="top">
               <p>&nbsp;</p>
               <p>&nbsp;</p>
            <p align="left"><span class="style107"><a href="national-security-search.do">Start a New Search</a></span>
            </p>
            <p>&nbsp;</p>
            <p align="left"><a href="javascript:window.print();"><img src="/springapp/images/common/print.png" alt="print" width="48" height="52" /></a></p>
            </td>
            
          </tr>
        </table>
		  <p>&nbsp;</p>
		  <p>&nbsp;</p>

</jsp:body>

</neon:NationalSecurityFunnel>
								 