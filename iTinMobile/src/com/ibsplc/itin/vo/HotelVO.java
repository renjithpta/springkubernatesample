package com.ibsplc.itin.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hotelDetail")
public class HotelVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cityCode;
	private String cityName;
	private String checkInDate;
	private String checkOutDate;
	private String checkInTime;
	private String checkOutTime;
	private String exactLocation;
	private String remarks;
	private String isBillable;

	public String getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
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
	 * @return the checkInDate
	 */
	@XmlElement(name = "checkInDate")
	public String getCheckInDate() {
		return checkInDate;
	}

	/**
	 * @param checkInDate
	 *            the checkInDate to set
	 */
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	/**
	 * @return the checkOutDate
	 */
	@XmlElement(name = "checkOutDate")
	public String getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * @param checkOutDate
	 *            the checkOutDate to set
	 */
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	/**
	 * @return the checkInTime
	 */
	@XmlElement(name = "checkInTime")
	public String getCheckInTime() {
		return checkInTime;
	}

	/**
	 * @param checkInTime
	 *            the checkInTime to set
	 */
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	/**
	 * @return the checkOutTime
	 */
	@XmlElement(name = "checkOutTime")
	public String getCheckOutTime() {
		return checkOutTime;
	}

	/**
	 * @param checkOutTime
	 *            the checkOutTime to set
	 */
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
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
	 * @return the exactLocation
	 */
	@XmlElement(name = "exactLocation")
	public String getExactLocation() {
		return exactLocation;
	}

	/**
	 * @param exactLocation the exactLocation to set
	 */
	public void setExactLocation(String exactLocation) {
		this.exactLocation = exactLocation;
	}

	
}
