package com.yoogalu.jeetest.asynctrans;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
public class MessageDispatcher {

	@Inject
	MessageDelegater delegator;

	@Inject
	Event<StatusEvent> event;

	@Inject
	TransactionDetails txnDetails;

	@Asynchronous
	public Future<String> dispatch(int id) {
		System.out.println("1. Dispatching " + id + " with txn: " + txnDetails.getKey());
		String status;
		try {
			delegator.carryOut(id);
			status = "SUCESSFUL";
		} catch (Exception e) {
			status = "EXCEPTION";
		}
		try {
			System.out.println("Firing event.");
			event.fire(new StatusEvent(id, status));
		} catch (Exception ex) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + ex.getMessage());
		}
		return new AsyncResult<>(status);
	}
}
