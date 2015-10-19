<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRFPcommonPage title="expired plan">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

 <c:if test="${empty member}">
	<tr>
		<td width="893" height="273" style="height:100%; padding: 10px 0;"><table width="741">
          <tr>
            <td width="733" height="106"><h2 align="left" class="style59">We're Sorry!</h2>
              <p align="left" class="style59"><span class="style27">Your Flat Rate plan and temporary membership have expired. We hope you found the plan to be useful. </span></p>
              <p align="left" class="style59">&nbsp;</p>
              <p align="left" class="style59"><span class="style27">To purchase an additional Flat Rate plan using your existing account, <a href="FRLanding.do">click here</a>.</span></p>
              <p align="left" class="style59">&nbsp;</p>
              <p align="left" class="style70a">To upgrade your expired account into  an annual SearchSystems.net membership without a Flat Rate plan, <a href="http://www.searchsystems.net">click here</a>.</p>
              <p align="left" class="style59">&nbsp;</p></td>
          </tr>
        </table>
          </td>
  </tr>
 </c:if>
  <c:if test="${! empty member}">
  <tr>
		<td width="893" height="273" style="height:100%; padding: 10px 0;"><table width="805">
          <tr>
            <td width="797" height="106"><h2 align="left" class="style59">Welcome Back!</h2>
              <p align="left" class="style59"><span class="style27">Your SearchSystems.net membership is still active. To access the directory and Premium databases, <a href="http://www.searchsystems.net">click here</a>.</span></p>
              <p align="left" class="style59">&nbsp;</p>
              <p align="left" class="style59"><span class="style27">Please Note: Your Flat Rate plan has expired. We hope you found the plan to be useful. </span><span class="style27">To purchase an additional Flat Rate plan,  <a href="FRLanding.do">click here</a>.</span></p>
            <p align="left" class="style59">&nbsp;</p></td>
          </tr>
        </table>
          </td>
  </tr>
  </c:if>
</jsp:body>
</neon:FRFPcommonPage>
