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
@Table(name = "ITIN_DTA_REQ_HTL")
public class DTAHotelEntity {

	@Id
	@Column(name = "HLT_REQ_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="FLIGHT_SEQ")
	private int hotelId;

	@Column(name = "DTA_CDE")
	private int dtaNumber;// FK
	
	
	@Column(name = "DTA_IND_REQ_NUM")
	private int indReqNum;
	
//	@Column(name = "BKN_REQ")
//	private String bookingRequested;

	@Column(name = "HTL_CTY_CDE")
	private String city;

	@Column(name = "HTL_CTY_EXC_LOC")
	private String exactLocation;

	@Column(name = "CHK_IN_DTE")
	private Date checkInDate;
	
	
	@Column(name = "HTL_CKIN_PRE_TIM")
	private String checkInPreferredTime;

	@Column(name = "CHK_OUT_DTE")
	private Date checkOutDate;
	
	
	@Column(name = "HTL_CKOT_PRE_TIM")
	private String checkOutPreferredTime;

	@Column(name = "NOF_DAYS")
	private int numberOfDays;

//	@Column(name = "NME")
//	private String name;

	@Column(name = "HTL_CLS")
	private String hotelClass;
	

	@Column(name = "HTL_LEG_REM")
	private String legRemarks;

	@Column(name = "HTL_EXC_REM")
	private String legExcRemarks;
	
	@Column(name = "LST_UPD_USR")
	private String updatedBy;

	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public int getDtaNumber() {
		return dtaNumber;
	}

	public void setDtaNumber(int dtaNumber) {
		this.dtaNumber = dtaNumber;
	}

	

	public void setCity(String city) {
		this.city = city;
	}

	public String getExactLocation() {
		return exactLocation;
	}

	public void setExactLocation(String exactLocation) {
		this.exactLocation = exactLocation;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	

	public int getIndReqNum() {
		return indReqNum;
	}

	public void setIndReqNum(int indReqNum) {
		this.indReqNum = indReqNum;
	}

	

	public String getCity() {
		return city;
	}

	public String getHotelClass() {
		return hotelClass;
	}

	public void setHotelClass(String hotelClass) {
		this.hotelClass = hotelClass;
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

	public String getCheckInPreferredTime() {
		return checkInPreferredTime;
	}

	public void setCheckInPreferredTime(String checkInPreferredTime) {
		this.checkInPreferredTime = checkInPreferredTime;
	}

	public String getCheckOutPreferredTime() {
		return checkOutPreferredTime;
	}

	public void setCheckOutPreferredTime(String checkOutPreferredTime) {
		this.checkOutPreferredTime = checkOutPreferredTime;
	}

}
