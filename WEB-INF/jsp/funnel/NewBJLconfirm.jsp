<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% session.removeAttribute("charged"); %>

<neon:NewFunnel title="Confirm Purchase">
<jsp:attribute name="stylesheet">BJL_02.css,new-funnel.css</jsp:attribute>
<jsp:body>

<script language="javascript" type="text/javascript">
function showDelay()
{
 	 window.location.replace("newBJLsearchDelay.do");
}

</script>



<form:form method="post" name="searchForm">
<table width="893" height="338" border="0" align="center">
          <tr>
            <td width="775" height="334"><table width="641" border="0">
                <tr>
                  <td height="52"><h1 align="left" class="style32">Please Confirm Your Search</h1></td>
                </tr>
              </table>
                <table width="645" border="0">
                  <tr>
                    <td width="639"><table width="614" border="0" align="left">
                         <c:if test="${empty searchSsnTax}">
                          		<tr>
    									<td width="164"><div align="left" class="style102a">Name to Search:</div></td>
    									<td width="440"><div align="left" class="style102a"><strong>${searchName}</strong></div></td>
    					  		</tr>
    					  		<tr>
                          			<td><div align="left" class="style102a">Type of Search:</div></td>
                        			<td><div align="left" class="style102a"><strong>Bankruptcy, Judgment, Tax Lien</strong></div></td>
                         		</tr>
                         		<tr>
                          <td><div align="left" class="style102a">Location:</div></td>
                          <c:if test="${searchState eq ' '}">
                          		<td><div align="left" class="style102a"><strong>Nationwide </strong></div></td>
                          </c:if>
                          <c:if test="${empty searchState}">
                          		<td><span class="style102a"><strong> </strong></span></td>
                          </c:if>
                          <c:if test="${!empty searchState}">
                          		<td><span class="style102a"><strong>${searchState}</strong></span></td>
                          </c:if>
                          
                        </tr>
						 </c:if>
						<c:if test="${!empty searchSsnTax}">
								<tr>
    									<td width="264"><div align="left" class="style102a">SSN / Tax ID to Search:</div></td>
    									<td width="340"><div align="left" class="style102a"><strong>${searchSsnTax} </strong></div></td>
    							</tr>
    							<tr>
                          			<td><div align="left" class="style102a">Type of Search:</div></td>
                          			<c:if test="${searchBJLType eq 'Any'}">
                        				<td><span class="style102a"><strong>Bankruptcy, Tax Lien</strong></span></td>
                        			</c:if>	
                        			<c:if test="${searchBJLType ne 'Any'}">
                        				<td><div align="left" class="style102a"><strong>Bankruptcy, Tax Lien</strong></div></td>
                        			</c:if>	
                         		</tr>
						</c:if>
                        
                       <tr>
                          <td><div align="left" class="style102a">Price:</div></td>
                          <td><div align="left" class="style102a"><strong>$${searchPrice}</strong></div></td>
                        </tr>
                        <tr>
                          <td><div align="left" class="style102a">Credit Card Number:</div></td>
                          <td><div align="left" class="style102a"><strong>***${ccLast4}</strong></div></td>
                        </tr>
                    </table></td>
                  </tr>
                </table>
              <p>&nbsp;</p>
              <table width="641" border="0">
                  <tr>
                    <td width="633" height="65"><table width="639" border="0">
                      <tr>
                        <td width="633"><p align="left" class="style102a">The total cost of this search is $5. The charge is          for the right to search, not for the retrieved results, so this fee is valid          even if no records are found and will be applied to your credit card          automatically. </p>
                            <p align="left" class="style102a"><br />
                                <strong>Please verify that the search  query is correct.</strong> If the  information you input is correct and you understand what databases will be  searched, press Continue To My Search. </p></td>
                      </tr>
                    </table>                    <p align="left">&nbsp;</p></td>
                  </tr>
                </table>
              <p align="center"><input type="image" src="/springapp/images/BJL/bt-continue-to-search.png" alt="continue to search" width="201" height="60" onclick="this.disabled=true; showDelay(); return false;" /></p>
              <p align="center">&nbsp;</p></td>
            <td width="613"><p><img src="/springapp/images/BJL/ss-logo-dotnet-bwhite.png" alt="ss logo" width="237" height="166"></p>
                <p align="center">&nbsp;</p>
            
             <p align="center"><a href="http://www.instantssl.com" id="comodoTL">Premium SSL Certificate</a>
				<script language="JavaScript" type="text/javascript">
				TrustLogo("https://www.searchsystems.net/springapp/images/common/comodo-sa.png", "SC5", "none");
				</script>
			</p>
              <p align="center">&nbsp;</p>
              <p align="center"><a href="${bbbUrl}" target="_blank"><img src="/springapp/images/BJL/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></p></td>
          </tr>
        </table>
</form:form>
</jsp:body>

</neon:NewFunnel>
								 