package com.yoogalu.jeetest.timeout;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class AnotherEjb {

	@Asynchronous
	public Future<Boolean> process() {
		System.out.println("AnotherEjb async task starting.");
		try {
			Thread.sleep(1000000000);
			return new AsyncResult<Boolean>(true);
		} catch (InterruptedException e) {
		} finally {
			System.out.println("AnotherEjb async task starting.");
		}
		return new AsyncResult<Boolean>(true);
	}
}
