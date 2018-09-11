package com.revature.data;

import java.util.Set;

import com.revature.beans.Book;
import com.revature.beans.Department;

public interface GenreDAO {
	// Data Access Object
	// Each bean has an object dedicated to
	// accessing the database on its behalf.
	
	// Usually a DAO is going to have CRUD methods.
	
	// create - Insert
	int addGenre(Department g);
	// read - Select
	Department getGenre(int id);
	Department getGenreByName(String genre);
	Set<Department> getGenres();
	Set<Department> getGenresByBook(Book b);
	// update - Update
	void updateGenre(Department g);
	// delete - Delete
	void deleteGenre(Department g);
}
