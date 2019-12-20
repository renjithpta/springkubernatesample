package com.ibsplc.itin.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author A-4211
 *
 */
@XmlRootElement(name = "costDetail")
public class CostDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private float value;

	/**
	 * @return the key
	 */
	@XmlElement(name = "key")
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	@XmlElement(name = "value")
	public float getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(float value) {
		this.value = value;
	}

}
