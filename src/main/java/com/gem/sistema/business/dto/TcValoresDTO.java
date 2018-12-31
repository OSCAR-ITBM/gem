package com.gem.sistema.business.dto;

public class TcValoresDTO {
	private Integer idRow;
	private Long idEtq;
	private String etiqueta;
	private Object valor;

	public Integer getIdRow() {
		return idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
	}

	public Long getIdEtq() {
		return idEtq;
	}

	public void setIdEtq(Long idEtq) {
		this.idEtq = idEtq;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

}
