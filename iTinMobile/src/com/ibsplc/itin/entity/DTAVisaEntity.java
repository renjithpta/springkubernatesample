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
@Table(name = "ITIN_DTA_REQ_VIS")

public class DTAVisaEntity {

	@Id
	@Column(name = "VIS_REQ_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ")
	@SequenceGenerator(name="SEQ", sequenceName="FLIGHT_SEQ")
	private int visaId;

	@Column(name = "DTA_CDE")
	private int dtaNumber;// FK

	@Column(name = "VIS_REF_NUM")
	private String visaRefNumber;

	@Column(name = "VIS_TYP")
	private String visaType;

	@Column(name = "TYP_REF_ID")
	private int visaReferenceId;

	@Column(name = "VIS_NME")
	private String visaName;

	@Column(name = "DTA_IND_REQ_NUM")
	private int indReqNum;

//	@Column(name = "VIS_REQ")
//	private String visaRequested;

	@Column(name = "VAL_FRM")
	private Date validFrom;

	@Column(name = "VAL_TIL")
	private Date validTill;

	@Column(name = "VIS_CON")
	private String country;

//	@Column(name = "PPT")
//	private String passport;

	@Column(name = "VIS_REM")
	private String remarks;

	@Column(name = "LST_UPD_USR")
	private String updatedBy;

	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;

	public int getVisaId() {
		return visaId;
	}

	public void setVisaId(int visaId) {
		this.visaId = visaId;
	}

	public int getDtaNumber() {
		return dtaNumber;
	}

	public void setDtaNumber(int dtaNumber) {
		this.dtaNumber = dtaNumber;
	}

	public String getVisaRefNumber() {
		return visaRefNumber;
	}

	public void setVisaRefNumber(String visaRefNumber) {
		this.visaRefNumber = visaRefNumber;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public int getVisaReferenceId() {
		return visaReferenceId;
	}

	public void setVisaReferenceId(int visaReferenceId) {
		this.visaReferenceId = visaReferenceId;
	}

	public String getVisaName() {
		return visaName;
	}

	public void setVisaName(String visaName) {
		this.visaName = visaName;
	}

	

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

}
