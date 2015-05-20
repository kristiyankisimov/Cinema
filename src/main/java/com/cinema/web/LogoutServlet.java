//package com.cinema.web;
//
//import java.io.IOException;
//
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.cinema.login.services.UserContext;
//
//@WebServlet("/logout")
//public class LogoutServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	@Inject
//	private UserContext context;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		context.setCurrentUser(null);
//		response.sendRedirect(request.getServletContext() + "/rest/login.html");
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		this.doGet(request, response);
//	}
//
//}
