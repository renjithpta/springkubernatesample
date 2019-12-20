package com.ibsplc.itin.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Head")
public class HeadVO {

	
	private String mstKey;
	private String mstCode;
	private String mstValue;
	private String mstDsc;
	//private String createdDate;
	//private String lastUpdatedDate;
	public String getMstKey() {
		return mstKey;
	}
	public void setMstKey(String mstKey) {
		this.mstKey = mstKey;
	}
	public String getMstCode() {
		return mstCode;
	}
	public void setMstCode(String mstCode) {
		this.mstCode = mstCode;
	}
	public String getMstValue() {
		return mstValue;
	}
	public void setMstValue(String mstValue) {
		this.mstValue = mstValue;
	}
	public String getMstDsc() {
		return mstDsc;
	}
	public void setMstDsc(String mstDsc) {
		this.mstDsc = mstDsc;
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
