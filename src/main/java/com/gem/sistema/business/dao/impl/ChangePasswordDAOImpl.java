package com.gem.sistema.business.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.ChangePasswordDAO;
import com.gem.sistema.business.domain.TcParametro;
import com.gem.sistema.business.predicates.ParametrosPredicate;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;

@Repository
public class ChangePasswordDAOImpl implements ChangePasswordDAO {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ParametrosRepository parametrosRepository;

	@Override
	public void save(String password) {
		String passEcript = passwordEncoder.encode(password);
		TcParametro tcParametro = new TcParametro();
		tcParametro.setCveParametro("PASS_AFECTA");
		tcParametro.setDescripcion("PASSWORD AFECTACION");
		tcParametro.setValor(passEcript);
		tcParametro.setDataType("PASSWORD");
		this.parametrosRepository.save(tcParametro);

	}

	@Override
	public void changePassword(String password) {
		TcParametro tcParametro = this.parametrosRepository
				.findOne(ParametrosPredicate.findByDataType("PASS_AFECTA", "PASSWORD"));
		String passEcript = passwordEncoder.encode(password);
		tcParametro.setValor(passEcript);
		this.parametrosRepository.save(tcParametro);

	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	String encripPass;

	@Override
	public boolean validatePassword(String password) {

		TcParametro tcParametro = this.parametrosRepository
				.findOne(ParametrosPredicate.findByDataType("PASS_AFECTA", "PASSWORD"));
		boolean bandera = this.passwordEncoder.matches(password, tcParametro.getValor());
		if (tcParametro != null)
			if (bandera) {
				return true;
			}
		return false;
	}

}
