package com.cinema.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 7534356812823137726L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
//	private String firstName;
//	private String lastName;
	private String password;
	
	public User() {
	}
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
//	@OneToMany(cascade=CascadeType.PERSIST)
//	private List<Movie> watchedMovies = new ArrayList<>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + "]";
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", userName=" + userName + ", firstName="
//				+ firstName + ", lastName=" + lastName + ", password="
//				+ password + "]";
//	}
	

}
