package com.cinema.dao;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.cinema.model.Movie;

@Singleton
public class MovieDAO {

	@PersistenceContext
	private EntityManager em;

	public void addMovie(Movie movie) {
		em.persist(movie);
	}

	public Collection<Movie> getAllMovies() {
		TypedQuery<Movie> query = em.createNamedQuery("getAllMovies",
				Movie.class);

		return queryAllMovies(query);
	}

	public Movie getMovieByTitle(String title) {
		TypedQuery<Movie> query = em.createNamedQuery("getMovieByTitle",
				Movie.class).setParameter("title", title);

		return queryMovie(query);
	}

	public Movie getMovieById(Long id) {
		TypedQuery<Movie> query = em.createNamedQuery("getMovieById",
				Movie.class).setParameter("id", id);

		return queryMovie(query);
	}

	private Movie queryMovie(TypedQuery<Movie> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private Collection<Movie> queryAllMovies(TypedQuery<Movie> query) {
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
