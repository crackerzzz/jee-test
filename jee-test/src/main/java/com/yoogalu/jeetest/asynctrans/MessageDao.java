package com.yoogalu.jeetest.asynctrans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageDao {

	@PersistenceContext
	EntityManager em;

	public void save(MessageStatus message) {
		em.persist(message);
	}

	public MessageStatus find(int id) {
		return em.find(MessageStatus.class, id);
	}

	public List<MessageStatus> findAll() {
		return em.createQuery("select m from MessageStatus m", MessageStatus.class).getResultList();
	}
}
