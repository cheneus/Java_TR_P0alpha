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
import com.revature.beans.Status;
import com.revature.services.StatusService;
import com.revature.services.StatusServiceOracle;
import com.revature.utils.LogUtil;

public class StatusDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(StatusDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private StatusService as = new StatusServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllStatuss(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with Status: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read Status in");
				Status a = om.readValue(sb.toString(), Status.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addStatus(a);
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
			StatusTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void StatusTimes(HttpServletRequest req, HttpServletResponse resp, int StatusId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+StatusId);
		PrintWriter writer = resp.getWriter();
		
		Status a = as.getStatusById(StatusId);
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
			a = om.readValue(sb.toString(), Status.class);
			as.updateStatus(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteStatus(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllStatuss(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all Statuss");
		int id = Integer.parseInt(req.getParameter("id"));
		Status Statuss = as.getStatusById(id);
		resp.getWriter().write(om.writeValueAsString(Statuss));
	}

}
