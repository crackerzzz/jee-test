package com.yoogalu.jeetest.singleton;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

@Singleton
public class SingletonProcessor {

	@Asynchronous
	@Interceptors(Retry.class)
	@RetryPolicy
	public Future<String> process(int id) {
		System.out.println(id + " starting.");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		System.out.println(id + " returning.");
		String result = id + " returning.";
		return new AsyncResult<String>(result);
	}
}
