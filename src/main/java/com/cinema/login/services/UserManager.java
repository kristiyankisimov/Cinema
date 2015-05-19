package com.cinema.login.services;

import java.net.HttpURLConnection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import com.cinema.dao.UserDAO;
import com.cinema.model.User;

@Stateless
@Path("user")
public class UserManager {

	private static final Response RESPONSE_OK = Response.ok().build();

	@Inject
	private UserDAO userDAO;

	@Inject
	private UserContext userContext;

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerUser(User newUser) {
		userDAO.addUser(newUser);
		userContext.setCurrentUser(newUser);
	}

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(User user) {
		boolean isUserValid = userDAO.validateUserCredentials(user.getUserName(), user.getPassword());

		if (!isUserValid) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}
		userContext.setCurrentUser(user);

		return RESPONSE_OK;
	}

	@GET
	@Path("authenticated")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response isAuthenticated() {
		if (userContext.getCurrentUser() == null) {
			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).build();
		}
		return RESPONSE_OK;
	}
	
	@GET
	@Path("logout")
	public void logout() {
		
	}
	
	@GET
	@Path("current")	
	@Consumes(MediaType.TEXT_PLAIN)
	public String getUser() {
		if (userContext.getCurrentUser() == null) {
			return null;
		}

		return userContext.getCurrentUser().getUserName();
	}
	
	
}
