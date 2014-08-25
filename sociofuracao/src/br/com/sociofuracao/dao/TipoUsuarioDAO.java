package br.com.sociofuracao.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.sociofuracao.model.TipoUsuario;

@Local
public interface TipoUsuarioDAO {
	
	public List<TipoUsuario> findAll();
	
	public TipoUsuario findById(Long id);

}
