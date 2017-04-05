package com.yoogalu.jeetest.asynctrans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HandleDao {

	@PersistenceContext
	EntityManager em;

	public void save(Handle handle) {
		em.persist(handle);
	}

	public Handle find(int id) {
		return em.find(Handle.class, id);
	}

	public List<Handle> findAll() {
		return em.createQuery("select m from Handle m", Handle.class).getResultList();
	}
}
