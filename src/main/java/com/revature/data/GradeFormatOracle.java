package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.GradeFormat;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class GradeFormatOracle implements GradeFormatDAO {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static Logger log = Logger.getLogger(GradeFormatOracle.class);

	@Override
	public int addGradeFormat(GradeFormat ev) {
		int key = 0;
		
		log.trace("Inserting a GradeFormat into the database.");
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into GradeFormat(name) values(?)";
			String[] keys = { "id" };
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, ev.getName());
			
			int number = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(number!=1) {
				log.warn("We didn't insert only one GradeFormat, or any GradeFormats at all.");
				conn.rollback();
			} else {
				log.trace("Inserted GradeFormat successfully");
				if(rs.next()) {
					key = rs.getInt(1);
					ev.setId(key);
					conn.commit();
				} else {
					log.trace("GradeFormat not created");
					ev.setId(0);
					conn.rollback();
				}
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, GradeFormatOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GradeFormatOracle.class);
			}
		}
		
		return key;
	}

	@Override
	public GradeFormat getGradeFormatById(int id) {
		log.trace("Retrieving GradeFormat with id = " + id);
		GradeFormat ev = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select name from GradeFormat where id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				ev = new GradeFormat();
				ev.setId(rs.getInt("id"));
				ev.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GradeFormatOracle.class);
		}
		log.trace("Method returning: " + ev);
		return ev;
	}

	@Override
	public GradeFormat getGradeFormat(GradeFormat ev) {
		log.trace("Retrieving GradeFormat with GradeFormat= " + ev);
		GradeFormat g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from GradeFormat where GradeFormat =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ev.getName());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new GradeFormat();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("GradeFormat"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GradeFormatOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}

	@Override
	public Set<GradeFormat> getGradeFormats() {
		log.trace("Retrieving GradeFormats");
		Set<GradeFormat> GradeFormats = new HashSet<GradeFormat>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from GradeFormat";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				GradeFormat g = new GradeFormat();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("name"));
				GradeFormats.add(g);
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GradeFormatOracle.class);
		}
		log.trace("Method returning: " + GradeFormats);
		return GradeFormats;
	}

//	@Override
//	public Set<GradeFormat> getGradeFormatsByBook(Book b) {
//		log.trace("Retrieving GradeFormats");
//		Set<GradeFormat> GradeFormats = new HashSet<GradeFormat>();
//		try (Connection conn = cu.getConnection()) {
//			String sql = "select g.id, g.GradeFormat from GradeFormat g join book_GradeFormat bg on bg.GradeFormat_id ="
//					+ "g.id where bg.book_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, b.getId());
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				log.trace(rs.getInt(1) + " | " + rs.getString(2));
//				GradeFormat g = new GradeFormat();
//				g.setId(rs.getInt("id"));
//				g.setGradeFormat(rs.getString("GradeFormat"));
//				GradeFormats.add(g);
//			}
//		} catch (SQLException e) {
//			LogUtil.logException(e, GradeFormatOracle.class);
//		}
//		log.trace("Method returning: " + GradeFormats);
//		return GradeFormats;
//	}

	@Override
	public void updateGradeFormat(GradeFormat g) {
		log.trace("Updating GradeFormat to "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "update GradeFormat set GradeFormat = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(2, g.getId());
            stmt.setString(1, g.getName());
            int rs = stmt.executeUpdate();
            log.trace("Updated "+rs+" rows.");
            if(rs!=1) {
            	log.warn("GradeFormat update failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("GradeFormat updated successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, GradeFormatOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GradeFormatOracle.class);
			}
        }
        log.trace("Method returning: " + g);
	}

	@Override
	public void deleteGradeFormat(GradeFormat ev) {
		log.trace("Deleting GradeFormat: "+ ev);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "delete from GradeFormat where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ev.getId());
            int rs = stmt.executeUpdate();
            log.trace("Deleted "+rs+" rows.");
            if(rs!=1) {
            	log.warn("GradeFormat delete failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("GradeFormat deleted successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, GradeFormatOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GradeFormatOracle.class);
			}
        }
        log.trace("Method returning: " + null);
	}
}
