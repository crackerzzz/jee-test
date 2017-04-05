package com.yoogalu.jeetest.asynctrans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;

@Transactional
public class TransactionSupport {

	@PersistenceContext
	EntityManager em;

	@Before
	public void preparePersistenceTest() throws Exception {
		clearData();
	}

	private void clearData() throws Exception {
//		utx.begin();
//		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.createQuery("delete from Message").executeUpdate();
//		utx.commit();
	}


	@After
	public void dumpMessages() throws Exception {
//		utx.commit();		
		System.out.println("dumping all messages: ");
		for(MessageStatus m : em.createQuery("select m from Message m", MessageStatus.class).getResultList()) {
			System.out.println(m);
		}
	}
}
