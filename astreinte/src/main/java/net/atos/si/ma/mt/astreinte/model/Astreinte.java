package net.atos.si.ma.mt.astreinte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

@Entity
public class Astreinte {

	@Id
	@GeneratedValue
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateAstreinte")
	private Date dateAstreinte;

	@Temporal(TemporalType.TIME)
	@Column(name = "hdebut")
	private Date hdebut;

	@Temporal(TemporalType.TIME)
	@Column(name = "hfin")
	private Date hfin;

	@ManyToOne
	private StatutAstreinte statutAstreinte;

	@ManyToOne
	private Ressource ressource;

	// @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JsonBackReference
	// private Ticket ticket;

	@ManyToOne
	private TypeAstreinte typeAstreinte;

	@Transient
	private Float k = Float.valueOf(-1f);

	@Transient
	private Float charge;

	@Transient
	private long idQC;

	public long getIdQC() {
		return idQC;
	}

	public void setIdQC(long idQC) {
		this.idQC = idQC;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateAstreinte() {
		return dateAstreinte;
	}

	public void setDateAstreinte(Date dateAstreinte) {
		this.dateAstreinte = dateAstreinte;
	}

	public Date getHdebut() {
		return hdebut;
	}

	@JsonDeserialize(using = TimeDeserializer.class)
	public void setHdebut(Date hdebut) {
		this.hdebut = hdebut;
	}

	public Date getHfin() {
		return hfin;
	}

	@JsonDeserialize(using = TimeDeserializer.class)
	public void setHfin(Date hfin) {
		this.hfin = hfin;
	}

	public StatutAstreinte getStatutAstreinte() {
		return statutAstreinte;
	}

	public void setStatutAstreinte(StatutAstreinte statutAstreinte) {
		this.statutAstreinte = statutAstreinte;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public TypeAstreinte getTypeAstreinte() {
		return typeAstreinte;
	}

	public void setTypeAstreinte(TypeAstreinte typeAstreinte) {
		this.typeAstreinte = typeAstreinte;
	}

	public Float getK() {
		return k;
	}

	public void setK(Float k) {
		this.k = k;
	}

	public Float getCharge() {
		return charge;
	}

	public void setCharge(Float charge) {
		this.charge = charge;
	}

}
