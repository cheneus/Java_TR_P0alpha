package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Book;
import com.revature.beans.Department;
import com.revature.driver.Main;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class GenreOracle implements GenreDAO {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static Logger log = Logger.getLogger(GenreOracle.class);

	@Override
	public int addGenre(Department g) {
		int key = 0;
		
		log.trace("Inserting a genre into the database.");
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into genre(genre) values(?)";
			String[] keys = { "id" };
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, g.getGenre());
			
			int number = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(number!=1) {
				log.warn("We didn't insert only one genre, or any genres at all.");
				conn.rollback();
			} else {
				log.trace("Inserted genre successfully");
				if(rs.next()) {
					key = rs.getInt(1);
					g.setId(key);
					conn.commit();
				} else {
					log.trace("Genre not created");
					g.setId(0);
					conn.rollback();
				}
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, GenreOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GenreOracle.class);
			}
		}
		
		return key;
	}

	@Override
	public Department getGenre(int id) {
		log.trace("Retrieving genre with id = " + id);
		Department g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, genre from genre where id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new Department();
				g.setId(rs.getInt("id"));
				g.setGenre(rs.getString("genre"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GenreOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}

	@Override
	public Department getGenreByName(String genre) {
		log.trace("Retrieving genre with genre= " + genre);
		Department g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, genre from genre where genre =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, genre);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new Department();
				g.setId(rs.getInt("id"));
				g.setGenre(rs.getString("genre"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GenreOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}

	@Override
	public Set<Department> getGenres() {
		log.trace("Retrieving genres");
		Set<Department> genres = new HashSet<Department>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, genre from genre";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				Department g = new Department();
				g.setId(rs.getInt("id"));
				g.setGenre(rs.getString("genre"));
				genres.add(g);
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GenreOracle.class);
		}
		log.trace("Method returning: " + genres);
		return genres;
	}

	@Override
	public Set<Department> getGenresByBook(Book b) {
		log.trace("Retrieving genres");
		Set<Department> genres = new HashSet<Department>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select g.id, g.genre from genre g join book_genre bg on bg.genre_id ="
					+ "g.id where bg.book_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, b.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				Department g = new Department();
				g.setId(rs.getInt("id"));
				g.setGenre(rs.getString("genre"));
				genres.add(g);
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GenreOracle.class);
		}
		log.trace("Method returning: " + genres);
		return genres;
	}

	@Override
	public void updateGenre(Department g) {
		log.trace("Updating Genre to "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "update genre set genre = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(2, g.getId());
            stmt.setString(1, g.getGenre());
            int rs = stmt.executeUpdate();
            log.trace("Updated "+rs+" rows.");
            if(rs!=1) {
            	log.warn("Genre update failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("Genre updated successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, GenreOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GenreOracle.class);
			}
        }
        log.trace("Method returning: " + g);
	}

	@Override
	public void deleteGenre(Department g) {
		log.trace("Deleting genre: "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "delete from genre where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, g.getId());
            int rs = stmt.executeUpdate();
            log.trace("Deleted "+rs+" rows.");
            if(rs!=1) {
            	log.warn("Genre delete failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("Genre deleted successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, GenreOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GenreOracle.class);
			}
        }
        log.trace("Method returning: " + null);
	}

}
