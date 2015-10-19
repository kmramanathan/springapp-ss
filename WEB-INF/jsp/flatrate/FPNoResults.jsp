<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRFPcommonPage title="no result found">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

    <tr>
		<td width="893" style="height:215px; background:url(/springapp/images/flatrate/tal_header_03.jpg) top repeat-y;">
			<table width="893" style="height:215px; background:url(/springapp/images/flatrate/bg_header_top_07.jpg) top no-repeat;">
				<tr>
					<td width="893" class="content_1" style="background:url(/springapp/images/flatrate/bg_fpheader_08.jpg) bottom no-repeat; padding:44px 565px 16px 60px;">
                      <p class="style28">&nbsp;</p>
                  <p class="style28">We're sorry, but we did not find any results.</p>
                  </td>
			  </tr>
			</table>
        </td>
	</tr>
	<tr>
		<td width="893" style="height:207px; background:url(/springapp/images/flatrate/bg_content_02.jpg) top no-repeat; padding:18px 7px 0 20px;">
			<div class="side_right">
				<div class="fill_bot">
					<div class="fill_top">
						<div class="left_top">
							<div class="left_bot">
								<div class="right_top">
									<div class="right_bot">
		  <table width="850" height="267" style="height:207px;">
<tr>
												<td width="1285" colspan="2" class="content_1" style="padding:20px 30px 20px 30px;"><h3>We're sorry but no records were found in our database for:</h3>
												  <table width="540" border="0" align="left">
                                                    <tr>
                                                      <td width="131"><h4 align="left" class="style13 style33">${sfpfc.searchFirstName} ${sfpfc.searchMidName} ${sfpfc.searchLastName}</h4></td>
                                                      <td width="86"><h4 align="left" class="style35">${sfpfc.searchCity}</h4></td>
                                                      <td width="46"><h4 align="left" class="style35">${sfpfc.searchState}</h4></td>
                                                      <td width="190"><h4 class="style35">DOB: ${sfpfc.searchDob}</h4></td>
                                                    </tr>
                                                  </table>					                              
					                              <p>&nbsp;</p>
					                              <p>&nbsp;</p>
					                              <table width="683" border="0">
                                                    <tr>
                                                      <td width="214"><p align="center"><strong>To try another search, </strong><strong>click the button below:</strong></p>
                                                        <table width="135" border="0" align="center">
                                                            <tr>
                                                              <td width="129"><div align="center"><a href="FPsearchLanding.do"><img src="/springapp/images/flatrate/bt-search-again-xsmall.png" alt="search again" width="109" height="32" border="0"></a></div></td>
                                                            </tr>
                                                          </table>
                                                        <p>&nbsp;</p></td>
                                                      <td width="459"><table width="410" border="1" align="center" cellpadding="1" cellspacing="1">
                                                        <tr>
                                                          <td><p align="left"><strong>Search Tips:</strong></p>
                                                            <p align="left">If no results were found, chances are you input too much information. </p>
                                                            <p align="left">Try a broader search, using just a name.</p>
                                                            <p align="left">If that returns too many results, refine your search using a state or city and state.</p>
                                                          <p align="left">If you still get too many results, try using the Advanced Search and include a date of birth.</p></td>
                                                        </tr>
                                                      </table>
                                                        </td>
                                                    </tr>
                                                  </table>                        </td>
</tr>
									  </table>
								  </div>
							  </div>
							</div>
						</div>
					</div>
				</div>
	  </div></td>
	</tr>
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;">&nbsp;</td>
  </tr>


</jsp:body>

</neon:FRFPcommonPage>
								 