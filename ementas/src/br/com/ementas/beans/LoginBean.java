package br.com.ementas.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.com.ementas.dao.PerfilDAO;
import br.com.ementas.dao.RegraDAO;
import br.com.ementas.dao.UsuarioDAO;
import br.com.ementas.model.Perfil;
import br.com.ementas.model.Regra;
import br.com.ementas.model.Usuario;
import br.com.ementas.util.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginBean {

	@EJB
	private UsuarioDAO usuarioDAO;
	
	@EJB
	private PerfilDAO perfilDAO;
	
	@EJB
	private RegraDAO regraDAO;

	public final static String BEAN_NAME = "loginBean";

	private static Usuario usuario;

	private String username;

	private String password;

	private Boolean active = Boolean.FALSE;
	
	private String verificaEdit;
	
	private String verificaVisualizar;
	
	private String verificaInserir;
	
	private String verificaSalvar;
	
	private String verificaEliminar;
	
	private String verificaAdmin;
	

	public LoginBean() {
		
		usuario = new Usuario();
		
	}

	public String autenticado() throws ServletException {
		
		

		setActive(Boolean.FALSE);
		Usuario user = usuarioDAO.findByUsuario(this.username, this.password);

		if (user != null) {
			usuario = new Usuario();
			setUsuario(user);
			setActive(Boolean.TRUE);
			verificarAdmin();
			verificarAcesso();
			return "/member/index.xhtml?faces-redirect=true";
		} else {
			setActive(Boolean.FALSE);
			FacesUtil.addMessageErro("Erro ao logar!");
			usuario = null;
			logout();
		}
		return null;
	}

	public String logout() throws ServletException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		LoginBean.usuario = null;
		request.logout();
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String cadPerfil(){
		verificarAdmin();
		return "/admin/perfil.xhtml?faces-redirect=true";
	}
	
	public String cadUsuario(){
		return "/admin/usuario.xhtml?faces-redirect=true";
	}
	
	public String cadEixosFormacoes(){
		return "/member/cadastros/cadastroeixoformacao.xhtml?faces-redirect=true";
	}
	
	public void verificarAdmin(){
		verificaAdmin = "true";
		for(Perfil p : LoginBean.usuario.getPerfis()){
			Perfil perfil = perfilDAO.getById(p.getIdPerfil());
			if(perfil.getDescricao().equalsIgnoreCase("ADMINISTRADOR")){
				verificaAdmin = "false";
			}
		}
	}
	
	public void verificarAcesso(){
		verificaEdit="true";
		for(Perfil p : LoginBean.usuario.getPerfis()){
			Perfil perfil = perfilDAO.findById(p.getIdPerfil());
			for(Regra r : perfil.getRegras()){
				if(r.getNomeRegra().equalsIgnoreCase("EDITAR")){
					this.verificaEdit="false";
				}
			}
		}
		
		verificaVisualizar="true";
		for(Perfil p : LoginBean.usuario.getPerfis()){
			Perfil perfil = perfilDAO.findById(p.getIdPerfil());
			for(Regra r : perfil.getRegras()){
				if(r.getNomeRegra().equalsIgnoreCase("VISUALIZAR")){
					this.verificaVisualizar="false";
				}
			}
		}
		
		verificaInserir="true";
		for(Perfil p : LoginBean.usuario.getPerfis()){
			Perfil perfil = perfilDAO.findById(p.getIdPerfil());
			for(Regra r : perfil.getRegras()){
				if(r.getNomeRegra().equalsIgnoreCase("INSERIR")){
					this.verificaInserir="false";
				}
			}
		}
		
		verificaSalvar="true";
		for(Perfil p : LoginBean.usuario.getPerfis()){
			Perfil perfil = perfilDAO.findById(p.getIdPerfil());
			for(Regra r : perfil.getRegras()){
				if(r.getNomeRegra().equalsIgnoreCase("SALVAR")){
					this.verificaSalvar="false";
				}
			}
		}
		
		verificaEliminar="true";
		for(Perfil p : LoginBean.usuario.getPerfis()){
			Perfil perfil = perfilDAO.findById(p.getIdPerfil());
			for(Regra r : perfil.getRegras()){
				if(r.getNomeRegra().equalsIgnoreCase("ELIMINAR")){
					this.verificaEliminar="false";
				}
			}
		}
		
	}
	
	public String cadCursos(){
		
		return "/member/cadastros/cadastrocurso.xhtml?faces-redirect=true";
	}
	
	public String cadDisciplinas(){
		
		return "/member/cadastros/cadastrodisciplina.xhtml?faces-redirect=true";
	}
	
	public String cadProfessores(){
		
		return "/member/cadastros/cadastroprofessor.xhtml?faces-redirect=true";
	}
	
	public String cadPlanoEnsinos(){
		
		return "/member/cadastros/cadastroplanoensino.xhtml?faces-redirect=true";
	}

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		LoginBean.usuario = usuario;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getVerificaEdit() {
		return this.verificaEdit;
	}

	public void setVerificaEdit(String verificaEdit) {
		this.verificaEdit = verificaEdit;
	}

	public String getVerificaVisualizar() {
		return this.verificaVisualizar;
	}

	public void setVerificaVisualizar(String verificaVisualizar) {
		this.verificaVisualizar = verificaVisualizar;
	}

	public String getVerificaInserir() {
		return this.verificaInserir;
	}

	public void setVerificaInserir(String verificaInserir) {
		this.verificaInserir = verificaInserir;
	}

	public String getVerificaSalvar() {
		return this.verificaSalvar;
	}

	public void setVerificaSalvar(String verificaSalvar) {
		this.verificaSalvar = verificaSalvar;
	}

	public String getVerificaEliminar() {
		return this.verificaEliminar;
	}

	public void setVerificaEliminar(String verificaEliminar) {
		this.verificaEliminar = verificaEliminar;
	}

	public String getVerificaAdmin() {
		return verificaAdmin;
	}

	public void setVerificaAdmin(String verificaAdmin) {
		this.verificaAdmin = verificaAdmin;
	}

	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public RegraDAO getRegraDAO() {
		return regraDAO;
	}

	public void setRegraDAO(RegraDAO regraDAO) {
		this.regraDAO = regraDAO;
	}
	
	

}
