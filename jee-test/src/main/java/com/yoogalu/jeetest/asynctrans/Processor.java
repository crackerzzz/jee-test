package com.yoogalu.jeetest.asynctrans;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Processor {

	@Inject
	HandleDao handleDao;

	@Inject
	TransactionDetails txnDetails;

	public void process(int id) {
		System.out.println("1. Handling " + id + " with txn: " + txnDetails.getKey());
		handleDao.save(new Handle("Handling " + id));
		if (id % 2 == 0) {
			System.out.println("Handled: " + id);
		} else {
			System.out.println("Throwing exception: " + id);
			throw new IllegalArgumentException("Cannot handle: " + id);
		}
	}
}
