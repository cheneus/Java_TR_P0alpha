package com.revature.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Book;
import com.revature.services.BookService;
import com.revature.services.BookServiceOracle;
import com.revature.utils.LogUtil;

public class BookDelegate implements FrontControllerDelegate {
	private Logger log = Logger.getLogger(BookDelegate.class);
	private ObjectMapper om = new ObjectMapper();
	private BookService bs = new BookServiceOracle();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String path = (String) req.getAttribute("path");
		log.trace(path);
		if(path==null) {
			switch(req.getMethod()) {
			case "GET": 
				getAllBooks(req, resp);
				break;
			case "POST":
				BufferedReader bf = req.getReader();
				StringBuilder sb = new StringBuilder();
				while(bf.ready()) {
					sb.append(bf.readLine());
				}
				log.trace("Post called with book: "+sb.toString());
				if(sb.equals(new StringBuilder(""))) {
					log.trace("String was empty, no data found");
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				log.trace("Attempt to read book in");
				Book b = om.readValue(sb.toString(), Book.class);
				log.trace(b);
				try {
					log.trace("adding book to database");
					bs.addBook(b);
					log.trace(b);
					resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.getWriter().write(om.writeValueAsString(b));
				} catch(Exception e) {
					LogUtil.logException(e, BookDelegate.class);
					resp.sendError(HttpServletResponse.SC_CONFLICT);
				}
				break;
			default:
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			bookTimes(req, resp, Integer.parseInt(path.toString()));
		}
	}

	private void getAllBooks(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.trace("Retrieve all book from database");
		Set<Book> books = bs.getBooks();
		log.trace(om.writeValueAsString(books));
		resp.getWriter().write(om.writeValueAsString(books));
	}

	private void bookTimes(HttpServletRequest req, HttpServletResponse resp, int bookId) throws IOException {
		log.trace("Operating on a specific book with id: "+bookId);
		PrintWriter writer = resp.getWriter();
		Book b;
		BufferedReader bf;
		StringBuilder sb;
		switch(req.getMethod()) {
		case "GET":
			b = bs.getBookById(bookId);
			resp.getWriter().write(om.writeValueAsString(b));
			break;
		case "PUT":
			// Update the book in the database
			bf = req.getReader();
			sb = new StringBuilder();
			while(bf.ready()) {
				sb.append(bf.readLine());
			}
			b = om.readValue(sb.toString(), Book.class);
			bs.updateBook(b);
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.write(om.writeValueAsString(b));
			break;
		case "DELETE":
			bf = req.getReader();
			sb = new StringBuilder();
			while(bf.ready()) {
				sb.append(bf.readLine());
			}
			b = om.readValue(sb.toString(), Book.class);
			bs.deleteBook(b);
			resp.setStatus(HttpServletResponse.SC_OK);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
