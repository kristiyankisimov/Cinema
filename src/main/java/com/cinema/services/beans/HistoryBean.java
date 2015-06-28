package com.cinema.services.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.cinema.model.Ticket;

@XmlRootElement
public class HistoryBean implements Serializable {

	private static final long serialVersionUID = 4254712076858088171L;

	private Ticket ticket;

	private String formattedString;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getFormattedString() {
		return formattedString;
	}

	public void setFormattedString(String formattedString) {
		this.formattedString = formattedString;
	}

	@Override
	public int hashCode() {
		return this.ticket.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.ticket.equals(obj);
	}

	@Override
	public String toString() {
		return "HistoryBean [ticket=" + ticket + ", formattedString="
				+ formattedString + "]";
	}

}
