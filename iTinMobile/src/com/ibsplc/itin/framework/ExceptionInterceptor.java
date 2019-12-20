package com.ibsplc.itin.framework;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author A-4211
 *
 */
public class ExceptionInterceptor implements MethodInterceptor {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionInterceptor.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = null;
		Method method = invocation.getMethod();
		LOGGER.trace("Entering ExceptionInterceptor for {}", method);

		try {
			result = invocation.proceed();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw ex;
		}
		LOGGER.trace("Exiting ExceptionInterceptor for {}", method);
		return result;
	}

}
