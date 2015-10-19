<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@ page import="java.util.*" %>
<%@page import="springapp.web.corporate.xmlParsingHelper"%>
<%@page import="springapp.web.corporate.xmlParsingHelper.*"%>
<% 
 
boolean member=false;
String username=(String)session.getAttribute("username");
if(username != null)
{
	member=true;
}
	
	String searchName=(String) session.getAttribute("searchName");
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">



<%@page import="springapp.web.corporate.xmlParsingHelper.officerObj.offAddressObj"%>
<%@page import="springapp.web.corporate.xmlParsingHelper.officerObj.offAddressObj"%>
<%@page import="springapp.web.corporate.xmlParsingHelper.officerObj.offAddressObj"%>
<%@page import="springapp.web.corporate.xmlParsingHelper.officerObj.offNameObj"%><html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>View Print Details</title>
    <meta name="title" content="SearchSystems.net Premium"/>
    <meta name="description" content="SearchSystems.net Criminal and Offender Records, Bankruptcies, Judgments and Tax Liens, federal, court, civil, bankruptcy, public"/>
    <meta name="keywords" content="criminal, offender, bankruptcies, tax, liens, judgments, federal, property, court, civil, bankruptcy, public, records, national, social security, documents, suit, action, certificates, lawsuits, information, real, estate, state, florida, california, michigan"/>
    <meta name="rating" content="GENERAL"/>

    <meta name="classification" content="Search"/>
    <meta name="copyright" content="SearchSystems.net"/>
    <meta name="distribution" content="GLOBAL"/>
    <meta name="Author" content="SearchSystems.net"/>
    <meta http-equiv="content-language" content="en" />
    <link href="/springapp/css/corporate.css" rel="stylesheet" type="text/css" media="screen" />
   <style type="text/css">
<!--
.style12 {	font-size: small;
	font-weight: bold;
	font-style: italic;
}
.style13 {	color: #FFFFFF;
	font-weight: bold;
}
.style14 {color: #8399B1}
.style19 {	color: #FF0000;
	font-weight: bold;
}
.style20 {font-family: Arial, Helvetica, sans-serif}
.style15 {color: #000000}
.style16 {color: #003366}
.style17 {	color: #000033;
	font-weight: bold;
}
.style18 {font-family: Arial, Helvetica, sans-serif; color: #000000; }
-->
    </style>
</head>
  
<body>


  <div class="bodywrap">

    <center>
      <div id="owrap">
        
        <!--// masthead begins //-->
        <div id="masthead">
            <div id="logo">
              <p><a href="http://www.searchsystems.net/" title="SearchSystems.net"><img src="/springapp/images/common/updated-logo.png" alt="SearchSystems.net" /></a></p>
              <p align="center"><img src="/springapp/images/common/ban-access-more.png" alt="access more" width="840" height="155" border="0" usemap="#Map" />
<map name="Map" id="Map"><area shape="rect" coords="693,94,824,138" href="#" /></map></p>
              <table width="926" border="0">
                <tr>
                  <td width="780" height="98"><h1 align="left">Nationwide Corporations Search Results</h1>
                  <h2 align="left">View and Print Details</h2></td>
                  <td width="136"><p align="left"><a href="<c:url value="/corporate/corporateSearchLanding.do" />">Start a New Search</a></p>
                  <p align="left"><a href="#" onClick="javascript: window.print(); return false;">Print</a></p>
                  <p align="left"><a href="#" onClick="javascript: history.go(-1); return false;">Back to Results</a></p></td>
                </tr>
              </table>
              <p>&nbsp;</p>
              <p class="style19"></p>
              <table width="893" border="0" cellspacing="0">
                <tr>
                  <td align="right" valign="bottom" colspan="3"><span style="display:block;float:left;width:900px"><a name="resultsAnchor" id="resultsAnchor"> </a></span></td>
                </tr>
                <tr bgcolor="#CDC8B1">
                  <td width="559" align="left" bgcolor="#FFFFFF"><h4><font color="black">Searched: <strong><%=searchName %></strong></font></h4></td>
                  <td width="330" colspan="2" align="right" valign="bottom" bgcolor="#FFFFFF"><font color="#384783"> 
                    &nbsp;&nbsp;&nbsp;
                    
                    &nbsp; <strong>1</strong> &nbsp; </font> </td>
                </tr>
              </table>
             
              <% 
              String strCheck[] = (String[])request.getAttribute("selectedCorporateRecordsIndex");
          
				Hashtable searchResult=(Hashtable)session.getAttribute("searchResult");
				Enumeration keys=searchResult.keys();
							
				if(strCheck!=null)
				{
				for(int i=0;i<strCheck.length;i++)
					{
						xmlParsingHelper xp=(xmlParsingHelper)searchResult.get(strCheck[i]);
						
						addressObj addObj=(addressObj)xp.getAObj();
						stockObj stObj=(stockObj)xp.getSObj();
						prinBusObj prinObj=(prinBusObj)xp.getPrObj();
						mailingObj mailObj=(mailingObj)xp.getMObj();
											    
						ArrayList histList=(ArrayList)xp.getHistoryList();
						ArrayList mergeList=(ArrayList)xp.getMergerList();
						ArrayList offList=(ArrayList)xp.getOfficerList();
					
							
				%>	
              
			  
	 <!-- Starts Here  -->
			  
              <table width="895" border="1" cellspacing="0">
                <tr class="altRow2">
                  <td width="4" rowspan="2" align="center" class="resultsHead"></td>
                  <td width="241" valign="top" bordercolor="#666666" bgcolor="#FFFFFF">
                    	<h4 align="left"><strong><%=xp.getName()%></strong><br />&nbsp; </h4>
                    </td>
                    
     <!-- Displays Mailing Address --> 
                   <td width="217" valign="top" bordercolor="#666666" bgcolor="#FFFFFF">
                  
                   	<%if(mailObj!=null) { %>
                  		<h5 align="left" class="style20"><span class="style15">Mailing Address:</span>
    			  			 	<%=mailObj.getAddress1()%>,<%=mailObj.getAddress2()%><br /><%=mailObj.getCity()%>,
                  				<%=mailObj.getZip()%>,<%=mailObj.getZip4() %>,<%=mailObj.getState() %> </h5> 
                  			 
                  	<%} else { %>		 
                  		<h5 align="left" class="style20"><span class="style15">Mailing Address: Not Found</span>
                  	<% } %>		 
                  			                   		
 	<!-- Displays Principal Address --> 
   			       <%if(prinObj!=null){ %>		 
                    	<h5 align="left"><span class="label style15">Principal Address:</span><span class="style20"><br />
                            <span class="style16"><%=prinObj.getAddress1()%> <%=prinObj.getAddress2()%><br />
                            <%=prinObj.getCity()%> <%=prinObj.getZip()%> <%=prinObj.getZip4()%>  <%=prinObj.getState()%> </span></span></h5>
                    	<h5 align="left"><span class="style20"> Principal County: <span class="style16"><%=prinObj.getCounty() %></span><br />
                      		Principal Country: <span class="style16"><%=prinObj.getCountry()%></span></span><br />&nbsp; </h5></td>
                   <% } else {%> 
                   		<h5 align="left"><span class="label style14">Principal Address: Not Found</span><span class="style20"><br />
                   <% } %>
                     
   <!-- Displays Direct Results --> 
                  <td width="251" valign="top" bordercolor="#666666" bgcolor="#FFFFFF">
                  		<h5 align="left" class="style20"><span class="label">Status:</span> <%=xp.getCorpStatus()%> <br />
                          <span class="label">Type:</span> <%=xp.getCorpType()%><br />
                          <span class="label">Jurisdiction:</span> <%=xp.getJurisdiction() %> <br />
                          <span class="label">Registry Number:</span> <%=xp.getRegistryNumber() %> <br />
                          <span class="label">Purpose: <span class="style16"> <%=xp.getPurpose() %></span></span></h5>
                       <h5 align="left" class="style20"><span class="label">Term: <span class="style16"> <%=xp.getTerm() %></span> <br />
                        	DBA Name: <span class="style16"> <%=xp.getDBAName() %></span><br />
                       	 	Tax ID: <span class="style16"> <%=xp.getTaxID() %></span><br />
                        	Capital: <span class="style16"> <%=xp.getCapital() %></span></span></h5></td>
                  <td width="160" valign="top" bordercolor="#666666" bgcolor="#FFFFFF"><h5 align="left">
                  		<span class="label">Filing:</span> <%=xp.getFilingDate() %><br />
                    	</h5>
                      		<h5 align="left"><span class="label">Last Reported:</span> <%=xp.getLastReportDate() %></h5>
                    		<h5 align="left"><span class="style15">Expiration Date:</span> <%=xp.getExpireDate() %></h5></td>
                </tr>
                
               
 	<!-- Displays Officer Results -->                
                <tr class="altRow2">
                  <td colspan="4"><table width="100%">
                      <tr>
                        <td width="51%" valign="top"><table width="100%">
                        
                        <%if(!offList.isEmpty()) { %>
                            <tr>
                              <td colspan="2" bgcolor="#FFFF99" class="resultsHead">
                              	<h4 align="left" class="style15"><strong>OFFICER DATA</strong></h4></td>
                            </tr>
                            <tr>
                              <td align="center" bgcolor="#FFFF99" class="resultsHead"><h5 class="style15">Name</h5></td>
                              <td align="center" bgcolor="#FFFF99" class="resultsHead"><h5 class="style15">Address</h5></td>
                            </tr>
                            
                            <% 
                            	Iterator offl=offList.iterator();
                            	while(offl.hasNext())
                            	{
                            		officerObj offObj=(officerObj)offl.next();								         	                  
                            	    offAddressObj offadd=(offAddressObj)offObj.getOaObj();
                            	    offNameObj offNam=(offNameObj)offObj.getOnObj();
                            %>
                            
                            <tr>
                              <td valign="top" bgcolor="#FFFF99"><h5 align="left" class="style20">
                              <strong>
                              	<%=offNam.getFirst()%> <%=offNam.getFirstRest()%> <%=offNam.getMiddle()%>
                              	<%=offNam.getLast()%> <%=offNam.getLastRest()%> <%=offNam.getSuffix()%> 
                              </strong><br />
                              <span class="label">Title: <span class="style16"><%=offNam.getTitle() %></span> <br />
                                        Start Date: <span class="style16"><%=offObj.getStartDate()%></span> <br />
                                        Status: <span class="style16"><%=offObj.getStatus() %></span></span> <br />
                              </h5></td>
                              <td valign="top" bgcolor="#FFFF99">
                              <h5 align="left" class="style20">
                              	<%=offadd.getAddress1()%> <%=offadd.getAddress2()%> <br />
                                <%=offadd.getCity() %> <%=offadd.getZip()%> <%=offadd.getZip4()%> <%=offadd.getState()%><br />
                              </h5></td>
                            </tr>
                        <% } } else { %>  
                         	<tr>
                              <td colspan="2" bgcolor="#FFFF99" class="resultsHead">
                              	<h4 align="left" class="style15"><strong>NO OFFICER DATA</strong></h4></td>
                            </tr>
                        <% } %>                     
                        </table></td>
                        
                        
  <!-- Displays History Results -->                      
                        <td width="49%" valign="top">
                        <table width="100%">
                        
                        <% if(!histList.isEmpty()) {%>
                            <tr>
                              <td colspan="3" bgcolor="#CCFF99" class="resultsHead"><h4 align="left" class="style15"><strong>HISTORIC DATA</strong></h4></td>
                            </tr>
                            <tr>
                              <td width="52%" align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style15">Corporation</h5></td>
                              <td width="32%" align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style15">Amendment</h5></td>
                              <td width="16%" align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style15">Date</h5></td>
                            </tr>
                            
                            <% 
                            	Iterator hist=histList.iterator();
                            	while(hist.hasNext())
                            	{
                            		historyObj hisObj=(historyObj)hist.next();								         	                  
                                                        
                            %>
                             <tr>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=hisObj.getCorporationName() %> </h5></td>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=hisObj.getAmendmentType() %> </h5></td>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=hisObj.getAmendmentDate() %> </h5></td>
                            </tr>
                            
                         <% } }  else {%>
                         	<tr>
                              <td colspan="3" bgcolor="#CCFF99" class="resultsHead"><h4 align="left" class="style15"><strong>NO HISTORIC DATA</strong></h4></td>
                            </tr>
                         <% } %>
                        </table>
                        </td>
                      </tr>
                      
                      
	<!-- Displays Merge Results -->                      
                      <tr>
                        <td valign="top"><table width="100%">
                        
                          <% if(!mergeList.isEmpty()) {%>

                            <tr>
                              <td colspan="5" bgcolor="#CCFF99" class="resultsHead">
                              <h4 align="left" class="style15">
                              	<strong>MERGER DATA</strong>
                              	</h4></td>
                            </tr>
                       		<% 
                            	Iterator merge=mergeList.iterator();
                            	while(merge.hasNext())
                            	{
                            		mergerObj merObj=(mergerObj)merge.next();								         	                  
                                                        
                            %>
                            <tr>
                              <td align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style18">Event</h5></td>
                              <td align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style18">Merge Date</h5></td>
                              <td align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style18">Merged Corp. ID</h5></td>
                              <td align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style18">Merged Corp. Name</h5></td>
                              <td align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style18">State</h5></td>
                              <td align="center" bgcolor="#CCFF99" class="resultsHead"><h5 class="style18">Surviving Corp. ID</h5></td>
                            </tr>
                            <tr>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=merObj.getEventDescription()%></h5></td>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=merObj.getMergeDate() %></h5></td>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=merObj.getMergedCorpID() %></h5></td>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=merObj.getMergedCorpName() %></h5></td>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=merObj.getStateCode() %></h5></td>
                              <td valign="top" bgcolor="#CCFF99"><h5 align="left" class="style20"><%=merObj.getSurvivingCorpID() %></h5></td>
                            </tr>
                           
                          </table>
                          
                         
                            <table width="100%">
                                                           
                               <%}  }else { %>
                              <tr>
                              	<td colspan="5" bgcolor="#CCFF99" class="resultsHead">
                              		<h4 align="left" class="style15">
                              			<strong>MERGER DATA</strong>
                              		</h4></td>
                            </tr>
                            <% } %>
                               
                          </table></td>
                          
    <!-- Displays Stock Values -->
                        <td valign="top"><table width="100%">
                        
                        <% if(stObj!=null) { %>
                            <tr>
                              <td colspan="5" bgcolor="#FFFF99" class="resultsHead"><h4 align="left" class="style17">STOCK DATA</h4></td>
                            </tr>
                            <tr>
                              <td align="center" bgcolor="#FFFF99" class="resultsHead"><h5 class="style18">Class</h5></td>
                              <td align="center" bgcolor="#FFFF99" class="resultsHead"><h5 class="style18">Par Value</h5></td>
                              <td align="center" bgcolor="#FFFF99" class="resultsHead"><h5 class="style18">Restrict Ind</h5></td>
                              <td align="center" bgcolor="#FFFF99" class="resultsHead"><h5 class="style18">Shares Authorized</h5></td>
                              <td align="center" bgcolor="#FFFF99" class="resultsHead"><h5 class="style18">Date</h5></td>
                            </tr>
                            <tr>
                              <td valign="top" bgcolor="#FFFF99"><h5 align="left" class="style20"><%=stObj.getStockClass() %></h5></td>
                              <td valign="top" bgcolor="#FFFF99"><h5 align="left" class="style20"><%=stObj.getStockParValue() %></h5></td>
                              <td valign="top" bgcolor="#FFFF99"><h5 align="left" class="style20"><%=stObj.getStockRestrictInd() %></h5></td>
                              <td valign="top" bgcolor="#FFFF99"><h5 align="left" class="style20"><%=stObj.getStockShareAuth() %></h5></td>
                              <td valign="top" bgcolor="#FFFF99"><h5 align="left" class="style20"><%=stObj.getStockDate() %></h5></td>
                            </tr>
                        <% } else { %>
                        	<tr>
                              <td colspan="5" bgcolor="#FFFF99" class="resultsHead"><h4 align="left" class="style17">NO STOCK DATA</h4></td>
                            </tr>
                        <%} %>    
                        
                        </table></td>
                      </tr>
                  </table></td>
                </tr>
              </table>
               <p>&nbsp;</p> 
			    <% } } %>
			    
 <!-- Ends here -->
			  
              <p>&nbsp;</p>
             	<%@ include file="Disclaimer.jsp" %>
              <p>&nbsp;</p>
            </div>
        </div>
        <!--// masthead ends //-->
        


                        <!--// content begins //-->

<!-- CONTENT GOES HERE -->
	<%@ include file="BottomBanner.jsp" %>
<!--// content ends //-->

  </div>

    <!--// mastfoot begins //-->
    	<%@ include file="Footer.jsp" %>
    <!--// mastfoot ends //-->

    </center>
  </div>
  

</body>
</html>
