package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_VALORES database table.
 * 
 */
@Entity
@Table(name = "TC_VALORES")
@NamedQuery(name = "TcValores.findAll", query = "SELECT t FROM TcValores t")
public class TcValores extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "ID_ETIQ_TABLA")
	@ManyToOne
	private TrEtiqTabla trEtiqTabla;

	private String valor;

	public TcValores() {
	}

	public TrEtiqTabla getTrEtiqTabla() {
		return trEtiqTabla;
	}

	public void setTrEtiqTabla(TrEtiqTabla trEtiqTabla) {
		this.trEtiqTabla = trEtiqTabla;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}