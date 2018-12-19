package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TR_PUESTO_FIRMA database table.
 * 
 */
@Entity
@Table(name = "TR_PUESTO_FIRMA")
@NamedQuery(name = "TrPuestoFirma.findAll", query = "SELECT t FROM TrPuestoFirma t")
public class TrPuestoFirma extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ANIO")
	private Long idAnio;

	@ManyToOne
	@JoinColumn(name = "ID_PUESTOS")
	private TcPuesto tcPuesto;

	@Column(name = "ID_REF")
	private Long idRef;

	private String nombre;

	public TrPuestoFirma() {
	}

	public TcPuesto getTcPuesto() {
		return tcPuesto;
	}

	public void setTcPuesto(TcPuesto tcPuesto) {
		this.tcPuesto = tcPuesto;
	}

	public Long getIdAnio() {
		return this.idAnio;
	}

	public void setIdAnio(Long idAnio) {
		this.idAnio = idAnio;
	}

	public Long getIdRef() {
		return this.idRef;
	}

	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}