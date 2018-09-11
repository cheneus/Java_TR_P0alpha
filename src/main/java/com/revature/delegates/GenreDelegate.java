package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Department;
import com.revature.services.GenreService;
import com.revature.services.GenreServiceOracle;

public class GenreDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(GenreDelegate.class);
	private GenreService gs = new GenreServiceOracle();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		// BookStore/genre
		log.trace("Genre delegate has been called");
		if(path != null && !"".equals(path)) {
			//Trying to operate on a specific genre
			modifyGenre(req, resp, path);
		} else {
			modifyCollection(req, resp);
		}
	}
	private void modifyCollection(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// GET, POST
		switch(req.getMethod()) {
		case "GET":
			resp.getWriter().write(om.writeValueAsString(gs.getGenres()));
			break;
		case "POST":
			Department g = null;
			BufferedReader bf = req.getReader();
			StringBuilder sb = new StringBuilder();
			while(bf.ready()) {
				sb.append(bf.readLine());
			}
			if(sb.equals(new StringBuilder(""))) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			g = om.readValue(sb.toString(), Department.class);
			
			gs.addGenre(g);
			if(g.getId()==0) {
				resp.sendError(HttpServletResponse.SC_CONFLICT);
				return;
			}
			resp.setStatus(HttpServletResponse.SC_CREATED);
			resp.getWriter().write(om.writeValueAsString(g));
			break;
		default: resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return;
		}
	}
	private void modifyGenre(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {
		// GET, PUT, DELETE
		int id = Integer.parseInt(path);
		Department g = gs.getGenreById(id);
		switch(req.getMethod()) {
		case "GET": 
			resp.getWriter().write(om.writeValueAsString(g));
			break;
		case "PUT":
			BufferedReader bf = req.getReader();
			StringBuilder sb = new StringBuilder();
			while(bf.ready()) {
				sb.append(bf.readLine());
			}
			g = om.readValue(sb.toString(), Department.class);
			gs.updateGenre(g);
			break;
		case "DELETE": 
			gs.deleteGenre(g);
			break;
		default: resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED); return;
		}
		
	}

}
