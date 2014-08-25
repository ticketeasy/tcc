package br.com.sociofuracao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String bairro;

	private String celular;

	private String cep;

	private String complemento;

	private String cpf;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataacesso;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datacadastro;

	@Temporal(TemporalType.DATE)
	private Date datanascimento;

	private String endereco;

	private String estado;

	private String facebook;

	private String instagran;

	private String nome;

	private String numero;

	private String pais;

	private String rg;

	private String senha;

	private String sexo;

	private String telefone;

	private String twitter;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoacesso;

	//bi-directional many-to-one association to Lugar
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
	private List<Lugar> lugars;

	//bi-directional many-to-one association to Transacao
	@OneToMany(mappedBy="usuario")
	private List<Transacao> transacaos;

	//bi-directional many-to-one association to Cidade
	@ManyToOne
	private Cidade cidade;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne
	@JoinColumn(name="TIPOSOCIO")
	private TipoUsuario tipousuario;

	public Usuario() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataacesso() {
		return this.dataacesso;
	}

	public void setDataacesso(Date dataacesso) {
		this.dataacesso = dataacesso;
	}

	public Date getDatacadastro() {
		return this.datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}

	public Date getDatanascimento() {
		return this.datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagran() {
		return this.instagran;
	}

	public void setInstagran(String instagran) {
		this.instagran = instagran;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public Date getUltimoacesso() {
		return this.ultimoacesso;
	}

	public void setUltimoacesso(Date ultimoacesso) {
		this.ultimoacesso = ultimoacesso;
	}

	public List<Lugar> getLugars() {
		return this.lugars;
	}

	public void setLugars(List<Lugar> lugars) {
		this.lugars = lugars;
	}

	public Lugar addLugar(Lugar lugar) {
		getLugars().add(lugar);
		lugar.setUsuario(this);

		return lugar;
	}

	public Lugar removeLugar(Lugar lugar) {
		getLugars().remove(lugar);
		lugar.setUsuario(null);

		return lugar;
	}

	public List<Transacao> getTransacaos() {
		return this.transacaos;
	}

	public void setTransacaos(List<Transacao> transacaos) {
		this.transacaos = transacaos;
	}

	public Transacao addTransacao(Transacao transacao) {
		getTransacaos().add(transacao);
		transacao.setUsuario(this);

		return transacao;
	}

	public Transacao removeTransacao(Transacao transacao) {
		getTransacaos().remove(transacao);
		transacao.setUsuario(null);

		return transacao;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public TipoUsuario getTipousuario() {
		return this.tipousuario;
	}

	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}

}