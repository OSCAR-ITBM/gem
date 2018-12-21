package com.gem.sistema.business.dto;

import java.util.Date;

public class Pm3711DTO {

	private Integer idRow;
	private Integer semestre;
	private Integer idSector;
	private Integer titulo;
	private Integer experiencia;
	private Integer certificacion;
	private String capturo;
	private Date fechaIng;
	private Long idAnio;
	private Long idRef;

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

	public Date getFechaIng() {
		return fechaIng;
	}

	public void setFechaIng(Date fechaIng) {
		this.fechaIng = fechaIng;
	}

	public Integer getTitulo() {
		return titulo;
	}

	public void setTitulo(Integer titulo) {
		this.titulo = titulo;
	}

	public Integer getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}

	public Integer getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(Integer certificacion) {
		this.certificacion = certificacion;
	}

	public String getCapturo() {
		return capturo;
	}

	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public Long getIdAnio() {
		return idAnio;
	}

	public void setIdAnio(Long idAnio) {
		this.idAnio = idAnio;
	}

	public Long getIdRef() {
		return idRef;
	}

	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

}
