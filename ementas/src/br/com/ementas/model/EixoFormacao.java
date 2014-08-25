package br.com.ementas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EIXODEFORMACAO")
public class EixoFormacao extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5251453203442494996L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="ID_EIXODEFORMACAO")
	private Long id;
	@Column(name="DESC_EIXO")
	private String descricao;
	@Column(name="ATIVO")
	private String ativo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	 
	 
}
