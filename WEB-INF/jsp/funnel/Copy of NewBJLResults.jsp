<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<% session.setAttribute("charged","true"); %>

<neon:NewFunnel title="Results">
<jsp:attribute name="stylesheet">BJL_02.css,new-funnel.css</jsp:attribute>


<jsp:body>

<script type="text/javascript">


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
			alert("value = " +document.BLJform.cb[i].checked);
		}
	}

function validate()
{
    var flag = false;
   if(document.BLJform.resultIds.checked == true)
	    {
	        flag=true;
	    }
  for(var j = 0; j < document.BLJform.resultIds.length; j++){
		
		if(document.BLJform.resultIds[j].checked == true){
				flag = true;
				break;
				}
		}

	if(flag== true){
		document.BLJform.submit();
	}
	
   else {
		document.getElementById('error').style.visibility = "visible";
	}
	}
	</script>
	<script type="text/javascript">
	/*var resultsIds=[];
	var selected=true;
	function selectAll()
	{
	 $('#selectedall').click(function () {

	        if (this.checked == false) {

	            $('.resultids:checked').attr('checked', false);
	            
	        }
	        else {
	            $('.resultids:not(:checked)').attr('checked', true);
	           
	          
	            resultsIds.push($('.resultids:checked').val());
	            	
	            
	            
	        }
	    });


	    $('#selectedall').click(function () {

	    });
	}*/

	/*function validate()
	{*/
		//var resultsIds=[];
		/*var flag=false;
		$("form").submit( function() {
			$('.resultids').each(function(){
		    if($('.resultids').is(':checked'))
		    {
		    	resultsIds.push($('.resultids:checked').val());
		    	//alert("value = " + resultsIds);
		    	return true;
		    }
		    document.getElementById('error').style.visibility = "visible";
		    return false;
			});
		});*/
		
		
	//}	
/*function selectAll()
{
$('.resultids').each(function(){
$(this).attr("checked",!$(this).attr("checked"));
alert("value = " + $(this).val());
})
}*/
</script>

<form:form name="BLJform" method="post" action="newResultDetailsBJL.do">

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
              <td height="87"><div align="left">
              <img src="/springapp/images/BJL/bt-view-details-xsmall.png" alt="view details" width="109" height="32" id="onSubmit" onclick="validate(); return false;" style="cursor:pointer"></div></td>
              <td valign="top">
                  <p class="style107" style="padding:0;margin:0;">Check boxes below and click View  Details button to view details and print results from this page.</p>
                  
                <p class="style107" style="padding:0;">Click on the field heading to sort the results by that column.</p>
             </td>
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
              <th width="83" bordercolor="#666666" >
                  <div align="left" class="style109" style="font-size:12px;width:83px;">Select All</div>
                  
				  <div align="left"  style="margin:7px 0 0 5px; ">
                  <input type="checkbox" name="selectall" id="selectedall" onclick="selectAll();" />
                  </div>
               
              </th>
              <th width="264" bordercolor="#666666" class="table-sortable:alphanumeric table-sortable"><div align="left" class="style105"><a href="#" onclick="return false;">Name</a></div></th>
              <th width="109" bordercolor="#666666" class="table-sortable:alphanumeric table-sortable"><div align="left" class="style105"><a href="#" onclick="return false;">Filing Date</a></div></th>
              <th width="189" bordercolor="#666666" class="table-sortable:alphanumeric table-sortable"><div align="left" class="style105"><a href="#" onclick="return false;">Location</a></div></th>
              <th width="245" bordercolor="#666666" class="table-sortable:alphanumeric table-sortable"><div align="left" class="style105"><a href="#" onclick="return false;">Type</a></div></th>
            </tr>
      </jsp:attribute>
      
      <jsp:body>   
       <c:forEach items="${results}" var="r">
         
       		<tr>
       		  <td bordercolor="#666666" ><input type="checkbox" class="resultids" name="resultIds" value="${r.resultId}" /></td>
              <td bordercolor="#666666" width="264"><div style="width:264px;" align="left" class="style106">${r.lastname}</div></td>
              <c:choose>
					<c:when test="${fn:length(r.filing_date) > 0}">
						<td bordercolor="#666666"><div style="width:109px;" align="left" class="style106">
							<c:out value="${fn:substring(r.filing_date,4,6)}"/>/<c:out value="${fn:substring(r.filing_date,6,8)}"/>/<c:out value="${fn:substring(r.filing_date,0,4)}"/></div>
						</td>
					</c:when>
					<c:otherwise>
						<td bordercolor="#666666"><div  align="left" class="style106">---</div></td>
					</c:otherwise>
				</c:choose>
              <td width="189" bordercolor="#666666"><div align="left" style="width:189px;" class="style106">${r.city}, ${r.state}</div></td>
              <td width="245" bordercolor="#666666"><div align="left" style="width:245px;" class="style106">${r.filing_type}</div></td>
            </tr>
            
            
           
       </c:forEach>
       </jsp:body>
</neon:BJLsortableTable>          
		  <p>&nbsp;</p>

</form:form>
</jsp:body>

</neon:NewFunnel>
								 