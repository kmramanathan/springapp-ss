<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:funnelPage title="Contact Us" rightSidebar="GenericSidebar.jsp">
<jsp:attribute name="stylesheet">funnel.css</jsp:attribute>

<jsp:body>

<a href="${closeWindowUrl}"><img src="images/common/bt-back-small.png" alt="Return" /></a>

<p class="note"><span class="req"><strong>*</strong></span> Required Fields</p>

<h1>Contact SearchSystems.net</h1>

<p>Use this form to contact us with any comments or questions that you have.
Is there something that you really like about our website? Do you have
questions about how to use it? Is there any way that we can make it better
or more user friendly?</p>

<p>Just fill in your name, email address, and the
subject of your message. Then type out what you want to say in the main
white box and click on Send Message when you're finished. We'll do our
best to answer your questions promptly.</p>

<div id="error">
</div>

<div id="formwrapper">
    <form:form method="post">
<form:errors path="*">
<b>Please correct the following errors:</b><br/>
</form:errors>
<form:errors path="*" element="div" id="error" cssClass="error-box"/>

        <ul>
            <li><label for="name"><span class="req"><strong>*</strong></span> Your Name:</label>
            <form:input path="name" cssClass="input-text" size="25" maxlength="50"/>
            </li>

            <li><label for="email"><span class="req"><strong>*</strong></span> Your Email:</label>
            <form:input path="email" cssClass="input-text" size="25" maxlength="50"/>
            </li>

            <li><label for="subject"><span class="req"><strong>*</strong></span> Subject:</label>
            <form:select path="subject">
            <form:option value="">[Choose]</form:option>
            <form:option value="Feedback">Feedback</form:option>
            <form:option value="Problem">Problem</form:option>
            <form:option value="Question">Question</form:option>
            <form:option value="Testimonial">Testimonial</form:option>
            </form:select>
            </li>

            <li><label for="message"><span class="req"><strong>*</strong></span> Message:</label>
			<form:textarea path="message" cols="30" rows="8"></form:textarea>
			</li>

            <li><label>&nbsp; </label><input type="submit" value="Send Message"/>
            </li>
        </ul>
    </form:form>
</div>

</jsp:body>

</neon:funnelPage>
