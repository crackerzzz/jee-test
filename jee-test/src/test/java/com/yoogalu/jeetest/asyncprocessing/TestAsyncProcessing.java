package com.yoogalu.jeetest.asyncprocessing;

import java.util.Arrays;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestAsyncProcessing {

	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "async-trans-test.war").addPackage(AsyncProcessor.class
				.getPackage()).addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(
						EmptyAsset.INSTANCE, "beans.xml");
		return war;
	}

	@Inject
	Invoker invoker;

	@Inject
	MessageDao messageDao;

	@Test
	@InSequence(1)
	public void get() throws Exception {
		invoker.invoke();
		Thread.sleep(3000);
		System.out.println("MESSAGES: " + Arrays.asList(messageDao.findAll()));
	}
}
