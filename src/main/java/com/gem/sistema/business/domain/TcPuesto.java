package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the TC_PUESTOS database table.
 * 
 */
@Entity
@Table(name = "TC_PUESTOS")
@NamedQuery(name = "TcPuesto.findAll", query = "SELECT t FROM TcPuesto t")
public class TcPuesto extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer estatus;

	@Column(name = "ID_SECTOR")
	private Integer idSector;

	private String puesto;

	// bi-directional many-to-one association to TrPuestoFirma
	@OneToMany(mappedBy = "tcPuesto")
	private List<TrPuestoFirma> trPuestoFirmas;

	public TcPuesto() {
	}

	public Integer getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Integer getIdSector() {
		return this.idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public List<TrPuestoFirma> getTrPuestoFirmas() {
		return this.trPuestoFirmas;
	}

	public void setTrPuestoFirmas(List<TrPuestoFirma> trPuestoFirmas) {
		this.trPuestoFirmas = trPuestoFirmas;
	}

	public TrPuestoFirma addTrPuestoFirma(TrPuestoFirma trPuestoFirma) {
		getTrPuestoFirmas().add(trPuestoFirma);
		trPuestoFirma.setTcPuesto(this);

		return trPuestoFirma;
	}

	public TrPuestoFirma removeTrPuestoFirma(TrPuestoFirma trPuestoFirma) {
		getTrPuestoFirmas().remove(trPuestoFirma);
		trPuestoFirma.setTcPuesto(null);

		return trPuestoFirma;
	}

}