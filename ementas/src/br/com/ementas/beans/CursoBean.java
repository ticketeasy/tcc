package br.com.ementas.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ementas.dao.CursoDAO;
import br.com.ementas.dao.DisciplinaDAO;
import br.com.ementas.model.Curso;
import br.com.ementas.model.Disciplina;
import br.com.ementas.util.FacesUtil;

@ManagedBean
@SessionScoped
public class CursoBean {
	
	@EJB
	private CursoDAO cursoDAO;
	
	@EJB
	private DisciplinaDAO disciplinaDAO;
	
	private List<Curso> cursos;
	
	private Curso curso;
	
	private List<Disciplina> disciplinas;
	
	private Disciplina disciplina;

	
	public CursoDAO getCursoDAO() {
		return cursoDAO;
	}

	public void setCursoDAO(CursoDAO cursoDAO) {
		this.cursoDAO = cursoDAO;
	}

	public List<Curso> getCursos() {
		this.cursos = cursoDAO.lstCurso();
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public String voltarCadCurso(){
		return "/member/cadastros/cadastrocurso.xhtml?faces-redirect=true";
	}
	
	public String novoCurso(){
		curso = new Curso();
		
		return "/member/cadastros/editCurso.xhtml?faces-redirect=true";
	}
	
	public String voltar(){
		return "/member/index.xhtml?faces-redirect=true";
	}
	
	public String salvar(){
		
		try{
			curso.setAtivo("1");
			cursoDAO.salvar(curso);
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}
		
		return "/member/cadastros/cadastrocurso.xhtml?faces-redirect=true";
	}
	
	public String editar(){
		curso = cursoDAO.getById(curso.getId());
		
		return "/member/cadastros/editCurso.xhtml?faces-redirect=true";
	}
	
	public void excluir(){
		this.curso = cursoDAO.getById(curso.getId());
		this.curso.setAtivo("0");
		cursoDAO.salvar(this.curso);
	}

	public DisciplinaDAO getDisciplinaDAO() {
		return disciplinaDAO;
	}

	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO) {
		this.disciplinaDAO = disciplinaDAO;
	}

	public List<Disciplina> getDisciplinas() {
		this.disciplinas = disciplinaDAO.lstDisciplina();
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void excluirDisciplina(){
		
		if(this.disciplina != null && this.disciplina.getId() != null){
			for(int i=0; i < this.curso.getDisciplinas().size(); i++){
				if(this.curso.getDisciplinas().get(i).getId().equals(this.disciplina.getId())){
					this.curso.getDisciplinas().remove(i);
				}
			}
		}
	}
	
	public void adicionaDisciplina(){
		
		Boolean exists = Boolean.FALSE;
		if(this.curso.getDisciplinas() != null){
			for(int i=0; i < this.curso.getDisciplinas().size(); i++){
				if(this.curso.getDisciplinas().get(i).getNomeDisc().equalsIgnoreCase(this.disciplina.getNomeDisc())){
					exists = Boolean.TRUE;
				}
			}
		}
		if(!exists){
		  if(disciplina.getId() != null){
			if(this.curso.getDisciplinas() == null){
				List<Disciplina> s = new ArrayList<Disciplina>();
				s.add(this.disciplina);
				this.curso.setDisciplinas(s);
			}else{
				this.curso.getDisciplinas().add(this.disciplina);
			}
			
		  }
		}
		
	}
	
	
}
