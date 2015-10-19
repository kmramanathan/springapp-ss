<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Hashtable"%>
<%@page import="springapp.web.findpeople.xmlParser"%>
<%@page import="springapp.web.findpeople.BGxmlManager"%>
<%@page import="springapp.web.findpeople.BGxmlManager.*"%>
<% 
boolean member=false;
String username=(String)session.getAttribute("username");
if(username != null){
	member=true;
}

 String key = (String) session.getAttribute("key");
 Hashtable BGResult = (Hashtable) session.getAttribute("BGResult");
 
 if(BGResult == null) {
	response.sendRedirect("/springapp/findpeople/searchLanding.do");	 
 }
 
 else
 {
	 String bgColor = "";
 BGxmlManager bgResult = (BGxmlManager) BGResult.get("BGResult"); 
  
 ArrayList BgcOffenders = (ArrayList)session.getAttribute("BgcOffenders");
 HashMap aliasesMap = (HashMap) session.getAttribute("BgcAliasesMap");
 HashMap offensesMap = (HashMap) session.getAttribute("BgcOffenses");
 
 
%>


<!DOCTYPE HTML PUBLIC "-//W3 C//DTD HTML 4.01 Transitional//EN">

<%@page import="springapp.web.findpeople.xmlParser.fpDetail"%>
<%@page import="net.searchsystems.limestone.bean.BGCOffenderBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.searchsystems.limestone.bean.BGCOffenseBean"%>
<%@page import="net.searchsystems.limestone.bean.BGCAliasBean"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>background report details</title>
<link type="text/css" rel="stylesheet" href="/springapp/css/findpeople_02.css">
<style type="text/css">
<!--

.menu {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #ffffff;
	float: right;
	width: 490px;
	text-align: right;
	margin: 25px 25px 0 0;
}
.menu a {
	color: #ffffff;
	text-decoration: underline;
}
.style24 {color: #FF0000}
.style27 {font-family: Arial, Helvetica, sans-serif}
.style30 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-style: italic;
}
.style31 {font-size: x-small}
.style23 {font-size: small}
.style32 {font-family: Arial, Helvetica, sans-serif; color: #003366; }
.style41 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-weight: bold;
}
.style42 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
.style44 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: small;
	font-weight: bold;
}
.style45 {font-size: small; font-family: Arial, Helvetica, sans-serif; }
.style47 {font-size: small; font-weight: bold; }
.style65 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }
.style66 {font-family: Arial, Helvetica, sans-serif; color: #003366; font-weight: bold; }
.style70 {font-family: Arial, Helvetica, sans-serif; font-size: small; font-style: italic; }
.style47 {font-size: small; font-weight: bold; }
.style71 {
	font-size: 14px
}
.style72 {font-family: Arial, Helvetica, sans-serif; font-size: 14px; font-weight: bold; }
-->
</style>
 
<% if(bgResult.getProRpt() != null) 
	{
		if(!bgResult.getProRpt().getAddress().equals("")){ %>
<script type="text/javascript" src="https://ecn.dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=6.2&s=1"></script>
      <script type="text/javascript">
      var map = null;
            
      function GetMap()
      {
           
         map = new VEMap('myMap');
         map.LoadMap();

        var FindAddress = "<%=bgResult.getProRpt().getAddress()%>','<%=bgResult.getProRpt().getCity()%>','<%=bgResult.getProRpt().getState()%>" ;
         map.Find(null,FindAddress,  null, null, null, null, false, false, true, true,findAddressCallBack);
         //AddPushpin();
      }
      
      // Call back function for VEMap.Find()

function findAddressCallBack(thelayer, resultsArray, places, hasMore, veErrorMessage)
{
	if(places != null && places.length >0)
	{
		var latitude = places[0].LatLong.Latitude;
		var longitude = places[0].LatLong.Longitude;
		var pin = new VEShape(VEShapeType.Pushpin, places[0].LatLong);
		
		pin.SetTitle('<%=bgResult.getProRpt().getLastName()%>');
        pin.SetDescription('<%=bgResult.getProRpt().getAddress()%>, <%=bgResult.getProRpt().getCity()%>, <%=bgResult.getProRpt().getState()%>');
        pin.SetCustomIcon("/springapp/images/findpeople/mapuser.png");
  		map.AddShape(pin);
	}
} 
     
      function Find()
      {
         try
         {
            map.Find(txtWhat.value, txtWhere.value);
         }
         catch(e)
         {
            alert(e.message);
         }
      }
      </script>
<% }  } %>

<script language="javascript" type="text/javascript">
function validateField(type)
{
	var checked = false;
     if(type=='all'){
    	document.BGCresult.action="bgcResults.do?printType=all";
		document.BGCresult.submit();
	 }
	 else{
		 for(var i=0; i<document.BGCresult.printChecked.length; i++)
		 {
			 if(document.BGCresult.printChecked[i].checked){
				 checked = true;
				 break;
			 }
			 
		 }
		 
		 if(document.BGCresult.printChecked.checked){
			 checked = true;
		 }
		 
	 	if(checked){
			document.BGCresult.action="bgcResults.do?printType=checked";
			document.BGCresult.submit();   
		}
		else{
			document.getElementById('error').style.visibility = "visible";
			return false;
			}
	 }
}
</script>

</head>
<body onLoad="GetMap()">
<table width="893" height="191%" align="center" style="height:100%">
	<tr>
		<% if(!member) { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="/springapp/flatrate/FPsearchLanding.do">Find People Flat Rate</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
    	<% } else { %>
    	<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="/springapp/flatrate/FPsearchLanding.do">Find People Flat Rate</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a> | Welcome <%=username%> [<a href="/springapp/findpeople/logout.do">Log Out</a>] </div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
   	 <% } %>
	</tr>
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;"><table width="893" height="714" border="0">
          <tr>
            <td width="896" height="710" valign="top">
			
			<% if(!member) {  %>
			<table width="893" height="63" border="0" bgcolor="#fffeb1">
              <tr>
                  <td><div align="left"><a href="https://www.searchsystems.net/springapp/funnel/WholePicture.html"><img src="/springapp/images/findpeople/ban-access-more.png" alt="access more" border="0"></a></div></td>
              </tr>
              </table>
			 <%  }  %>
			  
			  
                <table width="893" height="109" border="0">
                  <tr>
                    <td width="503" height="105"><table width="420" align="left">
                      <tr>
                        <td><h1 class="style32">Background Report Details</h1>
						<p align="left" class="style66">As of <script language="JavaScript" src="/springapp/js/date.js"></script>
																			  </p>
						
						</td>
                        
                      </tr>
                    </table>                    </td>
                    <td width="432"><div align="right">
                     <form:form method="post" action="/springapp/findpeople/searchLanding.do">
                    <table width="351" border="0">
                      <tr>
                            <td width="124"><div align="left" class="style23 style27"><strong>First Name <span class="style24">*</span></strong></div></td>
                            <td width="100"><div align="left" class="style23 style27"><strong>Last Name <span class="style24">*</span></strong></div></td>
                            <td width="147"><div align="left" class="style23 style27"><strong>State</strong></div></td>
                          </tr>

                      <tr>
                        <td><div align="left">
                            <form:input path="searchFirstName" cssClass="formfield" size="16" />
                        </div></td>
                        <td><div align="left">
                            <form:input path="searchLastName" cssClass="formfield" size="18" />
                        </div></td>
                        <td><div align="left">
                            <form:select path="searchState" cssClass="formfield">
										<form:options items="${searchState}"/>		
							</form:select>
                        </div></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><div align="right"><input type="image" src="/springapp/images/findpeople/bt-search-again-xsmall.png"  onclick="document.forms[0].submit();" alt="search again" width="109" height="32" border="0"></div></td>
                      </tr>
                    </table>
                  </form:form>
                    </div></td>
                  </tr>
                </table>
                <p>&nbsp;</p>
                 <table width="891" border="0">

                <tr>
                  <td width="870" height="143"><p><span class="style70" style="padding-bottom: 10px"><strong>Important:</strong> This Background Report  contains information gathered from public records sources.  The  information is only as accurate as the agencies that  contribute it.</span></p>
                    <p>&nbsp;</p>
                    <p><span class="style70" style="padding-bottom: 10px">Address history and address information is matched by name and birth date (when available). Statewide criminal records are matched by name and birth date and we include results where the date of birth is unavailable. We also provide additional data (such as Business Search, Campaign Contributions, U.S. Domain Name Ownership, etc.) that isn't searchable by birth date. Therefore we provide you with <strong>all</strong> available records that match the name you searched. These records may or may not belong to your subject, so please review the information carefully.</span></p>
                  <p>&nbsp;</p></td>

                </tr>
              </table>
                
                
                <table width="893" border="0">
                  <tr>
                    <td height="316">
                    
                    <table width="502" height="125" cellpadding="0" cellspacing="0">
                      <col width="117" />
                        <col width="205" />
                        <col width="59" />
                        <col width="99" />
                        <col width="85" />
                        <col width="64" />
                        <% if(bgResult.getProRpt() != null){ %>
                        <tr height="51">
                          <td width="291" height="17" bgcolor="#FFFFFF"><div align="left" class="style27">
                            <div align="left"><span class="style47"><%=bgResult.getProRpt().getFirstName()%> <%=bgResult.getProRpt().getMiddleName()%> <%=bgResult.getProRpt().getLastName()%></span></div>
                          </div></td>
                      </tr>
                      
                        <tr height="17">
                          <td height="17" bgcolor="#FFFFFF"><div align="left" class="style44">
                            <div align="left"><%=bgResult.getProRpt().getAddress()%></div>
                          </div></td>
                        </tr>
                        <tr height="17">
                          <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style44"><%=bgResult.getProRpt().getCity()%> <%=bgResult.getProRpt().getState()%> <%=bgResult.getProRpt().getCounty()%></span></div></td>
                        </tr>
                        <tr height="17">
                          <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style44"><%=bgResult.getProRpt().getPhone()%></span></div></td>
                        </tr>
                        <tr height="17">
                          <td height="17" bgcolor="#FFFFFF"><div align="left"></div></td>
                        </tr>
                        <tr height="17">
                          <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style44">Validity Date:</span> <span class="style45"><strong><%=bgResult.getProRpt().getValidityDate()%></strong></span></div></td>
                        </tr>
                        <tr height="17">
                          <td height="17" bgcolor="#FFFFFF"><div align="left"><span class="style44">DOB:</span> <span class="style44"><%=bgResult.getProRpt().getDOB()%></span></div></td>
                        </tr>
                        <tr><td><br></td></tr>
                        <% } %>
                        <tr height="17">
                          <td>
                          		 <a href="#"><img src="/springapp/images/findpeople/printer-icon.jpg" alt="print"  border="0" onClick="window.print()"/></a>                          </td>
                          
                        </tr>
                    </table>
					<table width="501" height="159">
                        <tr>
                        <%
                             	 	ArrayList history= bgResult.getAddHisList();
                              		ArrayList OccupantList = bgResult.getOccupantList();
                              		ArrayList birthList = bgResult.getBirthList();
                              		ArrayList deathList = bgResult.getDeathList();
                              		ArrayList businessList= bgResult.getBusinessList();
                              		ArrayList campaignList= bgResult.getCampaignList();
                              		ArrayList USDomainList= bgResult.getUSDomainList();
                              		ArrayList fccList= bgResult.getFCCList();
                              		ArrayList fflList= bgResult.getFFLList();
                              		ArrayList weaponList= bgResult.getWeaponList();
                              		ArrayList DeaList= bgResult.getDEAList();
                              		ArrayList mess= bgResult.getMerVessList();
                              		ArrayList airList= bgResult.getAirSerList();
                              		ArrayList faaList= bgResult.getFAAList();
                              
                              %>
                              <p>&nbsp;</p>
                          <td width="256" height="153"><p class="style65">Summary:</p>
                              <p>&nbsp;</p>
                                      
                        	 <p class="style45">Address History (<%=history.size() %>)</p> 
                            <% 	int otherCount=0;
                            	for(int g=0; g<OccupantList.size(); g++)
    				 			{
    				 				OccupantData occObj = (OccupantData) OccupantList.get(g);
    				 				ArrayList tempOtherList= occObj.getOtherList();
    				 				if(!tempOtherList.isEmpty())
    				 				{
    				 					otherCount = otherCount + tempOtherList.size();
    				 				}
    				 			}
                            
                            %> <p class="style45">Address Information (<%=otherCount %>)</p>
                              <p class="style45">Statewide Criminal Records <% if(BgcOffenders != null) { %>(<%=BgcOffenders.size() %>) <% } else { %>(0)<% } %></p>
                            <p class="style45">Business Search (<%=businessList.size() %>)</p>
                             <p class="style45">Campaign Contributions (<%=campaignList.size() %>)</p>
                             <p class="style45">U.S. Domain Name Ownership (<%=USDomainList.size() %>)</p>
                            <p class="style45">FCC Licenses (<%=fccList.size() %>)</p></td>
                          <td width="233"><p>&nbsp;</p>
                              <p class="style27">&nbsp;</p>
                             <p class="style45">Firearm Dealer Licenses (<%=fflList.size() %>)</p>
                            <p class="style45">Concealed Weapons Permits (<%=weaponList.size() %>)</p>
                             <p class="style45">DEA Search (<%=DeaList.size() %>)</p>
                             <p class="style45">Merchant Vessels (<%=mess.size() %>)</p>
                           <p class="style45">Aircraft Search (<%=airList.size() %>)</p>
                             <p class="style45">FAA Pilot Licenses (<%=faaList.size() %>)</p>
                             
                             </td>
                        </tr>
                      </table>
                    <p>&nbsp;</p>
					</td>
                    <td>
                    	 <% if(bgResult.getProRpt() != null) {
                    		 if(!bgResult.getProRpt().getAddress().equals("") ) { %><div id="myMap" style="position:relative; width:378px; height:259px; border:3px solid black;padding:3px; background-color: #c22727;" align="center"></div>		<% } }%>		                      
                    	
                    </td>
                  </tr>
                </table>
                
<!-- Displaying address History --> 

				 
                <h4 align="left" class="style32"><strong>Address History</strong></h4>
				<%   
				 		if(history.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
 
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="117" />
                  <col width="205" />
                  <col width="59" />
                  <col width="99" />
                  <col width="85" />
                  <col width="64" />
               
				<% 
                		
						  for(int m=0 ; m<history.size(); m++)
						  { 
						  	  if((m%2) == 0) {	bgColor = "";  } else {	bgColor = "#fffeb1"; }	
							  addressHistory adhis=(addressHistory) history.get(m); 
				%>	
				 <tr height="18">
                    <td height="18"><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td>&nbsp;</td>
                  </tr>		  
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td width="320" height="17"><div align="left" class="style41"><%=adhis.getAddress() %></div></td>
                    <td width="85"><div align="left" class="style42">County:</div></td>
                    <td width="191"><div align="left" class="style42"><%=adhis.getCounty() %></div></td>
                    <td width="109"><div align="left" class="style42">Validity Date:</div></td>
                    <td width="164"><div align="left" class="style42"><%=adhis.getValidityDate() %></div></td>
                    <td width="22">&nbsp;</td>
                  </tr>
                  
                    <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="17"><div align="left" class="style42"><strong><%=adhis.getCity() %> <%=adhis.getState() %> <%=adhis.getZip() %></strong></div></td>
                    <td><div align="left" class="style42">Phone:</div></td>
                    <td><div align="left" class="style42"><%=adhis.getPhone() %></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td>&nbsp;</td>
                    
                  </tr>
                  
                  <% }  %>
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">

                  <tr>
                    <td height="17" width="640"><div align="center"></div></td>
                  </tr>
                </table>
                
                 
                
<!-- Displaying address Information-->
<table width="242" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="33" width="196"><h4 align="left" class="style32"><strong>Address    Information</strong></h4>                    </td>
                  </tr>
                </table>
				<%   
				 		if(OccupantList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="891">
                 <% 
                	for(int s=0; s<OccupantList.size(); s++)
				 			{
                				if((s%2) == 0) {	bgColor = "";  } else {	bgColor = "#fffeb1"; }	
				 				OccupantData occObj = (OccupantData) OccupantList.get(s);
				%>
                 <tr>
                    <td width="235">
                    <table width="236" bgcolor="<%=bgColor %>">
                      <tr>
                        <td width="228">&nbsp;</td>
                      </tr>
                      <tr height="17">
                        <td height="17"><div align="left" class="style42"><strong><%=occObj.getAddress() %></strong></div></td>
                      </tr>
                      <tr height="17">
                        <td height="17"><div align="left" class="style42"><strong><%=occObj.getCity() %> <%=occObj.getState() %> <%=occObj.getZip() %></strong></div></td>
                      </tr>
                      <tr height="17">
                        <td height="17"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      </tr>
                      <% String[] phArray = occObj.getListedPhones();

					  String phone="";
					  if(phArray != null)
					  {
						  for(int p=0; p<phArray.length; p++)
						  {
							  if(phArray[p]!=null) {
						  %>
							<tr height="17"><td height="17"><div align="left" class="style42"><%=phArray[p] %> </div></td> </tr>
						<% } 	  
						  }
					  } %>
                 
                    </table>
                      <p>&nbsp;</p>
                    <p>&nbsp;</p></td>
                    <td width="399">
                    
                    <% ArrayList otherList = occObj.getOtherList();
                    	if(!otherList.isEmpty())
                    	{
                    %>		
                    <table width="398" bgcolor="<%=bgColor %>">
                    <tr>
                        <td width="217"><span class="style42"><strong>Relatives/Roommates:</strong></span></td>
                        <td width="82">&nbsp;</td>
                        <td width="103">&nbsp;</td>
                      </tr>
                    
                    <% for(int h=0; h<otherList.size(); h++)
            		{
                    	
            			otherPeople otherObj = (otherPeople) otherList.get(h);    		
            	
            		%>
                      
                      <tr height="17">
                        <td><div align="left" class="style42"><%=otherObj.getFirstName() %> <%=otherObj.getMiddleName() %> <%=otherObj.getLastName() %></div></td>
                        <td><div align="left" class="style42">Validity Date:</div></td>
                        <td><div align="left" class="style42"><%=otherObj.getValidityDate() %></div></td>
                      </tr>
                      <tr height="17">
                        <td><div align="left" class="style42"><%=otherObj.getPossibleRelative() %></div></td>
                        <td><div align="left" class="style42">DOB:</div></td>
                        <td><div align="left" class="style42"><%=otherObj.getDOB()%></div></td>
                      </tr>
                      <tr height="17">
                        <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                        <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                        <td><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      </tr>
                      
					<% } %>
                    </table>
                    <% } %>
                    
                    </td>
                    <td width="241">
                    <% ArrayList cenList = occObj.getCensusList();
                    	if(!cenList.isEmpty())
                    	{
                    %>
                    
                    <table width="239">
                    <% 
                    	censusData cenObj = (censusData) cenList.get(0);
                    
                    %>
                    
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Census Data</div></td>
                        <td bgcolor="#B9DCFF"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Population:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getPopulation() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Households per zip:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getHouseholds_per_zip() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">White population:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getWhite_population() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Black population:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getBlack_population() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Hispanic Population:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getHispanic_population() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Persons per house:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getPersons_per_household()%></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Average house value:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getAvg_house_value() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Income per household:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getIncome_per_household() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Lat:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getLatitude() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Long:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getLongitude()%></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Elevation:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getElevation() %></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">State:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getState()%></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">City type:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getCity_type()%></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#B9DCFF"><div align="left" class="style42">Area code:</div></td>
                        <td bgcolor="#B9DCFF"><div align="left" class="style42"><%=cenObj.getArea_code()%></div></td>
                      </tr>
                      <tr height="17">
                        <td bgcolor="#FFFFFF"><div align="left" class="style42"> </div></td>
                        <td bgcolor="#FFFFFF"><div align="left" class="style42"> </div></td>
                      </tr>
					<% } %>
                    </table>
               
                    </td>
                  </tr>
                <% } %>  
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">

                  <tr>
                    <td height="17" width="640"><div align="center"></div></td>
                  </tr>
                </table>
                
                
                
                            
<!-- Displaying Statewide Criminal Records Records --> 
								
                <h4 align="left" class="style32"><strong>Statewide Criminal Records</strong></h4>
            <% if(BgcOffenders == null || (BgcOffenders != null && BgcOffenders.size() == 0)) { %>
 			<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
 			<% } else { %>
    <form name="BGCresult" method="post"  target="_blank">
	<div><p class="style27">Click on 'View All Details' or check the records that you want to view and<br>
click on 'View Checked Details' and they will appear in a new browser window.
</p></div>                
<p>&nbsp; </p>
                <table width="774" border="0">
				<tr>
                    <td width="371"><span class="style32"><a href="#" onclick="validateField('all'); return false;">View All Details</a> | <a href="#" onclick="validateField('one'); return false;">View Checked Details</a></span></td>
                    <td width="421">&nbsp;</td>
                  </tr>
                </table>
				 <div style="font-size: 14px;color: red; visibility:hidden" id="error"> <b>Please check the record you want to view </b></div>
                 <p align="left" class="style32">&nbsp;</p>
                <table width="891">
                  <tr>
                    <td width="40">&nbsp;</td>
                    <td width="64"><span class="style65">Detail #</span></td>
                    <td width="246"><span class="style65">Name</span></td>
                    <td width="128"><span class="style65">Date of Birth</span></td>
                    <td width="389"><span class="style65">Provider</span></td>
                  </tr>
					<%  for(int w = 0; w < BgcOffenders.size(); w++)
					{
						BGCOffenderBean	offender = (BGCOffenderBean) BgcOffenders.get(w);	 %>
                  <tr>
                    <td><input type="checkbox" name="printChecked" id="offenderId" value="<%=offender.getBgcOffenderId()%>"></td>
                    <td><span class="style42"><%=w+1 %></span></td>
                    <td><span class="style42"><%=offender.getFullName()%></span></td>
                    <td><span class="style42"><% if(!offender.getDateOfBirth().equals("")) {%><%=offender.getDateOfBirth()%> <% } else { %>N/A <% } %></span></td>
                    <td><span class="style42"><%=offender.getProvider()%></span></td>
                  </tr>
                  <% } %>

                </table>
                <p>&nbsp;</p>
                <table width="774" border="0">
                  <tr>
                    <td width="371"><span class="style32"><a href="#" onclick="validateField('all'); return false;">View All Details</a> | <a href="#" onclick="validateField('one');return false;">View Checked Details</a></span></td>

                    <td width="421">&nbsp;</td>
                  </tr>
                </table>
                 <p align="left" class="style32">&nbsp;</p>
                 <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">

                  <tr>
                    <td height="17" width="640"><div align="center"></div></td>
                  </tr>
                </table>
         </form>       
               

<!-- Displaying Business List -->
 				
                <h4 align="left" class="style32"><strong>Business Search</strong></h4>
                <%   
				 		if(businessList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="116" />
                  <col width="106" />
                  <col width="220" />
                  <col width="115" />
                  <col width="131" />
                  
                  <% 
                       bgColor = "";
								 for(int m=0 ; m<businessList.size(); m++)
								  {
								  		if((m%2) == 0)	{
							  				bgColor = "";
							  			}
							 			 else  {
							  				bgColor = "#fffeb1";
							  			}	
									  businessSearch businessObj=(businessSearch) businessList.get(m);  %>
				   <tr height="17" >
                    <td height="17" bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td width="99" height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Company:</div></td>
                    <td width="260" bgcolor="<%=bgColor %>"><div align="left" class="style42"> <%=businessObj.getCompany()%></div></td>
                    <td width="94" bgcolor="<%=bgColor %>"><div align="left" class="style42">Phone:</div></td>
                    <td width="151" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getPhone() %></div></td>
                    <td width="111" bgcolor="<%=bgColor %>"><div align="left" class="style42">Square Footage:</div></td>
                    <td width="176" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getSquarefootage()%></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Address:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getAddress() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Fax:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getFax() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Credit Score:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getCreditscorelettergrade() %></div></td>
                  </tr>
                  <tr height="16" bgcolor="<%=bgColor %>">
                    <td height="16" bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getCity() %> <%=businessObj.getState() %> <%=businessObj.getZip() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">County:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"> <%=businessObj.getCounty() %>;</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Credit Score:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getCreditscorenumericgrade() %></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Title:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getOwnertitle() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Annual Sales:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getAnnualsales() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">SIC Code:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getPrimarysic()%></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Owner Name:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getOwnerfirst() %> <%=businessObj.getOwnerlast() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"># of Employees:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getNumberofemployees() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Description:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=businessObj.getPrimarysicdescription() %></div></td>
                  </tr>
                 
                  <% } %>
 
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">

                  <tr>
                    <td height="17" width="640"><div align="center"></div></td>
                  </tr>
                </table>
                
                
<!-- Displaying Campaign Contributions -->
 				        

                <h4 align="left" class="style32"><strong>Campaign Contributions</strong></h4>
                <%   
				 		if(campaignList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="51" />
                  <col width="158" />
                  <col width="116" />
                  <col width="140" span="3" />
                  <% 
                  			bgColor = "";
								 for(int m=0 ; m<campaignList.size(); m++)
								  {
										if((m%2) == 0)	{
							  				bgColor = "";
							  			}
							 			 else  {
							  				bgColor = "#fffeb1";
							  			}		
									 campaignContributors campObj=(campaignContributors) campaignList.get(m);  %>
				 <tr height="17" >
                    <td height="17" bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td width="251" height="30" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=campObj.getFirstname() %> <%=campObj.getMiddlename() %> <%=campObj.getLastname() %></div></td>
                    <td width="123" bgcolor="<%=bgColor %>"><div align="left" class="style42">Occupation:</div></td>
                    <td width="162" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=campObj.getOccupation() %></div></td>
                    <td width="114" bgcolor="<%=bgColor %>"><div align="left" class="style42">Candidate:</div></td>
                    <td width="202" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=campObj.getCandidate() %></div></td>
                    <td width="39" bgcolor="<%=bgColor %>">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=campObj.getAddress() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Contribution Date:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=campObj.getContributionDate() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Term:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=campObj.getTerm() %></div></td>
                    <td bgcolor="<%=bgColor %>">&nbsp;</td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=campObj.getCity() %> <%=campObj.getState() %> <%=campObj.getZip() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>">&nbsp;</td>
                  </tr>
                  
                  <% } %>
               </table>
               <% } %>
               <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="945"><div align="center"></div></td>
                  </tr>
                </table>
               
                
<!-- Displaying US Domain Information--> 

				 
                <h4 align="left" class="style32"><strong>U.S. Domain Name Ownership</strong></h4>
                <%   
				 		if(USDomainList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="73" />
                  <col width="101" />
                  <col width="177" />
                  <col width="64" />
                  <col width="149" />
                  
                  <% 
                  		bgColor = "";
					 for(int m=0 ; m<USDomainList.size(); m++)
					  {
						 	if((m%2) == 0)	{
				  				bgColor = "";
				  			}
				 			 else  {
				  				bgColor = "#fffeb1";
				  			}		
						  USDomainNameOwnership USDomainObj=(USDomainNameOwnership) USDomainList.get(m); %>
				   <tr height="18" >
                    <td height="18" bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                  </tr>
                  <tr height="17">
                    <td width="114" height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Domain Name:</div></td>
                    <td width="380" bgcolor="<%=bgColor %>"><div align="left" class="style42"><a href="http://<%=USDomainObj.getDomain_Name() %>/" target="_blank"><%=USDomainObj.getDomain_Name()%></a></div></td>
                    <td width="107" bgcolor="<%=bgColor %>"><div align="left" class="style42">Phone:</div></td>
                    <td width="277" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=USDomainObj.getPhone() %></div></td>
                    <td width="13" bgcolor="<%=bgColor %>">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Owner Name:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=USDomainObj.getFirstname() %> <%=USDomainObj.getMiddlename() %> <%=USDomainObj.getLastname() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Fax:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=USDomainObj.getFax() %></div></td>
                    <td bgcolor="<%=bgColor %>">&nbsp;</td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="<%=bgColor %>"><div align="left" class="style42">Company:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=USDomainObj.getCompany() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Email:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><a href="mailto:<%=USDomainObj.getEmailaddress() %>"><%=USDomainObj.getEmailaddress() %></a></div></td>
                    <td bgcolor="<%=bgColor %>">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Address:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=USDomainObj.getAddress() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>">&nbsp;</td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">
                        <div align="left"><%=USDomainObj.getCity() %> <%=USDomainObj.getState() %> <%=USDomainObj.getZip() %></div>
                    </div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>">&nbsp;</td>
                  </tr>
                 
                  
                  <% } %>
                  </table>
                  <% } %>
                  <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="944"><div align="center"></div></td>
                  </tr>
                </table>
                  
                
                
<!-- Displaying FCC Licenses -->
 				   
                <h4 align="left" class="style32"><strong>FCC Licenses</strong></h4>
                <%   
				 		if(fccList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="64" />
                  <col width="195" />
                  <col width="97" />
                  <col width="78" />
                  
                  <% 
                  		bgColor = "";
					 for(int m=0 ; m<fccList.size(); m++)
					  {
						 	if((m%2) == 0)	{
				  				bgColor = "";
				  			}
				 			 else  {
				  				bgColor = "#fffeb1";
				  			}		
						  FCCLicenses fccObj=(FCCLicenses) fccList.get(m); %>
                  
                   <tr height="17" >
                    <td height="17" bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    
                  </tr>
                  <tr height="17">
                    <td width="114" bgcolor="<%=bgColor %>"><div align="left" class="style42">Name:</div></td>
                    <td width="336" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fccObj.getFirstname() %> <%=fccObj.getMiddlename() %> <%=fccObj.getLastname() %></div></td>
                    <td width="141" bgcolor="<%=bgColor %>"><div align="left" class="style42">Callsign:</div></td>
                    <td width="300" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fccObj.getCallsign() %></div></td>
                  </tr>
                  <tr height="17">
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Address:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fccObj.getAddress() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">FRN Number:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fccObj.getFRNnumber() %></div></td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="<%=bgColor %>"><span class="style31"></span></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fccObj.getCity() %> <%=fccObj.getState() %> <%=fccObj.getZip() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"><span class="style31"></span></span></span></span></div></td>
                  </tr>
                 
                  <% } %>
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="733"><div align="center"></div></td>
                  </tr>
                </table>
                         
                
                
<!-- Displaying Firearm Dealer Licenses -->
 				   
                <h4 align="left" class="style32"><strong>Firearm Dealer Licenses</strong></h4>
                <%   
				 		if(fflList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="74" />
                  <col width="84" />
                  <col width="197" />
                  <col width="108" />
                  <col width="195" />
                  <% 
					bgColor = "";
					 for(int m=0 ; m<fflList.size(); m++)
					  {
						 	if((m%2) == 0)	{
				  				bgColor = "";
				  			}
				 			 else  {
				  				bgColor = "#fffeb1";
				  			}			
						  FFLFirearmDealerLicenses fflObj=(FFLFirearmDealerLicenses) fflList.get(m); %>       
					
					 <tr height="17" >
                    <td height="17" bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    
                  </tr>	                               
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td width="90" height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Name:</div></td>
                    <td width="331" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fflObj.getLicensee_name() %></div></td>
                    <td width="156" bgcolor="<%=bgColor %>"><div align="left" class="style42">Business Name:</div></td>
                    <td width="314" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fflObj.getBusiness_name()%></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Address:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fflObj.getPremise_street()%></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Mailing Address:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fflObj.getMailing_address() %></div></td>
                  </tr>
                  <tr height="16" bgcolor="<%=bgColor %>">
                    <td height="16" bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fflObj.getPremise_city() %> <%=fflObj.getPremise_state() %> <%=fflObj.getPremise_zip() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fflObj.getMailing_city() %> <%=fflObj.getMailing_city() %> <%=fflObj.getMailing_zip() %></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Phone:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fflObj.getPremise_phone() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Phone:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=fflObj.getMailing_phone() %></div></td>
                  </tr>
                  
	         <% } %>
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="733"><div align="center"></div></td>
                  </tr>
                </table>
				
               

<!-- Displaying Concealed Weapons Permits -->
 				
                <h4 align="left" class="style32"><strong>Concealed Weapons Permits</strong></h4>
                <%   
				 		if(weaponList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="125" />
                  <col width="196" />
                  <col width="118" />
                  <col width="84" />

				<% 
					bgColor = "";
								 for(int m=0 ; m<weaponList.size(); m++)
								  {
									if((m%2) == 0)	{
				  							bgColor = "";
				  						}
				 			 		else  {
				  							bgColor = "#fffeb1";
				  						}		
									  concealedWeaponPermits weaponObj=(concealedWeaponPermits) weaponList.get(m); %>
					
					 <tr height="17" >
                    <td height="17" bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style27"><span class="style31"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style27"><span class="style31"></span></span></div></td>
                  </tr>				  
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td width="267" height="17"><div align="left" class="style42"><%=weaponObj.getFirstname()%> <%=weaponObj.getMiddlename()%> <%=weaponObj.getLastname()%></div></td>
                    <td width="79"><div align="left" class="style42">Date of Birth:</div></td>
                    <td width="93"><div align="left" class="style42"><%=weaponObj.getDateofbirth()%></div></td>
                    <td width="126"><div align="left" class="style42">License Number:</div></td>
                    <td width="128"><div align="left" class="style42"><%=weaponObj.getLicenseNumber()%></div></td>
                    <td width="66"><div align="left" class="style42">County:</div></td>
                    <td width="132"><div align="left" class="style42"><%=weaponObj.getCountyName()%></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=weaponObj.getAddress()%></div></td>
                    <td><div align="left" class="style42">Race:</div></td>
                    <td><div align="left" class="style42"><%=weaponObj.getRace() %></div></td>
                    <td><div align="left" class="style42">Expires:</div></td>
                    <td><div align="left" class="style42"><%=weaponObj.getExpirationdate()%></div></td>
                    <td><div align="left" class="style42">State:</div></td>
                    <td><div align="left" class="style42"><%=weaponObj.getState1()%></div></td>
                  </tr>
                  <tr height="16" bgcolor="<%=bgColor %>">
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=weaponObj.getCity()%> <%=weaponObj.getState()%> <%=weaponObj.getZip()%></div></td>
                    <td><div align="left" class="style42">Sex:</div></td>
                    <td><div align="left" class="style42"><%=weaponObj.getSex()%></div></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                
				<% } %>
                 </table>
                 <% } %>
                 <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="733"><div align="center"></div></td>
                  </tr>
                </table>
                 
                
                
<!-- Displaying DEA Search -->
 				
                <h4 align="left" class="style32"><strong>DEA Search</strong></h4>
                <%   
				 		if(DeaList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="85" />
                  <col width="105" />
                  <col width="190" />
                  <col width="103" />
                  <col width="127" />

				<% 
					bgColor = "";
				 for(int m=0 ; m<DeaList.size(); m++)
				  {
					 if((m%2) == 0)	{ bgColor = ""; } else  { bgColor = "#fffeb1"; }
					  DEASearch DeaObj=(DEASearch) DeaList.get(m); %>
				 <tr>
                    <td><p>&nbsp;</p></td>
                  </tr>	  
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td width="85" height="17"><div align="left" class="style42">Name:</div></td>
                    <td width="265"><div align="left" class="style42"><%=DeaObj.getFirstname() %> <%=DeaObj.getLastname() %></div></td>
                    <td width="116"><div align="left" class="style42">DEA Number:</div></td>
                    <td width="192"><div align="left" class="style42"><%=DeaObj.getDEANumber() %></div></td>
                    <td width="233"><div align="left" class="style42">County: <%=DeaObj.getCounty() %></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="17"><div align="left" class="style42">Address:</div></td>
                    <td><div align="left" class="style42"><%=DeaObj.getAddress() %></div></td>
                    <td><div align="left" class="style42">Business Type:</div></td>
                    <td><div align="left" class="style42"><%=DeaObj.getBusinesstype() %></div></td>
                    <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="16" bgcolor="<%=bgColor %>">
                    <td height="16"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td><div align="left" class="style42"><%=DeaObj.getCity() %> <%=DeaObj.getState() %> <%=DeaObj.getZip() %></div></td>
                    <td><div align="left" class="style42">Expires:</div></td>
                    <td><div align="left" class="style42"><%=DeaObj.getExpirationdate() %></div></td>
                    <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                </table>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="<%=bgColor %>">
                  <col width="85" />
                  <col width="105" />
                  <col width="310" />
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td width="84" height="17"><div align="left" class="style42">Schedules:</div></td>
                    <td width="807"><div align="left" class="style42"><%=DeaObj.getSchedules() %></div></td>
                  </tr>
                  </table>
                  <table width="893" cellpadding="0" cellspacing="0">
                  
                  <% } %>
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="733"><div align="center"></div></td>
                  </tr>
                </table>
                        
 
                
                
<!-- Displaying Merchant Vessels -->
 				
                <h4 align="left" class="style32"><strong>Merchant Vessels</strong></h4>
                <%   
				 		if(mess.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="117" />
                  <col width="120" />
                  <col width="182" />
                  <col width="107" />
                  <col width="139" />
                  <% 
                   bgColor = "";
				  for(int m=0 ; m<mess.size(); m++)
				  {
					  if((m%2) == 0)	{ bgColor = ""; } else  { bgColor = "#fffeb1"; } 
					  merchantVessels mes=(merchantVessels) mess.get(m); %>
					  
				   <tr height="17">
                    <td height="17" bgcolor=""><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>	  
                  <tr height="17">
                    <td width="110" height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Vessel Name:</div></td>
                    <td width="226" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getVesselname() %></div></td>
                    <td width="104" bgcolor="<%=bgColor %>"><div align="left" class="style42">Callsign:</div></td>
                    <td width="179" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getCallsign() %></div></td>
                    <td width="101" bgcolor="<%=bgColor %>"><div align="left" class="style42">Shipyard:</div></td>
                    <td width="171" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getShipyard()%></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Hull Number:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getHullnumber()%></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">IMO Number:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getImonumber() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Home Port:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getHomeport() %></div></td>
                  </tr>
                  <tr height="16">
                    <td height="16" bgcolor="<%=bgColor %>"><div align="left" class="style42">Hull ID:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getHullidnumber() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Service Type:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getServicetype() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">State of Port:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getStateofport() %></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Company Owner:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getCompanyowner() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Boat Maker:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getBoatmaker() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Horsepower:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getHorsepower() %></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Company Type:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getCompanytype() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Year Built:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getYearbuilt() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Gross Tons:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getGrosstons() %></div></td>
                  </tr>
                  <tr height="18">
                    <td height="18" bgcolor="<%=bgColor %>"><div align="left" class="style42">Owner Name:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getOwnerfirstname() %> <%=mes.getOwnerlastname() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Hull Material:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getHullmaterial()%></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Length:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getLength() %></div></td>
                  </tr>
                  <tr height="17">
                    <td height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42">Address:</div></td>
                    <td width="226" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getAddress() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Motor Type:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getMotortype() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Width:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=mes.getWidth() %></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="17" ><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td><div align="left" class="style42"><%=mes.getCity() %> <%=mes.getState() %> <%=mes.getZip() %></div></td>
                    <td ><div align="left" class="style42">Country Built:</div></td>
                    <td ><div align="left" class="style42"><%=mes.getCountrybuilt() %></div></td>
                    <td ><div align="left" class="style42">Depth:</div></td>
                    <td ><div align="left" class="style42"><%=mes.getDepth() %></div></td>
                  </tr>
                
				<% } %>
                 
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="733"><div align="center"></div></td>
                  </tr>
                </table>
                
                
 <!-- Displaying Aircraft Search -->
 				              
                <h4 align="left" class="style32"><strong>Aircraft Search</strong></h4>
                <%   
				 		if(airList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="107" />
                  <col width="96" />
                  <col width="202" />
                  <col width="117" />
                  <col width="108" />
				<% 
					bgColor = "";
							 for(int m=0 ; m<airList.size(); m++)
							  {
									if((m%2) == 0)	{ bgColor = ""; } else  { bgColor = "#fffeb1"; }
								  aircraftSearch airObj=(aircraftSearch) airList.get(m); %>
                  <tr height="17">
                    <td height="17">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>								  
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td width="116" height="17"><div align="left" class="style42">Owner Name:</div></td>
                    <td width="207"><div align="left" class="style42"><%=airObj.getOwnername() %></div></td>
                    <td width="119"><div align="left" class="style42">Model:</div></td>
                    <td width="158"><div align="left" class="style42"><%=airObj.getModel() %></div></td>
                    <td width="124"><div align="left" class="style42">Year Built:</div></td>
                  <td width="167"><div align="left" class="style42"><%=airObj.getYearbuilt() %></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td height="16"><div align="left" class="style42">Address:</div></td>
                    <td><div align="left" class="style42"><%=airObj.getAddress() %>  <%=airObj.getAddress2() %></div></td>
                    <td height="17"><div align="left" class="style42">N Number:</div></td>
                    <td><div align="left" class="style42"><%=airObj.getNnumber() %></div></td>
                    <td><div align="left" class="style42">Certification Date:</div></td>
                    <td><div align="left" class="style42"><%=airObj.getCertdate() %></div></td>
                  </tr>
                  <tr height="16" bgcolor="<%=bgColor %>">
                    <td height="17"><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td><div align="left" class="style42"> <%=airObj.getCity() %> <%=airObj.getState() %> <%=airObj.getZip() %></div></td>

                    <td><div align="left" class="style42">Serial Number:</div></td>
                    <td><div align="left" class="style42"><%=airObj.getSerialnumber() %></div></td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>

                  
				<% } %>
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="733"><div align="center"></div></td>
                  </tr>
                </table>
                
                
<!-- Displaying Concealed Weapons Permits -->
 				
                <h4 align="left" class="style32"><strong>FAA Pilot Licenses</strong></h4>
                <%   
				 		if(faaList.isEmpty())
				 		{
				%>
				<h4 align="center" class="style32"><strong>NO RECORDS FOUND</strong></h4>
				<%		
						}
						else{
				 %>
                <table width="893" cellpadding="0" cellspacing="0">
                  <col width="138" />
                  <col width="177" />
                  <col width="127" />
                  <col width="209" />
                  <% 
                     bgColor = "";
					 for(int m=0 ; m<faaList.size(); m++)
					  {
						 if((m%2) == 0)	{ bgColor = ""; } else  { bgColor = "#fffeb1"; }
						  FAAPilotLicenses faaObj=(FAAPilotLicenses) faaList.get(m);
						   %>
                   <tr height="18" bgcolor="<%=bgColor %>">
                    <td height="18" bgcolor=""><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style23"><span class="style31"><span class="style27"></span></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor=""><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td width="289" height="17" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=faaObj.getFirstname() %> <%=faaObj.getMiddlename() %> <%=faaObj.getLastname() %></div></td>
                    <td width="116" bgcolor="<%=bgColor %>"><div align="left" class="style42">FAA Number:</div></td>
                    <td width="203" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=faaObj.getFAANumber() %></div></td>
                    <td width="117" bgcolor="<%=bgColor %>"><div align="left" class="style42">Certification Level:</div></td>
                    <td width="166" bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=faaObj.getCertlevel() %></div></td>
                  </tr>
                  <tr height="17" bgcolor="<%=bgColor %>">
                    <td><div align="left" class="style42"><%=faaObj.getAddress() %> <%=faaObj.getAddress2() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Medical Exp:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=faaObj.getMedicalExpDate() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Ratings:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=faaObj.getRatings()%></div></td>
                  </tr>
                  <tr height="16" bgcolor="<%=bgColor %>">
                    <td><div align="left" class="style42"><%=faaObj.getCity() %> <%=faaObj.getState() %> <%=faaObj.getZip() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42">Certification Type:</div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left" class="style42"><%=faaObj.getCertificationType() %></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                    <td bgcolor="<%=bgColor %>"><div align="left"><span class="style31"><span class="style27"></span></span></div></td>
                  </tr>
                 
                  <% }  %>
                </table>
                <% } %>
                <table width="893" cellpadding="0" cellspacing="0" bgcolor="#003366">
                  <tr>
                    <td height="17" width="733"><div align="center"></div></td>
                  </tr>
                </table>
                
 
            </td>
          </tr>
        </table>
          <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="814" border="0" align="center" bgcolor="#FEF8A5">
      <tr>
                  <td width="895"><p align="left" class="style30">&quot;Your website is the search engine of the future... a source that provides relief from the usual frustrations of online searching. SearchSystems.net is so consistently dependable, it feels as if you're not on this planet. It's just perfect.&quot; - Rose</p>
                      <p align="left" class="style31">&nbsp;</p>
                    <p align="left" class="style30">&quot;Your staff is great. SearchSystems.net is a 5-star program on a basic budget.&quot; - Rick</p>
                    <p align="left" class="style31">&nbsp;</p>
                <p align="left" class="style30">&quot;The one source I always mention in my presentations for private investigators and professional researchers.&quot;  - Tamara Thompson, P.I.</p></td>
                </tr>
            </table>
            <p>&nbsp;</p></td>
          </tr>
        </table>        </td>
  </tr>
	<tr>
		<td width="893" class="footer" style="height:56px; background:url(/springapp/images/findpeople/bg_footer_11.jpg) top no-repeat; padding:15px 0 0 60px;">
			<p>Copyright &copy; 2009 Pacific Information Resources, Inc. All rights reserved.</p>
			<p><a href="http://www.searchsystems.net/tos.php" target="_blank">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php" target="_blank">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php" target="_blank">About Us</a></p></td>
	</tr>
</table>

</body>
</html>
<% } %>