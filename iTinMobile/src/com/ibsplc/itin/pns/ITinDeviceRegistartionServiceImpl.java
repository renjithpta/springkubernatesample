package com.ibsplc.itin.pns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

@Path("/itinpns")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ITinDeviceRegistartionServiceImpl implements ITinDeviceRegistration{

	String Registration_URL = "http://192.168.16.36:8080/itinpns/mobility/register";
		
	//Redirect the call to Registration_URL
	@POST
	@Path("/register")
	@Override
	public String setDeviceInfo(DeviceInfo deviceInfo) {
		
				
			try {
				
				JSONObject mainDeviceInfo = new JSONObject();
				JSONObject dInfo = new JSONObject();
				dInfo.put("deviceid", deviceInfo.getDeviceid());
				dInfo.put("devicetype", deviceInfo.getDevicetype());
				dInfo.put("empid", deviceInfo.getEmpid());
				mainDeviceInfo.put("deviceInfo", dInfo);
				
				
				System.out.println(mainDeviceInfo.toString());
				return sendRegistration(mainDeviceInfo.toString(),Registration_URL);
				
				
			} catch (Exception exception) {
				
				exception.printStackTrace();
			}
			
		
		
		return "Sorry not registered";
	}

	
	private String sendRegistration(String body, String url)throws Exception {   
		
		String response = "";
		URL endpointURL;
		try {
			endpointURL = new URL(url);
		} catch (MalformedURLException e) {
		    throw new IllegalArgumentException("invalid url: " + url);
		}

		byte[] bytes = body.getBytes();
		HttpURLConnection conn = null;
		try {
   
		conn = (HttpURLConnection) endpointURL.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setFixedLengthStreamingMode(bytes.length);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	    // post the request
	    OutputStream out = conn.getOutputStream();
	    out.write(bytes);
	    out.close();
        // handle the response
	    int status = conn.getResponseCode();
	    if (status != 200) {
	      throw new IOException("Post failed with error code " + status);
	    }else if(status == 200){
	    	 
	    	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    	    for (String line; (line = in.readLine()) != null;) {
	    	    	response  = response + line;
	            }
	    	    
	    	    in.close();
	    }
	} finally {
	    if (conn != null) {
	        conn.disconnect();
	    }
	}
	return response;
	}
	
}
