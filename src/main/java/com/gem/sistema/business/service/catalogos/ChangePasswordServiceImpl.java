package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.ChangePasswordDAO;

@Service(value = "changePasswordService")
public class ChangePasswordServiceImpl implements ChangePasswordService {

	@Autowired
	private ChangePasswordDAO changePasswordDAO;

	@Override
	public void save(String password) {
		this.changePasswordDAO.save(password);

	}

	@Override
	public void changePassword(String password) {
		this.changePasswordDAO.changePassword(password);

	}

	@Override
	public boolean validatePassword(String password) {
		
		return this.changePasswordDAO.validatePassword(password);
	}

	public ChangePasswordDAO getChangePasswordDAO() {
		return changePasswordDAO;
	}

	public void setChangePasswordDAO(ChangePasswordDAO changePasswordDAO) {
		this.changePasswordDAO = changePasswordDAO;
	}

}
