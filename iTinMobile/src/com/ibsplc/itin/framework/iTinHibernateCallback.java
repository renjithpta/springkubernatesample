package com.ibsplc.itin.framework;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
/**
 * 
 * @author A-4211
 *
 */
public class iTinHibernateCallback implements HibernateCallback {

	private Object[] arguments = null;

	public iTinHibernateCallback(Object[] args) {
		arguments = args;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	@Override
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
