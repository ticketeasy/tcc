package br.com.ementas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ementas.model.EixoFormacao;

@Local
public interface EixoFormacaoDAO {
	
	public List<EixoFormacao> lstEixoFormacao();
	public EixoFormacao getById(Long id);
	public void salvar(EixoFormacao eixo);
}
