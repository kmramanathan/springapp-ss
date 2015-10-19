<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:PropertyFunnel title="View Results">

<jsp:attribute name="stylesheet">BJL_02.css,new-funnel.css</jsp:attribute>
<jsp:body>

<style type="text/css">
.tablepadd th{padding:8px 0 8px 0;background:#052A5E;font-size:16px;color:#fff;}
tr.odd{background:#ccc;}
tr.even{background:white;}
.rowstate th{color:#fff;background:#052A5E;font-size: 16px;}
.tablepadd th span{color:#fe0000;font-size: 15px;}
.style19 {font-family: Arial, Helvetica, sans-serif}
.style20 {font-size: small}
.style73 {	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight: bold;
}
.style15 {font-size: 0.8em}
.style118 {color: #000000;background: #bebaba;padding:5px 0 5px 0;font-weight: bold;}
.style1191{color: #000000;background: #68b868;padding:5px 0 5px 0;font-weight: bold;}
.style119 {color: #000000; }
.style120 {color: #FF0000}
.style121 {color: #003366; font-family: Arial, Helvetica, sans-serif;}
.realPropTable tr,.realPropTable td
{
margin:0;padding:2px 0 2px 0;
}
</style>

		  <table width="926" border="0" align="center">
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
              	<p>  <a href="realPropResults.do?downloadall=true">Download all results</a> </p>	
              		<p align="left"><span class="style107"><a href="javascript:window.print();"><img src="/springapp/images/common/print.png" alt="print" width="48" height="52" /></a></span></p>
              
               
              </td>
            </tr>
             <tr>
            <td>
            
            </td>
            </tr>
          </table>



<!--  Table starts here -->
     
  <!--  Paging starts here -->   
		  
 <table width="95%" align="center" cellpadding="0" cellspacing="0" class="realPropTable">
 <tr>
 <td colspan="2">
 <c:choose>
 	<c:when test="${eListsize > 0}">
 	<c:set var="ownrName" value=""/>
 	<c:set var="dateRecord" value=""/>
 	<c:set var="rowno" value=""/>
 		<c:forEach items="${eList}" var="realPropObj" varStatus="loop" begin="0">  			
 			<neon:PropertyResultDetails ownrName="${ownrName}" dateRecord="${dateRecord}" rowno="${dateRecord}" obj="${realPropObj}" />  			
 		 <c:set var="ownrName" value="${realPropObj.primaryowner}"/>	
		 <c:set var="dateRecord" value="${realPropObj.daterecorded}" />
		 <c:set var="rowno" value="${realPropObj.daterecorded}" />							
 		</c:forEach>
 	</c:when>
 
 </c:choose>
 </td>
 </tr>
   
    
</table>

<table width="875" border="0">
   <tr>
     <td><h4 align="left" class="style107">DISCLAIMER</h4>
     <p align="left" class="style107"><span class="style31">Search Systems provides no  warranty of any type as to the
      accuracy of this information, and any reliance  on this information is solely at your own risk and responsibility. 
      All  information retrieved from or through SearchSystems.net must be utilized in  accordance with the User Agreement 
      and all applicable state and federal laws.</span></p></td>
   </tr>
</table>
          
		  <p>&nbsp;</p>
		  

</jsp:body>

</neon:PropertyFunnel>
								 