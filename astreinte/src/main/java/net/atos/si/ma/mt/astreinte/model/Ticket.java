package net.atos.si.ma.mt.astreinte.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
public class Ticket {
	@Id
	@GeneratedValue
	private long id;
	@Column(length = 150)
	private String libelle;

	@Column(length = 5)
	private String qc;

	public String getQc() {
		return qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	@ManyToOne
	private Statut statut;
	@ManyToOne
	private Manager cp;
	@ManyToOne
	private Manager pmo;
	@ManyToOne
	private Manager fo;
	@ManyToOne
	private Manager dir;
	@Column(length = 150)
	private String mt;

	
	@OneToMany (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Astreinte> astreintes = new HashSet<Astreinte>();

	public Set<Astreinte> getAstreintes() {
		return astreintes;
	}

	public void setAstreintes(Set<Astreinte> astreintes) {
		this.astreintes = astreintes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Manager getCp() {
		return cp;
	}

	public void setCp(Manager cp) {
		this.cp = cp;
	}

	public Manager getPmo() {
		return pmo;
	}

	public void setPmo(Manager pmo) {
		this.pmo = pmo;
	}

	public Manager getFo() {
		return fo;
	}

	public void setFo(Manager fo) {
		this.fo = fo;
	}

	public Manager getDir() {
		return dir;
	}

	public void setDir(Manager dir) {
		this.dir = dir;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

}
