<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:EvictionFunnel title="View Print">
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
.style118 {color: #FFFFFF;background: #052A5E;padding:5px 0 5px 0;font-weight: bold;}
.style1191{color: #000000;background: #66CCFF;padding:5px 0 5px 0;font-weight: bold;}
.style119 {color: #000000; }
.style120 {color: #FF0000}
.style121 {color: #003366; font-family: Arial, Helvetica, sans-serif;}
.evictionTable tr,.evictionTable td
{
margin:0;padding:2px 0 2px 0;
}
</style>

		  <table width="926" border="0" align="center">
            <tr>
              <td width="712" height="120"><h1 align="left" class="style114">Eviction Search Results</h1>
               <p>Records Found: <strong>${eListsize}</strong></p>
               <p>Name Searched: <strong>${evictionName}${evictionBName}</strong></p>
               <p>State: <strong>${evictionState}</strong></p>
                <p>City: <strong>${evictionCity}</strong></p>
                <p>Reference: <strong>${evictionrefer}</strong></p>
               </td>
              <td width="204">
              	<p align="left" class="style107">
                   	<c:if test="${empty sessionScope.username}">
              			<a href="newSearchBJL.do">Start a New Search</a>
              		</c:if>	
              		<c:if test="${!empty sessionScope.username}">
                		<span class="style107"><a href="eviction-records.do">Start a New Search</a></span>
                	</c:if>
              	</p>
              		<p align="left"><span class="style107"><a href="javascript:window.print();"><img src="/springapp/images/common/print.png" alt="print" width="48" height="52" /></a></span></p>
              
               
              </td>
            </tr>
          </table>



<!--  Table starts here -->

 <table width="95%" align="center" cellpadding="0" cellspacing="0" class="evictionTable">
 <tr>
 <td colspan="2">
 <c:choose>
 	<c:when test="${eListsize > 0}">
 		<c:forEach items="${eList}" var="evicObj" varStatus="loop" begin="0">
 			
 			<neon:EvictionResultDetails obj="${evicObj}" />
 		</c:forEach>
 	</c:when>
 
 </c:choose>
 </td>
 </tr>
   
    
</table>

<table width="875" border="0">
   <tr>
     <td><h4 align="left" class="style107">DISCLAIMER</h4>
     <p align="left" class="style107"><span class="style31">Search Systems provides no  warranty of any type as to the accuracy of this information, and any reliance  on this information is solely at your own risk and responsibility. All  information retrieved from or through SearchSystems.net must be utilized in  accordance with the User Agreement and all applicable state and federal laws.</span></p></td>
   </tr>
</table>
          
		  <p>&nbsp;</p>
		  

</jsp:body>

</neon:EvictionFunnel>
								 