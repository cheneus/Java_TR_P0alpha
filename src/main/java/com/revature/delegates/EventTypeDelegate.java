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
import com.revature.beans.EventType;
import com.revature.services.EventTypeService;
import com.revature.services.EventTypeServiceOracle;
import com.revature.utils.LogUtil;

public class EventTypeDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(EventTypeDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private EventTypeService as = new EventTypeServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllEventTypes(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with EventType: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read EventType in");
				EventType a = om.readValue(sb.toString(), EventType.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addEventType(a);
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
			EventTypeTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void EventTypeTimes(HttpServletRequest req, HttpServletResponse resp, int EventTypeId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+EventTypeId);
		PrintWriter writer = resp.getWriter();
		
		EventType a = as.getEventTypeById(EventTypeId);
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
			a = om.readValue(sb.toString(), EventType.class);
			as.updateEventType(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteEventType(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllEventTypes(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all EventTypes");
		Set<EventType> EventTypes = as.getEventTypes();
		resp.getWriter().write(om.writeValueAsString(EventTypes));
	}

}
