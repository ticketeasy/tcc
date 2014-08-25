package br.com.ementas.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ementas.dao.DisciplinaDAO;
import br.com.ementas.dao.ProfessorDAO;
import br.com.ementas.model.Disciplina;
import br.com.ementas.model.Professor;
import br.com.ementas.util.FacesUtil;

@ManagedBean
@SessionScoped
public class ProfessorBean {
	
	@EJB
	private ProfessorDAO professorDAO;
	
	@EJB
	private DisciplinaDAO disciplinaDAO;
	
	private List<Professor> professores;
	
	private Professor professor;
	
	private List<Disciplina> disciplinas;
	
	private Disciplina disciplina;

	public ProfessorDAO getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}

	public List<Professor> getProfessores() {
		this.professores = professorDAO.lstProfessores();
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public String voltarCadProfessor(){
		return "/member/cadastros/cadastroprofessor.xhtml?faces-redirect=true";
	}
	
	public String novoProfessor(){
		this.professor = new Professor();
		return "/member/cadastros/editProfessor.xhtml?faces-redirect=true";
	}
	
	public String voltar(){
		return "/member/index.xhtml?faces-redirect=true";
	}
	
	public String salvar(){
		
		if(professor.getNome().isEmpty()){
			FacesUtil.addMessageErro("Digitar o nome do Professor!");
			return null;
		}
		
		try{
			professor.setAtivo("1");
			professorDAO.salvar(professor);
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}
		
		return "/member/cadastros/cadastroprofessor.xhtml?faces-redirect=true";
	}
	
	public String editar(){
		professor = professorDAO.getById(professor.getId());
		
		return "/member/cadastros/editProfessor.xhtml?faces-redirect=true";
	}
	
	public void excluir(){
		this.professor = professorDAO.getById(professor.getId());
		this.professor.setAtivo("0");
		professorDAO.salvar(this.professor);
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
	
	public void adicionaDisciplina(){
		
		Boolean exists = Boolean.FALSE;
		if(this.professor.getDisciplinas() != null){
			for(int i=0; i < this.professor.getDisciplinas().size(); i++){
				if(this.professor.getDisciplinas().get(i).getNomeDisc().equalsIgnoreCase(this.disciplina.getNomeDisc())){
					exists = Boolean.TRUE;
				}
			}
		}
		if(!exists){
	  	  if(disciplina.getId() != null){
			if(this.professor.getDisciplinas() == null){
				List<Disciplina> s = new ArrayList<Disciplina>();
				s.add(this.disciplina);
				this.professor.setDisciplinas(s);
			}else{
				this.professor.getDisciplinas().add(this.disciplina);
			}
			
		  }
		}
		
	}
	
	public void excluirDisciplina(){
		if(this.disciplina != null && this.disciplina.getId() != null){
			for(int i=0; i < this.professor.getDisciplinas().size(); i++){
				if(this.professor.getDisciplinas().get(i).getId().equals(this.disciplina.getId())){
					this.professor.getDisciplinas().remove(i);
				}
			}
		}
		
	}
}
