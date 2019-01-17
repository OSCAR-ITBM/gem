package com.gem.sistema.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import com.gem.sistema.business.domain.TcEtiqueta;
import com.gem.sistema.business.domain.TcTabla;
import com.gem.sistema.business.domain.TrEtiqTabla;

public class CatalogosTablasMB extends AbstractMB {

	private List<TcTabla> listTablas;
	private List<TcEtiqueta> listEtiquetas;
	private List<TrEtiqTabla> listEtiqTablas;

	@PostConstruct
	public void init() {

	}

	public List<TcTabla> getListTablas() {
		return listTablas;
	}

	public void setListTablas(List<TcTabla> listTablas) {
		this.listTablas = listTablas;
	}

	public List<TcEtiqueta> getListEtiquetas() {
		return listEtiquetas;
	}

	public void setListEtiquetas(List<TcEtiqueta> listEtiquetas) {
		this.listEtiquetas = listEtiquetas;
	}

	public List<TrEtiqTabla> getListEtiqTablas() {
		return listEtiqTablas;
	}

	public void setListEtiqTablas(List<TrEtiqTabla> listEtiqTablas) {
		this.listEtiqTablas = listEtiqTablas;
	}

}
