package com.ibsplc.itin.framework;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author A-4211
 *
 */
public class LoggingInterceptor implements MethodInterceptor {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoggingInterceptor.class);

	private static final String DATE_FORMAT = "yyyy-MM-dd' T 'HH:mm:ss.SSS Z";
	private boolean dateInfoRequiredInLog = true;

	public boolean isDateInfoRequiredInLog() {
		return dateInfoRequiredInLog;
	}

	public void setDateInfoRequiredInLog(boolean dateInfoRequiredInLog) {
		this.dateInfoRequiredInLog = dateInfoRequiredInLog;
	}

	/**
	 * AroundAdvice facility provided by the MethodInterceptor interface. Method
	 * entries and exits are logged.
	 * 
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		String methodName = null;
		String className = null;

		Method method = invocation.getMethod();
		methodName = method.getName();
		className = method.getDeclaringClass().getName();

		long entryTimeInMillis = System.currentTimeMillis();
		StringBuilder buffer = new StringBuilder(256);
		Date entryDate = new Date(entryTimeInMillis);
		buffer.append("Entering " + methodName + " of " + className);
		if (isDateInfoRequiredInLog()) {
			buffer.append(" at "
					+ new SimpleDateFormat(DATE_FORMAT).format(entryDate));
		}

		LOGGER.debug(buffer.toString());

		result = invocation.proceed();

		long exitTimeInMillis = System.currentTimeMillis();
		Date exitDate = new Date(exitTimeInMillis);
		buffer.setLength(0);
		buffer.append("Exited " + methodName + " of " + className);
		if (isDateInfoRequiredInLog()) {
			buffer.append(" at "
					+ new SimpleDateFormat(DATE_FORMAT).format(exitDate));
		}
		buffer.append("\t");
		buffer.append(exitTimeInMillis - entryTimeInMillis);
		buffer.append("ms");
		LOGGER.debug(buffer.toString());

		return result;
	}

}
