<%@ page language="java" import="springapp.web.findpeople.xmlParser.fpDetail" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 
boolean member=false;
String username=(String)session.getAttribute("username");
if(username != null)
{
	member=true;
}
  
  Hashtable fpResult = (Hashtable) session.getAttribute("fpResult"); 
  if(fpResult == null) {response.sendRedirect("/springapp/findpeople/searchLanding.do");  }
  else
   {
   int total = fpResult.size();
   session.setAttribute("totalResult", new Integer(total));
   Enumeration keys = fpResult.keys();
   String key ="";
   
   String selectedId =(String) session.getAttribute("key");
  
  
%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="springapp.web.findpeople.xmlParser.fpDetail"%>
<%@page import="springapp.web.findpeople.xmlParser.fpDetail"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Enumeration"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>find people details</title>
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
	margin-top: 25px;
	margin-right: 25px;
	margin-bottom: 0;
	margin-left: 0;
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
.style32 {
	font-family: Arial, Helvetica, sans-serif;
	color: #003366;
	padding: 3px;
}
.style35 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; }
.style39 {font-family: Arial, Helvetica, sans-serif; color: #003366; padding: 3px; font-weight: bold; }
.style40 {font-family: Arial, Helvetica, sans-serif; font-size: x-small; font-weight: bold; color: #006600; }
.style42 {
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	color: #003366;
}
.style43 {font-family: Arial, Helvetica, sans-serif; font-size: small; font-weight: bold; color: #006600; }
.style44 {font-family: Arial, Helvetica, sans-serif; font-size: small; }
-->
</style>
<script type="text/javascript"> 
	function getBGrequest(){
		document.BGrequest.action="resultsFPRecords.do?resultType=BG";
		document.BGrequest.submit();
	}
	function getFreeBG(){
		document.BGrequest.action="resultsFPRecords.do?resultType=FreeBgPage";
		document.BGrequest.submit();
	}
</script>
</head>
<body>
<table width="893" height="191%" align="center" style="height:100%">
<tr>
		<% if(!member) { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a></div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
    	<% } else { %>
    	<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a> | Welcome <%=username%><p>&nbsp;</p> [<a href="/springapp/findpeople/logout.do">Log Out</a>] </div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
   	 <% } %>
	</tr>
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;"><table width="893" height="511" border="0">
          <tr>
            <td width="896" height="507" valign="top"><table width="893" height="86" border="0">
<tr>
                    <td width="513" height="82"><p>&nbsp;</p>
                      <table width="451" align="center">
                      <tr>
                        <td><h1><span class="style32">Results</span></h1>
                        </td>
                      </tr>
                    </table> 
                    </td>
                    <td width="370"><div align="right">
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
             <table width="883" align="center">
                <tr>
                  <td height="83"><p><span class="style42">Here are the result details you requested. </span></p>
                  <p>&nbsp;</p>
                  <p><span class="style42">Need more information? Scroll down for instructions on how to order  a detailed Background Report</span>.</p></td>
                </tr>
              </table>
			  <form:form method="post" name="BGrequest">			  
              <table width="893" height="132" border="0">
                <tr>
                    <td width="813" height="128"><table width="887" border="0" bordercolor="#999999">
                        <tr>
                          <td width="27" height="24">&nbsp;</td>
                          <td width="140"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Name</strong></span></div>
                          </div></td>
                          <td width="119"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Date Reported</strong></span></div>
                          </div></td>
                          <td width="183"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Address</strong></span></div>
                          </div></td>
                          <td width="183"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Location</strong></span></div>
                          </div></td>
                          <td width="100"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Phone</strong></span></div>
                          </div></td>
                          <td width="105"><div align="center" class="style27">
                            <div align="left"><span class="style23"><strong>Date of Birth</strong></span></div>
                          </div></td>
                      </tr>
                      
               	<%   fpDetail fp=(fpDetail)fpResult.get(selectedId);
               		 if(fp != null){
                %>
                <tr>
                          <td><div align="left">
                              <label>
                              <input type="radio" name="key" id="radio" value="<%=selectedId%>" checked="checked"/>
                              </label>
                          </div></td>
                          <td><div align="center" class="style43">
                              
                            <p align="left"><%=fp.getFirstName()%> <%=fp.getMiddleName()%> <%=fp.getLastName()%></p>
                          </div></td>
                <td><div align="center" class="style43">
                              <p align="left"><%=fp.getValidityDate() %></p>
                          </div></td>
                <td><div align="center" class="style43">
                              <p align="left"><%=fp.getAddress()%></p>
                          </div></td>
                          <td><div align="center" class="style43">
                              
                            <p align="left"><%=fp.getCity()%>, <%=fp.getState()%></p>
                          </div></td>
                          <td><div align="center" class="style43">
                            <p align="left"><%=fp.getPhone()%></p>
                          </div></td>
                          <td><div align="center" class="style43">
                            <p align="left"><%=fp.getDOB()%></p>
                          </div></td>
                      </tr>
                 <% }
                 	while(keys.hasMoreElements())
					{
						key=(String)keys.nextElement();
						if(selectedId.equalsIgnoreCase(key))
							continue;
						fp=(fpDetail)fpResult.get(key);
						if(fp!=null)
						{
				%>   
                        <tr>
                          <td><div align="left">
                              <label>
                              <input type="radio" name="key" id="radio" value="<%=key%>"/>
                              </label>
                          </div></td>
                          <td><div align="center" class="style44">
                              
                            <p align="left"><%=fp.getFirstName()%> <%=fp.getMiddleName()%> <%=fp.getLastName()%></p>
                          </div></td>
                <td><div align="center" class="style44">
                              <p align="left"><%=fp.getValidityDate() %></p>
                          </div></td>
                <td><div align="center" class="style44">
                              <p align="left"><%=fp.getAddress()%></p>
                          </div></td>
                          <td><div align="center" class="style44">
                              
                            <p align="left"><%=fp.getCity()%>, <%=fp.getState()%></p>
                          </div></td>
                          <td><div align="center" class="style44">
                            <p align="left"><%=fp.getPhone()%></p>
                          </div></td>
                          <td><div align="center" class="style44">
                            <p align="left"><%=fp.getDOB()%></p>
                          </div></td>
                      </tr>
                 <% } }  %>      
                      </table>
                  <p>&nbsp;</p></td>
                </tr>
                </table>
      </form:form>          
              <table width="844" border="2" align="center" bordercolor="#000000">
                <tr>
                  <td width="840" height="221"><h4 class="style32">Need more information? Select a record and click on a button below to do a background search.</h4>
                    <h4 class="style32"><span class="style32">Important! For best results, select a record with a full date of birth.</span></h4>
                    <h4 class="style32">Background Report  includes:</h4>
                    <table width="769" align="center">
                      <tr>
                        <td width="479" height="156">
                        
                               <img src="/springapp/images/findpeople/BG-bullets.png" alt="background bullets" width="461" height="152">                          
                        </td>
                        <td width="278"><p>&nbsp;</p>
						<% if(!member) { %>
                        <p><a href="#" onClick="getBGrequest(); return false;"><img src="/springapp/images/findpeople/bt-background-1995.png" alt="background 1995" width="276" height="50" align="middle"></a></p>
                 
                        <p><a href="#" onClick="getFreeBG(); return false;"><img src="/springapp/images/findpeople/bt-background-free.png" alt="free background" width="276" height="50"></a></p>
                  <% } else {%>    
				  <p><a href="#" onClick="getBGrequest(); return false;"><img src="/springapp/images/findpeople/bt-background-1495.png" alt="background 1995" width="276" height="50" align="middle"></a></p>
				  <% } %>  
				  <p>&nbsp;</p>
				  <p align="center" class="style27"><a href="showStaticPages.do?page=BGsamplereport" target="_blank">View sample Background Report</a></p>
				  <p>&nbsp;</p>
				 	 </td>
                      </tr>
                    </table>
                  
                  <p>&nbsp;</p></td>
                </tr>
              </table>
              <p>&nbsp;</p>
   
              </td>
          </tr>
        </table>
          <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="816" border="0" align="center" bgcolor="#FEF8A5">
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