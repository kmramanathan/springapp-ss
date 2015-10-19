<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<neon:NewFunnel title="Confirm New Account">
<jsp:attribute name="stylesheet">BJL_02.css,new-funnel.css</jsp:attribute>
<jsp:body>

<form:form method="post" name="searchForm">
<table width="893" height="338" border="0" align="center">
          <tr>
            <td width="775" height="334"><table width="641" border="0">
                <tr>
                  <td height="52"><h1 align="left" class="style32">Please Confirm Your Account Details</h1></td>
                </tr>
              </table>
                <table width="645" border="0">
                  <tr>
                    <td width="639">
                    	<table width="614" border="0" align="left">
                         		<tr>
    									<td width="164"><div align="left" class="style102a">First Name:</div></td>
    									<td width="440"><div align="left" class="style102a"><strong>${firstname} </strong></div></td>
    							</tr>
    							<tr>
                          			<td><div align="left" class="style102a">Last Name:</div></td>
                        			<td><span class="style102a"><strong>${lastname}</strong></span></td>
                        			
                         		</tr>
								<tr>
                         			 <td><div align="left" class="style102a">Username:</div></td>
                          			 <td><div align="left" class="style102a"><strong>${username}</strong></div></td>
                        		</tr>
                        		<tr>
                         			 <td><div align="left" class="style102a">Email Address:</div></td>
                          			 <td><div align="left" class="style102a"><strong>${email}</strong></div></td>
                        		</tr>
                        		<tr>
                          			<td><div align="left" class="style102a">Credit Card Number:</div></td>
                          			<td><div align="left" class="style102a"><strong>***${ccLast4}</strong></div></td>
                        		</tr>
                    	</table>
                    </td>
                  </tr>
                </table>
             
              <table width="641" border="0">
                  <tr>
                    <td width="633" height="65"><table width="639" border="0">
                      <tr>
                        <td width="633">
                          
                    		 <p><strong>Click the button below to set up your Search Systems account for Premium searches.</strong></p>
                    		 <p><strong>There is no charge for setting up an account.</strong></p>
                    		 <p><strong>Your credit card will  be validated. We will not charge your card at this time. Search Systems will only charge your card when you log in and run Premium searches.</strong></p>
                    		
                    	</td>
                      </tr>
                    </table>                    
                    
                    </td>
                  </tr>
                </table>
              <p align="center"><input type="image" src="/springapp/images/common/bt-continue.png" alt="continue to search" width="149" height="60"  /></p>
              
             </td>
          </tr>
        </table>
</form:form>
</jsp:body>

</neon:NewFunnel>
								 