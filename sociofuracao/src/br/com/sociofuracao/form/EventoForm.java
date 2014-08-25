package br.com.sociofuracao.form;

import java.util.List;

import br.com.sociofuracao.model.Estadio;


public class EventoForm {
	private Long id;

	private String data;

	private String descricao;

	private String hora;

	private String responsavel;

	private String tipoevento;
	
	private Estadio estadio;
	
	private Long idEstadio;
	
	private byte[] imagem;
	
	private List<LugarForm> lugaresForm;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTipoevento() {
		return tipoevento;
	}

	public void setTipoevento(String tipoevento) {
		this.tipoevento = tipoevento;
	}

	
	

	public Estadio getEstadio() {
		return estadio;
	}

	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}

	public Long getIdEstadio() {
		return idEstadio;
	}

	public void setIdEstadio(Long idEstadio) {
		this.idEstadio = idEstadio;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public List<LugarForm> getLugaresForm() {
		return lugaresForm;
	}

	public void setLugaresForm(List<LugarForm> lugaresForm) {
		this.lugaresForm = lugaresForm;
	}

	
}
