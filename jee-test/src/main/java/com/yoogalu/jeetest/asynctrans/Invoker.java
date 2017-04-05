package com.yoogalu.jeetest.asynctrans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class Invoker {

	private static final int COUNT = 1;

	@Inject
	MessageDispatcher dispatcher;

	@Inject
	MessageDao messageDao;

	@Inject
	HandleDao handleDao;

	public void start() {
		System.out.println("Invoker");
		List<Future<String>> futures = new ArrayList<>();
		for (int i = 1; i <= COUNT; i++) {
			futures.add(dispatcher.dispatch(i));
		}

		int count = 0;
		while (true) {
			if (count == COUNT) {
				break;
			}
			Iterator<Future<String>> itr = futures.iterator();

			while (itr.hasNext()) {
				final Future<?> future = itr.next();
				if (future.isDone()) {
					try {
						count++;
						final String result = (String) future.get(10, TimeUnit.MICROSECONDS);
						System.out.println("RESULT of processing: " + result);
					} catch (InterruptedException e) {
						System.out.println("InterruptedException");
					} catch (ExecutionException e) {
						System.out.println("ExecutionException");
					} catch (TimeoutException e) {
						System.out.println("TimeoutException");
					} finally {
						itr.remove();
					}
				}
			}
		}
	}
}
