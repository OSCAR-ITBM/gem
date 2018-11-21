package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the FTECNICASM database table.
 * 
 */
@Entity
@NamedQuery(name="Ftecnicasm.findAll", query="SELECT f FROM Ftecnicasm f")
public class Ftecnicasm  implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID\"")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** The alcane 1. */
	@Column(name="ALCANE_1")
	private BigDecimal alcane1;

	/** The alcane 2. */
	@Column(name="ALCANE_2")
	private BigDecimal alcane2;

	/** The alcane 3. */
	@Column(name="ALCANE_3")
	private BigDecimal alcane3;

	/** The alcane 4. */
	@Column(name="ALCANE_4")
	private BigDecimal alcane4;

	/** The alcporcen 1. */
	@Column(name="ALCPORCEN_1")
	private BigDecimal alcporcen1;

	/** The alcporcen 2. */
	@Column(name="ALCPORCEN_2")
	private BigDecimal alcporcen2;

	/** The alcporcen 3. */
	@Column(name="ALCPORCEN_3")
	private BigDecimal alcporcen3;

	/** The alcporcen 4. */
	@Column(name="ALCPORCEN_4")
	private BigDecimal alcporcen4;

	/** The ambito 1. */
	private String ambito1;

	/** The ambito 2. */
	private int ambito2;

	/** The clvdep. */
	private String clvdep;

	/** The clvfin. */
	private String clvfin;

	/** The clvfun. */
	private String clvfun;

	/** The cobertura. */
	private String cobertura;

	/** The cveind. */
	private String cveind;

	/** The cvetemas. */
	private String cvetemas;

	/** The descfac. */
	private String descfac;

	/** The descripcion. */
	private String descripcion;

	/** The dimension. */
	private String dimension;

	/** The ef 1. */
	@Column(name="EF_1")
	private BigDecimal ef1;

	/** The ef 2. */
	@Column(name="EF_2")
	private BigDecimal ef2;

	/** The ef 3. */
	@Column(name="EF_3")
	private BigDecimal ef3;

	/** The ef 4. */
	@Column(name="EF_4")
	private BigDecimal ef4;

	/** The elaboro. */
	private String elaboro;

	/** The evaluacion. */
	private String evaluacion;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The formula. */
	private String formula;

	/** The frecuencia. */
	private String frecuencia;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The idsector. */
	private Integer idsector;

	/** The interpretacion. */
	private String interpretacion;

	/** The metanuale 1. */
	@Column(name="METANUALE_1")
	private BigDecimal metanuale1;

	/** The metanuale 2. */
	@Column(name="METANUALE_2")
	private BigDecimal metanuale2;

	/** The metanuale 3. */
	@Column(name="METANUALE_3")
	private BigDecimal metanuale3;

	/** The metanuale 4. */
	@Column(name="METANUALE_4")
	private BigDecimal metanuale4;

	/** The nomind. */
	private String nomind;

	/** The nope. */
	private String nope;

	/** The objetivo. */
	private String objetivo;

	/** The pe. */
	private String pe;

	/** The porcalcane 1. */
	@Column(name="PORCALCANE_1")
	private BigDecimal porcalcane1;

	/** The porcalcane 2. */
	@Column(name="PORCALCANE_2")
	private BigDecimal porcalcane2;

	/** The porcalcane 3. */
	@Column(name="PORCALCANE_3")
	private BigDecimal porcalcane3;

	/** The porcalcane 4. */
	@Column(name="PORCALCANE_4")
	private BigDecimal porcalcane4;

	/** The porcproge 1. */
	@Column(name="PORCPROGE_1")
	private BigDecimal porcproge1;

	/** The porcproge 2. */
	@Column(name="PORCPROGE_2")
	private BigDecimal porcproge2;

	/** The porcproge 3. */
	@Column(name="PORCPROGE_3")
	private BigDecimal porcproge3;

	/** The porcproge 4. */
	@Column(name="PORCPROGE_4")
	private BigDecimal porcproge4;

	/** The proge 1. */
	@Column(name="PROGE_1")
	private BigDecimal proge1;

	/** The proge 2. */
	@Column(name="PROGE_2")
	private BigDecimal proge2;

	/** The proge 3. */
	@Column(name="PROGE_3")
	private BigDecimal proge3;

	/** The proge 4. */
	@Column(name="PROGE_4")
	private BigDecimal proge4;

	/** The progporcen 1. */
	@Column(name="PROGPORCEN_1")
	private BigDecimal progporcen1;

	/** The progporcen 2. */
	@Column(name="PROGPORCEN_2")
	private BigDecimal progporcen2;

	/** The progporcen 3. */
	@Column(name="PROGPORCEN_3")
	private BigDecimal progporcen3;

	/** The progporcen 4. */
	@Column(name="PROGPORCEN_4")
	private BigDecimal progporcen4;

	/** The semaforo 11. */
	@Column(name="SEMAFORO1_1")
	private String semaforo11;

	/** The semaforo 12. */
	@Column(name="SEMAFORO1_2")
	private String semaforo12;

	/** The semaforo 13. */
	@Column(name="SEMAFORO1_3")
	private String semaforo13;

	/** The semaforo 14. */
	@Column(name="SEMAFORO1_4")
	private String semaforo14;

	/** The semaforo 21. */
	@Column(name="SEMAFORO2_1")
	private String semaforo21;

	/** The semaforo 22. */
	@Column(name="SEMAFORO2_2")
	private String semaforo22;

	/** The semaforo 23. */
	@Column(name="SEMAFORO2_3")
	private String semaforo23;

	/** The semaforo 24. */
	@Column(name="SEMAFORO2_4")
	private String semaforo24;

	/** The tema. */
	private String tema;

	/** The userid. */
	private String userid;

	/** The usuario. */
	private String usuario;

	/** The valido. */
	private String valido;

	/**
	 * Instantiates a new ftecnicasm.
	 */
	public Ftecnicasm() {
	}

	/**
	 * Gets the alcane 1.
	 *
	 * @return the alcane 1
	 */
	public BigDecimal getAlcane1() {
		return this.alcane1;
	}

	/**
	 * Sets the alcane 1.
	 *
	 * @param alcane1 the new alcane 1
	 */
	public void setAlcane1(BigDecimal alcane1) {
		this.alcane1 = alcane1;
	}

	/**
	 * Gets the alcane 2.
	 *
	 * @return the alcane 2
	 */
	public BigDecimal getAlcane2() {
		return this.alcane2;
	}

	/**
	 * Sets the alcane 2.
	 *
	 * @param alcane2 the new alcane 2
	 */
	public void setAlcane2(BigDecimal alcane2) {
		this.alcane2 = alcane2;
	}

	/**
	 * Gets the alcane 3.
	 *
	 * @return the alcane 3
	 */
	public BigDecimal getAlcane3() {
		return this.alcane3;
	}

	/**
	 * Sets the alcane 3.
	 *
	 * @param alcane3 the new alcane 3
	 */
	public void setAlcane3(BigDecimal alcane3) {
		this.alcane3 = alcane3;
	}

	/**
	 * Gets the alcane 4.
	 *
	 * @return the alcane 4
	 */
	public BigDecimal getAlcane4() {
		return this.alcane4;
	}

	/**
	 * Sets the alcane 4.
	 *
	 * @param alcane4 the new alcane 4
	 */
	public void setAlcane4(BigDecimal alcane4) {
		this.alcane4 = alcane4;
	}

	/**
	 * Gets the alcporcen 1.
	 *
	 * @return the alcporcen 1
	 */
	public BigDecimal getAlcporcen1() {
		return this.alcporcen1;
	}

	/**
	 * Sets the alcporcen 1.
	 *
	 * @param alcporcen1 the new alcporcen 1
	 */
	public void setAlcporcen1(BigDecimal alcporcen1) {
		this.alcporcen1 = alcporcen1;
	}

	/**
	 * Gets the alcporcen 2.
	 *
	 * @return the alcporcen 2
	 */
	public BigDecimal getAlcporcen2() {
		return this.alcporcen2;
	}

	/**
	 * Sets the alcporcen 2.
	 *
	 * @param alcporcen2 the new alcporcen 2
	 */
	public void setAlcporcen2(BigDecimal alcporcen2) {
		this.alcporcen2 = alcporcen2;
	}

	/**
	 * Gets the alcporcen 3.
	 *
	 * @return the alcporcen 3
	 */
	public BigDecimal getAlcporcen3() {
		return this.alcporcen3;
	}

	/**
	 * Sets the alcporcen 3.
	 *
	 * @param alcporcen3 the new alcporcen 3
	 */
	public void setAlcporcen3(BigDecimal alcporcen3) {
		this.alcporcen3 = alcporcen3;
	}

	/**
	 * Gets the alcporcen 4.
	 *
	 * @return the alcporcen 4
	 */
	public BigDecimal getAlcporcen4() {
		return this.alcporcen4;
	}

	/**
	 * Sets the alcporcen 4.
	 *
	 * @param alcporcen4 the new alcporcen 4
	 */
	public void setAlcporcen4(BigDecimal alcporcen4) {
		this.alcporcen4 = alcporcen4;
	}

	/**
	 * Gets the ambito 1.
	 *
	 * @return the ambito 1
	 */
	public String getAmbito1() {
		return this.ambito1;
	}

	/**
	 * Sets the ambito 1.
	 *
	 * @param ambito1 the new ambito 1
	 */
	public void setAmbito1(String ambito1) {
		this.ambito1 = ambito1;
	}

	/**
	 * Gets the ambito 2.
	 *
	 * @return the ambito 2
	 */
	public int getAmbito2() {
		return this.ambito2;
	}

	/**
	 * Sets the ambito 2.
	 *
	 * @param ambito2 the new ambito 2
	 */
	public void setAmbito2(int ambito2) {
		this.ambito2 = ambito2;
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
	 * Gets the clvfin.
	 *
	 * @return the clvfin
	 */
	public String getClvfin() {
		return this.clvfin;
	}

	/**
	 * Sets the clvfin.
	 *
	 * @param clvfin the new clvfin
	 */
	public void setClvfin(String clvfin) {
		this.clvfin = clvfin;
	}

	/**
	 * Gets the clvfun.
	 *
	 * @return the clvfun
	 */
	public String getClvfun() {
		return this.clvfun;
	}

	/**
	 * Sets the clvfun.
	 *
	 * @param clvfun the new clvfun
	 */
	public void setClvfun(String clvfun) {
		this.clvfun = clvfun;
	}

	/**
	 * Gets the cobertura.
	 *
	 * @return the cobertura
	 */
	public String getCobertura() {
		return this.cobertura;
	}

	/**
	 * Sets the cobertura.
	 *
	 * @param cobertura the new cobertura
	 */
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
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
	 * Gets the descfac.
	 *
	 * @return the descfac
	 */
	public String getDescfac() {
		return this.descfac;
	}

	/**
	 * Sets the descfac.
	 *
	 * @param descfac the new descfac
	 */
	public void setDescfac(String descfac) {
		this.descfac = descfac;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the dimension.
	 *
	 * @return the dimension
	 */
	public String getDimension() {
		return this.dimension;
	}

	/**
	 * Sets the dimension.
	 *
	 * @param dimension the new dimension
	 */
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	/**
	 * Gets the ef 1.
	 *
	 * @return the ef 1
	 */
	public BigDecimal getEf1() {
		return this.ef1;
	}

	/**
	 * Sets the ef 1.
	 *
	 * @param ef1 the new ef 1
	 */
	public void setEf1(BigDecimal ef1) {
		this.ef1 = ef1;
	}

	/**
	 * Gets the ef 2.
	 *
	 * @return the ef 2
	 */
	public BigDecimal getEf2() {
		return this.ef2;
	}

	/**
	 * Sets the ef 2.
	 *
	 * @param ef2 the new ef 2
	 */
	public void setEf2(BigDecimal ef2) {
		this.ef2 = ef2;
	}

	/**
	 * Gets the ef 3.
	 *
	 * @return the ef 3
	 */
	public BigDecimal getEf3() {
		return this.ef3;
	}

	/**
	 * Sets the ef 3.
	 *
	 * @param ef3 the new ef 3
	 */
	public void setEf3(BigDecimal ef3) {
		this.ef3 = ef3;
	}

	/**
	 * Gets the ef 4.
	 *
	 * @return the ef 4
	 */
	public BigDecimal getEf4() {
		return this.ef4;
	}

	/**
	 * Sets the ef 4.
	 *
	 * @param ef4 the new ef 4
	 */
	public void setEf4(BigDecimal ef4) {
		this.ef4 = ef4;
	}

	/**
	 * Gets the elaboro.
	 *
	 * @return the elaboro
	 */
	public String getElaboro() {
		return this.elaboro;
	}

	/**
	 * Sets the elaboro.
	 *
	 * @param elaboro the new elaboro
	 */
	public void setElaboro(String elaboro) {
		this.elaboro = elaboro;
	}

	/**
	 * Gets the evaluacion.
	 *
	 * @return the evaluacion
	 */
	public String getEvaluacion() {
		return this.evaluacion;
	}

	/**
	 * Sets the evaluacion.
	 *
	 * @param evaluacion the new evaluacion
	 */
	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
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
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public Integer getIdsector() {
		return this.idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the interpretacion.
	 *
	 * @return the interpretacion
	 */
	public String getInterpretacion() {
		return this.interpretacion;
	}

	/**
	 * Sets the interpretacion.
	 *
	 * @param interpretacion the new interpretacion
	 */
	public void setInterpretacion(String interpretacion) {
		this.interpretacion = interpretacion;
	}

	/**
	 * Gets the metanuale 1.
	 *
	 * @return the metanuale 1
	 */
	public BigDecimal getMetanuale1() {
		return this.metanuale1;
	}

	/**
	 * Sets the metanuale 1.
	 *
	 * @param metanuale1 the new metanuale 1
	 */
	public void setMetanuale1(BigDecimal metanuale1) {
		this.metanuale1 = metanuale1;
	}

	/**
	 * Gets the metanuale 2.
	 *
	 * @return the metanuale 2
	 */
	public BigDecimal getMetanuale2() {
		return this.metanuale2;
	}

	/**
	 * Sets the metanuale 2.
	 *
	 * @param metanuale2 the new metanuale 2
	 */
	public void setMetanuale2(BigDecimal metanuale2) {
		this.metanuale2 = metanuale2;
	}

	/**
	 * Gets the metanuale 3.
	 *
	 * @return the metanuale 3
	 */
	public BigDecimal getMetanuale3() {
		return this.metanuale3;
	}

	/**
	 * Sets the metanuale 3.
	 *
	 * @param metanuale3 the new metanuale 3
	 */
	public void setMetanuale3(BigDecimal metanuale3) {
		this.metanuale3 = metanuale3;
	}

	/**
	 * Gets the metanuale 4.
	 *
	 * @return the metanuale 4
	 */
	public BigDecimal getMetanuale4() {
		return this.metanuale4;
	}

	/**
	 * Sets the metanuale 4.
	 *
	 * @param metanuale4 the new metanuale 4
	 */
	public void setMetanuale4(BigDecimal metanuale4) {
		this.metanuale4 = metanuale4;
	}

	/**
	 * Gets the nomind.
	 *
	 * @return the nomind
	 */
	public String getNomind() {
		return this.nomind;
	}

	/**
	 * Sets the nomind.
	 *
	 * @param nomind the new nomind
	 */
	public void setNomind(String nomind) {
		this.nomind = nomind;
	}

	/**
	 * Gets the nope.
	 *
	 * @return the nope
	 */
	public String getNope() {
		return this.nope;
	}

	/**
	 * Sets the nope.
	 *
	 * @param nope the new nope
	 */
	public void setNope(String nope) {
		this.nope = nope;
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
	 * Gets the pe.
	 *
	 * @return the pe
	 */
	public String getPe() {
		return this.pe;
	}

	/**
	 * Sets the pe.
	 *
	 * @param pe the new pe
	 */
	public void setPe(String pe) {
		this.pe = pe;
	}

	/**
	 * Gets the porcalcane 1.
	 *
	 * @return the porcalcane 1
	 */
	public BigDecimal getPorcalcane1() {
		return this.porcalcane1;
	}

	/**
	 * Sets the porcalcane 1.
	 *
	 * @param porcalcane1 the new porcalcane 1
	 */
	public void setPorcalcane1(BigDecimal porcalcane1) {
		this.porcalcane1 = porcalcane1;
	}

	/**
	 * Gets the porcalcane 2.
	 *
	 * @return the porcalcane 2
	 */
	public BigDecimal getPorcalcane2() {
		return this.porcalcane2;
	}

	/**
	 * Sets the porcalcane 2.
	 *
	 * @param porcalcane2 the new porcalcane 2
	 */
	public void setPorcalcane2(BigDecimal porcalcane2) {
		this.porcalcane2 = porcalcane2;
	}

	/**
	 * Gets the porcalcane 3.
	 *
	 * @return the porcalcane 3
	 */
	public BigDecimal getPorcalcane3() {
		return this.porcalcane3;
	}

	/**
	 * Sets the porcalcane 3.
	 *
	 * @param porcalcane3 the new porcalcane 3
	 */
	public void setPorcalcane3(BigDecimal porcalcane3) {
		this.porcalcane3 = porcalcane3;
	}

	/**
	 * Gets the porcalcane 4.
	 *
	 * @return the porcalcane 4
	 */
	public BigDecimal getPorcalcane4() {
		return this.porcalcane4;
	}

	/**
	 * Sets the porcalcane 4.
	 *
	 * @param porcalcane4 the new porcalcane 4
	 */
	public void setPorcalcane4(BigDecimal porcalcane4) {
		this.porcalcane4 = porcalcane4;
	}

	/**
	 * Gets the porcproge 1.
	 *
	 * @return the porcproge 1
	 */
	public BigDecimal getPorcproge1() {
		return this.porcproge1;
	}

	/**
	 * Sets the porcproge 1.
	 *
	 * @param porcproge1 the new porcproge 1
	 */
	public void setPorcproge1(BigDecimal porcproge1) {
		this.porcproge1 = porcproge1;
	}

	/**
	 * Gets the porcproge 2.
	 *
	 * @return the porcproge 2
	 */
	public BigDecimal getPorcproge2() {
		return this.porcproge2;
	}

	/**
	 * Sets the porcproge 2.
	 *
	 * @param porcproge2 the new porcproge 2
	 */
	public void setPorcproge2(BigDecimal porcproge2) {
		this.porcproge2 = porcproge2;
	}

	/**
	 * Gets the porcproge 3.
	 *
	 * @return the porcproge 3
	 */
	public BigDecimal getPorcproge3() {
		return this.porcproge3;
	}

	/**
	 * Sets the porcproge 3.
	 *
	 * @param porcproge3 the new porcproge 3
	 */
	public void setPorcproge3(BigDecimal porcproge3) {
		this.porcproge3 = porcproge3;
	}

	/**
	 * Gets the porcproge 4.
	 *
	 * @return the porcproge 4
	 */
	public BigDecimal getPorcproge4() {
		return this.porcproge4;
	}

	/**
	 * Sets the porcproge 4.
	 *
	 * @param porcproge4 the new porcproge 4
	 */
	public void setPorcproge4(BigDecimal porcproge4) {
		this.porcproge4 = porcproge4;
	}

	/**
	 * Gets the proge 1.
	 *
	 * @return the proge 1
	 */
	public BigDecimal getProge1() {
		return this.proge1;
	}

	/**
	 * Sets the proge 1.
	 *
	 * @param proge1 the new proge 1
	 */
	public void setProge1(BigDecimal proge1) {
		this.proge1 = proge1;
	}

	/**
	 * Gets the proge 2.
	 *
	 * @return the proge 2
	 */
	public BigDecimal getProge2() {
		return this.proge2;
	}

	/**
	 * Sets the proge 2.
	 *
	 * @param proge2 the new proge 2
	 */
	public void setProge2(BigDecimal proge2) {
		this.proge2 = proge2;
	}

	/**
	 * Gets the proge 3.
	 *
	 * @return the proge 3
	 */
	public BigDecimal getProge3() {
		return this.proge3;
	}

	/**
	 * Sets the proge 3.
	 *
	 * @param proge3 the new proge 3
	 */
	public void setProge3(BigDecimal proge3) {
		this.proge3 = proge3;
	}

	/**
	 * Gets the proge 4.
	 *
	 * @return the proge 4
	 */
	public BigDecimal getProge4() {
		return this.proge4;
	}

	/**
	 * Sets the proge 4.
	 *
	 * @param proge4 the new proge 4
	 */
	public void setProge4(BigDecimal proge4) {
		this.proge4 = proge4;
	}

	/**
	 * Gets the progporcen 1.
	 *
	 * @return the progporcen 1
	 */
	public BigDecimal getProgporcen1() {
		return this.progporcen1;
	}

	/**
	 * Sets the progporcen 1.
	 *
	 * @param progporcen1 the new progporcen 1
	 */
	public void setProgporcen1(BigDecimal progporcen1) {
		this.progporcen1 = progporcen1;
	}

	/**
	 * Gets the progporcen 2.
	 *
	 * @return the progporcen 2
	 */
	public BigDecimal getProgporcen2() {
		return this.progporcen2;
	}

	/**
	 * Sets the progporcen 2.
	 *
	 * @param progporcen2 the new progporcen 2
	 */
	public void setProgporcen2(BigDecimal progporcen2) {
		this.progporcen2 = progporcen2;
	}

	/**
	 * Gets the progporcen 3.
	 *
	 * @return the progporcen 3
	 */
	public BigDecimal getProgporcen3() {
		return this.progporcen3;
	}

	/**
	 * Sets the progporcen 3.
	 *
	 * @param progporcen3 the new progporcen 3
	 */
	public void setProgporcen3(BigDecimal progporcen3) {
		this.progporcen3 = progporcen3;
	}

	/**
	 * Gets the progporcen 4.
	 *
	 * @return the progporcen 4
	 */
	public BigDecimal getProgporcen4() {
		return this.progporcen4;
	}

	/**
	 * Sets the progporcen 4.
	 *
	 * @param progporcen4 the new progporcen 4
	 */
	public void setProgporcen4(BigDecimal progporcen4) {
		this.progporcen4 = progporcen4;
	}

	/**
	 * Gets the semaforo 11.
	 *
	 * @return the semaforo 11
	 */
	public String getSemaforo11() {
		return this.semaforo11;
	}

	/**
	 * Sets the semaforo 11.
	 *
	 * @param semaforo11 the new semaforo 11
	 */
	public void setSemaforo11(String semaforo11) {
		this.semaforo11 = semaforo11;
	}

	/**
	 * Gets the semaforo 12.
	 *
	 * @return the semaforo 12
	 */
	public String getSemaforo12() {
		return this.semaforo12;
	}

	/**
	 * Sets the semaforo 12.
	 *
	 * @param semaforo12 the new semaforo 12
	 */
	public void setSemaforo12(String semaforo12) {
		this.semaforo12 = semaforo12;
	}

	/**
	 * Gets the semaforo 13.
	 *
	 * @return the semaforo 13
	 */
	public String getSemaforo13() {
		return this.semaforo13;
	}

	/**
	 * Sets the semaforo 13.
	 *
	 * @param semaforo13 the new semaforo 13
	 */
	public void setSemaforo13(String semaforo13) {
		this.semaforo13 = semaforo13;
	}

	/**
	 * Gets the semaforo 14.
	 *
	 * @return the semaforo 14
	 */
	public String getSemaforo14() {
		return this.semaforo14;
	}

	/**
	 * Sets the semaforo 14.
	 *
	 * @param semaforo14 the new semaforo 14
	 */
	public void setSemaforo14(String semaforo14) {
		this.semaforo14 = semaforo14;
	}

	/**
	 * Gets the semaforo 21.
	 *
	 * @return the semaforo 21
	 */
	public String getSemaforo21() {
		return this.semaforo21;
	}

	/**
	 * Sets the semaforo 21.
	 *
	 * @param semaforo21 the new semaforo 21
	 */
	public void setSemaforo21(String semaforo21) {
		this.semaforo21 = semaforo21;
	}

	/**
	 * Gets the semaforo 22.
	 *
	 * @return the semaforo 22
	 */
	public String getSemaforo22() {
		return this.semaforo22;
	}

	/**
	 * Sets the semaforo 22.
	 *
	 * @param semaforo22 the new semaforo 22
	 */
	public void setSemaforo22(String semaforo22) {
		this.semaforo22 = semaforo22;
	}

	/**
	 * Gets the semaforo 23.
	 *
	 * @return the semaforo 23
	 */
	public String getSemaforo23() {
		return this.semaforo23;
	}

	/**
	 * Sets the semaforo 23.
	 *
	 * @param semaforo23 the new semaforo 23
	 */
	public void setSemaforo23(String semaforo23) {
		this.semaforo23 = semaforo23;
	}

	/**
	 * Gets the semaforo 24.
	 *
	 * @return the semaforo 24
	 */
	public String getSemaforo24() {
		return this.semaforo24;
	}

	/**
	 * Sets the semaforo 24.
	 *
	 * @param semaforo24 the new semaforo 24
	 */
	public void setSemaforo24(String semaforo24) {
		this.semaforo24 = semaforo24;
	}

	/**
	 * Gets the tema.
	 *
	 * @return the tema
	 */
	public String getTema() {
		return this.tema;
	}

	/**
	 * Sets the tema.
	 *
	 * @param tema the new tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
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

	/**
	 * Gets the valido.
	 *
	 * @return the valido
	 */
	public String getValido() {
		return this.valido;
	}

	/**
	 * Sets the valido.
	 *
	 * @param valido the new valido
	 */
	public void setValido(String valido) {
		this.valido = valido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}