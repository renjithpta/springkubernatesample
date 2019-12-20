package com.ibsplc.itin.activemq.listner;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibsplc.itin.database.dao.ITinTravelServiceDao;
import com.ibsplc.itin.vo.ApproverVO;

/**
 * Listener Implement Spring SessionAwareMessageListener Interface
 * 
 */
public class JmsMessageListener implements
		SessionAwareMessageListener<ActiveMQTextMessage> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JmsMessageListener.class);

	private ITinTravelServiceDao iTinTravelServiceDao;

	public void setITinTravelServiceDao(
			ITinTravelServiceDao iTinTravelServiceDao) {
		this.iTinTravelServiceDao = iTinTravelServiceDao;
	}

	@Override
	public void onMessage(ActiveMQTextMessage message, Session session)
			throws JMSException {

		System.out.println("-------------------in------------------------------------------------------");
	
		message.acknowledge();

	}
}