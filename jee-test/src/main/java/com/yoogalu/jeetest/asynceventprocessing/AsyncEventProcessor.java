package com.yoogalu.jeetest.asynceventprocessing;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

@Stateless
public class AsyncEventProcessor {
	private final static String prefix = "******AsyncProcessor ";

	@Inject
	MessageDao dao;

	@Inject
	TransactionDetails txn;

	@Asynchronous
	public void process(@Observes(during = TransactionPhase.AFTER_SUCCESS) MessageStatus status) {
		System.out.println(prefix + txn.get());
		System.out.println(prefix + dao.findAll());
		dao.save(status, prefix);
		System.out.println(prefix + dao.findAll());
	}

}
