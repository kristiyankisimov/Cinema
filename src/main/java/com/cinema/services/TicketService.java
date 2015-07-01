package com.cinema.services;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

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
import com.cinema.utils.CinemaUtils;

@Stateless
@Path("tickets")
public class TicketService {

	private static final Response RESPONSE_OK = Response.ok().build();
	private static final Response RESPOMSE_NOT_OK = Response.status(
			HttpURLConnection.HTTP_BAD_REQUEST).build();

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
<<<<<<< HEAD
			Date now = new Date();
			if (now.getTime() - seat.getReservationTime().getTime() >= 10 * 60 * 1000) {
=======
			Calendar now = Calendar.getInstance();
			boolean isInTime = inTime(now, seat.getReservationTime());
			if (!isInTime) {
>>>>>>> origin/master
				freeAllSeats(seatsIds);
				return RESPOMSE_NOT_OK;
			}

			ticket.setSeat(seat);
			ticket.setChecked(false);
			tickets.add(ticket);
		}

		persistAllTickets(tickets);

		Screening updatedScreening = screeningDAO.getScreeningById(screening
				.getId());
		ticketBean.setScreening(updatedScreening);

		return RESPONSE_OK;
	}

	@GET
	@Path("allByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<HistoryBean> getAllTickets() {
		Collection<Ticket> tickets = ticketDAO.getTicketsByUserName(context
				.getCurrentUser().getUserName());

		Collection<HistoryBean> historyTickets = new ArrayList<>();

		for (Ticket ticket : tickets) {
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
	public synchronized Response reserveSeat(Seat seat) {
		Seat currentSeat = seatDAO.getSeatById(seat.getId());
		if (currentSeat == null) {
			return RESPOMSE_NOT_OK;
		}
<<<<<<< HEAD
		currentSeat.setIsReserved(true);
		currentSeat.setReservationTime(new Date());
=======
		currentSeat.setReservationStatus(CinemaUtils.IN_PROCESS);
		currentSeat.setReservationTime(Calendar.getInstance());
>>>>>>> origin/master

		return RESPONSE_OK;
	}

	@POST
	@Path("freeSeat")
	@Consumes(MediaType.APPLICATION_JSON)
	public synchronized Response freeSeat(Seat seat) {
		Seat currentSeat = seatDAO.getSeatById(seat.getId());
		if (currentSeat == null) {
			return RESPOMSE_NOT_OK;
		}
		currentSeat.setReservationStatus(CinemaUtils.FREE);
		currentSeat.setReservationTime(null);

		return RESPONSE_OK;
	}

	private void freeAllSeats(List<Long> seatIds) {
		for (Long id : seatIds) {
			Seat seat = seatDAO.getSeatById(id);
			seat.setReservationStatus(CinemaUtils.FREE);
			seat.setReservationTime(null);
		}
	}

	private void persistAllTickets(List<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			ticketDAO.addTicket(ticket);
		}
	}
<<<<<<< HEAD

	private String formatDate(Date date) {
=======
	
	@POST
	@Path("{ticketId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getMovieById(@PathParam("ticketId") String ticketId) {
		Ticket chosenMovie = ticketDAO.getTicketById(Long.parseLong(ticketId));
		
		chosenMovie.setChecked(true);
		return RESPONSE_OK;
	}
	
	@GET
	@Path("{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Ticket> getMovieByScreeningIdd(@PathParam("Id") Long Id) {
		return ticketDAO.getTicketsByScreeningId(Id);
	}
	

	private String formatDate(Calendar date) {
>>>>>>> origin/master
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
		return dateFormat.format(date.getTime());

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Ticket> getAllTicketss() {
		return ticketDAO.getAllTickets();
	}

	private boolean inTime(Calendar from, Calendar to) {
		if(to != null) {
			return from.get(Calendar.MINUTE) - to.get(Calendar.MINUTE) <= CinemaUtils.SEAT_RESERVATION_TIME;
		} 
		return true;
	}
<<<<<<< HEAD

	@GET
	@Path("{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Ticket> getTicketByUserName(
			@PathParam("userName") String userName) {
		return ticketDAO.getTicketsByUserName(userName);
	}

	@POST
	@Path("{ticketId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkTicket(@PathParam("ticketId") String ticketId) {
		Ticket currentTicket = ticketDAO
				.getTicketById(Long.parseLong(ticketId));
		if (currentTicket == null) {
			return RESPOMSE_NOT_OK;
		}
		currentTicket.setChecked(true);
		return RESPONSE_OK;
	}
=======
>>>>>>> origin/master
}
