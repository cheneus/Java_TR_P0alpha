package com.revature.services;

import java.util.Set;

import com.revature.beans.Department;

public interface GenreService {
	public Set<Department> getGenres();
	public Department getGenreById(int i);
	public void updateGenre(Department g);
	public void deleteGenre(Department g);
	public void addGenre(Department g);
}
