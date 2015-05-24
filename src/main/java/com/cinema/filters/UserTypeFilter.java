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

@WebFilter("/user_type_filter.html")
public class UserTypeFilter implements Filter {

	private static final String PATH_ADMIN = "/admin";
	private static final String PATH_USER = "/user";
	private static final String PATH_PAGES_ROOT = "/html";
	private static final String PATH_INDEX_PAGE = "/index.html";
	private static final String PATH_LOGIN_PAGE = "/login.html";
	
	@Inject
	private UserContext context;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		if (!isHttpRequest(servletRequest, servletResponse)) {
			return;
		}

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		User currentUser = context.getCurrentUser();

		if (currentUser == null) {
			String loginURL = request.getContextPath() + PATH_LOGIN_PAGE;
			response.sendRedirect(loginURL);
			return;
		}

		String redirectURL = request.getContextPath() + PATH_PAGES_ROOT
				+ (currentUser.getIsAdmin() ? PATH_ADMIN : PATH_USER) + PATH_INDEX_PAGE;

		response.sendRedirect(redirectURL);

		filterChain.doFilter(servletRequest, servletResponse);

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
