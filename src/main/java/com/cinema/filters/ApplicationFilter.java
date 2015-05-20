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

@WebFilter("/index.html")
public class ApplicationFilter implements Filter {

	@Inject
	private UserContext contect;
	
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
		
        User currentUser = contect.getCurrentUser();
        
        if(currentUser == null) {
        	String loginURL = httpRequest.getContextPath() + "/login.html";
        	httpResponse.sendRedirect(loginURL);
        	return;
        }
        
        chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	private boolean isHttpRequest(ServletRequest request, ServletResponse response) {
		return request instanceof HttpServletRequest && response instanceof HttpServletResponse;
	}

}
