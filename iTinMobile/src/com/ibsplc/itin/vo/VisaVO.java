package com.ibsplc.itin.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "visaDetail")
public class VisaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String visaName;
	private String countryCode;
	private String countryName;
	private String costCenter;
	private String costCenterCode;
	private String boProjectCode;
	private String boProject;
	private String remarks;
	private String validFrom;
	private String validTo;
	private String bookingAmount;
	private String bookingCompany;
	private String isBillable;

	public String getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}

	/**
	 * @return the visaName
	 */
	@XmlElement(name = "visaName")
	public String getVisaName() {
		return visaName;
	}

	/**
	 * @param visaName
	 *            the visaName to set
	 */
	public void setVisaName(String visaName) {
		this.visaName = visaName;
	}

	/**
	 * @return the countryCode
	 */
	@XmlElement(name = "countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the countryName
	 */
	@XmlElement(name = "countryName")
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the costCenter
	 */
	@XmlElement(name = "costCenter")
	public String getCostCenter() {
		return costCenter;
	}

	/**
	 * @param costCenter
	 *            the costCenter to set
	 */
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	/**
	 * @return the costCenterCode
	 */
	@XmlElement(name = "costCenterCode")
	public String getCostCenterCode() {
		return costCenterCode;
	}

	/**
	 * @param costCenterCode
	 *            the costCenterCode to set
	 */
	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	/**
	 * @return the boProjectCode
	 */
	@XmlElement(name = "boProjectCode")
	public String getBoProjectCode() {
		return boProjectCode;
	}

	/**
	 * @param boProjectCode
	 *            the boProjectCode to set
	 */
	public void setBoProjectCode(String boProjectCode) {
		this.boProjectCode = boProjectCode;
	}

	/**
	 * @return the boProject
	 */
	@XmlElement(name = "boProject")
	public String getBoProject() {
		return boProject;
	}

	/**
	 * @param boProject
	 *            the boProject to set
	 */
	public void setBoProject(String boProject) {
		this.boProject = boProject;
	}

	/**
	 * @return the remarks
	 */
	@XmlElement(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the validFrom
	 */
	@XmlElement(name = "validFrom")
	public String getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom
	 *            the validFrom to set
	 */
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return the validTo
	 */
	@XmlElement(name = "validTo")
	public String getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo
	 *            the validTo to set
	 */
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(String bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

	public String getBookingCompany() {
		return bookingCompany;
	}

	public void setBookingCompany(String bookingCompany) {
		this.bookingCompany = bookingCompany;
	}

}
