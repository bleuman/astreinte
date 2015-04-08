package net.atos.si.ma.mt.astreinte.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginData {
	
	@XmlElement
	public boolean statut;
	@XmlElement
	public String token;
	@XmlElement
	public String username;
	@XmlElement
	public String motif;
	@XmlElement
	public long id;
	@XmlElement
	public String role;

}
