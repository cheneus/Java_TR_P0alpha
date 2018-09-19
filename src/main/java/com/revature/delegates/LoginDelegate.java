package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;
import com.revature.beans.Login;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceOracle;
import com.revature.services.LoginService;
import com.revature.services.LoginServiceOracle;

public class LoginDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(LoginDelegate.class);
	private LoginService ls = new LoginServiceOracle();
	private EmployeeService es = new EmployeeServiceOracle();
	private ObjectMapper om = new ObjectMapper();

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		log.trace(req.getMethod() + " received by login delegate");
		HttpSession session = req.getSession();

		switch (req.getMethod()) {
		case "GET":
			checkLogin(req, resp);
			break;
		case "POST":
			// logging in
			Employee emp = (Employee) session.getAttribute("loggedEmployee");
			Login login = (Login) session.getAttribute("loggedLogin");
			if (emp != null || login != null) {
				respondWithUser(resp, emp, login);
			} else {
				checkLogin(req, resp);
			}
			break;
		case "DELETE":
			// logging out
			session.invalidate();
			// disassociates an id with a session and response says to delete cookie
			log.trace("User logged out");
			break;
		default:
			break;
		}
	}

	private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.trace("Logging in!");
		HttpSession session = req.getSession();
		Login l = (Login) session.getAttribute("loggedLogin");
		Employee e = (Employee) session.getAttribute("loggedEmployee");
		log.trace(l);
		log.trace(e);
		if (l != null || e != null) {
			respondWithUser(resp, e, l);
		} else {
			// Need to see if we are an employee. Then we need to see if we are a Login.
			// Then we need to store that information in the session object.
			String username = req.getParameter("user");
			String password = req.getParameter("pass");
			log.trace((username + " " + password));
			l = ls.getLogin(username, password);
			if (l != null) {
				e = es.getEmployee(l.getEmployee_id());
				session.setAttribute("loggedLogin", l);
			}

			if (e != null) {
				log.trace("employee being added to session");
				session.setAttribute("loggedEmployee", e);
			}
			if (l == null && e == null) {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No user found with those credentials");
			} else {
				respondWithUser(resp, e, l);
			}
		}
	}

	private void respondWithUser(HttpServletResponse resp, Employee emp,Login l) throws IOException {
//		private void respondWithUser(HttpServletResponse resp, Employee emp, Login l) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		String c = om.writeValueAsString(l);
//		String e = om.writeValueAsString(emp);
		StringBuilder sb = new StringBuilder("{\"Login\":");
		sb.append(c);
//		sb.append(", \"employee\":");
//		sb.append(e);
		sb.append("}");
		resp.getWriter().write(sb.toString());
	}

}
