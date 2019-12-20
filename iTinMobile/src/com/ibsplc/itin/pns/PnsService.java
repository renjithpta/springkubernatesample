package com.ibsplc.itin.pns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jettison.json.JSONObject;

public class PnsService {
	
	private String PNS_URL = "http://192.168.16.36:8080/itinpns/mobility/action/notification";
	
	

	public synchronized void sendNotification(Content content){
		
		try{
		
			JSONObject jContent = new JSONObject();
			JSONObject ob = new JSONObject();
			ob.put("empID",content.getEmpID());
			ob.put("data", content.getData());
			ob.put("description", content.getDescription());
			
			jContent.put("content", ob);
									
			sendNotification(jContent.toString(),PNS_URL);
		}catch (Exception e) {
	    }
		
	}
	
	
	private String sendNotification(String body, String url)throws Exception {   
		
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

	public static void main(String str[]){
		Content content = new Content();
		content.setData("Hello Data");
		content.setDescription("YEsd ata is available");
		content.setEmpID("A-4211");
		
		new PnsService().sendNotification(content);
	}
	
}
