package com.cinema.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cinema.dao.UserDAO;
import com.cinema.model.User;

@Stateless
@Path("users")
public class UserService {

	@Inject
	private UserDAO userDAO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUserNames() {
		return userDAO.getUserNames();
	}
}
