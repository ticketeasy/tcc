package br.com.ementas.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ementas.dao.DisciplinaDAO;
import br.com.ementas.dao.EixoFormacaoDAO;
import br.com.ementas.model.Disciplina;
import br.com.ementas.model.EixoFormacao;
import br.com.ementas.util.FacesUtil;

@ManagedBean
@SessionScoped
public class DisciplinaBean {
	
	@EJB
	DisciplinaDAO disciplinaDAO;
	
	@EJB
	private EixoFormacaoDAO eixoFormacaoDAO;
	
	private List<EixoFormacao> eixoformacoes;
	
	private List<Disciplina> disciplinas;
	
	private Disciplina disciplina;
	
	private EixoFormacao eixoFormacao;

	private Disciplina selectDisciplina;
	
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

	
	public String voltarCadDisciplina(){
		return "/member/cadastros/cadastrodisciplina.xhtml?faces-redirect=true";
	}
	
	public String novoDisciplina(){
		this.disciplina = new Disciplina();
		this.eixoFormacao = new EixoFormacao();
		return "/member/cadastros/editDisciplina.xhtml?faces-redirect=true";
	}
	
	public String voltar(){
		return "/member/index.xhtml?faces-redirect=true";
	}
	
	public String salvar(){
		
		if(disciplina.getNomeDisc().isEmpty()){
			FacesUtil.addMessageErro("Digitar o nome da disciplina!");
			return null;
		}
		
		if(this.eixoFormacao.getId() == null){
			FacesUtil.addMessageErro("Selecionar Eixo Formação!");
			return null;
		}
		
		try{
			disciplina.setAtivo("1");
			disciplina.setEixoFormacao(this.eixoFormacao);
			disciplinaDAO.salvar(disciplina);
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}
		
		return "/member/cadastros/cadastrodisciplina.xhtml?faces-redirect=true";
	}
	
	public String editar(){
		disciplina = disciplinaDAO.getById(disciplina.getId());
		this.eixoFormacao = disciplina.getEixoFormacao();
		return "/member/cadastros/editDisciplina.xhtml?faces-redirect=true";
	}
	
	public void excluir(){
		this.disciplina = disciplinaDAO.getById(disciplina.getId());
		this.disciplina.setAtivo("0");
		disciplinaDAO.salvar(this.disciplina);
	}

	public EixoFormacao getEixoFormacao() {
		return eixoFormacao;
	}

	public void setEixoFormacao(EixoFormacao eixoFormacao) {
		this.eixoFormacao = eixoFormacao;
	}

	public EixoFormacaoDAO getEixoFormacaoDAO() {
		return eixoFormacaoDAO;
	}

	public void setEixoFormacaoDAO(EixoFormacaoDAO eixoFormacaoDAO) {
		this.eixoFormacaoDAO = eixoFormacaoDAO;
	}

	public List<EixoFormacao> getEixoformacoes() {
		this.eixoformacoes = eixoFormacaoDAO.lstEixoFormacao();
		return eixoformacoes;
	}

	public void setEixoformacoes(List<EixoFormacao> eixoformacoes) {
		this.eixoformacoes = eixoformacoes;
	}
    
    public void selecionarEixo(){
    	
    }
    
    public void selectEixoFromDialog(EixoFormacao eixo) {
        this.eixoFormacao = eixo;
    }

	public Disciplina getSelectDisciplina() {
		return selectDisciplina;
	}

	public void setSelectDisciplina(Disciplina selectDisciplina) {
		this.selectDisciplina = selectDisciplina;
	}
    
    
}
