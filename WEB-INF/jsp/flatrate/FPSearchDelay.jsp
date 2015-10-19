<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>please wait</title>
<link type="text/css" rel="stylesheet" href="/springapp/css/flatrate.css">

<script language="javascript" type="text/javascript">
function sendToResult()
{
	setTimeout('document.images["delayGif"].src = "/springapp/images/flatrate/blue-animated-search.gif"', 200);
	setTimeout('window.status = "Waiting for www.searchsystems.net..."', 200); 
	document.dummy.submit();
	setTimeout('document.images["delayGif"].src = "/springapp/images/flatrate/blue-animated-search.gif"', 200);
}
</script>



</head>
<body onLoad="sendToResult();">

<form method="post" action="FPResults.do" name="dummy">
    	<input type="hidden" name="searchFirstName" value="${sfpfc.searchFirstName}">
    	<input type="hidden" name="searchMidName" value="${sfpfc.searchMidName}">
    	<input type="hidden" name="searchLastName" value="${sfpfc.searchLastName}">
    	<input type="hidden" name="searchDob" value="${sfpfc.searchDob}">
    	<input type="hidden" name="searchCity" value="${sfpfc.searchCity}">
    	<input type="hidden" name="searchState" value="${sfpfc.searchState}">
</form>
    
<table width="893" height="191%" align="center" style="height:100%">
<tr>
		<td width="893" style="height:70px; ">
        	<div class="menu"></div>
    <img src="/springapp/images/flatrate/logo_02.gif" alt="" /></td>
	</tr>
	<tr>
		<td width="893" style="height:215px; background:url(/springapp/images/flatrate/tal_header_03.jpg) top repeat-y;">
			<table width="893" style="height:215px; background:url(/springapp/images/flatrate/bg_header_top_07.jpg) top no-repeat;">
				<tr>
					<td width="893" class="content_1" style="background:url(/springapp/images/flatrate/bg_fpheader_08.jpg) bottom no-repeat; padding:44px 565px 16px 60px;">
                      <p class="style28">&nbsp;</p>
                      <p class="style28">Searching the database...</p>
                  <p class="style28">Please wait.<strong></strong> Do not refresh or click your browser's Back button.</p></td>
			  </tr>
			</table>
        </td>
	</tr>
	<tr>
		<td width="893" style="height:207px; background:url(/springapp/images/flatrate/bg_content_02.jpg) top no-repeat; padding:18px 7px 0 20px;">
			<div class="side_right">
				<div class="fill_bot">
					<div class="fill_top">
						<div class="left_top">
							<div class="left_bot">
								<div class="right_top">
									<div class="right_bot">
		  <table width="862" height="267" style="height:207px;">
<tr>
												<td colspan="2" style="padding:20px 30px 20px 30px;" class="content_1"><table width="718" border="0" align="center">
                                                  <tr>
                                                    <td width="868" height="79"><p>&nbsp;</p>
                                                        <p align="center"><img id="delayGif" src="/springapp/images/flatrate/blue-animated-search.gif" alt="searching" width="190" height="39"></p>
                                                    <h4 align="center"><strong>Your results will be here momentarily.</strong> </h4>
                                                    <h4 align="center"><span class="style34b">Please do not refresh this screen or click your browser's Back button.</span></h4></td>
                                                  </tr>
                                                </table>												<p>&nbsp;</p>                        </td>
</tr>
									  </table>
								  </div>
							  </div>
							</div>
						</div>
					</div>
				</div>
	  </div></td>
	</tr>
	<tr>
		<td width="893" style="height:100%; padding: 10px 0;">&nbsp;</td>
  </tr>
  
  <tr>
		<td width="893" class="footer" style="height:56px; background:url(images/bg_footer_11.jpg) top no-repeat; padding:15px 0 0 60px;">
			<p>Copyright &copy; 2009 Pacific Information Resources, Inc. All rights reserved.</p>
			<p><a href="#">Terms of Use</a> | <a href="http://premium.searchsystems.net/privacy.php">Privacy Policy</a> | <a href="http://www.searchsystems.net/about.php">About Us</a></p></td>
	</tr>
</table>
</body>
</html>
  
  