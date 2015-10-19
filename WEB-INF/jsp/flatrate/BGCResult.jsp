<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRBGCcommonPage title="result summary">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js</jsp:attribute>
<jsp:body>


<script language="javascript" type="text/javascript">
function validateField(type)
{
	if(type=='all'){
	 	document.BGCresult.action="BGCResults.do?printType=all";
		document.BGCresult.submit();
	 }
	 else{
		 	var flag = false;
			for(var j = 0; j < document.BGCresult.bgcKey.length; j++){
				if(document.BGCresult.bgcKey[j].checked == true){
						flag = true;
						break;
					}
				}
			if(document.BGCresult.bgcKey.checked == true){
				flag = true;
			}
			if(flag){
		  		document.BGCresult.action="BGCResults.do?printType=checked";
				document.BGCresult.submit();
			} else {
				document.getElementById('error').style.visibility = "visible";
			}
			
	 }
}
</script>

	<tr>
		<td width="950" style="height:100%; padding: 10px 0;"><table width="884" align="center">
          <tr>
            <td>
            
            <table width="893" border="0">
              <tr>
                <td width="896" valign="top"><table width="893" height="115" border="0">
                    <tr>
                      <td width="678" height="111"><table width="668" align="left">
                          <tr>
                            <td width="620" height="92"><h2 class="style76a style109">Nationwide Criminal Record Results</h2>
                                <p class="style77">Use the checkboxes below to view the full details for the records you wish to view and print, or click &quot;Print All Details&quot; to view and print details for all records on this page.</p></td>
                          </tr>
                      </table></td>
                      <td width="205"><div align="right"></div></td>
                    </tr>
                  </table>
                  
                    
    <c:set var="pageSize" value="30"/>
    <c:set var="pageCount" value="${size / pageSize}"/>
    <c:if test="${(pageSize < size) && ((size % pageSize) > 0)}">
    	<c:set var="pageCount" value="${pageCount + 1}"/>
    </c:if>             
  
  
<form:form method="post" name="BGCresult" target="_blank">
                       
      <table width="891" id="bgcList" class="table-autopage:<c:out value="${pageSize}"/> table-autosort:-1 table-stripeclass:alternate">
	  <thead>
		<tr>
			<td colspan="3" ><span class="style113"><a href="#" onClick="return validateField('all');">Print All Details</a> | <a href="#" onClick="return validateField('one');">Print Checked Details</a></span></td>	
			<td  colspan="2" align="left"><span class="style113"><a href="#" onclick="selectPage('previous', 'bgcList'); return false;">Previous</a>
					
			<%-- this forEach & paging stuff could use some work --%>
			<c:set var="currentPage" value="0"/>
			<c:forEach begin="0" end="${pageCount - 1}" step="1">|
				<c:set var="nextPage" value="${currentPage + 1}"/>
				<c:url value="" var="pageUrl"/>
				<a href="#" id="page<c:out value="${nextPage}"/>" class="pagelink" onclick="selectPage(<c:out value="${currentPage}"/>, 'bgcList'); return false;"><c:out value="${nextPage}"/></a>
				<c:set var="currentPage" value="${currentPage + 1}"/>
			</c:forEach>|

			<a href="#" onclick="selectPage('next', 'bgcList'); return false;">Next</a></span>
		</td>
	</tr>
	<tr><td colspan="5"><div class="style113" style="color: red; visibility:hidden" id="error"> <b>Please check the record you want to view </b></div>
                 <p align="left" class="style32">&nbsp;</p> </td></tr>
	<tr>
		<td width="40">&nbsp;</td>
		<td width="64" class="table-sortable:numeric"><span class="style65b">Detail #</span></td>
        <td width="246" class="table-sortable:alphanumeric"><span class="style65b">Name</span></td>
        <td width="128" class="table-sortable:alphanumeric"><span class="style65b">Date of Birth</span></td>
        <td width="389" class="table-sortable:alphanumeric"><span class="style65b">Provider</span></td>
        
	</tr>
	<tr height="8"><td colspan="5"></td></tr>
	</thead>
	<tbody>
	
	  <c:forEach items="${bgcList}" var="bg" begin="1" varStatus="cnt"> 
           	          <tr>
                        <td><input type="checkbox" name="bgcKey" value="${bg.id}"></td>
                        <td><span class="style74b">${cnt.count}</span></td>
                        <td><span class="style74b">${bg.firstName}, ${bg.lastName} ${bg.middleName}</span></td>
                        <td><span class="style74b">${bg.dob}</span></td>
                        <td><span class="style74b">${bg.sourceName}</span></td>
                       
                      </tr>
      </c:forEach>    
          
	</tbody>
	</table>
	
</form:form>	
	
	
                  <p>&nbsp;</p>
                  </td>
               </tr>
            </table>
            </td>
          </tr>
        </table>
		  <p>&nbsp;</p>
	  </td>
	</tr>
	

</jsp:body>
</neon:FRBGCcommonPage>
