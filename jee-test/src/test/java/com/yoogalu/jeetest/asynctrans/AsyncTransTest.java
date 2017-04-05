package com.yoogalu.jeetest.asynctrans;

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
public class AsyncTransTest {

	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "async-trans-test.war").addPackage(MessageDispatcher.class
				.getPackage()).addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource(
						EmptyAsset.INSTANCE, "beans.xml");
		return war;
	}

	@Inject
	Invoker invoker;

	@Inject
	MessageDao messageDao;

	@Inject
	HandleDao handleDao;

	@Test
	@InSequence(1)
	public void get() throws Exception {
		invoker.start();
		Thread.sleep(3000);
		System.out.println("MESSAGES: " + Arrays.asList(messageDao.findAll()));
		System.out.println("HANDLES: " + Arrays.asList(handleDao.findAll()));
	}
}
