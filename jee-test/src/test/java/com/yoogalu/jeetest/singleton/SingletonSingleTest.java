package com.yoogalu.jeetest.singleton;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SingletonSingleTest {

	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "async-test.jar").addClass(SingleCaller.class).addClass(
				SingletonProcessor.class).addClass(Retry.class).addClass(RetryPolicy.class).addAsManifestResource(
						EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}

	@Inject
	SimpleCaller caller;

	@Test
	public void test() throws Exception {
		caller.call();
		Thread.currentThread().join();
		System.out.println(
				"************************************************SingletonTest COMPLETED**********************************");
	}
}
