package com.yoogalu.jeetest.asynceventprocessing;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
public class Delegate {
	private final static String prefix = "----Delegate ";

	@Inject
	MessageDao dao;

	@Inject
	AsyncEventProcessor processor;

	@Inject
	TransactionDetails txn;

	@Inject
	Event<MessageStatus> event;

	public String invoke() {
		System.out.println(prefix + txn.get());
		for (int i = 1; i < 10; i++) {
			dao.save(new MessageStatus("status-" + i), prefix);
		}

		event.fire(new MessageStatus("status-25"));

		System.out.println(prefix + dao.findAll());
		return "";
	}
}
