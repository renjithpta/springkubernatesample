package com.ibsplc.itin.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="entry")
public class EntryVO {

	private String masterCode;
	
	 private String masterValue;

     private String masterDesc;

     private String BOType;

     private String isBOOrProj;

     private String approverFlow;

     private String crtdBy;

     @XmlElement(name="value")
	public String getMasterValue() {
		return masterValue;
	}

	public void setMasterValue(String masterValue) {
		this.masterValue = masterValue;
	}

	@XmlElement(name="desc")
	public String getMasterDesc() {
		return masterDesc;
	}

	public void setMasterDesc(String masterDesc) {
		this.masterDesc = masterDesc;
	}

	@XmlElement(name="BOtype")
	public String getBOType() {
		return BOType;
	}

	public void setBOType(String bOType) {
		BOType = bOType;
	}

	@XmlElement(name="BOPrjt")
	public String getIsBOOrProj() {
		return isBOOrProj;
	}

	public void setIsBOOrProj(String isBOOrProj) {
		this.isBOOrProj = isBOOrProj;
	}

	@XmlElement(name="approver")
	public String getApproverFlow() {
		return approverFlow;
	}

	public void setApproverFlow(String approverFlow) {
		this.approverFlow = approverFlow;
	}
	
	@XmlElement(name="crtdby")

	public String getCrtdBy() {
		return crtdBy;
	}

	public void setCrtdBy(String crtdBy) {
		this.crtdBy = crtdBy;
	}

	@XmlElement(name="mastercode")
	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}


	
	
	
}
