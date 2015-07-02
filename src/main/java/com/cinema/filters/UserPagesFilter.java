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

@WebFilter("/html/user/*")
public class UserPagesFilter implements Filter {

	private static final String PATH_LOGIN_PAGE = "/login.html";

	@Inject
	private UserContext context;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		if (!isHttpRequest(servletRequest, servletResponse)) {
			return;
		}

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");

		User currentUser = context.getCurrentUser();

		if (currentUser == null || currentUser.getIsAdmin()) {
			context.setCurrentUser(null);
			request.getSession().invalidate();
			String loginURL = request.getContextPath() + PATH_LOGIN_PAGE;
			response.sendRedirect(loginURL);
			return;
		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private boolean isHttpRequest(ServletRequest request,
			ServletResponse response) {
		return request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse;
	}

}
