package com.yoogalu.jeetest.asyncprocessing;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class AsyncProcessor {
	private final static String prefix = "******AsyncProcessor ";

	@Inject
	MessageDao dao;

	@Inject
	TransactionDetails txn;

//	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Asynchronous
	public void process(MessageStatus status) {
		System.out.println(prefix + txn.get());
		System.out.println(prefix + dao.findAll());
		dao.save(status, prefix);
		System.out.println(prefix + dao.findAll());
	}

}
