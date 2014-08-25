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
@Table(name="PROFESSOR")
public class Professor extends AbstractEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1862230226148913835L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="ID_PROFESSOR")
	private Long id;
	@Column(name="NOME_PROF")
	private String nome;
	@Column(name="EMAIL_PROF")
	private String email;
	@Column(name="ATIVO")
	private String ativo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DISCIPLINA_PROFESSOR", joinColumns = { @JoinColumn(name = "PROFESSOR_ID_PROFESSOR") }, 
			inverseJoinColumns = { @JoinColumn(name = "DISCIPLINA_ID_DISCIPLINA") })
	private List<Disciplina> disciplinas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
