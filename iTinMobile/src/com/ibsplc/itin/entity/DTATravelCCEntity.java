package com.ibsplc.itin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "ITIN_DTA_TRAVEL_CC_ALL")
public class DTATravelCCEntity {

	@Id
	@Column(name = "DTA_CDE")
	private int dtaNumber;
	
	@Column(name = "CST_ALCN_PRT")
	private int costAllocation;

	@Column(name = "CC_CDE")
	private String ccCode;

	@Column(name = "TVL_BO")
	private String boCode;

	
	
	private final static Logger LOGGER = Logger.getLogger(DTATravelCCEntity.class);


	public int getDtaNumber() {
		return dtaNumber;
	}

	public void setDtaNumber(int dtaNumber) {
		this.dtaNumber = dtaNumber;
	}
	
	public int getCostAllocation() {
		return costAllocation;
	}

	public void setCostAllocation(int costAllocation) {
		this.costAllocation = costAllocation;
	}
	public String getCcCode() {
		return ccCode;
	}

	public void setCcCode(String ccCode) {
		this.ccCode = ccCode;
	}

	public String getBoCode() {
		return boCode;
	}

	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}

	

	

}
