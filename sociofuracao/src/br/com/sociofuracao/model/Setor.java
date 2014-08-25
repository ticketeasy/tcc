package br.com.sociofuracao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the setor database table.
 * 
 */
@Entity
@NamedQuery(name="Setor.findAll", query="SELECT s FROM Setor s")
public class Setor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int capacidade;

	private String descricao;

	private double valormaximo;

	private double valorminimo;

	//bi-directional many-to-one association to Lugar
	@OneToMany(mappedBy="setor")
	private List<Lugar> lugars;

	//bi-directional many-to-one association to Estadio
	@ManyToOne
	private Estadio estadio;

	public Setor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValormaximo() {
		return this.valormaximo;
	}

	public void setValormaximo(double valormaximo) {
		this.valormaximo = valormaximo;
	}

	public double getValorminimo() {
		return this.valorminimo;
	}

	public void setValorminimo(double valorminimo) {
		this.valorminimo = valorminimo;
	}

	public List<Lugar> getLugars() {
		return this.lugars;
	}

	public void setLugars(List<Lugar> lugars) {
		this.lugars = lugars;
	}

	public Lugar addLugar(Lugar lugar) {
		getLugars().add(lugar);
		lugar.setSetor(this);

		return lugar;
	}

	public Lugar removeLugar(Lugar lugar) {
		getLugars().remove(lugar);
		lugar.setSetor(null);

		return lugar;
	}

	public Estadio getEstadio() {
		return this.estadio;
	}

	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}

}