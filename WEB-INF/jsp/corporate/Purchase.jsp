<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Search Systems - Value Proposition Page</title>

<meta name="description" content="description here" />
<meta name="keywords" content="keywords here" />
<meta http-equiv="content-language" content="en" />

<script language="JavaScript" src="http://www.trustlogo.com/trustlogo/javascript/trustlogo.js" type="text/javascript">
</script>
<link rel="stylesheet" title="Disabled for Preview-in-Browser: /springapp/css/common.css" type="text/css" media="screen" />
<style type="text/css" media="screen">
<!--


/* CSS Document */
body {background:#ddd url(/springapp/images/common/bg-body.gif) repeat-x; padding:0; margin:0;}

h1,h2,h3,h4,h5,h6 {font-family:Tahoma, Arial, Helvetica, sans-serif; font-weight:normal;}
p,blockquote,cite,li,dt,dd,label,span,input,textarea,option,td {font-family:Arial, Helvetica, sans-serif; font-weight:normal;}
code {font-family:Courier New, Courier, monospace; font-weight:normal;}

h1 {font-size:;}

h2 {font-size:;}

h3 {font-size:1.3em; color:#036; margin:0.5em 0;}

	#formwrapper h3 {margin:0;}

h4 {font-size:;}

h5 {font-size:;}

h6 {font-size:;}

p {font-size:0.9em; line-height:1.444em; margin:0.5em 0;}

	#colone p {color:#036;}
	#colone .twocol p {margin:0.5em 1em;}
	
	#formwrapper p {color:#666; margin:0;}
	
	p.note {float:right;}

	#testimonials p {font-size:0.8em; color:#333; margin:0.35em 0;}
	#testimonials p.credit {font-size:0.75em; color:#888; font-style:italic; text-align:right;}

blockquote {font-size:;}

/* form errors */
#error { color: #ff0000; padding: 10px 50px; }
#error div { background-color: #eee; border:1px dashed #bbb;}
#error .error-box { padding: 5px; }

li {font-size:0.9em;}

	#colone .twocol li {font-size:1em; background:url(/springapp/images/common/bullet-yellow-check.gif) no-repeat center left; padding:6px 2px 6px 37px; list-style-type:none; display:block;}
	#options li {list-style-type:none;}
	
	#formwrapper li {height:25px; color:#2c4669; padding-bottom:3px; list-style-type:none; display:block;}
	#formwrapper #submit li {height:auto; margin:0em 0.5em; float:left;}
	#formwrapper li.checkbox {font-size:0.7em;}

ul {}

	.twocol ul {padding:0; margin:0;}
	#options ul {padding:0 15px 20px 40px; margin:0; display:block;}
	ul.ccdetails {background:url(/springapp/images/common/bg-credit-card-info.gif) no-repeat top right; display:block;}

dt {}

dd {}

a {}
	a img {border:none;}
	a:hover {text-decoration:none;}

	#colone a {color:#4c7baa;}
	
	#coltwo a {color:#369;}

hr {}

label {font-size:0.9em;}

	#options label strong {color:#c60;}
	
	#formwrapper li label {width:155px; text-align:right; padding:2px 2px; display:block; float:left;}

span {}

	span.req {font-size:1.2em; color:#c30;}

strong {}
	
	.checkbox strong {color:#c63;}

/*form elements*/
form {padding:0; margin:0;}
	
	input {font-size:0.9em; padding:2px;}
		input.formfield, select.formfield {background:#fafafa url(/springapp/images/common/bg-form-fields.gif) repeat-x; border:1px solid #d6d6d6;}


/*site body*/
#owrap {width:950px;}
	#cwrap {}
		#iwrap {}
		
		#masthead {height:51px; text-align:left;}
		
		#maintable {background:#fff;}
		
			#colone {width:685px; background:url(/springapp/images/common/bg-col-one.jpg) no-repeat top left;}
			
				#colone .wrap {background:url(/springapp/images/common/bg-col-one-wrap.jpg) no-repeat bottom right; }
				#colone .wrap .innerwrap {background:url(/springapp/images/common/divider-3px-horizontal.gif) repeat-x bottom; padding:25px;}
				
				#options {background:url(/springapp/images/common/divider-3px-horizontal.gif) repeat-x bottom; padding:1px;}
					#options .wrapper {background:url(/springapp/images/common/bg-options.gif) no-repeat bottom; }
				
				.buttons {padding:10px 30px; clear:left;}
				
				#formwrapper {padding:10px 50px;}
				
				.arrow {background:url(/springapp/images/common/bg-twocol-arrow.gif) no-repeat center bottom; padding-bottom:70px;}
				
				#security {background:url(/springapp/images/common/divider-3px-horizontal.gif) repeat-x bottom; padding:1px;}
			
				#colone .divider {height:1px; font-size:0.1em; background:url(/springapp/images/common/divider-3px-horizontal.gif) repeat-x top;}
			
			#coltwo {width:265px; background:url(/springapp/images/common/divider-3px-vertical.gif) repeat-y left;}
			
				#photo {background:url(/springapp/images/common/divider-3px-horizontal.gif) repeat-x bottom; text-align:center; padding:1px;}
				
				#testimonials {background:url(/springapp/images/common/bg-testimonials.gif) no-repeat top right; padding:78px 25px 30px 25px;}
	

/* two columns */
.twocol {width:49.9%; float:left; display:block; padding:10px 0;}
	.buttons .twocol {text-align:center; padding:5px 0;}
	
/* borders */
.rightborder {background:url(/springapp/images/common/divider-3px-vertical.gif) repeat-y right;}

/*clearing elements*/
.cleft {clear:left;}
.cright {clear:both;}
.cboth {clear:both;}

/*common navigation*/
#mainnav li, #footer li {list-style-type:none;}
#mainnav ul, #footer li {list-style:none; padding:0; margin:0;}

/*main navigation*/
#mainnav {}
	#mainnav #nav {}
	#mainnav ul {}
	#mainnav li {}
	
/*drop down navigation - please refer to ddnav.js for script */
	#nav li ul {top:1em; left:0; position:absolute; display:none;}
	#nav li > ul {top:auto; left:auto;}
	#nav li:hover ul, #nav li.over ul {display:block;}	

/*footer navigation*/
#footer {background:url(/springapp/images/common/bg-footer.gif) repeat-x top; padding:15px 0;}
	#footer .wrap {width:920px; text-align:left;}
	#footer ul.security {padding:0; margin:0;}
	#footer ul {padding:0; margin:0.3em 0;}
		#footer ul.secondary {padding:0; margin:0;}
	#footer li {font-size:0.75em; color:#777; display:inline;}
	#footer .security li {display:block; float:right;}
	#footer a {color:#777;}
	
	


-->
</style>
<!--[if IE]><link href="css/ieonly.css" rel="stylesheet" type="text/css" media="screen" /><![endif]-->
<link rel="stylesheet" title="Disabled for Preview-in-Browser: css/print.css" type="text/css" media="print" />
<style type="text/css" media="print">
<!--



-->
</style>

<script type="text/JavaScript" src="javascript/common.js"></script>


</head>

<body>
<form:form method="post">
<center>
  <div id="owrap">

    <!--// masthead begins //-->
    <p align="left">
      <img src="/springapp/images/common/updated-logo.png" width="293" height="40" />
	</p>
    <!--// masthead ends //-->
    
    <!--// main content area begins //-->
    <table cellpadding="0" cellspacing="0" border="0" width="950" id="maintable"><tr valign="top">
    		
        <!--// column one begins //--> 
          <td id="colone" align="left"><div class="wrap"><div class="innerwrap">
            
           	  <div id="title"><img src="/springapp/images/common/billing-info-search.gif" alt="Billing Information! Sign Up Today! It's Easy, Safe and Secure" /></div>
              
              <div id="formwrapper">
              
              	<p>Please make sure that your name and address are the same as on the mailing address on your credit card statement.  The SearchSystems.net website protects your billing information and personal information through secure communications provided by Thawte SSL Certified encryption. <span class="req"><strong>*</strong></span> Required Fields</p>
             
	<form:errors path="*">
	<b>Please correct the following errors:</b><br/>
	</form:errors>
	<form:errors path="*" element="div" id="error" cssClass="error-box"/>
		<ul class="ccdetails">
			<c:url var="purchaseUrlIntl" value="purchaseCorporateRecordSearch.do?i18n=1"/>
			<c:if test="${!command.i18n}">
				<li>
				<strong><a href="${purchaseUrlIntl}">Outside the US? Click here</a></strong>
				</li>
			</c:if>             	
               	  	<li><label><span class="req"><strong>*</strong></span>Card Holder Name:</label> <form:input path="name" cssClass="formfield" size="30" /></li>
					<li><label><span class="req"><strong>*</strong></span>Credit Card Number:</label><form:input path="ccNumber" cssClass="formfield" size="30" /></li>
					<li><label><span class="req"><strong>*</strong></span>Authorization Code:</label> <form:input path="ccAuthCode" cssClass="formfield" size="10" /></li>
					<li><font size="-2">The Authorization Code for Visa and MasterCard is the 3 digit number printed on the back <br />of your credit card. On American Express cards it is the four numbers printed on the front.</font></li>
					<li><font size="-2">&nbsp;</font></li>
					<li><label><span class="req"><strong>*</strong></span>Expiration Date:</label> 
                      <form:select path="ccExpMonth" cssClass="formfield">
						<form:option value="-">Month</form:option>
						<form:options items="${cardExpirationMonths}"/>
                      </form:select>
                        &nbsp; 
                      <form:select path="ccExpYear" cssClass="formfield">
						<form:option value="-">Year</form:option>
						<form:options items="${cardExpirationYears}"/>
                      </form:select>
                    </li>
                </ul>
                <div class="divider"></div>
				<ul>
                	<li><label>Company Name:</label><form:input path="company" cssClass="formfield" size="40" /></li>
                    <c:choose>
					<c:when test="${command.i18n}">
					<li><label><span class="req"><strong>*</strong></span>Billing Address Line 1:</label> <form:input path="address" cssClass="formfield" size="40" /></li>
                    <li><label><span class="req"></span>Billing Address Line 2:</label> <form:input path="address2" cssClass="formfield" size="40" /></li>
				    </c:when>
					<c:otherwise>
						<li><label><span class="req"><strong>*</strong></span>Billing Address:</label> <form:input path="address" cssClass="formfield" size="40" /></li>
					</c:otherwise>
					</c:choose>
                	<li><label><span class="req"><strong>*</strong></span>Billing City:</label> <form:input path="city" cssClass="formfield" size="40" /></li>
					<c:choose>
					<c:when test="${command.i18n}">
					<li><label>Province/State:</label> <form:input path="state" cssClass="formfield" size="12" /> (optional)</li>
					<li><label>Postal Code:</label> <form:input path="postalCode" cssClass="formfield" size="12" /> (optional)</li>
					<li><label><span class="req"><strong>*</strong></span>Country Code:</label> <form:input path="countryCode" cssClass="formfield" size="2" /> (2 letters)</li>
					</c:when>
					<c:otherwise>
					<form:hidden path="countryCode" />
					<li><label><span class="req"><strong>*</strong></span>Billing State:</label> 
						<form:select path="state" cssClass="formfield">
							<form:option value="-">Select</form:option>
							<form:options items="${usStates}"/>
						</form:select>
					</li>
					<li><label><span class="req"><strong>*</strong></span>Zip Code:</label> <form:input path="postalCode" cssClass="formfield" size="12" /></li>
					</c:otherwise>
					</c:choose>
					<li><label><span class="req"><strong>*</strong></span>Phone:</label> <form:input path="phone" cssClass="formfield" size="26" /></li>
					<li><div align="left" class="small"><div align="center">
(Area code first. No parentheses or dashes, ex: 2135550010)
</div></div></li>
                    <li><label><span class="req"><strong>*</strong></span>Email:</label> <form:input path="email" cssClass="formfield" size="26" /></li>
					<li><label><span class="req"><strong>*</strong></span>Confirm E-mail:</label> <form:input path="confirmEmail" cssClass="formfield" size="26" /></li>
                    <li class="checkbox"><label></label><form:checkbox path="receiveNewsletter" cssClass="formfield" /> <strong>Yes</strong> I would like to receive the Search Systems Newsletter!</li>
                    <li class="checkbox"><label></label><form:checkbox path="acceptAgreement" cssClass="formfield" /> I have read &amp; agree to the <a href="${showAgreementUrl}?version=mexicanfunnel" target="_blank">User Agreement</a></li>
                </ul>
                
                <ul id="submit">
                	<li><a onClick="${thawteOnClick}" href="#" title="Thawte, It's a trust thing"><img src="/springapp/images/common/col-one-logo-thawte.gif" alt="Thawte, It's a trust thing" /></a></li>
                	<li><a href="${bbbUrl}" target="_blank" title="Better Business Bureau"><img src="/springapp/images/common/col-one-logo-bbb.gif" alt="Better Business Bureau" /></a></li>
                    <li><input type="image" src="/springapp/images/common/bt-make-your-secure-payment.gif" alt="Proceed to Payment Form" /></li>
				</ul>
                
                <div class="cleft"></div>
              </div>
            
          </div></div></td>
        <!--// column one ends //--> 
        
        <!--// column two begins //-->     
          <td id="coltwo" align="left"><div class="wrap">
          
          		<div id="security"><img src="/springapp/images/common/icons-security.gif" alt="Thawte, BBB, Hacker Safe" border="0" /></div>
                
                <!--<map name="Map" id="Map">
                    <area shape="rect" coords="16,25,154,71" href="#" alt="Thawte" />
                    <area shape="rect" coords="162,20,218,77" href="#" alt="BBB" />
                    <area shape="rect" coords="72,99,184,162" href="#" alt="Hacker Safe" />
                </map>-->
                                
              <div id="photo"><img src="/springapp/images/common/image-male-searching.jpg" alt="Search Systems" /></div>
                
              <div id="testimonials">
                
               	  <p>"An excellent resource for me, and your customer service is excellent"</p>
               	  <p class="credit">- Kris W.</p>
                  
               	  <p>"I found a Florida based company, inactive since 2002, the registered agent name & address (and his phone number was easy to locate), and the specific names and addresses of the directors in Russia...Outstanding service and friendly help - what more could I ask?  Best Wishes"</p>
               	  <p class="credit">- A.</p>                  
              </div>
                
          </div></td>
        <!--// column two begins //--> 
    
   	  </tr></table>
    <!--// main content area ends //-->

  </div>
   
<!--// footer begins //-->
<%@include file="Footer.jsp" %>
<!--// footer ends //-->
	
</center>
</form:form>
</body>
</html>
