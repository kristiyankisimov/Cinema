package com.cinema.services;

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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cinema.dao.ScreeningDAO;
import com.cinema.model.Screening;
import com.cinema.model.Seat;
import com.cinema.services.beans.ScreeningBean;
import com.cinema.services.beans.TicketBean;
import com.cinema.utils.CinemaUtils;

@Stateless
@Path("screenings")
public class ScreeningService {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private ScreeningDAO screeningDAO;

	@Inject
	private TicketBean ticket;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Screening> getAllTicketss() {
		return screeningDAO.getAllScreenings();
	}
	
	@GET
	@Path("a")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ScreeningBean> getAllScreenings() {

		Collection<Screening> screenings = screeningDAO.getAllScreenings();

		Collection<ScreeningBean> screeningBeans = new ArrayList<>();

		for (Screening screening : screenings) {
			Calendar screenigTime = screening.getDate();
			if (screenigTime.after(today())) {

				ScreeningBean screeningBean = new ScreeningBean(screening);

				if (screenigTime.after(Calendar.getInstance())) {
					screeningBean.setIsComing(Boolean.TRUE);
				}

				screeningBean.setFormattedDate(formatDate(screeningBean
						.getScreening().getDate()));

				screeningBeans.add(screeningBean);
			}
		}

		return screeningBeans;
	}
	

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getScreeningByDate(@PathParam("id") Long id) {
		Screening screening = screeningDAO.getScreeningById(id);
		ticket.setScreening(screening);

		return RESPONSE_OK;
	}

	

	@GET
	@Path("current")
	@Produces(MediaType.APPLICATION_JSON)
	public Screening getCurrentMovie() {
		Screening screening = ticket.getScreening();
		Screening updatedScreening = screeningDAO.getScreeningById(screening
				.getId());
		updateSeatsStatus(updatedScreening.getSeats());
		ticket.setScreening(updatedScreening);
		return updatedScreening;
	}

	private Calendar today() {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar;
	}

	private String formatDate(Calendar date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
		return dateFormat.format(date.getTime());

	}

	private void updateSeatsStatus(List<Seat> seats) {
		for (Seat seat : seats) {
			Calendar now = Calendar.getInstance();
			boolean isInTime = inTime(now, seat.getReservationTime());
			if (seat.getReservationStatus() == CinemaUtils.IN_PROCESS
					&& !isInTime) {
				seat.setReservationStatus(CinemaUtils.FREE);
				seat.setReservationTime(null);
			}
		}
	}

	private boolean inTime(Calendar from, Calendar to) {
		if(to != null) {
			return from.get(Calendar.MINUTE) - to.get(Calendar.MINUTE) <= CinemaUtils.SEAT_RESERVATION_TIME;
		} 
		return true;
	}

}
