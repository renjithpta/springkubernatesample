package com.ibsplc.itin.cas.cache;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.ExchangeHelper;
import org.codehaus.jettison.json.JSONObject;

/**
 * 
 * @author A-4211
 * 
 */
public class TokenCache implements Processor {

	private CacheHandler cacheHandler;

	public void process(Exchange exchange) throws Exception {

		String content = exchange.getIn().getBody(String.class);
		
		handleResponse(content);
		if (ExchangeHelper.isOutCapable(exchange)) {
			exchange.getOut().copyFrom(exchange.getIn());
			exchange.getOut().setBody(content);
		}
	}

	private void handleResponse(String content) {

		try {

			JSONObject cacheData = new JSONObject(content);
			JSONObject ssoMessage = (JSONObject) cacheData.get("ssoMessage");
			String serviceToken = ssoMessage.get("serviceToken").toString();
			String username = ssoMessage.get("username").toString();

			if (serviceToken != null && username != null) {
				cacheHandler.registerToken(serviceToken, username);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * @return the cacheHandler
	 */
	public CacheHandler getCacheHandler() {
		return cacheHandler;
	}

	/**
	 * @param cacheHandler
	 *            the cacheHandler to set
	 */
	public void setCacheHandler(CacheHandler cacheHandler) {
		this.cacheHandler = cacheHandler;
	}

}
