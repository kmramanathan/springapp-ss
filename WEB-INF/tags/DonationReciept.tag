<%@tag description="page layout" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="head" fragment="true"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="javascript" required="false" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" type="java.lang.String"%>
<%@attribute name="load" required="false" type="java.lang.String"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Search Systems Donation</title>
<style type="text/css">
	<c:forTokens var="item" items="${stylesheet}" delims=",">
    @import url("/springapp/css/${item}");
	</c:forTokens>    
</style>
 
<SCRIPT type="text/javascript">
	window.history.forward();
	function noBack() { window.history.forward(); }
</SCRIPT>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

<center>
  <div id="owrap">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
	<td rowspan="2" valign="middle">
    <a href="http://publicrecords.searchsystems.net/"><img src="images/common/updated-logo.png" width="293" height="40" /></a>
    </td>
   
 </tr>
 <tr><td colspan="4">&nbsp;</td> </tr>
 <tr><td colspan="4">&nbsp;</td> </tr>
 </table>
 <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> 
        <td id="colone" align="left"><table width="866" align="center">
          
            
            
            
            <jsp:doBody />
              
              
              
              
            </table>
        </td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
        <!--// column two begins //--> 
    
   	  </tr></table>
  </div>
   
<!--// footer begins //-->
<div style="clear: both; height: 30px;"></div></div><div class="footer style19h style20h">Copyright &copy; 1997-2012 Search Systems, Inc. All rights reserved.</div>

<!--// footer ends //-->
	
</center></body>
</html>
 
