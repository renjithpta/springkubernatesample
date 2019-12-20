package com.ibsplc.itin.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="costCenter")
public class CostCenterVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String costCenter;
	private String costCenterCode;
	private String ccValue;
	private String boProjectCode;
	private String boProject;
	private int costAllocation;
	private String erpProjCde;
	

	


	private String BOtype;// added by 4144 to show BO/type
	private String BOcommercial; // added by 4144 to show BO/type
	
	//private String createdDate;
	//private String lastUpdatedDate;

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
	 * @return the boProjectCode
	 */
	@XmlElement(name = "boProjectCode")
	public String getBoProjectCode() {
		return boProjectCode;
	}

	/**
	 * @param boProjectCode
	 * 
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
	 * @return the costAllocation
	 */
	@XmlElement(name = "costAllocation")
	public int getCostAllocation() {
		return costAllocation;
	}

	/**
	 * @param costAllocation the costAllocation to set
	 */
	public void setCostAllocation(int costAllocation) {
		this.costAllocation = costAllocation;
	}

	public String getCcValue() {
		return ccValue;
	}

	public void setCcValue(String ccValue) {
		this.ccValue = ccValue;
	}

//	public String getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(String createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public String getLastUpdatedDate() {
//		return lastUpdatedDate;
//	}
//
//	public void setLastUpdatedDate(String lastUpdatedDate) {
//		this.lastUpdatedDate = lastUpdatedDate;
//	}

	public String getErpProjCde() {
		return erpProjCde;
	}

	public void setErpProjCde(String erpProjCde) {
		this.erpProjCde = erpProjCde;
	}
	
	@XmlElement(name="BOtype")
	public String getBOtype() {
		return BOtype;
	}

	public void setBOtype(String bOtype) {
		BOtype = bOtype;
	}

	@XmlElement(name="BOcommercial")
	public String getBOcommercial() {
		return BOcommercial;
	}

	public void setBOcommercial(String bOcommercial) {
		BOcommercial = bOcommercial;
	}

}
