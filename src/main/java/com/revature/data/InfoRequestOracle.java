package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.InfoRequest;
import com.revature.beans.InfoRequest;
import com.revature.exceptions.NullArgumentException;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class InfoRequestOracle implements InfoRequestDAO {

	private Logger log = Logger.getLogger(InfoRequestOracle.class);
	private ConnectionUtil cu = ConnectionUtil.getInstance();
	
	@Override
	public int addInfoReq(InfoRequest InfoRequest) {
		Connection conn = cu.getConnection();
		try {
			log.trace("Inserting InfoRequest into db");
			conn.setAutoCommit(false);
			String sql = "insert into info_request (title, form_ref, requestor_id, requestee_id, response, open) values (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,InfoRequest.getId());
			ps.setInt(2, InfoRequest.getSupervisor().getId());
			ps.setString(3, InfoRequest.getTitle());
			int result = ps.executeUpdate();
			if(result!=1)
			{
				log.warn("Insertion of InfoRequest failed.");
				conn.rollback();
			}
			else {
				log.trace("Successful insertion of InfoRequest");
				conn.commit();
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
	}

	@Override
	public InfoRequest getInfoReq(InfoRequest emp) {
		if(emp==null)
		{
			throw new NullArgumentException("emp can't be null");
		}
		try(Connection conn = cu.getConnection())
		{
			String sql = "select ID, sup_id, title from emp where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				log.trace("This is a InfoRequest");
				if(rs.getObject("sup_id")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new InfoRequest(rs.getInt("sup_id")));
				}
				emp.setTitle(rs.getString("title"));
			}
			else
			{
				log.trace("This is not a InfoRequest");
				emp.setFirst(null);
				emp.setLast(null);
				emp.setId(0);
				emp.setPassword(null);
				emp.setUsername(null);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,InfoRequestOracle.class);
		}
		return emp;
	}

	@Override
	public Set<InfoRequest> getInfoReq() {
		Set<InfoRequest> empList = new HashSet<InfoRequest>();
		try(Connection conn = cu.getConnection())
		{
			String sql = "Select id, sup_id, title from emp";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				InfoRequest emp = new InfoRequest();
				if(rs.getObject("sup_id")==null)
				{
					log.trace("null supervisor");
				} else {
					emp.setSupervisor(new InfoRequest(rs.getInt("sup_id")));
				}
				emp.setTitle(rs.getString("title"));
				emp.setId(rs.getInt("id"));
				empList.add(emp);
			}
		}
		catch(Exception e)
		{
			LogUtil.logException(e,InfoRequestOracle.class);
		}
		return empList;
	}
	@Override
	public void updateInfoReq(InfoRequest InfoRequest)
	{
		log.trace("Updating InfoRequest from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update emp set sup_id = ?, title = ? where id = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, InfoRequest.getSupervisor().getId());
			pstm.setString(2, InfoRequest.getTitle());
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
	public void deleteInfoRequest(InfoRequest InfoRequest) {
		log.trace("Removing InfoRequest from database.");
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "delete from emp where id = ?";
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


}
