package com.ibsplc.itin.pns;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

/**
 * 
 * @author A-4211
 * 
 */
@XmlRootElement(name = "deviceInfo")
public class DeviceInfo {

	private String deviceid;
	private String devicetype;
	private String deviceuid;
	private String empid;

	public String getDeviceid() {
		return deviceid;
	}

	@XmlElement("deviceid")
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	@XmlElement("devicetype")
	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	@XmlElement("deviceuid")
	public String getDeviceuid() {
		return deviceuid;
	}

	public void setDeviceuid(String deviceuid) {
		this.deviceuid = deviceuid;
	}

	@XmlElement("empid")
	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

}
