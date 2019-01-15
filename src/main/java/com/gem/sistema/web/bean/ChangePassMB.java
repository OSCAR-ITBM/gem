package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.service.catalogos.ChangePasswordService;

@ManagedBean(name = "changePassMB")
@ViewScoped
public class ChangePassMB extends AbstractMB {

	private String labelPass = "Password";
	private String password;
	private String newPassword;
	private String confirmPassword;
	private Integer tipoPass = 1;

	private boolean bRender = false;

	@ManagedProperty("#{changePasswordService}")
	private ChangePasswordService changePasswordService;

	@PostConstruct
	public void init() {

	}

	public void save() {
		if (StringUtils.isNotBlank(password)) {
			boolean bandera = this.changePasswordService.validatePassword(password);
			if (tipoPass == 1) {

				if (!bandera) {

					if (password.equals(confirmPassword)) {
						this.changePasswordService.save(password);
						generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
								"El password se guardo correctamente");
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
								"El password no coinside con la confirmación");
					}
				} else {
					generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!", "El password ya existe");
				}
			} else if (tipoPass == 2) {
				if (StringUtils.isNotBlank(newPassword) || StringUtils.isNotBlank(confirmPassword)) {
					if (bandera) {
						if (newPassword.equals(confirmPassword)) {
							this.changePasswordService.save(newPassword);
							generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
									"El password se cambio correctamente");
						} else {
							generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
									"El password no coinside con la confirmación");
						}
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
								"El password no existe en la base");
					}
				} else {
					generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!",
							"El nuevo password y la confirmacion son obligatorios");
				}
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Info!", "El password es obligatorio");
		}
	}

	public void changePass() {
		if (tipoPass == 1) {
			labelPass = "Password";

			bRender = false;
		} else if (tipoPass == 2) {
			labelPass = "Password Anterior";
			bRender = true;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public ChangePasswordService getChangePasswordService() {
		return changePasswordService;
	}

	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	public String getLabelPass() {
		return labelPass;
	}

	public void setLabelPass(String labelPass) {
		this.labelPass = labelPass;
	}

	public boolean isbRender() {
		return bRender;
	}

	public void setbRender(boolean bRender) {
		this.bRender = bRender;
	}

	public Integer getTipoPass() {
		return tipoPass;
	}

	public void setTipoPass(Integer tipoPass) {
		this.tipoPass = tipoPass;
	}

}
