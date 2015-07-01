package com.cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.cinema.model.Seat;
import com.cinema.model.Ticket;

@Singleton
public class TicketDAO {

	@PersistenceContext
	private EntityManager em;

	public void addTicket(Ticket newTicket) {
		em.persist(newTicket);
	}

	public Collection<Ticket> getAllTickets() {
		TypedQuery<Ticket> query = em.createNamedQuery("getAllTickets",
				Ticket.class);
		return queryAllMovies(query);
	}

	public Ticket getTicketById(Long ticketId) {
		TypedQuery<Ticket> query = em.createNamedQuery("getTicketById",
				Ticket.class).setParameter("id", ticketId);
		return queryMovie(query);
	}

	public Collection<Ticket> getTicketsByUserName(String userName) {
		TypedQuery<Ticket> query = em.createNamedQuery("getTicketsByUserName",
				Ticket.class).setParameter("userName", userName);
		return queryAllMovies(query);
	}

	public Collection<Ticket> getTicketsByScreeningId(Long screeningId) {
		TypedQuery<Ticket> query = em.createNamedQuery(
				"getTicketsByScreeningId", Ticket.class).setParameter(
				"screeningId", screeningId);
		return queryAllMovies(query);
	}

	public Collection<Ticket> getAllCheckedTickets() {
		TypedQuery<Ticket> query = em.createNamedQuery("getAllCheckedTickets",
				Ticket.class).setParameter("checked", true);
		return queryAllMovies(query);
	}

	public Collection<Ticket> getAllUncheckedTickets() {
		TypedQuery<Ticket> query = em.createNamedQuery("getAllCheckedTickets",
				Ticket.class).setParameter("checked", false);
		return queryAllMovies(query);
	}

	public Collection<Seat> getAllTicketSeats() {
		return null;
	}

	private Ticket queryMovie(TypedQuery<Ticket> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private Collection<Ticket> queryAllMovies(TypedQuery<Ticket> query) {
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
