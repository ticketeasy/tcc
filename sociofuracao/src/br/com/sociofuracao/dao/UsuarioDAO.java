package br.com.sociofuracao.dao;

import javax.ejb.Local;

import br.com.sociofuracao.model.Usuario;

@Local
public interface UsuarioDAO{
	
	public Usuario login(String email, String senha);
	
	public void salvar(Usuario usuario);
	
	public Usuario findByCpf(String cpf);
	
}
