package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.EventLocation;
import com.revature.beans.EventType;
import com.revature.beans.GradeFormat;
import com.revature.beans.GradeStatus;
import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursement;
import com.revature.beans.TuitionReimbursementForm;
import com.revature.beans.TuitionReimbursementType;
import com.revature.exceptions.NullArgumentException;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class TuitionReimbursementOracle implements TuitionReimbursementDAO {
	private Logger log = Logger.getLogger(AddressOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	@Override
	public int addTuitionReimbursement(TuitionReimbursement tr) {
		Connection conn = cu.getConnection();
		int key = 0;
		try {
			log.trace("Inserting TuitionReimbursement into db");
			conn.setAutoCommit(false);
			String sql = "insert into TuitionReimbursement "
					+ "(amount_reimbursed, reason, form_ref, grade_stat, approved_by, remarks, type_id, status)"
					+ " values (?,?,?,?,?, ?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, key);
					
			ps.setDouble(1,tr.getAmount_reimbursed());
			ps.setString(2, tr.getReason());
			ps.setInt(3, tr.getForm_ref().getId());
			ps.setInt(4, tr.getGrade_stat().getId());
			ps.setInt(5, tr.getApproved_by().getId());
			ps.setString(6,tr.getRemarks());
			ps.setInt(7,tr.getType_id().getId());
			ps.setInt(8, 0);
			
			int result = ps.executeUpdate();
			if(result!=1)
			{
				log.warn("Insertion of TuitionReimbursement failed.");
				conn.rollback();
			}
			else {
				log.trace("Successful insertion of TuitionReimbursement");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,TuitionReimbursementOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,TuitionReimbursementOracle.class);
			}
		}
		return key;
	}

	@Override
	public TuitionReimbursement getTuitionReimbursement(TuitionReimbursement tr) {
		if(tr==null)
		{
			throw new NullArgumentException("tr can't be null");
		}
		try(Connection conn = cu.getConnection())
		{
			String sql = "select amount_reimbursed, date_received, reason, form_ref, grade_stat, approved_by, remarks, type_id, status from TuitionReimbursement where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tr.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				TuitionReimbursementForm tf = new TuitionReimbursementForm();
				TuitionReimbursementType tfy = new TuitionReimbursementType();
				GradeStatus gs = new GradeStatus();
				Employee em = new Employee();
				Status s = new Status();
				
				tf.setId(rs.getInt("form_ref"));
				tfy.setId(rs.getInt("type_if"));
				gs.setId(rs.getInt("grade_stat"));
				em.setId(rs.getInt("apporved_by"));
				s.setId(rs.getInt("status"));
				
				tr.setStatus(s);
				tr.setAmount_reimbursed(rs.getDouble("cost"));
				tr.setDate_received(rs.getDate("date_received"));
				tr.setReason(rs.getString("reason"));
				tr.setForm_ref(tf);
				tr.setGrade_stat(gs);
				tr.setApproved_by(em);
				tr.setRemarks(rs.getString("remarks"));
				tr.setType_id(tfy);
			}
			else
			{
				log.trace("This is not a TuitionReimbursement");
				tr.setId(0);
				tr.setStatus(null);
				tr.setAmount_reimbursed(0);
				tr.setDate_received(null);
				tr.setReason(null);
				tr.setForm_ref(null);
				tr.setGrade_stat(null);
				tr.setApproved_by(null);
				tr.setRemarks(null);
				tr.setType_id(null);	
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementOracle.class);
		}
		return tr;
	}

	@Override
	public TuitionReimbursement getTuitionReimbursementById(int i) {
		TuitionReimbursement tr = new TuitionReimbursement();
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, amount_reimbursed, date_received, reason, form_ref, grade_stat, approved_by, remarks, type_id, status from TuitionReimbursement where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				TuitionReimbursementForm tf = new TuitionReimbursementForm();
				TuitionReimbursementType tfy = new TuitionReimbursementType();
				GradeStatus gs = new GradeStatus();
				Employee em = new Employee();
				Status s = new Status();
				tf.setId(rs.getInt("form_ref"));
				tfy.setId(rs.getInt("type_if"));
				gs.setId(rs.getInt("grade_stat"));
				em.setId(rs.getInt("apporved_by"));
				s.setId(rs.getInt("status"));
				tr.setId(i);
				tr.setStatus(s);
				tr.setAmount_reimbursed(rs.getDouble("cost"));
				tr.setDate_received(rs.getDate("date_received"));
				tr.setReason(rs.getString("reason"));
				tr.setForm_ref(tf);
				tr.setGrade_stat(gs);
				tr.setApproved_by(em);
				tr.setRemarks(rs.getString("remarks"));
				tr.setType_id(tfy);
			}
			else
			{
				log.trace("This is not a TuitionReimbursement");
				tr.setId(0);
				tr.setStatus(null);
				tr.setAmount_reimbursed(0);
				tr.setDate_received(null);
				tr.setReason(null);
				tr.setForm_ref(null);
				tr.setGrade_stat(null);
				tr.setApproved_by(null);
				tr.setRemarks(null);
				tr.setType_id(null);	
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementOracle.class);
		}
		return tr;
	}

	@Override
	public Set<TuitionReimbursement> getTuitionReimbursements() {
		Set<TuitionReimbursement> trList = new HashSet<TuitionReimbursement>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, amount_reimbursed, date_received, reason, form_ref, grade_stat, approved_by, remarks, type_id, status from TuitionReimbursement";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				TuitionReimbursement tr = new TuitionReimbursement();
				TuitionReimbursementForm tf = new TuitionReimbursementForm();
				TuitionReimbursementType tfy = new TuitionReimbursementType();
				GradeStatus gs = new GradeStatus();
				Employee em = new Employee();
				Status s = new Status();
				tf.setId(rs.getInt("form_ref"));
				tfy.setId(rs.getInt("type_if"));
				gs.setId(rs.getInt("grade_stat"));
				em.setId(rs.getInt("apporved_by"));
				s.setId(rs.getInt("status"));
				
				tr.setStatus(s);
				tr.setAmount_reimbursed(rs.getDouble("cost"));
				tr.setDate_received(rs.getDate("date_received"));
				tr.setReason(rs.getString("reason"));
				tr.setForm_ref(tf);
				tr.setGrade_stat(gs);
				tr.setApproved_by(em);
				tr.setRemarks(rs.getString("remarks"));
				tr.setType_id(tfy);
				tr.setId(rs.getInt("id"));
				trList.add(tr);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementOracle.class);
		}
		return trList;
	}

	@Override
	public Set<TuitionReimbursement> getTuitionReimbursementsByPendingStatus() {
		Set<TuitionReimbursement> trList = new HashSet<TuitionReimbursement>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, amount_reimbursed, date_received, reason, form_ref, grade_stat, approved_by, remarks, type_id, status from TuitionReimbursement where status = 0";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				TuitionReimbursement tr = new TuitionReimbursement();
				TuitionReimbursementForm tf = new TuitionReimbursementForm();
				TuitionReimbursementType tfy = new TuitionReimbursementType();
				GradeStatus gs = new GradeStatus();
				Employee em = new Employee();
				Status s = new Status();
				tf.setId(rs.getInt("form_ref"));
				tfy.setId(rs.getInt("type_if"));
				gs.setId(rs.getInt("grade_stat"));
				em.setId(rs.getInt("apporved_by"));
				s.setId(rs.getInt("status"));
				
				tr.setStatus(s);
				tr.setAmount_reimbursed(rs.getDouble("cost"));
				tr.setDate_received(rs.getDate("date_received"));
				tr.setReason(rs.getString("reason"));
				tr.setForm_ref(tf);
				tr.setGrade_stat(gs);
				tr.setApproved_by(em);
				tr.setRemarks(rs.getString("remarks"));
				tr.setType_id(tfy);
				tr.setId(rs.getInt("id"));
				trList.add(tr);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementOracle.class);
		}
		return trList;
	}

	@Override
	public void updateTuitionReimbursement(TuitionReimbursement tr) {
		log.trace("Updating TuitionReimbursement from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update TuitionReimbursement  set sup_id = ?, title = ? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, TuitionReimbursement.getSupervisor().getId());
//			pstm.setString(2, TuitionReimbursement.getTitle());
//			pstm.setInt(3, TuitionReimbursement.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("TuitionReimbursement not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("TuitionReimbursement updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementOracle.class);
		}

	}

	@Override
	public void deleteTuitionReimbursement(TuitionReimbursement tr) {
		log.trace("Removing TuitionReimbursement from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "delete from TuitionReimbursement where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, tr.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("TuitionReimbursement not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("TuitionReimbursement deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementOracle.class);
		}
	}

	@Override
	public Set<TuitionReimbursement> getTuitionReimbursementsByStatus(Status s) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
