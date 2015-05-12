package com.cinema.login.services;

import java.io.Serializable;

import com.cinema.model.User;

public class UserContext implements Serializable {

	private static final long serialVersionUID = -1279624028672049836L;
	
	private User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	
	
}
