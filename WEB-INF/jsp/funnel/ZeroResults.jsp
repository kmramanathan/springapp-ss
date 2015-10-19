<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Search Results" rightSidebar="">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>
<div id="title"><a href="searchAgain.do">Search Again</a></div>
              
<div id="formwrapper">

  <h3>Results</h3>

We're sorry but no records were found in our database for:

<p></p>

<table cellspacing="0" style="background-color: #FFFFFF;">
    <tr>
        <td nowrap="nowrap" style="padding: 0 75px 0 0;">First Name:</td>
        <td class="fill" style="padding: 0;"><strong><c:out value="${firstName}"/></strong></td>
    </tr>
    <tr>
        <td nowrap="nowrap" style="padding: 0 75px 0 0;">Last Name:</td>
        <td class="fill" style="padding: 0;"><strong><c:out value="${lastName}"/></strong></td>
    </tr>
    <tr>
        <td style="padding: 0.30em 0 0 0;">Date of Birth:</td>
        <td style="padding: 0.30em 0 0 0;"><strong><c:out value="${DOB}"/></strong></td>
    </tr>
</table>

<p></p>

Tips to help you in future searches:

<ol>
	<li>
	Was the first name and last name entered in the correct fields?  Was the birth date 
	correct?  If you searched by state, did you choose the correct one?
	</li>

    <li>
    Did you input a middle initial, middle name, or designation such as Jr., Sr., or PhD?  
    Be sure to leave those out of the search fields as our database matches on first name, 
    last name, and birth date only.
    </li>
    
    <li>
    Our criminal record data is the largest, most complete, and up-to-date information 
    available instantly on the Internet, but we do NOT have every criminal record.  Each 
    state and county has different rules as to if and how they will allow access to their 
    records.  Only law enforcement has access to the true "National" search of the FBI's 
    National Criminal Information Center (NCIC) databases.    
    </li> 
    
    <li>
    Take advantage of our free
    <a href="criminalRecordsGuide.do">Search Systems Guide to Criminal Records on the Internet</a>
    to help you find the information you need.  Our guide lists the databases contained in our 
    Premium database, but also includes a complete description of our 
    <a href="http://www.searchsystems.net/list.php?nid=494">Criminal Records Directory</a> 
    which contains thousands of links to criminal court records, "wanted" lists and databases, 
    warrants, arrests, bookings, inmate, parole, and sex offender databases, as well as links to 
    information on how to order criminal histories from state agencies - all organized nationwide 
    and by state and category so that they are easy to find.    
    </li>    
</ol>


</div>
</jsp:body>

</neon:funnelPage>
