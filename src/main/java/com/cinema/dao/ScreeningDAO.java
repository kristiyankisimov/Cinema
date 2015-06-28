package com.cinema.dao;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Singleton;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlRootElement;

import com.cinema.model.Movie;
import com.cinema.model.Screening;

@Singleton
public class ScreeningDAO {

	@PersistenceContext
	private EntityManager em;

	public void addScreening(Screening screening) {
		em.persist(screening);
	}

	public Screening getScreeningByDate(Date date) {

		TypedQuery<Screening> query = em.createNamedQuery("getScreeningByDate",
				Screening.class).setParameter("date", date);

		return queryScreening(query);

	}

	public Screening getScreeningById(Long id) {

		TypedQuery<Screening> query = em.createNamedQuery("getScreeningById",
				Screening.class).setParameter("id", id);

		return queryScreening(query);
	}

	public Screening getScreeningByHall(Long HALL_ID) {

		TypedQuery<Screening> query = em.createNamedQuery("getScreeningByHall",
				Screening.class).setParameter("HALL_ID", HALL_ID);

		return queryScreening(query);
	}

	public Collection<Screening> getAllScreenings() {
		TypedQuery<Screening> query = em.createNamedQuery("getAllScreenings",
				Screening.class);

		return queryAllScreenings(query);
	}

	private Screening queryScreening(TypedQuery<Screening> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private Collection<Screening> queryAllScreenings(TypedQuery<Screening> query) {
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
