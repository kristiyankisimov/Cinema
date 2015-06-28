package com.cinema.services.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import com.cinema.model.Movie;
import com.cinema.model.Screening;

@SessionScoped
public class TicketBean implements Serializable {

	private static final long serialVersionUID = -6890082163819147277L;

	private Movie chosenMovie;

	private Screening screening;

	public Movie getChosenMovie() {
		return chosenMovie;
	}

	public void setChosenMovie(Movie chosenMovie) {
		this.chosenMovie = chosenMovie;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

}
