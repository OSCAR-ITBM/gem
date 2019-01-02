package com.gem.sistema.business.dto;

import org.apache.commons.lang3.StringUtils;

import com.ibm.icu.math.BigDecimal;

public class Pm5911DTO {

	private Integer idRow;
	private Integer semestre = 0;
	private String fechaIng = StringUtils.EMPTY;
	private String titulo = BigDecimal.ZERO.toString();
	private String experencia = BigDecimal.ZERO.toString();
	private String certificacion = BigDecimal.ZERO.toString();
	private String capturo = BigDecimal.ZERO.toString();
	private Integer idAnio;
	private Integer idRef;
	private Integer idSector = BigDecimal.ONE.intValue();

	public Pm5911DTO() {
	}

	@Override
	public String toString() {
		return "Pm5911DTO [idRow=" + idRow + ", semestre=" + semestre + ", fechaIng=" + fechaIng + ", titulo=" + titulo
				+ ", experencia=" + experencia + ", certificacion=" + certificacion + ", capturo=" + capturo
				+ ", idAnio=" + idAnio + ", idRef=" + idRef + ", idSector=" + idSector + "]";
	}

	public Pm5911DTO(Integer idRow, Integer semestre, String fechaIng, String titulo, String experencia,
			String certificacion, String capturo, Integer idAnio, Integer idRef, Integer idSector) {
		super();
		this.idRow = idRow;
		this.semestre = semestre;
		this.fechaIng = fechaIng;
		this.titulo = titulo;
		this.experencia = experencia;
		this.certificacion = certificacion;
		this.capturo = capturo;
		this.idAnio = idAnio;
		this.idRef = idRef;
		this.idSector = idSector;
	}

	public Integer getIdRow() {
		return idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public String getFechaIng() {
		return fechaIng;
	}

	public void setFechaIng(String fechaIng) {
		this.fechaIng = fechaIng;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getExperencia() {
		return experencia;
	}

	public void setExperencia(String experencia) {
		this.experencia = experencia;
	}

	public String getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(String certificacion) {
		this.certificacion = certificacion;
	}

	public String getCapturo() {
		return capturo;
	}

	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	public Integer getIdAnio() {
		return idAnio;
	}

	public void setIdAnio(Integer idAnio) {
		this.idAnio = idAnio;
	}

	public Integer getIdRef() {
		return idRef;
	}

	public void setIdRef(Integer idRef) {
		this.idRef = idRef;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

}
