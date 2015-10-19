<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<% session.removeAttribute("charged");
%>
<neon:PropertyFunnel title="Confirm Purchase">
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css</jsp:attribute>
<jsp:body>
<script type="text/javascript">
function showRealPropDelay()
{
	window.location.replace("newPropertySearchDelay.do");
}
</script>
<tr>
<td>

<div id="title"></div>
              
<div id="formwrapper">

  <form:form method="post" name="PropertyAddressSearchForm">

  <h1 style="border: 0; padding-top: 1em;"><strong>Please Confirm Your Selection</strong></h1>


	<table style="margin-left:100px;">
	
	<c:if test="${searchRealProp}">
		<tr><td><label><b>Your Search Request</b></label></td><td>&nbsp;</td></tr>
    	<tr><td><label>Name to Search:</label></td><td><c:out value="${searchName}"/></td></tr>
		<tr><td><label>State:</label></td><td><c:out value="${searchState}"/></td></tr>
		<c:if test="${not empty searchCity}">
		<tr><td><label>City:</label></td><td><c:out value="${searchCity}"/></td></tr>
		</c:if>
		<tr><td><label>Type of Search:</label></td><td><c:out value="${searchRealPropType}"/></td></tr>
		<c:if test="${not empty searchRefer}">
		<tr><td><label>Reference:</label></td><td><c:out value="${searchRefer}"/></td></tr>
		</c:if>
		
	</c:if>
	<c:if test="${searchRealPropAddress}">
		<tr><td><label><b>Your Search Request</b></label></td><td>&nbsp;</td></tr>
    	<tr><td><label>Address to Search:</label></td>
    	<td>
	    	<c:out value="${searchAppartnum}"/>  <c:out value="${searchStreet}"/>  
	    	 	
    	</td>
    	
    	</tr>
		<c:if test="${not empty searchCity}">
		<tr><td><label>City:</label></td><td><c:out value="${searchCity}"/></td></tr>
		</c:if>
		
		<tr><td><label>State:</label></td><td><c:out value="${searchState}"/></td></tr>
		
		<tr><td><label>Type of Search:</label></td><td><c:out value="${searchRealPropAddressSearchType}"/></td></tr>
		<c:if test="${not empty searchRefer}">
		<tr><td><label>Reference:</label></td><td><c:out value="${searchRefer}"/></td></tr>
		</c:if>
		
	</c:if>
	
	

	<tr><td><label>&nbsp;</label></td><td>&nbsp;</td></tr>
	<tr><td><label>Credit Card Number</label></td><td>***${ccLast4}</td></tr>

	</table>


  <div>

<c:if test="${search}">
<p style="color:#003366;">
The total cost of this search is $<c:out value="${searchPrice}"/>.
</p>

<p  style="color:#003366;"> 
By performing this search, I acknowledge that I will be charged for this search even if there are no results.


</p>

<p style="color:#003366;">Please verify that the name and state are correct.
</p>
<p style="color:#003366;">Please also check that the first and last names are in the correct order.
</p>
</c:if>



  </div>


  <p>
   <!-- <input type="image" src="images/common/bt-make-your-secure-payment.gif" onclick="this.disabled=true; javascript:document.forms[0].submit();" alt="Make Payment and Start Searching" /> -->
  <input type="image" src="images/common/bt-make-your-secure-payment.gif" onclick="this.disabled=true; showRealPropDelay(); return false;" alt="Make Payment and Start Searching" />
 </p>


  </form:form>

  <br /><br />

</div>
</td>
</tr>
</jsp:body>

</neon:PropertyFunnel>
