package com.ibsplc.itin.framework;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
/**
 * 
 * @author A-4211
 *
 */
public class iTinJdbcConnectionCallback implements ConnectionCallback {

	private Object[] arguments = null;

	public iTinJdbcConnectionCallback(Object[] args) {
		arguments = args;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	@Override
	public Object doInConnection(Connection connection) throws SQLException,
			DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
