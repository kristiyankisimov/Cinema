package com.cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.annotation.XmlRootElement;

import com.cinema.model.Movie;


@XmlRootElement
@Singleton
public class MovieDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addMovie(Movie movie) {
		em.persist(movie);
	}
	
	public Collection<Movie> getAllMovies() {
		return em.createNamedQuery("getAllMovies", Movie.class).getResultList();
	}
	
	
	public Movie getMovieByTitle(String title) {
		return em.createNamedQuery("getMovieByTitle", Movie.class).setParameter("title", title).getSingleResult();
	}
	
	
}
