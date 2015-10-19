<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 
boolean member=false;
String username=(String)session.getAttribute("username");
if(username != null){
	member=true;
}

 String YOB = (String)session.getAttribute("BgcYob");
 Hashtable BGResult = (Hashtable) session.getAttribute("BGResult");
 ArrayList offToFetch = (ArrayList) session.getAttribute("offToFetch");
 ArrayList<BGCOffenderBean> offenders = (ArrayList)session.getAttribute("BgcOffenders");
 HashMap aliasesMap = (HashMap) session.getAttribute("BgcAliases");
 HashMap offensesMap = (HashMap) session.getAttribute("BgcOffenses");
 String searchDob = (String) session.getAttribute("searchDob");
 
 if(!(BGResult == null || offenders==null || offToFetch==null)) {
	
 
 ArrayList<BGCOffenderBean> checkedOff = new ArrayList<BGCOffenderBean>(); 
 int size = offToFetch.size();
 Integer[] offFetch=new Integer[size];
 int i=0;
 for (BGCOffenderBean o : offenders) { 
	 
	 if(offToFetch.contains(o.getBgcOffenderId())) {
		 checkedOff.add(o);
		 offFetch[i++] = o.getBgcOffenderId();
	 }
 }
 		 
 
 BGxmlManager bgResult = (BGxmlManager) BGResult.get("BGResult"); 
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.HashMap"%>
<%@page import="springapp.web.findpeople.BGxmlManager"%>
<%@page import="net.searchsystems.limestone.bean.BGCOffenderBean"%>
<%@page import="net.searchsystems.limestone.bean.BGCAliasBean"%>
<%@page import="net.searchsystems.limestone.bean.BGCOffenseBean"%>
<%@page import="net.searchsystems.limestone.bean.BGCOffenseSupplementBean"%>
<%@page import="java.util.List"%>
<%@page import="net.searchsystems.limestone.BGCOffenseSupplement"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>criminal record details</title>
<link type="text/css" rel="stylesheet" href="/springapp/css/findpeople_03.css">
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
.style27 {font-family: Arial, Helvetica, sans-serif}
.style30 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-style: italic;
}
.style31 {font-size: x-small}
.style32 {font-family: Arial, Helvetica, sans-serif; color: #003366; }
.style41 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: x-small;
	font-weight: bold;
}
.style42 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
.style71 {
	font-size: 14px
}
.style72 {font-family: Arial, Helvetica, sans-serif; font-size: 14px; font-weight: bold; }
.style75 {color: #003366; font-weight: bold; }
-->
</style>
</head>
<body>
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
		<td width="893" style="padding: 10px 0;">
		<% if(!member) {  %>
			<table width="893" height="63" border="0" bgcolor="#fffeb1">
              <tr>
                  <td><div align="left"><a href="https://www.searchsystems.net/springapp/funnel/WholePicture.html"><img src="/springapp/images/findpeople/ban-access-more.png" alt="access more" border="0"></a></div></td>
              </tr>
              </table>
			 <%  }  %>
		
		<table width="893" border="0">
          <tr>
            <td width="896" valign="top"><table width="889" height="76" border="0">
  <tr>
                    <td width="883" height="72"><table width="880" align="left">
                      <tr>
                        <td height="53"><h1 class="style32">Statewide Criminal Record Result Details</h1>
                        </td>
                      </tr>
                    </table>                    </td>
            </tr>
                </table>
                
<!--  Displaying the Search Input -->                
    <table width="893" border="0">
          <tr>
                    <td height="109"><p class="style27"><strong>Name Searched:</strong> <span class="style75"><%=bgResult.getProRpt().getFirstName() %> <%if(bgResult.getProRpt().getDOB().equals("")) {%><%=bgResult.getProRpt().getMiddleName() %> <% } %> <%=bgResult.getProRpt().getLastName() %></span></p>
                    <p class="style27"><strong>Date of Birth:</strong> <span class="style75"><%= searchDob %></span></p>
                    <p class="style27"><strong>State: </strong><span class="style75"><%=bgResult.getProRpt().getState() %></span></p>
                    <p class="style27">&nbsp;</p>
                    <p class="style42">Results include records where no DOB is listed. Those records may or may not pertain to your subject.</p></td>
                <td><p>&nbsp;</p>
                    </td>
                  </tr>
                </table>
                
<!-- Printing to pdf --> 
                     
			<a href="#"><img src="/springapp/images/findpeople/printer-icon.jpg" alt="Print"  border="0" onClick="window.print()"/></a>
               <p>&nbsp;</p>
                         		
                       		 
<!--  Displaying Offender info -->
		  <%   for (BGCOffenderBean o : checkedOff) {    %>
                <table width="891">
                  <tr>
                    <td height="215"><h4 class="style27">Offender Info</h4>
                      <table width="883" border="0">
             
                        <tr>
                          <td width="367" bgcolor="#FFFFFF"><p class="style41">Full Name</p>
                          <p class="style42"><%=o.getFullName() %></p></td>
                          <td width="195" bgcolor="#FFFFFF"><p class="style41">DOB</p>
                          <p class="style42"><%=o.getDateOfBirth()%></p></td>
                          <td width="178" bgcolor="#FFFFFF"><p class="style41">ID</p>
                          <p class="style42"><%=o.getRecordOffenderId() %></p>                          </td>
                          <td width="115" bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                        </tr>
                        
                        <tr>
                          <td bgcolor="#FFFFFF"><p class="style41">Address</p>
                          <p class="style42"><%=o.getStreet() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">City</p>
                          <p class="style42"><%=o.getCity() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">State</p>
                          <p class="style42"><%=o.getState() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Postal Code</p>
                          <p class="style42"><%=o.getPostalCode() %></p></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><p class="style41">Gender</p>
                          <p class="style42"><%=o.getGender() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Race</p>
                          <p class="style42"><%=o.getRace() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">County</p>
                          <p class="style42"><%=o.getCounty() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Country</p>
                          <p class="style42"><%=o.getCountry() %></p></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><p class="style41">Height</p>
                          <p class="style42"><%=o.getHeightFeet() %>'<%=o.getHeightInches() %>''</p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Weight</p>
                          <p class="style42"><%=o.getWeight() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Eye Color</p>
                          <p class="style42"><%=o.getEyeColor() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Hair Color</p>
                          <p class="style42"><%=o.getHairColor() %></p></td>
                        </tr>
                        <tr>
                          <td bgcolor="#FFFFFF"><p class="style41">Record Source</p>
                          <p class="style42"><%=o.getRecordSource() %></p></td>
                          <td bgcolor="#FFFFFF"><p class="style41">Record State</p>
                          <p class="style42"><%=o.getRecordState() %></p></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                          <td bgcolor="#FFFFFF"><div align="left"><span class="style31"><span class="style27"><span class="style31"></span></span></span></div></td>
                        </tr>
                    </table>                      </td>
                  </tr>
                  <tr>
                    <td><h4 class="style27">Aliases</h4>
                    <% if(aliasesMap !=null) { 
                    	BGCAliasBean aliasBean[] = (BGCAliasBean[]) aliasesMap.get(o.getBgcOffenderId());
                    		if(aliasBean.length != 0)
                    		{
                    	%>
							
						<table width="491" border="0">
                      <% 
                        	for (BGCAliasBean alias : aliasBean) 
                			{   	%>
                        <tr>
						  <td bgcolor="#FFFEB1"><div align="left"><span class="style42">First Name</span></div></td>	
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42">Last Name</span></div></td>
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42">Middle Name</span></div></td>
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42">Suffix</span></div></td>
                        </tr>
                        
                        <tr>
						  <td bgcolor="#FFFEB1"><div align="left"><span class="style42"><%=alias.getFirstName() %></span></div></td>	
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42"><%=alias.getLastName() %></span></div></td>
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42"><%=alias.getMiddleName() %></span></div></td>
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42"><%=alias.getSuffix() %></span></div></td>
                        </tr>
                        <%  } %>
                      </table>
                      <% } else {%>
							
                      <table width="491" border="0">
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42">No aliases found.</span></div></td>
                        </tr>
                      </table>
                      <% } } %>
                      
                    <p>&nbsp;</p></td>
                  </tr>
                  <tr>
                    <td><h4 class="style27">Offenses</h4>
                     <% if(offensesMap !=null) { 
                    	int r=0;
                    	BGCOffenseBean offenseBean[] = (BGCOffenseBean[]) offensesMap.get(o.getBgcOffenderId());
                    		if(offenseBean.length != 0)
                    		{
                    			String bgColor = "";
                    			for (BGCOffenseBean offense : offenseBean) 
                    			{ 
                    				
                    				if((r%2) == 0)	{ bgColor = "";	} else  {bgColor = "#fffeb1";}
                    				
                    				%>
                    			
                    
                      <p class="style41 style71">Result #<%=++r%></p>
                      <table width="554" border="0">
                        <tr>
                          <td width="127" bgcolor="<%=bgColor %>"><div align="left" class="style41">Description</div></td>
                          <td width="417" bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getDescription() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Degree of Offense</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getDegreeOfOffense() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Arresting Agency</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getArrestingAgency() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Disposition</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getDisposition() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Disposition Date</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getDispositionDate() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Arrest Date</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getArrestDate() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Sentence</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getSentence() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Sentence Date</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getSentence() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Confinement</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getConfinement() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Probation</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getProbation() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Offense Date</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getOffenseDate() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Fine</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=offense.getFine() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Statute</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                         <span class="style42"><%=offense.getStatute() %></span>
                          </div></td>
                        </tr>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Original Plea</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                         <span class="style42"><%=offense.getPlea() %></span>
                          </div></td>
                        </tr>
                        <% 	List<BGCOffenseSupplementBean> offSupList=offense.getBGCOffenseSupplementBeans();
                        	String caseNumber = "";
        					for(BGCOffenseSupplementBean offSup : offSupList)
        					{  				
        						
        						if(offSup.getDisplayTitle() != null && (offSup.getDisplayTitle().equalsIgnoreCase("Cause Number"))){
        							caseNumber = offSup.getDisplayValue();
        						}
        						if(offSup.getDisplayTitle() != null && (offSup.getDisplayTitle().equalsIgnoreCase("Case Number"))){
        							caseNumber = offSup.getDisplayValue();
        						}
        					}
                        %>
                        <tr>
                          <td bgcolor="<%=bgColor %>"><div align="left" class="style41">Case Number</div></td>
                          <td bgcolor="<%=bgColor %>"><div align="left">
                                                          <span class="style42"><%=caseNumber %></span>
                          </div></td>
                        </tr>
                        
                      </table>
                      <p>&nbsp;</p>
                      <% } } else { %>
                      <table width="491" border="0">
                        <tr>
                          <td bgcolor="#FFFEB1"><div align="left"><span class="style42">No Offenses found.</span></div></td>
                        </tr>
                      </table>
                      
                      <% } } %>
             
                    </td>
                  </tr>
                </table>
                <p><hr>
            <% } %>    
            </td>
          </tr>
        </table>
        
        	<p>&nbsp;</p>
                    <p><span class="style27">To see the data sources and coverage for our statewide criminal records search, <a href="https://www.searchsystems.net/springapp/funnel/ourDatabases.do">click here</a>.</span></p>
                    <p>&nbsp;</p>
                    <table width="834" border="1" align="center">
                      <tr>
                        <td><span class="style42"><strong>DISCLAIMER</strong><br>
Search Systems provides no warranty of any type as to the accuracy of this information,  and any reliance on this information is solely at your own risk and responsibility.  All  information retrieved from or through SearchSystems.net must be utilized in accordance with  the User Agreement and all applicable state and federal laws. <br>
<br>
Copyright © 2003-2008 Pacific Information Resources, Inc. All rights reserved. </span></td>
                      </tr>
                    </table>
                    <p>&nbsp;</p>
                    <p>&nbsp;</p>
          <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94">
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