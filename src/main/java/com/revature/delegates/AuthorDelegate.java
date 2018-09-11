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
import com.revature.beans.Author;
import com.revature.services.AuthorService;
import com.revature.services.AuthorServiceOracle;
import com.revature.utils.LogUtil;

public class AuthorDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(AuthorDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private AuthorService as = new AuthorServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllAuthors(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with author: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read author in");
				Author a = om.readValue(sb.toString(), Author.class);
				log.trace(a);
				try {
					log.trace("adding book to database");
					as.addAuthor(a);
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
			authorTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void authorTimes(HttpServletRequest req, HttpServletResponse resp, int authorId) throws JsonProcessingException, IOException {
		log.trace("Operating on a specific book with id: "+authorId);
		PrintWriter writer = resp.getWriter();
		Author a = as.getAuthorById(authorId);
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
			a = om.readValue(sb.toString(), Author.class);
			as.updateAuthor(a);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(a));
			break;
		case "DELETE":
			as.deleteAuthor(a);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private void getAllAuthors(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		log.trace("Retrieving a list of all authors");
		Set<Author> authors = as.getAuthors();
		resp.getWriter().write(om.writeValueAsString(authors));
	}

}
