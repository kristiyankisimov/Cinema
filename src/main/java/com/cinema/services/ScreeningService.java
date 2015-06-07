package com.cinema.services;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cinema.dao.ScreeningDAO;
import com.cinema.model.Screening;

@Stateless
@Path("screenings")
public class ScreeningService {
	
	private static final Response RESPONSE_OK = Response.ok().build();
	
	@Inject
	private ScreeningDAO screeningDAO;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Screening> getAllScreenings() {
        return screeningDAO.getAllScreenings();
    }
	
	@GET
	@Path("{screeningDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Screening getScreeningByDate(@PathParam("screeningDate") Date screeningDate) {
		return screeningDAO.getScreeningByDate(screeningDate);
	}

}
