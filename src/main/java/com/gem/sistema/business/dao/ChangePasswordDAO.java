package com.gem.sistema.business.dao;

public interface ChangePasswordDAO {
	void save(String password);

	void changePassword(String password);

	boolean validatePassword(String password);

}
