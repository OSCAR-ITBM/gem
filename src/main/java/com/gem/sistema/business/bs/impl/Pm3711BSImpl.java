package com.gem.sistema.business.bs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.Pm3711BS;
import com.gem.sistema.business.dao.Pm3711DAO;
import com.gem.sistema.business.dto.Pm3711DTO;

@Repository(value = "pm3711BS")
public class Pm3711BSImpl implements Pm3711BS {

	@Autowired
	private Pm3711DAO pm3711dao;

	@Override
	public List<Pm3711DTO> findAll() {
		return this.pm3711dao.findAll();
	}

	@Override
	public void deletePm3711(Integer idRow) {
		this.pm3711dao.deletePm3711(idRow);
	}

	public Pm3711DAO getPm3711dao() {
		return pm3711dao;
	}

	public void setPm3711dao(Pm3711DAO pm3711dao) {
		this.pm3711dao = pm3711dao;
	}

}
