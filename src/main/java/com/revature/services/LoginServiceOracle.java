package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Login;
import com.revature.data.LoginDAO;
import com.revature.data.TRAppDAOFactory;

public class LoginServiceOracle implements LoginService {
	private Logger log = Logger.getLogger(LoginServiceOracle.class);
	private TRAppDAOFactory tf = TRAppDAOFactory.getInstance();
	private LoginDAO dd = tf.getLoginDAO();
	@Override
	public int addLogin(Login login) {
		dd.addLogin(login);
		return 0;
	}
	@Override
	public void updateLogin(Login login) {
		dd.updateLogin(login);
		
	}
	@Override
	public void deleteLogin(Login login) {
		dd.deleteLogin(login);
	}
	@Override
	public Login getLogin(String username, String password) {
		return dd.getLogin(username, password);
		
	}
	@Override
	public Set<Login> getLogins() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Login getLogin(Login login) {
		return dd.getLogin(login);
	}
}
