package com.ibsplc.itin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibsplc.itin.database.dao.ITinTravelServiceDao;

public class TestService {

	
	@Autowired
	private ITinTravelServiceDao iTinTravelServiceDao;

	public ITinTravelServiceDao getiTinTravelServiceDao() {
		return iTinTravelServiceDao;
	}

	public void setiTinTravelServiceDao(ITinTravelServiceDao iTinTravelServiceDao) {
		this.iTinTravelServiceDao = iTinTravelServiceDao;
	}
	
	
}
