<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="obj" required="true" type="springapp.domain.NewBJLResults" %>
<%@attribute name="docno" required="true" type="java.lang.String" %>
<%@attribute name="typ" required="true" type="java.lang.String" %>
<%@attribute name="clazz" required="false" type="java.lang.String" %>
<%@attribute name="un" required="true" type="java.lang.String" %>
<%@attribute name="ftd" required="true" type="java.lang.String" %>
<%@attribute name="rownum" required="false" type="java.lang.String" %>


	<c:choose>
 		<c:when test="${obj.docket_number != docno or obj.filing_type_desc != ftd}">
 		 <c:if test="${not empty rownum}"> 		 
 			<tr>
   				<td colspan="5">
   		 		<br/><br/>
				</td>
   			</tr>
  	 	 </c:if> 	
  	 	 
  	 	     <tr style="background:#bebaba;color:#000;width:100%;height:20px;text-align:center;">
	 		 	<th colspan="6" >
	 		 		Records From:&nbsp; <c:if test="${not empty  obj.court_desc}">${obj.court_desc}, 
 										</c:if>
 										<c:choose>
						<c:when test="${not empty obj.filing_state}">${obj.filing_state}</c:when>
						<c:otherwise> ${obj.state}</c:otherwise>
					</c:choose>	
	 		 	
	 		 	</th>
	 		 </tr>	
	 		 <tr colspan="6" style="background:#bebaba;">		 	
	 		 	<th colspan="3">
	 		 	<div style="background:#bebaba;color:#000;width:450px;height:20px;border:0px solid red">
			 		 Docket#:&nbsp;&nbsp;
	 		 	<c:choose>
			 			<c:when test="${not empty obj.docket_number}">${obj.docket_number}</c:when>
			 			<c:otherwise>${un}</c:otherwise>
			 		</c:choose>	
					</div>	 		 
				</th>
			    <th colspan="3">
						<div style="width:450px;border:0px solid green;float:right;text-align:right;" >
					 				 ${obj.filing_group_desc}
						</div>
				 </th>
			 	</tr>	 		 	
	 		 
		   	 <tr>
			  <th>LIABILITY AMOUNT:</th>
		  <th><c:if test="${not empty obj.liability_amount}">$<fmt:formatNumber value="${obj.liability_amount}"/></c:if></th>
			 <!-- <th>ACTION:</th>
			 <td>${obj.action_desc}</td> -->
			 
			 <th>&nbsp;</th>	
			 <th>FILING DATE:</th>
			 <th colspan="2"> 
			   			<c:choose>
			   				<c:when test="${fn:length(obj.filing_date) > 0}">
								<c:out value="${fn:substring(obj.filing_date,4,6)}"/>/
								<c:out value="${fn:substring(obj.filing_date,6,8)}"/>/
								<c:out value="${fn:substring(obj.filing_date,0,4)}"/>
							</c:when>
						</c:choose>
					</th>
		   </tr>		
		   <!--  <tr>
		   <th>&nbsp;</th><th>&nbsp;</th>
		  <th>JUDGMENT AMOUNT:</th>
		   <th><c:if test="${not empty obj.judgement_amount}">$<fmt:formatNumber value="${obj.judgement_amount}"/>
		   </c:if>
		   </th> 
		   
		   <th>&nbsp;</th>
		   
		    <th>&nbsp;</th>
		   <th>&nbsp;</th>		   
		  <!--
		   <th>INITIAL DATE:</th>
		   <th>
		   		<c:choose>
		   			<c:when test="${fn:length(obj.initial_date) > 0}">
						<c:out value="${fn:substring(obj.initial_date,4,6)}"/>/
						<c:out value="${fn:substring(obj.initial_date,6,8)}"/>/
						<c:out value="${fn:substring(obj.initial_date,0,4)}"/>
					</c:when>
				</c:choose>
			</th> 
			<th>&nbsp;</th>
		  </tr> -->
		 <!-- <c:if test="${not empty obj.liability_amount}">
		  <tr>
		    <th>&nbsp;</th><td>&nbsp;</td>
		  <!-- <th>LIABILITY AMOUNT:</th>
		  <td><c:if test="${not empty obj.liability_amount}">$<fmt:formatNumber value="${obj.liability_amount}"/>
		  </c:if>
		  </td>
		  <th>&nbsp;</th>
		  <th>&nbsp;</th>
		   <th>&nbsp;</th>		   
		  <!--
		  <th>UPDATE DATE:</th>
		   <th>
		   		<c:choose>
		   			<c:when test="${fn:length(obj.update_date) > 0}">
						<c:out value="${fn:substring(obj.update_date,4,6)}"/>/
						<c:out value="${fn:substring(obj.update_date,6,8)}"/>/
						<c:out value="${fn:substring(obj.update_date,0,4)}"/>
					</c:when>
				</c:choose>
			</th>
			<th>&nbsp;</th>
		  </tr> 		  
		 </c:if> -->
		 
		   <c:if test="${fn:length(obj.filing_date) > 0}">
		  <tr><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th>
		  <th>
					<c:choose>
		   			<c:when test="${fn:length(obj.judgement_date) > 0}">
					JUDGMENT DATE: 					
					</c:when>
				</c:choose>
		  </th>
		   <th>
		   		<c:choose>
		   			<c:when test="${fn:length(obj.judgement_date) > 0}">
						<c:out value="${fn:substring(obj.judgement_date,4,6)}"/>/
						<c:out value="${fn:substring(obj.judgement_date,6,8)}"/>/
						<c:out value="${fn:substring(obj.judgement_date,0,4)}"/>
					</c:when>
				</c:choose>
			</th>
			<th>&nbsp;</th>
		  </tr> 
		  </c:if>
		  <tr>		  
		   <!-- <th>&nbsp;</th><th>&nbsp;</th>
		 
		  <th>AMOUNT:</th>
		  <th><c:if test="${not empty obj.amount}">$<fmt:formatNumber value="${obj.amount}"/></c:if></th>
		  
		  <th colspan="4">&nbsp;</th>
		  </tr> 		    				  		    		    
		  -->
		   	<tr>
			<th colspan="2">Name</th><th width="110">SSN/EIN</th><th colspan="3">Address</th></tr> 
		 
		  	<tr>
			 		<th colspan="2">
					<c:if test="${not empty obj.lastname}"> </c:if>
       					${obj.firstname} ${obj.middlename} ${obj.lastname} <br>${obj.name_type_desc }
					</th>
			 		<th width="110">
			 			<c:choose>
			 				<c:when test="${fn:length(obj.ssn) > 0}">
			 					${fn:substring(obj.ssn,0,3)}-${fn:substring(obj.ssn,3,5)}-${fn:substring(obj.ssn,5,9)}
			 				</c:when>
			 			</c:choose>
			 		</th>
			 		<th colspan="3">
					${obj.house_number} ${obj.street_direction } ${obj.street_name } ${obj.street_suffix } ${obj.apartment_num }<br>
			   			<c:if test="${not empty obj.city}">${obj.city},</c:if> 
						${obj.state} ${obj.zipcode }
					</th>
		 	</tr>
 		</c:when>
 		<c:otherwise>
 		
 			<tr>
			 		<th colspan="2">
					<c:if test="${not empty obj.lastname}"> </c:if>
					${obj.firstname} ${obj.middlename} ${obj.lastname}<br>${obj.name_type_desc }
					</th>
			 		<th>
						<c:choose>
			 				<c:when test="${fn:length(obj.ssn) > 0}">
			 					${fn:substring(obj.ssn,0,3)}-${fn:substring(obj.ssn,3,5)}-${fn:substring(obj.ssn,5,9)}
			 				</c:when>
			 			</c:choose>
			 		</th>
			 		<th colspan="3">${obj.house_number} ${obj.street_direction } ${obj.street_name } ${obj.street_suffix } ${obj.apartment_num }<br>
			   			<c:if test="${not empty obj.city}">${obj.city},</c:if>
						${obj.state} ${obj.zipcode }
						</th>
		 	</tr> 		
 		</c:otherwise> 	
 	</c:choose>
