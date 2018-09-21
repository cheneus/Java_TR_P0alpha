package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.InfoRequest;
import com.revature.beans.TuitionReimbursementForm;
import com.revature.exceptions.NullArgumentException;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class InfoRequestOracle implements InfoRequestDAO {
	private Logger log = Logger.getLogger(InfoRequestOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	private TRAppDAOFactory bf =TRAppDAOFactory.getInstance();
	private EmployeeDAO emp = bf.getEmployeeDAO();
	private TuitionReimbursementFormDAO trf= bf.getTuitionReimbursementFormDAO();
	
	@Override
	public int addInfoReq(InfoRequest InfoRequest) {
		Connection conn = cu.getConnection();
		log.trace("Adding InfoRequest to database.");
		log.trace(InfoRequest);
		int key = 0;
		try {
			log.trace("Inserting InfoRequest into db");
			conn.setAutoCommit(false);
			String sql = "insert into info_request (form_ref, requestor_id, requestee_id) values (?,?,?)";
			String[] keys = { "id" };
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setInt(1, InfoRequest.getFormRef().getId());
			pstm.setInt(2, InfoRequest.getRequestorId().getId());
			pstm.setInt(3, InfoRequest.getRequesteeId().getId());
			int result = pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if(rs.next())
			{
				log.trace("Info Request created.");
				key = rs.getInt(1);
				InfoRequest.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("Info Request not created.");
				conn.rollback();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,InfoRequestOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,InfoRequestOracle.class);
			}
		}
		return key;
	}

	@Override
	public InfoRequest getInfoReq(InfoRequest InfoRequest) {
		Connection conn = cu.getConnection();
		log.trace("getting InfoRequest to database.");
		log.trace(InfoRequest);
		
		if(InfoRequest==null)
		{
			throw new NullArgumentException("InfoRequest can't be null");
		}
		try {
			String sql = "select form_ref,requestor_id, requestee_id, response, open from Info_Request where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, InfoRequest.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				log.trace("This is a InfoRequest");
				if(rs.getInt("open")==0)
				{
					log.trace("this request has been fullfilled");
				} else {
					log.trace("this request still waiting for a response");
				}
				Employee em1 = new Employee();
				Employee em2 = new Employee();
				TuitionReimbursementForm tr = new TuitionReimbursementForm();
				em1.setId(rs.getInt("requestor_id"));
				em2.setId(rs.getInt("requestee_id"));
				tr.setId(rs.getInt("form_ref"));
				InfoRequest.setTitle(rs.getString("title"));
				InfoRequest.setFormRef(tr);
				InfoRequest.setRequestorId(em1);
				InfoRequest.setRequesteeId(em2);
				InfoRequest.setResponse(rs.getString("response"));
				InfoRequest.setOpen(rs.getInt("open"));
			}
			else
			{
				log.trace("This is not a InfoRequest");
				InfoRequest.setId(0);
				InfoRequest.setTitle(null);
				InfoRequest.setFormRef(null);
				InfoRequest.setRequestorId(null);
				InfoRequest.setRequesteeId(null);
				InfoRequest.setResponse(null);
				InfoRequest.setOpen(0);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,InfoRequestOracle.class);
		}
		return InfoRequest;
	}

	@Override
	public Set<InfoRequest> getInfoReq() {
		Set<InfoRequest> InfoRequestList = new HashSet<InfoRequest>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "Select id, form_ref,requestor_id, requestee_id, response, open from Info_Request";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				InfoRequest InfoRequest = new InfoRequest();
				Employee em1 = new Employee();
				Employee em2 = new Employee();
				TuitionReimbursementForm tr = new TuitionReimbursementForm();
				em1.setId(rs.getInt("requestor_id"));
				em2.setId(rs.getInt("requestee_id"));
				tr.setId(rs.getInt("form_ref"));
				InfoRequest.setFormRef(tr);
				InfoRequest.setRequestorId(em1);
				InfoRequest.setRequesteeId(em2);
				InfoRequest.setResponse(rs.getString("response"));
				InfoRequest.setOpen(rs.getInt("open"));
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,InfoRequestOracle.class);
		}
		return InfoRequestList;
	}
	@Override
	public void updateInfoReq(InfoRequest InfoRequest)
	{
		log.trace("Updating InfoRequest from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update Info_Request set response=? open=? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, InfoRequest.getResponse());
			pstm.setInt(2, InfoRequest.getOpen());
			pstm.setInt(3, InfoRequest.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("InfoRequest not updated.");
				conn.rollback();
			}
			else
			{
				log.trace("InfoRequest updated.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,InfoRequestOracle.class);
		}
	}

	@Override
	public void deleteInfoReq(InfoRequest InfoRequest) {
		log.trace("Removing InfoRequest from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "delete from Info_Request where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, InfoRequest.getId());
			int number = pstm.executeUpdate();
			if(number!=1)
			{
				log.warn("InfoRequest not deleted.");
				conn.rollback();
			}
			else
			{
				log.trace("InfoRequest deleted.");
				conn.commit();
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,InfoRequestOracle.class);
		}
	}

	@Override
	public InfoRequest getInfoReqById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<InfoRequest> getUrInfoReq(int i) {
		Set<InfoRequest> InfoRequestList = new HashSet<InfoRequest>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "Select id, form_ref,requestor_id, requestee_id, response, open from Info_Request where requestee_id= ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, i);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				InfoRequest InfoRequest = new InfoRequest();
				Employee em1 = new Employee();
				Employee em2 = new Employee();
				TuitionReimbursementForm tr = new TuitionReimbursementForm();
				em1.setId(rs.getInt("requestor_id"));
				em1 = emp.getEmployee(em1);
				em2.setId(rs.getInt("requestee_id"));
				em2 = emp.getEmployee(em2);
				tr.setId(rs.getInt("form_ref"));
				tr=trf.getTuitionReimbursementForm(tr);
				log.trace(rs.getString("response"));
				InfoRequest.setResponse(rs.getString("response"));
				InfoRequest.setFormRef(tr);
				InfoRequest.setRequestorId(em1);
				InfoRequest.setRequesteeId(em2);
				

				InfoRequest.setOpen(rs.getInt("open"));
				InfoRequestList.add(InfoRequest);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,InfoRequestOracle.class);
		}
		return InfoRequestList;
	}


}
