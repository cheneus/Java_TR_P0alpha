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
//	private LoginService cs = new LoginServiceOracle();
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
			Login cust = (Login) session.getAttribute("loggedLogin");
			if (emp != null || cust != null) {
				respondWithUser(resp, emp, cust);
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
		Login c = (Login) session.getAttribute("loggedLogin");
		Employee e = (Employee) session.getAttribute("loggedEmployee");
		if (c != null || e != null) {
			respondWithUser(resp, e, c);
		} else {

			// Need to see if we are an employee. Then we need to see if we are a Login.
			// Then we need to store that information in the session object.
			String username = req.getParameter("user");
			String password = req.getParameter("pass");
			log.trace((username + " " + password));
			c = cs.getLogin(username, password);
			e = es.getEmployee(username, password);

			if (c != null) {
				log.trace("Login being added to session");
				session.setAttribute("loggedLogin", c);
			}
			if (e != null) {
				log.trace("employee being added to session");
				session.setAttribute("loggedEmployee", e);
			}
			if (c == null && e == null) {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No user found with those credentials");
			} else {
				respondWithUser(resp, e, c);
			}
		}
	}

	private void respondWithUser(HttpServletResponse resp, Employee emp, Login cust) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		String c = om.writeValueAsString(cust);
		String e = om.writeValueAsString(emp);
		StringBuilder sb = new StringBuilder("{\"Login\":");
		sb.append(c);
		sb.append(", \"employee\":");
		sb.append(e);
		sb.append("}");
		resp.getWriter().write(sb.toString());
	}

}
