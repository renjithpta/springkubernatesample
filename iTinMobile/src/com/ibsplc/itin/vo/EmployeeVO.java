package com.ibsplc.itin.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author A-4211
 * 
 */
@XmlRootElement(name = "employee")
public class EmployeeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String empCode;
	private String empName;
	private String userID;

	private String empStatus;

	private String costCenter;
	private int costCenterCode;
	private String lobDptUnit;
	private String project;

	private String approverCode;
	private String designation;
	private String grade;
	private String baseStation;
	private String baseStationReg;
	private String officeEmail;
	private String mobileNumber;

	/**
	 * @return the empCode
	 */
	@XmlElement(name = "empCode")
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @param empCode
	 *            the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return the empName
	 */
	@XmlElement(name = "empName")
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the userID
	 */
	@XmlElement(name = "userID")
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the empStatus
	 */
	@XmlElement(name = "empStatus")
	public String getEmpStatus() {
		return empStatus;
	}

	/**
	 * @param empStatus
	 *            the empStatus to set
	 */
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
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

	public int getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(int costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	/**
	 * @return the lobDptUnit
	 */
	@XmlElement(name = "lobDepartmentUnit")
	public String getLobDptUnit() {
		return lobDptUnit;
	}

	/**
	 * @param lobDptUnit
	 *            the lobDptUnit to set
	 */
	public void setLobDptUnit(String lobDptUnit) {
		this.lobDptUnit = lobDptUnit;
	}

	/**
	 * @return the project
	 */
	@XmlElement(name = "project")
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the approverCode
	 */
	@XmlElement(name = "approverCode")
	public String getApproverCode() {
		return approverCode;
	}

	/**
	 * @param approverCode
	 *            the approverCode to set
	 */
	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}

	/**
	 * @return the designation
	 */
	@XmlElement(name = "destination")
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the baseStation
	 */
	@XmlElement(name = "baseStation")
	public String getBaseStation() {
		return baseStation;
	}

	/**
	 * @param baseStation
	 *            the baseStation to set
	 */
	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation;
	}

	/**
	 * @return the baseStationReg
	 */
	@XmlElement(name = "baseStationRegion")
	public String getBaseStationReg() {
		return baseStationReg;
	}

	/**
	 * @param baseStationReg
	 *            the baseStationReg to set
	 */
	public void setBaseStationReg(String baseStationReg) {
		this.baseStationReg = baseStationReg;
	}

	/**
	 * @return the officeEmail
	 */
	@XmlElement(name = "officeEmail")
	public String getOfficeEmail() {
		return officeEmail;
	}

	/**
	 * @param officeEmail
	 *            the officeEmail to set
	 */
	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	/**
	 * @return the mobileNumber
	 */
	@XmlElement(name = "mobileNumber")
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
