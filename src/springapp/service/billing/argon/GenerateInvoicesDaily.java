package springapp.service.billing.argon;

public class GenerateInvoicesDaily {	
	/**
	 * Helper function to build a Postgres interval for dates.
	 */
	/*
	protected String buildInterval(SubscriptionDaily sd) {
		Integer units = sd.getRecurringUnits();
		String period = sd.getTimePeriodName();		
		String interval;
		if ((units == 0) || (period.equals("none"))) {
			interval = "0";
		} else {
			interval = units + " " + period;
		}
		return interval;
	}
	*/
	
	/*
	 * billing dates:
	 * init_date is always the initial sub creation
	 * 
	 * TODO: figure out initial/recurring billing
	 * 
	 * billed in advance:
	 * setup:
	 *   set prev, next = now
	 *   if rate has initial period:
	 *     set next & end dates
	 *     generate invoice for trial period
	 *   else:
	 *     run recur to generate invoice
	 *       this is perhaps unnecessary to do immediately since it will
	 *       happen on the daily run. maybe just collect payment and let
	 *       invoicing system figure it out later when it does the run.
	 * recur: (also used after initial setup)
	 *   set prev = next
	 *   set next = next + interval
	 *   get updated bill dates into sd object
	 *   run invoice (sub start/end should be correct on inv detail)
	 * 
	 * TODO: does initial (trial) period make sense if billed after?
	 * i don't think it does.
	 * 
	 * billed after:
	 * setup:
	 *   set prev = now
	 *   set next = now + interval
	 * recur:
	 *   run invoice (should use old sub start/end)
	 *   set prev = next
	 *   set next = next + interval
	 */
	
	/*
	 * these functions all expect to run within a db txn.
	 * they will throw the ex to the caller for rollback.
	 */
	
	/**
	 * Refresh the bill dates in the object. Used for prebill after advancing bill dates.
	 * 
	 * ? replace with UserManager.getSubscription()
	 * maybe just get new values from sub
	 */
	/*
	protected void getUpdatedBillDates(Connection conn, SubscriptionDaily sd) throws SQLException {
		PreparedStatement stat = conn.prepareStatement(queryStrings.get("getSubscription"));
		stat.setInt(1, sd.getSubscriptionId());
		ResultSet rs = stat.executeQuery();			
		if (rs.next()) {
			sd.setPrevBillDate(rs.getTimestamp("prev_bill_date"));
			sd.setNextBillDate(rs.getTimestamp("next_bill_date"));
		} else {
			throw new SQLException("Failed to get updated bill dates");
		}
	}
	*/
	
	/**
	 * Create an invoice based on the sd object.
	 * 
	 * ? replace with BillingManager.billSubscription()
	 * need separate fn's for invoice & payment
	 */
	/*
	protected Integer addSubscriptionInvoice(Connection conn, SubscriptionDaily sd) throws SQLException {
		PreparedStatement stat = null;
		int invoiceId = 0;
		
		// 1) add invoice header
		invoiceId = getSeqNextVal("ss_invoices_invoice_id_seq");
		Timestamp t = new Timestamp(System.currentTimeMillis());
		//Timestamp t = 
		stat = conn.prepareStatement(queryStrings.get("addInvoice"));
		stat.setInt(1, invoiceId);
		stat.setInt(2, sd.getUserId());
		stat.setTimestamp(3, t);
		stat.setTimestamp(4, t);
		stat.setBigDecimal(5, new BigDecimal("0.00"));
		stat.setBigDecimal(6, new BigDecimal("0.00"));
		stat.executeUpdate();
		stat.close();

		// 2) add invoice details
		int invoiceDetailId = getSeqNextVal("ss_invoice_details_invoice_detail_id_seq");
		int quantity = 1;
		BigDecimal quantityBig = new BigDecimal(quantity);
		BigDecimal price = sd.getRecurringPrice();

		stat = conn.prepareStatement(queryStrings.get("addInvoiceDetail"));
		stat.setInt(1, invoiceDetailId);
		stat.setInt(2, invoiceId);
		stat.setBigDecimal(3, price);
		stat.setInt(4, quantity);
		stat.setBigDecimal(5, price.multiply(quantityBig));
		stat.setString(6, sd.getRateName());
		stat.setString(7, sd.getRateDescription());
		stat.setTimestamp(8, sd.getPrevBillDate());
		stat.setTimestamp(9, sd.getNextBillDate());
		stat.executeUpdate();
		stat.close();
		
		// 3) update header with amount due (sum of details)
		stat = conn.prepareStatement(queryStrings.get("updateInvoiceAmountBilled"));
		stat.setInt(1, invoiceId);
		stat.setInt(2, invoiceId); // need this twice due to subquery
		stat.executeUpdate();
		stat.close();
		
		return invoiceId;
	}
	*/
	
	/**
	 * Apply a payment to a specific invoice detail.
	 * 
	 * @param paymentId
	 * @param invoiceDetailId
	 * @return the new payment detail id
	 * @throws SQLException
	 * 
	 * move to BillingManager or maybe repository
	 */
	/*
	protected int applyPaymentToInvoiceDetail(Connection conn, int paymentId, int invoiceDetailId, BigDecimal amount) throws SQLException {
		int paymentDetailId = 0;
		int invoiceId;
		BigDecimal totalPrice;
		BigDecimal amountPaid;
		
		PreparedStatement stat = null;		
		
		// get the inv det info
		stat = conn.prepareStatement(queryStrings.get("getInvoiceDetail"));
		stat.setInt(1, invoiceDetailId);
		ResultSet rs = stat.executeQuery();			
		if (rs.next()) {
			invoiceId = rs.getInt("invoice_id");
			totalPrice = rs.getBigDecimal("total_price");
			amountPaid = rs.getBigDecimal("amount_paid");
		} else {
			throw new SQLException("Failed to get invoice detail");
		}
		rs.close();
		stat.close();

		// don't apply if the item is already paid		
		if (amountPaid.equals(totalPrice)) {
			// bail out?
		}
		
		// apply the payment
		paymentDetailId = getSeqNextVal("ss_payment_details_payment_detail_id_seq");
		Timestamp t = new Timestamp(System.currentTimeMillis());
		
		stat = conn.prepareStatement(queryStrings.get("applyPaymentToInvoiceDetail"));
		stat.setInt(1, paymentDetailId);
		stat.setInt(2, paymentId);
		stat.setInt(3, invoiceDetailId);
		stat.setTimestamp(4, t);
		stat.setBigDecimal(5, amount);
		stat.executeUpdate();
		stat.close();
		
		// update the invoice detail
		stat = conn.prepareStatement(queryStrings.get("updateInvoiceDetailAmountPaid"));
		stat.setBigDecimal(1, amountPaid.add(amount));
		stat.setInt(2, invoiceDetailId); // need this twice due to subquery
		stat.executeUpdate();
		stat.close();
		
		// update the invoice
		stat = conn.prepareStatement(queryStrings.get("updateInvoiceAmountPaid"));
		stat.setInt(1, invoiceId);
		stat.setInt(2, invoiceId); // need this twice due to subquery
		stat.executeUpdate();
		stat.close();
		
		// update the payment
		stat = conn.prepareStatement(queryStrings.get("updatePaymentAmountApplied"));
		stat.setInt(1, paymentId);
		stat.setInt(2, paymentId); // need this twice due to subquery
		stat.executeUpdate();
		stat.close();
		
		return paymentDetailId;
	}
	*/
	
	/**
	 * Generate an invoice for a postbill recurring sub.
	 * 
	 * @param sd The subscription object
	 * @return the new invoice id
	 */
	/*
	protected Integer runRecurForSubscription(SubscriptionDaily sd) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement stat = null;
		String interval = buildInterval(sd);
		System.out.println("interval: " + interval);
		
		int invoiceId = 0;
		
		try {
			conn.setAutoCommit(false);
			
			if (sd.isBillInAdvance()) {
				// advance the bill dates
				advanceSubscriptionBillDates(conn, sd, interval); 
				
				// get the updated bill dates and add to the object
				getUpdatedBillDates(conn, sd);
				
				// run invoice
				invoiceId = addSubscriptionInvoice(conn, sd);
			} else {
				// run invoice
				invoiceId = addSubscriptionInvoice(conn, sd);

				// advance the bill dates
				advanceSubscriptionBillDates(conn, sd, interval); 
			}			
			
			// all done
			conn.commit();
			
		} catch (SQLException e) {
			try { 
				conn.rollback(); 
			} catch (Exception e1) {
				System.out.println("run recur rollback failed for user: " + sd.getSubscriptionId());
				e.printStackTrace();
				System.exit(3);				
			}
			System.out.println("run recur failed for user: " + sd.getSubscriptionId());
			e.printStackTrace();
		} finally {
			this.releaseJdbcResources(conn, stat, rs);
		}
			
		return invoiceId;
	}
	*/

	
	/*
	 * apply payments to invoice details for a single user
	 * 
	 * move to BillingManager
	 */
	/*
	protected void applyPaymentsForUser(int userId) {
		Connection conn = getConnection();
		
		try {
			conn.setAutoCommit(false);

			ArrayList<InvoiceDetail> details = getUnpaidInvoiceDetailsForUser(userId);					
			System.out.println("i: " + details.size());			

			ArrayList<Payment> payments = getUnappliedPaymentsForUser(userId);				
			System.out.println("p: " + payments.size());			
			
			// main loop
			while (true) {
				Iterator<InvoiceDetail> x = details.iterator();
				if (!x.hasNext()) {
					break;
				}
				InvoiceDetail id = x.next();
				int invoiceDetailId = id.getInvoiceDetailId();
				BigDecimal idAmountLeft = id.getTotalPrice().subtract(id.getAmountPaid());			
	
				Iterator<Payment> y = payments.iterator();
				if (!y.hasNext()) {
					break;
				}
				Payment p = y.next();
				int paymentId = p.getPaymentId();
				BigDecimal pAmountLeft = p.getAmountPaid().subtract(p.getAmountApplied());
				
				// determine how much to apply
				String msg;
				if (idAmountLeft.equals(pAmountLeft)) {
					msg = "Payment " + paymentId + " matches detail " + invoiceDetailId + ": " + idAmountLeft;
					applyPaymentToInvoiceDetail(conn, paymentId, invoiceDetailId, pAmountLeft);
					x.remove();
					y.remove();
				} else {
					if (idAmountLeft.compareTo(pAmountLeft) > 0) {
						msg = "Payment " + paymentId + " less than detail " + invoiceDetailId + ": " + pAmountLeft;
						applyPaymentToInvoiceDetail(conn, paymentId, invoiceDetailId, pAmountLeft);
						id.setAmountPaid(id.getAmountPaid().add(pAmountLeft));
						y.remove();
					} else {
						msg = "Payment " + paymentId + " exceeds detail " + invoiceDetailId + ": " + idAmountLeft;
						applyPaymentToInvoiceDetail(conn, paymentId, invoiceDetailId, idAmountLeft);
						p.setAmountApplied(p.getAmountApplied().add(idAmountLeft));
						x.remove();
					}
				}								
				System.out.println(msg);
			}
			
			conn.commit();
		} catch (SQLException e) {
			try { 
				conn.rollback(); 
			} catch (Exception e1) {
				System.out.println("Apply payments rollback failed for user: " + userId);
				e.printStackTrace();
				System.exit(3);				
			}
			System.out.println("Apply payments failed for user: " + userId);
			e.printStackTrace();
		} finally {
			this.releaseJdbcResources(conn, null, null);
		}		
	}
	*/
	
	/*
	 * move to BillingManager or job
	 */
	/*
	protected void applyPayments() {
		// get eligible users
		ArrayList<Integer> users = findUsersForApplyPayments();
		
		for (Iterator<Integer> i = users.iterator(); i.hasNext(); ) {
			Integer userId = i.next();
			System.out.println("Applying payments for user " + userId);
			applyPaymentsForUser(userId);
		}
	}
	*/	
	
	/**
	 * Process the list of subs and generate invoices for each one.
	 * 
	 * move to BillingManager or job
	 */
	/*
	 * 
	 */
	/*
	protected ArrayList<Integer> generateInvoices(ArrayList<SubscriptionDaily> subscriptions) {
		//Connection conn = getConnection();
		ArrayList<Integer> invoices = new ArrayList<Integer>();

		for (Iterator<SubscriptionDaily> i = subscriptions.iterator(); i.hasNext(); ) {			
			SubscriptionDaily sd = i.next();
	
			Integer invoiceId = runRecurForSubscription(sd);
			System.out.println("invoice: " + invoiceId);
			invoices.add(invoiceId);
		}
			
		return invoices;
	}
	*/
	
	/**
	 * Process the list of subs and generate invoices for each one.
	 * 
	 * move to BillingManager or job
	 */
	/*
	protected ArrayList<Integer> generatePayments(ArrayList<Integer> usersAutopay) {
		ArrayList<Integer> paymentIds = new ArrayList<Integer>();
		
		for (Iterator<Integer> i = usersAutopay.iterator(); i.hasNext(); ) {
			Integer userId = i.next();
			System.out.println("Generating payments for user " + userId);
			
			ArrayList<Invoice> invoices;
			try {
				invoices = getUnpaidInvoicesForUser(userId);
			} catch (SQLException e) {
				System.out.println("Couldn't get unpaid invoices for user: " + userId);
				continue;
			}
			
			BigDecimal paymentAmount = new BigDecimal("0.00");
			BigDecimal paymentZero = new BigDecimal("0.00");
			
			for (Iterator<Invoice> j = invoices.iterator(); j.hasNext(); ) {
				Invoice inv = j.next();
				BigDecimal invRemaining = inv.getAmountBilled().subtract(inv.getAmountPaid()); 
				paymentAmount = paymentAmount.add(invRemaining);
				System.out.println("foo: " + invRemaining + ":" + paymentAmount);
			}
			
			if (paymentAmount.compareTo(paymentZero) == 0) {
				// no payment due
				System.out.println("No payment due for user " + userId + " for " + paymentAmount);
			} else {
				// charge for this amount
				try {
					System.out.println("Attempting to charge user " + userId + " for " + paymentAmount);
					Integer paymentId = generatePaymentForUser(userId, paymentAmount, "Automatic Payment", false);
					paymentIds.add(paymentId);
					
					// now apply the payment
					applyPaymentsForUser(userId);					
				} catch (AxisFault e) {
					System.out.println("Error generating CC payment for user " + userId);
					e.printStackTrace();
				}
			}
		}
		
		return paymentIds;
	}
	*/	
}