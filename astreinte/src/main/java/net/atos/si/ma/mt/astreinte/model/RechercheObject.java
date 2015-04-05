package net.atos.si.ma.mt.astreinte.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RechercheObject {
	@XmlElement
	public String query;

}
