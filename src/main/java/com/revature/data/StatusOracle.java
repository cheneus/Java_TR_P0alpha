package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class StatusOracle implements StatusDAO {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static Logger log = Logger.getLogger(StatusOracle.class);

	@Override
	public int addStatus(Status ev) {
		int key = 0;
		
		log.trace("Inserting a Status into the database.");
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into Status(name) values(?)";
			String[] keys = { "id" };
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, ev.getName());
			
			int number = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(number!=1) {
				log.warn("We didn't insert only one Status, or any Status at all.");
				conn.rollback();
			} else {
				log.trace("Inserted Status successfully");
				if(rs.next()) {
					key = rs.getInt(1);
					ev.setId(key);
					conn.commit();
				} else {
					log.trace("Status not created");
					ev.setId(0);
					conn.rollback();
				}
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, StatusOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, StatusOracle.class);
			}
		}
		
		return key;
	}

	@Override
	public Status getStatusById(int id) {
		log.trace("Retrieving Status with id = " + id);
		Status ev = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select name from Status where id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				ev = new Status();
				ev.setId(rs.getInt("id"));
				ev.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, StatusOracle.class);
		}
		log.trace("Method returning: " + ev);
		return ev;
	}

	@Override
	public Status getStatus(Status ev) {
		log.trace("Retrieving Status with Status= " + ev);
		Status g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from Status where Status =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ev.getName());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new Status();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("Status"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, StatusOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}

	@Override
	public Set<Status> getStatus() {
		log.trace("Retrieving Status");
		Set<Status> Statuss = new HashSet<Status>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from Status";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				Status g = new Status();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("name"));
				Statuss.add(g);
			}
		} catch (SQLException e) {
			LogUtil.logException(e, StatusOracle.class);
		}
		log.trace("Method returning: " + Statuss);
		return Statuss;
	}

//	@Override
//	public Set<Status> getStatussByBook(Book b) {
//		log.trace("Retrieving Statuss");
//		Set<Status> Statuss = new HashSet<Status>();
//		try (Connection conn = cu.getConnection()) {
//			String sql = "select g.id, g.Status from Status g join book_Status bg on bg.Status_id ="
//					+ "g.id where bg.book_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, b.getId());
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				log.trace(rs.getInt(1) + " | " + rs.getString(2));
//				Status g = new Status();
//				g.setId(rs.getInt("id"));
//				g.setStatus(rs.getString("Status"));
//				Statuss.add(g);
//			}
//		} catch (SQLException e) {
//			LogUtil.logException(e, StatusOracle.class);
//		}
//		log.trace("Method returning: " + Statuss);
//		return Statuss;
//	}

	@Override
	public void updateStatus(Status g) {
		log.trace("Updating Status to "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "update Status set Status = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(2, g.getId());
            stmt.setString(1, g.getName());
            int rs = stmt.executeUpdate();
            log.trace("Updated "+rs+" rows.");
            if(rs!=1) {
            	log.warn("Status update failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("Status updated successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, StatusOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, StatusOracle.class);
			}
        }
        log.trace("Method returning: " + g);
	}

	@Override
	public void deleteStatus(Status s) {
		log.trace("Deleting Status: "+ s);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "delete from Status where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, s.getId());
            int rs = stmt.executeUpdate();
            log.trace("Deleted "+rs+" rows.");
            if(rs!=1) {
            	log.warn("Status delete failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("Status deleted successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, StatusOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, StatusOracle.class);
			}
        }
        log.trace("Method returning: " + null);
	}

}
