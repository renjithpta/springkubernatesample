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
public class BookingVO implements Serializable {

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
       
	private String bookedBy;         
	private String modeOfTravel;
	private String airline;
	private String flightNum;
	private String pnr;
	private String ticketNum;
	

	public String getModeOfTravel() {
		return modeOfTravel;
	}

	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
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

	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookingVO [origin=" + origin + ", orginCode=" + orginCode
				+ ", destinationCode=" + destinationCode + ", destination="
				+ destination + ", departureDate=" + departureDate
				+ ", departureTime=" + departureTime + ", arrivalDate="
				+ arrivalDate + ", arrivalTime=" + arrivalTime + ", bookedBy="
				+ bookedBy + ", modeOfTravel=" + modeOfTravel + ", airline="
				+ airline + ", flightNum=" + flightNum + ", pnr=" + pnr
				+ ", ticketNum=" + ticketNum + "]";
	}

	
	
	
}
