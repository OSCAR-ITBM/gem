package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getDateSystem;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.gem.sistema.business.domain.Balancepre;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.BalancepreService;
import com.gem.sistema.web.datamodel.DataModelGeneric;

@ManagedBean(name = "balancePresupuestarioMB")
@ViewScoped
public class BalancePresupuestarioMB extends AbstractMB {

	private static final Integer TIPO_PERIODO = 3;
	
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";

	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";

	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:object:%1$s:trim');";

	private static final String UPDATE_OBJETS = "jQuery('#form1\\\\:hiddenUpdate').click();";

	private DataModelGeneric<Balancepre> dataModelBalancepre;

	private List<Balancepre> listBalancepre;

	private List<TcPeriodo> listTrimestres;

	private Integer trimestre;

	private Balancepre balancepreSelected;

	private Conctb conctb;

	private Integer currentIndex;
	
	private Integer oldTrimestre;
	
	private Long oldConsecutivo;

	private Boolean bEdition;

	private Boolean bModifcar;
	
	private Boolean bAdicionar;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{balancepreService}")
	private BalancepreService balancepreService;

	@PostConstruct
	public void init() {
		conctb = conctbRepository.findByIdsectorAndIdRef(this.getUserDetails().getIdSector(), 0);
		listTrimestres = periodoRepository.findByTipoPeriodo(TIPO_PERIODO);

		if (!CollectionUtils.isEmpty(listTrimestres)) {
			trimestre = listTrimestres.get(0).getPeriodo();
		}
		this.refreshData();
		this.bAdicionar = Boolean.FALSE;
	}

	public void refreshData() {
		listBalancepre = balancepreService.findAllByTrimestre(this.getUserDetails().getIdSector(),
				this.getUserDetails().getIdMunicipio(), conctb.getId(), trimestre);
		dataModelBalancepre = new DataModelGeneric<Balancepre>(listBalancepre);
		this.bAdicionar = Boolean.FALSE;
	}

	public void addElement() {

		this.bAdicionar = Boolean.TRUE;
		this.setbEdition(Boolean.TRUE);
		listBalancepre = balancepreService.findAllByTrimestre(this.getUserDetails().getIdSector(),
				this.getUserDetails().getIdMunicipio(), conctb.getId(), trimestre);
		Balancepre balancepreNew = new Balancepre();
		balancepreNew.setConcepto("");
		listBalancepre.add(balancepreNew);

		this.activateRowEdit(listBalancepre.size() - 1);
		RequestContext.getCurrentInstance()
				.execute("PF('balanceWdg').paginator.setPage(" + (this.getPage() - 1) + ");");

		if (listBalancepre.size() > 20) {
			Integer indexOf = this.getRowCurrent(listBalancepre.size());
			this.activateRowEdit(indexOf);

			RequestContext.getCurrentInstance().execute(String.format(FOCUS_BY_ROWID, (listBalancepre.size() - 1)));
		}

		dataModelBalancepre.setListT(listBalancepre);

	}

	public void onRowEdit(RowEditEvent event) {
		balancepreSelected = new Balancepre();
		balancepreSelected = (Balancepre) event.getObject();
		balancepreSelected.setIdSector(this.getUserDetails().getIdSector());
		balancepreSelected.setCapturo(this.getUserDetails().getUsername());
		balancepreSelected.setFeccap(getDateSystem());
		balancepreSelected.setIdAnio(conctb.getId());
		balancepreSelected.setIdRef(this.getUserDetails().getIdMunicipio());

		int lastIndex = dataModelBalancepre.getListT().size() - 1;

		if (bModifcar == Boolean.TRUE) {

			balancepreSelected = balancepreService.modify(balancepreSelected, oldTrimestre, oldConsecutivo);
			//if (balancepreSelected.isbGuardar() == Boolean.TRUE) {
			//}
		} else {
			balancepreSelected.setIndex(lastIndex);
			balancepreSelected = this.balancepreService.save(balancepreSelected);
		}
		if (CollectionUtils.isEmpty(dataModelBalancepre.getListT())) {
			this.activateRowEdit(dataModelBalancepre.getListT().size());
			dataModelBalancepre.getListT().add(lastIndex, balancepreSelected);
		} else {
			if (dataModelBalancepre.getListT().get(lastIndex).isbGuardar() == Boolean.TRUE) {
				listBalancepre = balancepreService.findAllByTrimestre(this.getUserDetails().getIdSector(),
						this.getUserDetails().getIdMunicipio(), conctb.getId(), trimestre);
				RequestContext.getCurrentInstance().execute(UPDATE_OBJETS);
			} else {
				if (bModifcar == Boolean.TRUE && balancepreSelected.isbGuardar() == Boolean.FALSE) {
					this.activateRowEdit(currentIndex);
				} else {
					this.activateRowEdit(lastIndex);
				}

				if (dataModelBalancepre.getListT().size() > 20) {
					int index = this.getRowCurrent(dataModelBalancepre.getListT().size());
					this.activateRowEdit(index);
				}
				dataModelBalancepre.getListT().set(lastIndex, balancepreSelected);
			}
		}
	}

	public void onInitRowEdit(final RowEditEvent event) {
		this.bAdicionar = Boolean.TRUE;
		balancepreSelected = (Balancepre) event.getObject();
		if (null != event.getObject()) {

			if (null != balancepreSelected.getId() && balancepreSelected.getId() != 0) {
				this.setbModifcar(Boolean.TRUE);
				oldTrimestre = this.balancepreSelected.getTrimestre();
				oldConsecutivo = this.balancepreSelected.getConsecutivo();

				for (int i = 0; i < dataModelBalancepre.getListT().size(); i++) {

					if (dataModelBalancepre.getListT().get(i).getId().equals(balancepreSelected.getId())) {
						currentIndex = i;
						break;
					}
				}
			} else {
				this.setbModifcar(Boolean.FALSE);
			}
		}
	}
	
	public void delete(Integer index) {
		balancepreSelected = this.dataModelBalancepre.getListT().get(index);

		if (null == balancepreSelected.getId() || balancepreSelected.getId() == 0) {
			listBalancepre.remove(balancepreSelected);
		} else {
			listBalancepre.remove(balancepreSelected);
			balancepreService.delete(balancepreSelected);
		}
	}

	public Integer getRowCurrent(Integer index) {
		Double rows = 20.0;
		Double size = index.doubleValue();
		Double row = (size % rows);
		Integer filas = row.intValue() - 1;
		if (filas < 0) {
			filas = 19;
		}

		return filas;
	}

	public int getPage() {
		int rows = listBalancepre.size();
		int de = 1;
		double maxRow = 20;
		double pageActua = (rows * de) / maxRow;
		String page = "" + Math.ceil(pageActua);
		return Integer.parseInt(this.getValue(page)[0]);
	}

	public void goToLastPage() {
		if (this.getbEdition() == Boolean.TRUE)
			if (this.dataModelBalancepre.getListT().size() <= 20) {
				this.activateRowEditOnInsert(this.dataModelBalancepre.getListT().size());

			} else {
				this.activateRowEdit(this.dataModelBalancepre.getListT().size() - 1);

			}
	}

	public void activateRowEditOnInsert(final int index) {

		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	public void activateRowEdit(final int index) {
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	public String[] getValue(String data) {
		return data.split("\\.");
	}

	public DataModelGeneric<Balancepre> getDataModelBalancepre() {
		return dataModelBalancepre;
	}

	public void setDataModelBalancepre(DataModelGeneric<Balancepre> dataModelBalancepre) {
		this.dataModelBalancepre = dataModelBalancepre;
	}

	public List<Balancepre> getListBalancepre() {
		return listBalancepre;
	}

	public void setListBalancepre(List<Balancepre> listBalancepre) {
		this.listBalancepre = listBalancepre;
	}

	public List<TcPeriodo> getListTrimestres() {
		return listTrimestres;
	}

	public void setListTrimestres(List<TcPeriodo> listTrimestres) {
		this.listTrimestres = listTrimestres;
	}

	public TcPeriodoRepositoy getPeriodoRepository() {
		return periodoRepository;
	}

	public void setPeriodoRepository(TcPeriodoRepositoy periodoRepository) {
		this.periodoRepository = periodoRepository;
	}

	public BalancepreService getBalancepreService() {
		return balancepreService;
	}

	public void setBalancepreService(BalancepreService balancepreService) {
		this.balancepreService = balancepreService;
	}

	public Balancepre getBalancepreSelected() {
		return balancepreSelected;
	}

	public void setBalancepreSelected(Balancepre balancepreSelected) {
		this.balancepreSelected = balancepreSelected;
	}

	public Integer getOldTrimestre() {
		return oldTrimestre;
	}

	public void setOldTrimestre(Integer oldTrimestre) {
		this.oldTrimestre = oldTrimestre;
	}

	public Long getOldConsecutivo() {
		return oldConsecutivo;
	}

	public void setOldConsecutivo(Long oldConsecutivo) {
		this.oldConsecutivo = oldConsecutivo;
	}

	public Boolean getbEdition() {
		return bEdition;
	}

	public void setbEdition(Boolean bEdition) {
		this.bEdition = bEdition;
	}

	public Boolean getbModifcar() {
		return bModifcar;
	}

	public void setbModifcar(Boolean bModifcar) {
		this.bModifcar = bModifcar;
	}

	public Boolean getbAdicionar() {
		return bAdicionar;
	}

	public void setbAdicionar(Boolean bAdicionar) {
		this.bAdicionar = bAdicionar;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

}
