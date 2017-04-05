package com.yoogalu.jeetest.timeout;

import java.util.concurrent.Future;

import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class SingletonEjb {

	@Inject
	AnotherEjb ejb;

	private Future<Boolean> ongoing;

	public void process() {
		System.out.println("SingletonEjb starting.");
		if (isOK()) {
			ongoing = ejb.process();
		} else {
			System.out.println(this.toString() + " is busy.");
		}
		System.out.println("SingletonEjb finished.");
	}

	private boolean isOK() {
		if (ongoing != null) {
			if (ongoing.isDone()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "SingletonEjb [ejb=" + ejb + "]";
	}

}
