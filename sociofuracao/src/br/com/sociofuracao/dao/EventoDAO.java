package br.com.sociofuracao.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.sociofuracao.model.Evento;

@Local
public interface EventoDAO {
	
	public List<Evento> findAll();
	public void salvar(Evento evento);
	public Evento findById(Long id);
	public void remove(Evento evento);
	public List<Evento> getAll();
	public Evento getById(Long id);
}
