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
@Table(name = "ITIN_DTA_REQ_CAB")
public class DTACablEntity {

	@Id
	@Column(name = "CAB_REQ_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="FLIGHT_SEQ")
	private int cabId;

	@Column(name = "DTA_CDE")
	private int dtaNumber;// FK
	
	
	@Column(name = "DTA_IND_REQ_NUM")
	private int indReqNum;

	@Column(name = "CAB_ORG_CDE")
	private String orgin;

	@Column(name = "CAB_ORG_EXC_LOC")
	private String exactOrgin;
	
	@Column(name = "CON_NUM")
	private String contactNumber;

	@Column(name = "GST_CON_NUM")
	private String guestContactNumber;
	
	@Column(name = "CAB_DEP_DTE")
	private Date departureDate;

	@Column(name = "CAB_DST_CDE")
	private String destination;

	@Column(name = "CAB_DST_EXC_LOC")
	private String exactDestination;

	@Column(name = "CAB_DEP_PRE_TIM")
	private String prefferedDepTime;
	
	@Column(name = "CAB_ARR_PRE_TIM")
	private String prefferedArrTime;
	
	@Column(name = "CAB_ARR_DAT")
	private String arrivalDate;
	

	@Column(name = "CAB_LEG_REM")
	private String legRemarks;

	@Column(name = "CAB_EXC_REM")
	private String legExcRemarks;

	@Column(name = "LST_UPD_USR")
	private String updatedBy;

	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;

	@Column(name = "CAB_RET")
	private String cabReturn;                   

	@Column(name = "CAB_RET_DUR")
	private String cabRetainDuration; 

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}

	public int getDtaNumber() {
		return dtaNumber;
	}

	public void setDtaNumber(int dtaNumber) {
		this.dtaNumber = dtaNumber;
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

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getIndReqNum() {
		return indReqNum;
	}

	public void setIndReqNum(int indReqNum) {
		this.indReqNum = indReqNum;
	}

	public String getPrefferedDepTime() {
		return prefferedDepTime;
	}

	public void setPrefferedDepTime(String prefferedDepTime) {
		this.prefferedDepTime = prefferedDepTime;
	}

	public String getPrefferedArrTime() {
		return prefferedArrTime;
	}

	public void setPrefferedArrTime(String prefferedArrTime) {
		this.prefferedArrTime = prefferedArrTime;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getLegRemarks() {
		return legRemarks;
	}

	public void setLegRemarks(String legRemarks) {
		this.legRemarks = legRemarks;
	}

	public String getLegExcRemarks() {
		return legExcRemarks;
	}

	public void setLegExcRemarks(String legExcRemarks) {
		this.legExcRemarks = legExcRemarks;
	}

	public String getCabReturn() {
		return cabReturn;
	}

	public void setCabReturn(String cabReturn) {
		this.cabReturn = cabReturn;
	}

	public String getExactOrgin() {
		return exactOrgin;
	}

	public void setExactOrgin(String exactOrgin) {
		this.exactOrgin = exactOrgin;
	}

	public String getExactDestination() {
		return exactDestination;
	}

	public void setExactDestination(String exactDestination) {
		this.exactDestination = exactDestination;
	}

	public String getCabRetainDuration() {
		return cabRetainDuration;
	}

	public void setCabRetainDuration(String cabRetainDuration) {
		this.cabRetainDuration = cabRetainDuration;
	}

	public String getGuestContactNumber() {
		return guestContactNumber;
	}

	public void setGuestContactNumber(String guestContactNumber) {
		this.guestContactNumber = guestContactNumber;
	}
	
}
