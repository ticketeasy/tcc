package br.com.sociofuracao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transacao database table.
 * 
 */
@Entity
@NamedQuery(name="Transacao.findAll", query="SELECT t FROM Transacao t")
public class Transacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datatrans;

	private int formapagto;

	private double valor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public Transacao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatatrans() {
		return this.datatrans;
	}

	public void setDatatrans(Date datatrans) {
		this.datatrans = datatrans;
	}

	public int getFormapagto() {
		return this.formapagto;
	}

	public void setFormapagto(int formapagto) {
		this.formapagto = formapagto;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}