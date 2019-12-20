package com.ibsplc.itin.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author A-4211
 * 
 */
@XmlRootElement(name = "masterInfo")
public class MasterInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CostCenterVO> costCenters;
	private List<PlaceVO> cities;
	private List<PlaceVO> indianCities;
	private List<PlaceVO> countryMasterList;
	private List<PurposeVO> purposeDetails;
	private List<CurrencyVO> currencyDetails;
	private List<HeadVO> headDetails;
	
	private String tokenDate;
	
	@XmlElement(name = "tokenDate")
	public String getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(String tokenDate) {
		this.tokenDate = tokenDate;
	}

	/**
	 * @return the costCenters
	 */
	@XmlElement(name = "costCenters")
	public List<CostCenterVO> getCostCenters() {
		if (costCenters == null) {
			costCenters = new ArrayList<CostCenterVO>();
		}
		return costCenters;
	}

	/**
	 * @param costCenters
	 *            the costCenters to set
	 */
	public void setCostCenters(List<CostCenterVO> costCenters) {
		this.costCenters = costCenters;
	}

	/**
	 * @return the places
	 */
	@XmlElement(name = "cities")
	public List<PlaceVO> getCities() {
		if (cities == null) {
			cities = new ArrayList<PlaceVO>();
		}
		return cities;
	}

	/**
	 * @param places
	 *            the places to set
	 */
	public void setCities(List<PlaceVO> cities) {
		this.cities = cities;
	}

	/**
	 * @return the indianCities
	 */
	@XmlElement(name = "indianCities")
	public List<PlaceVO> getIndianCities() {
		if (indianCities == null) {
			indianCities = new ArrayList<PlaceVO>();
		}
		return indianCities;
	}

	/**
	 * @param indianCities
	 *            the indianCities to set
	 */
	public void setIndianCities(List<PlaceVO> indianCities) {
		this.indianCities = indianCities;
	}

	/**
	 * @return the purposeDetails
	 */
	@XmlElement(name = "purposeDetails")
	public List<PurposeVO> getPurposeDetails() {
		if (purposeDetails == null) {
			purposeDetails = new ArrayList<PurposeVO>();
		}
		return purposeDetails;
	}

	/**
	 * @param purposeDetails
	 *            the purposeDetails to set
	 */
	public void setPurposeDetails(List<PurposeVO> purposeDetails) {
		this.purposeDetails = purposeDetails;
	}

	public List<PlaceVO> getCountryMasterList() {
		return countryMasterList;
	}

	public void setCountryMasterList(List<PlaceVO> countryMasterList) {
		this.countryMasterList = countryMasterList;
	}

	public List<CurrencyVO> getCurrencyDetails() {
		return currencyDetails;
	}

	public void setCurrencyDetails(List<CurrencyVO> currencyDetails) {
		this.currencyDetails = currencyDetails;
	}

	public List<HeadVO> getHeadDetails() {
		return headDetails;
	}

	public void setHeadDetails(List<HeadVO> headDetails) {
		this.headDetails = headDetails;
	}

}
