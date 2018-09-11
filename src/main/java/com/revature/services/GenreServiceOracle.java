package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Department;
import com.revature.data.BookAppDAOFactory;
import com.revature.data.GenreDAO;

public class GenreServiceOracle implements GenreService {
	private Logger log = Logger.getLogger(GenreServiceOracle.class);
	private BookAppDAOFactory bf = BookAppDAOFactory.getInstance();
	private GenreDAO gd = bf.getGenreDAO();
	
	@Override
	public Set<Department> getGenres() {
		return gd.getGenres();
	}
	@Override
	public Department getGenreById(int i) {
		return gd.getGenre(i);
	}
	@Override
	public void updateGenre(Department g) {
		gd.updateGenre(g);
	}
	@Override
	public void deleteGenre(Department g) {
		gd.deleteGenre(g);
	}
	@Override
	public void addGenre(Department g) {
		gd.addGenre(g);
	}
}
