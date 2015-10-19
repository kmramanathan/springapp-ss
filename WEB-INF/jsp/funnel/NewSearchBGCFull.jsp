<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:CrimeFunnel title="Enter Search" >
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css</jsp:attribute>

<jsp:body>

<script type="text/javascript">
	function shownote(a, b, c){
		document.getElementById(a).style.display = "block";
		document.getElementById(b).style.display = "none";
		document.getElementById(c).style.display = "none";
	}

	function getstat(){
		var stat = document.crimeForm.bgcState.value;
		if(stat == "all"){
			document.crimeForm.bgcMatchMissingDates.checked = false;
		}
		else if(stat == "DE"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Delaware_Criminal_Records/";
		}
		else if(stat == "MA"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Massachusetts_Criminal_Records/";
		}else if(stat == "SD"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/South_Dakota_Criminal_Records/";
		}else if(stat == "WY"){
			window.location.href= "http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Criminal_Records/Wyoming_Criminal_Records/";
		}else{
			document.crimeForm.bgcMatchMissingDates.checked = false;
		}
	}
</script>
<style type="text/css">
.formfield1{ height:23px; }
.style999{ font-size:11px;}
.style59 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
	font-weight:bold;
	}
	.style71 {font-family: Arial, Helvetica, sans-serif; color: #003366; font-size:15px;}
.style61 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; }
.style72 {color: #FF0000}

.style74 {color: #000000}
.style75 {font-size: 14px}
.style76 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14;
}
.style77 {font-size: 14;}
.style81 {color:#003366}
.style82 {
	color: #009900;
	font-weight: bold;
}
.style83 {color: #0000FF}
.style85 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }
.style86 {font-size: 12px}
	
</style>

<tr><td >
<div id="formwrapper">
<table  border="0" cellpadding="0" cellspacing="0" width="820">
<tr valign="top">
<td>
<h2 class="style59">Criminal Records Search</h2>
              <p class="style71 style81" style="color: #003366; font-size:15px;">Instantly search over 400 million criminal records from thousands of jurisdictions, updated weekly. Includes over 20 terrorist and international databases. Search by state or nationwide, using partial names,  date of birth OR approximate age, and get results that don't have a date of birth or only a partial date.</p>
              <p class="style59"><span class="style71">Please fill out the required fields below. Please DO NOT include suffixes such
as Jr., Sr., III, M.D., and PhD in the first or last name fields.

</span></p>
   
  <p class="style59"><span class="style71">If you'd like to include a middle initial, see "Additional Options" below.</span></p>
   </td>
   <td> <img src="/springapp/images/newfunnel/image-male-searching.jpg" alt="image male searching" width="258" height="239"/></td>
</tr>    

<form:form method="post" name="crimeForm">
<form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top">
<td >
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr valign="top">
<td>
        

<b style="color: #003366;">Basic Options</b></td> <td align="right"><div align="right"><span class="style61" ><span class="style72">*</span>Required Fields</span></div></td>

</tr>
<tr>
<td colspan="2">
<ul >
<li>
<label for="bgc_first_name"><span class="req"><strong>*</strong></span> First Name:</label> 
<form:input path="bgcFirstName" cssClass="formfield" size="20" maxlength="20" />
<form:checkbox path="bgcFirstNameExact" cssClass="formfield" /> Exact Match
</li>            
            <li>
<label for="bgc_last_name"><span class="req"><strong>*</strong></span> Last Name:</label> 
<form:input path="bgcLastName" cssClass="formfield" size="20" maxlength="20" />
<form:checkbox path="bgcLastNameExact" cssClass="formfield" /> Exact Match
</li>

<c:if test="${!command.nationwideSearch}">
<li >
<label for="bgc_state"><span class="req"><strong>*</strong></span> Search Area:</label>
<form:select path="bgcState" size="1"  cssClass="formfield1" onchange="getstat();">
<form:option value="all">Nationwide</form:option>
<form:options items="${usStates}"/>
</form:select>

</li>
</c:if>
<li><p align="center" style="padding:0; margin:0; color:#000000; "><strong>Price:</strong> $14.95 Nationwide or $6.95 Statewide</p></li>
<li><b>Date of Birth Options</b></li>
<li><i>Search by date of birth OR year of birth (choose one):</i></li>

            <li>
<label for="bgc_dob"><span class="req"><strong>*</strong></span> Date of Birth:</label>

<form:radiobutton path="bgcDobRange" value="false" />
<form:select path="bgcDobMonth" cssClass="formfield1">
<form:option value="0">Month</form:option>
<form:options items="${dobMonths}"/>
</form:select>&nbsp;
<form:select path="bgcDobDay" cssClass="formfield1">
<form:option value="0">Day</form:option>
<form:options items="${dobDays}"/>
</form:select>&nbsp; 
<form:select path="bgcDobYear" cssClass="formfield1">
<form:option value="0">Year</form:option>
<form:options items="${dobYears}"/>
</form:select>
            </li>

            <li>
<label for="bgc_dob"><span class="req"><strong>*</strong></span> Year of Birth:</label>
<form:radiobutton path="bgcDobRange" value="true" />
<form:select path="bgcDobRangeBaseYear" cssClass="formfield1">
<form:option value="0">Year</form:option>
<form:options items="${dobYears}"/>
</form:select>
plus or minus
<form:select path="bgcDobRangeFuzz" cssClass="formfield1">
<form:option value="0">0</form:option>
<form:option value="1">1</form:option>
<form:option value="2">2</form:option>
<form:option value="3">3</form:option>
</form:select>
years
            </li>

<li><b>Additional Options</b></li>
<li>
<form:checkbox path="bgcMatchMissingDates" cssClass="formfield" /> Include results where DOB is missing or unavailable
<span class="style61"><a href="#" onclick="shownote('dob','ini','ref');return false;">what's this?</a></span>

</li>
<li style="height:80px;"><span style="color:red; font-size: 12pt;">If you check this box, the results will include additional records that MAY or MAY NOT be for your person.
 These additional records do not include birth dates. CAUTION: If you check this box you may get too many results for a common name. Your limit is 500.

</span></li>
<li>
<label for="bgcMiddleInitial">Middle Initial:</label> 
<form:input path="bgcMiddleInitial" cssClass="formfield" size="2" maxlength="1" />
(optional)<span class="style61"><a href="#" onclick="shownote('ini','dob','ref');return false;">what's this?</a></span>
</li>
<li>
<label for="bgcReferenceCode">Reference Code:</label> 
<form:input path="bgcReferenceCode" cssClass="formfield" size="20" maxlength="20" />
(optional)<span class="style61"><a href="#" onclick="shownote('ref','ini','dob');return false;">what's this?</a></span>
</li>
<li><b>Purpose of your search</b></li>
<li>
	<div><span class="req"><strong>*</strong></span>
	<form:select path="bgcPurpose" cssClass="formfield1">
		<form:option value="">[Choose]</form:option>
		<form:options items="${bgcSearchPurposes}"/>		
	</form:select>
	</div>
</li>

        </ul>
        </td>
        </tr>
        <tr>
        <td colspan="2">


    <p><input class="go" type="submit"  tabindex="3" value="Proceed" style="cursor:pointer; margin:0; padding:0; margin-left:180px;"/></p>
    <p class="style59" style="padding:4px 0 0 0; margin:0;"><span class="style71"> Did you remember to check our <a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do">COVERAGE PAGE</a> to see
     which jurisdictions, types of records, and date ranges are provided in each State?
</span></p>
</td>
</tr>
</table>
</td>

        <td valign="bottom">
        <p align="center"><a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do">Database Coverage</a></p>
     <p align="center"><a href="https://www.searchsystems.net/springapp/funnel/BestResultsGuide.html;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C8603">Guide to Best Results</a></p>
<div style=" margin-top:150px;">
<div style="width:200px;float: right;display: none;" id="dob">
            <p class="style72 style61"><strong style="color: red;">Include Missing DOB</strong></p>
            <p class="style61 style72" style="color: red;">Some jurisdictions only provide a partial date of birth and other jurisdictions don't provide a date of birth at all.  CAUTION: If you checked this box you may get too many results for a common name.  Your limit is 500.</p>
</div> 
<div  style="width:200px;float: right;display: none;" id="ini"> 
            <p class="style72 style61"><strong style="color: red;">Middle Initial</strong></p>
            <p class="style61 style72" style="color: red;">Search results will include records where there   is a match on the middle   initial, or the middle name starts with the letter you enter. Records   that don't   have a middle name or initial will also be included in your results.</p>
</div>
<div style="width:200px;float: right;display: none;" id="ref">
            <p class="style72 style61"><strong style="color: red;">Reference Code</strong></p>
            <p class="style61 style72" style="color: red;">You can enter an optional reference code for   your own tracking   purposes. The code will appear on your credit card billing statement or monthly invoice.   (NOTE:   Reference code may not be available for some credit cards.)</p>
</div>          
</div>

</td>

</tr>



    </form:form>
    <tr>
<td  colspan="2">


<p style="padding:0; margin:0;"><span class="style85"><b>Results may include traffic violations, in addition to criminal   felony, misdemeanor, and sex offender records.</b></span></p>
</td>
</tr>
<tr>
<td  colspan="2">

    <p class="style999" style="color:#000000;">
All information retrieved from or through SearchSystems.net must be utilized 
in accordance with the <a href="${showAgreementUrl}?version=funnel" target="_blank">User Agreement</a> and all applicable state and federal laws, 
including the <a href="http://www.ftc.gov/os/statutes/fcra.htm" target="_blank">Fair Credit Reporting Act</a>; 
any violation of these will be grounds for immediate termination of your account without notice. 
</p>
</td>
</tr>
<tr>
<td  colspan="2">
    <p class="style999" style="color:#000000;" >Please see our <a href="${showAgreementUrl}?version=disclaimer" target="_blank">disclaimer</a> and also our <a href="${showAgreementUrl}?version=disclaimer" target="_blank">Notice to California Employers or Employers screening California residents</a>.</p>
 </td>
 </tr>
</table>
</div>

</td></tr>

</jsp:body>

</neon:CrimeFunnel>