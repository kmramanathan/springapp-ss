<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<neon:PropertyFunnel title="Propert Search by Address" >
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
<style type="text/css">
#button2
{
	font-size:14px;
}
.formfield{
	height:25px;
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
	font-size: 22px;
	padding: 0;
	margin: 0;
}
.style712{font-family: Arial, Helvetica, sans-serif;color:black!important;padding:0!important;margin:0!important;}
.style71 {font-family: Arial, Helvetica, sans-serif;color:black!important;padding:0!important;margin:0!important;}
.style71 label{font-weight: bold;}
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
.style90 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FF0000; }
.style91 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #FF0000; }
.style818{color:#003366!important;margin:0!important;padding:0 0 10px 9px!important;}
.style819{padding: 0 0 3px 0;margin:0;}
#datediv{display:none;color:red;position:absolute;width:250px;text-align:justify;font-size: 12px;margin-left:250px;margin-top:7px}
#refdiv{display:none;color:red;position:absolute;width:250px;text-align:justify;font-size: 12px;margin-left:35px;margin-top:-30px;}
@-moz-document url-prefix() { 
  #refdiv {
     margin-left:450px;
  }
}
@media screen and (-webkit-min-device-pixel-ratio:0) {
    #refdiv{ margin-left:445px;}

</style>
  <form:form name="crimeForm" method="post">
    <form:errors path="*" element="div" id="error" cssClass="error-box" />
<tr valign="top"><td >
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td><h1 class="style59">
		Real Property Address Search</h1></td>
		<td><p class="style818 style71" ><strong>Search Property Address records and get instant results for just $5.</strong></p></td>
	</tr>
</table>
<table width="88%" border="0" cellpadding="0" cellspacing="0" align="center">
          <tr valign="top">
            <td width="100%" >
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr valign="top">
                    <td width="100%">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="182" bgcolor="#FFFF99"><p class="style71" style="margin:0;padding:0 0 0 8px">
                        <strong>Search by Property Address</strong></p></td>
                        <td width="20" bgcolor="#FFFFFF">&nbsp;</td>
                        <td width="223" bgcolor="#B4DDFE" class="style71"><div align="left">
                        <a href="/springapp/funnel/realpropSearch.do"><strong>Search by Name</strong></a></div>
                        </td>
                      </tr>
                    </table>
                      <table width="100%" bgcolor="FFFF99" border="0" cellpadding="0" cellspacing="0">                       
                       <tr>
                          <td><span class="style71"><label>House Number<strong class="style72">*</strong>:</label></span></td>
                          	<td>                            
                          		  <form:input path="appartnum" cssClass="formfield" size="20" maxlength="20" />
                            </td>
                            <td>
                          	<span class="style71">
                            	<label>Street<strong class="style72">*</strong>:</label>
                            </span>
                            </td>
                            <td>
                              <form:input path="street" cssClass="formfield" size="20" maxlength="20" />
                          </td>
                        </tr>
                        
                        <tr>
                          <td><span class="style71"><label>City<strong class="style72">*</strong>:</label></span></td>
                          	<td>                            
                          		  <form:input path="city" cssClass="formfield" size="20" maxlength="20" />
                            </td>
                            <td>
                          	<span class="style71">
                            	<label>State<strong class="style72">*</strong>:</label>
                            </span>
                            </td>
                            <td>
                            	<form:select path="state" size="1"  cssClass="formfield1">
                            	<form:options items="${usStates}"/>
                            	</form:select>
                          </td>
                        </tr>
                      <!--   <tr>
                        	<td width="130">&nbsp;</td><td><span class="style15">(Don't include suffixes like Inc., LLC) </span></td>
                        	<td colspan="2">&nbsp;</td>
                        </tr>
                        
                        <tr>
                        	<td>&nbsp;</td>
                        	<td colspan="3">
                        		<span class="style15">Searching a common name? Narrow your search <a href="#" onclick="showCity();" >here</a>.</span>
                        	</td>
                        </tr>
                       -->
                       <tr>
                          <td colspan="3" align="center">
                          <div align="center">
                          	<span class="style71">
                            <label>Your Reference:</label></span>
                            <form:input path="reference" cssClass="formfield" size="20" maxlength="20" />
                            <span class="style61">(optional)</span>
                            <span class="style61"><img src="/springapp/img/Help.jpg" border="0" width="18" height="23" id="refbtn"></span>
								<div id="refdiv" align="right">You can enter an optional reference code for your own tracking purposes. The code will appear on your credit card billing statement or monthly invoice. (NOTE: Reference code may not be available for some credit cards.)
								</div>
                           </div>     
                         </td>
                                                  
                            <td></td>
                        </tr>
                        <tr>
                          <td class="style71">
                          <p class="style71">&nbsp;</p>
                          	  <span style="color:red;font-size:11px;float: right"><b>*Required Fields</b></span>
                       	 </td>
                          <td width="227">
                          		<p class="style712 style819">
									<form:radiobutton path="newacc" value="true"/>
									<strong>I am a new customer</strong></p>
                                	<p class="style712 style819">
                   					<form:radiobutton path="newacc" value="false"/>
									<strong>I have an account</strong>
                                	</p>
                          </td>
                          <td colspan="2">
                          	<input type="submit" name="button2" id="button2" value="Proceed">
                          </td>
                        </tr>
                      </table>
                    
                      <p class="style61" style="color:black;margin:0;padding:7px 0 0 12px;font-size:10px;line-height: 200%;"><span class="style152">Our evictions database contains information from    publicly available sources. We do not  make any   representation or warranty as to the character or integrity of the   person  that is the subject of your search  through our service. Information retrieved from a Personal Search  should not be used for  any FCRA related business, employment, or tenant screening purpose. All information retrieved from or through SearchSystems.net must be   utilized   in accordance with the <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do?version=funnel" target="_blank">User Agreement</a> and all applicable state and federal   laws. Please see our <a href="https://www.searchsystems.net/springapp/legal/showAgreement.do;jsessionid=3C4ACCF3A24B5DCBC8F329AF65C86039?version=disclaimer" target="_blank">disclaimer</a>.</span></p>
                    </td>
                  </tr>
                </table>
             
            
              </td>
			
          </tr>
        </table>	

</td></tr>
</form:form>
 <script>
					  $(document).ready(function(){
							$("#datediv").hide();
							$("#datebtn").show();
							$("#datebtn").click(function () {
							$("#datediv").slideToggle();
							});
						});
						$(document).ready(function(){
							$("#refdiv").hide();
							$("#refbtn").show();
							$("#refbtn").click(function () {
							$("#refdiv").slideToggle();
							});
						});


					  </script>
</jsp:body>
</neon:PropertyFunnel>