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
import com.revature.beans.InfoRequest;
import com.revature.beans.TuitionReimbursementForm;
import com.revature.services.InfoRequestService;
import com.revature.services.InfoRequestServiceOracle;
import com.revature.utils.LogUtil;

public class InfoReqDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(InfoReqDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private InfoRequestService as = new InfoRequestServiceOracle();

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if (path == null) {
			switch (req.getMethod()) {
			case "GET":
				getAllInfoRequests(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while (bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with InfoRequest: " + sb.toString());
				if (sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read InfoRequest in");
				InfoRequest a = om.readValue(sb.toString(), InfoRequest.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addInfoReq(a);
					log.trace(a);
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(om.writeValueAsString(a));
				} catch (Exception e) {
					LogUtil.logException(e, InfoRequest.class);
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			switch (path) {
			case "my": {
				System.out.println("inforeq my");
				log.trace(req.getHeader("eid"));
				int i = Integer.parseInt(req.getHeader("eid"));
				log.trace("Retrieving a list of all my InfoReq");
				Set<InfoRequest> infoR = as.getUrInfoReq(i);
				resp.getWriter().write(om.writeValueAsString(infoR));
				break;
			}
			default:
				InfoRequestTimes(req, resp, Integer.parseInt(path.toString()));
			}
		}
	}

	private void InfoRequestTimes(HttpServletRequest req, HttpServletResponse resp, int InfoRequestId)
			throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: " + InfoRequestId);
		PrintWriter writer = resp.getWriter();

		InfoRequest a = as.getInfoReqById(InfoRequestId);
		switch (req.getMethod()) {
		case "GET":
			resp.getWriter().write(om.writeValueAsString(a));
			break;
		case "PUT":
			// Update the book in the database
			BufferedReader bf = req.getReader();
			StringBuilder sb = new StringBuilder();
			while (bf.ready()) {
				sb.append(bf.readLine());
			}
			a = om.readValue(sb.toString(), InfoRequest.class);
			as.updateInfoReq(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteInfoReq(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllInfoRequests(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all InfoRequests");
		Set<InfoRequest> InfoRequests = as.getInfoReq();
		resp.getWriter().write(om.writeValueAsString(InfoRequests));
	}

}
