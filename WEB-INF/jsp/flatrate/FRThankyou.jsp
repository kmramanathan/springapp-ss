<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRcommonPage title="thank you">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

	<tr>
		<td width="893" height="321" style="height:100%; padding: 10px 0;"><table width="842">
          <tr>
            <td width="834" height="91"><h2 align="left" class="style59">Thank You for purchasing a Flat Rate plan!</h2>
              <p class="style71a">Your account has been registered successfully.  Your membership includes   your Flat Rate search plan,  access to our Public Records Directory, as well as discounts on Find People, Background Reports, Criminal Records,   Bankruptcies, Judgments, and Tax Liens searches.</p>
              <p class="style71a">&nbsp;</p>
              <p>&nbsp; </p>
              <p><span class="style72"><span class="style71a"><span class="style74a"><span class="style75"><strong>Username:</strong> ${username}</span></span></span></span></p>
              <p class="style73a">&nbsp;</p>
              <p class="style73a">Your Password is not displayed here. <span class="style71a">When logging into our system, please make sure you enter your username and   password exactly as you did in the signup form. The password is case-sensitive,   which means entering a password of</span><span class="style71a"> ABCDXYZ is not the same as abcdxyz.</span></p>
              <p class="style73a">&nbsp;</p>
              <p class="style73a">&nbsp;</p>
              <p class="style73a">To perform Flat Rate Find People or Criminal Record searches, <a href="FRLogin.do">go here</a>.</p>
              <p class="style73a">&nbsp;</p>
              <p class="style73a">If you would like access to our Public Records Directory and single Premium database searches, <a href="http://www.searchsystems.net/">log in here</a>.
                <label></label></p>
            </td>
          </tr>
        </table>
          </td>
  </tr>
	
</jsp:body>
</neon:FRcommonPage>
