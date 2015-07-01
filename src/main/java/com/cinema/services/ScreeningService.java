package com.cinema.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
import com.cinema.services.beans.ScreeningBean;
import com.cinema.services.beans.TicketBean;

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
	public Collection<ScreeningBean> getAllScreenings() {

		Collection<Screening> screenings = screeningDAO.getAllScreenings();

		Collection<ScreeningBean> screeningBeans = new ArrayList<>();

		for (Screening screening : screenings) {
			Date screenigTime = screening.getDate();
			if (screenigTime.after(today())) {

				ScreeningBean screeningBean = new ScreeningBean(screening);

				if (screenigTime.after(new Date())) {
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
		ticket.setScreening(updatedScreening);
		return updatedScreening;
	}

	private Date today() {
		Calendar callendar = Calendar.getInstance();

		callendar.set(Calendar.HOUR, 0);
		callendar.set(Calendar.MINUTE, 0);
		callendar.set(Calendar.SECOND, 0);

		return callendar.getTime();
	}

	private String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

		return dateFormat.format(date);

	}
}
