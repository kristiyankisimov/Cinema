package com.cinema.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
		@NamedQuery(name = "getMovieByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title"),
		@NamedQuery(name = "getAllMovies", query = "SELECT m FROM Movie m"),
		@NamedQuery(name = "getMovieById", query = "SELECT m FROM Movie M WHERE m.id = :id") })
public class Movie implements Serializable {

	private static final long serialVersionUID = -6685667598944744746L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String description;

	private Integer length;

	private Double price;

	public Movie() {

	}

	public Movie(String title, String description, Integer length, Double price) {
		this.title = title;
		this.description = description;
		this.length = length;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		if (!(obj instanceof Movie)) {
			return false;
		}

		Movie movie = (Movie) obj;
		if (id != null) {
			if (!id.equals(movie.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + "]";
	}

}
