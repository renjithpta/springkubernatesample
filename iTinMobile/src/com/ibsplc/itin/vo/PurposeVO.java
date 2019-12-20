package com.ibsplc.itin.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "purposeDetail")
public class PurposeVO {

	private String purposeCode;
	private String purposeDetail;
//	private String createdDate;
//	private String lastUpdatedDate;
	
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

	/**
	 * @return the purposeCode
	 */
	@XmlElement(name = "purposeCode")
	public String getPurposeCode() {
		return purposeCode;
	}

	/**
	 * @param purposeCode
	 *            the purposeCode to set
	 */
	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	/**
	 * @return the purposeDetail
	 */
	@XmlElement(name = "purposeDetail")
	public String getPurposeDetail() {
		return purposeDetail;
	}

	/**
	 * @param purposeDetail
	 *            the purposeDetail to set
	 */
	public void setPurposeDetail(String purposeDetail) {
		this.purposeDetail = purposeDetail;
	}

}
