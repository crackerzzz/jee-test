package com.yoogalu.jeetest.asynctrans;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Stateless
public class EventHandler {

	@Inject
	MessageDao dao;

	@Inject
	TransactionDetails txnDetails;

	@Asynchronous
	public void onStatusChanged(@Observes StatusEvent event) {
		System.out.println("Received event " + event + " with txn: " + txnDetails.getKey());
		dao.save(new MessageStatus(event.getId() + ":" + event.getStatus()));
		throw new IllegalArgumentException(String.format("Event {} failed.", event));
	}
}
