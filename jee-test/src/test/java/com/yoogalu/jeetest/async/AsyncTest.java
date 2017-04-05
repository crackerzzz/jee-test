package com.yoogalu.jeetest.async;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yoogalu.jeetest.async.AsyncCaller;
import com.yoogalu.jeetest.async.AsyncProcessor;

@RunWith(Arquillian.class)
public class AsyncTest {

	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "async-test.jar").addClass(AsyncProcessor.class)
				.addClass(AsyncCaller.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(jar.toString(true));
		return jar;
	}

	@Inject
	AsyncCaller caller;

	@Test
	public void test() throws Exception {
		caller.work();
		System.out.println(
				"************************************************AsyncTest COMPLETED**********************************");
	}
}
