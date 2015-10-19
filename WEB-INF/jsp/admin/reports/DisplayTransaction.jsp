<%@taglib tagdir="/WEB-INF/tags" prefix="neon"%>
<%@include file="/WEB-INF/jsp/include.jsp" %>

<neon:clientAdminPage title="View Credit Card Transaction">
<jsp:attribute name="stylesheet">admin.css</jsp:attribute>

<jsp:body>
<h2>View Credit Card Transaction</h2>

<h3>Key Fields</h3>
<table class="transaction">
	<tr>
		<td class="label">Transaction ID</td>
		<td class="value"><c:out value="${t.transactionId}"/></td>
	</tr>
	<tr>
		<td class="label">User ID</td>
		<td class="value"><c:out value="${t.userId}"/></td>
	</tr>
	<tr>
		<td class="label">Date</td>
		<td class="value"><fmt:formatDate value="${t.createDate}" type="both" dateStyle="SHORT" timeStyle="LONG"/></td>
	</tr>
	<tr>
		<td class="label">Status</td>
		<td class="value"><c:out value="${t.transactionStatusId}"/></td>
	</tr>
	<tr>
		<td class="label">Amount</td>
		<td class="value"><c:out value="${t.cost}"/></td>
	</tr>
	<tr>
		<td class="label">Last 4 Digits of CC</td>
		<td class="value"><c:out value="${t.ccLastDigits}"/></td>
	</tr>
</table>

	<tr>
		<td class="label" colspan="2"></td>
	</tr>

<h3>Merchant Bank Reporting Fields</h3>
<table class="transaction">
	<tr>
		<td class="label">Response Code</td>
		<td class="value"><c:out value="${t.bankResponseCode}"/></td>
	</tr>
	<tr>
		<td class="label">Response Subcode</td>
		<td class="value"><c:out value="${t.bankResponseSubcode}"/></td>
	</tr>	
	<tr>
		<td class="label">Reason Code</td>
		<td class="value"><c:out value="${t.bankResponseReasonCode}"/></td>
	</tr>
	<tr>
		<td class="label">Reason Text</td>
		<td class="value"><c:out value="${t.bankResponseReasonText}"/></td>
	</tr>
	
	<tr>
		<td class="label">Approval Code</td>
		<td class="value"><c:out value="${t.bankApprovalCode}"/></td>
	</tr>
	<tr>
		<td class="label">AVS Result</td>
		<td class="value"><c:out value="${t.bankAvsResultCode}"/></td>
	</tr>
	<tr>
		<td class="label">Transaction Code</td>
		<td class="value"><c:out value="${t.bankTransactionCode}"/></td>
	</tr>
	<tr>
		<td class="label">CAVV Response</td>
		<td class="value"><c:out value="${t.bankCavvResponseCode}"/></td>
	</tr>
	<tr>
		<td class="label">CVV Response</td>
		<td class="value"><c:out value="${t.bankCvvResponseCode}"/></td>
	</tr>



<%--
  search_category_id smallint,
  search_sub_category_id smallint,
  email character varying(50) NOT NULL,
  cc_name character varying(50) NOT NULL,
  cc_address1 character varying(50) NOT NULL,
  cc_address2 character varying(50) NOT NULL,
  cc_city character varying(50) NOT NULL,
  cc_state character varying(50) NOT NULL,
  cc_postal_code character varying(20) NOT NULL,
  cc_phone character varying(20) NOT NULL,
  cc_exp_month smallint NOT NULL,
  cc_exp_year smallint NOT NULL,
--%>

</table>

</jsp:body>

</neon:clientAdminPage>