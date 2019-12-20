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
@Table(name = "ITIN_DTA_REQ_TRV")
public class DTAFlightTrainEntity {

	@Id
	@Column(name = "TRV_REQ_ID")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="FLIGHT_SEQ")
	private int reqId;

	@Column(name = "DTA_CDE")
	private int dtaNumber;// FK

	@Column(name = "DTA_IND_REQ_NUM")
	private int indReqNum;

	@Column(name = "TRV_MDE")
	private int travelMode;
	
	@Column(name = "TRV_ORG_CDE")
	private String originCode;
	

	@Column(name = "TRV_ORG_EXC_LOC")
	private String originExactLocation;

	@Column(name = "TRV_DEP_DTE")
	private Date departureDate;

	@Column(name = "TRV_DEP_PRE_TIM")
	private String prefferedDepTime;

	@Column(name = "TRV_DST_CDE")
	private String destinationCode;

	@Column(name = "TRV_DST_EXC_LOC")
	private String destiantionExactLocation;

	@Column(name = "TRV_ARR_DAT")
	private Date Arrivaldate;
	
	
	@Column(name = "TRV_ARR_PRE_TIM")
	private String prefferedArrTime;
	
	@Column(name = "TRV_LEG_REM")
	private String legRemarks;

	@Column(name = "LST_UPD_USR")
	private int updatedBy;


	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;
	

	public int getReqId() {
		return reqId;
	}


	public void setReqId(int reqId) {
		this.reqId = reqId;
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


	public int getTravelMode() {
		return travelMode;
	}


	public void setTravelMode(int travelMode) {
		this.travelMode = travelMode;
	}


	public String getOriginCode() {
		return originCode;
	}


	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}


	public String getOriginExactLocation() {
		return originExactLocation;
	}


	public void setOriginExactLocation(String originExactLocation) {
		this.originExactLocation = originExactLocation;
	}


	public Date getDepartureDate() {
		return departureDate;
	}


	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}


	public String getPrefferedDepTime() {
		return prefferedDepTime;
	}


	public void setPrefferedDepTime(String prefferedDepTime) {
		this.prefferedDepTime = prefferedDepTime;
	}


	public String getDestinationCode() {
		return destinationCode;
	}


	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}


	public String getDestiantionExactLocation() {
		return destiantionExactLocation;
	}


	public void setDestiantionExactLocation(String destiantionExactLocation) {
		this.destiantionExactLocation = destiantionExactLocation;
	}


	


	public Date getArrivaldate() {
		return Arrivaldate;
	}


	public void setArrivaldate(Date arrivaldate) {
		Arrivaldate = arrivaldate;
	}


	public String getPrefferedArrTime() {
		return prefferedArrTime;
	}


	public void setPrefferedArrTime(String prefferedArrTime) {
		this.prefferedArrTime = prefferedArrTime;
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

}
