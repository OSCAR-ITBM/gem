package com.gem.sistema.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
// import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class CargaCuentas50008000MB.
 */
@ManagedBean(name = "cargacuenta58MB")
@ViewScoped
public class CargaCuentas50008000MB extends AbstractMB {

	/** The msg error partida gasto. */
	private static String MSG_ERROR_PARTIDA_GASTO = "ERROR EN LA PARTIDA DE GASTO, DEBE SER CAPITULO";
	
	/** The accounts 5100. */
	private static String[] ACCOUNTS_5100 = { "5100", "8211", "8271", "8251", "8241", "8221" };
	
	/** The accounts 5200. */
	private static String[] ACCOUNTS_5200 = { "5200", "8212", "8272", "8252", "8242", "8222" };
	
	/** The accounts 5300. */
	private static String[] ACCOUNTS_5300 = { "5300", "8213", "8273", "8253", "8243", "8223" };
	
	/** The accounts 5400. */
	private static String[] ACCOUNTS_5400 = { "5400", "8214", "8274", "8254", "8244", "8224" };
	
	/** The accounts 5600. */
	private static String[] ACCOUNTS_5600 = { "5600", "8215", "8275", "8255", "8245", "8225" };
	
	/** The accounts 5700. */
	private static String[] ACCOUNTS_5700 = { "5700", "8216", "8276", "8256", "8246", "8226" };
	
	/** The accounts 8217. */
	private static String[] ACCOUNTS_8217 = { "8217", "8277", "8257", "8247", "8227" };

	/** The cta. */
	private String cta;
	
	/** The scta. */
	private String scta;
	
	/** The sscta. */
	private String sscta;
	
	/** The ssscta. */
	private String ssscta;
	
	/** The msg. */
	private String msg;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The xcatpro repository. */
	@ManagedProperty("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/** The natgas repository. */
	@ManagedProperty(value = "#{natgasRepository}")
	private NatgasRepository natgasRepository;

	/** The account service. */
	@ManagedProperty("#{accountService}")
	private AccountService accountService;

	/**
	 * Sets the cta.
	 *
	 * @param cta the new cta
	 */
	public void setCta(String cta) {
		this.cta = cta;
	}

	/**
	 * Gets the cta.
	 *
	 * @return the cta
	 */
	public String getCta() {
		return cta;
	}

	/**
	 * Sets the scta.
	 *
	 * @param scta the new scta
	 */
	public void setScta(String scta) {
		this.scta = scta;
	}

	/**
	 * Gets the scta.
	 *
	 * @return the scta
	 */
	public String getScta() {
		return scta;
	}

	/**
	 * Sets the sscta.
	 *
	 * @param sscta the new sscta
	 */
	public void setSscta(String sscta) {
		this.sscta = sscta;
	}

	/**
	 * Gets the sscta.
	 *
	 * @return the sscta
	 */
	public String getSscta() {
		return sscta;
	}

	/**
	 * Sets the ssscta.
	 *
	 * @param ssscta the new ssscta
	 */
	public void setSsscta(String ssscta) {
		this.ssscta = ssscta;
	}

	/**
	 * Gets the ssscta.
	 *
	 * @return the ssscta
	 */
	public String getSsscta() {
		return ssscta;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	/**
	 * Gets the natgas repository.
	 *
	 * @return the natgas repository
	 */
	public NatgasRepository getNatgasRepository() {
		return natgasRepository;
	}

	/**
	 * Sets the natgas repository.
	 *
	 * @param natgasRepository the new natgas repository
	 */
	public void setNatgasRepository(NatgasRepository natgasRepository) {
		this.natgasRepository = natgasRepository;
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
	 * Sets the account service.
	 *
	 * @param accountService the new account service
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Load.
	 */
	public void load() {
		System.out.println("loading :" + cta + "," + scta + "," + sscta + "," + ssscta);
		GemUser user = getUserDetails();
		Long idSector = Long.valueOf(user.getIdSector());
		String modScta = accountService.fillZeros(scta, 10);
		String modSscta = accountService.fillZeros(sscta, 15);
		String modSsscta = accountService.fillZeros(ssscta, 4);
		if (cta.equals("5100")
				&& !(modSsscta.startsWith("1") || modSsscta.startsWith("2") || modSsscta.startsWith("3"))) {
			displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 1000, 2000 o 3000");
			return;
		} else if (cta.equals("5200") && !modSsscta.startsWith("4")) {
			displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 4000");
			return;
		} else if (cta.equals("5300") && !modSsscta.startsWith("8")) {
			displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 8000");
			return;
		} else if (cta.equals("5400") && !modSsscta.startsWith("9")) {
			displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 9000");
			return;
		} else if (cta.equals("5600") && !modSsscta.startsWith("6")) {
			displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 6000");
			return;
		} else if (cta.equals("5700") && !modSsscta.startsWith("5")) {
			displayErrorMessage(MSG_ERROR_PARTIDA_GASTO + " 5000");
			return;
		}
		String[] names = getNames(modScta, modSscta, modSsscta, idSector);
		if (names == null)
			return;
		if (cta.equals("5100"))
			createLevelAccounts(ACCOUNTS_5100, names, idSector, modScta, modSscta, modSsscta);
		else if (cta.equals("5200"))
			createLevelAccounts(ACCOUNTS_5200, names, idSector, modScta, modSscta, modSsscta);
		else if (cta.equals("5300"))
			createLevelAccounts(ACCOUNTS_5300, names, idSector, modScta, modSscta, modSsscta);
		else if (cta.equals("5400"))
			createLevelAccounts(ACCOUNTS_5400, names, idSector, modScta, modSscta, modSsscta);
		else if (cta.equals("5600"))
			createLevelAccounts(ACCOUNTS_5600, names, idSector, modScta, modSscta, modSsscta);
		else if (cta.equals("5700"))
			createLevelAccounts(ACCOUNTS_5700, names, idSector, modScta, modSscta, modSsscta);
		else if (cta.equals("8217"))
			createLevelAccounts(ACCOUNTS_8217, names, idSector, modScta, modSscta, modSsscta);

	}

	/**
	 * Gets the names.
	 *
	 * @param modScta the mod scta
	 * @param modSscta the mod sscta
	 * @param modSsscta the mod ssscta
	 * @param idSector the id sector
	 * @return the names
	 */
	String[] getNames(String modScta, String modSscta, String modSsscta, Long idSector) {
		String[] values = new String[3];
		Catdep catdep = catdepRepository.findFirstByIdsectorAndClvdepAndUltnivOrderByIdAsc(idSector.intValue(), modScta,
				"S");
		if (catdep == null) {
			displayErrorMessage("no existe la dependencia o no es de último nivel");
			return null;
		} else {
			displayInfoMessage("si existe la dependencia y es de último nivel");
			values[0] = catdep.getNomdep();
		}
		Xcatpro xcatpro = xcatproRepository.findFirstByClvdepAndProgragramAndUltnivAndSector(modScta, modSscta, "S",
				idSector.intValue());
		if (xcatpro == null) {
			displayErrorMessage("no existe el programa o no es de último nivel");
			return null;
		} else {
			displayInfoMessage("si existe el programa y es de último nivel");
			values[1] = xcatpro.getNompro();
		}
		Natgas natgas = natgasRepository.findFirstByClvgasAndIndcapAndIdsector(modSsscta, "S", idSector.intValue());
		if (natgas == null) {
			displayErrorMessage("no existe la naturaleza de gasto.");
			return null;
		} else {
			if (modSsscta.substring(3).equals("0")) {
				displayErrorMessage("La naturaleza de gasto no es de ultimo nivel.");
				return null;
			} else {
				displayInfoMessage("Existe la naturaleza de gasto y es de último nivel");
				values[2] = natgas.getNomgas();
			}
		}
		return values;
	}

	/**
	 * Creates the level accounts.
	 *
	 * @param accounts the accounts
	 * @param names the names
	 * @param idSector the id sector
	 * @param modScta the mod scta
	 * @param modSscta the mod sscta
	 * @param modSsscta the mod ssscta
	 */
	void createLevelAccounts(String[] accounts, String[] names, Long idSector, String modScta, String modSscta,
			String modSsscta) {
		for (String account : accounts) {
			createMissingAccounts(account, names, idSector, modScta, modSscta, modSsscta);
		}
	}

	/**
	 * Creates the missing accounts.
	 *
	 * @param currentAccount the current account
	 * @param names the names
	 * @param idSector the id sector
	 * @param modScta the mod scta
	 * @param modSscta the mod sscta
	 * @param modSsscta the mod ssscta
	 */
	void createMissingAccounts(String currentAccount, String[] names, Long idSector, String modScta, String modSscta,
			String modSsscta) {
		if (accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(currentAccount,
				modScta, modSscta, modSsscta, "", idSector) == null) {
			accountService.saveAccount(
					new Cuenta(currentAccount, modScta, modSscta, modSsscta, "", names[2], "S", 4, this.getUserDetails().getUsername(), idSector));
		}
		if (accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(currentAccount,
				modScta, modSscta, "", "", idSector) == null) {
			accountService.saveAccount(
					new Cuenta(currentAccount, modScta, modSscta, "", "", names[1], "N", 3, this.getUserDetails().getUsername(), idSector));
		}
		if (accountService.findFirstByCuentaAndScuentaAndSscuentaAndSsscuentaAndSssscuentaAndIdsector(currentAccount,
				modScta, "", "", "", idSector) == null) {
			accountService
					.saveAccount(new Cuenta(currentAccount, modScta, "", "", "", names[0], "N", 2, this.getUserDetails().getUsername(), idSector));
		}

	}

}
