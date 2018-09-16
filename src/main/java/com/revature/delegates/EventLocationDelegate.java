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
import com.revature.beans.EventLocation;
import com.revature.services.EventLocationService;
import com.revature.services.EventLocationServiceOracle;
import com.revature.utils.LogUtil;

public class EventLocationDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(EventLocationDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private EventLocationService as = new EventLocationServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllEventLocations(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with EventLocation: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read EventLocation in");
				EventLocation a = om.readValue(sb.toString(), EventLocation.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addEventLocation(a);
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
			EventLocationTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void EventLocationTimes(HttpServletRequest req, HttpServletResponse resp, int EventLocationId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+EventLocationId);
		PrintWriter writer = resp.getWriter();
		
		EventLocation a = as.getEventLocationById(EventLocationId);
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
			a = om.readValue(sb.toString(), EventLocation.class);
			as.updateEventLocation(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteEventLocation(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllEventLocations(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all EventLocations");
		int id = Integer.parseInt(req.getParameter("id"));
		EventLocation EventLocations = as.getEventLocationById(id);
		resp.getWriter().write(om.writeValueAsString(EventLocations));
	}

}
