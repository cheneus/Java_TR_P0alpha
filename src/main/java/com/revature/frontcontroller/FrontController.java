package com.revature.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.revature.delegates.FrontControllerDelegate;
import com.revature.delegates.RequestDelegate;

public class FrontController extends DefaultServlet {
	private static final long serialVersionUID = 1338673954657621764L;

	private Logger log = Logger.getLogger(FrontController.class);
	private FrontControllerDelegate delegate = new RequestDelegate();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.trace(request.getRequestURI());
		if(request.getRequestURI().substring(request.getContextPath().length()).startsWith("/static")) {
			log.trace("static content");
			super.doGet(request, response);
		} else {
			log.trace("non-static content");
			delegate.process(request,response);
		}
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request,response);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
