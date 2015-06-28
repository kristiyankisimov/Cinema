package com.cinema.services.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.cinema.model.Screening;

@XmlRootElement
public class ScreeningBean implements Serializable {

	private static final long serialVersionUID = -2669322374615582829L;

	private Screening screening;

	private Boolean isComing;

	private String formattedDate;

	public ScreeningBean() {

	}

	public ScreeningBean(Screening screening) {
		this.isComing = Boolean.FALSE;
		this.screening = screening;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public Boolean getIsComing() {
		return isComing;
	}

	public void setIsComing(Boolean isComing) {
		this.isComing = isComing;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	@Override
	public int hashCode() {
		return this.screening.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.screening.equals(obj);
	}

	@Override
	public String toString() {
		return "ScreeningBean [screening=" + screening + ", isComing="
				+ isComing + "]";
	}

}
