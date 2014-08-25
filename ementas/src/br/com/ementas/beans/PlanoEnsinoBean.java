package br.com.ementas.beans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.ementas.dao.CursoDAO;
import br.com.ementas.dao.PlanoEnsinoDAO;
import br.com.ementas.dao.UsuarioDAO;
import br.com.ementas.model.Curso;
import br.com.ementas.model.Disciplina;
import br.com.ementas.model.EixoFormacao;
import br.com.ementas.model.PlanoEnsino;
import br.com.ementas.model.Professor;
import br.com.ementas.model.Usuario;
import br.com.ementas.util.FacesUtil;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@ManagedBean
@SessionScoped
public class PlanoEnsinoBean {
	
	@EJB
	private PlanoEnsinoDAO planoEnsinoDAO;
	
	@EJB
	private CursoDAO cursoDAO;
	
	@EJB
	private UsuarioDAO usuarioDAO;

	private List<PlanoEnsino> planoEnsinos;
	
	private PlanoEnsino planoEnsino;
	
	private List<Curso> cursos;

	private Curso curso;
	
	private List<Disciplina> disciplinas;
	
	private List<Professor> professores;
	
	private List<EixoFormacao> eixoFormacoes;
	
	private Professor professor;
	
	private EixoFormacao eixoFormacao;
	
	private List<Usuario> usuarios;
	
	private StreamedContent streamedContent;
	
	private String verificaInsert;
	
	public PlanoEnsinoDAO getPlanoEnsinoDAO() {
		return planoEnsinoDAO;
	}

	public void setPlanoEnsinoDAO(PlanoEnsinoDAO planoEnsinoDAO) {
		this.planoEnsinoDAO = planoEnsinoDAO;
	}

	public List<PlanoEnsino> getPlanoEnsinos() {
		this.planoEnsinos = planoEnsinoDAO.lstPlanoEnsino();
		return planoEnsinos;
	}

	public void setPlanoEnsinos(List<PlanoEnsino> planoEnsinos) {
		this.planoEnsinos = planoEnsinoDAO.lstPlanoEnsino();
		this.planoEnsinos = planoEnsinos;
	}

	public PlanoEnsino getPlanoEnsino() {
		return planoEnsino;
	}

	public void setPlanoEnsino(PlanoEnsino planoEnsino) {
		this.planoEnsino = planoEnsino;
	}
	
	public String voltar(){
		return "/member/index.xhtml?faces-redirect=true";
	}
	
	public String novoPlano(){
		this.verificaInsert = "false";
		this.planoEnsino = new PlanoEnsino();
		this.cursos = cursoDAO.lstCurso();
		this.disciplinas = new ArrayList<Disciplina>();
		return "/member/cadastros/editPlanoEnsino.xhtml?faces-redirect=true";
	}
	
	public String editar(){
		this.verificaInsert = "true";
		this.eixoFormacoes = new ArrayList<EixoFormacao>();
		this.professores = new ArrayList<Professor>();
		this.disciplinas = new ArrayList<Disciplina>();
		this.planoEnsino = planoEnsinoDAO.getById(this.planoEnsino.getId());
		this.disciplinas = this.planoEnsino.getCurso().getDisciplinas();
		this.eixoFormacoes.add(this.planoEnsino.getDisciplina().getEixoFormacao());
		this.professores = this.planoEnsino.getDisciplina().getProfessores();
		return "/member/cadastros/editPlanoEnsino.xhtml?faces-redirect=true";
	}

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

	public void handleCurso(){
		this.eixoFormacoes = new ArrayList<EixoFormacao>();
		this.professores = new ArrayList<Professor>();
		this.disciplinas = new ArrayList<Disciplina>();
		try{
			if(planoEnsino.getCurso() != null){
				this.disciplinas = planoEnsino.getCurso().getDisciplinas();
			}
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
		}
	}
	
	public void handleDados(){
		this.eixoFormacoes = new ArrayList<EixoFormacao>();
		this.professores = new ArrayList<Professor>();
		//this.lstEixo = new HashMap<String,Long>();
		try{
			if(planoEnsino.getDisciplina() != null){
			   this.eixoFormacoes.add(planoEnsino.getDisciplina().getEixoFormacao());
			   this.professores = planoEnsino.getDisciplina().getProfessores();
			} 
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
		}
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<EixoFormacao> getEixoFormacoes() {
		return eixoFormacoes;
	}

	public void setEixoFormacoes(List<EixoFormacao> eixoFormacoes) {
		this.eixoFormacoes = eixoFormacoes;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public EixoFormacao getEixoFormacao() {
		return eixoFormacao;
	}

	public void setEixoFormacao(EixoFormacao eixoFormacao) {
		this.eixoFormacao = eixoFormacao;
	}
	
	public String voltarCadPlanoEnsino(){
		return "/member/cadastros/cadastroplanoensino.xhtml?faces-redirect=true";
	}
	
	public String salvar(){
		
		if(planoEnsino.getCurso() == null){
			FacesUtil.addMessageErro("Selecionar um curso!");
			return null;
		}
		
		if(planoEnsino.getDisciplina() == null){
			FacesUtil.addMessageErro("Selecionar Disciplina!");
			return null;
		}
		
		if(planoEnsino.getProfessor() == null){
			FacesUtil.addMessageErro("Selecionar o professor!");
			return null;
		}
		
		if(planoEnsino.getProfessor1() == null){
			FacesUtil.addMessageErro("Selecionar o professor Auxiliar!");
			return null;
		}
		
		if(planoEnsino.getEixoFormacao() == null){
			FacesUtil.addMessageErro("Selecionar Eixo Formação!");
			return null;
		}
		
		try{
			planoEnsino.setAtivo("1");
			
			planoEnsinoDAO.salvar(planoEnsino);
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}
		
		return "/member/cadastros/cadastroplanoensino.xhtml?faces-redirect=true";
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<Usuario> getUsuarios() {
		this.usuarios = usuarioDAO.lstUsuarios();
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@SuppressWarnings("unused")
	public String  imprimir() {
	
        try {
        	
        	Document document = new Document();
        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	
        	
			PdfWriter writer = PdfWriter.getInstance(document, baos);
        	document.open();
        	
        	Font fontTitle = new Font(Font.TIMES_ROMAN, 20, Font.BOLDITALIC);
        	Font subTitle = new Font(Font.TIMES_ROMAN, 16, Font.ITALIC);
        	Font textFont = new Font(Font.TIMES_ROMAN, 10, Font.TIMES_ROMAN);
        	
        	Paragraph title = new Paragraph("PLANO DE ENSINO", fontTitle);
        	title.setAlignment(Element.ALIGN_CENTER);
        	document.add(title);
        	
        	Paragraph disciplina = new Paragraph("CURSO - "+this.planoEnsino.getCurso().getNomeCurso(), subTitle);
        	disciplina.setAlignment(Element.ALIGN_CENTER);
        	disciplina.add(Chunk.NEWLINE);
        	disciplina.add("DISCIPLINA - "+this.planoEnsino.getDisciplina().getNomeDisc());
        	document.add(disciplina);
        	
        	Paragraph texto = new Paragraph();
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Professor: "+this.planoEnsino.getProfessor().getNome(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Professor Auxiliar: "+this.planoEnsino.getProfessor1().getNome(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Usuario: "+this.planoEnsino.getUsuario().getNome(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Ementa: "+this.planoEnsino.getEmenta(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Competencia 1: "+this.planoEnsino.getCompetencia1(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Competencia 2: "+this.planoEnsino.getCompetencia2(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Competencia 3: "+this.planoEnsino.getCompetencia3(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Competencia 4: "+this.planoEnsino.getCompetencia4(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Competencia 5: "+this.planoEnsino.getCompetencia5(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Conteudo Ementa: "+this.planoEnsino.getConteudoEmenta(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Referencia 1: "+this.planoEnsino.getReferenciabas1(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Referencia Cpl 1: "+this.planoEnsino.getReferenciacompl1(),textFont));
        	texto.add(Chunk.NEWLINE);
        	texto.add(new Paragraph("Referencia Cpl 1: "+this.planoEnsino.getReferenciacompl2(),textFont));
        	texto.add(Chunk.NEWLINE);
        	
        	document.add(texto);
        	
        	document.close();
            
            streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(baos.toByteArray()), "application/pdf");
            
            return "/member/cadastros/viewplanoensino.xhtml?faces-redirect=true";
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
	}

	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

	public String getVerificaInsert() {
		return verificaInsert;
	}

	public void setVerificaInsert(String verificaInsert) {
		this.verificaInsert = verificaInsert;
	}

	
	
	
}
