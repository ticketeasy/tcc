package br.com.ementas.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DISCIPLINA")
public class Disciplina extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="ID_DISCIPLINA")
	private Long id;
	@Column(name="NOME_DISC")
	private String nomeDisc;
	@Column(name="ATIVO")
	private String ativo;
	@ManyToOne
    @JoinColumn(name="EIXODEFORMACAO_ID_EIXODEFORMACAO")
	private EixoFormacao eixoFormacao;
	
	@ManyToMany( mappedBy = "disciplinas", fetch=FetchType.EAGER )
	private List<Curso> cursos;
	
	@ManyToMany( mappedBy = "disciplinas", fetch=FetchType.EAGER )
	private List<Professor> professores;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDisc() {
		return nomeDisc;
	}

	public void setNomeDisc(String nomeDisc) {
		this.nomeDisc = nomeDisc;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public EixoFormacao getEixoFormacao() {
		return eixoFormacao;
	}

	public void setEixoFormacao(EixoFormacao eixoFormacao) {
		this.eixoFormacao = eixoFormacao;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	
}
