package br.com.sociofuracao.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sociofuracao.dao.EstadioDAO;
import br.com.sociofuracao.dao.EventoDAO;
import br.com.sociofuracao.dao.LugarDAO;
import br.com.sociofuracao.form.EventoForm;
import br.com.sociofuracao.form.LugarForm;
import br.com.sociofuracao.model.Estadio;
import br.com.sociofuracao.model.Evento;
import br.com.sociofuracao.model.Lugar;
import br.com.sociofuracao.util.FacesUtil;


@ManagedBean
@SessionScoped
public class SocioEventoController {
	
	@EJB 
	private EventoDAO eventoDAO;
	
	@EJB
	private EstadioDAO estadioDAO;
	
	@EJB
	private LugarDAO lugarDAO;
	
	private List<Evento> eventos;
	
	private List<EventoForm> eventosForm;
	
	private EventoForm eventoForm;

	private Evento evento;
	
	private List<Lugar> lugares;
	
	SimpleDateFormat dfdata = new SimpleDateFormat("dd/MM/yyyy");

	SimpleDateFormat dfhora = new SimpleDateFormat("HH:mm:ss");
	
	private List<LugarForm> lugaresForm;
	
	private Map<LugarForm, Boolean> checked = new HashMap<LugarForm, Boolean>();
	
	private LugarForm lugarForm;
	
	private String termo;
	
	private String termoAceite;
		
	public List<Evento> getEventos() {
		eventos = eventoDAO.findAll();
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
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
	
	public String getFormtData(Date data){
		return dfdata.format(data);
	}

	
	
	public List<Lugar> getLugares() {
		lugares = LoginController.getSessionUser().getLugars();	
		return lugares;
	}

	public void setLugares(List<Lugar> lugares) {
		this.lugares = lugares;
	}

	public String disponibilizarIngresso(){
		checked = new HashMap<LugarForm, Boolean>();
		termo = new String();
		return "/member/eventos/registrarlugarevento.xhtml?faces-redirect=true";
	}

	
	public List<LugarForm> getLugaresForm() {
		return lugaresForm;
	}

	public void setLugaresForm(List<LugarForm> lugaresForm) {
		this.lugaresForm = lugaresForm;
	}

	public LugarForm getLugarForm() {
		return lugarForm;
	}

	public void setLugarForm(LugarForm lugarForm) {
		this.lugarForm = lugarForm;
	}

	public List<EventoForm> getEventosForm() {
		eventosForm = new ArrayList<EventoForm>();
		for(Evento ev : getEventos()){
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
			
			lugaresForm = new ArrayList<LugarForm>();
			
			if(getLugares() != null && getLugares().size() > 0){
				for(Lugar lugar : getLugares()){
					LugarForm lugarForm = new LugarForm();
					lugarForm.setEventos(lugar.getEventos());
					lugarForm.setFileira(lugar.getFileira());
					lugarForm.setId(lugar.getId());
					lugarForm.setNumero(lugar.getNumero());
					lugarForm.setObservacao(lugar.getObservacao());
					lugarForm.setSetor(lugar.getSetor());
					lugarForm.setUsuario(lugar.getUsuario());
					lugaresForm.add(lugarForm);
				}
			}
			
			for(Lugar l : ev.getLugars()){
				for(int i=0; i < lugaresForm.size(); i++){
					if(l.getId() == lugaresForm.get(i).getId()){
						lugaresForm.remove(i);
					}
				}
			}
			
			if(lugaresForm.size() > 0){
				evento.setLugaresForm(lugaresForm);
				eventosForm.add(evento);
			}
			
						
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

	public Map<LugarForm, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<LugarForm, Boolean> checked) {
		this.checked = checked;
	}
	
	public String registrar(){
		
		try{
		
		if(termo.isEmpty()){
			FacesUtil.addMessageErro("Aceitar o termo para poder disponibilizar o lugar!");
			return null;
		}else if(termo.contains("rejeitar")){
			return "/member/eventos/eventosdisponiveis.xhtml?faces-redirect=true";
		}else{
			
			List<LugarForm> lugares = new ArrayList<LugarForm>();
			for(LugarForm aKey: checked.keySet()){
				Boolean aValue = checked.get(aKey);
				if (aValue == Boolean.TRUE) {
					lugares.add(aKey);
				}
			}
			
			if(lugares.size() == 0){
				FacesUtil.addMessageErro("Selecione ao menos um lugar para o evento!");
				return null;
			}
			
			Evento evento = eventoDAO.findById(this.eventoForm.getId());
			
			if(evento != null){
				List<Evento> eventos = new ArrayList<Evento>();
				eventos.add(evento);
				for(LugarForm l : lugares){
					Lugar lugar = lugarDAO.findById(l.getId());
					lugar.getEventos().add(evento);
					lugarDAO.salvar(lugar);
				}
				
			}
			
		}
		
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getLocalizedMessage());
			return null;
		}
		
		return "/member/eventos/registrareventosucess.xhtml?faces-redirect=true";
	}
	
	public String voltar(){
		return "/member/eventos/eventosdisponiveis.xhtml?faces-redirect=true";
	}

	public String getTermo() {
		return termo;
	}

	public void setTermo(String termo) {
		this.termo = termo;
	}

	public String getTermoAceite() {
		termoAceite="	Declaramos que conheço e cumpro os “Requisitos para utilização do \n"+
	                "Contrato Padrão, constantes do Clube Atlético Paranense, bem como \n"+
				    "conheço e aceito de forma irrestrita o teor das cláusulas padrão que \n"+
	                "compõem o Contrato Padrão de disponibilizar tais lugares de meu \n"+
				    "dominio para o evento em questão.";
			 
		return termoAceite;
	}

	public void setTermoAceite(String termoAceite) {
		this.termoAceite = termoAceite;
	}
	
	
}
