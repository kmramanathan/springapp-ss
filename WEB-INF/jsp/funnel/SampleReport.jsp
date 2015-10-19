<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Sample Report" rightSidebar="SampleReportSidebar.jsp">

<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<style type="text/css">
<!--
#report h1 { font-size:1.4em; color:#036; margin:0.5em 0; font-weight: bold; padding-bottom: 5px; }
#report h2,h3 { font-size:1.2em; color:#036; margin:0.5em 0; font-weight: bold; padding: 5px 0px 5px 0px; }
#report table,tr,td { font-size:10pt; font-family: Arial, Helvetica, sans-serif; }
-->
</style>

    <div id="report">

            <h1>Criminal Records - Sample Report </h1>

      <h2>Offender Info - Record 1</h2>
            
              <table border="0" cellpadding="5" cellspacing="0">
                <tr>
                  <td width="242">
                    <strong>Full Name</strong><br />
                    DOE, JOHN N
                  </td>
                  <td width="90"><strong>DOB</strong><br />
                    01-01-1900
                  </td>
                  <td colspan="2"><strong>ID</strong><br />
                    XX-1234-XX-1234
                  </td>
                </tr>
                <tr>
                  <td><strong>Address</strong><br />
                    4800 HWY 480N
                  </td>
                  <td><strong>City</strong><br />
                    Hometown
                  </td>
                  <td width="67"><strong>State</strong><br />
                    NC 
                  </td>
                  <td width="84"><strong>Postal Code</strong><br />
                    28000
                  </td>
                </tr>
                <tr>
                  <td><strong>Gender</strong><br />
                    Male
                  </td>
                  <td><strong>Race</strong><br />
                    W
                  </td>
                  <td><strong>County</strong><br />
                    ---
                  </td>
                  <td><strong>Country</strong><br />
                    --- 
                  </td>
                </tr>
                <tr>
                  <td><strong>Height</strong><br />
                    6 - 0 
                  </td>
                  <td><strong>Weight</strong><br />
                    185 
                  </td>
                  <td><strong>Eye Color</strong><br />
                    Brown
                  </td>
                  <td><strong>Hair Color</strong><br />
                    Brown
                  </td>
                </tr>
                <tr>
                  <td><strong>Record Source</strong><br />
                    North Dakota Admin Office of Courts
                  </td>
                  <td><strong>Record State</strong><br />
                    ND
                  </td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
            </table>
            </div>

    <h3>Aliases</h3>
            

            <p>DOE, JOHNNY; DOE, JOHN E </p>


            <h3>Offenses</h3>

            <p><strong>Result #1</strong></p>
            
              <table border="0" cellpadding="5" cellspacing="0">
                <tr>
                  <td width="124">Description</td>
                  <td width="259">SEXUAL ASSAULT</td>
                </tr>
                <tr>
                </tr>
                <tr>
                  <td>Degree Of Offense</td>
                  <td>MISDEMEANOR CLASS A</td>
                </tr>
                <tr>
                  <td>Offense Date</td>
                  <td>2004-01-15</td>
                  </tr>
                  <tr>
                  <td>Sentence Date</td>
                  <td>2004-12-01</td>
                </tr>
                <tr>
                  <td>Probation</td>
                  <td>TO REPORT TO COUNTY CORRECTIONAL CENTER ON MARCH 1, 2005 AT 5:00 P.M. AND TO BE RELEASED AUGUST 1, 2005 AT 7:00 A.M. TO REPORT THE NEXT 6 WEEKENDS IN THIS MANNER UNTIL THE 45 DAYS HAVE BEEN SERVED UNLESS HE IS ENTERED INTO IN PATIENT TREATMENT, THEN DAY FOR DAY CREDIT WILL BE GIVEN.</td>
                </tr>
                <tr>
                  <td>Plea</td>
                  <td>GUILTY</td>
                  </tr>
                <tr>
                  <td>Case Number</td>
                  <td>2XX-1234-XX-1234</td>
                  </tr>
                <tr>
                  <td>Close Date</td>
                  <td>2006-01-01</td>
                </tr>
                <tr>
                  <td>Citation Number</td>
                  <td>11111111</td>
                </tr>
                <tr>
                  <td>Defendant Appeared</td>
                  <td>YES</td>
                </tr>
                <tr>
                  <td>Current Status</td>
                  <td>ORDER VIOLATING PROBATION</td>
                </tr>
                <tr>
                  <td>Party Type</td>
                  <td>DEFENDANT</td>
                </tr>
                <tr>
                  <td>Title</td>
                  <td>STATE OF NORTH DAKOTA VS DOE JOHN N</td>
                </tr>
                <tr>
                  <td>Mandatory Court Appearance</td>
                  <td>Y</td>
                </tr>
             </table>


            <hr  />

            <h2>Offender Info - Record 2</h2>
            
              <table border="0" cellpadding="5" cellspacing="0">
                <tr>
                  <td><strong>Full Name</strong><br />
                  DOE, JOHN N </td>
                  <td><strong>DOB</strong><br />
                  01-01-1900 </td>
                  <td colspan="2"><strong>ID</strong><br />
                  XX-1234-XX-1234 </td>
                </tr>
                <tr>
                  <td><strong>Address</strong><br />
                    4800 HWY 480N
                  </td>
                  <td><strong>City</strong><br />
                    Hometown
                  </td>
                  <td width="67"><strong>State</strong><br />
                    NC 
                  </td>
                  <td width="84"><strong>Postal Code</strong><br />
                    28000
                  </td>
                </tr>
                <tr>
                  <td><strong>Gender</strong><br />
                    Male
                  </td>
                  <td><strong>Race</strong><br />
                    W
                  </td>
                  <td><strong>County</strong><br />
                  --- </td>
                  <td><strong>Country</strong><br />
                  --- </td>
                </tr>
                <tr>
                  <td><strong>Height</strong><br />
                    6 - 0 
                  </td>
                  <td><strong>Weight</strong><br />
                    185 
                  </td>
                  <td><strong>Eye Color</strong><br />
                    Brown
                  </td>
                  <td><strong>Hair Color</strong><br />
                    Brown
                  </td>
                </tr>
                <tr>
                  <td><strong>Record Source</strong><br />
                  New Jersey Dept of Corrections </td>
                  <td><strong>Record State</strong><br />
                  NJ </td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
              </table>

            <h3>Aliases</h3>
            
            <p>DOE, JOHNNY; DOE, JOHN E</p>

            <h3>Offenses</h3>

              <p><strong>Result #1</strong></p>
              
              <table border="0" cellpadding="5" cellspacing="0">
                <tr>
                  <td>Description</td>
                  <td>ENDANGERING A VULNERABLE ADULT</td>
                </tr>
                <tr>
                  <td>Degree Of Offense</td>
                  <td>FELONY CLASS B</td>
                </tr>
                <tr>
                  <td>Offense Date</td>
                  <td>1996-10-04 </td>
                </tr>
                <tr>
                  <td>Sentence Date </td>
                  <td>1998-05-01 </td>
                </tr>
                <tr>
                  <td>Probation </td>
                  <td>CREDIT FOR TIME SERVED </td>
                </tr>
                <tr>
                  <td>Plea </td>
                  <td>GUILTY </td>
                </tr>
                <tr>
                  <td>Case Number </td>
                  <td>XX-1234-XX-1234 </td>
                </tr>
                <tr>
                  <td>Close Date </td>
                  <td>2001-01-01 </td>
                </tr>
                <tr>
                  <td>Sentence Maximum </td>
                  <td>5 YEARS</td>
                </tr>
                <tr>
                  <td>Inmate Number </td>
                  <td>000xxxxxxB </td>
                </tr>
                <tr>
                  <td>Institution </td>
                  <td>PAROLED FROM BAYSIDE STATE PRISON </td>
                </tr>
                <tr>
                  <td>Institution County </td>
                  <td>MONMOUTH </td>
                </tr>
                <tr>
                  <td>Admission Date </td>
                  <td>1998-05-14 </td>
                </tr>
                <tr>
                  <td>Release Date </td>
                  <td>1999-06-01</td>
                </tr>
                <tr>
                  <td>Current Status </td>
                  <td>PAROLE </td>
                </tr>
                <tr>
                  <td>Party Type </td>
                  <td>INMATE </td>
                </tr>
                <tr>
                  <td>Title </td>
                  <td>STATE OF NEW JERSEY VS DOE JOHN N </td>
                </tr>
              </table>


          <hr />

        <p align="center"><a href="landing.html"><img src="images/common/bt-back.png" alt="back" width="149" height="60" /></a></p>

    <h3>DISCLAIMER</h3>

          <p>Search Systems provides no warranty of any type as to the accuracy of this  information, and any reliance on this information is solely at your own risk and  responsibility. All information retrieved from or through SearchSystems.net  must be utilized in accordance with the User Agreement and all applicable state  and federal laws.</p>

</jsp:body>

</neon:funnelPage>