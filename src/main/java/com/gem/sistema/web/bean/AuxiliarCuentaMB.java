
package com.gem.sistema.web.bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Maycta;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.AccountService;
import com.gem.sistema.business.service.catalogos.MayCtaService;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;

import java.math.BigDecimal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Formatter;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.leftPad;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import com.gem.sistema.web.security.model.GemUser;
import com.roonin.utils.UtilDate;

// TODO: Auto-generated Javadoc
/**
 * The Class AuxiliarCuentaMB.
 */
@ManagedBean(name = "auxiliarCuentaMB")
@ViewScoped
public class AuxiliarCuentaMB extends AbstractMB implements Serializable {


  /** The cta. */
  private String cta;
	
	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;

  /** The df. */
  private static DecimalFormat df = new DecimalFormat("###,###,###,##0.00");

  /** The download. */
  private StreamedContent download;
  
  /** The temp. */
  private File temp;

  /** The firmas repository. */
  @ManagedProperty("#{firmasRepository}")
  private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
  ConctbRepository conctbRepository;

  /** The may cta service. */
  @ManagedProperty("#{mayctaService}")
  MayCtaService mayCtaService;

  /** The account service. */
  @ManagedProperty("#{accountService}")
  private AccountService accountService;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

  /**
   * Sets the cta.
   *
   * @param cta the new cta
   */
  public void setCta(String cta){
    this.cta = cta;
  }

  /**
   * Gets the cta.
   *
   * @return the cta
   */
  public String getCta(){
    return cta;
  }

  /**
   * Gets the mes.
   *
   * @return the mes
   */
  public String getMes() {
    return StringUtils.defaultIfBlank(mes, "01");
  }

  /**
   * Sets the mes.
   *
   * @param mes the new mes
   */
  public void setMes(String mes) {
    this.mes = mes;
  }

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
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
   * Gets the may cta service.
   *
   * @return the may cta service
   */
  public MayCtaService getMayCtaService() {
    return mayCtaService;
  }

  /**
   * Sets the may cta service.
   *
   * @param mayCtaService the new may cta service
   */
  public void setMayCtaService(MayCtaService mayCtaService) {
    this.mayCtaService = mayCtaService;
  }

  /**
   * Gets the account service.
   *
   * @return the account service
   */
  public AccountService getAccountService(){
    return accountService;
  }

  /**
   * Sets the account service.
   *
   * @param accountService the new account service
   */
  public void setAccountService(AccountService accountService){
    this.accountService = accountService;
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
   * Prints the header.
   *
   * @param out the out
   * @param idSector the id sector
   */
  private void printHeader(BufferedWriter out,  Integer idSector){
    Firmas firmas= this.firmasRepository.findFirstByIdsector(idSector);
    writeToBuffer(out, firmas.getCampo1()==null?" ":StringUtils.center(firmas.getCampo1(), 146));
    writeToBuffer(out, firmas.getCampo2()==null?" ":StringUtils.center(firmas.getCampo2(), 146));
    Conctb conctb = conctbRepository.findFirstByIdsectorOrderByIdDesc(idSector);
    String monthS= getMes();
    try{
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Date monthDate=df.parse("01/"+monthS+"/"+conctb.getAnoemp());
      writeToBuffer(out,StringUtils.leftPad("Fecha de emisión:"+df.format(new Date()), 146));
      writeToBuffer(out,StringUtils.center("MOVIMIENTOS AL "+UtilDate.getLastDayOfMonth(monthDate) +" DE " +UtilDate.getMonthName(new Integer(mes)) + " "+ conctb.getAnoemp(),146));

      writeToBuffer(out,rightPad("CTA",9,"-")+"-"+rightPad("SCTA",14,"-")+"---"+rightPad("SSCTA",17,"-")+"-"+rightPad("SSSCTA",9,"-")+"-"+rightPad("SSSSCTA",10,"-")+"-"+rightPad("TITULO",60,"-")+ "-" +leftPad("Saldo final",18,"-"));
    }catch(ParseException e){
			e.printStackTrace();
		}
  }

  /**
   * Prints the account information.
   *
   * @param out the out
   * @param idSector the id sector
   * @return true, if successful
   */
  private boolean printAccountInformation(BufferedWriter out,  Integer idSector){
    BigDecimal finalBalance = new BigDecimal(0);
    Maycta maycta = this.mayCtaService.findFirstByCuenta(cta);
    System.out.println("Maycte = "+ maycta );
    if(maycta == null){
        displayInfoMessage("NO EXISTE LA CUENTA");
        return false;
    }
    List<Cuenta> cuentas = this.accountService.findAllByIdsectorAndCuenta(new Long(idSector), cta);
    if(cuentas != null){


      for(Cuenta cuenta: cuentas){
        if(maycta!=null && maycta.getNatcta()!= null && maycta.getNatcta().equals("D")){
          finalBalance = cuenta.getSalini().add(getChargesByMonth(cuenta,new Integer(mes))).subtract(getDepositsByMonth(cuenta,new Integer(mes)));
        }
        else{
          finalBalance = cuenta.getSalini().subtract(getChargesByMonth(cuenta,new Integer(mes))).add(getDepositsByMonth(cuenta,new Integer(mes)));
        if(finalBalance.compareTo(new BigDecimal(0)) >= 0)
          finalBalance = finalBalance.negate();

        }

        writeToBuffer(out,rightPad(cuenta.getCuenta(),9)+" "+rightPad(cuenta.getScuenta(), 14)+"   "+rightPad(cuenta.getSscuenta(), 17)+" "+rightPad(cuenta.getSsscuenta(),9)+" "+rightPad(cuenta.getSssscuenta(),10)+" "+rightPad(cuenta.getNomcta(),60)+" "+leftPad(df.format(finalBalance),18));
        finalBalance = new BigDecimal(0);
      }
    }
    return true;

  }

  /**
   * Gets the charges by month.
   *
   * @param cuenta the cuenta
   * @param month the month
   * @return the charges by month
   */
  BigDecimal getChargesByMonth(Cuenta cuenta, int month){
    BigDecimal totalCharges = new BigDecimal(0);
    List<BigDecimal> charges = cuenta.getCargosAsList();
    for(int i = 0; i <= month; i++){
      totalCharges = totalCharges.add((BigDecimal)charges.get(i));
    }
    return totalCharges;
  }
  
  /**
   * Gets the deposits by month.
   *
   * @param cuenta the cuenta
   * @param month the month
   * @return the deposits by month
   */
  BigDecimal getDepositsByMonth(Cuenta cuenta, int month){
    BigDecimal totalDeposits =  new BigDecimal(0);;
    List<BigDecimal> deposits = cuenta.getAbonosAsList();
    for(int i = 0; i <= month; i++){
      totalDeposits = totalDeposits.add((BigDecimal)deposits.get(i));
    }
    return totalDeposits;
  }


  /**
   * Write to buffer.
   *
   * @param out the out
   * @param row the row
   */
  private void writeToBuffer(BufferedWriter out , String row){
    try{
      out.write(row+"\r\n");
    }catch(IOException e){
      e.printStackTrace();
    }
  }

  /**
   * Process.
   */
  public void process(){
    df.setNegativePrefix("-");
    GemUser user = getUserDetails();
    Integer idSector = Integer.valueOf(user.getIdSector());
    try{
      temp = File.createTempFile("salfin","info");
      BufferedWriter out = new BufferedWriter
    (new OutputStreamWriter(new FileOutputStream(temp),"ISO-8859-1"));
       printHeader(out,idSector);
       boolean foundAccount =printAccountInformation(out,idSector);
      out.close();
      if(foundAccount)
        setDownload(new DefaultStreamedContent(new FileInputStream(temp), "text/plain", "salfin.txt"));
    }catch(IOException e){
      e.printStackTrace();
    }
  }


  /**
   * Inits the.
   */
  @PostConstruct
  public void init() {
    setListMes(tcMesRepository.findAll());
    if (!CollectionUtils.isEmpty(listMes)) {
      setMes(getListMes().get(0).getMes());
    }
  }

}
