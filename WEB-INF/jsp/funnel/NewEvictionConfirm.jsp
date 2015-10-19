<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<% session.removeAttribute("charged");
%>
<neon:EvictionFunnel title="Confirm Purchase">
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css</jsp:attribute>
<jsp:body>
<script type="text/javascript">
function showEvictionDelay()
{
	window.location.replace("newEvictionSearchDelay.do");
}
</script>
<tr>
<td>

<div id="title"></div>
              
<div id="formwrapper">

  <form:form method="post" name="searchEvictionForm">

  <h1 style="border: 0; padding-top: 1em;"><strong>Please Confirm Your Selection</strong></h1>


	<table style="margin-left:100px;">
	
	<c:if test="${searchEviction}">
		<tr><td><label><b>Your Search Request</b></label></td><td>&nbsp;</td></tr>
    	<tr><td><label>Name to Search:</label></td><td><c:out value="${searchName}"/></td></tr>
		<tr><td><label>State:</label></td><td><c:out value="${searchState}"/></td></tr>
		<c:if test="${not empty searchCity}">
		<tr><td><label>City:</label></td><td><c:out value="${searchCity}"/></td></tr>
		</c:if>
		<tr><td><label>Type of Search:</label></td><td><c:out value="${searchEvictionType}"/></td></tr>
		
		<tr><td><label>Reference:</label></td><td><c:out value="${searchRefer}"/></td></tr>
		
		
	</c:if>
	<c:if test="${searchEBusiness}">
		<tr><td><label><b>Your Search Request</b></label></td><td>&nbsp;</td></tr>
    	<tr><td><label>Name to Search:</label></td><td><c:out value="${searchName}"/></td></tr>
		<tr><td><label>State:</label></td><td><c:out value="${searchState}"/></td></tr>
		<c:if test="${not empty searchCity}">
		<tr><td><label>City:</label></td><td><c:out value="${searchCity}"/></td></tr>
		</c:if>
		<tr><td><label>Type of Search:</label></td><td><c:out value="${searchEvictionType}"/></td></tr>
		
		<tr><td><label>Reference:</label></td><td><c:out value="${searchRefer}"/></td></tr>
		
		
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
  <input type="image" src="images/common/bt-make-your-secure-payment.gif" onclick="this.disabled=true; showEvictionDelay(); return false;" alt="Make Payment and Start Searching" />
 </p>


  </form:form>

  <br /><br />

</div>
</td>
</tr>
</jsp:body>

</neon:EvictionFunnel>
