package com.cinema.dao;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.cinema.model.Seat;

@Singleton
public class SeatDAO {

	@PersistenceContext
	private EntityManager em;

	public void addSeat(Seat seat) {
		em.persist(seat);
	}

	public Seat getSeatById(Long id) {
		TypedQuery<Seat> query = em.createNamedQuery("getSeatById", Seat.class)
				.setParameter("id", id);

		return queryMovie(query);
	}

	private Seat queryMovie(TypedQuery<Seat> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
