package br.com.sociofuracao.dao;

import javax.ejb.Local;

import br.com.sociofuracao.model.Cidade;

@Local
public interface CidadeDAO {
	
	public Cidade findByCidade(String cidade);

}
