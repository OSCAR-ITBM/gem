package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PP_CAPTURA database table.
 * 
 */
@Entity
@Table(name="PP_CAPTURA")
@NamedQuery(name="PpCaptura.findAll", query="SELECT p FROM PpCaptura p")
public class PpCaptura implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name="\"ID\"")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The anio cap. */
	@Column(name="ANIO_CAP")
	private Integer anioCap;

	/** The campo 0. */
	private String campo0;

	/** The campo 1. */
	private String campo1;

	/** The campo 2. */
	private String campo2;

	/** The campo 3. */
	private String campo3;

	/** The campo 4. */
	private String campo4;

	/** The campo 5. */
	private String campo5;

	/** The clavedep. */
	private Integer clavedep;

	/** The clvdep. */
	private String clvdep;

	/** The clvfuen. */
	private String clvfuen;

	/** The clvnep. */
	private String clvnep;

	/** The clvreg. */
	private String clvreg;

	/** The desc proy. */
	@Column(name="DESC_PROY")
	private String descProy;

	/** The dimensionc. */
	private String dimensionc;

	/** The dimensiond. */
	private BigDecimal dimensiond;

	/** The est proy. */
	@Column(name="EST_PROY")
	private String estProy;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The num ver. */
	@Column(name="NUM_VER")
	private Integer numVer;

	/** The obj proy. */
	@Column(name="OBJ_PROY")
	private String objProy;

	/** The sectorid. */
	private Integer sectorid;

	/** The tipind. */
	private String tipind;

	/** The userid. */
	@Column(name="USERID")
	private String userid;

	/**
	 * Instantiates a new pp captura.
	 */
	public PpCaptura() {
	}


	/**
	 * Gets the anio cap.
	 *
	 * @return the anio cap
	 */
	public Integer getAnioCap() {
		return this.anioCap;
	}

	/**
	 * Sets the anio cap.
	 *
	 * @param anioCap the new anio cap
	 */
	public void setAnioCap(Integer anioCap) {
		this.anioCap = anioCap;
	}

	/**
	 * Gets the campo 0.
	 *
	 * @return the campo 0
	 */
	public String getCampo0() {
		return this.campo0;
	}

	/**
	 * Sets the campo 0.
	 *
	 * @param campo0 the new campo 0
	 */
	public void setCampo0(String campo0) {
		this.campo0 = campo0;
	}

	/**
	 * Gets the campo 1.
	 *
	 * @return the campo 1
	 */
	public String getCampo1() {
		return this.campo1;
	}

	/**
	 * Sets the campo 1.
	 *
	 * @param campo1 the new campo 1
	 */
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	/**
	 * Gets the campo 2.
	 *
	 * @return the campo 2
	 */
	public String getCampo2() {
		return this.campo2;
	}

	/**
	 * Sets the campo 2.
	 *
	 * @param campo2 the new campo 2
	 */
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	/**
	 * Gets the campo 3.
	 *
	 * @return the campo 3
	 */
	public String getCampo3() {
		return this.campo3;
	}

	/**
	 * Sets the campo 3.
	 *
	 * @param campo3 the new campo 3
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	/**
	 * Gets the campo 4.
	 *
	 * @return the campo 4
	 */
	public String getCampo4() {
		return this.campo4;
	}

	/**
	 * Sets the campo 4.
	 *
	 * @param campo4 the new campo 4
	 */
	public void setCampo4(String campo4) {
		this.campo4 = campo4;
	}

	/**
	 * Gets the campo 5.
	 *
	 * @return the campo 5
	 */
	public String getCampo5() {
		return this.campo5;
	}

	/**
	 * Sets the campo 5.
	 *
	 * @param campo5 the new campo 5
	 */
	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}

	/**
	 * Gets the clavedep.
	 *
	 * @return the clavedep
	 */
	public Integer getClavedep() {
		return this.clavedep;
	}

	/**
	 * Sets the clavedep.
	 *
	 * @param clavedep the new clavedep
	 */
	public void setClavedep(Integer clavedep) {
		this.clavedep = clavedep;
	}

	/**
	 * Gets the clvdep.
	 *
	 * @return the clvdep
	 */
	public String getClvdep() {
		return this.clvdep;
	}

	/**
	 * Sets the clvdep.
	 *
	 * @param clvdep the new clvdep
	 */
	public void setClvdep(String clvdep) {
		this.clvdep = clvdep;
	}

	/**
	 * Gets the clvfuen.
	 *
	 * @return the clvfuen
	 */
	public String getClvfuen() {
		return this.clvfuen;
	}

	/**
	 * Sets the clvfuen.
	 *
	 * @param clvfuen the new clvfuen
	 */
	public void setClvfuen(String clvfuen) {
		this.clvfuen = clvfuen;
	}

	/**
	 * Gets the clvnep.
	 *
	 * @return the clvnep
	 */
	public String getClvnep() {
		return this.clvnep;
	}

	/**
	 * Sets the clvnep.
	 *
	 * @param clvnep the new clvnep
	 */
	public void setClvnep(String clvnep) {
		this.clvnep = clvnep;
	}

	/**
	 * Gets the clvreg.
	 *
	 * @return the clvreg
	 */
	public String getClvreg() {
		return this.clvreg;
	}

	/**
	 * Sets the clvreg.
	 *
	 * @param clvreg the new clvreg
	 */
	public void setClvreg(String clvreg) {
		this.clvreg = clvreg;
	}

	/**
	 * Gets the desc proy.
	 *
	 * @return the desc proy
	 */
	public String getDescProy() {
		return this.descProy;
	}

	/**
	 * Sets the desc proy.
	 *
	 * @param descProy the new desc proy
	 */
	public void setDescProy(String descProy) {
		this.descProy = descProy;
	}

	/**
	 * Gets the dimensionc.
	 *
	 * @return the dimensionc
	 */
	public String getDimensionc() {
		return this.dimensionc;
	}

	/**
	 * Sets the dimensionc.
	 *
	 * @param dimensionc the new dimensionc
	 */
	public void setDimensionc(String dimensionc) {
		this.dimensionc = dimensionc;
	}

	/**
	 * Gets the dimensiond.
	 *
	 * @return the dimensiond
	 */
	public BigDecimal getDimensiond() {
		return this.dimensiond;
	}

	/**
	 * Sets the dimensiond.
	 *
	 * @param dimensiond the new dimensiond
	 */
	public void setDimensiond(BigDecimal dimensiond) {
		this.dimensiond = dimensiond;
	}

	/**
	 * Gets the est proy.
	 *
	 * @return the est proy
	 */
	public String getEstProy() {
		return this.estProy;
	}

	/**
	 * Sets the est proy.
	 *
	 * @param estProy the new est proy
	 */
	public void setEstProy(String estProy) {
		this.estProy = estProy;
	}

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return this.idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	/**
	 * Gets the num ver.
	 *
	 * @return the num ver
	 */
	public Integer getNumVer() {
		return this.numVer;
	}

	/**
	 * Sets the num ver.
	 *
	 * @param numVer the new num ver
	 */
	public void setNumVer(Integer numVer) {
		this.numVer = numVer;
	}

	/**
	 * Gets the obj proy.
	 *
	 * @return the obj proy
	 */
	public String getObjProy() {
		return this.objProy;
	}

	/**
	 * Sets the obj proy.
	 *
	 * @param objProy the new obj proy
	 */
	public void setObjProy(String objProy) {
		this.objProy = objProy;
	}

	/**
	 * Gets the sectorid.
	 *
	 * @return the sectorid
	 */
	public Integer getSectorid() {
		return this.sectorid;
	}

	/**
	 * Sets the sectorid.
	 *
	 * @param sectorid the new sectorid
	 */
	public void setSectorid(Integer sectorid) {
		this.sectorid = sectorid;
	}

	/**
	 * Gets the tipind.
	 *
	 * @return the tipind
	 */
	public String getTipind() {
		return this.tipind;
	}

	/**
	 * Sets the tipind.
	 *
	 * @param tipind the new tipind
	 */
	public void setTipind(String tipind) {
		this.tipind = tipind;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

}