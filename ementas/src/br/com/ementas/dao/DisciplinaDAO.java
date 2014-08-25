package br.com.ementas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ementas.model.Disciplina;

@Local
public interface DisciplinaDAO {
	
	public List<Disciplina> lstDisciplina();
	public Disciplina getById(Long id);
	public void salvar(Disciplina disciplina);

}
