<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

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

	for(var i = 0; i < document.RealPropform.resultIds.length;  i++){
			document.RealPropform.resultIds[i].checked = checked;
		}
	}
function validate()
{
    var flag = false;
   if(document.RealPropform.resultIds.checked == true)
	    {
	        flag=true;
	    }
  for(var j = 0; j < document.RealPropform.resultIds.length; j++){
		
		if(document.RealPropform.resultIds[j].checked == true){
				flag = true;
				break;
				}
		}

	if(flag== true){
		document.RealPropform.submit();
	}	
   else {
		document.getElementById('error').style.visibility = "visible";
	}
	}
	</script>
	<style type="text/css">
	#table{width:920px;margin:0 auto}
	#table .odd{background:#ccc;}
	#table .even{background:#fff}
	.pagelinks
	{
		float:right;
	}
	</style>

<form:form name="RealPropform" method="post" action="PropertyResultDetails.do">
			  <p>&nbsp;</p>
		  <table width="914" border="0">
         <tr>
              <td width="712" height="120"><h1 align="left" class="style114">Real Property Search Results</h1>
               <p>Records Found: <strong>${eListsize}</strong></p>
               	<c:if test="${not empty sessionScope.RPNameSearch}">
              	 <p>Name Searched: <strong>${realPropName}</strong></p>
               </c:if>
              	<c:if test="${not empty sessionScope.RPAdresSearch}">
               	<p>Address Searched: <strong> ${realPropAppartnum}    ${realPropStreet}</strong></p>               	
               <c:if test="${not empty realPropCity}">
                <p>City: <strong>${realPropCity}</strong></p>
                </c:if>
               </c:if>                
               <p>State: <strong>${realPropState}</strong></p>
                <c:if test="${not empty realProprefer}">
                <p>Reference: <strong>${realProprefer}</strong></p>
                </c:if>
               </td>
              <td width="204">
              	<p align="left" class="style107">
                   	<c:if test="${empty sessionScope.username}">
              			<a href="realpropSearch.do">Start a New Search</a>
              		</c:if>	
              		<c:if test="${!empty sessionScope.username}">
                		<span class="style107"><a href="realpropSearch.do">Start a New Search</a></span>
                	</c:if>
              	</p>
              		<p align="left"><span class="style107"><a href="javascript:window.print();"><img src="/springapp/images/common/print.png" alt="print" width="48" height="52" /></a></span></p>
              
               
              </td>
            </tr>
        </table>
		  <table width="914" border="0">           
            <tr>
              <td height="65" colspan="3">
        <input type="submit" name="printChecked" value="View Checked Details" 
        onclick="validate(); return false;" style="cursor:pointer;width:150px;height:30px;margin-left:50px;"/>
        &nbsp;&nbsp;&nbsp;
        
        <input type="submit" name="printChecked" id="printOne" value="View Details for This Page Only" 
        style=" width:210px;height:30px;cursor:pointer;" onclick="selectAll()"/>&nbsp;
         </td>
            </tr>
          </table>
          <div id="error" style="color:#990000;visibility:hidden;" align="center"><b>Please Check boxes</b></div>
                    
		<!-- display tag starts here -->
          <input type="hidden" value="${UsID}" id="userid" name="userid"/>
          <display:table name="${results}" id="table" pagesize="50" sort="list" requestURI="newResultsBJL.do">
          	<display:column title="<input type='checkbox' name='selectall' onClick='selectAll()'>" media="html" sortable="false" headerClass="sortable">
          	<input type="checkbox" name="resultIds" value="${table.docket_number}" >
          </display:column>
          	<display:column title="Name" sortable="true" headerClass="sortable"><c:out value="${table.firstname}"/> </display:column>
          	<display:column title="Filing Date" sortable="true" headerClass="sortable">
				<c:choose>
    			    <c:when test="${fn:length(table.filing_date) > 0}">
					<c:out value="${fn:substring(table.filing_date,0,4)}"/>-<c:out value="${fn:substring(table.filing_date,4,6)}"/>-<c:out value="${fn:substring(table.filing_date,6,8)}"/>
					</c:when>
					<c:otherwise>
						---
					</c:otherwise>
				</c:choose>
          	</display:column>
          	<display:column property="state" sortable="true" headerClass="sortable"></display:column>
          	<display:column property="filing_group" sortable="true" headerClass="sortable"></display:column>
          	
          </display:table>
          <!-- end of display tag -->
  
		  <p>&nbsp;</p>

</form:form>
</jsp:body>

</neon:NewFunnel>
								 