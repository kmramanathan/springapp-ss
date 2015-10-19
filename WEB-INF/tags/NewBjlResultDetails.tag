<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="obj" required="true" type="springapp.domain.NewBJLResults" %>
<%@attribute name="docno" required="false" type="java.lang.String" %>
<%@attribute name="typ" required="false" type="java.lang.String" %>
<%@attribute name="clazz" required="false" type="java.lang.String" %>
<%@attribute name="un" required="false" type="java.lang.String" %>
<%@attribute name="ftd" required="false" type="java.lang.String" %>
<%@attribute name="rownum" required="false" type="java.lang.String" %>


	<c:choose>
 		<c:when test="${obj.docket_number != docno or obj.filing_type_desc != ftd}"> 		
 		<c:if test="${not empty rownum}">
 			<tr>
   				<td colspan="6">
   		 		<br/><br/>
				</td>
   			</tr>
  	 	</c:if>
  	 	  	 	
	 		 <tr style="background:#bebaba;color:#000;width:100%;height:20px;text-align:center;">
	 		 	<th colspan="6" >
	 		 	<c:if test="${typ == 'BK'}">
	 		 		Records From:&nbsp;<c:if test="${empty obj.court_desc}">${obj.state} 
	 		 			
	 		 			</c:if>
	 		 		<c:choose>	 		 			
		 		 			<c:when test="${not empty obj.court_desc}">	 		 			
		 		 			<c:choose>
		 		 			<c:when test="${not empty obj.court_city}">						
								${obj.court_desc}, ${obj.court_city}
								<c:if test="${not empty obj.court_state}">
								, ${obj.court_state}
								<c:if test="${not empty obj.court_zip}">						
									${obj.court_desc}, ${obj.court_state}, ${obj.court_zip}		
											
								</c:if>	
								<c:if test="${not empty obj.court_phone}"><br>Phone: ${obj.court_phone}</c:if>
								</c:if>												
							</c:when>
							<c:otherwise>
								<c:if test="${not empty obj.court_state}">							
								<c:choose>
								<c:when test="${not empty obj.court_zip}">						
									${obj.court_desc}, ${obj.court_state}, ${obj.court_zip}			
									<c:if test="${not empty obj.court_phone}"><br>Phone: ${obj.court_phone}</c:if>		
								</c:when>	
								<c:otherwise>
								${obj.court_desc}, ${obj.court_state}
								</c:otherwise>
								</c:choose>
								</c:if>	
								<c:if test="${empty obj.court_state}">	
									${obj.court_desc}, <c:if test="${not empty obj.filing_state}">
									${obj.filing_state} </c:if>
									<c:if test="${empty obj.filing_state}">${obj.state} </c:if>
								</c:if>					
							</c:otherwise>
							</c:choose>
		 		 		</c:when>
	 		 		<c:otherwise>
		 		 		<c:if test="${not empty obj.filing_state}">${obj.filing_state} </c:if>
						<c:if test="${empty obj.filing_state}">${obj.state} </c:if>
	 		 		</c:otherwise>
							
					</c:choose>	
					
				 </c:if>
				 
				 
				 	<c:if test="${typ == 'TX'}">
	 		 		Records From:&nbsp; 
	 		 		<c:if test="${not empty obj.court_desc}">${obj.court_desc},  				 
					</c:if>
					<c:if test="${not empty obj.court_city}">${obj.court_city},</c:if>
					<c:if test="${not empty obj.court_state}">
					<c:choose>
						<c:when test="${not empty obj.court_zip}">						
							${obj.court_state}, ${obj.court_zip}					
						</c:when>
						<c:otherwise>
							${obj.court_state}
						</c:otherwise>
					</c:choose>	
									
					</c:if>
					
					<c:if test="${not empty obj.court_phone}"><br>Phone: ${obj.court_phone}</c:if>
					<c:choose>
						<c:when test="${empty obj.court_state}">
						<c:if test="${not empty obj.filing_state}">${obj.filing_state} 				 
					</c:if>
					<c:if test="${empty obj.filing_state}">${obj.state}  				 
					</c:if>
					</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>	
				 </c:if>	 	 			 
					 	 			 
	 		 	
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
					 				 <c:if test="${typ == 'BK'}">
								 	   ${obj.bankruptcy_type_desc}
								 	 </c:if>
								 	 <c:if test="${typ == 'TX'}">
								 	    ${obj.filing_group_desc}
								 	 </c:if>
						</div>
				 </th>

			 	</tr>
			 	
		   	   	<c:if test="${typ == 'BK'}">
		   	   		<tr><th><c:if test="${not empty obj.action_desc}">ACTION:</c:if></th>
					<th>${obj.action_desc}</th>
					<th>&nbsp;</th>	
					<th>FILING DATE:</th>
					<th colspan="2"> 
			   				<c:if test="${fn:length(obj.filing_date) > 0}">
								<c:out value="${fn:substring(obj.filing_date,4,6)}"/>/
								<c:out value="${fn:substring(obj.filing_date,6,8)}"/>/
								<c:out value="${fn:substring(obj.filing_date,0,4)}"/>
							</c:if>
					</th>
					</c:if>
		     	</tr>
		     	
		     	<c:if test="${typ == 'TX'}">
		   	   		<tr><th>LIABILITY AMOUNT:</th>
					<th><c:if test="${not empty obj.liability_amount}">$<fmt:formatNumber value="${obj.liability_amount}"/>
					</c:if></th>
					<th>&nbsp;</th>	
					<th>FILING DATE:</th>
					<th colspan="2"> 
			   			
			   				<c:if test="${fn:length(obj.filing_date) > 0}">
								<c:out value="${fn:substring(obj.filing_date,4,6)}"/>/
								<c:out value="${fn:substring(obj.filing_date,6,8)}"/>/
								<c:out value="${fn:substring(obj.filing_date,0,4)}"/>
							</c:if>
						
						</th>
		     		</tr>
		     	</c:if>
		    	<c:if test="${typ == 'BK'}">			
		   		<tr><th>TRUSTEE:</th>
		   			<th colspan="2">${obj.trustee}</th>		   			
		   			<th>INITIAL DATE:</th>
		   			<th colspan="2">
		   		
		   				<c:if test="${fn:length(obj.initial_date) > 0}">
							<c:out value="${fn:substring(obj.initial_date,4,6)}"/>/
							<c:out value="${fn:substring(obj.initial_date,6,8)}"/>/
							<c:out value="${fn:substring(obj.initial_date,0,4)}"/>
						</c:if>
				
					</th>
		  		</tr> 		    
		  		<tr>
		  		<th>ASSETS AVAILABLE:</th>
					<th> 
		    			<c:if test="${obj.assets_available == 'Y'}"> <c:out value="YES"/> </c:if>
		    			<c:if test="${obj.assets_available == 'N'}"> <c:out value="NO"/> </c:if>
		    		</th>
		    		<th>&nbsp;</th>
		    		<c:choose>
		    			<c:when test="${fn:length(obj.discharge_date) > 0}">
		    				<th>DISCHARGE DATE:</th>
		   					<th colspan="2">
							
				   				<c:if test="${fn:length(obj.discharge_date) > 0}">
								<c:out value="${fn:substring(obj.discharge_date,4,6)}"/>/
								<c:out value="${fn:substring(obj.discharge_date,6,8)}"/>/
								<c:out value="${fn:substring(obj.discharge_date,0,4)}"/>
								</c:if>					
							</th>
						</c:when>
						<c:otherwise>
							<th>&nbsp;</th>
				   		<th colspan="2">
				   		&nbsp;
						</th>
						</c:otherwise>
			</c:choose>
		  	</tr>
		 	
		  	<c:if test="${fn:length(obj.closed_date) > 0}">		
		   		<tr>
		   			<th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>CLOSED DATE:</th>
		   			<th colspan="2">
		   			
							<c:out value="${fn:substring(obj.closed_date,4,6)}"/>/
							<c:out value="${fn:substring(obj.closed_date,6,8)}"/>/
							<c:out value="${fn:substring(obj.closed_date,0,4)}"/>
						
			</th>
		  	</tr> 
		  	</c:if>
		  
		 	<c:if test="${fn:length(obj.update_date) > 0}">	
		  	<tr><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>UPDATE DATE:</th>
		   	<th colspan="2">
		   		
						<c:out value="${fn:substring(obj.update_date,4,6)}"/>/
						<c:out value="${fn:substring(obj.update_date,6,8)}"/>/
						<c:out value="${fn:substring(obj.update_date,0,4)}"/>
					
			</th>
		  	</tr> 
		  	</c:if>				    
		  	</c:if>
		 
		  	<c:if test="${typ == 'TX'}">			
		   	<tr>
		   	<th>&nbsp;</th><th>&nbsp;</th>
		   	<!--
		   	<th>AMOUNT:</th>
		   	<td><c:if test="${not empty obj.amount}">$<fmt:formatNumber value="${obj.amount}"/></c:if></td>
		   	-->
		   	<th>&nbsp;</th><th>INITIAL DATE:</th>
		  	<th colspan="2">
		   		
		   			<c:if test="${fn:length(obj.initial_date) > 0}">
						<c:out value="${fn:substring(obj.initial_date,4,6)}"/>/
						<c:out value="${fn:substring(obj.initial_date,6,8)}"/>/
						<c:out value="${fn:substring(obj.initial_date,0,4)}"/>
					</c:if>
				
			</th>
		  	</tr> 		    
		  	</c:if>
		   	<tr>
			<th colspan="2">Name</th><th width="110">SSN/EIN</th><th colspan="3">Address</th></tr> 
		 
		  	<tr>
			<th colspan="2">
			
			${obj.fullname}	 <br>${obj.name_type_desc } <br>
			<!-- ${obj.firstname} ${obj.middlename} ${obj.lastname} 
			<c:if test="${fn:length(obj.dob) > 0}">	
			DOB: 
		  				<c:out value="${fn:substring(obj.dob,4,6)}"/>/
						<c:out value="${fn:substring(obj.dob,6,8)}"/>/
						<c:out value="${fn:substring(obj.dob,0,4)}"/>
						<br>
		  	</c:if>	-->
		  	<c:if test="${fn:length(obj.businessname) > 0}">	
			Business: ${obj.businessname}	
		  				
		  	</c:if>	
		  	
			</th>			
			 <th>
			 		<c:choose>
			 				<c:when test="${fn:length(obj.ssn) > 0}">
			 					${fn:substring(obj.ssn,0,3)}-${fn:substring(obj.ssn,3,5)}-${fn:substring(obj.ssn,5,9)}
			 				</c:when>
			 		</c:choose>
			</th>
			 		<th colspan="3">${obj.house_number} ${obj.street_direction } ${obj.street_name } ${obj.street_suffix } ${obj.apartment_num }<br>
			   			<c:if test="${not empty obj.city}">${obj.city}, </c:if>${obj.state} ${obj.zipcode }
			   	    </th>
		 	</tr>
		 	
		 		
</c:when>
       <c:otherwise>
 			<tr>
			 		<th colspan="2">
			 		${obj.fullname}	 <br>${obj.name_type_desc } <br>
			<!-- ${obj.firstname} ${obj.middlename} ${obj.lastname} 
			<c:if test="${fn:length(obj.dob) > 0}">	
			DOB: 
		  				<c:out value="${fn:substring(obj.dob,4,6)}"/>/
						<c:out value="${fn:substring(obj.dob,6,8)}"/>/
						<c:out value="${fn:substring(obj.dob,0,4)}"/>
						<br>
		  	</c:if>	-->
		  	<c:if test="${fn:length(obj.businessname) > 0}">	
			Business: ${obj.businessname}	
		  				
		  	</c:if>	
			 		
			 		</th>
			 		<th>
			 		
			 				<c:if test="${fn:length(obj.ssn) > 0}">
			 					${fn:substring(obj.ssn,0,3)}-${fn:substring(obj.ssn,3,5)}-${fn:substring(obj.ssn,5,9)}
			 				</c:if>
			 			
			 		</th>
			 		<th colspan="3">${obj.house_number} ${obj.street_direction } ${obj.street_name } ${obj.street_suffix } ${obj.apartment_num }<br>
			   			<c:if test="${not empty obj.city}">${obj.city}, </c:if>${obj.state} ${obj.zipcode }</th>
		 	</tr> 		
 		</c:otherwise> 	
</c:choose>
