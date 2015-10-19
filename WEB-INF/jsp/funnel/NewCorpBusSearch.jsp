<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:NewCorpFunnel title="Corporations by Business Name" >
<jsp:attribute name="stylesheet">funnel.css,new-funnel.css,style_02.css</jsp:attribute>
<jsp:body>
<script type="text/javascript">

	function showCity()
	{
		document.getElementById("cityShow").style.display="block";
		document.getElementById("cityShow1").style.display="block";
	}
</script>
<script type="text/javascript" src="/springapp/js/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#ind").click(function()
		{
			$('input:radio[name=corporation]:nth(1)').attr('checked',true);
			window.location.replace("corp-ind-search.do");
		});
	$("#bus").click(function()
		{
			$('input:radio[name=corporation]:nth(0)').attr('checked',true);
			window.location.replace("corp-bus-search.do");
		});
	
});

</script>

<style type="text/css">
#button2
{
	font-size:14px;
}
.formfield
{
	height: 24px;
}
.formfield1
{
	height:23px;
}
.formfield1 option
{
	margin:0;padding:0;
}
	
.style59 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
	margin: 0;
	padding: 0;
}
.style721
	{
	font-family: Arial, Helvetica, sans-serif;color:black!important;paddibg:0!important;margin:0!important;
	font-size: 14px;
	}
.style71 {font-family: Arial, Helvetica, sans-serif;color:black!important;paddibg:0!important;margin:0!important;
}
 .style71 label{ font-size: 13px!important;font-weight: bold}
.style61 {font-family: Arial, Helvetica, sans-serif; font-size: 12px;color:#000;paddibg:0!important;margin:0!important;}
.style72 {color: #FF0000!important}
.style75 {font-size: 14px}
.style76 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14;
}
.style15 {font-size: 0.8em}
.style77 {font-size: 14}
.style81 {color: #003366}
.style83 {
	font-family: Arial, Helvetica, sans-serif;
	color: #006633;
	font-weight: bold;
}
.style86 {font-size: 12px}
.style88 {color: #000000}
.style90 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #000000; }
.style91 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FF0000; }
.style818{color:#003366!important;margin:0!important;padding:10px 0 10px 9px!important;}

.style819{margin:0!important;padding:0!important;font-size: 12px; color: #000!important;}
.style106 {color: #333333}
.style107 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #333333; }
.style592 {font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #003366;
}
p{margin:0;padding: 0;}
.tablechart{border:1px!important;color:#000;}
.tablechart td{border:1px solid #000!important;color:#000;}
.corptable{border: 3px inset gray;}
#refdiv{display:none;color:red;position:absolute;width:250px;text-align:justify;font-size: 12px;margin-left:-30px}
</style>
  <form:form name="crimeForm" method="post">
    <form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top"><td >
  <table border="0" width="100%" cellpadding="0" cellspacing="0">
            	<tr>
            		<td valign="top">
            			<h1 class="style59">Corporations Nationwide or Statewide</h1>
            		</td>
             		</tr>
             		<tr>
             		<td>
             			<p class="style818 style71" style="color:green!important;font-size:18px;font-style:italic"><strong>When a free search isn't enough</strong></p>
             		</td>
             		</tr>
			  </table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
          <tr valign="top">
            <td width="100%" >
          
			  
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr valign="top">
                    <td width="100%">
                     <table width="100%" bgcolor="#B4DDFE">
			   		<tr>
			   			<td><span class="style61"><strong class="style72">*</strong><span class="style91"> Required Fields</span></span></td>
			   			<td><div align="right"><strong>$7</strong> Nationwide or <strong>$4</strong> Statewide</div></td>
			   		</tr>
			   		</table>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#B4DDFE">
                      <tr>
                        <td><strong>Search by: </strong>
                       		<input type="radio" id="bus" name="corpoaration" value="bus" checked="checked"/> Business Name
                        	<input type="radio" id="ind" name="corpoaration" value="ind"/>Agent, Officer, or Director Name
                       </td>
                      </tr>
                    </table>
                      <table width="100%" bgcolor="#B4DDFE" border="0" cellpadding="0" cellspacing="0">
                       <tr>
                          <td colspan="2"><span class="style71"><label>Business Name<strong class="style72">*</strong></label>
                            </span></td>
                            
                            
                           
                            <td>&nbsp;</td>
                            
                            <td><span class="style71">
                            <label>Search Area<strong class="style72">*</strong></label></span></td>
                           
                      </tr>
                      <tr>
                      	<td colspan="2">
                            <form:input path="businessname" cssClass="formfield" size="26" maxlength="20" /> <span class="style90">(Don't include suffixes like INC, LLC, LLP, LP)</span>
                            </td>
                            
                            <td> &nbsp; </td>
                             <td>
                            <form:select path="state" size="1"  cssClass="formfield1" onchange="getstat();">
                           <form:option value="">Nationwide</form:option>
							<form:options items="${usStates}"/>
                            </form:select>
                            </td>
                      </tr>
                  		<tr>
                          <td valign="bottom" style="color:red;font-size:11px">
                                                    
                            </td>
                            <td valign="top">
                            		<div style="margin-left: 120px;float: left;width: 220px;">
                            			<p class="style71 style819">
									<form:radiobutton path="newacc" value="true"/>
									<strong>I am a new customer</strong></p>
                                	<p class="style71 style819">
                   					<form:radiobutton path="newacc" value="false"/>
									<strong>I have an account</strong>
                                	</p>
                                	</div>
                                	<div style="float: left;margin-top: 10px">
                                		<input type="image" src="/springapp/img/bt-green-srch.jpg" name="button2" id="button2" value="Proceed">
                                	</div>
                                	</td>
                            <td valign="top">
                            
                              
                              
                            </td>
                            <td align="left">
                          <div align="left">
                          <span class="style71">
                            <label>Your Reference:</label></span><br>
                            <form:input path="reference" cssClass="formfield" size="20" maxlength="20" /> <span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="18" height="23" id="refbtn"></span>
                            <div id="refdiv" align="right">You can enter an optional reference code for your own tracking purposes. The code will appear on your credit card billing statement or monthly invoice. (NOTE: Reference code may not be available for some credit cards.)</div>
                            
                            </div>
                            </td>
                           
                        </tr>
                        <tr>
                        	<td colspan="4">&nbsp;</td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
             
            
              </td>
            
          </tr>
        </table>
        <table width="800" border="0" align="center">
                  <tr>
                    <td><p><strong>Search for Corporations, Limited Liability Companies, Limited Partnerships, and more.</strong></p>
                      <p><strong>Advantages over<a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Corporations_and_Companies/">Online State Registries</a>:</strong></p>
                     <ul style="margin-left:25px;">
                        <li>Search <strong>nationwide</strong> by business  or individual name</li>
                        <li>Search <strong>ALL</strong> states by individual name to find Agents, Officers, and Directors</li>
                        <li>Find records for previous company names (e.g. in California)</li></ul>
                        <div align="center">
                        <table width="740" border="1" class="corptable">
                          <tr>
                            <th width="730" height="138" scope="col"><div align="left">
                                <ol>
                                  <li>Input the business name only. Leave out suffixes  like LLC, INC, LLP, LP.</li>
                                  <li>Search by the simplest, most accurate name. If you are looking for &quot;Grand Design Fortress Mattress Company,  LLC,&quot; we recommend that you  input &quot;Grand Design&quot; or &quot;Grand Design Fortress.&quot;</li>
                                  <li>Searching a single word is not  advisable,  as it will give you  too many results.</li>
                                  <li>Be aware that there is a limit of 250 results. The closest matching  results will display first.</li>
                                  <li>Once you find the record you want,  check<a href="http://publicrecords.searchsystems.net/Free_Public_Records_by_Type_of_Record/Corporations_and_Companies/">State records</a>for more information.</li>
                                </ol>
                              </div></th>
                          </tr>
                        </table>
                        <p align="left"><span class="style107"><span class="style106">This  search provides records acquired from Secretary of States and may include Entity  Type, Status, Filing Date, Filing State, Purpose, names  &amp; addresses of officers and registered agents, stock information, and  history. This information is intended as a resource to help you find business records you need, but should not be considered a replacement for official State Records. </span>All information retrieved from or through SearchSystems.net must be   utilized   in accordance with the<a href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=funnel" target="_blank">User Agreement</a>and all applicable state and federal   laws,   including the<a href="http://www.ftc.gov/os/statutes/fcra.htm" target="_blank">Fair Credit Reporting Act</a>;   any violation of these will be grounds for immediate termination of your   account without notice. Please see our<a href="https://www.searchsystems.net/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">Disclaimer</a>, <a href="http://publicrecords.searchsystems.net/privacypolicy.php">Privacy Policy</a></span></p>
                      </div>
                     
                    </td>
                  </tr>
                </table>
                <script>
					  
						$(document).ready(function(){
							$("#refdiv").hide();
							$("#refbtn").show();
							$("#refbtn").click(function () {
							$("#refdiv").slideToggle();
							});
						});


					  </script>	

</td></tr>
</form:form>
</jsp:body>
</neon:NewCorpFunnel>