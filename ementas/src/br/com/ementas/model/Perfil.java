/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ementas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "PERFIL")
public class Perfil extends AbstractEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="IDPERFIL")
    private Long idPerfil;
    
	@Column(name="DESCRICAO")
    private String descricao;
    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PERFIL_REGRA", joinColumns = { @JoinColumn(name = "IDPERFIL") }, 
			inverseJoinColumns = { @JoinColumn(name = "IDREGRA") })
    private List<Regra> regras;
    
    @Column(name="ATIVO")
    private String ativo;
    
    @ManyToMany( mappedBy = "perfis")
	private List<Usuario> usuario;

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
    
}
