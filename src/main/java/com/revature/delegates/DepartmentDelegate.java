package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Department;
import com.revature.services.DepartmentService;
import com.revature.services.DepartmentServiceOracle;
import com.revature.utils.LogUtil;

public class DepartmentDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(DepartmentDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private DepartmentService as = new DepartmentServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllDepartments(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with Department: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read Department in");
				Department a = om.readValue(sb.toString(), Department.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addDepartment(a);
					log.trace(a);
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(om.writeValueAsString(a));
				} catch(Exception e) {
					LogUtil.logException(e, BookDelegate.class);
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			DepartmentTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void DepartmentTimes(HttpServletRequest req, HttpServletResponse resp, int DepartmentId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+DepartmentId);
		PrintWriter writer = resp.getWriter();
		
		Department a = as.getDepartmentById(DepartmentId);
		switch(req.getMethod()) {
		case "GET":
			resp.getWriter().write(om.writeValueAsString(a));
			break;
		case "PUT":
			// Update the book in the database
			BufferedReader bf = req.getReader();
			StringBuilder sb = new StringBuilder();
			while(bf.ready()) {
				sb.append(bf.readLine());
			}
			a = om.readValue(sb.toString(), Department.class);
			as.updateDepartment(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteDepartment(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllDepartments(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all Departments");
		int id = Integer.parseInt(req.getParameter("id"));
		Department Departments = as.getDepartmentById(id);
		resp.getWriter().write(om.writeValueAsString(Departments));
	}

}
