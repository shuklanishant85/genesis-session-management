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
import javax.servlet.http.HttpSession;

@WebServlet("/Session")
public class SessionServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -8690590565536927031L;
	private final String username = "nishant";
	private final String password = "Password@123";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30 * 60);
		session.setAttribute("user_name", user);
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

	}

}
