
package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;
import com.roonin.utils.UtilDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.service.reportador.ExportaInformacionService;

// TODO: Auto-generated Javadoc
/**
 * The Class ExportaInformacionMB.
 */
@ManagedBean(name = "exportaInfo")
@ViewScoped
public class ExportaInformacionMB extends AbstractMB implements Serializable {

	/** The month. */
	private Integer month;

	/** The exporta informacion service. */
	@ManagedProperty("#{exportaInformacionService}")
	private ExportaInformacionService exportaInformacionService;

	/** The download. */
	private StreamedContent download;

	/** The temp. */
	private File temp;

	/**
	 * Sets the month.
	 *
	 * @param month the new month
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public Integer getMonth() {
		month = (month == null) ? 1 : month;
		return month;
	}

	/**
	 * Gets the exporta informacion service.
	 *
	 * @return the exporta informacion service
	 */
	public ExportaInformacionService getExportaInformacionService() {
		return exportaInformacionService;
	}

	/**
	 * Sets the exporta informacion service.
	 *
	 * @param exportaInformacionService the new exporta informacion service
	 */
	public void setExportaInformacionService(ExportaInformacionService exportaInformacionService) {
		this.exportaInformacionService = exportaInformacionService;
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
	 * Process.
	 */
	public void process() {
		try {
			temp = File.createTempFile("export", "info");
			BufferedWriter out = new BufferedWriter(new FileWriter(temp));
			exportaInformacionService.findRecordsForMonth(month, getIdSectorForCurrentUser(), (record) -> {
				List<String> row = new ArrayList<String>();
				row.add(record.get("clave"));
				row.add(record.get("renpol"));
				row.add(record.get("cuenta"));
				row.add(record.get("scta"));
				row.add(record.get("sscta"));
				row.add(record.get("ssscta"));
				row.add(record.get("sssscta"));

				row.add(getFormatedDateField(record.get("xfecha")));
				row.add(record.get("tippol"));
				row.add(record.get("conpol"));
				row.add(record.get("concep"));
				row.add(this.notDecimal( record.get("canpol")));
				row.add(this.notDecimal(record.get("canpolh")));
				try {
					out.write(StringUtils.join(row, "|") + "\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}

			});
			out.close();
			setDownload(new DefaultStreamedContent(new FileInputStream(temp), "text/plain", "sife.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the formated date field.
	 *
	 * @param field the field
	 * @return the formated date field
	 */
	private String getFormatedDateField(String field) {
		return UtilDate.getFormatedDateString("dd/MM/yy", UtilDate.convertStringToDate("yyyy-MM-dd", field));

	}

	/**
	 * Gets the rounded number field.
	 *
	 * @param field the field
	 * @return the rounded number field
	 */
	private String getRoundedNumberField(String field) {
		if (field == null)
			return "";
		try {
			BigDecimal bd = new BigDecimal(field);
			bd = bd.setScale(2, RoundingMode.CEILING);
			return bd.toString();
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * Clean file.
	 */
	public void cleanFile() {
		try {
			FileUtils.forceDelete(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Not decimal.
	 *
	 * @param num the num
	 * @return the string
	 */
	public String notDecimal (String num){
	    String ret = null;
	    BigDecimal numInt = new BigDecimal(num);
	    try {
	        ret = numInt.toBigIntegerExact().toString();
	    } catch (ArithmeticException e){
	    	numInt = numInt.setScale(2,BigDecimal.ROUND_UP); 
	        ret = numInt.toPlainString();
	    }
	    return ret;
	}
}
