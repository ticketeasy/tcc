package br.com.ementas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PLANODEENSINO")
public class PlanoEnsino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="ID_PLANODEENSINO")
	private Long id;
	@Column(name="EMENTA")
	private String ementa;
	@Column(name="CARGAHORARIA")
	private String cargaHoraria;
	@Column(name="COMPETENCIA1")
	private String competencia1;
	@Column(name="COMPETENCIA2")
	private String competencia2;
	@Column(name="COMPETENCIA3")
	private String competencia3;
	@Column(name="COMPETENCIA4")
	private String competencia4;
	@Column(name="COMPETENCIA5")
	private String competencia5;
	@Column(name="CONTEUDO_EMENTA")
	private String conteudoEmenta;
	@Column(name="REFERENCIABAS1")
	private String referenciabas1;
	@Column(name="REFERENCIABAS2")
	private String referenciabas2;
	@Column(name="REFERENCIABAS3")
	private String referenciabas3;
	@Column(name="REFERENCIACOMPL1")
	private String referenciacompl1;
	@Column(name="REFERENCIACOMPL2")
	private String referenciacompl2;
	@Column(name="ATIVO")
	private String ativo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "CURSO_ID_CURSO")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name = "DISCIPLINA_ID_DISCIPLINA")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name = "DISCIPLINA_EIXODEFORMACAO_ID_EIXODEFORMACAO")
	private EixoFormacao eixoFormacao;
	
	@ManyToOne
	@JoinColumn(name = "PROFESSOR_ID_PROFESSOR")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "PROFESSOR_ID_PROFESSOR1")
	private Professor professor1;
	
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID_USUARIO")
	private Usuario usuario;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmenta() {
		return ementa;
	}
	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}
	public String getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public String getCompetencia1() {
		return competencia1;
	}
	public void setCompetencia1(String competencia1) {
		this.competencia1 = competencia1;
	}
	public String getCompetencia2() {
		return competencia2;
	}
	public void setCompetencia2(String competencia2) {
		this.competencia2 = competencia2;
	}
	public String getCompetencia3() {
		return competencia3;
	}
	public void setCompetencia3(String competencia3) {
		this.competencia3 = competencia3;
	}
	public String getCompetencia4() {
		return competencia4;
	}
	public void setCompetencia4(String competencia4) {
		this.competencia4 = competencia4;
	}
	public String getCompetencia5() {
		return competencia5;
	}
	public void setCompetencia5(String competencia5) {
		this.competencia5 = competencia5;
	}
	public String getConteudoEmenta() {
		return conteudoEmenta;
	}
	public void setConteudoEmenta(String conteudoEmenta) {
		this.conteudoEmenta = conteudoEmenta;
	}
	public String getReferenciabas1() {
		return referenciabas1;
	}
	public void setReferenciabas1(String referenciabas1) {
		this.referenciabas1 = referenciabas1;
	}
	public String getReferenciabas2() {
		return referenciabas2;
	}
	public void setReferenciabas2(String referenciabas2) {
		this.referenciabas2 = referenciabas2;
	}
	public String getReferenciabas3() {
		return referenciabas3;
	}
	public void setReferenciabas3(String referenciabas3) {
		this.referenciabas3 = referenciabas3;
	}
	public String getReferenciacompl1() {
		return referenciacompl1;
	}
	public void setReferenciacompl1(String referenciacompl1) {
		this.referenciacompl1 = referenciacompl1;
	}
	public String getReferenciacompl2() {
		return referenciacompl2;
	}
	public void setReferenciacompl2(String referenciacompl2) {
		this.referenciacompl2 = referenciacompl2;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public EixoFormacao getEixoFormacao() {
		return eixoFormacao;
	}
	public void setEixoFormacao(EixoFormacao eixoFormacao) {
		this.eixoFormacao = eixoFormacao;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Professor getProfessor1() {
		return professor1;
	}
	public void setProfessor1(Professor professor1) {
		this.professor1 = professor1;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
