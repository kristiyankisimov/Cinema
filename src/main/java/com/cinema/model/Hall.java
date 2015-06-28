package com.cinema.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({ @NamedQuery(name = "getAllSeats", query = "SELECT h FROM Hall h") })
public class Hall {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer number;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Seat> seats = new ArrayList<>();

	public Hall() {

	}

	public Hall(int number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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
		if (!(obj instanceof User)) {
			return false;
		}
		Hall hall = (Hall) obj;
		if (id != null) {
			if (!id.equals(hall.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Hall [id=" + id + ", number=" + number + ", seats=" + seats
				+ "]";
	}

}
