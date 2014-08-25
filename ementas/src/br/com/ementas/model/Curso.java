package br.com.ementas.model;

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

@Entity
@Table(name="CURSO")
public class Curso extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="ID_CURSO")
	private Long id;
	@Column(name="NOME_CURSO")
	private String nomeCurso;
	@Column(name="DESCRICAO")
	private String descricao;
	@Column(name="ATIVO")
	private String ativo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CURSO_DISCIPLINA", joinColumns = { @JoinColumn(name = "CURSO_ID_CURSO") }, 
			inverseJoinColumns = { @JoinColumn(name = "DISCIPLINA_ID_DISCIPLINA") })
	private List<Disciplina> disciplinas;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
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
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	
	
}
