<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:NewFunnel title="View Print">
<jsp:attribute name="stylesheet">BJL_02.css,new-funnel.css</jsp:attribute>

<jsp:body>

<style type="text/css">
.tablepadd th{padding:8px 0 8px 0;background:#68b868;font-size:16px;color:#000;}
tr.odd{background:#ccc;}
tr.even{background:white;}
.rowstate th{color:#000;background:#052A5E;font-size: 16px;}
.tablepadd th span{color:#000;font-size: 16px;font-weight:bold;}
</style>
		  <table width="906" border="0">
            <tr valign="top">
              <td colspan="2"  width="712">
              <h2 align="left" class="style114">Bankruptcy, Judgment &amp; Tax Lien Search Results</h2>
              </td>              
            </tr>
            <tr valign="top">
            <td width="485">
            	 <h2 align="left" class="style107">View and Print Details</h2>
            </td>
             <td>
             <table>
             <tr>
             <td>
                   		<c:if test="${empty sessionScope.username}">
              			<a href="newSearchBJL.do">Start a New Search</a>
              		</c:if>	
              		<c:if test="${!empty sessionScope.username}">
                		<span class="style107">
                		<a href="newSearchBJL.do">Start a New Search</a>
                		</span>
                	</c:if>              	
             </td>
             <td>
             <span class="style107"><a href="newResultsBJL.do">Back to Summary</a></span> 
             </td>
             </tr>
             <tr>
             <td>
             	<span class="style107"><a href="javascript:window.print();">Print this page</a></span>
             </td>
              <td>
               <span class="style107"><a href="newResultsBJL.do?download=true">Download this page</a></span>             
             </td>
             </tr>		
             <tr>
             <td colspan="2" >           
              <span class="style107"><a href="newResultsBJL.do?downloadall=true">Download all results</a></span>
             </td>
             </tr>
             </table>  
              </td>
            </tr>
          </table>



<!--  Table starts here -->

 <table width="906" align="center" style="margin-left:15px;" border="0">
   
   <!-- Start bankruptic list -->
   <c:if test="${bankSize > 0}">
   <tr class="tablepadd">
 		<th>BANKRUPTCIES</th><th>&nbsp;</th>
   		<th colspan="4">
   		<!--  Records From:&nbsp;
 				<span>
 					<c:if test="${not empty bankCourt}">${bankCourt}, 
 					</c:if>${bankState}
 					</span> -->
 		</th>
 	</tr>
 	</c:if>
 	<tr>
 		<td colspan="6">
	   		
	   			<c:if test="${bankSize > 0}">
	   				<c:set var="docno" value=""/>
	   				<c:set var="rownum" value="0"/>  
	   				<c:set var="unknown" value="UNKNOWN"/>
	   				<c:set var="ftd" value=""/>
	   			    <c:forEach items="${bankList}" var="bankObj" varStatus="loop" begin="0">
	   				  <neon:NewBjlResultDetails docno="${docno}" rownum="${docno}"  ftd="${ftd}" un="${unknown}" typ="BK" clazz="${loop.index % 2 == 0 ? 'odd' : 'even'}" obj="${bankObj}" />
			 		  <c:set var="docno" value="${bankObj.docket_number}"/>	
			 		  <c:set var="ftd" value="${bankObj.filing_type_desc}" />			 		   				 	      
					</c:forEach> 					
	   			</c:if>  
</td></tr>
   <!-- end bankruptic list -->
   <tr>
 		<td colspan="6">
		&nbsp;<br/>
   </td></tr>
   <!-- Judgments Starts -->
   <c:if test="${judgeSize > 0}">
   <tr class="tablepadd">
   		<th>JUDGMENTS
   		</th>
   		<th>&nbsp;
   		</th>
   		<th colspan="4">
   	<!-- Records From:&nbsp;
 			<span>
 				<c:if test="${not empty judgeCourt}">${judgeCourt}, 
 				</c:if>${judgeState}
 			</span>--> 
 		</th>
   </tr>
   </c:if>
   <tr>
    	<td colspan="6">
   			<c:choose>  
	   			<c:when test="${judgeSize > 0}">
	   				<c:set var="docno" value=""/> 
	   				<c:set var="unknown" value="UNKNOWN"/>
	   				<c:set var="ftd" value=""/>
	   				<c:set var="rownum" value=""/>  
	   				    
				 	<c:forEach items="${JudgeList}" var="judgeObj" varStatus="loop" begin="0">
				 		<neon:NewJudgeBjlResultDetails docno="${docno}" ftd="${ftd}" un="${unknown}"  rownum="${docno}"  clazz="${loop.index % 2 == 0 ? 'odd' : 'even'}" typ="BL" obj="${judgeObj}" />
				 		<c:set var="docno" value="${judgeObj.docket_number}"/>
				 		<c:set var="ftd" value="${judgeObj.filing_type_desc}" />
				 		 
				 	</c:forEach>
	   			</c:when>  
	   			 	
			</c:choose>		       
		 			   
			</td>
   	</tr>
  <!-- Judgments end -->
   <tr>
 		<td colspan="6">
		&nbsp;<br/><br/>
   </td></tr>
 <!-- TAX LIENS Starts -->
 <c:if test="${taxSize > 0}">
   <tr class="tablepadd">
   		<th>TAX LIENS</th>
   		<th>&nbsp;</th>
   		<th colspan="4">
   		<!--
   		Records From:&nbsp;
 			<span>
 				<c:if test="${not empty taxCourt}">${taxCourt}, 
 				</c:if>${taxState}
 			</span>
 		-->
 		</th>
   </tr>
  </c:if>
  
   <tr>
   		<td colspan="6">
   		 	<c:choose>  
	   			<c:when test="${taxSize > 0}">
	   				<c:set var="docno" value=""/>
	   				<c:set var="unknown" value="UNKNOWN"/>
	   				<c:set var="ftd" value=""/>
	   				<c:set var="rownum" value=""/>  
	   				 
				 	<c:forEach items="${TaxlienList}" var="taxObj" varStatus="loop" begin="0" step="1">					 						 		 		
				 		<neon:NewBjlResultDetails docno="${docno}"  ftd="${ftd}" un="${unknown}" rownum="${docno}" clazz="${loop.index % 2 == 0 ? 'odd' : 'even'}" typ="TX" obj="${taxObj}" />
				 		<c:set var="docno" value="${taxObj.docket_number}"/>
				 		<c:set var="ftd" value="${taxObj.filing_type_desc}" />
				 		
					</c:forEach>	
	   			</c:when>  
	   					 	
			</c:choose>			
		</td>
   </tr>
  	 
<!-- TAX LIENS end -->       
       
</table>

<table width="875" border="0">
   <tr>
     <td><h4 align="left" class="style107">DISCLAIMER</h4>
     <p align="left" class="style107"><span class="style31">Search Systems provides no  warranty of any type as to the accuracy of this information, and any reliance  on this information is solely at your own risk and responsibility. All  information retrieved from or through SearchSystems.net must be utilized in  accordance with the User Agreement and all applicable state and federal laws.</span></p></td>
   </tr>
</table>
          
		  <p>&nbsp;</p>
		  

</jsp:body>

</neon:NewFunnel>
								 