package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TC_ETIQUETAS database table.
 * 
 */
@Entity
@Table(name = "TC_ETIQUETAS")
@NamedQuery(name = "TcEtiqueta.findAll", query = "SELECT t FROM TcEtiqueta t")
public class TcEtiqueta extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;

	@Column(name = "\"STATUS\"")
	private Integer status;

	// bi-directional many-to-one association to TrEtiqTabla
	@OneToMany(mappedBy = "tcEtiqueta")
	private List<TrEtiqTabla> trEtiqTablas;

	public TcEtiqueta() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<TrEtiqTabla> getTrEtiqTablas() {
		return this.trEtiqTablas;
	}

	public void setTrEtiqTablas(List<TrEtiqTabla> trEtiqTablas) {
		this.trEtiqTablas = trEtiqTablas;
	}

	public TrEtiqTabla addTrEtiqTabla(TrEtiqTabla trEtiqTabla) {
		getTrEtiqTablas().add(trEtiqTabla);
		trEtiqTabla.setTcEtiqueta(this);

		return trEtiqTabla;
	}

	public TrEtiqTabla removeTrEtiqTabla(TrEtiqTabla trEtiqTabla) {
		getTrEtiqTablas().remove(trEtiqTabla);
		trEtiqTabla.setTcEtiqueta(null);

		return trEtiqTabla;
	}

}