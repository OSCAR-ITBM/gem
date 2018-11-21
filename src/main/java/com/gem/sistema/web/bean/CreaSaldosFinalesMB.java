package com.gem.sistema.web.bean;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Maycta;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.MayctaRepository;
import com.gem.sistema.business.service.catalogos.AccountService;

// TODO: Auto-generated Javadoc
/**
 * The Class CreaSaldosFinalesMB.
 */
@ManagedBean(name = "creaSaldosFinalesMB")
@ViewScoped
public class CreaSaldosFinalesMB extends AbstractMB {
	
	/** The Constant downloadURL. */
	private static final String downloadURL = "/views/utilerias/creaSaldosFinalesDownload.xhtml";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CreaSaldosFinalesMB.class);
	
	/** The Constant _INNER_PASS. */
	private static final String _INNER_PASS = "ycreasal";

	/** The account service. */
	@ManagedProperty(value = "#{accountService}")
	private AccountService accountService;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The maycta repository. */
	@ManagedProperty("#{mayctaRepository}")
	private MayctaRepository mayctaRepository;

	/** The password. */
	private String password;
	
	/** The download. */
	private StreamedContent download;

	/**
	 * Sets the account service.
	 *
	 * @param accountService the new account service
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Gets the account service.
	 *
	 * @return the account service
	 */
	public AccountService getAccountService() {
		return accountService;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the download.
	 *
	 * @return the download
	 */
	public StreamedContent getDownload() {
		return download;
	}

	/**
	 * Sets the download.
	 *
	 * @param download the new download
	 */
	public void setDownload(StreamedContent download) {
		this.download = download;
	}

	/**
	 * Validate password.
	 *
	 * @return the string
	 */
	public String validatePassword() {
		if (StringUtils.isBlank(password)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"El campo password esta vació.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} else if (!_INNER_PASS.equals(password)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El password es incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} else {
			return downloadURL;
		}
	}

	/**
	 * Prepare download.
	 */
	public void prepareDownload() {
		Conctb conctb = getConctbRepository().findFirstByOrderByIdAsc();
		StringWriter writer = new StringWriter();
		processAccounts(writer);
		try {
			writer.close();
			setDownload(new DefaultStreamedContent(IOUtils.toInputStream(writer.toString(), "UTF-8"), "text/plain",
					"sdos" + conctb.getAnoemp() + ".txt"));
		} catch (IOException e) {
			LOGGER.error("Error Descargando Archivo {}", e);
			e.printStackTrace();
		}
		// System.out.println("Testing call method");
		// List <Cuenta> cuentas = accountService.findAccountsToBackup();
		// System.out.println("cuentas>"+cuentas.get(0));
		// System.out.println("maycta cuentas>"+cuentas.get(0).getMaycta());
	}

	/**
	 * Process accounts.
	 *
	 * @param writer the writer
	 */
	private void processAccounts(StringWriter writer) {
		DecimalFormat df = new DecimalFormat("#0.##");
		for (Cuenta cuenta : accountService
				.findAccountsToBackup(((Integer) getUserDetails().getIdSector()).longValue())) {
			/*
			 * revisar si efectivamente la relacion entre Cuenta y Maycta es uno
			 * a uno
			 */
			List<Maycta> findByCuenta = mayctaRepository.findByCuenta(cuenta.getCuenta());
			String natcta = ((Maycta) findByCuenta.get(0)).getNatcta();

			BigDecimal sf = processAccount(
					natcta /* cuenta.getMaycta().getNatcta() */, cuenta.getAbonosAsList(), cuenta.getCargosAsList(),
					cuenta.getSalini().doubleValue());

			if (sf != null && BigDecimal.ZERO.compareTo(sf) != 0 && !(cuenta.getCuenta().startsWith("4") || cuenta.getCuenta().startsWith("8"))) {
				System.out.println("WRITING");
				writer.write(new StringBuffer().append(cuenta.getCuenta()).append(",").append(cuenta.getScuenta())
						.append(",").append(cuenta.getSscuenta()).append(",").append(cuenta.getSsscuenta())
						.append(",").append(cuenta.getSssscuenta()).append(",").append(df.format(sf)).append("\r\n")
						.toString());
			}
		}
	}

  /**
   * Adds the quotes.
   *
   * @param str the str
   * @return the string
   */
  private String addQuotes(String str){
    if(StringUtils.isBlank(str)) return str;
    return new StringBuffer("'")
               .append(str)
               .append("'")
               .toString();
  }

	/**
	 * Process account.
	 *
	 * @param accountType the account type
	 * @param abonos the abonos
	 * @param cargos the cargos
	 * @param salini the salini
	 * @return the big decimal
	 */
	private BigDecimal processAccount(String accountType, List<BigDecimal> abonos, List<BigDecimal> cargos,
			Double salini) {
		BigDecimal sf = new BigDecimal(0.0);
		if ("D".equalsIgnoreCase(accountType)) {
			for (int i = 0; i < 12; i++) {
				sf = sf.add(cargos.get(i).subtract(abonos.get(i)));
			}
		} else {
			for (int i = 0; i < 12; i++) {
				sf = sf.add(abonos.get(i).subtract(cargos.get(i)));
			}
		}

		return (sf = sf.add(new BigDecimal(salini))).equals(new BigDecimal(0)) ? null : sf;
	}

	/**
	 * Gets the maycta repository.
	 *
	 * @return the mayctaRepository
	 */
	public MayctaRepository getMayctaRepository() {
		return mayctaRepository;
	}

	/**
	 * Sets the maycta repository.
	 *
	 * @param mayctaRepository            the mayctaRepository to set
	 */
	public void setMayctaRepository(MayctaRepository mayctaRepository) {
		this.mayctaRepository = mayctaRepository;
	}

}
