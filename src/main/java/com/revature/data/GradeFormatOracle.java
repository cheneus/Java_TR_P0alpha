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
	private static Logger log = Logger.getLogger(GradeFormatOracle.class);
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	@Override
	public int addGradeFormat(GradeFormat a) {
		int key =0;
		log.trace("Adding GradeFormat to database.");
		log.trace(a);
		Connection conn = cu.getConnection();
		try{
			conn.setAutoCommit(false);
			String sql = "insert into GradeFormat (firstname,lastname,aboutblurb) values(?,?,?)";
			String[] keys = {"id"};
			PreparedStatement pstm = conn.prepareStatement(sql, keys);
			pstm.setString(1,a.getFirst());
			pstm.setString(2, a.getLast());
			pstm.setString(3, a.getAbout());
			
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			
			if(rs.next())
			{
				log.trace("GradeFormat created.");
				key = rs.getInt(1);
				a.setId(key);
				conn.commit();
			}
			else
			{
				log.trace("GradeFormat not created.");
				conn.rollback();
			}
		}
		catch(Exception e)
		{
			LogUtil.rollback(e,conn,GradeFormatOracle.class);
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,GradeFormatOracle.class);
			}
		}
		return key;
	}
	@Override
	public GradeFormat getGradeFormat(int id) {
		GradeFormat a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting GradeFormat with id: "+id);
			String sql = "Select firstname, lastname, aboutblurb from GradeFormat where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("GradeFormat found.");
				a = new GradeFormat();
				a.setId(id);
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));

			}
		} catch (Exception e) {
			LogUtil.logException(e,GradeFormatOracle.class);
		}
		return a;
	}
	@Override
	public GradeFormat getGradeFormatByName(String firstname, String lastname) {
		GradeFormat a = null;
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting GradeFormat with firstname ="+firstname+" and lastname="+lastname);
			String sql = "Select id, aboutblurb from GradeFormat where firstname=? and lastname=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, firstname);
			pstm.setString(2, lastname);
			ResultSet rs = pstm.executeQuery();
			if(rs.next())
			{
				log.trace("GradeFormat found.");
				a = new GradeFormat();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(firstname);
				a.setLast(lastname);

			}
		} catch (Exception e) {
			LogUtil.logException(e,GradeFormatOracle.class);
		}
		return a;
	}
	@Override
	public Set<GradeFormat> getGradeFormats() {
		Set<GradeFormat> GradeFormats = new HashSet<GradeFormat>();
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting all GradeFormats");
			String sql = "Select id, firstname, lastname, aboutblurb from GradeFormat";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				GradeFormat a = new GradeFormat();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));
				GradeFormats.add(a);
			}
		} catch (Exception e) {
			LogUtil.logException(e,GradeFormatOracle.class);
		}
		return GradeFormats;
	}
	@Override
	public Set<GradeFormat> getGradeFormatsByBook(Book b) {
		Set<GradeFormat> GradeFormats = new HashSet<GradeFormat>();
		try(Connection conn = cu.getConnection())
		{
			log.trace("Getting all GradeFormats by book");
			String sql = "Select id, firstname, lastname, aboutblurb from GradeFormat join book_GradeFormat"
					+ " on GradeFormat.id=book_GradeFormat.GradeFormat_id where book_id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, b.getId());
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				GradeFormat a = new GradeFormat();
				a.setId(rs.getInt("id"));
				a.setAbout(rs.getString("aboutblurb"));
				a.setFirst(rs.getString("firstname"));
				a.setLast(rs.getString("lastname"));
				GradeFormats.add(a);
			}
		} catch (Exception e) {
			LogUtil.logException(e,GradeFormatOracle.class);
		}
		return GradeFormats;
	}
	@Override
	public void updateGradeFormat(GradeFormat a) {
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "update GradeFormat set firstname=?, lastname=?, aboutblurb=? where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, a.getFirst());
			pstm.setString(2, a.getLast());
			pstm.setString(3, a.getAbout());
			pstm.setInt(4, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("GradeFormat updated");
				conn.commit();
			}
			else {
				log.trace("GradeFormat update failed");
				conn.rollback();
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, GradeFormatOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,GradeFormatOracle.class);
			}
		}
	}
	@Override
	public void deleteGradeFormat(GradeFormat a) {
		log.trace("Deleting GradeFormat: "+a);
		Connection conn = cu.getConnection();
		try	{
			conn.setAutoCommit(false);
			String sql = "delete from GradeFormat where id=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, a.getId());
			
			int result = pstm.executeUpdate();
			
			if(result == 1)
			{
				log.trace("GradeFormat deleted");
				conn.commit();
			}
			else {
				log.trace("GradeFormat delete failed");
				conn.rollback();
			}
		} catch(Exception e) {
			LogUtil.rollback(e, conn, GradeFormatOracle.class);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LogUtil.logException(e,GradeFormatOracle.class);
			}
		}
	}
	@Override
	public GradeFormat getGradeFormat(GradeFormat GradeFormat) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GradeFormat getGradeFormatById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
