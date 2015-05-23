package com.cinema.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Seat implements Serializable {

	private static final long serialVersionUID = -5253501132450911505L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer seatNumber;

	private Boolean isReserved;

	@Temporal(TemporalType.DATE)
	private Date reservationTime;

	public Seat() {

	}

	public Seat(int seatNumber) {
		this.isReserved = Boolean.FALSE;
		this.seatNumber = seatNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Boolean getIsReserved() {
		return isReserved;
	}

	public void setIsReserved(Boolean isReserved) {
		this.isReserved = isReserved;
	}

	public Date getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
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
		Seat seat = (Seat) obj;
		if (id != null) {
			if (!id.equals(seat.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", seatNumber=" + seatNumber
				+ ", isReserved=" + isReserved + ", reservationTime="
				+ reservationTime + "]";
	}

}
