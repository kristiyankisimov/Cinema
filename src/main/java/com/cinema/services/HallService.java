package com.cinema.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cinema.dao.HallDAO;
import com.cinema.model.Hall;

@Stateless
@Path("movieInformation")
public class HallService {

	@Inject
	private HallDAO hallDAO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Hall> getAllMovies() {
		System.out.println(hallDAO.getAllSeats());
		return hallDAO.getAllSeats();
	}
	
}
