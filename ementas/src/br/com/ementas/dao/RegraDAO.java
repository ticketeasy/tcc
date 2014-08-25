package br.com.ementas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ementas.model.Regra;

@Local
public interface RegraDAO {
	
	public List<Regra> lstRegras();
	public Regra getById(Long id);
	public void salvar(Regra regra);
	public Regra getNome(String desc);

}
