
<%@ page language="java" import="springapp.web.findpeople.xmlParser.fpDetail" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<% 
boolean member=false;
String username=(String)session.getAttribute("username");
String BGlanding=(String)session.getAttribute("BGlanding");
if(username != null){
	member=true;
}
if(BGlanding == null )
{
	response.sendRedirect("/springapp/findpeople/searchLanding.do");
}
   Hashtable teaserResult = (Hashtable) session.getAttribute("teaserResult");
   
   if(teaserResult == null){ response.sendRedirect("/springapp/findpeople/searchLanding.do");   }
   else
   {
 	   	Enumeration keys = teaserResult.keys();
   		String key ="";
				

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Enumeration"%>
<%@page import="springapp.web.findpeople.xmlParser.fpDetail"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>background - names found</title>
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
.style34 {
	color: #003366;
	font-family: Tahoma;
	font-size: small;
}
.style35 {
	font-size: small;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}
.style36 {
	font-family: Tahoma;
	color: #003366;
}
-->
</style>

<script language="JavaScript" src="https://secure.comodo.net/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>

<script language="javascript" type="text/javascript">
function validateField()
{
	var flag = true;
	
	if(document.BGform.key.value == null){
		flag = false;
	}
	for(var i=0; i<document.BGform.key.length; i++){
		if(document.BGform.key[i].checked){
			flag = true;
		}
	}
	if(flag){
		document.BGform.action="resultsFPRecords.do?resultType=BG";
		document.BGform.submit();
	}
	else{
		document.getElementById('errorMsg').style.visibility = "visible";
		return flag;
	}
	
}

</script>

</head>
<body>
<table width="893" height="191%" align="center" style="height:100%">
<tr>
<% if(member) { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a> | Welcome <%=username%><p>&nbsp;</p> [<a href="/springapp/findpeople/logout.do">Log Out</a>] </div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
		<% } else { %>
		<td width="893" style="height:70px; ">
        	<div class="menu"><a href="https://www.searchsystems.net">Search Systems Home</a> | <a href="/springapp/findpeople/searchLanding.do">Find People</a> | <a href="https://www.searchsystems.net/springapp/legal/contactUs.do" target="_blank">Contact Us</a> </div>
    		<a href="https://www.searchsystems.net"><img src="/springapp/images/findpeople/logo_02.gif" alt="searchsystems" border="0"/></a>
    	</td>
		<% } %>
	</tr>
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;">
        <p>&nbsp;</p>
        <table width="893" height="263">
          <tr>
            <td><table width="966" height="114" border="0">
              <tr>
                <td width="582" height="110"><h1 align="left" class="style32">Background Report - Names Found</h1>
                    <h4 align="left" class="style34">Please select one name,  then click the button below to purchase a Background Report for that person. <a href="showStaticPages.do?page=BGsamplereport" target="_blank">View sample Background Report</a>.<br/>
                  <br/>If you are unsure of which name to choose from the list below, try our <a href="searchLanding.do?resultType=NoBGlanding">Find People</a> search first. For <% if(member) {%>$1.00 <% } else { %>$1.75 <% } %>you will get all the names, addresses, birth dates, phone numbers and dates reported. You can then proceed from there with your background report.</h4>
                </td>
                <td width="353"><div align="right">
                    <form:form method="post" action="/springapp/findpeople/searchLanding.do">
                    <table width="351" border="0">
                      <tr>
                        <td width="124"><div align="left" class="style35">First Name <span class="style24">*</span></div></td>
                        <td width="100"><div align="left" class="style35">Last Name <span class="style24">*</span></div></td>
                        <td width="147"><div align="left" class="style35">State</div></td>
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
            <form:form method="post" name="BGform">
              <table width="893" border="0">
                <tr>
                  <td width="813">
                  
                  
                  <table width="966" border="2" bordercolor="#999999">
              <tr>
                        <td width="55" height="24">&nbsp;</td>
                      <td width="189"><div align="left" class="style27"><span class="style23"><strong>Name</strong></span></div></td>
                      <td width="48"><div align="left" class="style27"><span class="style23"><strong>Age</strong></span></div></td>
                      <td width="171"><div align="left" class="style27"><span class="style23"><strong>Location</strong></span></div></td>
                      <td width="68"><div align="center" class="style27"><span class="style23"><strong>Address</strong></span></div></td>
                      <td width="67"><div align="center" class="style27"><span class="style23"><strong>Map</strong></span></div></td>
                      <td width="61"><div align="center" class="style27"><span class="style23"><strong>Phone</strong></span></div></td>
                      <td width="105"><div align="center" class="style27"><span class="style23"><strong>Date of Birth</strong></span></div></td>
                      <td width="142"><div align="center" class="style27"><span class="style23"><strong>Date Reported</strong></span></div></td>
                    </tr>
                    
                     <%     
                    while(keys.hasMoreElements())
				{
					key=(String)keys.nextElement();
					fpDetail fp=(fpDetail)teaserResult.get(key);
										
					if(fp!=null)
					{
				%>   
				
                      <tr>
                        <td><div align="left">
                        <input type="radio" name="key" value="<%=fp.getPersonID() %>"> </div></td>

                        <td><div align="left" class="style27">
                          <div align="left">
                            <p class="style23"><%=fp.getFirstName()%> <%=fp.getMiddleName()%> <%=fp.getLastName()%></p>
                          </div>
                        </div></td>
                        <td><div align="left" class="style27">
                          <div align="left">
                            <p class="style23"><% if(fp.getAge() != null) {%>
                              <%=fp.getAge() %>
                              <% } %></p>
                          </div>
                        </div></td>
                        <td><div align="left" class="style27">
                          <div align="left">
                            <p class="style23"><%=fp.getCity()%>, <%=fp.getState() %></p>
                          </div>
                        </div></td>
                        <td><div align="center"><% if(fp.getAddress() != "") { %> <img src="/springapp/images/findpeople/checkmark-green.jpg" alt="checkmark" width="29" height="32"> <% } %></div></td>
                        <td><div align="center"><% if(fp.getAddress() != "") { %><img src="/springapp/images/findpeople/checkmark-green.jpg" alt="checkmark" width="29" height="32"><% } %></div></td>
                        <td><div align="center"><% if(fp.getPhone() != "") { %><img src="/springapp/images/findpeople/checkmark-green.jpg" alt="checkmark" width="29" height="32"><% } %></div></td>
                        <td><div align="center"><% if(fp.getDOB() != "") { %><img src="/springapp/images/findpeople/checkmark-green.jpg" alt="checkmark" width="29" height="32"><% } %></div></td>
                        <td><div align="center" class="style27">
                          <p class="style23"><%=fp.getValidityDate()%></p>
                          </div></td>
                    </tr>
					<tr><td> </td><td> </td> <td> </td><td> </td><td> </td><td> </td><td> </td><td> </td><td> </td></tr>
                 <% } } %>
                 </table>
                 
                 </td>
                </tr>
              </table>
              </form:form>
              </td>
          </tr>
        </table>
        <p align="center"> <% if(member) {%><img src="/springapp/images/findpeople/bt-background-1495.png" alt="14.95" width="276" height="50" style="cursor:pointer" onClick="validateField();"><% } else { %><img src="/springapp/images/findpeople/bt-background-1995.png" alt="19.95" width="276" height="50" style="cursor:pointer" onClick="validateField();"> <% } %></p>
        
        <p align="center" id="errorMsg" style="font:Arial, Helvetica, sans-serif;color:#FF0000;visibility:hidden"><strong>Please select one name via the radio button </strong> </p>
         <table width="862" border="0" align="center">
          <tr>
            <td width="659" class="content_1" style="padding:0 30px;">
            <h3><span class="style27"><strong>SearchSystems.net</strong> </span> </h3>
            <ul>
              <li class="style27"> Public Records research since 1990.</li>
              <li class="style27"> Online since 1996.</li>
              <li class="style27"> Largest public records resource on the net.</li>
              <li class="style27"> Only public records site rated A+ by the Better Business Bureau.</li>
            </ul></td>
            <td width="193" class="content_1" style="padding:0 30px;">
              <p align="center"><a href="http://www.instantssl.com" id="comodoTL">SSL</a>
<script type="text/javascript">TrustLogo("https://www.searchsystems.net/springapp/images/findpeople/Comodo-seal-100.gif", "SC", "none");</script>
</p>
            <p align="center"><a href="${bbbUrl}"><img src="/springapp/images/findpeople/bbb-clickratingsm.gif" alt="bbb" width="135" height="52"></a></p></td>
          </tr>
        </table>
        <table width="858" height="98" border="0" align="center" bgcolor="#FEF8A5">
          <tr>
            <td width="926" height="94"><p>&nbsp;</p>
            <table width="815" border="0" align="center" bgcolor="#FEF8A5">
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
		<td width="895" class="footer" style="height:56px; background:url(/springapp/images/findpeople/bg_footer_11.jpg) top no-repeat; padding:15px 0 0 60px;">
		  <p>Copyright &copy; 2009 Pacific Information Resources, Inc. All rights reserved.</p>
			<p><a href="http://www.searchsystems.net/tos.php" target="_blank">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php" target="_blank">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php" target="_blank">About Us</a></p></td>
  </tr>
</table>
</body>
</html>
<% } %>