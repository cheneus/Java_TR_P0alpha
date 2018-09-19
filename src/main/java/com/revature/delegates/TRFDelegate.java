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
import com.revature.beans.TuitionReimbursementForm;
import com.revature.services.TuitionReimbursementFormService;
import com.revature.services.TuitionReimbursementFormServiceOracle;
import com.revature.utils.LogUtil;

public class TRFDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(TRFDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private TuitionReimbursementFormService as = new TuitionReimbursementFormServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllTuitionReimbursementForms(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with TuitionReimbursementForm: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read TuitionReimbursementForm in");
				TuitionReimbursementForm a = om.readValue(sb.toString(), TuitionReimbursementForm.class);
				log.trace(a);
				try {
					log.trace("adding TuitionReimbursementForm to database");
					as.addTuitionReimbursementForm(a);
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
		} else if (path.equals("si")) {	
			System.out.println("TRFSI");
				getAllTRFBySnID(req, resp);
		} else {
			TuitionReimbursementFormTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void getAllTRFBySnID(HttpServletRequest req, HttpServletResponse resp)throws JsonProcessingException, IOException {
		int id =  Integer.parseInt(req.getParameter("id"));
		log.trace("Retrieving a list of TuitionReimbursementForms for TRFSI");
		Set<TuitionReimbursementForm> trf = as.getTuitionReimbursementFormsByNoAppr(id);
		resp.getWriter().write(om.writeValueAsString(trf));
		
	}

	private void TuitionReimbursementFormTimes(HttpServletRequest req, HttpServletResponse resp, int TuitionReimbursementFormId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific trf with id: "+TuitionReimbursementFormId);
		PrintWriter writer = resp.getWriter();
		
		TuitionReimbursementForm a = new TuitionReimbursementForm();
		log.trace(a);
		switch(req.getMethod()) {
		case "GET":
			a = as.getTuitionReimbursementFormById(TuitionReimbursementFormId);
			resp.getWriter().write(om.writeValueAsString(a));
			break;
		case "PUT":
			// Update the book in the database
			BufferedReader bf = req.getReader();
			StringBuilder sb = new StringBuilder();
			while(bf.ready()) {
				sb.append(bf.readLine());
			}
			a = om.readValue(sb.toString(), TuitionReimbursementForm.class);
			as.updateTuitionReimbursementForm(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteTuitionReimbursementForm(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllTuitionReimbursementForms(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all TuitionReimbursementForms");
//		Set<TuitionReimbursementForm> TuitionReimbursementForms = as.getTuitionReimbursementForms();
		Set<TuitionReimbursementForm> TuitionReimbursementForms = as.getTuitionReimbursementFormsOnView();
		resp.getWriter().write(om.writeValueAsString(TuitionReimbursementForms));
	}

}
