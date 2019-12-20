package com.ibsplc.itin.vo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author A-4211
 * 
 */
@XmlRootElement(name = "travelDetail")
public class TravelVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String origin;
	private String orginCode;
	private String destinationCode;
	private String destination;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private boolean returnTravel;         
	private String isBillable;            // newly added on May 13, 2013 by Savin Koshy (A-5152)
	private String remark;            // newly added on May 13, 2013 by Savin Koshy (A-5152)
	private int modeOfTravel;
	private String exactDestination;
	private String excatOrgin;
	
	public String getExactDestination() {
		return exactDestination;
	}

	public void setExactDestination(String exactDestination) {
		this.exactDestination = exactDestination;
	}

	public String getExcatOrgin() {
		return excatOrgin;
	}

	public void setExcatOrgin(String excatOrgin) {
		this.excatOrgin = excatOrgin;
	}

	public int getModeOfTravel() {
		return modeOfTravel;
	}

	public void setModeOfTravel(int modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}

	public String getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}

	/**
	 * @return the origin
	 */
	@XmlElement(name = "origin")
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@XmlElement(name = "destination")
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@XmlElement(name = "departureDate")
	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	@XmlElement(name = "departureTime")
	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the orginCode
	 */
	@XmlElement(name = "originCode")
	public String getOrginCode() {
		return orginCode;
	}

	/**
	 * @param orginCode
	 *            the orginCode to set
	 */
	public void setOrginCode(String orginCode) {
		this.orginCode = orginCode;
	}

	/**
	 * @return the destinationCode
	 */
	@XmlElement(name = "destinationCode")
	public String getDestinationCode() {
		return destinationCode;
	}

	/**
	 * @param destinationCode
	 *            the destinationCode to set
	 */
	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	/**
	 * @return the arrivalDate
	 */
	@XmlElement(name = "arrivalDate")
	public String getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * @param arrivalDate
	 *            the arrivalDate to set
	 */
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/**
	 * @return the arrivalTime
	 */
	@XmlElement(name = "arrivalTime")
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime
	 *            the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * @return the returnTravel
	 */
	@XmlElement(name="returnTravel")
	public boolean isReturnTravel() {
		return returnTravel;
	}

	/**
	 * @param returnTravel the returnTravel to set
	 */
	public void setReturnTravel(boolean returnTravel) {
		this.returnTravel = returnTravel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
