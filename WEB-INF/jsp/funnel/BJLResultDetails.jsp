<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:BJLcommonPage title="View Print">
<jsp:attribute name="stylesheet">BJL_02.css</jsp:attribute>

<jsp:body>

		
		<p><a href="https://www.searchsystems.net/springapp/funnel/WholePicture.html"><img src="/springapp/images/BJL/banner-upsell.png" alt="search systems" width="840" height="73"></a></p>
		  <p>&nbsp;</p>
		  <table width="926" border="0">
            <tr>
              <td width="712" height="120"><h2 align="left" class="style114">Bankruptcy, Judgment &amp; Tax Lien Search Results</h2>
                <h2 align="left" class="style107">View and Print Details</h2></td>
              <td width="204">
              	<p align="left" class="style107">
                   	<c:if test="${empty sessionScope.username}">
              			<a href="searchBJL.do">Start a New Search</a>
              		</c:if>	
              		<c:if test="${!empty sessionScope.username}">
                		<span class="style107"><a href="http://www.searchsystems.net/login-new.php?redir=https://premium.searchsystems.net/secure/search.php">Start a New Search</a></span>
                	</c:if>
              	</p>
              		
                <p align="left" class="style107">&nbsp;</p>
                <p align="left"><span class="style107"><a href="javascript:window.print();">Print</a></span></p>
                <p align="left">&nbsp;</p>
                <p align="left"><span class="style107"><a href="resultsBJL.do">Back to Results</a></span>    </p>
              </td>
            </tr>
          </table>
          
   <!--  Table starts here -->       
       <c:forEach items="${BJLList}" var="result"> 
          <neon:bjlResultDetails d="${result}"/>
       </c:forEach>
         

	 <table width="875" border="0">
            <tr>
              <td><h4 align="left" class="style107">DISCLAIMER</h4>
              <p align="left" class="style107"><span class="style31">Search Systems provides no  warranty of any type as to the accuracy of this information, and any reliance  on this information is solely at your own risk and responsibility. All  information retrieved from or through SearchSystems.net must be utilized in  accordance with the User Agreement and all applicable state and federal laws.</span></p></td>
            </tr>
          </table>
          
		  <p>&nbsp;</p>
		  <p>&nbsp;</p>

</jsp:body>

</neon:BJLcommonPage>
								 