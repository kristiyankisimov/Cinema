package com.cinema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
		@NamedQuery(name = "getScreeningById", query = "SELECT s FROM Screening s WHERE s.id = :id"),
		@NamedQuery(name = "getScreeningByDate", query = "SELECT s FROM Screening s WHERE s.date = :date"),
		@NamedQuery(name = "getScreeningByHall", query = "SELECT s FROM Screening s WHERE s.hall.id = :HALL_ID"),
		@NamedQuery(name = "getAllScreenings", query = "SELECT s FROM Screening s") })
public class Screening implements Serializable {

	private static final long serialVersionUID = -4706458560125137984L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Movie movie;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Hall hall;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Seat> seats = new ArrayList<>();

	public Screening() {

	}

	public Screening(Calendar date, Movie movie, Hall hall, List<Seat> seats) {
		super();
		this.date = date;
		this.movie = movie;
		this.hall = hall;
		this.seats = seats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Screening)) {
			return false;
		}
		Screening screening = (Screening) obj;
		if (id != null) {
			if (!id.equals(screening.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Screening [id=" + id + ", date=" + date + "]";
	}

}
