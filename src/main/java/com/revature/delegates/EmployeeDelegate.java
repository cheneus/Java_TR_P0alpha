package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceOracle;
import com.revature.utils.LogUtil;

public class EmployeeDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(EmployeeDelegate.class);
	private EmployeeService em = new EmployeeServiceOracle();
	private ObjectMapper om = new ObjectMapper();

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		log.trace(req.getMethod() + " received by employee delegate");
		HttpSession session = req.getSession();
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if (path == null) {
			switch (req.getMethod()) {
			case "GET":
				getAllEmployee(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while (bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with Employee: " + sb.toString());
				if (sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read Employee in");
				Employee a = om.readValue(sb.toString(), Employee.class);
				log.trace(a);
				try {
					log.trace("adding employee to database");
					em.addEmployee(a);
					log.trace(a);
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(om.writeValueAsString(a));
				} catch (Exception e) {
					LogUtil.logException(e, EmployeeDelegate.class);
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			EmployeeTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void EmployeeTimes(HttpServletRequest req, HttpServletResponse resp, int eid)
			throws JsonProcessingException, IOException {
		log.trace("Operating on a specific employee with id: " + eid);
		PrintWriter writer = resp.getWriter();

		Employee a = em.getEmployeeById(eid);
		switch (req.getMethod()) {
		case "GET":
			resp.getWriter().write(om.writeValueAsString(a));
			break;
		case "PUT":
			// Update the book in the database
			BufferedReader bf = req.getReader();
			StringBuilder sb = new StringBuilder();
			while (bf.ready()) {
				sb.append(bf.readLine());
			}
			log.trace(sb.toString());
			a = om.readValue(sb.toString(), Employee.class);
			em.updateEmployee(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			em.deleteEmployee(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllEmployee(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all Employees");
		Set<Employee> stat = em.getEmployees();
		resp.getWriter().write(om.writeValueAsString(stat));
	}

}
