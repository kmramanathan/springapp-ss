<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:CrimeFunnel title="Search Results">
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css</jsp:attribute>

<jsp:body>
<style type="text/css">
.styletable111{font-size: 13px}
</style>
<div id="formwrapper">
<c:set var="fname" value="${firstName}" scope="session"/>
<c:set var="lname" value="${lastName}" scope="session"/>
<c:set var="location" value="${location}" scope="session"/>
<c:set var="dob" value="${DOB}" scope="session"/>
<c:set var="aliasSearchFlag" value="${aliasSearchFlag}" scope="session"/>
<c:set var="aliasSSN" value="${aliasSSN}" scope="session"/>
<div style="color:red;">NOTE: We are not able to store these results for later viewing. Click the "Print Version" link below and then save or print your results.</div>
<table width="99%" border="0" cellpadding="0" cellspacing="0">
<tr><td><h3>Search Results</h3></td><td align="right"><h3 align="right"><a href="no-records-print.jsp" target="blank">Print Version</a></h3></td></tr></table>
<p>Your <c:choose><c:when test="${aliasSearchFlag}">AKA / Alias Criminal</c:when><c:otherwise>criminal record</c:otherwise></c:choose> Search is complete.</p>        				
<c:choose>
<c:when test="${location eq 'all'}">
<p style="color:#000;">We searched over 550 million criminal records nationwide and over 100 national and international terrorist and debarred persons databases and found NO criminal disposition records for your subject, based on your search criteria:</p>
</c:when>
<c:otherwise>
<p style="color:#000;">We searched available criminal disposition records and did not find any records that matched your subject, based on your search criteria:</p>
</c:otherwise></c:choose>
<table cellspacing="0" cellpadding="0" border="0" style="background-color: #FFFFFF;margin-left: 55px;">
    <tr>
        <td nowrap="nowrap" style="padding: 0 75px 0 0;">First Name:</td>
        <td class="fill" style="padding: 0;"><strong><c:out value="${firstName}"/></strong></td>
    </tr>
    <tr>
        <td nowrap="nowrap" style="padding: 0 75px 0 0;">Last Name:</td>
        <td class="fill" style="padding: 0;"><strong><c:out value="${lastName}"/></strong></td>
    </tr>
     <tr>
        <td nowrap="nowrap" style="padding: 0 75px 0 0;">Location:</td>
        
        <c:choose>
        	<c:when test="${location eq 'all'}">
        		<td class="fill" style="padding: 0;"><strong>Nationwide</strong></td>
        	</c:when>
        	<c:otherwise>
        		<td class="fill" style="padding: 0;"><strong><c:out value="${location}"/></strong></td>
        	</c:otherwise>
        </c:choose>
        
        
    </tr>
    <tr>
        <td style="padding: 0.30em 0 0 0;">Date of Birth:</td>
        <td style="padding: 0.30em 0 0 0;"><strong><c:out value="${DOB}"/></strong></td>
    </tr>
    <c:if test="${aliasSearchFlag && aliasSSN ne null}">
    <tr>
     	<td style="padding: 0.30em 0 0 0;">SSN:</td>        
    	<td style="padding: 0.30em 0 0 0;"><strong><c:out value="${aliasSSN}"/></strong></td>
	</tr>
	</c:if>
</table>
<p>&nbsp;</p>
 <c:choose>
	<c:when test="${aliasSearchFlag}">
		<c:choose>
			<c:when test="${location eq 'all'}">
				<table align="center" class="styletable111">
				<tr align="center">
				<td width="120"><b>State</b></td><td width="150"><b>Name Searched</b></td><td width="100">&nbsp;</td>
				<td width="120"><b>Records Found</b></td><td width="100"><b>Coverage</b></td><td width="150">&nbsp;</td>
				</tr>
				<c:forEach var="displaystates" items="${USStatelist}">
				<tr align="center"><td>${displaystates.value}</td><td>${firstName}&nbsp;${lastName}</td>
				<td>&nbsp;</td><td align="center">0</td>
				<td><c:choose> <c:when test="${displaystates.key eq 'us' or displaystates.key eq 'all'}">
				<a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?view=us" target="blank">Coverage</a></c:when>
				<c:otherwise><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?state=${displaystates.key}" target="blank">Coverage</a>
				</c:otherwise> </c:choose></td><td>&nbsp;</td>
				</tr>
				
				</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<table align="center" class="styletable111">
					<tr align="center"> 
						<th width="120">State</th><th width="150">Name Searched</th>
						<th width="120">Records Found</th><th width="100">Coverage</th>
					</tr>
					<c:forEach var="displaystates" items="${USStatelist}">
					<c:if test="${displaystates.key eq location}">
						<tr align="center">
							<td>${displaystates.value}</td><td>${firstName}&nbsp;${lastName}</td>
							<td>0</td><td><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do?state=${displaystates.key}" target="blank">Coverage</a></td>
						</tr>
					</c:if>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
		<c:when test="${location eq 'all'}">
			<table align="center" class="styletable111">
			<tr align="center"> <td width="220"><b>State</b></td><td width="300"><b>Name Searched</b></td><td width="100"><b>Date of Birth</b></td><td width="120"><b>Records Found</b></td></tr>
			<c:forEach var="displaystates" items="${USStatelist}">
			
			<tr align="center"><td>${displaystates.value}</td><td>${firstName}&nbsp;${lastName}</td><td>${DOB}</td><td align="center">0</td></tr>
			
			</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<table align="center" class="styletable111">
			<tr align="center"> <th width="220">State</th><th width="300">Name Searched</th><th width="100">Date of Birth</th><th width="120">Records Found</th></tr>
			<c:forEach var="displaystates" items="${USStatelist}">
			<c:if test="${displaystates.key eq location}">
			<tr align="center"><td>${displaystates.value}</td><td>${firstName}&nbsp;${lastName}</td><td>${DOB}</td><td>0</td></tr>
			</c:if>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	</c:otherwise>
</c:choose>

<p>If you believe there is a criminal record for this person and it didn't appear, there are a number of reasons that this might happen.</p>
<ol>
<li>Check the first name and last name.  Was each spelled correctly?  Were the first and last names in the correct fields? Was the correct birth date entered? </li>
<li>Did you input a middle name, middle initial, or designation such as Jr., Sr., or PhD into the first or last name field? A middle initial should ONLY go in the middle initial field. Designations such as Jr., Sr., or PhD should not be entered at all.</li>
<li>Was the conviction in Federal Court?  If so, that information may only be found through the <a href="https://pacer.login.uscourts.gov/cgi-bin/login.pl?" target="_blank">Federal PACER Court System</a>.  PACER Registration and a small fee per search is required.</li>
<li>If the record was expunged or was a juvenile conviction, it should not appear in our database.</li>
<li>Are you looking for an arrest or warrant?  Criminal records consist of dispositions and convictions.  If a person has been arrested, but there is no disposition or conviction, then that arrest hasn't yet become a criminal record.</li>
<li>Our criminal record data is the largest, most complete, and up-to-date information publicly available online in a single source, but not every jurisdiction makes their information available to the public.  Check our <a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do" target="blank">coverage page</a>, use our <a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/" target="blank">Criminal Records Directory</a>, or <a href="http://publicrecords.searchsystems.net/contact.php" target="_blank">contact us</a> if you need help.</li>
</ol>

                <p>Here are some tips to help you in the future:
                </p>

                <ol>
                  <li> Try searching with a partial name to get more results.  For example, do a partial name search on "Ric" to get results for Rick, Ricky, Richard, Richie, and Rickie.</li>
                  <li> Try searching without the exact date of birth.  We allow searches by birth year and allow you to do expand that up to a six-year range. </li>
                  <li> Not every jurisdiction provides a birth date with a criminal record.  If it isn't a common name, try using the check box that says "Include results where the DOB is missing or unavailable."  We don't recommend this option with common names (e.g. John Anderson, or Steve Smith) as it will take you beyond our 500 result limit.  Most of our terrorist and debarred persons databases do not have birth dates in the records, so check the "Include results" box to make sure those records are searched as well.</li>
                  <li>Check our <a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do" target="blank">coverage page</a> before you do a search.</li>
                  <li> Take advantage of our free <a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/" target="_blank">Criminal Records Directory</a> to find: state rules about criminal records; access thousands of free criminal court records, inmate records, "wanted" lists and databases, warrants, arrests, bookings, parole, and sex offender databases: and find information on how to order "official" criminal histories from state agencies.</li>
                  <li>Check out	our "<a href="https://www.searchsystems.net/springapp/funnel/BestResultsGuide.html" target="blank">Best Results Guide</a>" for more helpful tips</li>
                </ol>
                <p>&nbsp;</p>

			<p align="right">
				<span class="style107">
					<c:choose>
						<c:when test="${aliasSearchFlag}"><a href="aliasSearch.do">Back to Search Page</a></c:when>
			        	<c:otherwise><a href="newSearch.do">Back to Search Page</a></c:otherwise>
		        	</c:choose>
	        	</span>
        	</p>
			

</div>
</jsp:body>

</neon:CrimeFunnel>
