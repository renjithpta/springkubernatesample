package com.ibsplc.itin.database.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			

			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"com/ibsplc/itin/database/dao/databaseApplication.xml");

			ITinTravelServiceDao travelServiceDao = (ITinTravelServiceDao) ctx
					.getBean("travelServiceDao");

			travelServiceDao.getMasterInformation("01-JAN-2009 12:00:00 AM");
			//travelServiceDao.getDTA("12487"); //dtacode
			
			//travelServiceDao.getApprovals("1790");
			//travelServiceDao.getBOProject("3143", "442", "N");
			
			//travelServiceDao.getTravelRequests("4472");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
