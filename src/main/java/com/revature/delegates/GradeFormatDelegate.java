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
import com.revature.services.GradeFormatService;
import com.revature.services.GradeFormatServiceOracle;
import com.revature.utils.LogUtil;

public class GradeFormatDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(GradeFormatDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private GradeFormatService as = new GradeFormatServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllGradeFormats(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with GradeFormat: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read GradeFormat in");
				GradeFormat a = om.readValue(sb.toString(), GradeFormat.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addGradeFormat(a);
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
			GradeFormatTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void GradeFormatTimes(HttpServletRequest req, HttpServletResponse resp, int GradeFormatId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+GradeFormatId);
		PrintWriter writer = resp.getWriter();
		
		GradeFormat a = as.getGradeFormatById(GradeFormatId);
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
			a = om.readValue(sb.toString(), GradeFormat.class);
			as.updateGradeFormat(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteGradeFormat(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllGradeFormats(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all GradeFormats");
		Set<GradeFormat> GradeFormats = as.getGradeFormats();
		resp.getWriter().write(om.writeValueAsString(GradeFormats));
	}

}
