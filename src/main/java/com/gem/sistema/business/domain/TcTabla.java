package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TC_TABLAS database table.
 * 
 */
@Entity
@Table(name = "TC_TABLAS")
@NamedQuery(name = "TcTabla.findAll", query = "SELECT t FROM TcTabla t")
public class TcTabla extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String descripcion;

	// bi-directional many-to-one association to TrEtiqTabla
	@OneToMany(mappedBy = "tcTabla")
	private List<TrEtiqTabla> trEtiqTablas;

	public TcTabla() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<TrEtiqTabla> getTrEtiqTablas() {
		return this.trEtiqTablas;
	}

	public void setTrEtiqTablas(List<TrEtiqTabla> trEtiqTablas) {
		this.trEtiqTablas = trEtiqTablas;
	}

	public TrEtiqTabla addTrEtiqTabla(TrEtiqTabla trEtiqTabla) {
		getTrEtiqTablas().add(trEtiqTabla);
		trEtiqTabla.setTcTabla(this);

		return trEtiqTabla;
	}

	public TrEtiqTabla removeTrEtiqTabla(TrEtiqTabla trEtiqTabla) {
		getTrEtiqTablas().remove(trEtiqTabla);
		trEtiqTabla.setTcTabla(null);

		return trEtiqTabla;
	}

}