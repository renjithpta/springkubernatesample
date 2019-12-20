package com.ibsplc.itin.pns;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="content")
public class Content {

	//private Type type;

	private String description;
	private String data;
	private String empID;
	
	
	
	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement(name="data")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	@XmlElement(name="empID")
	public String getEmpID() {
		return empID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Content [description=" + description + ", data=" + data
				+ ", empID=" + empID + "]";
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
//	@Override
//	public String toString() {
//		return "ContentVO [type=" + type + ", description=" + description
//				+ ", data=" + data + "]";
//	}






/*	public enum Type {
		LINK, DATA, CALENDAR;
	}

	*/
	
}
