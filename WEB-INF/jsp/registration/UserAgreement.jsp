<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<title>Search Systems DirectPass Registration</title>
<link rel="stylesheet" type="text/css" href="css/searchsystems.css" />
<link rel="stylesheet" type="text/css" href="css/registration.css"  />
</head>

<body>

<div id="page">

<%@ include file="top.jsp" %>

<div id="content">

<h1>SearchSystems.net DirectPass Registration</h1>

<h3>Step 1 of 3</h3>

<div id="content-main">

<div class="blurb">
    <b>
    Become a SearchSystems.net DirectPass &trade; subscriber for $48.50 US per year or 
    sign up for a monthly subscription for only $4.95!</b>  
    Access the first and best directory of public records on the Internet including property 
    databases, corporations, professional licenses, court and criminal records, inmates, limited 
    partnerships, offenders, inmates, most wanted, births, deaths, marriages, divorces, and much more!

    <ul>
        <li style="padding-bottom: 5px;">
        <b>Over 36,000 hand-picked public record databases!</b> 
        We're continually adding and updating our links.  Plus we've written a brief description 
        of every link that includes what to expect and search tips.
        </li>
        
        <li style="padding-bottom: 5px;"><b>Premium Service Discounts!</b> 
        Save up to $10.00 per search on our Premium criminal record, bankruptcy, judgment, 
        and tax lien database searches.
        </li>
    </ul>
</div>
    	    	
<div class="comparison">
<table>
    <tr>
        <th>&nbsp;</th>
        <th><b>Non-Subscriber</b></th>
        <th><b>DirectPass</b></th>
    </tr>
    <tr>
        <td><b>Description of each database</b></td>
        <td align="center"><img src="img/check.gif" width="20" height="20" alt="yes" /></td>
        <td align="center"><img src="img/check.gif" width="20" height="20" alt="yes" /></td>
    </tr>
    <tr>
        <td><b>Search the site using the Public Record Locator</b></td>
        <td align="center"><img src="img/check.gif" width="20" height="20" alt="yes" /></td>
        <td align="center"><img src="img/check.gif" width="20" height="20" alt="yes" /></td>
    </tr>
    <tr>
        <td><b>Access over 36,000 public record databases</b></td>
        <td align="center"><img src="img/invis.gif" width="20" height="20" alt="no"  /></td>
        <td align="center"><img src="img/check.gif" width="20" height="20" alt="yes" /></td>
    </tr>
    <tr>
        <td><b>No banner or Google ads</b></td>
        <td align="center"><img src="img/invis.gif" width="20" height="20" alt="no"  /></td>
        <td align="center"><img src="img/check.gif" width="20" height="20" alt="yes" /></td>
    </tr>
    <tr>
        <td><b>Access Search Systems Premium</b></td>
        <td align="center"><img src="img/invis.gif" width="20" height="20" alt="no"  /></td>
        <td align="center"><img src="img/check.gif" width="20" height="20" alt="yes" /></td>
    </tr>
    <tr>
        <td><b>Receive discounts on our Premium databases</b></td>
        <td align="center"><img src="img/invis.gif" width="20" height="20" alt="no"  /></td>
        <td align="center"><img src="img/check.gif" width="20" height="20" alt="yes" /></td>
    </tr>
</table>
</div>

<div class="picture-right">        
    <img src="img/directpass-small.jpg" width="187" height="107" alt="DirectPass" />
    <img src="img/invis.gif" width="245" height="25" alt=""/>
    <img src="img/woman.jpg" width="245" height="269" alt=""/>
</div>

<!-- end content-main div -->
</div>

<div id="content-form">
    <form method="post" action="registration.do">
        <table cellspacing="0" border="0">
            <tr>
                <th>User Agreement</th>
            </tr>
            <tr>
                <td style="padding: 1em; align: center">
                    (<a href="agreement.php">Printer-friendly version</a>)<br/>

<div style="text-align: justify; width: auto; height: 300px; overflow: scroll; background-color: #FFF; padding: 6px; 
border: #000 1px solid; margin-top: .5em">
<%@ include file="agreement-text.html" %>
</div>
                </td>
            </tr>
            <tr>
                <td class="first">
                    <strong>Enter agree if you agree with our User Agreement.</strong> <input type="text" name="agree" size="6" maxlength="5" />
                    <br/><br/>
                    <div class="text">
                        <input type="radio" class="radio" name="isus" value="y" /> My address is within the United States.<br/>
                        <input type="radio" class="radio" name="isus" value="n" /> My address is outside of the United States.
                    </div>
                    <br/>
                </td>
            <tr>
            <tr class="bottom">
                <td>
                    <input type="hidden" name="function" value="Continue"/>
                    <input type="submit" name="function" class="submit" value="Continue"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- end content-form div -->

</div>
<!-- end content div -->

<!-- put a footer div here -->
<p>
    If you encounter any problems, use our <a href="http://www.searchsystems.net/webmaster.php">contact form</a>
    or call us at (800) 350-2232 or (805) 375-4041. Customer service is available 8-5 PST M-F. 
</p>

</div>
<!-- end page div -->

</body>
</html>
