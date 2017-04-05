package com.yoogalu.jeetest.asynceventprocessing;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class TransactionDetails {

	@Resource
	TransactionSynchronizationRegistry txnRegistry;

	public Object getKey() {
		return txnRegistry.getTransactionKey();
	}

	public int getStatus() {
		return txnRegistry.getTransactionStatus();
	}

	public String get() {
		return getKey() + " - " + getStatus();
	}

}
