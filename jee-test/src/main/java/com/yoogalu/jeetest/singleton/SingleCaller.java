package com.yoogalu.jeetest.singleton;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SingleCaller {

	@Inject
	SingletonProcessor processor;
	
	public void call() {
		processor.process(5);
		processor.process(3);
	}
}
