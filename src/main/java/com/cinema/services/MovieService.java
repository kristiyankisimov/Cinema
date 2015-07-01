package com.cinema.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cinema.dao.MovieDAO;
import com.cinema.model.Movie;
import com.cinema.model.Screening;
import com.cinema.services.beans.TicketBean;

@Stateless
@Path("movies")
public class MovieService {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private MovieDAO movieDAO;

	@Inject
	private TicketBean ticket;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Movie> getAllMovies() {
		return movieDAO.getAllMovies();
	}

	@POST
	@Path("{movieId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMovieById(@PathParam("movieId") String movieId) {
		Movie chosenMovie = movieDAO.getMovieById(Long.parseLong(movieId));
		ticket.setChosenMovie(chosenMovie);
		return RESPONSE_OK;
	}

	@GET
	@Path("current")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getCurrentMovie() {
		return ticket.getChosenMovie();
	}
}
