package com.ibsplc.itin.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Place")
public class PlaceVO {

	
	private String placeCode;
	private String placeName;
	//private String createdDate;
	//private String lastUpdatedDate;
	/**
	 * @return the placeCode
	 */
	@XmlElement(name="placeCode")
	public String getPlaceCode() {
		return placeCode;
	}
	/**
	 * @param placeCode the placeCode to set
	 */
	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}
	/**
	 * @return the placeName
	 */
	@XmlElement(name="placeName")
	public String getPlaceName() {
		return placeName;
	}
	/**
	 * @param placeName the placeName to set
	 */
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
//	public String getCreatedDate() {
//		return createdDate;
//	}
//	public void setCreatedDate(String createdDate) {
//		this.createdDate = createdDate;
//	}
//	public String getLastUpdatedDate() {
//		return lastUpdatedDate;
//	}
//	public void setLastUpdatedDate(String lastUpdatedDate) {
//		this.lastUpdatedDate = lastUpdatedDate;
//	}
	
}
