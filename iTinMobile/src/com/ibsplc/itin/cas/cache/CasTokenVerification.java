package com.ibsplc.itin.cas.cache;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.codehaus.jettison.json.JSONObject;

/**
 * 
 * @author A-4211
 * 
 */
public class CasTokenVerification implements RequestHandler {

	private CacheHandler cacheHandler;

	/**
	 * 
	 */
	@Override
	public Response handleRequest(Message message, ClassResourceInfo info) {

		Response response = null;
		String errorMessage = "";
		boolean isToken = false;

		Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
		String request = (String) message.get(Message.REQUEST_URL);
		if (request.contains("masterInfo")) {
			return null;

		} else {

			if (headers != null) {

				if (headers.containsKey("cas-token") && headers.containsKey("username")) {
					String casToken = null;
					String userName = null;
					if (headers.get("cas-token") instanceof List) {
						casToken = headers.get("cas-token").get(0);
					}
					if (headers.get("username") instanceof List) {
						userName = headers.get("username").get(0);
					}

					if (casToken != null && userName != null) {
						isToken = true;
						if (cacheHandler.isValidToken(casToken, userName)) {
							return null;
						} else {
							errorMessage = createErrorMessage(
									"Cas Token is expired ",
									"Please login again", "100");
						}
					}

				}
				if (!isToken) {
					errorMessage = createErrorMessage(
							"cas-token or username is not present in the http header",
							"Please enter a cas-token and username in the http header of request",
							"101");
				}
			}

		}
			
//			while (headerNames.hasNext()) {
//				key = headerNames.next();
//				
//				
//
//				if (key.equals("cas-token")) {
//					isToken = true;
//					if (headers.get(key) instanceof List) {
//						String casToken = headers.get(key).get(0);
//						//System.out.println("Cas Token in HTTP HEADER ----- =  "+ casToken);
//						
//						if (cacheHandler.isValidToken(casToken)) {
//							
//							return null;
//						} else {
//							errorMessage = createErrorMessage(
//									"Cas Token is expired ",
//									"Please login again", "100");
//						}
//					}
//				}
//			}
//			if (!isToken) {
//				errorMessage = createErrorMessage(
//						"cas-token is not present in the http header",
//						"Please enter a cas-token in the http header of request",
//						"101");
//			}
//
//		}
//		}
		response = Response.status(Response.Status.SERVICE_UNAVAILABLE)
				.type(MediaType.APPLICATION_JSON).entity(errorMessage).build();
		return response;
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

	private String createErrorMessage(String error, String comment, String code) {

		if (error == null)
			return null;

		JSONObject errorMessage = new JSONObject();
		try {

			JSONObject errorDetail = new JSONObject();
			errorMessage.put("error", errorDetail);
			errorDetail.put("code", code);
			errorDetail.put("cause", error);
			errorDetail.put("comment", comment);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return errorMessage.toString();
	}
}
