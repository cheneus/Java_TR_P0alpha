package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
		if (path == null) {
			switch (req.getMethod()) {
			case "GET":
				getAllTuitionReimbursementForms(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while (bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with TuitionReimbursementForm: " + sb.toString());
				if (sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read TuitionReimbursementForm in");

				om.setDateFormat(new SimpleDateFormat("dd-MMM-yy"));
				TuitionReimbursementForm a = om.readValue(sb.toString(), TuitionReimbursementForm.class);
				log.trace(a);
				try {
					log.trace("adding TuitionReimbursementForm to database");
					as.addTuitionReimbursementForm(a);
					log.trace(a);
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(om.writeValueAsString(a));
				} catch (Exception e) {
					LogUtil.logException(e, TRFDelegate.class);
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			switch (path) {
			case "si": {
				System.out.println("TRFSI");
				String test = req.getParameter("id");
				log.trace(test);
				getAllTRFBySnID(req, resp);
				break;
			}
			case "my": {
				System.out.println("TRFmy");
				int i = Integer.parseInt(req.getParameter("id"));
				log.trace("Retrieving a list of all my TuitionReimbursementForms");
				Set<TuitionReimbursementForm> TuitionReimbursementForms = as.getMyTuitionReimbursementForms(i);
				resp.getWriter().write(om.writeValueAsString(TuitionReimbursementForms));
				break;
			}
			case "mgr": {
				System.out.println("TRFmgr");
				log.trace(req.getHeader("eid"));
				int did = Integer.parseInt(req.getHeader("deptid"));
				log.trace("Retrieving a list of all my ppl TuitionReimbursementForms");
				int i = Integer.parseInt(req.getHeader("eid"));
				log.trace("regular");
				Set<TuitionReimbursementForm> TuitionReimbursementForms = as.getTRFNoApprByManager(i, did);
				resp.getWriter().write(om.writeValueAsString(TuitionReimbursementForms));
				break;
			}
			case "mgrsi": {
				System.out.println("TRFmgrsi");
				log.trace(req.getHeader("eid"));
				int did = Integer.parseInt(req.getHeader("deptid"));
				if (did == 2) {
					log.trace("Ran into HR");
					Set<TuitionReimbursementForm> TuitionReimbursementForms = as.getTRFNoApprByHR();
					resp.getWriter().write(om.writeValueAsString(TuitionReimbursementForms));
				} else {
					int i = Integer.parseInt(req.getHeader("eid"));
					log.trace("Retrieving a list of all my ppl TuitionReimbursementForms");
					Set<TuitionReimbursementForm> TuitionReimbursementForms = as.getTRFNoApprByManagerSI(i, did);
					resp.getWriter().write(om.writeValueAsString(TuitionReimbursementForms));
				}
				break;
			}
			default:
				TuitionReimbursementFormTimes(req, resp, Integer.parseInt(path.toString()));
			}
//		} else if (path.equals("si")) {	
//			System.out.println("TRFSI");
//			String test = req.getParameter("id");
//			log.trace(test);
//			getAllTRFBySnID(req, resp);
//		} else {
//			TuitionReimbursementFormTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void TuitionReimbursementFormTimes(HttpServletRequest req, HttpServletResponse resp,
			int TuitionReimbursementFormId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific trf with id: " + TuitionReimbursementFormId);
		PrintWriter writer = resp.getWriter();

		TuitionReimbursementForm a = new TuitionReimbursementForm();
		switch (req.getMethod()) {
		case "GET":
			a = as.getTuitionReimbursementFormById(TuitionReimbursementFormId);
			resp.getWriter().write(om.writeValueAsString(a));
			break;
		case "PUT":
			// Update the TRF in the database
			BufferedReader bf = req.getReader();
			StringBuilder sb = new StringBuilder();
			while (bf.ready()) {
				sb.append(bf.readLine());
			}
			log.trace(sb);
			a = om.readValue(sb.toString(), TuitionReimbursementForm.class);
			log.trace(a);
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

	private void getAllTuitionReimbursementForms(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all TuitionReimbursementForms");
//		Set<TuitionReimbursementForm> TuitionReimbursementForms = as.getTuitionReimbursementForms();
		Set<TuitionReimbursementForm> TuitionReimbursementForms = as.getTuitionReimbursementFormsOnView();
		resp.getWriter().write(om.writeValueAsString(TuitionReimbursementForms));
	}

	private void getAllTRFBySnID(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		Set<TuitionReimbursementForm> trf = null;
		String test = req.getParameter("id");
		if (null == req.getParameter("id")) {
			log.trace("null test");
			trf = null;
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			log.trace(id);
			log.trace("Retrieving a list of TuitionReimbursementForms for TRFSI");
			trf = as.getTuitionReimbursementFormsByNoAppr(id);
		}

		log.trace(trf);
		resp.getWriter().write(om.writeValueAsString(trf));

	}

}
