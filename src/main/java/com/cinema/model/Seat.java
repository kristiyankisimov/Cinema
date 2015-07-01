package com.cinema.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({ @NamedQuery(name = "getSeatById", query = "SELECT s FROM Seat s WHERE s.id = :id") })
public class Seat implements Serializable {

	private static final long serialVersionUID = -5253501132450911505L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer rowNumber;

	private Integer colNumber;

	private Integer reservationStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar reservationTime;

	public Seat() {

	}

	public Seat(int rowNumber, int colNumber) {
		this.reservationStatus = Integer.valueOf(0);
		this.rowNumber = rowNumber;
		this.colNumber = colNumber;

	}

	public Seat(Integer rowNumber, Integer colNumber,
			Integer reservationStatus, Calendar reservationTime) {
		this.rowNumber = rowNumber;
		this.colNumber = colNumber;
		this.reservationStatus = reservationStatus;
		this.reservationTime = reservationTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getColNumber() {
		return colNumber;
	}

	public void setColNumber(Integer colNumber) {
		this.colNumber = colNumber;
	}

	public Integer getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(Integer reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public Calendar getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Calendar reservationTime) {
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
		if (!(obj instanceof Seat)) {
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
		return "Seat [id=" + id + ", rowNumber=" + rowNumber + ", colNumber="
				+ colNumber + ", isReserved=" + reservationStatus
				+ ", reservationTime=" + reservationTime + "]";
	}

}
