package com.genesis.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author nisshukl0
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -3798923320165799341L;
	private final String username = "nishant";
	private final String password = "Password@123";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		if (username.equals(user) && password.equals(pwd)) {
			Cookie loginCookie = new Cookie("session_cookie", user);
			loginCookie.setMaxAge(60 * 60);
			response.addCookie(loginCookie);
			response.sendRedirect("/login-success.jsp");
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/session-management/login.jsp");
			PrintWriter out = response.getWriter();
			out.write("<font color=red>Either user name or password is wrong.</font>");
			dispatcher.include(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/session-management/login.jsp");
		dispatcher.include(request, response);
	}

}
