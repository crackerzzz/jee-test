package com.yoogalu.jeetest.asynctrans;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MessageDelegater {

	@Inject
	Processor processor;

	@Inject
	TransactionDetails txnDetails;

	public void carryOut(int id) throws Exception {
		System.out.println("1. Processing " + id + " with txn: " + txnDetails.getKey());
		try {
			processor.process(id);
		} catch (Exception e) {
			throw new Exception("Exception during handling.");
		}
	}

}
