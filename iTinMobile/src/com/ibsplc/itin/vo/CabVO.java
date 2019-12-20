package com.ibsplc.itin.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cabDetail")
public class CabVO {

	private String cityCode;
	private String cityName;

	private String pickUpPoint;
	private String pickUpTime;
	private String dropPoint;
	private String contactNo;
	private String guestContactNo;
	private String pickUpDate;
	private String remarks;
	private String retVehicle;
	private String retDuration;
	private String remark;
	private String updatedBy;

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRetDuration() {
		return retDuration;
	}

	public void setRetDuration(String retDuration) {
		this.retDuration = retDuration;
	}

	/**
	 * @return the cityCode
	 */
	@XmlElement(name = "cityCode")
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the cityName
	 */
	@XmlElement(name = "cityName")
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the pickUpPoint
	 */
	@XmlElement(name = "pickUpPoint")
	public String getPickUpPoint() {
		return pickUpPoint;
	}

	/**
	 * @param pickUpPoint
	 *            the pickUpPoint to set
	 */
	public void setPickUpPoint(String pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}

	/**
	 * @return the pickUpTime
	 */
	@XmlElement(name = "pickUpTime")
	public String getPickUpTime() {
		return pickUpTime;
	}

	/**
	 * @param pickUpTime
	 *            the pickUpTime to set
	 */
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	/**
	 * @return the dropPoint
	 */
	@XmlElement(name = "dropPoint")
	public String getDropPoint() {
		return dropPoint;
	}

	/**
	 * @param dropPoint
	 *            the dropPoint to set
	 */
	public void setDropPoint(String dropPoint) {
		this.dropPoint = dropPoint;
	}

	/**
	 * @return the contactNo
	 */
	@XmlElement(name = "contactNo")
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo
	 *            the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the guestContactNo
	 */
	@XmlElement(name = "guestContactNo")
	public String getGuestContactNo() {
		return guestContactNo;
	}

	/**
	 * @param guestContactNo
	 *            the guestContactNo to set
	 */
	public void setGuestContactNo(String guestContactNo) {
		this.guestContactNo = guestContactNo;
	}

	/**
	 * @return the pickUpDate
	 */
	@XmlElement(name = "pickUpDate")
	public String getPickUpDate() {
		return pickUpDate;
	}

	/**
	 * @param pickUpDate
	 *            the pickUpDate to set
	 */
	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
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

	public String getRetVehicle() {
		return retVehicle;
	}

	public void setRetVehicle(String retVehicle) {
		this.retVehicle = retVehicle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
