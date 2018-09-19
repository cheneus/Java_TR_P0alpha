package com.revature.data;

import java.sql.Connection;
import java.sql.Date;
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
import com.revature.beans.Status;
import com.revature.beans.TuitionReimbursementForm;
import com.revature.exceptions.NullArgumentException;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class TuitionReimbursementFormOracle implements TuitionReimbursementFormDAO {
	private Logger log = Logger.getLogger(TuitionReimbursementFormOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	@Override
	public int addTuitionReimbursementForm(TuitionReimbursementForm tr) {
		Connection conn = cu.getConnection();
		int key = 0;
		try {
			log.trace("Inserting TuitionReimbursementForm into db");
			conn.setAutoCommit(false);
			String sql = "insert into Tuition_Reimbursement_Form (date_of_event, total_Days, event_location,event_city,event_state, event_type, Title, cost, grade_format_id, submitted_by, event_related_attachments, status)"
					+ " values (?,?,?,?,? ,?,? ,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			
			ps.setDate(1,(Date) tr.getDateOfEvent());
			ps.setInt(2, tr.getTotalDays());
			ps.setString(3, tr.getEvent_address());
			ps.setString(4, tr.getEvent_city());
			ps.setString(5, tr.getEvent_state());
			ps.setInt(6,tr.getEventId().getId());
			ps.setString(7,tr.getTitle());
			ps.setDouble(8,tr.getCost());
			ps.setInt(9,tr.getGrade_format_id().getId());
			ps.setInt(10,tr.getSubmitted_by().getId());
			ps.setString(11,tr.getEvent_related_attachments());
			ps.setInt(12, 0);
			
			int result = ps.executeUpdate();
			if(result!=1)
			{
				log.warn("Insertion of TuitionReimbursementForm failed.");
				conn.rollback();
			}
			else {
				log.trace("Successful insertion of TuitionReimbursementForm");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,TuitionReimbursementFormOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,TuitionReimbursementFormOracle.class);
			}
		}
		return key;
	}

	@Override
	public TuitionReimbursementForm getTuitionReimbursementForm(TuitionReimbursementForm tr) {
		if(tr==null)
		{
			throw new NullArgumentException("tr can't be null");
		}
		try(Connection conn = cu.getConnection())
		{
			String sql = "select date_of_event, total_Days, event_address,event_city,event_state, event_type, Title, cost, grade_format_id, submitted_by, event_related_attachments, status, date_submitted from Tuition_Reimbursement_Form where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tr.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				EventLocation lo = new EventLocation();
				Employee em = new Employee();
				GradeFormat gf = new GradeFormat();
				EventType ev = new EventType();
				Status s = new Status();
				lo.setId(rs.getInt("location_id"));
				em.setId(rs.getInt("submitted_by"));
				gf.setId(rs.getInt("grade_format_id"));
				ev.setId(rs.getInt("event_type"));
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setTotalDays(rs.getInt("totalDays"));
				tr.setEvent_address(rs.getString("event_address"));
				tr.setEvent_city(rs.getString("event_city"));
				tr.setEvent_state(rs.getString("event_state"));
				tr.setEventId(ev);
				tr.setTitle(rs.getString("Title"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachments"));
			
			}
			else
			{
				log.trace("This is not a TuitionReimbursementForm");
				tr.setDateOfEvent(rs.getDate(null));
				tr.setTotalDays(rs.getInt(null));
				tr.setEvent_address(null);
				tr.setEvent_city(null);
				tr.setEvent_state(null);
				tr.setEventId(null);
				tr.setTitle(null);
				tr.setCost(rs.getDouble(null));
				tr.setGrade_format_id(null);
				tr.setSubmitted_by(null);
				tr.setStatus(null);
				tr.setEvent_related_attachments(rs.getString(null));
				
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementFormOracle.class);
		}
		return tr;
	}

	@Override
	public TuitionReimbursementForm getTuitionReimbursementFormById(int i) {
		TuitionReimbursementForm tr = new TuitionReimbursementForm();
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, date_of_event, total_days, date_submitted, event_address,event_city,event_state, event_type, title, "
					+ "cost, grade_format_id, submitted_by, event_related_attachments, status from Tuition_Reimbursement_Form where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Employee em = new Employee();
				GradeFormat gf = new GradeFormat();
				EventType ev = new EventType();
				Status s = new Status();
				em.setId(rs.getInt("submitted_by"));
				gf.setId(rs.getInt("grade_format_id"));
				ev.setId(rs.getInt("event_type"));
				s.setId(rs.getInt("status"));
				tr.setId(i);
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setDate_submitted(rs.getDate("date_submitted"));
				tr.setTotalDays(rs.getInt("total_days"));
				tr.setEvent_address(rs.getString("event_address"));
				tr.setEvent_city(rs.getString("event_city"));
				tr.setEvent_state(rs.getString("event_state"));
				tr.setEventId(ev);
				tr.setTitle(rs.getString("title"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachments"));
			
			}
			else
			{
				log.trace("This is not a TuitionReimbursementForm");
				tr.setDateOfEvent(rs.getDate(null));
				tr.setTotalDays(rs.getInt(null));
				tr.setEvent_address(null);
				tr.setEvent_city(null);
				tr.setEvent_state(null);
				tr.setEventId(null);
				tr.setTitle(null);
				tr.setCost(rs.getDouble(null));
				tr.setGrade_format_id(null);
				tr.setSubmitted_by(null);
				tr.setStatus(null);
				tr.setEvent_related_attachments(rs.getString(null));
				
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementFormOracle.class);
		}
		return tr;
	}

	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementForms() {
		Set<TuitionReimbursementForm> trList = new HashSet<TuitionReimbursementForm>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, date_of_event, total_days, date_submitted,event_address,event_city,event_state, event_type, title, cost, grade_format_id, submitted_by, event_related_attachments, status from Tuition_Reimbursement_Form";
			PreparedStatement pstm = conn.prepareStatement(sql);
			log.trace(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				TuitionReimbursementForm tr = new TuitionReimbursementForm();
				Employee em = new Employee();
				GradeFormat gf = new GradeFormat();
				EventType ev = new EventType();
				Status s = new Status();
				
				em.setId(rs.getInt("submitted_by"));
				gf.setId(rs.getInt("grade_format_id"));
				ev.setId(rs.getInt("event_type"));
				
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setTotalDays(rs.getInt("total_Days"));
				tr.setDate_submitted(rs.getDate("date_submitted"));
				tr.setEvent_address(rs.getString("event_address"));
				tr.setEvent_city(rs.getString("event_city"));
				tr.setEvent_state(rs.getString("event_state"));
				tr.setEventId(ev);
				tr.setTitle(rs.getString("title"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachments"));
				tr.setId(rs.getInt("id"));
				trList.add(tr);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementFormOracle.class);
		}
		return trList;
	}

	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByNoAppr(int i) {
		Set<TuitionReimbursementForm> trList = new HashSet<TuitionReimbursementForm>();

		try(Connection conn = cu.getConnection())
		{
			String sql = "select * from Tuition_Reimbursement_Form where status != 6 and submitted_by =?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, i);
			log.trace(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				TuitionReimbursementForm tr = new TuitionReimbursementForm();
				Employee em = new Employee();
				GradeFormat gf = new GradeFormat();
				EventType ev = new EventType();
				Status s = new Status();
				em.setId(rs.getInt("submitted_by"));
				gf.setId(rs.getInt("grade_format_id"));
				ev.setId(rs.getInt("event_type"));
				s.setId(rs.getInt("status"));
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setDate_submitted(rs.getDate("date_submitted"));
				tr.setTotalDays(rs.getInt("total_days"));
				tr.setEvent_address(rs.getString("event_address"));
				tr.setEvent_city(rs.getString("event_city"));
				tr.setEvent_state(rs.getString("event_state"));
				tr.setEventId(ev);
				tr.setTitle(rs.getString("title"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachments"));
				tr.setId(rs.getInt("id"));
				trList.add(tr);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementFormOracle.class);
		}
		return trList;
	}

	@Override
	public void updateTuitionReimbursementForm(TuitionReimbursementForm tr) {
		log.trace("Updating TuitionReimbursementForm from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update Tuition_Reimbursement_Form  set sup_id = ?, title = ? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, TuitionReimbursementForm.getSupervisor().getId());
//			pstm.setString(2, TuitionReimbursementForm.getTitle());
//			pstm.setInt(3, TuitionReimbursementForm.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("TuitionReimbursementForm not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("TuitionReimbursementForm updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementFormOracle.class);
		}

	}

	@Override
	public void deleteTuitionReimbursementForm(TuitionReimbursementForm tr) {
		log.trace("Removing TuitionReimbursementForm from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "delete from Tuition_Reimbursement_Form  where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, tr.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("TuitionReimbursementForm not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("TuitionReimbursementForm deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementFormOracle.class);
		}
	}

	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByStatus(Status s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsOnView() {
		Set<TuitionReimbursementForm> trList = new HashSet<TuitionReimbursementForm>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "select * from gettrfname_view";
			PreparedStatement pstm = conn.prepareStatement(sql);
			log.trace(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				TuitionReimbursementForm tr = new TuitionReimbursementForm();
				Employee em = new Employee();
				GradeFormat gf = new GradeFormat();
				EventType ev = new EventType();
				Status s = new Status();
				em.setId(rs.getInt("eid"));
				em.setFirstname(rs.getString("firstname"));
				em.setLastname(rs.getString("lastname"));
				em.setEmail(rs.getString("email"));
				s.setName(rs.getString("status"));
				ev.setName(rs.getString("event_type"));
				gf.setName(rs.getString("grade_format"));
				
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setTotalDays(rs.getInt("total_Days"));
				tr.setDate_submitted(rs.getDate("date_submitted"));
				tr.setEvent_address(rs.getString("event_address"));
				tr.setEvent_city(rs.getString("event_city"));
				tr.setEvent_state(rs.getString("event_state"));
				tr.setEventId(ev);
				tr.setTitle(rs.getString("title"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachments"));
				tr.setId(rs.getInt("id"));
				trList.add(tr);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,TuitionReimbursementFormOracle.class);
		}
		return trList;
	}
	
}
