package br.com.sociofuracao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the estadio database table.
 * 
 */
@Entity
@NamedQuery(name="Estadio.findAll", query="SELECT e FROM Estadio e")
public class Estadio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String administrador;

	private String bairro;

	private int capacidade;

	private String complemento;

	private String endereco;

	@Lob
	private byte[] img;

	private String nome;

	private String nomefantasia;

	private String numero;

	private String telefone;

	//bi-directional many-to-many association to Clube
	@ManyToMany(mappedBy="estadios")
	private List<Clube> clubes;

	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="estadio", cascade=CascadeType.PERSIST)
	private List<Evento> eventos;

	//bi-directional many-to-one association to Setor
	@OneToMany(mappedBy="estadio")
	private List<Setor> setors;

	public Estadio() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdministrador() {
		return this.administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public byte[] getImg() {
		return this.img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomefantasia() {
		return this.nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Clube> getClubes() {
		return this.clubes;
	}

	public void setClubes(List<Clube> clubes) {
		this.clubes = clubes;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setEstadio(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setEstadio(null);

		return evento;
	}

	public List<Setor> getSetors() {
		return this.setors;
	}

	public void setSetors(List<Setor> setors) {
		this.setors = setors;
	}

	public Setor addSetor(Setor setor) {
		getSetors().add(setor);
		setor.setEstadio(this);

		return setor;
	}

	public Setor removeSetor(Setor setor) {
		getSetors().remove(setor);
		setor.setEstadio(null);

		return setor;
	}

}