package com.ibsplc.itin.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author A-4211
 *
 */
@XmlRootElement(name = "BusinessOpportunity")
public class BusinessOpportunityVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// BO project code and BO project name ..... common for below specified
	// fields
//	private Map<String, String> boNonCommercial;
//	private Map<String, String> boCommercial;
//	private Map<String, String> projects;
//	private Map<String, String> others;
//	private Map<String, String> leads;
	
	private String masterCode;
	private String masterValue;
	private String masterDesc;
	private String BOType;
	private String isBOOrProj;
	private String approverFlow;
	private String crtdBy;
//	private String createdDate;
//	private String lastUpdatedDate;

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getMasterValue() {
		return masterValue;
	}

	public void setMasterValue(String masterValue) {
		this.masterValue = masterValue;
	}

	public String getMasterDesc() {
		return masterDesc;
	}

	public void setMasterDesc(String masterDesc) {
		this.masterDesc = masterDesc;
	}

	public String getBOType() {
		return BOType;
	}

	public void setBOType(String type) {
		BOType = type;
	}

	public String getIsBOOrProj() {
		return isBOOrProj;
	}

	public void setIsBOOrProj(String isBOOrProj) {
		this.isBOOrProj = isBOOrProj;
	}

	public String getApproverFlow() {
		return approverFlow;
	}

	public void setApproverFlow(String approverFlow) {
		this.approverFlow = approverFlow;
	}

	public String getCrtdBy() {
		return crtdBy;
	}

	public void setCrtdBy(String crtdBy) {
		this.crtdBy = crtdBy;
	}

	public BusinessOpportunityVO() {
//		boNonCommercial = new HashMap<String, String>();
//		boCommercial = new HashMap<String, String>();
//		projects = new HashMap<String, String>();
//		others = new HashMap<String, String>();
//		leads = new HashMap<String, String>();
	}

	/**
	 * @return the boNonCommerical
	 */
//	@XmlElement(name = "boNonCommercial")
//	public Map<String, String> getBoNonCommercial() {
//		return boNonCommercial;
//	}
//
//	/**
//	 * @param boNonCommerical
//	 *            the boNonCommerical to set
//	 */
//	public void setBoNonCommerical(Map<String, String> boNonCommerical) {
//		this.boNonCommercial = boNonCommerical;
//	}
//
//	/**
//	 * @return the boCommerical
//	 */
//	@XmlElement(name = "boCommercial")
//	public Map<String, String> getBoCommerical() {
//		return boCommercial;
//	}
//
//	/**
//	 * @param boCommerical
//	 *            the boCommerical to set
//	 */
//	public void setBoCommerical(Map<String, String> boCommerical) {
//		this.boCommercial = boCommerical;
//	}
//
//	/**
//	 * @return the projects
//	 */
//	@XmlElement(name = "projects")
//	public Map<String, String> getProjects() {
//		return projects;
//	}
//
//	/**
//	 * @param projects
//	 *            the projects to set
//	 */
//	public void setProjects(Map<String, String> projects) {
//		this.projects = projects;
//	}
//
//	/**
//	 * @return the others
//	 */
//	@XmlElement(name = "others")
//	public Map<String, String> getOthers() {
//		return others;
//	}
//
//	/**
//	 * @param others
//	 *            the others to set
//	 */
//	public void setOthers(Map<String, String> others) {
//		this.others = others;
//	}
//
//	/**
//	 * @return the leads
//	 */
//	@XmlElement(name = "leads")
//	public Map<String, String> getLeads() {
//		return leads;
//	}
//
//	/**
//	 * @param leads
//	 *            the leads to set
//	 */
//	public void setLeads(Map<String, String> leads) {
//		this.leads = leads;
//	}
//
//	public Map<String, String> getBoCommercial() {
//		return boCommercial;
//	}
//
//	public void setBoCommercial(Map<String, String> boCommercial) {
//		this.boCommercial = boCommercial;
//	}

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

//	public void setLastUpdatedDate(String lastUpdatedDate) {
//		this.lastUpdatedDate = lastUpdatedDate;
//	}
//
//	public void setBoNonCommercial(Map<String, String> boNonCommercial) {
//		this.boNonCommercial = boNonCommercial;
//	}

}
