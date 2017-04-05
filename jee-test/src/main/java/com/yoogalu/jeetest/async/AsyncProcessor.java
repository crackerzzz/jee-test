package com.yoogalu.jeetest.async;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class AsyncProcessor {

	@Asynchronous
	public Future<String> process(int id) {
		String status = "OK";
		if (id % 2 == 0) {
			System.out.println(id + ": " + status);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		} else {
			status = "EXCEPTION";
			System.out.println(id + ": " + status);
			throw new IllegalArgumentException("Cannot process: " + id);
		}
		
		return new AsyncResult<String>(id + ": " + status);
	}
}
