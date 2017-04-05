package com.yoogalu.jeetest.asyncprocessing;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Delegate {
	private final static String prefix = "----Delegate ";
	
	@Inject
	MessageDao dao;

	@Inject
	AsyncProcessor processor;

	@Inject
	TransactionDetails txn;

	public String invoke() {
		System.out.println(prefix + txn.get());
		for (int i = 1; i < 2; i++) {
			dao.save(new MessageStatus("status-" + i), prefix);
		}

		processor.process(new MessageStatus("status-25"));

		System.out.println(prefix + dao.findAll());
		return "";
	}
}
