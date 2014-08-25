package br.com.sociofuracao.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.sociofuracao.model.Lugar;

@Local
public interface LugarDAO {
	
	public List<Lugar> lstLugar(String email);
	public Lugar findById(Long id);
	public void salvar(Lugar lugar);

}
