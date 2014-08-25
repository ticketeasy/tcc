package br.com.ementas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ementas.model.Professor;

@Local
public interface ProfessorDAO {
	
	public List<Professor> lstProfessores();
	public Professor getById(Long id);
	public void salvar(Professor professor);

}
