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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "REGRA")
public class Regra implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8773338776310208990L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="IDREGRA")
	private Long idRegra;
	
	@Column(name = "NOME_REGRA")
    private String nomeRegra;
    
	@ManyToMany( mappedBy = "regras", fetch=FetchType.EAGER )
	private List<Perfil> perfis;

	

	public Long getIdRegra() {
		return idRegra;
	}


	public void setIdRegra(Long idRegra) {
		this.idRegra = idRegra;
	}


	public String getNomeRegra() {
		return nomeRegra;
	}


	public void setNomeRegra(String nomeRegra) {
		this.nomeRegra = nomeRegra;
	}


	public List<Perfil> getPerfis() {
		return perfis;
	}


	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	


}
