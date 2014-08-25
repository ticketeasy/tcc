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
public class UsuarioController {

	@EJB
	private PaisDAO paisDAO;

	@EJB
	private EstadoDAO estadoDAO;

	@EJB
	private UsuarioDAO usuarioDAO;
	
	@EJB
	private CidadeDAO cidadeDAO;
	
	@EJB
	private TipoUsuarioDAO tipoUsuarioDAO;

	private UsuarioForm usuarioForm;
	
	private HashMap<String, Object> lstTiposSocios = new HashMap<String, Object>();

	private HashMap<String, Object> lstPaises = new HashMap<String, Object>();

	private HashMap<String, Object> lstEstados = new HashMap<String, Object>();

	private HashMap<String, Object> lstCidades = new HashMap<String, Object>();
	
	private String senha_one;

	private String senha_two;

	@SuppressWarnings("unchecked")
	public String atualizaCadastro(){
		usuarioForm = new UsuarioForm();
		if(LoginController.getSessionUser() != null){
			Usuario usuario = usuarioDAO.findByCpf(LoginController.getSessionUser().getCpf());
			if(usuario != null){
				if(usuario.getSenha() != null){
					senha_one = senha_two = usuario.getSenha();
				}
				if(usuario.getBairro() != null)
					usuarioForm.setBairro(usuario.getBairro());
				if(usuario.getCelular() != null)
					usuarioForm.setCelular(usuario.getCelular());
				if(usuario.getCep() != null)
					usuarioForm.setCep(usuario.getCep());
				if(usuario.getCidade() != null)
					usuarioForm.setCidade(usuario.getCidade().getNome());
				if(usuario.getComplemento() != null)
					usuarioForm.setComplemento(usuario.getComplemento());
				if(usuario.getCpf() != null)
					usuarioForm.setCpf(usuario.getCpf());
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				if (usuario.getDatanascimento() != null) {
					usuarioForm.setDatanascimento(df.format(usuario.getDatanascimento()));
				}
				
				if(usuario.getTipousuario() != null){
					usuarioForm.setTiposocio(usuario.getTipousuario().getIdtiposocio());
				}
				if(usuario.getEmail() != null)
					usuarioForm.setEmail(usuario.getEmail());
				if(usuario.getEndereco() != null)
					usuarioForm.setEndereco(usuario.getEndereco());
				if(usuario.getEstado() != null)
					usuarioForm.setEstado(usuario.getEstado());
				if(usuario.getFacebook() != null)
					usuarioForm.setFacebook(usuario.getFacebook());
				if(usuario.getInstagran() != null)
					usuarioForm.setInstagran(usuario.getInstagran());
				if(usuario.getNome() != null)
					usuarioForm.setNome(usuario.getNome());
				if(usuario.getNumero() != null)
					usuarioForm.setNumero(usuario.getNumero());
				if(usuario.getPais() != null)
					usuarioForm.setPais(usuario.getPais());
				if(usuario.getRg() != null)
					usuarioForm.setRg(usuario.getRg());
				if(usuario.getSexo() != null)
					usuarioForm.setSexo(usuario.getSexo());
				if(usuario.getTelefone() != null)
					usuarioForm.setTelefone(usuario.getTelefone());
				if(usuario.getTwitter() != null)
					usuarioForm.setTwitter(usuario.getTwitter());
			}

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
		return "/member/cadastro/editusuario.xhtml?faces-redirect=true";
	}

	public UsuarioForm getUsuarioForm() {
		return usuarioForm;
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

	public void setUsuarioForm(UsuarioForm usuarioForm) {
		this.usuarioForm = usuarioForm;
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

	public HashMap<String, Object> getLstCidades() {
		return lstCidades;
	}

	public void setLstCidades(HashMap<String, Object> lstCidades) {
		this.lstCidades = lstCidades;
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

	public String salvarUsuario() {

		Usuario usuario = usuarioDAO.findByCpf(usuarioForm.getCpf());
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
			usuario.setEndereco(usuarioForm.getEndereco());
			usuario.setEstado(usuarioForm.getEstado());
			usuario.setFacebook(usuarioForm.getFacebook());
			usuario.setInstagran(usuarioForm.getInstagran());
			usuario.setNome(usuarioForm.getNome());
			usuario.setNumero(usuarioForm.getNumero());
			usuario.setPais(usuarioForm.getPais());
			usuario.setRg(usuarioForm.getRg());
			
			usuario.setSexo(usuarioForm.getSexo());
			usuario.setTelefone(usuarioForm.getTelefone());
			usuario.setTwitter(usuarioForm.getTwitter());
			usuarioDAO.salvar(usuario);

		} catch (Exception e) {
			FacesUtil.addMessageErro(e.getMessage());
			return null;
		}
		FacesUtil.addMessage("Usuario autualizado com sucesso...");
		return "#";
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
	
	public String cadastrarEventos(){
		return "/member/cadastro/cadastrarevento.xhtml?faces-redirect=true";
	}
	
	public String eventosDisponiveis(){
		return "/member/eventos/eventosdisponiveis.xhtml?faces-redirect=true";
	}
	
}
