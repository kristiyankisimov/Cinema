package com.cinema.login.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cinema.dao.MovieDAO;
import com.cinema.model.Movie;


@Stateless
@Path("movies")
public class MovieService {
	
	@Inject
	private MovieDAO movieDAO;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Movie> getAllBooks() {
        return movieDAO.getAllMovies();
    }
}
