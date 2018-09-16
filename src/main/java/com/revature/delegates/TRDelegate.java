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
import com.revature.beans.TuitionReimbursement;
import com.revature.services.TuitionReimbursementService;
import com.revature.services.TuitionReimbursementServiceOracle;
import com.revature.utils.LogUtil;

public class TRDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(TRDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private TuitionReimbursementService as = new TuitionReimbursementServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllTuitionReimbursements(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with TuitionReimbursement: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read TuitionReimbursement in");
				TuitionReimbursement a = om.readValue(sb.toString(), TuitionReimbursement.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addTuitionReimbursement(a);
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
			TuitionReimbursementTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void TuitionReimbursementTimes(HttpServletRequest req, HttpServletResponse resp, int TuitionReimbursementId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+TuitionReimbursementId);
		PrintWriter writer = resp.getWriter();
		
		TuitionReimbursement a = as.getTuitionReimbursementById(TuitionReimbursementId);
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
			a = om.readValue(sb.toString(), TuitionReimbursement.class);
			as.updateTuitionReimbursement(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteTuitionReimbursement(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllTuitionReimbursements(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all TuitionReimbursements");
		Set<TuitionReimbursement> TuitionReimbursements = as.getTuitionReimbursements();
		resp.getWriter().write(om.writeValueAsString(TuitionReimbursements));
	}

}
