package br.com.ementas.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ementas.dao.EixoFormacaoDAO;
import br.com.ementas.model.EixoFormacao;
import br.com.ementas.util.FacesUtil;

@ManagedBean
@SessionScoped
public class EixoFormacaoBean {
	
	@EJB
	private EixoFormacaoDAO eixoFormacaoDAO;
	
	private List<EixoFormacao> eixoformacoes;
	
	private EixoFormacao eixoFormacao;

	public List<EixoFormacao> getEixoformacoes() {
		this.eixoformacoes = eixoFormacaoDAO.lstEixoFormacao();
		return eixoformacoes;
	}

	public void setEixoformacoes(List<EixoFormacao> eixoformacoes) {
		this.eixoformacoes = eixoformacoes;
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
	
	public String voltar(){
		return "/member/index.xhtml?faces-redirect=true";
	}
	
	public String novoEixo(){
		eixoFormacao = new EixoFormacao();
		return "/member/cadastros/editEixoFormacoes.xhtml?faces-redirect=true";
	}
	
	public String voltarCadEixo(){
		return "/member/cadastros/cadastroeixoformacao.xhtml?faces-redirect=true";
	}
	
	public String salvar(){
		
		try{
			eixoFormacao.setAtivo("1");
			eixoFormacaoDAO.salvar(eixoFormacao);
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}
		
		return "/member/cadastros/cadastroeixoformacao.xhtml?faces-redirect=true";
	}
	
	public String editar(){
		eixoFormacao = eixoFormacaoDAO.getById(eixoFormacao.getId());
		return "/member/cadastros/editEixoFormacoes.xhtml?faces-redirect=true";
	}
	
	public void excluir(){
		this.eixoFormacao = eixoFormacaoDAO.getById(eixoFormacao.getId());
		this.eixoFormacao.setAtivo("0");
		eixoFormacaoDAO.salvar(this.eixoFormacao);
	}
	
	
}
