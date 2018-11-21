package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the FINALIDAD database table.
 * 
 */
@Entity
@NamedQuery(name="Finalidad.findAll", query="SELECT f FROM Finalidad f")
public class Finalidad implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;

	/** The clvdepg. */
	private String clvdepg;

	/** The cvefin. */
	private String cvefin;

	/** The cvefinal. */
	private int cvefinal;

	/** The cveind. */
	private String cveind;

	/** The cveprog. */
	private String cveprog;

	/** The cvetemas. */
	private String cvetemas;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The formula. */
	private String formula;

	/** The frecuencia. */
	private String frecuencia;

	/** The id ref. */
	@Column(name="ID_REF")
	private long idRef;

	/** The medio. */
	private String medio;

	/** The nombre. */
	private String nombre;

	/** The objetivo. */
	private String objetivo;

	/** The sectorid. */
	private int sectorid;

	/** The supuesto. */
	private String supuesto;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/** The usuario. */
	private String usuario;

	/**
	 * Instantiates a new finalidad.
	 */
	public Finalidad() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the clvdepg.
	 *
	 * @return the clvdepg
	 */
	public String getClvdepg() {
		return this.clvdepg;
	}

	/**
	 * Sets the clvdepg.
	 *
	 * @param clvdepg the new clvdepg
	 */
	public void setClvdepg(String clvdepg) {
		this.clvdepg = clvdepg;
	}

	/**
	 * Gets the cvefin.
	 *
	 * @return the cvefin
	 */
	public String getCvefin() {
		return this.cvefin;
	}

	/**
	 * Sets the cvefin.
	 *
	 * @param cvefin the new cvefin
	 */
	public void setCvefin(String cvefin) {
		this.cvefin = cvefin;
	}

	/**
	 * Gets the cvefinal.
	 *
	 * @return the cvefinal
	 */
	public int getCvefinal() {
		return this.cvefinal;
	}

	/**
	 * Sets the cvefinal.
	 *
	 * @param cvefinal the new cvefinal
	 */
	public void setCvefinal(int cvefinal) {
		this.cvefinal = cvefinal;
	}

	/**
	 * Gets the cveind.
	 *
	 * @return the cveind
	 */
	public String getCveind() {
		return this.cveind;
	}

	/**
	 * Sets the cveind.
	 *
	 * @param cveind the new cveind
	 */
	public void setCveind(String cveind) {
		this.cveind = cveind;
	}

	/**
	 * Gets the cveprog.
	 *
	 * @return the cveprog
	 */
	public String getCveprog() {
		return this.cveprog;
	}

	/**
	 * Sets the cveprog.
	 *
	 * @param cveprog the new cveprog
	 */
	public void setCveprog(String cveprog) {
		this.cveprog = cveprog;
	}

	/**
	 * Gets the cvetemas.
	 *
	 * @return the cvetemas
	 */
	public String getCvetemas() {
		return this.cvetemas;
	}

	/**
	 * Sets the cvetemas.
	 *
	 * @param cvetemas the new cvetemas
	 */
	public void setCvetemas(String cvetemas) {
		this.cvetemas = cvetemas;
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return this.feccap;
	}

	/**
	 * Sets the feccap.
	 *
	 * @param feccap the new feccap
	 */
	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	/**
	 * Gets the formula.
	 *
	 * @return the formula
	 */
	public String getFormula() {
		return this.formula;
	}

	/**
	 * Sets the formula.
	 *
	 * @param formula the new formula
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return this.frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia the new frecuencia
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public long getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(long idRef) {
		this.idRef = idRef;
	}

	/**
	 * Gets the medio.
	 *
	 * @return the medio
	 */
	public String getMedio() {
		return this.medio;
	}

	/**
	 * Sets the medio.
	 *
	 * @param medio the new medio
	 */
	public void setMedio(String medio) {
		this.medio = medio;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the objetivo.
	 *
	 * @return the objetivo
	 */
	public String getObjetivo() {
		return this.objetivo;
	}

	/**
	 * Sets the objetivo.
	 *
	 * @param objetivo the new objetivo
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	/**
	 * Gets the sectorid.
	 *
	 * @return the sectorid
	 */
	public int getSectorid() {
		return this.sectorid;
	}

	/**
	 * Sets the sectorid.
	 *
	 * @param sectorid the new sectorid
	 */
	public void setSectorid(int sectorid) {
		this.sectorid = sectorid;
	}

	/**
	 * Gets the supuesto.
	 *
	 * @return the supuesto
	 */
	public String getSupuesto() {
		return this.supuesto;
	}

	/**
	 * Sets the supuesto.
	 *
	 * @param supuesto the new supuesto
	 */
	public void setSupuesto(String supuesto) {
		this.supuesto = supuesto;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return this.userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}