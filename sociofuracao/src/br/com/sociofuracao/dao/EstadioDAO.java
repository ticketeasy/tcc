package br.com.sociofuracao.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.sociofuracao.model.Estadio;

@Local
public interface EstadioDAO {
	
	public List<Estadio> findAll();
	public Estadio findById(Long id);

}
