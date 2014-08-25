package br.com.sociofuracao.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import br.com.sociofuracao.dao.CidadeDAO;
import br.com.sociofuracao.dao.EstadoDAO;
import br.com.sociofuracao.dao.PaisDAO;
import br.com.sociofuracao.dao.TipoUsuarioDAO;
import br.com.sociofuracao.dao.UsuarioDAO;
import br.com.sociofuracao.form.UsuarioForm;
import br.com.sociofuracao.model.Cidade;
import br.com.sociofuracao.model.Estado;
import br.com.sociofuracao.model.Pais;
import br.com.sociofuracao.model.TipoUsuario;
import br.com.sociofuracao.model.Usuario;
import br.com.sociofuracao.util.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginController {

	private Usuario usuario = new Usuario();

	@EJB
	private UsuarioDAO usuarioDAO;

	@EJB
	private PaisDAO paisDAO;

	@EJB
	private EstadoDAO estadoDAO;
	
	@EJB
	private CidadeDAO cidadeDAO;
	
	@EJB
	private TipoUsuarioDAO tipoUsuarioDAO;
	
	public final static String BEAN_NAME = "loginController";

	private int msg_erro = 0;

	private static Usuario sessionUser;

	private String ultimoAcesso;

	private String email;

	private String senha;

	private String dataCadastro;

	private UsuarioForm usuarioForm;

	private String senha_one;

	private String senha_two;

	private HashMap<String, Object> lstTiposSocios = new HashMap<String, Object>();
	
	private HashMap<String, Object> lstPaises = new HashMap<String, Object>();

	private HashMap<String, Object> lstEstados = new HashMap<String, Object>();

	private HashMap<String, Object> lstCidades = new HashMap<String, Object>();

	public LoginController() {
		usuario = new Usuario();
		msg_erro = 0;
	}

	public String login() {
		msg_erro = 0;
		usuario = new Usuario();
		usuario = usuarioDAO.login(email, senha);
		if (usuario == null) {
			msg_erro = 2;
			RequestContext.getCurrentInstance().addCallbackParam("msg_erro",
					msg_erro);
			usuario = new Usuario();
			return "#";
		}
		if(usuario.getDataacesso() != null){
			ultimoAcesso = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(usuario
				.getDataacesso().getTime());
		}else{
			ultimoAcesso = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
		}
		
		if(usuario.getDatacadastro() == null){
			dataCadastro = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
		}else{
			dataCadastro = new SimpleDateFormat("dd/MM/yyyy").format(usuario
				.getDatacadastro().getTime());
		}
		setSessionUser(usuario);
		usuario.setDataacesso(new Date());
		usuarioDAO.salvar(usuario);
		return "/member/index.xhtml?faces-redirect=true";
	}
	
	public String voltar(){
		return "/index.xhtml?faces-redirect=true";
	}

	public String cadastrarUsuario() {
		usuarioForm = new UsuarioForm();
		return "/cadastro/cadastrousuario.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getMsg_erro() {
		return msg_erro;
	}

	public void setMsg_erro(int msg_erro) {
		this.msg_erro = msg_erro;
	}

	public static Usuario getSessionUser() {
		return sessionUser;
	}

	public static void setSessionUser(Usuario sessionUser) {
		LoginController.sessionUser = sessionUser;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public String getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public UsuarioForm getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(UsuarioForm usuarioForm) {
		this.usuarioForm = usuarioForm;
	}

	public String getSenha_one() {
		return senha_one;
	}

	public void setSenha_one(String senha_one) {
		this.senha_one = senha_one;
	}

	public String getSenha_two() {
		return senha_two;
	}

	public void setSenha_two(String senha_two) {
		this.senha_two = senha_two;
	}

	public HashMap<String, Object> getLstPaises() {
		lstPaises = new HashMap<String, Object>();
		for (Pais pais : paisDAO.getPaises()) {
			lstPaises.put(pais.getNome(), pais.getSigla());
		}
		return lstPaises;
	}

	public void setLstPaises(HashMap<String, Object> lstPaises) {
		this.lstPaises = lstPaises;
	}

	public HashMap<String, Object> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(HashMap<String, Object> lstEstados) {
		this.lstEstados = lstEstados;
	}

	@SuppressWarnings("unchecked")
	public void handleEstado() {
		lstEstados = new HashMap<String, Object>();

		if (usuarioForm.getPais() != null && !usuarioForm.getPais().isEmpty()) {
			Pais pais = paisDAO.findBySigla(usuarioForm.getPais());
			if (pais != null) {
				if (pais.getEstados() != null && pais.getEstados().size() > 0) {
					for (Estado e : pais.getEstados()) {
						lstEstados.put(e.getNome(), e.getSigla());
					}
				}
			}
		}

		if (lstEstados != null && lstEstados.size() > 0) {
			lstEstados = sortByValues(lstEstados);
		}

	}

	@SuppressWarnings("unchecked")
	public void handleCidade() {
		lstCidades = new HashMap<String, Object>();

		if (usuarioForm.getEstado() != null
				&& !usuarioForm.getEstado().isEmpty()) {
			Estado estado = estadoDAO.findBySigla(usuarioForm.getEstado());
			if (estado != null) {
				if (estado.getCidades() != null
						&& estado.getCidades().size() > 0) {
					for (Cidade c : estado.getCidades()) {
						lstCidades.put(c.getNome(), c.getNome());
					}
				}
			}
		}

		if (lstCidades != null && lstCidades.size() > 0) {
			lstCidades = sortByValues(lstCidades);
		}

	}

	public HashMap<String, Object> getLstCidades() {
		return lstCidades;
	}

	public void setLstCidades(HashMap<String, Object> lstCidades) {
		this.lstCidades = lstCidades;
	}

	public String salvarUsuario() {

		Usuario usuario = usuarioDAO.findByCpf(usuarioForm.getCpf());

		if (usuario != null) {
			FacesUtil.addMessageErro("Usuario já existe cadastrado!");
			return null;
		}
		
		if(!senha_one.equals(senha_two)){
			FacesUtil.addMessageErro("Senha não conferem!");
			return null;
		}

		usuario = new Usuario();

		try {
			usuario.setBairro(usuarioForm.getBairro());
			usuario.setCelular(usuarioForm.getCelular());
			usuario.setCep(usuarioForm.getCep());
			if(usuarioForm.getCidade() != null && !usuarioForm.getCidade().isEmpty()){
				Cidade cidade = cidadeDAO.findByCidade(usuarioForm.getCidade());
				usuario.setCidade(cidade);
			}
			
			if(usuarioForm.getTiposocio() != null){
				TipoUsuario tipoUsuario = tipoUsuarioDAO.findById(usuarioForm.getTiposocio());
				usuario.setTipousuario(tipoUsuario);
			}
			
			usuario.setComplemento(usuarioForm.getComplemento());
			usuario.setCpf(usuarioForm.getCpf());
			usuario.setDatacadastro(new Date());

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				if (usuarioForm.getDatanascimento() != null
						&& !usuarioForm.getDatanascimento().isEmpty()) {
					usuario.setDatanascimento(df.parse(usuarioForm
							.getDatanascimento()));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			usuario.setEmail(usuarioForm.getEmail());
			usuario.setEndereco(usuarioForm.getEndereco());
			usuario.setEstado(usuarioForm.getEstado());
			usuario.setFacebook(usuarioForm.getFacebook());
			usuario.setInstagran(usuarioForm.getInstagran());
			usuario.setNome(usuarioForm.getNome());
			usuario.setNumero(usuarioForm.getNumero());
			usuario.setPais(usuarioForm.getPais());
			usuario.setRg(usuarioForm.getRg());
			usuario.setSenha(senha_one);
			usuario.setSexo(usuarioForm.getSexo());
			usuario.setTelefone(usuario.getTelefone());
			usuario.setTwitter(usuarioForm.getTwitter());
			usuarioDAO.salvar(usuario);

		} catch (Exception e) {
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}

		return "/cadastro/result.xhtml?faces-redirect=true";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap sortByValues(HashMap map) {
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

	public HashMap<String, Object> getLstTiposSocios() {
		List<TipoUsuario> lst = tipoUsuarioDAO.findAll();
		lstTiposSocios = new HashMap<String, Object>();
		for(TipoUsuario u : lst){
			lstTiposSocios.put(u.getDescricao(), u.getIdtiposocio());
		}
		
		return lstTiposSocios;
	}

	public void setLstTiposSocios(HashMap<String, Object> lstTiposSocios) {
		this.lstTiposSocios = lstTiposSocios;
	}
	
	

}