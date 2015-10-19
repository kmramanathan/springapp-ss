<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>


<neon:NewFunnel title="No Results Found">
<jsp:attribute name="stylesheet">BJL_02.css,new-funnel.css</jsp:attribute>
<jsp:body>

<table width="884" align="center">
          <tr>
            <td><h2 align="left" class="style108a">We're sorry but no records were found in our database for: </h2>
              <div align="left" class="style107">
                <table cellspacing="0">
                  <tbody>
           <c:if test="${not empty bjlSearchName}">       
                    <tr>
                      <td width="86" nowrap="nowrap">Name:</td>
                      <td width="146"><span class="style109a">${bjlSearchName}</span></td>
                    </tr>
                    <tr>
                      <td>Location:</td>
                      <td><span class="style109a">${bjlSearchState}</span></td>
                    </tr>
           </c:if>
		    <c:if test="${not empty bjlBusinessName}">       
                    <tr>
                      <td width="86" nowrap="nowrap">Name:</td>
                      <td width="146"><span class="style109a">${bjlBusinessName}</span></td>
                    </tr>
                    <tr>
                      <td>Location:</td>
                      <td><span class="style109a">${bjlSearchState}</span></td>
                    </tr>
           </c:if>
           <c:if test="${not empty bjlSearchSsnTaxId}">
           			<tr>
                      <td width="86" nowrap="nowrap">SSN / Tax ID:</td>
                      <td width="146"><span class="style109a">${bjlSearchSsnTaxId}</span></td>
                    </tr>
					<tr>
                      <td>Location:</td>
                      <td><span class="style109a">${bjlSearchState}</span></td>
                    </tr>
          </c:if>        
                    
                  </tbody>
                </table>
                <p>&nbsp;</p>
                <p>Tips to help you in future searches: </p>
              </div>
              <ol class="style107">
                <li>
                  <div align="left">If you're searching an individual name we suggest to not enter a middle initial, middle name, or designation such as Jr. or Sr.;</div>
                </li>
                <li>
                  <div align="left">When  you search a business name, don't include designations such as &quot;LTD,&quot;  the filing may have it spelled out as &quot;Limited&quot; and therefore you won't  get a result. Stick to the basic name.</div>
                </li>
               
                <li>
                  <div align="left">Try  to get more results, rather than less-but keep the number of results  below the maximum of 250.</div>
                </li>
                <li>
                  <div align="left">Searches by social security  number or Tax ID will yield results for bankruptcies and tax liens, but  not judgments. If you're looking for judgments use the &quot;Individual&quot; or &quot;Business Name&quot;search pages.</div>
                </li>
              </ol>
            <p align="left"><span class="style107"><a href="newSearchBJL.do">Start a New Search</a></span></p></td>
          </tr>
        </table>
		  <p>&nbsp;</p>
		  <p>&nbsp;</p>

</jsp:body>

</neon:NewFunnel>
								 