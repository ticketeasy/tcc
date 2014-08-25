package br.com.ementas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ementas.model.Usuario;

@Local
public interface UsuarioDAO {
	
	public Usuario findByUsuario(String usuario, String senha);
	
	public List<Usuario> lstUsuarios();
	
	public Usuario getById(Long id);
	public void salvar(Usuario usuario);
	

}
