package com.cinema.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cinema.dao.SeatDAO;
import com.cinema.dao.TicketDAO;
import com.cinema.login.services.UserContext;
import com.cinema.model.Screening;
import com.cinema.model.Seat;
import com.cinema.model.Ticket;
import com.cinema.services.beans.TicketBean;

@Stateless
@Path("tickets")
public class TicketService {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private UserContext context;

	@Inject
	private TicketDAO ticketDAO;

	@Inject
	private TicketBean ticketBean;

	@Inject
	private SeatDAO seatDAO;

	@POST
	@Path("{seatIds}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getScreeningByDate(@PathParam("seatIds") List<Long> ids) {
		Screening screening = ticketBean.getScreening();

		for (int i = 0; i < ids.size(); i++) {
			Ticket ticket = new Ticket();
			ticket.setScreening(screening);
			ticket.setUser(context.getCurrentUser());

			Seat seat = seatDAO.getSeatById(ids.get(i));
			seat.setIsReserved(true);
			seat.setReservationTime(new Date());
			
			ticket.setSeat(seat);
			ticket.setChecked(false);
			ticketDAO.addTicket(ticket);

		}
		return RESPONSE_OK;
	}
}
