package com.cinema.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = -6685667598944744746L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String characters;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharacters() {
		return characters;
	}

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		
		Movie movie = (Movie)obj;
		if (id != null) {
			if (!id.equals(movie.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", characters="
				+ characters + ", description=" + description + "]";
	}
	
	

}
