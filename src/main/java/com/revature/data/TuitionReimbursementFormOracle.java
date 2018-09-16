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
	private Logger log = Logger.getLogger(AddressOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	@Override
	public int addTuitionReimbursementForm(TuitionReimbursementForm tr) {
		Connection conn = cu.getConnection();
		int key = 0;
		try {
			log.trace("Inserting TuitionReimbursementForm into db");
			conn.setAutoCommit(false);
			String sql = "insert into TuitionReimbursementForm (date_of_event, time_of_event, location_id, event_id, description, cost, grade_format_id, submitted_by, event_related_attachments, status)"
					+ " values (?,?,?,?,? ,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			
			ps.setDate(1,(Date) tr.getDateOfEvent());
			ps.setDate(2, (Date) tr.getTimeOfEvent());
			ps.setInt(3, tr.getLocationId().getId());
			ps.setInt(4,tr.getEventId().getId());
			ps.setString(5,tr.getDescription());
			ps.setDouble(6,tr.getCost());
			ps.setInt(7,tr.getGrade_format_id().getId());
			ps.setInt(8,tr.getSubmitted_by().getId());
			ps.setString(9,tr.getEvent_related_attachments());
			ps.setInt(10, 0);
			
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
			String sql = "select date_of_event, time_of_event, location_id, event_id, description, cost, grade_format_id, submitted_by, event_related_attachments, status, date_submmitted from TuitionReimbursementForm where id=?";
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
				ev.setId(rs.getInt("event_id"));
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setTimeOfEvent(rs.getDate("time_of_event"));
				tr.setLocationId(lo);
				tr.setEventId(ev);
				tr.setDescription(rs.getString("description"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachment"));
			
			}
			else
			{
				log.trace("This is not a TuitionReimbursementForm");
				tr.setDateOfEvent(rs.getDate(null));
				tr.setTimeOfEvent(rs.getDate(null));
				tr.setLocationId(null);
				tr.setEventId(null);
				tr.setDescription(null);
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
		TuitionReimbursementForm tr = null;
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, date_of_event, time_of_event, location_id, event_id, description, cost, grade_format_id, submitted_by, event_related_attachments, status, date_submmitted from TuitionReimbursementForm where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				tr = new TuitionReimbursementForm();
				EventLocation lo = new EventLocation();
				Employee em = new Employee();
				GradeFormat gf = new GradeFormat();
				EventType ev = new EventType();
				Status s = new Status();
				lo.setId(rs.getInt("location_id"));
				em.setId(rs.getInt("submitted_by"));
				gf.setId(rs.getInt("grade_format_id"));
				ev.setId(rs.getInt("event_id"));
				tr.setId(rs.getInt("id"));
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setTimeOfEvent(rs.getDate("time_of_event"));
				tr.setLocationId(lo);
				tr.setEventId(ev);
				tr.setDescription(rs.getString("description"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachment"));
			
			}
			else
			{
				log.trace("This is not a TuitionReimbursementForm");
				tr.setDateOfEvent(rs.getDate(null));
				tr.setTimeOfEvent(rs.getDate(null));
				tr.setLocationId(null);
				tr.setEventId(null);
				tr.setDescription(null);
				tr.setCost(rs.getDouble(null));
				tr.setGrade_format_id(null);
				tr.setSubmitted_by(null);
				tr.setStatus(null);
				tr.setEvent_related_attachments(rs.getString(null));
				tr.setId(0);
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
			String sql = "select id, date_of_event, time_of_event, location_id, event_id, description, cost, grade_format_id, submitted_by, event_related_attachments, status, date_submmitted from TuitionReimbursementForm";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				TuitionReimbursementForm tr = new TuitionReimbursementForm();
				EventLocation lo = new EventLocation();
				Employee em = new Employee();
				GradeFormat gf = new GradeFormat();
				EventType ev = new EventType();
				Status s = new Status();
				lo.setId(rs.getInt("location_id"));
				em.setId(rs.getInt("submitted_by"));
				gf.setId(rs.getInt("grade_format_id"));
				ev.setId(rs.getInt("event_id"));
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setTimeOfEvent(rs.getDate("time_of_event"));
				tr.setLocationId(lo);
				tr.setEventId(ev);
				tr.setDescription(rs.getString("description"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachment"));
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
	public Set<TuitionReimbursementForm> getTuitionReimbursementFormsByPendingStatus() {
		Set<TuitionReimbursementForm> trList = new HashSet<TuitionReimbursementForm>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "select id, date_of_event, time_of_event, location_id, event_id, description, cost, grade_format_id, submitted_by, event_related_attachments, status, date_submmitted from TuitionReimbursementForm where status = 0";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				TuitionReimbursementForm tr = new TuitionReimbursementForm();
				EventLocation lo = new EventLocation();
				Employee em = new Employee();
				GradeFormat gf = new GradeFormat();
				EventType ev = new EventType();
				Status s = new Status();
				lo.setId(rs.getInt("location_id"));
				em.setId(rs.getInt("submitted_by"));
				gf.setId(rs.getInt("grade_format_id"));
				ev.setId(rs.getInt("event_id"));
				tr.setDateOfEvent(rs.getDate("date_of_event"));
				tr.setTimeOfEvent(rs.getDate("time_of_event"));
				tr.setLocationId(lo);
				tr.setEventId(ev);
				tr.setDescription(rs.getString("description"));
				tr.setCost(rs.getDouble("cost"));
				tr.setGrade_format_id(gf);
				tr.setSubmitted_by(em);
				tr.setStatus(s);
				tr.setEvent_related_attachments(rs.getString("event_related_attachment"));
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
			
			String sql = "update TuitionReimbursementForm  set sup_id = ?, title = ? where id = ?";
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
			
			String sql = "delete from tr where id = ?";
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
	
}
