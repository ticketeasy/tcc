package br.com.sociofuracao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clube database table.
 * 
 */
@Entity
@NamedQuery(name="Clube.findAll", query="SELECT c FROM Clube c")
public class Clube implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cnpj;

	private String bairro;

	@Column(name="CIDADE_NOME")
	private String cidadeNome;

	private String complemento;

	private String endereco;

	@Lob
	private byte[] escudo;

	private String facebook;

	private String instagran;

	private String nome;

	private String numero;

	private String telefone;

	private String twitter;

	//bi-directional many-to-many association to Estadio
	@ManyToMany
	@JoinTable(
		name="clube_estadio"
		, joinColumns={
			@JoinColumn(name="CNPJ_CLUBE")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_ESTADIO")
			}
		)
	private List<Estadio> estadios;

	public Clube() {
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidadeNome() {
		return this.cidadeNome;
	}

	public void setCidadeNome(String cidadeNome) {
		this.cidadeNome = cidadeNome;
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

	public byte[] getEscudo() {
		return this.escudo;
	}

	public void setEscudo(byte[] escudo) {
		this.escudo = escudo;
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

	public List<Estadio> getEstadios() {
		return this.estadios;
	}

	public void setEstadios(List<Estadio> estadios) {
		this.estadios = estadios;
	}

}