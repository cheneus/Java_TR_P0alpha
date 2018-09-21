package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.TuitionReimbursementForm;
import com.revature.beans.TuitionReimbursementType;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class TuitionReimbursementTypeOracle implements TuitionReimbursementTypeDAO {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static Logger log = Logger.getLogger(TuitionReimbursementTypeOracle.class);

	@Override
	public int addTuitionReimbursementType(TuitionReimbursementType ev) {
		int key = 0;
		
		log.trace("Inserting a TuitionReimbursementType into the database.");
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into TuitionReimbursementType(name) values(?)";
			String[] keys = { "id" };
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, ev.getName());
			
			int number = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(number!=1) {
				log.warn("We didn't insert only one TuitionReimbursementType, or any TuitionReimbursementType at all.");
				conn.rollback();
			} else {
				log.trace("Inserted TuitionReimbursementType successfully");
				if(rs.next()) {
					key = rs.getInt(1);
					ev.setId(key);
					conn.commit();
				} else {
					log.trace("TuitionReimbursementType not created");
					ev.setId(0);
					conn.rollback();
				}
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, TuitionReimbursementTypeOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, TuitionReimbursementTypeOracle.class);
			}
		}
		
		return key;
	}

	@Override
	public TuitionReimbursementType getTuitionReimbursementTypes(TuitionReimbursementType tr) {
		log.trace("Retrieving TuitionReimbursementType with TuitionReimbursementType= " + tr);
		TuitionReimbursementType g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from TuitionReimbursementType where TuitionReimbursementType =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, tr.getName());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new TuitionReimbursementType();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("TuitionReimbursementType"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, TuitionReimbursementTypeOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}
	
	@Override
	public TuitionReimbursementType getTuitionReimbursementTypesById(int i) {
		log.trace("Retrieving TuitionReimbursementType with TuitionReimbursementType= " + i);
		TuitionReimbursementType g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from TuitionReimbursementType where TuitionReimbursementType =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, i);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new TuitionReimbursementType();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("TuitionReimbursementType"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, TuitionReimbursementTypeOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}

	@Override
	public Set<TuitionReimbursementType> getTuitionReimbursementTypes() {
		log.trace("Retrieving TuitionReimbursementTypes");
		Set<TuitionReimbursementType> TuitionReimbursementTypes = new HashSet<TuitionReimbursementType>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from TuitionReimbursementType";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				TuitionReimbursementType g = new TuitionReimbursementType();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("name"));
				TuitionReimbursementTypes.add(g);
			}
		} catch (SQLException e) {
			LogUtil.logException(e, TuitionReimbursementTypeOracle.class);
		}
		log.trace("Method returning: " + TuitionReimbursementTypes);
		return TuitionReimbursementTypes;
	}

	@Override
	public void updateTuitionReimbursementType(TuitionReimbursementType g) {
		log.trace("Updating TuitionReimbursementType to "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "update TuitionReimbursementType set TuitionReimbursementType = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(2, g.getId());
            stmt.setString(1, g.getName());
            int rs = stmt.executeUpdate();
            log.trace("Updated "+rs+" rows.");
            if(rs!=1) {
            	log.warn("TuitionReimbursementType update failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("TuitionReimbursementType updated successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, TuitionReimbursementTypeOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, TuitionReimbursementTypeOracle.class);
			}
        }
        log.trace("Method returning: " + g);
	}

	@Override
	public void deleteTuitionReimbursementType(TuitionReimbursementType s) {
		log.trace("Deleting TuitionReimbursementType: "+ s);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "delete from TuitionReimbursementType where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, s.getId());
            int rs = stmt.executeUpdate();
            log.trace("Deleted "+rs+" rows.");
            if(rs!=1) {
            	log.warn("TuitionReimbursementType delete failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("TuitionReimbursementType deleted successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, TuitionReimbursementTypeOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, TuitionReimbursementTypeOracle.class);
			}
        }
        log.trace("Method returning: " + null);
	}
}
