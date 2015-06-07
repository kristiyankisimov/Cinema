package com.cinema.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
		@NamedQuery(name = "getScreeningByDate", query = "SELECT s FROM Screening s WHERE s.date = :date"),
		@NamedQuery(name = "getScreeningByHall", query = "SELECT s FROM Screening s WHERE s.hall.id = :HALL_ID"),
		@NamedQuery(name = "getAllScreenings", query = "SELECT s FROM Screening s") })
public class Screening {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Movie movie;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Hall hall;

	public Screening() {
		
	}
	public Screening(Date date, Movie movie, Hall hall) {
		super();
		this.date = date;
		this.movie = movie;
		this.hall = hall;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
		if (!(obj instanceof User)) {
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
