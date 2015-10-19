<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRcommonPage title="flat rate login">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>


	<tr>
		<td width="893" height="357" style="height:100%; padding: 10px 0;"><table width="892">
          <tr>
            <td width="655"><table width="596">
              <tr>
                <td width="588" height="91"><h2 align="left" class="style59">Flat Rate Login</h2>
                    <p class="style76">If you have an active Flat Rate plan with SearchSystems.net, login here to start your searches. </p>
                  <p class="style76">&nbsp;</p>
                  <p class="style71a"><font color="red">${error}</font></p>
                  <p class="style76">&nbsp;</p>
                  
             <form:form method="post" action="FRLogin.do">   
               
                  <table width="541" >
                    <tr>
                        <td width="116"><span class="style71a"><strong>* Username:</strong></span></td>
                        <td width="212"><label><form:input path="username" size="20"/></label></td>
                        <td width="133"><a href="https://members.searchsystems.net/retrieve_username_step1.php" class="style44">Username Help</a></td>
                    </tr><tr><td colspan="3"><p class="style76">&nbsp;</p></td></tr>
                      <tr>
                        <td><span class="style71a"><strong>* Password:</strong></span></td>
                        <td><form:password path="password" size="20"/></td>
                        <td><a href="https://members.searchsystems.net/reset_password_step1.php" class="style44">Password Help</a></td>
                    </tr>
                    </table>
                  <p>&nbsp;</p>
                  <table width="396">
                      <tr>
                        <td width="469"><div align="center"><input type="image" src="/springapp/images/flatrate/bt-login-small.png" alt="login" width="94" height="38"></div></td>
                      </tr>
                    </table>
                    <form:errors path="*" element="div" id="error" cssClass="error-box" />
              </form:form>      
                    
                  </td>
              </tr>
            </table></td>
            <td width="225"><p class="style71a">&nbsp;</p>
            <p class="style71a">Don't have a Flat Rate Plan? <a href="FRSignup.do">Click here</a>.</p></td>
          </tr>
        </table>
		</td>
  </tr>
  
</jsp:body>
</neon:FRcommonPage>
