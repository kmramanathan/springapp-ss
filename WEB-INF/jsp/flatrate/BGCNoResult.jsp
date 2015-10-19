<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:FRBGCcommonPage title="no records found">
<jsp:attribute name="stylesheet">flatrate.css</jsp:attribute>

<jsp:body>

	<tr>
		<td width="950" style="height:100%; padding: 10px 0;"><table width="884" align="center">
          <tr>
            <td><h2 align="left" class="style108">We're sorry but no records were found in our database for: </h2>
              <div align="left" class="style107">
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <table width="314" cellspacing="0">
                  <tbody>
                    <tr>
                      <td width="156" nowrap="nowrap">First Name:</td>
                      <td width="152"><strong>${sfc.bgcFirstName}</strong></td>
                    </tr>
                    <tr>
                      <td nowrap="nowrap">Last Name:</td>
                      <td><strong>${sfc.bgcLastName }</strong></td>
                    </tr>
                    <tr>
                      <td>Location:</td>
                      <td><strong>Nationwide</strong></td>
                    </tr>
                    <tr>
                      <td>Date of Birth:</td>
                      <td><strong>${sfc.bgcDobMonth}/${sfc.bgcDobDay}/${sfc.bgcDobYear}</strong></td>
                    </tr>
                  </tbody>
                </table>
                <p>&nbsp;</p>
                <p>Tips to help you in future searches:
                </p>
                <ol>
                  <li> Was the first name and last name entered in the correct fields?  Was the birth date   	correct? </li>
                  <li> Did you input a middle initial, middle name, or designation such as Jr., Sr., or PhD? Be sure to leave designations out of the search fields. This search does not allow middle initials in the query.</li>
                  <li> Our criminal record data is the largest, most complete, and up-to-date information       available instantly on the Internet, but we do NOT have every criminal record.  Each       state and county has different rules as to if and how they will allow access to their       records.  Only law enforcement has access to the true "National" search of the FBI National Criminal Information Center (NCIC) databases. Check our <a href="BGCDatasource.do" target="blank">coverage page</a> to see what records and counties are included.</li>
                  <li> Take advantage of our free <a href="http://www.searchsystems.net/free-criminal-records-search-guide.php">Search Systems Guide to Criminal Records on the Internet</a> to help you find the information you need.  Our guide lists the databases contained in our       database, but also includes a complete description of our <a href="http://www.searchsystems.net/list.php?nid=494">Criminal Records Directory</a> which contains thousands of links to criminal court records, "wanted" lists and databases,       warrants, arrests, bookings, inmate, parole, and sex offender databases, as well as links to       information on how to order criminal histories from state agencies - all organized nationwide       and by state and category so that they are easy to find. </li>
                </ol>
                <p>&nbsp;</p>
              </div>
              <p align="left"><span class="style107"><a href="BGCsearchLanding.do">Search Again</a></span></p></td>
          </tr>
        </table>
		  <p>&nbsp;</p>
	  </td>
	</tr>

</jsp:body>
</neon:FRBGCcommonPage>	
