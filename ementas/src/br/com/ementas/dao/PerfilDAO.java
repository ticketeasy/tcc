package br.com.ementas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ementas.model.Perfil;

@Local
public interface PerfilDAO {

	public List<Perfil> lstPerfis();
	public Perfil getById(Long id);
	public void salvar(Perfil perfil);
	public Perfil findById(Long id);
	
	
}
