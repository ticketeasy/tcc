package br.com.ementas.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ementas.dao.PerfilDAO;
import br.com.ementas.dao.RegraDAO;
import br.com.ementas.model.Perfil;
import br.com.ementas.model.Regra;
import br.com.ementas.util.FacesUtil;

@ManagedBean
@SessionScoped
public class PerfilBean {
	
	@EJB
	private PerfilDAO perfilDAO;
	
	@EJB
	private RegraDAO regraDAO;
	
	private List<Perfil> perfis;
	
	private Perfil perfil;
	
	private String[] selectedRegras;

	public List<Perfil> getPerfis() {
		this.perfis = perfilDAO.lstPerfis();
		return this.perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public String voltar(){
		return "/member/index.xhtml?faces-redirect=true";
	}
	
	public String editar(){
		selectedRegras = null;
		if(perfil.getRegras() != null && perfil.getRegras().size() > 0){
			selectedRegras = new String[perfil.getRegras().size()];
			for(int i=0; i <= (perfil.getRegras().size() - 1); i++){
				selectedRegras[i] = perfil.getRegras().get(i).getNomeRegra();
			}
		}
		return "/admin/editPerfil.xhtml?faces-redirect=true";
	}
	
	public void excluir(){
		this.perfil = perfilDAO.getById(perfil.getIdPerfil());
		this.perfil.setAtivo("0");
		perfilDAO.salvar(this.perfil);
	}
	
	public String novoPerfil(){
		this.perfil = new Perfil();
		selectedRegras = null;
		return "/admin/editPerfil.xhtml?faces-redirect=true";
	}
	
	public String voltarCadPerfil(){
		return "/admin/perfil.xhtml?faces-redirect=true";
	}

	public String[] getSelectedRegras() {
		return selectedRegras;
	}

	public void setSelectedRegras(String[] selectedRegras) {
		this.selectedRegras = selectedRegras;
	}
	
	public String salvar(){
		
		if(perfil.getDescricao() == null){
			FacesUtil.addMessageErro("Descrever o Perfil!");
			return null;
		}
		
		
		try{
			perfil.setAtivo("1");
			
			List<Regra> regras = new ArrayList<Regra>();
			for(int i=0; i <= (selectedRegras.length - 1); i++){
				Regra regra = regraDAO.getNome(selectedRegras[i]);
				if(regra != null)
				  regras.add(regra);
			}
			perfil.setRegras(regras);
			perfilDAO.salvar(perfil);
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}
		
		return "/admin/perfil.xhtml?faces-redirect=true";
	}

	public RegraDAO getRegraDAO() {
		return regraDAO;
	}

	public void setRegraDAO(RegraDAO regraDAO) {
		this.regraDAO = regraDAO;
	}
	
	

}
