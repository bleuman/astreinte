package net.atos.si.ma.mt.astreinte.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorBean {
	private String errorMsg;
	private int errorCode;

	public ErrorBean() {
		super();
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorBean(String errorMsg, int errorCode) {
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

}
