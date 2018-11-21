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
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm5811;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.Pm5811Repository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.Pm5811Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte142MB.
 */
@ManagedBean(name = "reporte142MB")
@ViewScoped
public class Reporte142MB extends BaseDirectReport {

	/** The Constant SI. */
	private static final String SI = "SI";
	
	/** The Constant NO. */
	private static final String NO = "NO";
	
	/** The Constant REPORTE_142. */
	private static final String REPORTE_142 = "reporte142";
	
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

	/** The pm 5811. */
	private Pm5811 pm5811;
	
	/** The list pm 5811. */
	private List<Pm5811> listPm5811;

	/** The con trim. */
	private String conTrim;
	
	/** The combo tri. */
	private List<Pm5811> comboTri;

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
	
	/** The b lbl est sup 2. */
	private boolean bLblEstSup2 = Boolean.TRUE;
	
	/** The b lbl certf. */
	private boolean bLblCertf = Boolean.TRUE;
	
	/** The b sec pub. */
	private boolean bSecPub = Boolean.FALSE;
	
	/** The b est sup. */
	private boolean bEstSup = Boolean.FALSE;
	
	/** The b est sup 2. */
	private boolean bEstSup2 = Boolean.FALSE;
	
	/** The b certf. */
	private boolean bCertf = Boolean.FALSE;

	/** The pm 5811 service. */
	@ManagedProperty("#{pm5811Service}")
	private Pm5811Service pm5811Service;

	/** The pm 5811 repository. */
	@ManagedProperty("#{pm5811Repository}")
	private Pm5811Repository pm5811Repository;

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
	 * Gets the pm 5811.
	 *
	 * @return the pm 5811
	 */
	public Pm5811 getPm5811() {
		return pm5811;
	}

	/**
	 * Sets the pm 5811.
	 *
	 * @param pm5811 the new pm 5811
	 */
	public void setPm5811(Pm5811 pm5811) {
		this.pm5811 = pm5811;
	}

	/**
	 * Gets the list pm 5811.
	 *
	 * @return the list pm 5811
	 */
	public List<Pm5811> getListPm5811() {
		return listPm5811;
	}

	/**
	 * Sets the list pm 5811.
	 *
	 * @param listPm5811 the new list pm 5811
	 */
	public void setListPm5811(List<Pm5811> listPm5811) {
		this.listPm5811 = listPm5811;
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
	 * Gets the pm 5811 service.
	 *
	 * @return the pm 5811 service
	 */
	public Pm5811Service getPm5811Service() {
		return pm5811Service;
	}

	/**
	 * Sets the pm 5811 service.
	 *
	 * @param pm5811Service the new pm 5811 service
	 */
	public void setPm5811Service(Pm5811Service pm5811Service) {
		this.pm5811Service = pm5811Service;
	}

	/**
	 * Gets the pm 5811 repository.
	 *
	 * @return the pm 5811 repository
	 */
	public Pm5811Repository getPm5811Repository() {
		return pm5811Repository;
	}

	/**
	 * Sets the pm 5811 repository.
	 *
	 * @param pm5811Repository the new pm 5811 repository
	 */
	public void setPm5811Repository(Pm5811Repository pm5811Repository) {
		this.pm5811Repository = pm5811Repository;
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
	 * Checks if is b lbl est sup 2.
	 *
	 * @return true, if is b lbl est sup 2
	 */
	public boolean isbLblEstSup2() {
		return bLblEstSup2;
	}

	/**
	 * Sets the b lbl est sup 2.
	 *
	 * @param bLblEstSup2 the new b lbl est sup 2
	 */
	public void setbLblEstSup2(boolean bLblEstSup2) {
		this.bLblEstSup2 = bLblEstSup2;
	}

	/**
	 * Checks if is b est sup 2.
	 *
	 * @return true, if is b est sup 2
	 */
	public boolean isbEstSup2() {
		return bEstSup2;
	}

	/**
	 * Sets the b est sup 2.
	 *
	 * @param bEstSup2 the new b est sup 2
	 */
	public void setbEstSup2(boolean bEstSup2) {
		this.bEstSup2 = bEstSup2;
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

		jasperReporteName = REPORTE_142;
		endFilename = jasperReporteName + PDF;
		capturo = this.getUserDetails().getUsername();
		fechaCap = Calendar.getInstance().getTime();

		listPm5811 = pm5811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		if (null == listPm5811.get(0).getUserid()) {
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

		if (listPm5811.size() == 2)
			bAdd = Boolean.TRUE;
		else
			bAdd = Boolean.FALSE;

		listsemestre = new ArrayList<String>();
		for (int i = 1; i <= 2; i++)
			listsemestre.add("0" + i);
		semestre = listsemestre.get(0);

		comboTri = pm5811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
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
		parameters.put("firmaP1", firmas.getL26());
		parameters.put("firmaN1", firmas.getN26());

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
	public List<Pm5811> getComboTri() {
		return comboTri;
	}

	/**
	 * Sets the combo tri.
	 *
	 * @param comboTri the new combo tri
	 */
	public void setComboTri(List<Pm5811> comboTri) {
		this.comboTri = comboTri;
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
		listPm5811.add(index, pm5811Service.add());
		if (null == listPm5811.get(1).getUserid())
			listPm5811.remove(1);

	}

	/**
	 * Save.
	 *
	 * @param index the index
	 */
	public void save(Integer index) {
		pm5811 = this.entitySave(index);
		pm5811 = this.notEmpty(pm5811);
		if (bModificar) {

			pm5811Repository.save(pm5811);
			// this.calcularAcumulado(pm5811);
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
			bEstSup2 = Boolean.FALSE;
			bLblEstSup2 = Boolean.TRUE;

			if (pm5811Repository.count(this.getUserDetails().getIdSector()) == 2)
				bAdd = Boolean.TRUE;
			else
				bAdd = Boolean.FALSE;
		} else {
			listPm5811.set(index, pm5811);
			listPm5811 = pm5811Service.save(index, listPm5811);

			if (listPm5811.get(index).isbGuardar()) {
				// this.calcularAcumulado(pm5811);
				bEstSup2 = Boolean.FALSE;
				bLblEstSup2 = Boolean.TRUE;
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

				if (listPm5811.size() == 2)
					bAdd = Boolean.TRUE;
				else
					bAdd = Boolean.FALSE;
			}
		}
		comboTri = pm5811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());

	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(Integer index) {
		listPm5811 = pm5811Service.delete(index, listPm5811);
		comboTri = pm5811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
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

		if (CollectionUtils.isEmpty(pm5811Repository.findAllByIdsector(this.getUserDetails().getIdSector()))) {
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
			pm5811 = pm5811Repository.findAllBysemesAndIdsector(listPm5811.get(index).getSemes(),
					this.getUserDetails().getIdSector());
			listPm5811.set(index, pm5811);
		} else
			listPm5811.set(index, pm5811Service.clean());
	}

	/**
	 * Modify.
	 *
	 * @param index the index
	 */
	public void modify(Integer index) {

		pm5811 = listPm5811.get(index);
		if (pm5811.getSecpub().equals(SI)) {
			bLblEstSup = Boolean.FALSE;
			bEstSup = Boolean.TRUE;
		} else if (pm5811.getSecpub().equals(NO)) {
			bLblEstSup = Boolean.TRUE;
			bEstSup = Boolean.FALSE;
		}

		if (pm5811.getEstsup().equals(SI)) {
			this.setbLblEstSup2(Boolean.FALSE);
			this.setbEstSup2(Boolean.TRUE);
		} else if (pm5811.getEstsup().equals(NO)) {
			this.setbLblEstSup2(Boolean.TRUE);
			this.setbEstSup2(Boolean.FALSE);
		}

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
		bEstSup2 = Boolean.FALSE;
		bLblEstSup2 = Boolean.TRUE;
		this.isbLblCertf();
		this.isbLblEstSup();
		this.isbLblSecPub();
		listPm5811 = pm5811Repository.findAllByIdsectorOrderBySemesAsc(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPm5811)) {

			bBtnMoficar = Boolean.TRUE;
			bBorrar = Boolean.TRUE;

		}

		bModificar = Boolean.FALSE;

		if (listPm5811.size() == 2)
			bAdd = Boolean.TRUE;
		else
			bAdd = Boolean.FALSE;

		listPm5811 = pm5811Service.orderBySemestreAsc(this.getUserDetails().getIdSector());
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Edicion cancelada");
	}

	/**
	 * Entity save.
	 *
	 * @param index the index
	 * @return the pm 5811
	 */
	public Pm5811 entitySave(Integer index) {
		pm5811 = listPm5811.get(index);

		pm5811.setUserid(this.getUserDetails().getUsername());
		pm5811.setIdRef(0L);
		pm5811.setIdsector(this.getUserDetails().getIdSector());
		pm5811.setFeccap(Calendar.getInstance().getTime());

		pm5811.setSemes(bModificar == true ? pm5811.getSemes() : Integer.valueOf(semestre));
		return pm5811;
	}

	/**
	 * Active txt.
	 *
	 * @param index the index
	 * @param camp the camp
	 */
	public void activeTxt(Integer index, String camp) {
		pm5811 = listPm5811.get(index);
		if (camp.equals("secpub")) {
			if (pm5811.getSecpub().equals(SI)) {
				bEstSup = Boolean.TRUE;
				bLblEstSup = Boolean.FALSE;
			} else if (pm5811.getSecpub().equals(NO)) {
				bEstSup = Boolean.FALSE;
				bLblEstSup = Boolean.TRUE;
				pm5811.setPubpuesto("");
				pm5811.setPublugar("");
				pm5811.setPubfecfin(null);
				pm5811.setPubfecini(null);

			}

		} else if (camp.equals("estsup")) {
			if (pm5811.getEstsup().equals(SI)) {
				bEstSup2 = Boolean.TRUE;
				bLblEstSup2 = Boolean.FALSE;
			} else if (pm5811.getEstsup().equals(NO)) {
				bEstSup2 = Boolean.FALSE;
				bLblEstSup2 = Boolean.TRUE;
				pm5811.setEspest("");
			}

		}
		listPm5811.set(index, pm5811);
	}

	/**
	 * Not empty.
	 *
	 * @param entity the entity
	 * @return the pm 5811
	 */
	public Pm5811 notEmpty(Pm5811 entity) {
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
