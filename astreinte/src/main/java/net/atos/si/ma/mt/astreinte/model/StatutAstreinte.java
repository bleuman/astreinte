package net.atos.si.ma.mt.astreinte.model;

import javax.persistence.Entity;

@Entity
public class StatutAstreinte extends Param {
	public static final long ONGOING = 1;
	public static final long VALID = 2;
	public static final long REJECTED = 3;

}
