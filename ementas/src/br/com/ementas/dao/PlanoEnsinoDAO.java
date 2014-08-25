package br.com.ementas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ementas.model.PlanoEnsino;

@Local
public interface PlanoEnsinoDAO {
	public List<PlanoEnsino> lstPlanoEnsino();
	public PlanoEnsino getById(Long id);
	public void salvar(PlanoEnsino planoEnsino);
}
