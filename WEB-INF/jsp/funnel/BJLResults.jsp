<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<neon:BJLcommonPage title="Results">

<jsp:attribute name="stylesheet">BJL_02.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>

<jsp:body>

<script type="text/javascript" language="javascript">
var checked = false;
function selectAll()
{
	if (checked == false){
           checked = true;
          }
        else{
          checked = false;
          }

	for(var i = 0; i < document.BLJform.resultIds.length;  i++){
			document.BLJform.resultIds[i].checked = checked;
		}
		
}

function validate()
{
    var flag = false;
	for(var j = 0; j < document.BLJform.resultIds.length; j++){
		if(document.BLJform.resultIds[j].checked == true){
				flag = true;
				break;
				}
		}
	if(flag){
		document.BLJform.submit();
	} else {
		document.getElementById('error').style.visibility = "visible";
	}
	
}
</script>

<form:form name="BLJform" method="post" action="resultDetailsBJL.do">

	<p><a href="https://www.searchsystems.net/springapp/funnel/WholePicture.html"><img src="/springapp/images/BJL/banner-upsell.png" alt="search systems" width="840" height="73"></a></p>
		  <p>&nbsp;</p>
		  <table width="914" border="0">
          <tr>
            <td width="849" height="58"><h1 align="left" class="style110">Bankruptcies, Judgments &amp; Tax Liens Search Results</h1></td>
          </tr>
        </table>
		  <table width="914" border="0">
            <tr>
              <td width="140" height="29"><div align="right" class="style107">
                <div align="left"><strong>Search Term:</strong></div>
              </div></td>
              <td width="747"><div align="left" class="style107"><strong>${searchName}</strong></div></td>
              <td width="13">&nbsp;</td>
            </tr>
            <tr>
              <td height="87"><div align="left"><img src="/springapp/images/BJL/bt-view-details-xsmall.png" alt="view details" width="109" height="32" onclick="validate(); return false;" style="cursor:pointer"></div></td>
              <td><div align="left">
                  <p class="style107">Check boxes below and click View  Details button to view details and print results from this page.</p>
                  <p class="style107">&nbsp;</p>
                <p class="style107">Click on the field heading to sort the results by that column.</p>
              </div></td>
              <td>&nbsp;</td>
            </tr>
          </table>
          <div id="error" style="color:#990000;visibility:hidden;" align="center"><b>Please Check boxes</b></div>
          
          
  <!--  table starts here -->   
  
  	<c:set var="pageCount" value="${resultsCount / pageSize}"/>
	<c:if test="${(pageSize < resultsCount) && ((resultsCount % pageSize) > 0)}">
		<c:set var="pageCount" value="${pageCount + 1}"/>
	</c:if>
  
         
 <neon:BJLsortableTable pageSize="${pageSize}" rowCount="${resultsCount}" tableColumns="5" tableName="bjl-results">
		  
	<jsp:attribute name="tableHeaders">
            <tr>
              <th width="83" bordercolor="#666666">
                  <div align="left" class="style109">Select All</div>
                  <label>
				  <div align="left">
                    <input type="checkbox" name="selectall" onclick="selectAll();" />
                  </div>
                </label>
              </th>
              <th width="264" bordercolor="#666666" class="table-sortable:alphanumeric"><div align="left" class="style105"><a href="#" onclick="return false;">Defendant Name</a></div></th>
              <th width="109" bordercolor="#666666" class="table-sortable:alphanumeric"><div align="left" class="style105"><a href="#" onclick="return false;">Filing Date</a></div></th>
              <th width="189" bordercolor="#666666" class="table-sortable:alphanumeric"><div align="left" class="style105"><a href="#" onclick="return false;">Def. Location</a></div></th>
              <th width="245" bordercolor="#666666" class="table-sortable:alphanumeric"><div align="left" class="style105"><a href="#" onclick="return false;">Type</a></div></th>
            </tr>
      </jsp:attribute>
      
      <jsp:body>   
       <c:forEach items="${results}" var="r">
       		<tr>
       		
              <td bordercolor="#666666"><input type="checkbox" name="resultIds" value="${r.resultId}" /></td>
              <td bordercolor="#666666"><div align="left" class="style106">${r.defendant}</div></td>
              <c:choose>
					<c:when test="${fn:length(r.filingDate) > 0}">
						<td bordercolor="#666666"><div align="left" class="style106">
							<c:out value="${fn:substring(r.filingDate,4,6)}"/>/<c:out value="${fn:substring(r.filingDate,6,8)}"/>/<c:out value="${fn:substring(r.filingDate,0,4)}"/></div>
						</td>
					</c:when>
					<c:otherwise>
						<td bordercolor="#666666"><div align="left" class="style106">---</div></td>
					</c:otherwise>
				</c:choose>
              <td bordercolor="#666666"><div align="left" class="style106">${r.addressCity}, ${r.addressState}</div></td>
              <td bordercolor="#666666"><div align="left" class="style106">${r.filingType}</div></td>
            </tr>
            
       </c:forEach>
       </jsp:body>
</neon:BJLsortableTable>          
		  <p>&nbsp;</p>

</form:form>
</jsp:body>

</neon:BJLcommonPage>
								 