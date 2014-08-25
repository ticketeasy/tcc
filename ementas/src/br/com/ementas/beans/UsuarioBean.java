package br.com.ementas.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ementas.dao.PerfilDAO;
import br.com.ementas.dao.UsuarioDAO;
import br.com.ementas.model.Perfil;
import br.com.ementas.model.Usuario;
import br.com.ementas.util.FacesUtil;

@ManagedBean
@SessionScoped
public class UsuarioBean {
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	private List<Usuario> usuarios;
	
	@EJB
	private PerfilDAO perfilDAO;
	
	private Usuario usuario;
	
	private List<Perfil> lstPerfis;
	
	private List<Perfil> perfis;

	private Perfil perfil;
	

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String editar(){
		perfis = usuario.getPerfis();
		
		return "/admin/editUsuario.xhtml?faces-redirect=true";
	}
	
	public void excluir(){
		this.usuario = usuarioDAO.getById(usuario.getId());
		this.usuario.setAtivo("0");
		usuarioDAO.salvar(this.usuario);
	}
	
	public String novoUsuario(){
		this.usuario = new Usuario();
		this.perfis = new ArrayList<Perfil>();
		return "/admin/editUsuario.xhtml?faces-redirect=true";
	}
	
	public String salvar(){
		
		if(usuario.getNome() == null){
			FacesUtil.addMessageErro("Informar nome do usuario!");
			return null;
		}
		
		if(usuario.getUsuario() == null){
			FacesUtil.addMessageErro("Informar o usuario!");
			return null;
		}
		
		if(usuario.getSenha() == null){
			FacesUtil.addMessageErro("Informar senha!");
			return null;
		}
		
		try{
			usuario.setAtivo("1");
			usuario.setPerfis(perfis);
			usuarioDAO.salvar(usuario);
		}catch(Exception e){
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}
		
		return "/admin/usuario.xhtml?faces-redirect=true";
	}
	
	public String voltar(){
		return "/member/index.xhtml?faces-redirect=true";
	}
	
	public String voltarCadUsuario(){
		return "/admin/usuario.xhtml?faces-redirect=true";
	}

	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void adicionaPerfil(){
		Boolean exists = Boolean.FALSE;
		
		for(int i=0; i < this.perfis.size(); i++){
			if(this.perfis.get(i).getDescricao().equalsIgnoreCase(perfil.getDescricao())){
				exists = Boolean.TRUE;
			}
		}
		
		if(!exists){
		  if(perfil.getIdPerfil() != null){
			if(this.perfis == null){
				perfis = new ArrayList<Perfil>();
				perfis.add(perfil);
			}else{
				perfis.add(perfil);
			}
				
		  }
		}
		
	}
	
	public List<Perfil> getLstPerfis() {
		this.lstPerfis = perfilDAO.lstPerfis();
		return lstPerfis;
	}

	public void setLstPerfis(List<Perfil> lstPerfis) {
		this.lstPerfis = lstPerfis;
	}

	public void excluirPerfil(){
		if(this.perfil != null && this.perfil.getIdPerfil() != null){
			for(int i=0; i < this.perfis.size(); i++){
				if(this.perfis.get(i).getIdPerfil().equals(this.perfil.getIdPerfil())){
					this.perfis.remove(i);
				}
			}
			
		}
	}

	
}
