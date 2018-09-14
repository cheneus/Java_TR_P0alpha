package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Submission;
import com.revature.beans.Submission;
import com.revature.exceptions.NullArgumentException;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class SubmissionOracle implements SubmissionDAO {

	private Logger log = Logger.getLogger(SubmissionOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	
	@Override
	public int addSubmission(Submission ev) {
		int key = 0;
		
		log.trace("Inserting a Submission into the database.");
		Connection conn = cu.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into Submission(type, attachment) values(?,?)";
			String[] keys = { "id" };
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, ev.getType());
			stmt.setString(1, ev.getAttachment());
			
			int number = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(number!=1) {
				log.warn("We didn't insert only one Submission, or any Submissions at all.");
				conn.rollback();
			} else {
				log.trace("Inserted Submission successfully");
				if(rs.next()) {
					key = rs.getInt(1);
					ev.setId(key);
					conn.commit();
				} else {
					log.trace("Submission not created");
					ev.setId(0);
					conn.rollback();
				}
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, SubmissionOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, SubmissionOracle.class);
			}
		}
		
		return key;
	}

	@Override
	public Submission getSubmissionById(int id) {
		log.trace("Retrieving Submission with id = " + id);
		Submission ev = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select name from Submission where id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				ev = new Submission();
				ev.setId(rs.getInt("id"));
				ev.setAttachment(rs.getString("attachment"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, SubmissionOracle.class);
		}
		log.trace("Method returning: " + ev);
		return ev;
	}

	@Override
	public Submission getSubmission(Submission ev) {
		log.trace("Retrieving Submission with Submission= " + ev);
		Submission g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, name from Submission where Submission =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ev.getType());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				log.trace(rs.getInt(1) + " | " + rs.getString(2));
				g = new Submission();
				g.setId(rs.getInt("id"));
				g.setType(rs.getString("Submission"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, SubmissionOracle.class);
		}
		log.trace("Method returning: " + g);
		return g;
	}


//	@Override
//	public Set<Submission> getSubmissionsByBook(Book b) {
//		log.trace("Retrieving Submissions");
//		Set<Submission> Submissions = new HashSet<Submission>();
//		try (Connection conn = cu.getConnection()) {
//			String sql = "select g.id, g.Submission from Submission g join book_Submission bg on bg.Submission_id ="
//					+ "g.id where bg.book_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, b.getId());
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				log.trace(rs.getInt(1) + " | " + rs.getString(2));
//				Submission g = new Submission();
//				g.setId(rs.getInt("id"));
//				g.setSubmission(rs.getString("Submission"));
//				Submissions.add(g);
//			}
//		} catch (SQLException e) {
//			LogUtil.logException(e, SubmissionOracle.class);
//		}
//		log.trace("Method returning: " + Submissions);
//		return Submissions;
//	}

	@Override
	public void updateSubmission(Submission g) {
		log.trace("Updating Submission to "+ g);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "update Submission set Submission = ? where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(2, g.getId());
            stmt.setString(1, g.getType());
            int rs = stmt.executeUpdate();
            log.trace("Updated "+rs+" rows.");
            if(rs!=1) {
            	log.warn("Submission update failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("Submission updated successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, SubmissionOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, SubmissionOracle.class);
			}
        }
        log.trace("Method returning: " + g);
	}

	@Override
	public void deleteSubmission(Submission ev) {
		log.trace("Deleting Submission: "+ ev);
		Connection conn = cu.getConnection();
        try {
        	// JDBC automatically commits data. Lets stop it.
        	conn.setAutoCommit(false);
            String sql = "delete from Submission where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ev.getId());
            int rs = stmt.executeUpdate();
            log.trace("Deleted "+rs+" rows.");
            if(rs!=1) {
            	log.warn("Submission delete failure. Rolling back.");
            	conn.rollback();
            } else {
            	log.trace("Submission deleted successfully. Committing.");
            	conn.commit();
            }
            
        } catch (SQLException e) {
            LogUtil.rollback(e, conn, SubmissionOracle.class);
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e, SubmissionOracle.class);
			}
        }
        log.trace("Method returning: " + null);
	}
}
