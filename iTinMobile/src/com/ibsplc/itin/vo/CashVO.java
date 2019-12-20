package com.ibsplc.itin.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cashDetail")
public class CashVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String headName;
	private String headCode;
	private String currency;
	private String currencyCode;
	private String billable;
	private String amount;
	private String amountInUSD;
	private String noNights;
	private String rate;
	private String countryCode;
	private String visaType;
	private String createdBy;
	private String createdOn;
	
	
	private String isCshAdvReq;
	
	
	
@XmlElement(name="headName")
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getIsCshAdvReq() {
		return isCshAdvReq;
	}
	public void setIsCshAdvReq(String isCshAdvReq) {
		this.isCshAdvReq = isCshAdvReq;
	}
	@XmlElement(name="currency")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@XmlElement(name="billable")
	public String getBillable() {
		return billable;
	}
	public void setBillable(String billable) {
		this.billable = billable;
	}
	@XmlElement(name="amount")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@XmlElement(name="noNights")
	public String getNoNights() {
		return noNights;
	}
	public void setNoNights(String noNights) {
		this.noNights = noNights;
	}
	
	@XmlElement(name="rate")
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@XmlElement(name="countryCode")
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@XmlElement(name="visaType")
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	
	@XmlElement(name="headCode")
	public String getHeadCode() {
		return headCode;
	}
	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}
	@XmlElement(name="currencyCode")
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@XmlElement(name="amountInUSD")
	public String getAmountInUSD() {
		return amountInUSD;
	}
	public void setAmountInUSD(String amountInUSD) {
		this.amountInUSD = amountInUSD;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
	
}
