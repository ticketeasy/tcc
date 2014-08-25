package br.com.sociofuracao.beans;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import br.com.sociofuracao.dao.EstadioDAO;
import br.com.sociofuracao.dao.EventoDAO;
import br.com.sociofuracao.form.EventoForm;
import br.com.sociofuracao.model.Estadio;
import br.com.sociofuracao.model.Evento;
import br.com.sociofuracao.util.FacesUtil;

@ManagedBean
@SessionScoped
public class EventoController {

	@EJB
	private EventoDAO eventoDAO;

	@EJB
	private EstadioDAO estadioDAO;

	SimpleDateFormat dfdata = new SimpleDateFormat("dd/MM/yyyy");

	SimpleDateFormat dfhora = new SimpleDateFormat("HH:mm:ss");

	private List<Evento> eventos;

	private Evento evento;

	private List<EventoForm> eventosForm;

	private EventoForm eventoForm;

	private Map<Long, Boolean> checked = new HashMap<Long, Boolean>();

	private HashMap<String, Object> lstEstadio = new HashMap<String, Object>();

	private UploadedFile imagem;

	ExternalContext extContext = FacesContext.getCurrentInstance()
			.getExternalContext();

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<EventoForm> getEventosForm() {
		eventosForm = new ArrayList<EventoForm>();
		for (Evento ev : eventoDAO.getAll()) {
			EventoForm evento = new EventoForm();
			if (ev.getData() != null)
				evento.setData(dfdata.format(ev.getData()));
			if (ev.getDescricao() != null)
				evento.setDescricao(ev.getDescricao());
			if (ev.getHora() != null)
				evento.setHora(dfhora.format(ev.getHora()));
			evento.setId(ev.getId());

			if (ev.getResponsavel() != null)
				evento.setResponsavel(ev.getResponsavel());
			if (ev.getTipoevento() != null)
				evento.setTipoevento(ev.getTipoevento());

			Estadio estadio = estadioDAO.findById(ev.getEstadio().getId());
			if (estadio != null)
				evento.setIdEstadio(estadio.getId());
			

			eventosForm.add(evento);
		}
		return eventosForm;
	}

	public void setEventosForm(List<EventoForm> eventosForm) {
		this.eventosForm = eventosForm;
	}

	public EventoForm getEventoForm() {
		return eventoForm;
	}

	public void setEventoForm(EventoForm eventoForm) {
		this.eventoForm = eventoForm;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}

	public String excluir() {
		Boolean excluded = Boolean.FALSE;

		try {
			for (Long aKey : checked.keySet()) {
				Boolean aValue = checked.get(aKey);
				if (aValue == Boolean.TRUE) {
					Evento ev = eventoDAO.getById(aKey);
					if (ev != null) {
						eventoDAO.remove(ev);
						excluded = Boolean.TRUE;
					}
				}
			}
			
			if(!excluded){
				FacesUtil.addMessageErro("Selecionar um evento para eliminar! Nenhum registro excluido.");
				return null;
			}
		} catch (Exception e) {
			FacesUtil.addMessageErro(e.getLocalizedMessage());
			return null;
		}

		return null;
	}

	public String incluir() {
		eventoForm = new EventoForm();
		evento = new Evento();
		return "/member/cadastro/editEvento.xhtml?faces-redirect=true";
	}

	public String editar() {
		imagem = null;
		Evento evento = eventoDAO.getById(eventoForm.getId());
		if (evento != null) {
			eventoForm = new EventoForm();
			if (evento.getData() != null)
				eventoForm.setData(dfdata.format(evento.getData()));
			if (evento.getDescricao() != null)
				eventoForm.setDescricao(evento.getDescricao());
			if (evento.getEstadio() != null)
				eventoForm.setIdEstadio(evento.getEstadio().getId());
			if (evento.getHora() != null)
				eventoForm.setHora(dfhora.format(evento.getHora()));
			if (evento.getId() != null)
				eventoForm.setId(evento.getId());
			if (evento.getResponsavel() != null)
				eventoForm.setResponsavel(evento.getResponsavel());
			if (evento.getTipoevento() != null)
				eventoForm.setTipoevento(evento.getTipoevento());
			
		}

		return "/member/cadastro/editEvento.xhtml?faces-redirect=true";
	}

	public HashMap<String, Object> getLstEstadio() {
		lstEstadio = new HashMap<String, Object>();

		List<Estadio> estadios = estadioDAO.findAll();
		if (estadios != null) {
			for (Estadio e : estadios) {
				lstEstadio.put(e.getNome(), e.getId());
			}
		}

		return lstEstadio;
	}

	public void setLstEstadio(HashMap<String, Object> lstEstadio) {
		this.lstEstadio = lstEstadio;
	}

	public String salvar() {
		Boolean _bsalva = Boolean.TRUE;
		
		if (eventoForm.getId() != null)
			evento = eventoDAO.getById(eventoForm.getId());

		if (eventoForm.getData() == null) {
			FacesUtil.addMessageErro("Informar a data do evento!");
			_bsalva = Boolean.FALSE;
		}
		if (eventoForm.getHora() == null) {
			FacesUtil.addMessageErro("Informar a hora do evento!");
			_bsalva = Boolean.FALSE;
		}
		if (eventoForm.getDescricao() == null) {
			FacesUtil.addMessageErro("Informar a descrição do evento!");
			_bsalva = Boolean.FALSE;
		}

		if (eventoForm.getIdEstadio() <= 0) {
			FacesUtil.addMessageErro("Informar o Estadio do evento!");
			_bsalva = Boolean.FALSE;
		}

		if (eventoForm.getResponsavel() == null) {
			FacesUtil.addMessageErro("Informar o responsável do evento!");
			_bsalva = Boolean.FALSE;
		}

		if (!_bsalva) {
			return null;
		}
		try {

			if (evento == null)
				evento = new Evento();

			Estadio estadio = estadioDAO.findById(eventoForm.getIdEstadio());
			evento.setData(dfdata.parse(eventoForm.getData()));
			evento.setHora(Time.valueOf(eventoForm.getHora()));
			evento.setDescricao(eventoForm.getDescricao());
			evento.setResponsavel(eventoForm.getResponsavel());
			evento.setTipoevento(eventoForm.getTipoevento());
			evento.setEstadio(estadio);
			if (!imagem.getFileName().isEmpty()) {
				byte[] bytes = IOUtils.toByteArray(imagem.getInputstream());
				evento.setImagem(bytes);
			}
			eventoDAO.salvar(evento);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			FacesUtil.addMessageErro(e.getLocalizedMessage());
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			FacesUtil.addMessageErro(e.getLocalizedMessage());
			return null;
		}

		return "/member/cadastro/cadastrarevento.xhtml?faces-redirect=true";
	}

	public String voltarEvento() {
		return "/member/cadastro/cadastrarevento.xhtml?faces-redirect=true";

	}

	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public SimpleDateFormat getDfdata() {
		return dfdata;
	}

	public void setDfdata(SimpleDateFormat dfdata) {
		this.dfdata = dfdata;
	}

	public SimpleDateFormat getDfhora() {
		return dfhora;
	}

	public void setDfhora(SimpleDateFormat dfhora) {
		this.dfhora = dfhora;
	}

	public UploadedFile getImagem() {
		return imagem;
	}

	public void setImagem(UploadedFile imagem) {
		this.imagem = imagem;
	}
	
	

}
