package com.cinema.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.login.services.UserContext;
import com.cinema.model.User;

@WebFilter(urlPatterns = {"/html/*", "/rest/movies", "/rest/movies/*",
		"/rest/screenings", "/rest/screenings/*", 
		"/rest/tickets", "/rest/tickets/*",
		"/rest/movieInformation"})
public class ApplicationFilter implements Filter {

	private static final String PATH_LOGIN_PAGE = "/login.html";
	
	@Inject
	private UserContext context;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (!isHttpRequest(request, response)) {
			return;
		}
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		httpResponse
				.setHeader("Cache-Control",
						"no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
		httpResponse.setHeader("Pragma", "no-cache");

		User currentUser = context.getCurrentUser();

		if (currentUser == null) {
			String loginURL = httpRequest.getContextPath() + PATH_LOGIN_PAGE;
			httpResponse.sendRedirect(loginURL);
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	private boolean isHttpRequest(ServletRequest request,
			ServletResponse response) {
		return request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse;
	}

}
