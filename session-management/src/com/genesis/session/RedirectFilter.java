package com.genesis.session;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class RedirectFilter implements Filter {

	boolean isValidRequest = false;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequestWrapper wrapperRequest = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
		StringBuffer uri = wrapperRequest.getRequestURL();
		if (uri.toString().contains("jsp")) {
			isValidRequest = true;
		}
		if (isValidRequest) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			((HttpServletResponse) servletResponse).sendRedirect("/session-management/login.jsp");
		}
	}

	@Override
	public void destroy() {
	}
}
