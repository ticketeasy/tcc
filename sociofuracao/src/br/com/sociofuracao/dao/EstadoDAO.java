package br.com.sociofuracao.dao;

import javax.ejb.Local;

import br.com.sociofuracao.model.Estado;

@Local
public interface EstadoDAO {
	public Estado findBySigla(String sigla);
}
