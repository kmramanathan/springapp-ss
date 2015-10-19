<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<neon:NationalSecurityFunnel title="Search Results">

<jsp:attribute name="stylesheet">funnel.css,resultsBgc.css</jsp:attribute>
<jsp:attribute name="javascript">jquery-1.2.1.pack.js,table.js,selectPage.js,verifyCheckBoxesForPrint.js</jsp:attribute>

<jsp:body>
<script type="text/javascript">
var checked = false;
//var flag=false;
function CheckedAll()
{
	if (checked == false){
           checked = true;
          }
        else{
          checked = false;
          }

	for(var i = 0; i < document.national.resultsToPrint.length;  i++){
			document.national.resultsToPrint[i].checked = checked;
			
			if($("[type='checkbox']:checked").length > 50)
			{	
				//document.criminal.submit();
				this.checked=false;
				//flag=true;
				//validate();
				break;
				
			}
			
			
		}
		
		//alert(count);
}
$(document).ready(function(){
$('#command').submit(function(){
function validate()
{
    var flag = false;
   if(document.national.resultsToPrint.checked == true)
	    {
	        flag=true;
	    }
  for(var j = 0; j < document.national.resultsToPrint.length; j++){
		
		if(document.national.resultsToPrint[j].checked == true){
				flag = true;
				break;
				}
		}
		}
	});
});
</script>
<tr>
<td>


<%-- CJ tracking code, uses BGC response id --%>
<iframe src="https://secure.img-cdn.mediaplex.com/0/11449/universal.html?page_name=results_page&Sale_1=${searchPrice}&Sale_2=1&mpuid=${responseId}" HEIGHT="1" WIDTH="1" FRAMEBORDER="0"></iframe>

<div id="title" style="float:left; width:250px;"><a href="nationSecurity.do">Back to Search Page</a>
</div>


              
<div id="formwrapper">
<p>&nbsp;</p>
<h1>National Security Search Results</h1>
<c:set var="pageSize" value="50" />
<c:out value="${offendersCount}"/> results found.<br/>
<c:set var="pageCount" value="${offendersCount / pageSize}"/>
<c:if test="${(pageSize < offendersCount) && ((offendersCount % pageSize) > 0)}">
	<c:set var="pageCount" value="${pageCount + 1}"/>
</c:if>

<div id="bgc-results">
<form:form action="NssPrintResults.do" method="post" name="national">

<table>
<tr>
<td><input type="submit" name="printChecked" value="View Checked Details" style=" width:150px;"/>
        <input type="submit" name="printAll" value="View All Details" style=" width:120px;" onclick="printAll = true;"/>
		<input type="submit" name="printChecked" id="printOne" value="View Details for This Page Only" style=" width:210px;" onclick="CheckedAll()"/>
        &nbsp;</td>
</tr>
<tr>
<td><!-- display tag starts here -->
          <c:set var="row" value="1"/>
          <display:table name="${offenders}" id="table" pagesize="50" sort="list" requestURI="NssResults.do">
		  <c:url value="NssResultDetails.do?offenderId=${table.nssOffenderId}&hashKey=${table.hashKey}" var="detailUrl"/>
          	<display:column title="" media="html" sortable="false" headerClass="sortable">
          	
			<form:checkbox path="resultsToPrint" value="${table.nssOffenderId}" />
          </display:column>
		  <display:column title="Row #" sortable="true" headerClass="sortable"><c:out value="${row}"/></display:column>
		  <display:column title="Detail #" sortable="true" headerClass="sortable"><c:out value="${table.nssOffenderId}"/></display:column>
          	<display:column title="Name" sortable="true" headerClass="sortable"><c:out value="${table.fullName}"/></display:column>
          	<display:column title="Date of Birth" sortable="true" headerClass="sortable">
				<c:choose>
					<c:when test="${table.dateOfBirth eq '1900-01-01'}">
						-- -- --
					</c:when>	
					<c:otherwise>
					<c:out value="${table.dateOfBirth}"/>
					</c:otherwise>			
			</c:choose>	
          	</display:column>
          	<display:column property="provider" sortable="true" headerClass="sortable"></display:column>
          	<c:set var="row" value="${row+1}"/>
          </display:table>
          <!-- end of display tag -->
		  </td>
</tr>
</table>
</form:form>
</div>



</div>


</td>
</tr>
</jsp:body>

</neon:NationalSecurityFunnel>
