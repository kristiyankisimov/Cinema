package com.cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.cinema.model.Hall;

@Singleton
public class HallDAO {

	@PersistenceContext
	private EntityManager em;

	public Collection<Hall> getAllSeats() {
		TypedQuery<Hall> query = em.createNamedQuery("getAllSeats", Hall.class);

		return queryScreening(query);
	}

	private Collection<Hall> queryScreening(TypedQuery<Hall> query) {
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
}
