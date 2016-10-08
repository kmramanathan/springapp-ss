<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<neon:NewCrimeFunnel title="Search Results">

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

	for(var i = 0; i < document.criminal.resultsToPrint.length;  i++){
			document.criminal.resultsToPrint[i].checked = checked;
			
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
   if(document.criminal.resultsToPrint.checked == true)
	    {
	        flag=true;
	    }
  for(var j = 0; j < document.criminal.resultsToPrint.length; j++){
		
		if(document.criminal.resultsToPrint[j].checked == true){
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

<div id="title" style="float:left; width:250px;">
<c:choose>
    <c:when test="${aliasSearchFlag}">
       <a href="newSearch.do">Back to Search Page</a>
    </c:when>    
    <c:otherwise>
       <a href="aliasSearch.do">Back to Search Page</a>
    </c:otherwise>
</c:choose> 
</div>
<div style="float:right; width:480px;"><Strong>Need more information? Try our 
<a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/${directoryStates[searchState]}_Criminal_Records/" target="blank">${directoryStates[searchState]} criminal records directory</a></Strong>
</div>

              
<div id="formwrapper">
<p>&nbsp;</p>
<h1>
<c:choose>
    <c:when test="${aliasSearchFlag}">
        Criminal Record Results
    </c:when>    
    <c:otherwise>
       AKA / Alias Criminal Search
    </c:otherwise>
</c:choose>
</h1>
<c:set var="pageSize" value="50" />
<c:out value="${offendersCount}"/> results found.<br/>
<c:set var="pageCount" value="${offendersCount / pageSize}"/>
<c:if test="${(pageSize < offendersCount) && ((offendersCount % pageSize) > 0)}">
	<c:set var="pageCount" value="${pageCount + 1}"/>
</c:if>

<div id="bgc-results">
<form:form action="newPrintResults.do" method="post" name="criminal">
<!--
<table border="0"  id="orders" class="autoLayout table-autopage:<c:out value="${pageSize}"/> table-autosort:-1 table-stripeclass:alternate">
<thead>
<tr>
	<td colspan="3">
        <input type="submit" name="printChecked" value="View Checked Details" style=" width:150px;"/>
        <input type="submit" name="printAll" value="View All Details" style=" width:120px;" onclick="printAll = true;"/>
		<input type="submit" name="printChecked" id="printOne" value="View Details for This Page Only" style=" width:210px;" onclick="CheckedAll()"/>
        &nbsp;
    </td>
	<td colspan="3" style="text-align:left;" align="left">
		Showing <c:out value="${pageSize}"/> results per page.
			<select id="pageSize" name="pageSize" onchange="setPageSize(document.getElementById('pageSize').value)">
<c:forEach var="size" items="${pageSizes}">
<c:set var="selected" value=""/>
<c:if test="${size eq pageSize}">
<c:set var="selected" value="selected"/>
</c:if> 
<option value="${size}" ${selected}>${size}</option>
</c:forEach>
			</select> -->
			<%--		
			<form:select path="pageSize" onchange="setPageSize(document.getElementById('pageSize').value)">
<form:option value="10">10</form:option>
<form:option value="25">25</form:option>
<form:option value="50">50</form:option>
			</form:select> 
			--%>
			<!--
	</td>
</tr>

<tr class="result-headers">
	<th>&nbsp;</th>
	<th class="table-sortable:numeric" align="left">Row #</th>
	<th class="table-sortable:numeric" align="left">Detail #</th>
	<th class="table-sortable:alphanumeric" align="left">Name</th>
	<th class="table-sortable:alphanumeric" align="left" >Date of Birth</th>
	<th class="table-sortable:alphanumeric"align="left" >Provider</th>
</tr>
</thead>

<tbody>
	<c:set var="row" value="1"/>
    <c:forEach items="${offenders}" var="o">
		<c:url value="newResultDetails.do?offenderId=${o.bgcOffenderId}&hashKey=${o.hashKey}" var="detailUrl"/>
	  	<tr>
			<td ><form:checkbox path="resultsToPrint" value="${o.bgcOffenderId}" /></td>
	  		<td ><c:out value="${row}"/></td>
			<td ><c:out value="${o.bgcOffenderId}"/></td>
	  		<td ><c:out value="${o.fullName}"/></td>
			<td >
			<c:choose>
					<c:when test="${o.dateOfBirth eq '1900-01-01'}">
						-- -- --
					</c:when>	
					<c:otherwise>
					<c:out value="${o.dateOfBirth}"/>
					</c:otherwise>			
			</c:choose>				
			</td>
			<td width="450"><c:out value="${o.provider}"/></td>
	  	</tr>
		<c:set var="row" value="${row+1}"/>
  	</c:forEach>
</tbody>
<tfoot>
	<tr><td colspan="6" align="left"><hr width="75%"/></td></tr>

	<tr><td colspan="6">
	Navigate to Page:<br/>		
	<a href="#" onclick="selectPage('previous', 'orders'); return false;">[ &lt;&lt;&nbsp;Previous ]</a>
	
	<%-- this forEach & paging stuff could use some work --%>
	<c:set var="currentPage" value="0"/>
	<c:forEach begin="0" end="${pageCount - 1}" step="1">
		<c:set var="nextPage" value="${currentPage + 1}"/>
		<c:url value="" var="pageUrl"/>
		<a href="#" id="page<c:out value="${nextPage}"/>" class="pagelink" onclick="selectPage(<c:out value="${currentPage}"/>, 'orders'); return false;"><c:out value="${nextPage}"/></a>
		<c:set var="currentPage" value="${currentPage + 1}"/>
	</c:forEach>

	<a href="#" onclick="selectPage('next', 'orders'); return false;">[ Next&nbsp;&gt;&gt; ]</a>
	</td></tr>
</tfoot>

</table>-->
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
          <display:table name="${offenders}" id="table" pagesize="50" sort="list" requestURI="newResults.do">
		  <c:url value="newResultDetails.do?offenderId=${table.bgcOffenderId}&hashKey=${table.hashKey}" var="detailUrl"/>
          	<display:column title="" media="html" sortable="false" headerClass="sortable">
          	
			<form:checkbox path="resultsToPrint" value="${table.bgcOffenderId}" />
          </display:column>
		  <display:column title="Row #" sortable="true" headerClass="sortable"><c:out value="${row}"/></display:column>
		  <display:column title="Detail #" sortable="true" headerClass="sortable"><c:out value="${table.bgcOffenderId}"/></display:column>
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

</neon:NewCrimeFunnel>
