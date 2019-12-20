package com.ibsplc.itin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIN_COM_MST")
public class CommonMastersEntity {

	
	@Column(name = "MST_KEY")
	private String masterKey; // FK
	@Id
	@Column(name = "MST_CDE")
	private String masterCode;

	@Column(name = "MST_VAL")
	private String masterValue;

	@Column(name = "MST_DSC")
	private String masterDesc;

	@Column(name = "CV1_VAL")
	private String configValue1;

	@Column(name = "CV2_VAL")
	private String configValue2;

	@Column(name = "CV3_VAL")
	private String configValue3;

	@Column(name = "MST_REM")
	private String remarks;

	@Column(name = "MST_STS")
	private Character status; // FK

	@Column(name = "CRT_USR")
	private String createdBy;

	@Column(name = "CRT_DAT")
	private Date createdOn;

	@Column(name = "LST_UPD_USR")
	private String updatedBy;

	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getMasterValue() {
		return masterValue;
	}

	public void setMasterValue(String masterValue) {
		this.masterValue = masterValue;
	}

	public String getMasterDesc() {
		return masterDesc;
	}

	public void setMasterDesc(String masterDesc) {
		this.masterDesc = masterDesc;
	}

	public String getConfigValue1() {
		return configValue1;
	}

	public void setConfigValue1(String configValue1) {
		this.configValue1 = configValue1;
	}

	public String getConfigValue2() {
		return configValue2;
	}

	public void setConfigValue2(String configValue2) {
		this.configValue2 = configValue2;
	}

	public String getConfigValue3() {
		return configValue3;
	}

	public void setConfigValue3(String configValue3) {
		this.configValue3 = configValue3;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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

}
