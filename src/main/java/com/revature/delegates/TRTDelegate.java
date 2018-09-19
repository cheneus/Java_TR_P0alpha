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
import com.revature.beans.TuitionReimbursementType;
import com.revature.services.TuitionReimbursementTypeService;
import com.revature.services.TuitionReimbursementTypeServiceOracle;
import com.revature.utils.LogUtil;

public class TRTDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(TRTDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private TuitionReimbursementTypeService as = new TuitionReimbursementTypeServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllTuitionReimbursementTypes(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with TuitionReimbursementType: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read TuitionReimbursementType in");
				TuitionReimbursementType a = om.readValue(sb.toString(), TuitionReimbursementType.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addTuitionReimbursementType(a);
					log.trace(a);
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(om.writeValueAsString(a));
				} catch(Exception e) {
					LogUtil.logException(e, TRTDelegate.class);
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			TuitionReimbursementTypeTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void TuitionReimbursementTypeTimes(HttpServletRequest req, HttpServletResponse resp, int TuitionReimbursementTypeId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+TuitionReimbursementTypeId);
		PrintWriter writer = resp.getWriter();
		
		TuitionReimbursementType a = as.getTuitionReimbursementTypeById(TuitionReimbursementTypeId);
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
			a = om.readValue(sb.toString(), TuitionReimbursementType.class);
			as.updateTuitionReimbursementType(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteTuitionReimbursementType(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllTuitionReimbursementTypes(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all TuitionReimbursementTypes");
		Set<TuitionReimbursementType> TuitionReimbursementTypes = as.getTuitionReimbursementTypes();
		resp.getWriter().write(om.writeValueAsString(TuitionReimbursementTypes));
	}

}
