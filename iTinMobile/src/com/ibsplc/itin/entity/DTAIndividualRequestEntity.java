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
@Table(name = " ITIN_DTA_REQ_IND_DTL")
public class DTAIndividualRequestEntity {

	@Id
	@Column(name = "DTA_IND_REQ_NUM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "DTA_IND_REQ_SEQ")
	private Integer dtaIndReqNum;

	@Column(name = "DTA_CDE")
	private Integer dtaNumber;

	@Column(name = "IND_ITM_CDE")
	private Integer indItemCode;

	@Column(name = "REQ_TYP")
	private Integer reqType;

	@Column(name = "EXP_REQ")
	private String excepRequested;

	@Column(name = "EXP_REM")
	private String excepRemarks;

	@Column(name = "DTA_STS")
	private Integer dtaStatus;

	@Column(name = "DTA_SUB_STS")
	private Integer dtaSubStatus;

	@Column(name = "DTA_ACN_BY")
	private Integer dtaAcnBy;

	@Column(name = "IS_ACT")
	private Character isAct;

	@Column(name = "CRT_USR")
	private Integer createdBy;

	@Column(name = "CRT_DAT")
	private Date createdOn;

	@Column(name = "LST_UPD_USR")
	private Integer updatedBy;

	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;

	@Column(name = "IS_QTE_SEL")
	private Character isQuoteSelected;

	@Column(name = "QTE_ID")
	private Integer quoteId;

	@Column(name = "QTE_TSP_ID")
	private String quoteTspId;

	@Column(name = "CAN_TSP_VIEW")
	private String canTspView;

	@Column(name = "ACC_CDE")
	private String accountCode;
	
	@Column(name = "FLT_REM")
	private String flightRemarks;
	
	@Column(name = "IS_BIL_CST")
	private String billableToCust;
	
	@Column(name = "IS_GUESTHOUSE_BOOK")
	private String guestHouseBooking;
	

	public Integer getDtaIndReqNum() {
		return dtaIndReqNum;
	}

	public void setDtaIndReqNum(Integer dtaIndReqNum) {
		this.dtaIndReqNum = dtaIndReqNum;
	}

	public Integer getDtaNumber() {
		return dtaNumber;
	}

	public void setDtaNumber(Integer dtaNumber) {
		this.dtaNumber = dtaNumber;
	}

	public String getExcepRequested() {
		return excepRequested;
	}

	public void setExcepRequested(String excepRequested) {
		this.excepRequested = excepRequested;
	}

	public String getExcepRemarks() {
		return excepRemarks;
	}

	public void setExcepRemarks(String excepRemarks) {
		this.excepRemarks = excepRemarks;
	}

	public Integer getIndItemCode() {
		return indItemCode;
	}

	public void setIndItemCode(Integer indItemCode) {
		this.indItemCode = indItemCode;
	}

	public Integer getReqType() {
		return reqType;
	}

	public void setReqType(Integer reqType) {
		this.reqType = reqType;
	}

	public Integer getDtaStatus() {
		return dtaStatus;
	}

	public void setDtaStatus(Integer dtaStatus) {
		this.dtaStatus = dtaStatus;
	}

	public Integer getDtaSubStatus() {
		return dtaSubStatus;
	}

	public void setDtaSubStatus(Integer dtaSubStatus) {
		this.dtaSubStatus = dtaSubStatus;
	}

	public Integer getDtaAcnBy() {
		return dtaAcnBy;
	}

	public void setDtaAcnBy(Integer dtaAcnBy) {
		this.dtaAcnBy = dtaAcnBy;
	}

	public char getIsAct() {
		return isAct;
	}

	public void setIsAct(char isAct) {
		this.isAct = isAct;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Character getIsQuoteSelected() {
		return isQuoteSelected;
	}

	public void setIsQuoteSelected(Character isQuoteSelected) {
		this.isQuoteSelected = isQuoteSelected;
	}

	public Integer getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Integer quoteId) {
		this.quoteId = quoteId;
	}

	public String getQuoteTspId() {
		return quoteTspId;
	}

	public void setQuoteTspId(String quoteTspId) {
		this.quoteTspId = quoteTspId;
	}

	public String getCanTspView() {
		return canTspView;
	}

	public void setCanTspView(String canTspView) {
		this.canTspView = canTspView;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getFlightRemarks() {
		return flightRemarks;
	}

	public void setFlightRemarks(String flightRemarks) {
		this.flightRemarks = flightRemarks;
	}

	public String getBillableToCust() {
		return billableToCust;
	}

	public void setBillableToCust(String billableToCust) {
		this.billableToCust = billableToCust;
	}

	public String getGuestHouseBooking() {
		return guestHouseBooking;
	}

	public void setGuestHouseBooking(String guestHouseBooking) {
		this.guestHouseBooking = guestHouseBooking;
	}

}
