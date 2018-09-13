package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.GradeStatus;
import com.revature.beans.GradeStatus;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class GradeStatusOracle implements GradeStatusDAO {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static Logger log = Logger.getLogger(GradeStatusOracle.class);

	@Override
	public int addGradeStatus(GradeStatus ev) {
		int key = 0;
		
		log.trace("Inserting a GradeStatus into the database.");
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into GradeStatus(name) values(?)";
			String[] keys = { "id" };
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, ev.getName());
			
			int number = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(number!=1) {
				log.warn("We didn't insert only one GradeStatus, or any GradeStatuss at all.");
				conn.rollback();
			} else {
				log.trace("Inserted GradeStatus successfully");
				if(rs.next()) {
					key = rs.getInt(1);
					ev.setId(key);
					conn.commit();
				} else {
					log.trace("GradeStatus not created");
					ev.setId(0);
					conn.rollback();
				}
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, GradeStatusOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GradeStatusOracle.class);
			}
		}
		
		return key;
	}

	@Override
	public GradeStatus getGradeStatusById(int id) {
		log.trace("Retrieving GradeStatus with id = " + id);
		GradeStatus ev = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select name from GradeStatus where id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				ev = new GradeStatus();
				ev.setId(rs.getInt("id"));
				ev.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GradeStatusOracle.class);
		}
		log.trace("Method returning: " + ev);
		return ev;
	}

	@Override
	public GradeStatus getGradeStatus(GradeStatus ev) {
		log.trace("Retrieving GradeStatus with GradeStatus= " + ev);
		GradeStatus g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from GradeStatus where GradeStatus =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ev.getName());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new GradeStatus();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("GradeStatus"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GradeStatusOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}

	@Override
	public Set<GradeStatus> getGradeStatus() {
		log.trace("Retrieving GradeStatuss");
		Set<GradeStatus> GradeStatuss = new HashSet<GradeStatus>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from GradeStatus";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				GradeStatus g = new GradeStatus();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("name"));
				GradeStatuss.add(g);
			}
		} catch (SQLException e) {
			LogUtil.logException(e, GradeStatusOracle.class);
		}
		log.trace("Method returning: " + GradeStatuss);
		return GradeStatuss;
	}

//	@Override
//	public Set<GradeStatus> getGradeStatussByBook(Book b) {
//		log.trace("Retrieving GradeStatuss");
//		Set<GradeStatus> GradeStatuss = new HashSet<GradeStatus>();
//		try (Connection conn = cu.getConnection()) {
//			String sql = "select g.id, g.GradeStatus from GradeStatus g join book_GradeStatus bg on bg.GradeStatus_id ="
//					+ "g.id where bg.book_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, b.getId());
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				log.trace(rs.getInt(1) + " | " + rs.getString(2));
//				GradeStatus g = new GradeStatus();
//				g.setId(rs.getInt("id"));
//				g.setGradeStatus(rs.getString("GradeStatus"));
//				GradeStatuss.add(g);
//			}
//		} catch (SQLException e) {
//			LogUtil.logException(e, GradeStatusOracle.class);
//		}
//		log.trace("Method returning: " + GradeStatuss);
//		return GradeStatuss;
//	}

	@Override
	public void updateGradeStatus(GradeStatus g) {
		log.trace("Updating GradeStatus to "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "update GradeStatus set GradeStatus = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(2, g.getId());
            stmt.setString(1, g.getName());
            int rs = stmt.executeUpdate();
            log.trace("Updated "+rs+" rows.");
            if(rs!=1) {
            	log.warn("GradeStatus update failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("GradeStatus updated successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, GradeStatusOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GradeStatusOracle.class);
			}
        }
        log.trace("Method returning: " + g);
	}

	@Override
	public void deleteGradeStatus(GradeStatus ev) {
		log.trace("Deleting GradeStatus: "+ ev);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "delete from GradeStatus where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ev.getId());
            int rs = stmt.executeUpdate();
            log.trace("Deleted "+rs+" rows.");
            if(rs!=1) {
            	log.warn("GradeStatus delete failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("GradeStatus deleted successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, GradeStatusOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, GradeStatusOracle.class);
			}
        }
        log.trace("Method returning: " + null);
	}
}
