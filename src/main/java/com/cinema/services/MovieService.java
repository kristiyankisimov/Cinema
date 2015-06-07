package com.cinema.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cinema.dao.MovieDAO;
import com.cinema.model.Movie;

@Stateless
@Path("movies")
public class MovieService {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private MovieDAO movieDAO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Movie> getAllMovies() {
		return movieDAO.getAllMovies();
	}

	@GET
	@Path("{movieId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMovieById(@PathParam("movieId") String movieId) {
		return movieDAO.getMovieById(Long.parseLong(movieId));
	}
}
