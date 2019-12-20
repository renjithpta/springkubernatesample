package com.ibsplc.itin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITIN_DTA_FLT_REQ")
public class DTAFlightEntity {

	@Id
	@Column(name = "FLT_REQ_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="FLIGHT_SEQ")
	private int flightId;

	@Column(name = "DTA_CDE")
	private int dtaNumber;// FK

	@Column(name = "DTA_IND_REQ_NUM")
	private int indReqNum;
	
	@Column(name = "FLT_ORG_CDE")
	private String orgin;
	
	@Column(name = "FLT_DEP_DTE")
	private Date departureDate;


	@Column(name = "FLT_DEP_PRE_TIM")
	private String prefferedTimeDep;
	
	@Column(name = "FLT_DST_CDE")
	private String destination;
		
	
	@Column(name = "FLT_ARR_DAT")
	private Date arrivalDate;

	@Column(name = "FLT_ARR_PRE_TIM")
	private String prefferedTimeArr;
	
	@Column(name = "FLT_LEG_REM")
	private String legRemarks;

	@Column(name = "FLT_EXC_REM")
	private String legExcRemarks;
		
	@Column(name = "LST_UPD_USR")
	private int updatedBy;
	
	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;
	
	
	public int getFlightId() {
		return flightId;
	}
	
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getDtaNumber() {
		return dtaNumber;
	}

	public void setDtaNumber(int dtaNumber) {
		this.dtaNumber = dtaNumber;
	}

	public int getIndReqNum() {
		return indReqNum;
	}

	public void setIndReqNum(int indReqNum) {
		this.indReqNum = indReqNum;
	}

	public String getOrgin() {
		return orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getPrefferedTimeDep() {
		return prefferedTimeDep;
	}

	public void setPrefferedTimeDep(String prefferedTimeDep) {
		this.prefferedTimeDep = prefferedTimeDep;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getPrefferedTimeArr() {
		return prefferedTimeArr;
	}

	public void setPrefferedTimeArr(String prefferedTimeArr) {
		this.prefferedTimeArr = prefferedTimeArr;
	}

	public String getLegRemarks() {
		return legRemarks;
	}

	public void setLegRemarks(String legRemarks) {
		this.legRemarks = legRemarks;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getLegExcRemarks() {
		return legExcRemarks;
	}

	public void setLegExcRemarks(String legExcRemarks) {
		this.legExcRemarks = legExcRemarks;
	}

}
