package br.com.sociofuracao.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.sociofuracao.model.Pais;

@Local
public interface PaisDAO {
	
	public List<Pais> getPaises();
	public Pais findBySigla(String sigla);

}
