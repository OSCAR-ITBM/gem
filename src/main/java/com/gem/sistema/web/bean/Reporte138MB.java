package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm3511;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm3511Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm3511Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte138MB.
 */
@ManagedBean(name = "reporte138MB")
@ViewScoped
public class Reporte138MB extends BaseDirectReport {

	/** The Constant SI. */
	private static final String SI = "SI";
	
	/** The Constant NO. */
	private static final String NO = "NO";
	
	/** The Constant REPORTE_138. */
	private static final String REPORTE_138 = "reporte138";
	
	/** The Constant PDF. */
	private static final String PDF = ".pdf";

	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;
	
	/** The semestre. */
	private String semestre;
	
	/** The listsemestre. */
	private List<String> listsemestre;
	
	/** The capturo. */
	private String capturo;
	
	/** The fecha cap. */
	private Date fechaCap;

	/** The pm 3511. */
	private Pm3511 pm3511;
	
	/** The selecte pm 3511. */
	private Pm3511 selectePm3511 = new Pm3511();
	
	/** The list pm 3511. */
	private List<Pm3511> listPm3511;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm3511> comboTri;

	/** The b lblsemestre. */
	private boolean bLblsemestre = Boolean.TRUE;
	
	/** The b combo tri. */
	private boolean bComboTri = Boolean.FALSE;
	
	/** The b lbl. */
	private boolean bLbl = Boolean.TRUE;
	
	/** The b txt. */
	private boolean bTxt = Boolean.FALSE;
	
	/** The b V save. */
	private boolean bVSave = Boolean.FALSE;
	
	/** The b V modificar. */
	private boolean bVModificar = Boolean.TRUE;
	
	/** The b btn moficar. */
	private boolean bBtnMoficar = Boolean.TRUE;
	
	/** The b modificar. */
	private boolean bModificar = Boolean.FALSE;
	
	/** The b borrar. */
	private boolean bBorrar = Boolean.TRUE;
	
	/** The b add. */
	private boolean bAdd = Boolean.FALSE;
	
	/** The b lbl sec pub. */
	private boolean bLblSecPub = Boolean.TRUE;
	
	/** The b lbl est sup. */
	private boolean bLblEstSup = Boolean.TRUE;
	
	/** The b lbl certf. */
	private boolean bLblCertf = Boolean.TRUE;
	
	/** The b sec pub. */
	private boolean bSecPub = Boolean.FALSE;
	
	/** The b est sup. */
	private boolean bEstSup = Boolean.FALSE;
	
	/** The b certf. */
	private boolean bCertf = Boolean.FALSE;

	/** The pm 3511 service. */
	@ManagedProperty("#{pm3511Service}")
	private Pm3511Service pm3511Service;

	/** The pm 3511 repository. */
	@ManagedProperty("#{pm3511Repository}")
	private Pm3511Repository pm3511Repository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the semestre.
	 *
	 * @return the semestre
	 */
	public String getSemestre() {
		return semestre;
	}

	/**
	 * Sets the semestre.
	 *
	 * @param semestre the new semestre
	 */
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	/**
	 * Gets the listsemestre.
	 *
	 * @return the listsemestre
	 */
	public List<String> getListsemestre() {
		return listsemestre;
	}

	/**
	 * Sets the listsemestre.
	 *
	 * @param listsemestre the new listsemestre
	 */
	public void setListsemestre(List<String> listsemestre) {
		this.listsemestre = listsemestre;
	}

	/**
	 * Gets the pm 3511.
	 *
	 * @return the pm 3511
	 */
	public Pm3511 getPm3511() {
		return pm3511;
	}

	/**
	 * Sets the pm 3511.
	 *
	 * @param pm3511 the new pm 3511
	 */
	public void setPm3511(Pm3511 pm3511) {
		this.pm3511 = pm3511;
	}

	/**
	 * Gets the list pm 3511.
	 *
	 * @return the list pm 3511
	 */
	public List<Pm3511> getListPm3511() {
		return listPm3511;
	}

	/**
	 * Sets the list pm 3511.
	 *
	 * @param listPm3511 the new list pm 3511
	 */
	public void setListPm3511(List<Pm3511> listPm3511) {
		this.listPm3511 = listPm3511;
	}

	/**
	 * Checks if is b lblsemestre.
	 *
	 * @return true, if is b lblsemestre
	 */
	public boolean isbLblsemestre() {
		return bLblsemestre;
	}

	/**
	 * Sets the b lblsemestre.
	 *
	 * @param bLblsemestre the new b lblsemestre
	 */
	public void setbLblsemestre(boolean bLblsemestre) {
		this.bLblsemestre = bLblsemestre;
	}

	/**
	 * Checks if is b combo tri.
	 *
	 * @return true, if is b combo tri
	 */
	public boolean isbComboTri() {
		return bComboTri;
	}

	/**
	 * Sets the b combo tri.
	 *
	 * @param bComboTri the new b combo tri
	 */
	public void setbComboTri(boolean bComboTri) {
		this.bComboTri = bComboTri;
	}

	/**
	 * Checks if is b lbl.
	 *
	 * @return true, if is b lbl
	 */
	public boolean isbLbl() {
		return bLbl;
	}

	/**
	 * Sets the b lbl.
	 *
	 * @param bLbl the new b lbl
	 */
	public void setbLbl(boolean bLbl) {
		this.bLbl = bLbl;
	}

	/**
	 * Checks if is b txt.
	 *
	 * @return true, if is b txt
	 */
	public boolean isbTxt() {
		return bTxt;
	}

	/**
	 * Sets the b txt.
	 *
	 * @param bTxt the new b txt
	 */
	public void setbTxt(boolean bTxt) {
		this.bTxt = bTxt;
	}

	/**
	 * Checks if is b V save.
	 *
	 * @return true, if is b V save
	 */
	public boolean isbVSave() {
		return bVSave;
	}

	/**
	 * Sets the b V save.
	 *
	 * @param bVSave the new b V save
	 */
	public void setbVSave(boolean bVSave) {
		this.bVSave = bVSave;
	}

	/**
	 * Checks if is b V modificar.
	 *
	 * @return true, if is b V modificar
	 */
	public boolean isbVModificar() {
		return bVModificar;
	}

	/**
	 * Sets the b V modificar.
	 *
	 * @param bVModificar the new b V modificar
	 */
	public void setbVModificar(boolean bVModificar) {
		this.bVModificar = bVModificar;
	}

	/**
	 * Checks if is b btn moficar.
	 *
	 * @return true, if is b btn moficar
	 */
	public boolean isbBtnMoficar() {
		return bBtnMoficar;
	}

	/**
	 * Sets the b btn moficar.
	 *
	 * @param bBtnMoficar the new b btn moficar
	 */
	public void setbBtnMoficar(boolean bBtnMoficar) {
		this.bBtnMoficar = bBtnMoficar;
	}

	/**
	 * Checks if is b modificar.
	 *
	 * @return true, if is b modificar
	 */
	public boolean isbModificar() {
		return bModificar;
	}

	/**
	 * Sets the b modificar.
	 *
	 * @param bModificar the new b modificar
	 */
	public void setbModificar(boolean bModificar) {
		this.bModificar = bModificar;
	}

	/**
	 * Checks if is b borrar.
	 *
	 * @return true, if is b borrar
	 */
	public boolean isbBorrar() {
		return bBorrar;
	}

	/**
	 * Sets the b borrar.
	 *
	 * @param bBorrar the new b borrar
	 */
	public void setbBorrar(boolean bBorrar) {
		this.bBorrar = bBorrar;
	}

	/**
	 * Checks if is b add.
	 *
	 * @return true, if is b add
	 */
	public boolean isbAdd() {
		return bAdd;
	}

	/**
	 * Sets the b add.
	 *
	 * @param bAdd the new b add
	 */
	public void setbAdd(boolean bAdd) {
		this.bAdd = bAdd;
	}

	/**
	 * Gets the pm 3511 service.
	 *
	 * @return the pm 3511 service
	 */
	public Pm3511Service getPm3511Service() {
		return pm3511Service;
	}

	/**
	 * Sets the pm 3511 service.
	 *
	 * @param pm3511Service the new pm 3511 service
	 */
	public void setPm3511Service(Pm3511Service pm3511Service) {
		this.pm3511Service = pm3511Service;
	}

	/**
	 * Gets the pm 3511 repository.
	 *
	 * @return the pm 3511 repository
	 */
	public Pm3511Repository getPm3511Repository() {
		return pm3511Repository;
	}

	/**
	 * Sets the pm 3511 repository.
	 *
	 * @param pm3511Repository the new pm 3511 repository
	 */
	public void setPm3511Repository(Pm3511Repository pm3511Repository) {
		this.pm3511Repository = pm3511Repository;
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return capturo;
	}

	/**
	 * Sets the capturo.
	 *
	 * @param capturo the new capturo
	 */
	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	/**
	 * Gets the fecha cap.
	 *
	 * @return the fecha cap
	 */
	public Date getFechaCap() {
		return fechaCap;
	}

	/**
	 * Sets the fecha cap.
	 *
	 * @param fechaCap the new fecha cap
	 */
	public void setFechaCap(Date fechaCap) {
		this.fechaCap = fechaCap;
	}

	/**
	 * Checks if is b lbl sec pub.
	 *
	 * @return true, if is b lbl sec pub
	 */
	public boolean isbLblSecPub() {
		return bLblSecPub;
	}

	/**
	 * Sets the b lbl sec pub.
	 *
	 * @param bLblSecPub the new b lbl sec pub
	 */
	public void setbLblSecPub(boolean bLblSecPub) {
		this.bLblSecPub = bLblSecPub;
	}

	/**
	 * Checks if is b lbl est sup.
	 *
	 * @return true, if is b lbl est sup
	 */
	public boolean isbLblEstSup() {
		return bLblEstSup;
	}

	/**
	 * Sets the b lbl est sup.
	 *
	 * @param bLblEstSup the new b lbl est sup
	 */
	public void setbLblEstSup(boolean bLblEstSup) {
		this.bLblEstSup = bLblEstSup;
	}

	/**
	 * Checks if is b lbl certf.
	 *
	 * @return true, if is b lbl certf
	 */
	public boolean isbLblCertf() {
		return bLblCertf;
	}

	/**
	 * Sets the b lbl certf.
	 *
	 * @param bLblCertf the new b lbl certf
	 */
	public void setbLblCertf(boolean bLblCertf) {
		this.bLblCertf = bLblCertf;
	}

	/**
	 * Checks if is b sec pub.
	 *
	 * @return true, if is b sec pub
	 */
	public boolean isbSecPub() {
		return bSecPub;
	}

	/**
	 * Sets the b sec pub.
	 *
	 * @param bSecPub the new b sec pub
	 */
	public void setbSecPub(boolean bSecPub) {
		this.bSecPub = bSecPub;
	}

	/**
	 * Checks if is b est sup.
	 *
	 * @return true, if is b est sup
	 */
	public boolean isbEstSup() {
		return bEstSup;
	}

	/**
	 * Sets the b est sup.
	 *
	 * @param bEstSup the new b est sup
	 */
	public void setbEstSup(boolean bEstSup) {
		this.bEstSup = bEstSup;
	}

	/**
	 * Checks if is b certf.
	 *
	 * @return true, if is b certf
	 */
	public boolean isbCertf() {
		return bCertf;
	}

	/**
	 * Sets the b certf.
	 *
	 * @param bCertf the new b certf
	 */
	public void setbCertf(boolean bCertf) {
		this.bCertf = bCertf;
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
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		bLblsemestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		this.isbLblCertf();
		this.isbLblEstSup();
		this.isbLblSecPub();
		this.isbCertf();
		this.isbEstSup();
		this.isbSecPub();

		jasperReporteName = REPORTE_138;
		endFilename = jasperReporteName + PDF;
		capturo = this.getUserDetails().getUsername();
		fechaCap = Calendar.getInstance().getTime();

		listPm3511 = pm3511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm3511.get(0).getUserid()) {
			bLblsemestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bBorrar = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.TRUE;
			bAdd = Boolean.FALSE;
		}

		if (listPm3511.size() == 2)
			bAdd = Boolean.TRUE;
		else
			bAdd = Boolean.FALSE;

		listsemestre = new ArrayList<String>();
		for (int i = 1; i <= 2; i++)
			listsemestre.add("0" + i);
		semestre = listsemestre.get(0);

		comboTri = pm3511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		conTrim = comboTri.get(0).getSemes().toString();

	}

	/**
	 * Gets the months.
	 *
	 * @param semes the semes
	 * @return the months
	 */
	public String getMonths(Integer semes) {
		if (semes == 1) {
			return "01,06";
		} else {
			return "07,12";
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		String[] mesArray = this.getMonths(Integer.valueOf(conTrim)).split(",");
		parameters.put("municipioName", firmas.getCampo1());
		parameters.put("municipioClave", conctb.getClave());
		parameters.put("lastDayOfMonth", getLastDay(Integer.valueOf(mesArray[1])));
		parameters.put("mesInicioName", tcMesRepository.findByMes(mesArray[0]).getDescripcion());
		parameters.put("mesFinName", tcMesRepository.findByMes(mesArray[1]).getDescripcion());
		parameters.put("year", conctb.getAnoemp());
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("semestre", Integer.valueOf(conTrim));
		parameters.put("idSector", getUserDetails().getIdSector());
		parameters.put("firmaP1", firmas.getL3());
		parameters.put("firmaN1", firmas.getN3());
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the con trim.
	 *
	 * @return the con trim
	 */
	public String getConTrim() {
		return conTrim;
	}

	/**
	 * Sets the con trim.
	 *
	 * @param conTrim the new con trim
	 */
	public void setConTrim(String conTrim) {
		this.conTrim = conTrim;
	}

	/**
	 * Gets the combo tri.
	 *
	 * @return the combo tri
	 */
	public List<Pm3511> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm3511> comboTri) {
		this.comboTri = comboTri;
	}

	/**
	 * Gets the selecte pm 3511.
	 *
	 * @return the selecte pm 3511
	 */
	public Pm3511 getSelectePm3511() {
		return selectePm3511;
	}

	/**
	 * Sets the selecte pm 3511.
	 *
	 * @param selectePm3511 the new selecte pm 3511
	 */
	public void setSelectePm3511(Pm3511 selectePm3511) {
		this.selectePm3511 = selectePm3511;
	}

	/**
	 * Adds the.
	 *
	 * @param index the index
	 */
	public void add(Integer index) {
		bLblsemestre = Boolean.FALSE;
		bComboTri = Boolean.TRUE;
		bLbl = Boolean.FALSE;
		bTxt = Boolean.TRUE;
		bVSave = Boolean.TRUE;
		bVModificar = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.FALSE;
		bBorrar = Boolean.TRUE;
		bAdd = Boolean.TRUE;
		listPm3511.add(index, pm3511Service.add());
		if (null == listPm3511.get(1).getUserid())
			listPm3511.remove(1);
		this.activeTxt(index, "secpub");
		this.activeTxt(index, "estsup");
		this.activeTxt(index, "cert");

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm3511 = this.entitySave(index);
		pm3511 = this.notEmpty(pm3511);
		if (bModificar) {

			pm3511Repository.save(pm3511);
			// this.calcularAcumulado(pm3511);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se modificaron los datos correctamente");

			bLblsemestre = Boolean.TRUE;
			bComboTri = Boolean.FALSE;
			bLbl = Boolean.TRUE;
			bTxt = Boolean.FALSE;
			bVSave = Boolean.FALSE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.FALSE;
			bModificar = Boolean.FALSE;
			bBorrar = Boolean.FALSE;
			bLblCertf = Boolean.TRUE;
			bLblEstSup = Boolean.TRUE;
			bLblSecPub = Boolean.TRUE;
			bCertf = Boolean.FALSE;
			bEstSup = Boolean.FALSE;
			bSecPub = Boolean.FALSE;

			if (pm3511Repository.count(this.getUserDetails().getIdSector()) == 2)
				bAdd = Boolean.TRUE;
			else
				bAdd = Boolean.FALSE;

		} else {

			listPm3511.set(index, pm3511);
			listPm3511 = pm3511Service.save(index, listPm3511);

			if (listPm3511.get(index).isbGuardar()) {
				// this.calcularAcumulado(pm3511);
				bLblsemestre = Boolean.TRUE;
				bComboTri = Boolean.FALSE;
				bLbl = Boolean.TRUE;
				bTxt = Boolean.FALSE;
				bVSave = Boolean.FALSE;
				bVModificar = Boolean.TRUE;
				bBtnMoficar = Boolean.FALSE;
				bModificar = Boolean.FALSE;
				bBorrar = Boolean.FALSE;
				bLblCertf = Boolean.TRUE;
				bLblEstSup = Boolean.TRUE;
				bLblSecPub = Boolean.TRUE;
				bCertf = Boolean.FALSE;
				bEstSup = Boolean.FALSE;
				bSecPub = Boolean.FALSE;

				if (listPm3511.size() == 2)
					bAdd = Boolean.TRUE;
				else
					bAdd = Boolean.FALSE;

			}
		}
		comboTri = pm3511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm3511 = pm3511Service.delete(index, listPm3511);
		comboTri = pm3511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		bLblCertf = Boolean.TRUE;
		bLblEstSup = Boolean.TRUE;
		bLblSecPub = Boolean.TRUE;
		bCertf = Boolean.FALSE;
		bEstSup = Boolean.FALSE;
		bSecPub = Boolean.FALSE;
		bAdd = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;

		if (CollectionUtils
				.isEmpty(pm3511Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector()))) {
			bBorrar = Boolean.TRUE;
			bVModificar = Boolean.TRUE;
			bBtnMoficar = Boolean.TRUE;
		}

	}

	/**
	 * Clean.
	 *
	 * @param index the index
	 */
	public void clean(Integer index) {
		if (bModificar) {
			pm3511 = pm3511Repository.findAllBysemesAndIdsector(listPm3511.get(index).getSemes(),
					this.getUserDetails().getIdSector());
			listPm3511.set(index, pm3511);
		} else
			listPm3511.set(index, pm3511Service.clean());
		this.activeTxt(index, "secpub");
		this.activeTxt(index, "estsup");
		this.activeTxt(index, "cert");
	}

	/**
	 * Modify.
	 *
	 * @param index the index
	 */
	public void modify(Integer index) {

		bLblsemestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.FALSE;
		bTxt = Boolean.TRUE;
		bVSave = Boolean.TRUE;
		bVModificar = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bModificar = Boolean.TRUE;
		bBorrar = Boolean.TRUE;
		bAdd = Boolean.TRUE;
		this.activeTxt(index, "secpub");
		this.activeTxt(index, "estsup");
		this.activeTxt(index, "cert");

	}

	/**
	 * Cancel.
	 *
	 * @param index the index
	 */
	public void cancel(Integer index) {
		bLblsemestre = Boolean.TRUE;
		bComboTri = Boolean.FALSE;
		bLbl = Boolean.TRUE;
		bTxt = Boolean.FALSE;
		bVSave = Boolean.FALSE;
		bBtnMoficar = Boolean.FALSE;
		bBorrar = Boolean.FALSE;
		bVModificar = Boolean.FALSE;
		bVModificar = Boolean.TRUE;
		bLblCertf = Boolean.TRUE;
		bLblEstSup = Boolean.TRUE;
		bLblSecPub = Boolean.TRUE;
		bCertf = Boolean.FALSE;
		bEstSup = Boolean.FALSE;
		bSecPub = Boolean.FALSE;
		this.isbLblCertf();
		this.isbLblEstSup();
		this.isbLblSecPub();
		listPm3511 = pm3511Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm3511)) {

			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		}

		bModificar = Boolean.FALSE;

		if (listPm3511.size() == 2) {
			bAdd = Boolean.TRUE;
		} else {
			bAdd = Boolean.FALSE;
		}
		listPm3511 = pm3511Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 3511
	 */
	public Pm3511 entitySave(Integer index) {
		pm3511 = listPm3511.get(index);

		pm3511.setUserid(this.getUserDetails().getUsername());
		pm3511.setIdRef(0L);
		pm3511.setIdsector(this.getUserDetails().getIdSector());
		pm3511.setFeccap(Calendar.getInstance().getTime());
		pm3511.setCapturo(this.getUserDetails().getUsername());
		pm3511.setSemes(bModificar == true ? pm3511.getSemes() : Integer.valueOf(semestre));
		return pm3511;
	}

	/**
	 * Active txt.
	 *
	 * @param index the index
	 * @param camp the camp
	 */
	public void activeTxt(Integer index, String camp) {
		pm3511 = listPm3511.get(index);
		if (camp.equals("secpub")) {
			if (pm3511.getSecpub().equals(SI)) {
				bLblSecPub = Boolean.FALSE;
				bSecPub = Boolean.TRUE;
			} else if (pm3511.getSecpub().equals(NO)) {
				this.setbLblSecPub(Boolean.TRUE);
				this.setbSecPub(Boolean.FALSE);
				pm3511.setUltlugar("");
				pm3511.setPubpuesto("");
			}

		} else if (camp.equals("estsup")) {
			if (pm3511.getEstsup().equals(SI)) {
				bEstSup = Boolean.TRUE;
				bLblEstSup = Boolean.FALSE;
			} else if (pm3511.getEstsup().equals(NO)) {
				bEstSup = Boolean.FALSE;
				bLblEstSup = Boolean.TRUE;
				pm3511.setEspest("");
			}

		} else if (camp.equals("cert")) {
			if (pm3511.getCertif().equals(SI)) {
				bCertf = Boolean.TRUE;
				bLblCertf = Boolean.FALSE;

			} else if (pm3511.getCertif().equals(NO)) {
				bCertf = Boolean.FALSE;
				bLblCertf = Boolean.TRUE;
				pm3511.setEspecer("");

			}

		}
	}

	/**
	 * Calcular acumulado.
	 *
	 * @param entity the entity
	 */
	public void calcularAcumulado(Pm3511 entity) {
		Integer contar = pm3511Repository.count(this.getUserDetails().getIdSector());

		// if(contar.intValue() < 2){
		// entity.setAcudi(0);
		// entity.setAcudc(0);
		// } else {
		// entity.setAcudi(Pm3511Repository.sumAcumDi(this.getUserDetails().getIdSector()));
		// entity.setAcudc(Pm3511Repository.sumAcumDc(this.getUserDetails().getIdSector()));
		// }
		// Pm3511Repository.save(entity);
	}

	/** The new date 1. */
	Date newDate1;

	/**
	 * Gets the new date 1.
	 *
	 * @return the new date 1
	 */
	public Date getNewDate1() {
		return newDate1;
	}

	/**
	 * Sets the new date 1.
	 *
	 * @param newDate1 the new new date 1
	 */
	public void setNewDate1(Date newDate1) {
		this.newDate1 = newDate1;
	}

	/**
	 * On date select.
	 *
	 * @param event the event
	 */
	public void onDateSelect(SelectEvent event) {
		this.newDate1 = (Date) event.getObject();

	}

	/**
	 * On date row sected.
	 *
	 * @param index the index
	 * @param camp the camp
	 */
	public void onDateRowSected(Integer index, String camp) {
		selectePm3511 = listPm3511.get(index);
		if (camp.equals("fechaInput")) {
			selectePm3511.setFecing(this.getNewDate1());

		}

	}

	/**
	 * Not empty.
	 *
	 * @param entity the entity
	 * @return the pm 3511
	 */
	public Pm3511 notEmpty(Pm3511 entity) {
		entity.setNombre(null == entity.getNombre() ? "" : entity.getNombre());
		entity.setGrado(null == entity.getGrado() ? "" : entity.getGrado());
		entity.setDocu(null == entity.getDocu() ? "" : entity.getDocu());
		entity.setExperi(null == entity.getExperi() ? "" : entity.getExperi());
		entity.setUltpuesto(null == entity.getUltpuesto() ? "" : entity.getUltpuesto());
		entity.setSecpub(null == entity.getSecpub() ? "" : entity.getSecpub());
		entity.setPubpuesto(null == entity.getPubpuesto() ? "" : entity.getPubpuesto());
		entity.setUltlugar(null == entity.getUltlugar() ? "" : entity.getUltlugar());
		entity.setEstsup(null == entity.getEstsup() ? "" : entity.getEstsup());
		entity.setEspest(null == entity.getEspest() ? "" : entity.getEspest());
		return entity;
	}

}
