package com.revature.driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.revature.data.TRAppDAOFactory;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class Main {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		firstAttempt();
	}

	private static void firstAttempt() {
		String sql = "select * from address";
		//String sql = "select id, title from book";
		try (Connection conn = cu.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//log.trace(rs.getInt("id")+" | "+rs.getString("title"));
				log.trace(rs.getInt(1)+" | "+rs.getString(2));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, Main.class);
		}
	}

}
