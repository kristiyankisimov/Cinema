package com.cinema.services;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.Application;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;

import com.cinema.dao.ScreeningDAO;
import com.cinema.dao.SeatDAO;
import com.cinema.dao.TicketDAO;
import com.cinema.login.services.UserContext;
import com.cinema.model.Movie;
import com.cinema.model.Screening;
import com.cinema.model.Seat;
import com.cinema.model.Ticket;
import com.cinema.services.beans.HistoryBean;
import com.cinema.services.beans.SeatIdsBean;
import com.cinema.services.beans.TicketBean;

@Stateless
@Path("tickets")
public class TicketService {

	private static final Response RESPONSE_OK = Response.ok().build();
	private static final Response RESPOMSE_NOT_OK = Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build();

	@Inject
	private UserContext context;

	@Inject
	private TicketDAO ticketDAO;

	@Inject
	private TicketBean ticketBean;

	@Inject
	private SeatDAO seatDAO;
	
	@Inject
	private ScreeningDAO screeningDAO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getScreeningByDate(SeatIdsBean bean) {

		List<Long> seatsIds = bean.getSeatsIds();
		
		Screening screening = ticketBean.getScreening();
		
		List<Ticket> tickets = new ArrayList<>();
		
		for (int i = 0; i < seatsIds.size(); i++) {
			Ticket ticket = new Ticket();
			ticket.setScreening(screening);
			ticket.setUser(context.getCurrentUser());

			Seat seat = seatDAO.getSeatById(seatsIds.get(i));
			if(seat.getIsReserved()) {
				return RESPOMSE_NOT_OK;
			}
			seat.setIsReserved(true);
			seat.setReservationTime(new Date());

			ticket.setSeat(seat);
			ticket.setChecked(false);
			tickets.add(ticket);
		}
		
		persistAllTickets(tickets);
		
		Screening updatedScreening = screeningDAO.getScreeningById(screening.getId());
		ticketBean.setScreening(updatedScreening);
		
		return RESPONSE_OK;
	}
	
	@GET
	@Path("allByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<HistoryBean> getAllTickets() {
		Collection<Ticket> tickets = ticketDAO.getTicketsByUserName(context.getCurrentUser().getUserName());
		
		Collection<HistoryBean> historyTickets = new ArrayList<>();
		
		for(Ticket ticket : tickets) {
			HistoryBean bean = new HistoryBean();
			bean.setTicket(ticket);
			String formattedDate = formatDate(ticket.getScreening().getDate());
			bean.setFormattedString(formattedDate);
			
			historyTickets.add(bean);
		}
		
		return historyTickets;
	}
	
	private void persistAllTickets(List<Ticket> tickets) {
		for(Ticket ticket : tickets) {
			ticketDAO.addTicket(ticket);
		}
	}
	
	private String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

		return dateFormat.format(date);

	}
}


