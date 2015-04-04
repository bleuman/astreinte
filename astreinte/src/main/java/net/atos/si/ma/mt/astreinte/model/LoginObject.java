package net.atos.si.ma.mt.astreinte.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginObject {
	@XmlElement
	public String login;
	@XmlElement
	public String password;
}
