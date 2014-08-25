package br.com.sociofuracao.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the lugar database table.
 * 
 */
@Entity
@Table(name="LUGAR")
@NamedQuery(name="Lugar.findAll", query="SELECT l FROM Lugar l")
public class Lugar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private int fileira;

	private int numero;

	private String observacao;

	//bi-directional many-to-many association to Evento
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="lugar_evento"
		, joinColumns={
			@JoinColumn(name="LUGAR_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="EVENTO_ID")
			}
		)
	private List<Evento> eventos;

	//bi-directional many-to-one association to Setor
	@ManyToOne
	private Setor setor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	
	public Lugar() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFileira() {
		return this.fileira;
	}

	public void setFileira(int fileira) {
		this.fileira = fileira;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Setor getSetor() {
		return this.setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}