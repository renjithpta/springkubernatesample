package com.ibsplc.itin.service;

import com.ibsplc.itin.vo.EmployeeVO;

/**
 * 
 * @author A-4211
 *
 */
public interface AuthenticationService {

	
	/**
	 * 
	 * @param userID
	 * @param password
	 * @return - user type
	 */
	public EmployeeVO isValidUser(String userID,String password);
}
