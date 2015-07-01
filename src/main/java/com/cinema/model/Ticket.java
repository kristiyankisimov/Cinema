package com.cinema.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
		@NamedQuery(name = "getTicketById", query = "SELECT t FROM Ticket t WHERE t.id = :id"),
		@NamedQuery(name = "getTicketsByUserName", query = "SELECT t FROM Ticket t WHERE t.user.userName = :userName"),
		@NamedQuery(name = "getTicketsByScreeningId", query = "SELECT t FROM Ticket t WHERE t.screening.id = :screeningId"),
		@NamedQuery(name = "getAllCheckedTickets", query = "SELECT t FROM Ticket t WHERE t.checked = :checked"),
		@NamedQuery(name = "getAllTickets", query = "SELECT t FROM Ticket t"),
		@NamedQuery(name = "getAllTicketsSeats", query = "SELECT t.seat FROM Ticket t") })
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	private Screening screening;

	@OneToOne(cascade = CascadeType.ALL)
	private Seat seat;

	private Boolean checked;

	public Ticket() {

	}

	public Ticket(User user, Screening screening, Seat seat, Boolean checked) {
		this.user = user;
		this.screening = screening;
		this.seat = seat;
		this.checked = checked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
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
		Ticket other = (Ticket) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", user=" + user + "]";
	}

}
