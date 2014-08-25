package br.com.ementas.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ementas.model.Curso;

@Local
public interface CursoDAO {
	
	public List<Curso> lstCurso();
	public Curso getById(Long id);
	public void salvar(Curso curso);

}
