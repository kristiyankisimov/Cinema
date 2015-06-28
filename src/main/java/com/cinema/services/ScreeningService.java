package com.cinema.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.cinema.services.beans.ScreeningBean;

@Stateless
@Path("screenings")
public class ScreeningService {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private ScreeningDAO screeningDAO;

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

	@GET
	@Path("{screeningDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Screening getScreeningByDate(
			@PathParam("screeningDate") Date screeningDate) {
		return screeningDAO.getScreeningByDate(screeningDate);
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
