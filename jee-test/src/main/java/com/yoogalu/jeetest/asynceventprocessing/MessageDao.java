package com.yoogalu.jeetest.asynceventprocessing;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageDao {

	@PersistenceContext
	EntityManager em;

	public void save(MessageStatus message, String prefix) {
		System.out.println(prefix + "Saving " + message);
		em.persist(message);
	}

	public MessageStatus find(int id) {
		return em.find(MessageStatus.class, id);
	}

	public List<MessageStatus> findAll() {
		return em.createQuery("select m from MessageStatus m", MessageStatus.class).getResultList();
	}
}
