package com.yoogalu.jeetest.singleton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SimpleCaller {
	private final static int COUNT = 10;

	@Inject
	SingletonProcessor processor;
	
	public void call() {
		List<Future<String>> futures = new ArrayList<>();
		for (int i = 1; i <= COUNT; i++) {
			futures.add(processor.process(i));
		}
		
		int count = 0;
		while (true) {
			if (count == COUNT) {
				System.out.println("All future tasks have been completed.");
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
