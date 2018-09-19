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
import com.revature.beans.GradeFormat;
import com.revature.beans.GradeStatus;
import com.revature.services.GradeStatusService;
import com.revature.services.GradeStatusServiceOracle;
import com.revature.utils.LogUtil;

public class GradeStatusDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(GradeStatusDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private GradeStatusService as = new GradeStatusServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllGradeStatus(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with GradeStatus: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read GradeStatus in");
				GradeStatus a = om.readValue(sb.toString(), GradeStatus.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addGradeStatus(a);
					log.trace(a);
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(om.writeValueAsString(a));
				} catch(Exception e) {
					LogUtil.logException(e, GradeStatusDelegate.class);
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			GradeStatusTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void GradeStatusTimes(HttpServletRequest req, HttpServletResponse resp, int GradeStatusId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+GradeStatusId);
		PrintWriter writer = resp.getWriter();
		
		GradeStatus a = as.getGradeStatusById(GradeStatusId);
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
			a = om.readValue(sb.toString(), GradeStatus.class);
			as.updateGradeStatus(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteGradeStatus(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllGradeStatus(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all GradeStatuss");
		Set<GradeStatus> GradeStatus = as.getGradeStatus();
		resp.getWriter().write(om.writeValueAsString(GradeStatus));
	}

}
