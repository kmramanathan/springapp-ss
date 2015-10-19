<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRcommonPage title="flat rate menu">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

<c:if test="${empty sessionScope.FRusername}">
	 <c:redirect url="FRLogin.do" />
</c:if>

<script type="text/javascript" >
  function checkFP(){
	  var available = false;
	  <c:if test="${!empty sessionScope.FPSearchAvailable || !empty sessionScope.FPComSearchAvailable}">
      		available = true;
      </c:if>
	  if(available){
		  window.location = 'FPsearchLanding.do';
	  }else{
		  document.getElementById('fpError').style.visibility = "visible";
	  }
	}  

  function checkBGC(){
	  var available = false;
	  <c:if test="${!empty sessionScope.BGCSearchAvailable || !empty sessionScope.BGCComSearchAvailable}">
      		available = true;
      </c:if>
	  if(available){
		  window.location = 'BGCsearchLanding.do';
	  }else{
		  document.getElementById('bgcError').style.visibility = "visible";
	  }
	}  
</script>

	<tr>
		<td width="893" style="height:100%; padding: 10px 0;"><table width="773">
          <tr>
            <td height="106"><h2 align="left" class="style59">Flat Rate Menu &amp; Status</h2>
              <p align="left" class="style59"><span class="style27">Welcome back, <strong>${FRusername}</strong>.</span></p>
            
              
     <c:forEach items="${activeList}" var="al"> 
              <c:if test="${al.plantypeid eq 1}">
              		<c:set var="plan" value="FindPeople" />
              		<p align="left" class="style70a">&nbsp;</p> 
              		<p align="left" class="style59"><span class="style27">You have<strong> ${al.daysavailable} days</strong> remaining in your <strong>${plan}</strong>  Flat Rate plan. </span></p>
              		<p align="left" class="style70a">There are <strong>${al.findpeoplesearches} Find People</strong> search credits available to use today.</p>
              		<p align="left" class="style70a">&nbsp;</p>
              </c:if>  
               <c:if test="${al.plantypeid eq 2}">
              		<c:set var="plan" value="Criminal" />
              		<p align="left" class="style70a">&nbsp;</p> 
              		<p align="left" class="style59"><span class="style27">You have<strong> ${al.daysavailable} days</strong> remaining in your <strong>${plan}</strong>  Flat Rate plan. </span></p>
              		<p align="left" class="style70a">There are <strong>${al.criminalsearches} Criminal Records</strong> search credits available to use today.</p>
              		<p align="left" class="style70a">&nbsp;</p>
              </c:if>  
               <c:if test="${al.plantypeid eq 3}">
              		<c:set var="plan" value="Combined" />
              		<p align="left" class="style70a">&nbsp;</p> 
              		<p align="left" class="style59"><span class="style27">You have<strong> ${al.daysavailable} days</strong> remaining in your <strong>${plan}</strong>  Flat Rate plan. </span></p>
              		<p align="left" class="style70a">There are <strong>${al.findpeoplesearches} Find People</strong> search credits and <strong>${al.criminalsearches} Criminal Records</strong> search credits available to use today.</p>
              		<p align="left" class="style70a">&nbsp;</p>
              </c:if> 
      </c:forEach>                       
              
              <p align="left" class="style70a">Use the buttons below to access the search pages, or purchase additional plans below.</p>
            <p align="left" class="style59">&nbsp;</p></td>
          </tr>
        </table>
          <p>&nbsp;</p>
          <table width="813">
          <tr>
            <td width="805" height="217"><p class="style73">Access the search pages:</p>
              <table width="693">
                <tr>
                  <td><a href="#"><img src="/springapp/images/flatrate/bt-find-people-fr.png" alt="find people" width="254" height="49" onclick="checkFP(); return false;"></a></td>
                  <td><div id="fpError" style="visibility: hidden;"><span class="style74">[Exception - Sorry, you do not have an active Find People Flat Rate plan. To purchase one, use the button below]</span></div></td>
                </tr>
                <tr>
                  <td><a href="#"><img src="/springapp/images/flatrate/bt-crim-fr.png" alt="criminal records" width="254" height="49" onclick="checkBGC(); return false;"></a></td>
                  <td><div id="bgcError" style="visibility: hidden;"><span class="style74">[Exception - Sorry, you do not have an active Criminal Records Flat Rate plan. To purchase one, use the button below]</span></div></td>
                </tr>
              </table>
              <p class="style73">&nbsp;</p>
              <p class="style73">&nbsp;</p>
              <p class="style73">Purchase another plan:</p>
              <p><a href="FRSignup.do"><img src="/springapp/images/flatrate/bt-purchase-new-fr.png" alt="purchase new plan" width="270" height="49"></a>
                <label></label></p>
              </td>
          </tr>
        </table>
      </td>
	</tr>
	
</jsp:body>
</neon:FRcommonPage>
	