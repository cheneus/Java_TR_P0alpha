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
import com.revature.beans.Address;
import com.revature.services.AddressService;
import com.revature.services.AddressServiceOracle;
import com.revature.utils.LogUtil;

public class AddressDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(AddressDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private AddressService as = new AddressServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllAddresss(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with Address: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read Address in");
				Address a = om.readValue(sb.toString(), Address.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addAddress(a);
					log.trace(a);
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(om.writeValueAsString(a));
				} catch(Exception e) {
					LogUtil.logException(e, AddressDelegate.class);
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			AddressTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void AddressTimes(HttpServletRequest req, HttpServletResponse resp, int AddressId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+AddressId);
		PrintWriter writer = resp.getWriter();
		log.trace(AddressId);
		Address a = as.getAddressById(AddressId);
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
			a = om.readValue(sb.toString(), Address.class);
			as.updateAddress(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteAddress(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllAddresss(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all Addresss");
		int id = Integer.parseInt(req.getParameter("id"));
		Address Addresss = as.getAddressById(id);
		resp.getWriter().write(om.writeValueAsString(Addresss));
	}

}
