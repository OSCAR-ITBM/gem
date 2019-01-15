package com.gem.sistema.business.service.catalogos;

public interface ChangePasswordService {

	void save(String password);

	void changePassword(String password);

	boolean validatePassword(String password);

}
