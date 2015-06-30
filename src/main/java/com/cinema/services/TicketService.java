package com.cinema.services;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cinema.dao.ScreeningDAO;
import com.cinema.dao.SeatDAO;
import com.cinema.dao.TicketDAO;
import com.cinema.login.services.UserContext;
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
	public synchronized Response buyTickets(SeatIdsBean bean) {

		List<Long> seatsIds = bean.getSeatsIds();
		
		Screening screening = ticketBean.getScreening();
		
		List<Ticket> tickets = new ArrayList<>();
		
		for (int i = 0; i < seatsIds.size(); i++) {
			Ticket ticket = new Ticket();
			ticket.setScreening(screening);
			ticket.setUser(context.getCurrentUser());

			Seat seat = seatDAO.getSeatById(seatsIds.get(i));
			Calendar now = Calendar.getInstance();
			if(now.get(Calendar.MINUTE) - seat.getReservationTime().get(Calendar.MINUTE) >= 10) {
				freeAllSeats(seatsIds);
				return RESPOMSE_NOT_OK;
			}

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
	
	@POST
	@Path("reserveSeat")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response reserveSeat(Seat seat) {
		Seat currentSeat = seatDAO.getSeatById(seat.getId());
		if(currentSeat == null) {
			return RESPOMSE_NOT_OK;
		}
		currentSeat.setIsReserved(true);
		currentSeat.setReservationTime(Calendar.getInstance());
		
		return RESPONSE_OK;
	}
	
	@POST
	@Path("freeSeat")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response freeSeat(Seat seat) {
		Seat currentSeat = seatDAO.getSeatById(seat.getId());
		if(currentSeat == null) {
			return RESPOMSE_NOT_OK;
		}
		currentSeat.setIsReserved(false);
		currentSeat.setReservationTime(null);
		
		return RESPONSE_OK;
	}
	
	private void freeAllSeats(List<Long> seatIds) {
		for(Long id : seatIds) {
			Seat seat = seatDAO.getSeatById(id);
			seat.setIsReserved(false);
			seat.setReservationTime(null);
		}
	}
	
	private void persistAllTickets(List<Ticket> tickets) {
		for(Ticket ticket : tickets) {
			ticketDAO.addTicket(ticket);
		}
	}
	
	private String formatDate(Calendar date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

		return dateFormat.format(date.getTime());

	}
}


