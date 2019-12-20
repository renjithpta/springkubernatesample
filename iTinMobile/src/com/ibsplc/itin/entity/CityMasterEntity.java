package com.ibsplc.itin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITIN_CTY_MST")
public class CityMasterEntity {

	@Id
	@Column(name = "CTY_CDE")
	private String cityCode;

	@Column(name = "CTY_NAM")
	private String cityName; // FK

	@Column(name = "CNY_CDE")
	private String countryCode; // FK

	@Column(name = "REG_CDE")
	private Integer regionCode;

	@Column(name = "CTY_GMT")
	private String gmt;

	@Column(name = "BAS_STA")
	private String baseStation;

	@Column(name = "IS_TRN_CTY")
	private Character train;

	@Column(name = "IS_CAB_CTY")
	private Character cab;

	@Column(name = "IS_FLT_CTY")
	private Character flight;

	@Column(name = "CTY_CUR")
	private String currency; // FK

	@Column(name = "CON_PER")
	private String contactPerson;

	@Column(name = "CON_ADD_PRT1")
	private String contactAddress;

	@Column(name = "CTY_REM")
	private String remarks;

	@Column(name = "CRT_USR")
	private String createdBy;

	@Column(name = "CRT_DAT")
	private Date createdOn;

	@Column(name = "LST_UPD_USR")
	private String updatedBy;

	@Column(name = "LST_UPD_DAT")
	private Date updatedOn;
	
	@Column(name = "CON_ADD_PRT2")
	private String contactAddressPart2;
	
	@Column(name = "CON_ADD_PRT3")
	private String contactAddressPart3;
	


	public String getCityCode() {
		return cityCode;
	}

	@GeneratedValue(strategy = GenerationType.AUTO)
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	public String getGmt() {
		return gmt;
	}

	public void setGmt(String gmt) {
		this.gmt = gmt;
	}

	public String getBaseStation() {
		return baseStation;
	}

	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation;
	}

	public Character getTrain() {
		return train;
	}

	public void setTrain(Character train) {
		this.train = train;
	}

	public Character getFlight() {
		return flight;
	}

	public void setFlight(Character flight) {
		this.flight = flight;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getContactAddressPart2() {
		return contactAddressPart2;
	}

	public void setContactAddressPart2(String contactAddressPart2) {
		this.contactAddressPart2 = contactAddressPart2;
	}

	public String getContactAddressPart3() {
		return contactAddressPart3;
	}

	public void setContactAddressPart3(String contactAddressPart3) {
		this.contactAddressPart3 = contactAddressPart3;
	}

	public Integer getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}

	public Character getCab() {
		return cab;
	}

	public void setCab(Character cab) {
		this.cab = cab;
	}

}
