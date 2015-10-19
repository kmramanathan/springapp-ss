<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:NewCorpFunnel title="View Print">
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
.style118 {color: #FFFFFF;background: #052A5E;padding:5px 0 5px 0;font-weight: bold;font-size: 16px;}
.style1191{color: #000000;background: #66CCFF;padding:5px 0 5px 0;font-weight: bold;}
.style119 {color: #000000; }
.style120 {color: #FF0000}
.style121 {color: #003366; font-family: Arial, Helvetica, sans-serif;}
.corpTable tr,.corpTable td
{
margin:0;padding:2px 0 2px 0;
font-size: 12px;
}
.odd tr,.odd td{margin:0;padding:2px 0 2px 0;font-size: 12px;}
.even tr,.even td{margin:0;padding:2px 0 2px 0;font-size: 12px;}
.strong{font-weight: bold;font-size: 12px;text-indent: 4px;}
.corpresult tr td{text-indent: 4px;}
#addressDiv .odd{background: #FFFF99;font-size: 12px;}
#addressDiv .even{background: #ffffff;font-size: 12px;}
#nameDiv .odd{background: #FFFF99;font-size: 12px;}
#nameDiv .even{background: #ffffff;font-size: 12px;}
#historyDiv .odd{background: #FFFF99;font-size: 12px;}
#historyDiv .even{background: #ffffff;font-size: 12px;}
</style>

		  <table width="885" border="0" align="left">
            <tr>
              <td width="50%" height="120"><h1 align="left" class="style114">Corporations Search Results</h1>
               
               <p>Name Searched: <strong>${corpName}${corpBName}</strong></p>
               <p>State: <strong>${corpState}</strong></p>
                
                <p>Reference: <strong>${corpRefer}</strong></p>
               </td>
              <td width="50%">
              	<p align="center" class="style107">
                   	<c:if test="${empty sessionScope.username}">
              			<a href="corp-bus-search.do">Start a New Search</a>
              		</c:if>	
              		<c:if test="${!empty sessionScope.username}">
                		<span class="style107"><a href="corp-bus-search.do">Start a New Search</a></span>
                	</c:if>
              	</p>
              		<p align="center"><span class="style107"><a href="javascript:window.print();"><img src="/springapp/images/common/print.png" alt="print" width="48" height="52" /></a></span></p>
              
               <div style="border: 3px solid #003366;font-size: 12px;"><p>This information comes from state registries and is only as current as the information they provide us. For the most recent status report, <a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Corporations_and_Companies/">go here.</a></p></div>
              </td>
            </tr>
          </table>



<!--  Table starts here -->
<div style="clear:both;">&nbsp;</div>
 <table width="95%" align="center" cellpadding="0" cellspacing="0" class="corpTable">
 <tr>
 <td colspan="2">
 
 		<c:forEach items="${corpResList}" var="corpObj" varStatus="loop" begin="0">
 		<c:set var="fileno" value="${corpObj.filing_number}"/>
 			<c:if test="${corpObj.filing_number == fileno}">
 			
 			<neon:CorpResultDetails obj="${corpObj}" count="${loop.count}"/>
 			
 			<div id="addressDiv">
 			<c:set var="row" value="1"/>
 			<c:set var="clazz" value="1"/>
 			<c:forEach items="${addrList}" var="addrObj" varStatus="loop" begin="0">
 				
 				<c:if test="${addrObj.filing_number == corpObj.filing_number}">
 					<neon:CorpAddress addrObj="${addrObj}" clazz="${loop.index % 2 == 0 ? 'odd' : 'even'}" count="${row}"/>
					<c:set var="row" value="${row+1}"/>
			   </c:if>
 			
 			</c:forEach>
 			</div>
 			<div id="nameDiv">
 			<c:set var="row" value="1"/>
 			<c:forEach items="${nameList}" var="nameObj" varStatus="loop" begin="0">
 				
 				<c:if test="${corpObj.filing_number == nameObj.filing_number}">
 				<neon:CorpNameInfo nameObj="${nameObj}" clazz="${loop.index % 2 == 0 ? 'odd' : 'even'}" count="${row}" />
 				<c:set var="row" value="${row+1}"/>
 				</c:if>
 				
 			</c:forEach>
 			</div>
 			<div id="historyDiv">
 			
 			<c:set var="row" value="1"/>
 			<c:forEach items="${historyList}" var="hisObj" varStatus="loop" begin="0">
 				<c:if test="${corpObj.filing_number == hisObj.filing_number}">
 					<neon:CorpHistory hisObj="${hisObj}" clazz="${loop.index % 2 == 0 ? 'odd' : 'even'}" count="${row}" />
 					
 					<c:set var="row" value="${row+1}"/>
 				</c:if>
 			
 			</c:forEach>
 			</div>
 			</c:if>
 			
 		</c:forEach>
 	
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

</neon:NewCorpFunnel>
								 