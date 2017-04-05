package com.yoogalu.jeetest.asyncprocessing;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Invoker {
	private final static String prefix = ">>Invoker ";

	@Inject
	Delegate delegate;

	@Inject
	MessageDao dao;

	@Inject
	TransactionDetails txn;

	public void invoke() {
		System.out.println(prefix + txn.get());
		delegate.invoke();
		dao.save(new MessageStatus("status-555555"), prefix);
		System.out.println(prefix + dao.findAll());
	}
}
