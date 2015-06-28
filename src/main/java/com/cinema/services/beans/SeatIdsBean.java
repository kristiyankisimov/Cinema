package com.cinema.services.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeatIdsBean implements Serializable {

	private static final long serialVersionUID = -3767693880091997958L;
	
	private List<Long> seatsIds;

	public List<Long> getSeatsIds() {
		return seatsIds;
	}

	public void setSeatsIds(List<Long> seatsIds) {
		this.seatsIds = seatsIds;
	}

}
