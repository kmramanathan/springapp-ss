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

	for(var i = 0; i < document.BLJform.resultIds.length;  i++){
			document.BLJform.resultIds[i].checked = checked;
		}
	}
function DownloadAll()
{

	//window.location.href	
	document.BLJform.action ='newResultsBJL.do?downloadall=true';
	//alert(document.BLJform.action);
	
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
	<style type="text/css">
	#table{width:920px;margin:0 auto}
	#table .odd{background:#ccc;}
	#table .even{background:#fff}
	.pagelinks
	{
		float:right;
	}
	</style>

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
              <td height="65" colspan="3">
        <input type="submit" name="printChecked" value="View Checked Details" 
        onclick="validate(); return false;" style="cursor:pointer;width:150px;height:30px;margin-left:50px;"/>&nbsp;&nbsp;&nbsp;
        
        <input type="submit" name="printChecked" id="printOne" value="View Details for This Page Only" 
        style=" width:210px;height:30px;cursor:pointer;" onclick="selectAll()"/>&nbsp;
        
      <!--   <input type="submit" name="printChecked" id="dnlodAll" value="All Details" 
          onclick="DownloadAll()"/>&nbsp; -->
          
        <a href="newResultsBJL.do?downloadall=true"> 
        <img src="/springapp/images/BJL/dwnldAll.png" alt="download all records" align="middle"  
        style="margin-bottom:10px;";   />  
		</a>
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
								 