package com.ibsplc.itin.entity;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "ITIN_DTA_REQ_ADV")
public class DTACashAdvanceEntity {

	@Id
	@Column(name = "DTA_ADV_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "ITIN_CASH_ADV_SEQ")
	private int cashAdvanceId;
		
	
	@Column(name = "DTA_CDE")
	private String dtaCode;
	
	@Column(name = "ADV_ITM_TYP")
	private int typeComboValue;
	
	@Column(name = "ADV_IN_USD")
	private long amountInUsd;
	
	@Column(name = "ADV_RTE")
	private String advanceRate;
	
	@Column(name = "ITM_QTY")
	private String numDays;
	
	@Column(name = "ADV_AMT")
	private String advanceTotal;
	
	@Column(name = "ADV_CUR")
	private String advanceCurrency;
	
	@Column(name = "INI_REM")
	private String initiatorRemarks;
	
	@Column(name = "CRT_USR")
	private String createdBy;

	@Column(name = "CRT_DAT")
	private Date createdDate;

	@Column(name = "LST_UPD_USR")
	private String lastUpdatedUser;

	@Column(name = "LST_UPD_DAT")
	private Date lastUpdatedDate;
	
	@Column(name = "PDM_ID")
	private int pdmid;
	
	@Column(name = "IS_CSH_ADV_REQ")
	private String cashAdvanceRequired;
	
	@Column(name = "IS_BIL_CST")
	private String billableToCustomer;
	
	@Column(name = "CST_CUR")
	private int maxLimitCurrency;
	
	@Column(name = "MAX_LMT")
	private String maximumLimit;
	
	@Column(name = "BIL_PRT")
	private String billablePercent;
	
	//Is User Entered
	@Column(name = "IS_USR_ENT")
	private String isUserEntered;
	
	//Status
	@Column(name = "MST_STS")
	private String status;
	
	//From Parent DTA
	@Column(name = "FRM_PAR_DTA")
	private String fromParDta;
	
	//From Parent Cash Adv
	@Column(name = "PAR_DTA_ADV_ID")
	private int parCashAdvanceId;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillableToCustomer() {
		return billableToCustomer;
	}

	public void setBillableToCustomer(String billableToCustomer) {
		this.billableToCustomer = billableToCustomer;
	}

	

	public String getMaximumLimit() {
		return maximumLimit;
	}

	public void setMaximumLimit(String maximumLimit) {
		this.maximumLimit = maximumLimit;
	}

	public int getCashAdvanceId() {
		return cashAdvanceId;
	}

	public void setCashAdvanceId(int cashAdvanceId) {
		this.cashAdvanceId = cashAdvanceId;
	}

	public String getDtaCode() {
		return dtaCode;
	}

	public void setDtaCode(String dtaCode) {
		this.dtaCode = dtaCode;
	}

	public int getTypeComboValue() {
		return typeComboValue;
	}

	public void setTypeComboValue(int typeComboValue) {
		this.typeComboValue = typeComboValue;
	}

	public String getAdvanceRate() {
		return advanceRate;
	}

	public void setAdvanceRate(String advanceRate) {
		this.advanceRate = advanceRate;
	}

	public String getNumDays() {
		return numDays;
	}

	public void setNumDays(String numDays) {
		this.numDays = numDays;
	}

	public String getAdvanceTotal() {
		return advanceTotal;
	}

	public void setAdvanceTotal(String advanceTotal) {
		this.advanceTotal = advanceTotal;
	}

	public String getAdvanceCurrency() {
		return advanceCurrency;
	}

	public void setAdvanceCurrency(String advanceCurrency) {
		this.advanceCurrency = advanceCurrency;
	}

	public String getInitiatorRemarks() {
		return initiatorRemarks;
	}

	public void setInitiatorRemarks(String initiatorRemarks) {
		this.initiatorRemarks = initiatorRemarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public long getAmountInUsd() {
		return amountInUsd;
	}

	public void setAmountInUsd(long amountInUsd) {
		this.amountInUsd = amountInUsd;
	}

	public int getPdmid() {
		return pdmid;
	}

	public void setPdmid(int pdmid) {
		this.pdmid = pdmid;
	}

	public String getCashAdvanceRequired() {
		return cashAdvanceRequired;
	}

	public void setCashAdvanceRequired(String cashAdvanceRequired) {
		this.cashAdvanceRequired = cashAdvanceRequired;
	}

	public int getMaxLimitCurrency() {
		return maxLimitCurrency;
	}

	public void setMaxLimitCurrency(int maxLimitCurrency) {
		this.maxLimitCurrency = maxLimitCurrency;
	}

	public String getBillablePercent() {
		return billablePercent;
	}

	public void setBillablePercent(String billablePercent) {
		this.billablePercent = billablePercent;
	}

	//Is User Entered
	public String getIsUserEntered() {
		return isUserEntered;
	}

	public void setIsUserEntered(String isUserEntered) {
		this.isUserEntered = isUserEntered;
	}

	public String getFromParDta() {
		return fromParDta;
	}

	public void setFromParDta(String fromParDta) {
		this.fromParDta = fromParDta;
	}

	public int getParCashAdvanceId() {
		return parCashAdvanceId;
	}

	public void setParCashAdvanceId(int parCashAdvanceId) {
		this.parCashAdvanceId = parCashAdvanceId;
	}

	
	
	
	
	
	
	
}
