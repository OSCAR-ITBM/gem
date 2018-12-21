package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TR_ETIQ_TABLAS database table.
 * 
 */
@Entity
@Table(name = "TR_ETIQ_TABLAS")
@NamedQuery(name = "TrEtiqTabla.findAll", query = "SELECT t FROM TrEtiqTabla t")
public class TrEtiqTabla extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "ID_ETIQUETA")
	@ManyToOne
	private TcEtiqueta tcEtiqueta;

	@JoinColumn(name = "ID_TABLA")
	@ManyToOne
	private TcTabla tcTabla;

	// bi-directional many-to-one association to TcValore
	@OneToMany(mappedBy = "trEtiqTabla")
	private List<TcValores> tcValores;

	public TrEtiqTabla() {
	}

	public TcEtiqueta getTcEtiqueta() {
		return tcEtiqueta;
	}

	public void setTcEtiqueta(TcEtiqueta tcEtiqueta) {
		this.tcEtiqueta = tcEtiqueta;
	}

	public TcTabla getTcTabla() {
		return tcTabla;
	}

	public void setTcTabla(TcTabla tcTabla) {
		this.tcTabla = tcTabla;
	}

	public List<TcValores> getTcValores() {
		return this.tcValores;
	}

	public void setTcValores(List<TcValores> tcValores) {
		this.tcValores = tcValores;
	}

	public TcValores addTcValores(TcValores tcValores) {
		getTcValores().add(tcValores);
		tcValores.setTrEtiqTabla(this);

		return tcValores;
	}

	public TcValores removeTcValore(TcValores tcValores) {
		getTcValores().remove(tcValores);
		tcValores.setTrEtiqTabla(null);

		return tcValores;
	}

}