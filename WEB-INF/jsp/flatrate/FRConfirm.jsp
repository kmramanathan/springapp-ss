<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRcommonPage title="confirm your plan">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>
<form:form method="post">
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;"><table width="808">
          <tr>
            <td width="800" height="451"><h2 class="style59">Please Confirm Your Selection</h2>
              <center>
                <table width="556">
                  <tbody>
                    <tr>
                      <td width="191" height="31"><span class="style71a">
                      <label><strong>Flat Rate Plan  Request</strong></label>
                      </span></td>
                      <td width="353"><span class="style71a"></span></td>
                    </tr>
                    <tr>
                      <td><span class="style71a">
                        <label>Your Name:</label>
                      </span></td>
                      <td><span class="style71a">${name}</span></td>
                    </tr>
                    <tr>
                      <td><span class="style71a">
                        <label>Your Username:</label>
                      </span></td>
                      <td><span class="style71a">${username}</span></td>
                    </tr>
                    <tr>
                      <td><span class="style71a">
                        <label>Flat Rate Plan:</label>
                      </span></td>
                      <td><span class="style71a"><strong>${plan}</strong></span></td>
                    </tr>
                    <tr>
                      <td><span class="style71a">
                        <label></label>
                      </span></td>
                      <td><span class="style71a"></span></td>
                    </tr>
                    <tr>
                      <td><span class="style71a">
                        <label>Credit Card Number</label>
                      </span></td>
                      <td><span class="style71a">***${ccLast4}</span></td>
                    </tr>
                  </tbody>
                </table>
                <p>&nbsp;</p>
              </center>
                <strong class="style71a">Please verify that the billing plan is correct.</strong> <span class="style71a">The billing will take place immediately. </span>
                <br>
                <span class="style71a"> All use of our services must be in accordance with our <a target="_blank" href="https://www.searchsystems.net/springapp/funnel/FR-Terms-Of-Use.html">User Agreement</a>. By clicking  on the "Make Payment" button and paying for services, you agree to its terms. Please  read the Agreement carefully.
             </span>
              <p>&nbsp;</p>
              <table width="794">
                <tr>
                  <td width="305">&nbsp;</td>
                  <td width="314"><input type="image" src="/springapp/images/flatrate/bt-make-payment.png" alt="make payment" width="145" height="48"></td>
                  <td width="159"><div align="center"><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
					<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script>
                    </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><div align="center"><a href="${bbbUrl}"><img src="/springapp/images/flatrate/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></div></td>
                </tr>
              </table>
              <p>&nbsp;</p>
              </td>
          </tr>
        </table>
      </td>
	</tr>
</form:form>
</jsp:body>
</neon:FRcommonPage>

	