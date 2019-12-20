package com.ibsplc.itin.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author A-4211
 *
 */
@XmlRootElement(name = "approver")
public class ApproverVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> requestIDs;
	private List<String> dtavoList;
	

	// Approver ID
	private String userID;
	// Approver Employee code
	private String empCode;   //approver code
	private String dtaNo;       
	private String approverRemarks;
	private String nextAction;
	private String nextApprover;
	private String dtaActionBy;
	private String approveBy;
	private String curntOpndEmpCode;
	private String updatedUser;
	
	// Added for iTin Mail Bot on September 25, 2019
	private String apprIdentifier;
	private String staticCode;
	
	@XmlElement(name = "updatedUser")
	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	@XmlElement(name = "approveBy")
	public String getApproveBy() {
		return approveBy;
	}

	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}

	@XmlElement(name = "curntOpndEmpCode")
	public String getCurntOpndEmpCode() {
		return curntOpndEmpCode;
	}

	public void setCurntOpndEmpCode(String curntOpndEmpCode) {
		this.curntOpndEmpCode = curntOpndEmpCode;
	}
	@XmlElement(name = "nextAction")
	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	@XmlElement(name = "nextApprover")
	public String getNextApprover() {
		return nextApprover;
	}

	public void setNextApprover(String nextApprover) {
		this.nextApprover = nextApprover;
	}

	@XmlElement(name = "dtaActionBy")
	public String getDtaActionBy() {
		return dtaActionBy;
	}

	public void setDtaActionBy(String dtaActionBy) {
		this.dtaActionBy = dtaActionBy;
	}

	/**
	 * @return the requestIDs
	 */
	@XmlElement(name = "requestIDs")
	public List<String> getRequestIDs() {
		return requestIDs;
	}

	/**
	 * @param requestIDs
	 *            the requestIDs to set
	 */
	public void setRequestIDs(List<String> requestIDs) {
		this.requestIDs = requestIDs;
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

	@XmlElement(name = "approverRemarks")
	public String getApproverRemarks() {
		return approverRemarks;
	}

	public void setApproverRemarks(String approverRemarks) {
		this.approverRemarks = approverRemarks;
	}

	@XmlElement(name = "dtaNo")
	public String getDtaNo() {
		return dtaNo;
	}

	public void setDtaNo(String dtaNo) {
		this.dtaNo = dtaNo;
	}

	@XmlElement(name = "dtavoList")
	public List<String> getDtavoList() {
		return dtavoList;
	}

	public void setDtavoList(List<String> dtavoList) {
		this.dtavoList = dtavoList;
	}

	public String getApprIdentifier() {
		return apprIdentifier;
	}

	public void setApprIdentifier(String apprIdentifier) {
		this.apprIdentifier = apprIdentifier;
	}

	public String getStaticCode() {
		return staticCode;
	}

	public void setStaticCode(String staticCode) {
		this.staticCode = staticCode;
	}
}
